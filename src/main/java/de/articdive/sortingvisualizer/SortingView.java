package de.articdive.sortingvisualizer;

import de.articdive.sortingvisualizer.sortings.BubbleSort;
import de.articdive.sortingvisualizer.sortings.SortingAlgorithm;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import org.jetbrains.annotations.NotNull;

import java.util.*;

/**
 * @author Articdive
 */
public final class SortingView extends Canvas {
    private final SortingVisualizer sortingVisualizer;
    private int[] data = null;
    private boolean sorting;
    private boolean stairMode = true;
    private SortingAlgorithm sortingAlgorithm;

    public SortingView(@NotNull SortingVisualizer sortingVisualizer) {
        super();
        this.sortingVisualizer = sortingVisualizer;
        this.sortingAlgorithm = new BubbleSort(this);
    }

    public void generateData() {
        data = new int[sortingVisualizer.ARRAY_SIZE];
        // Random Level Data
        if (!stairMode) {
            int SPECTRUM_SIZE = sortingVisualizer.SPECTRUM_SIZE;

            Random rnd = new Random();
            for (int i = 0; i < sortingVisualizer.ARRAY_SIZE; i++) {
                data[i] = rnd.nextInt(SPECTRUM_SIZE);
            }
        } else {
            List<Integer> ints = new ArrayList<>();
            for (int i = 0; i < sortingVisualizer.ARRAY_SIZE; i++) {
                ints.add(i);
            }
            Collections.shuffle(ints);
            data = ints.stream().mapToInt(value -> value).toArray();
        }
        drawData();
    }

    public void clear() {
        data = null;
        drawData();
    }

    public void drawData() {
        // Setup background and remove any previous drawings.
        GraphicsContext gc = this.getGraphicsContext2D();
        gc.setFill(Color.web("#0f4c75"));
        gc.fillRect(20, 20, getWidth() - 40, getHeight());

        if (data != null) {
            double currentWidth = 30;
            double rectangleWidth = (getWidth() - 60) / sortingVisualizer.ARRAY_SIZE;
            double heightScale = (getHeight() - 30) / Arrays.stream(data).max().orElseThrow();

            // gc.setFill(Color.web("#bbe1fa"));
            gc.setFill(Color.WHITE);
            for (int i : data) {
                gc.fillRect(currentWidth, 20, rectangleWidth, i * heightScale);
                currentWidth = currentWidth + rectangleWidth;
            }
        }
    }

    public void beginSorting() {
        if (data != null) {
            sorting = true;
            sortingAlgorithm.sort(data);
        }
    }

    public void endSorting() {
        sortingAlgorithm.stopThread();
        sorting = false;
    }

    public boolean isSorting() {
        return sorting;
    }

    public void setSorting(boolean sorting) {
        this.sorting = sorting;
    }

    public void setStairMode(boolean stairMode) {
        this.stairMode = stairMode;
    }

    public void setSortingAlgorithm(SortingAlgorithm sortingAlgorithm) {
        this.sortingAlgorithm = sortingAlgorithm;
    }
}
