package algorithms;



import dataStructure.LinkedList;

public class MergeSort<T extends Comparable<T>> {

    public LinkedList<T> sort(LinkedList<T> list) {

        if (list == null || list.getSize() <= 1) {
            return list;
        }

        int middle = list.getSize() / 2;

        LinkedList<T> left = new LinkedList<>();
        LinkedList<T> right = new LinkedList<>();

        for (int i = 0; i < middle; i++) {
            left.addLast(list.get(i));
        }

        for (int i = middle; i < list.getSize(); i++) {
            right.addLast(list.get(i));
        }

        left = sort(left);
        right = sort(right);

        return merge(left, right);
    }

    private LinkedList<T> merge(LinkedList<T> left,
                                LinkedList<T> right) {

        LinkedList<T> result = new LinkedList<>();

        int leftIndex = 0;
        int rightIndex = 0;

        while (leftIndex < left.getSize() &&
               rightIndex < right.getSize()) {

            T leftItem = left.get(leftIndex);
            T rightItem = right.get(rightIndex);

            if (leftItem.compareTo(rightItem) <= 0) {

                result.addLast(leftItem);
                leftIndex++;

            } else {

                result.addLast(rightItem);
                rightIndex++;
            }
        }

        while (leftIndex < left.getSize()) {

            result.addLast(left.get(leftIndex));
            leftIndex++;
        }

        while (rightIndex < right.getSize()) {

            result.addLast(right.get(rightIndex));
            rightIndex++;
        }

        return result;
    }

}