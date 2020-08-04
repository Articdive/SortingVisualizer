package de.articdive.sortingvisualizer.sortings;

import de.articdive.sortingvisualizer.SortingView;

import java.util.concurrent.TimeUnit;

public class BubbleSort extends SortingAlgorithm {
    public BubbleSort(SortingView sortingView) {
        super(sortingView);
    }

    @Override
    public void sort(int[] data) {
        currentThread = new Thread(() -> {
            for (int i = 0; i < data.length; i++) {
                for (int j = 0; j < data.length - i - 1; j++) {
                    if (data[j] > data[j + 1]) {
                        try {
                            TimeUnit.MILLISECONDS.sleep(10);
                        } catch (InterruptedException e) {
                            return;
                        }
                        // swap arr[j+1] and arr[i]
                        int temp = data[j];
                        data[j] = data[j + 1];
                        data[j + 1] = temp;
                        view.drawData();
                    }
                }
            }
            view.setSorting(false);
        });
        currentThread.start();
    }
}
