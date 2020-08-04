package de.articdive.sortingvisualizer.sortings;

import de.articdive.sortingvisualizer.SortingView;

import java.util.concurrent.TimeUnit;

public class CombSort extends SortingAlgorithm{
    public CombSort(SortingView view) {
        super(view);
    }

    @Override
    public void sort(int[] data) {
        currentThread = new Thread(() -> {
            int n = data.length;

            // initialize gap
            int gap = n;

            // Initialize swapped as true to make sure that
            // loop runs
            boolean swapped = true;

            // Keep running while gap is more than 1 and last
            // iteration caused a swap
            while (gap != 1 || swapped)
            {
                // Find next gap
                gap = getNextGap(gap);

                // Initialize swapped as false so that we can
                // check if swap happened or not
                swapped = false;

                // Compare all elements with current gap
                for (int i=0; i<n-gap; i++)
                {
                    if (data[i] > data[i+gap])
                    {
                        try {
                            TimeUnit.MILLISECONDS.sleep(10);
                        } catch (InterruptedException e) {
                            return;
                        }
                        // Swap arr[i] and arr[i+gap]
                        int temp = data[i];
                        data[i] = data[i+gap];
                        data[i+gap] = temp;

                        // Set swapped
                        swapped = true;
                        view.drawData();
                    }
                }
            }
            view.setSorting(false);
        });
        currentThread.start();
    }

    // To find gap between elements
    int getNextGap(int gap)
    {
        // Shrink gap by Shrink factor
        gap = (gap*10)/13;
        return Math.max(gap, 1);
    }
}
