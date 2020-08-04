package de.articdive.sortingvisualizer.sortings;

import de.articdive.sortingvisualizer.SortingView;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

public class BogoSort extends SortingAlgorithm {
    public BogoSort(SortingView view) {
        super(view);
    }

    @Override
    public void sort(int[] data) {
        currentThread = new Thread(() -> {
            while (!isSorted(data)) {
                try {
                    TimeUnit.MILLISECONDS.sleep(150);
                } catch (InterruptedException e) {
                    return;
                }

                List<Integer> intList = Arrays.stream(data).boxed().collect(Collectors.toList());

                Collections.shuffle(intList);

                for (int i = 0; i < intList.size(); i++) {
                    data[i] = intList.get(i);
                }
                view.drawData();
            }
            view.setSorting(false);
        });
        currentThread.start();
    }
}
