package course.task;

/**
 * Обертка над статическим массивом
 */
public class StaticArray implements Array {

    protected int[] array;

    public StaticArray(int[] a) {
        this.array = new int[a.length];
        System.arraycopy(a, 0, this.array, 0, a.length);
    }

    @Override
    public int size() {
        // TODO: вернуть длину массива
        return array.length;
    }

    @Override
    public boolean contains(int value) {
        // TODO: проверить, что элемент есть в массиве
        for (int current : array)
            if (current == value) return true;
        return false;
    }

    @Override
    public int get(int index) {
        // TODO: получить элемент по индексу
        return array[index];
    }

    @Override
    public int set(int index, int value) {
        // TODO: присвоить значение по индексу. Вернуть значение элемента, которое заменили
        int old = array[index];
        array[index] = value;
        return old;
    }

    @Override
    public int indexOf(int value) {
        // TODO: получить индекс первого подходящего элемента
        for (int i = 0; i < this.size(); i++)
            if (array[i] == value) return i;
        return -1;
    }

    @Override
    public int lastIndexOf(int value) {
        // TODO: получить индекс последнего подходящего элемента
        for (int i = (this.size() - 1); i >= 0; i--)
            if (array[i] == value) return i;
        return -1;
    }

    @Override
    public void reverse() {
        // TODO: перевернуть массив
        for (int i = 0; i < (this.size() / 2); i++) {
            swap(array, i, size() - i - 1);
        }
    }

    @Override
    public Array subArray(int fromIndex, int toIndex) {
        // TODO: вернуть подмассив, начиная с индекса fromIndex включительно и заканчивая индексом toIndex невкоючительно
        if (fromIndex == 0 && toIndex == array.length) return this;
        if (fromIndex < 0 || toIndex <= fromIndex || fromIndex + toIndex > array.length) return null;
        int[] tempArray = new int[toIndex - fromIndex];
        for (int i = 0; i < (toIndex - fromIndex); i++) {
            tempArray[i] = array[fromIndex + i];
        }
        StaticArray newArray = new StaticArray(tempArray);
        return newArray;
    }

    @Override
    public void sort() {
        bubbleSort();
    }

    @Override
    public void sort(ArraySort sort) {
        switch (sort) {
            case BUBBLE:
                bubbleSort();
                break;
            case INSERTION:
                insertionSort();
                break;
            case SELECTION:
                selectionSort();
                break;
            case MERGE:
                mergeSort();
                break;
            case QUICK:
                quickSort();
                break;
            default:
                sort();
        }
    }

    @Override
    public String toString() {
        // TODO: вернуть массив в виде строки. Например, [3, 4, 6, -2]
        String string = new String();
        for (int current : array)
            string = string + current;
        return string;
    }

    private void bubbleSort() {
        // TODO: сортировка пузырьком (по возрастанию)
        boolean needToSort = true;
        while (needToSort) {
            needToSort = false;
            for (int i = 1; i < array.length; i++) {
                if (array[i - 1] > array[i]) {
                    needToSort = true;
                    swap(array, i, i - 1 );
                }
            }
        }
    }

    private void insertionSort() {
        //TODO*: сортировка вставками (по возрастанию)
        for (int i = 1; i < array.length; i++) {
            for (int j = i; j > 0 || array[j] > array[j - 1]; j--) {
                swap(array, j, j-1);
            }
        }
    }


    private void selectionSort() {
        //TODO*: сортировка выбором (по возрастанию)
        int min;
        int minIndex;
        for (int i = 0; i < array.length - 1; i++) {
            min = array[i];
            minIndex = i;
            for (int j = i + 1; j < array.length; j++) {
                if (min > array[j]) {
                    min = array[j];
                    minIndex = j;
                }
            }
            array[minIndex] = array[i];
            array[i] = min;
        }
    }

    private void mergeSort() {
        //TODO**: сортировка слиянием (по возрастанию)
        mergeSortRec(this);
    }

    private Array mergeSortRec(Array staticArray) {
        if (staticArray.size() == 1) return staticArray;
        var subArray1 = mergeSortRec(staticArray.subArray(0, staticArray.size() / 2));
        var subArray2 = mergeSortRec(staticArray.subArray(staticArray.size() / 2, staticArray.size()));
        int[] resultArray = new int[subArray1.size() + subArray2.size()];
        int i = 0, j = 0;
        while (i + j < resultArray.length) {
            if (i == subArray1.size()) resultArray[i + j] = subArray2.get(j++);
            if (j == subArray2.size()) resultArray[i + j] = subArray1.get(i++);
            if (subArray1.get(i) < subArray2.get(j)) resultArray[i + j] = subArray1.get(i++);
            else resultArray[i + j] = subArray1.get(j++);
        }
        var result = new StaticArray(resultArray);
        return result;
    }

    private void quickSort() {
        //TODO**: быстрая сортировка (по возрастанию)
        quickSortRec(0, array.length);
    }

    private void quickSortRec(int start, int end) {
        if (end - start <= 1) return;
        int base = (array[start] + array[end/2] + array[end-1]) / 3;
        int lowIndex = start;
        int equalIndex = start;
        for (int i = start; i < end; i++) {
            if (array[i] < base){
                if ( i <= lowIndex) {
                    lowIndex++;
                    equalIndex++;
                } else{
                    lowIndex++;
                    equalIndex++;
                    for(int j = i; j >= lowIndex; j--){
                        swap(array, j, j-1);
                    }

                }
            } else if( array[i] == base){
                if ( i <= equalIndex) {
                    equalIndex++;
                } else{
                    equalIndex++;
                    for(int j = i; j >= equalIndex; j--){
                        swap(array, j, j-1);
                    }
                }
            }

        }
        if (start == lowIndex || equalIndex == end){
            quickSortRec(start, start + (end - start)/2);
            quickSortRec(start + (end - start)/2, end);
        } else {
            quickSortRec(start, lowIndex);
            quickSortRec(equalIndex, end);
        }

    }

    public boolean isAscSorted() {
        // TODO: проверить, что массив отсортирован по возрастанию
        for (int i = 1; i < array.length; i++)
            if (array[i - 1] > array[i]) return false;
        return true;
    }

    private void swap (int [] array, int index1, int index2){
        int temp = array[index1];
        array[index1] = array[index2];
        array[index2] = temp;
    }
}
