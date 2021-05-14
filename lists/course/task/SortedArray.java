package course.task;

/**
 * Сортированный статический массив (по возрастанию).
 * <p>
 * Любая операция должна гарантировать, что массив, по ее окончании, отстортирован
 */
public class SortedArray extends StaticArray {

    public SortedArray(int[] array) {
        super(array);
        if (!isAscSorted()) {
            sort();
        }
    }

    @Override
    public boolean contains(int value) {
        return binarySearch(value, 0, array.length) != -1;
    }

    @Override
    public int set(int index, int value) {
        // TODO: присовить значение по индексу
        int oldValue = array[index];
        array[index] = value;
        return oldValue;
    }

    @Override
    public int indexOf(int value) {
        return binarySearch(value, 0, array.length - 1);
    }

    @Override
    public int lastIndexOf(int value) {
        // TODO: получить индекс последнего подходящего элемента
        int index = this.indexOf(value);
        for (int i = index; array[i] == value; i++)
            index = i;
        return index;
    }

    @Override
    public void sort() {
        super.sort();
    }

    @Override
    public void sort(ArraySort sort) {
        super.sort(sort);
    }

    private int binarySearch(int value, int left, int right) {
        // TODO: реализовать бинарный поиск
        int tempIndex = left + (right - left) / 2;
        while (left < right) {
            if (array[tempIndex] > value) right = tempIndex;
            else if (array[tempIndex] < value) left = tempIndex;
            else return tempIndex;
        }
        if (array[left] == value) return left;
        return -1;
    }

    public SortedArray merge(SortedArray other) {
        // TODO: произвести слиянеие двух сортированных массивов
        int[] tempArray = new int[array.length + other.size()];
        int ourIndex = 0;
        int otherIndex = 0;
        for (int i = 0; i < tempArray.length; i++) {
            if (array[ourIndex] <= other.get(otherIndex)) {
                tempArray[i] = array[ourIndex];
                ourIndex++;
            } else {
                tempArray[i] = other.get(otherIndex);
                otherIndex++;
            }
        }
        return this;
    }

    public SortedArray mergeAll(SortedArray... others) {
        // TODO: произвести слиянеие N + 1 сортированных массивов
        for (SortedArray other : others)
            merge(other);
        return this;
    }
}
