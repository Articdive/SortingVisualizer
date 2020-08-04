package de.articdive.sortingvisualizer.sortings;

import de.articdive.sortingvisualizer.SortingView;

public abstract class SortingAlgorithm {
    protected final SortingView view;
    protected Thread currentThread;

    public SortingAlgorithm(SortingView view) {
        this.view = view;
    }

    public abstract void sort(int[] data);

    public boolean isSorted(int[] data) {
        int previousNumber = 0;
        for (int datum : data) {
            if (datum >= previousNumber) {
                previousNumber = datum;
            } else {
                return false;
            }
        }
        return true;
    }

    public void stopThread() {
        if (currentThread != null) {
            currentThread.interrupt();
        }
    }
}
