package scanner;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public abstract class ListToConnect<T> {
    protected List<T> units;
    protected List<Boolean> isChecked;
    protected final Random random = new Random();

    /**
     *
     * @param units
     */
    public ListToConnect(List<T> units) {
        this.units = units;
        isChecked = new ArrayList<Boolean>(units.size());
    }

    /**
     *get random unit without repeats
     */
    public T getNext() {
        int startIndex = random.nextInt(units.size());
        for (int resultIndex = startIndex; resultIndex < units.size(); resultIndex++) {
            if (!isChecked.get(resultIndex)) {
                isCheckedControl(resultIndex);
                return units.get(resultIndex);
            }
        }
        for(int resultIndex = 0; resultIndex < startIndex; resultIndex++) {
            if (!isChecked.get(resultIndex)) {
                isCheckedControl(resultIndex);
                return units.get(resultIndex);
            }
        }
        return null;
    }

    protected abstract void isCheckedControl(int prm);
}
