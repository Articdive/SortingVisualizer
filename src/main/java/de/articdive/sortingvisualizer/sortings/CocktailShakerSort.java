package de.articdive.sortingvisualizer.sortings;

import de.articdive.sortingvisualizer.SortingView;

import java.util.concurrent.TimeUnit;

public class CocktailShakerSort extends SortingAlgorithm{
    public CocktailShakerSort(SortingView view) {
        super(view);
    }

    @Override
    public void sort(int[] data) {
        currentThread = new Thread(() -> {
            boolean swapped = true;
            int start = 0;
            int end = data.length;

            while (swapped) {
                // reset the swapped flag on entering the
                // loop, because it might be true from a
                // previous iteration.
                swapped = false;

                // loop from bottom to top same as
                // the bubble sort
                for (int i = start; i < end - 1; ++i) {
                    if (data[i] > data[i + 1]) {
                        try {
                            TimeUnit.MILLISECONDS.sleep(10);
                        } catch (InterruptedException e) {
                            return;
                        }
                        int temp = data[i];
                        data[i] = data[i + 1];
                        data[i + 1] = temp;
                        swapped = true;
                        view.drawData();
                    }
                }

                // if nothing moved, then array is sorted.
                if (!swapped) {
                    break;
                }

                // otherwise, reset the swapped flag so that it
                // can be used in the next stage
                swapped = false;

                // move the end point back by one, because
                // item at the end is in its rightful spot
                end = end - 1;

                // from top to bottom, doing the
                // same comparison as in the previous stage
                for (int i = end - 1; i >= start; i--) {
                    if (data[i] > data[i + 1]) {
                        try {
                            TimeUnit.MILLISECONDS.sleep(10);
                        } catch (InterruptedException e) {
                            return;
                        }
                        int temp = data[i];
                        data[i] = data[i + 1];
                        data[i + 1] = temp;
                        swapped = true;
                        view.drawData();
                    }
                }

                // increase the starting point, because
                // the last stage would have moved the next
                // smallest number to its rightful spot.
                start = start + 1;
            }
            view.setSorting(false);
        });
        currentThread.start();
    }
}
