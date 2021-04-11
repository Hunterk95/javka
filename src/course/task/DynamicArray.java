package course.task;

/**
 * Динамический массив
 */
public class DynamicArray extends StaticArray implements Dynamic {

    private static final int DEFAULT_CAPACITY = 10;
    private static final float GROW_FACTOR = 1.5f;

    /**
     * текущая длина массива
     */
    private int size;

    public DynamicArray() {
        super(new int[DEFAULT_CAPACITY]);
        size = 0;
    }

    public DynamicArray(int[] array) {
        super(array);
        size = array.length;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void add(int value) {
        // TODO: добавить элемент в конец массива
        if (size == array.length) {
            int[] newArray = new int[(int) (size * GROW_FACTOR)];
            for (int i = 0; i < size; i++)
                newArray[i] = array[i];
            array = newArray;
        }
        array[size] = value;
        size++;
    }

    @Override
    public void add(int index, int value) {
        // TODO: добавить элемент в указанный индекс (остальные сдвинуть вправо)
        if (size == array.length) {
            int[] newArray = new int[(int) (size * GROW_FACTOR)];
            int shift = 0;
            for (int i = 0; i < size + 1; i++) {
                if (i == index) {
                    newArray[i] = value;
                    shift = 1;
                } else newArray[i] = array[i - shift];
            }
            array = newArray;
            size++;
        } else {
            int tmp = value;
            int tmp2;
            for (int i = index; i < size + 1; i++) {
                tmp2 = array[i];
                array[i] = tmp;
                tmp = tmp2;
            }
            size++;
        }
    }

    @Override
    public void addAll(Array array) {
        // TODO: конкатенация с переданным массивом
        if (this.array.length <= (size + array.size())) {
            int[] newArray = new int[size + array.size()];
            for (int i = 0; i < size; i++)
                newArray[i] = this.array[i];
            this.array = newArray;
        }
        for (int i = size; i < (size + array.size()); i++)
            this.array[i] = array.get(i - size);
        size += array.size();
    }

    @Override
    public boolean remove(int value) {
        // TODO: удалить элемент из массива
        if (super.contains(value)) {
            int shift = 0;
            for (int i = 0; i < size; i++) {
                if (array[i] == value){
                    array[i] = 0;
                    shift++;
                    continue;
                }
                if(shift>0){
                    array[i-shift] = array[i];
                    array[i] = 0;
                }
            }
            size -= shift;
            return true;
        }
        return false;
    }

    @Override
    public boolean removeAll(int[] values) {
        // TODO: удалить все указанные элементы из массива
        boolean contains = false;
        for(int current:values)
            if(remove(current)) contains = true;
        return contains;
    }

    @Override
    public int removeOf(int index) {
        // удалить элемент по индексу
        int oldValue = array[index];
        for (int i = index; i < size-1; i++)
            array[i] = array[i+1];
        array[size] = 0;
        size --;
        return oldValue;
    }
}
