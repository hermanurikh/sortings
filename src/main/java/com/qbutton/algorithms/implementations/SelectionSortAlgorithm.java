package com.qbutton.algorithms.implementations;

import com.qbutton.algorithms.common.AbstractSortAlgorithm;

/**
 * Selection sort algorithm implementation (сортировка выбором).
 *
 * @author German Urikh
 * @since version 1.0 on 03.12.15.
 */
public class SelectionSortAlgorithm extends AbstractSortAlgorithm {
    /**
     * {@inheritDoc}
     */
    public void sort(int[] array) {
        if (array == null || array.length == 0) return;
        for (int i = 0; i < array.length - 1; i++) {
            int minimumIndex = i;
            for (int j = i + 1; j < array.length; j++) {
                if (array[j] < array[minimumIndex]) {
                    minimumIndex = j;
                }
            }
            if (i != minimumIndex) {
                swap(array, minimumIndex, i);
            }
        }
    }
}