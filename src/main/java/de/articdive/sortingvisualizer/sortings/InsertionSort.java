package de.articdive.sortingvisualizer.sortings;

import de.articdive.sortingvisualizer.SortingView;

import java.util.concurrent.TimeUnit;

public class InsertionSort extends SortingAlgorithm{
    public InsertionSort(SortingView view) {
        super(view);
    }

    @Override
    public void sort(int[] data) {
        currentThread = new Thread(() -> {
            int n = data.length;
            for (int i = 1; i < n; ++i) {
                int key = data[i];
                int j = i - 1;

            /* Move elements of arr[0..i-1], that are
               greater than key, to one position ahead
               of their current position */
                while (j >= 0 && data[j] > key) {
                    try {
                        TimeUnit.MILLISECONDS.sleep(10);
                    } catch (InterruptedException e) {
                        return;
                    }

                    data[j + 1] = data[j];
                    j = j - 1;

                    view.drawData();
                }
                data[j + 1] = key;
            }
            view.setSorting(false);
        });
        currentThread.start();
    }
}
