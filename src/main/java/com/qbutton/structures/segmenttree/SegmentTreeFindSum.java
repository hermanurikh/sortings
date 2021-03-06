package com.qbutton.structures.segmenttree;

public class SegmentTreeFindSum {
    private final int[] segTree;
    private final int[] lazyUpdates;
    private final int maxIndex;

    //O(n) construction cost - because the size of segTree will be at max 4 times bigger then arr, and we do constant work per element
    public SegmentTreeFindSum(int[] arr) {
        this.maxIndex = arr.length - 1;
        int nextPowerOf2 = 2;
        while (nextPowerOf2 < arr.length) {
            nextPowerOf2 *= 2;
        }
        this.segTree = new int[2 * nextPowerOf2 - 1];
        this.lazyUpdates = new int[segTree.length];
        //next power of 2, multiply by 2 and subtract 1
        constructTree(arr, 0, maxIndex, 0);
    }

    //O(log(n)) - we are looking in at most 4 directions
    public int findSum(int from, int to) {
        return findSum(from, to, 0, maxIndex, 0);
    }

    public void incrementBy(int delta, int from, int to) {
        incrementBy(delta, from, to, 0, maxIndex, 0);
    }

    private void incrementBy(int delta, int startRange, int endRange, int low, int high, int pos) {
        if (low > high) return;

        int leftChildIdx = pos * 2 + 1;
        int rightChildIdx = pos * 2 + 2;

        updateLazyForPosAndChildren(low, high, pos, leftChildIdx, rightChildIdx);

        //no overlap
        if (startRange > high || endRange < low) {
            return;
        }

        if (startRange <= low && endRange >= high) {
            //total overlap
            segTree[pos] += delta;
            if (low != high) {
                //not a leaf
                lazyUpdates[leftChildIdx] += delta;
                lazyUpdates[rightChildIdx] += delta;
            }
            return;
        }

        //partial overlap
        int mid = (low+high)/2;
        incrementBy(delta, startRange, endRange, low, mid, leftChildIdx);
        incrementBy(delta, startRange, endRange, mid+1, high, rightChildIdx);
        int left = segTree[leftChildIdx];
        int right = segTree[rightChildIdx];
        segTree[pos] = left + right;
    }

    private int findSum(int startRange, int endRange, int low, int high, int pos) {
        if (low > high) return 0;

        int leftChildIdx = pos * 2 + 1;
        int rightChildIdx = pos * 2 + 2;

        updateLazyForPosAndChildren(low, high, pos, leftChildIdx, rightChildIdx);

        if (startRange > high || endRange < low) {
            //no overlap, return pseudo value
            return 0;
        }

        if (startRange <= low && endRange >= high) {
            //total overlap, return current element in segment tree which is min
            return segTree[pos];
        }
        int mid = (low + high) / 2;
        // return minimum of min in left child and min in right child
        var left = findSum(startRange, endRange, low, mid, leftChildIdx);
        var right = findSum(startRange, endRange, mid + 1, high, rightChildIdx);
        return left + right;
    }

    private void updateLazyForPosAndChildren(int low, int high, int pos, int leftChildIdx, int rightChildIdx) {
        if (lazyUpdates[pos] != 0) {
            segTree[pos] += lazyUpdates[pos];
            if (low != high) {
                //not a leaf
                lazyUpdates[leftChildIdx] += lazyUpdates[pos];
                lazyUpdates[rightChildIdx] += lazyUpdates[pos];
            }
            lazyUpdates[pos] = 0;
        }
    }

    private void constructTree(int[] arr, int low, int high, int pos) {
        if (low == high) {
            segTree[pos] = arr[low];
            return;
        }
        //no overflow expected
        int mid = (low + high) / 2;
        int leftChildPos = 2 * pos + 1;
        int rightChildPos = 2 * pos + 2;
        constructTree(arr, low, mid, leftChildPos);
        constructTree(arr, mid + 1, high, rightChildPos);
        int left = segTree[leftChildPos];
        int right = segTree[rightChildPos];
        segTree[pos] = left + right;
    }
}
