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
        for(int i = 0; i < units.size(); i++){
            isChecked.add(false);
        }
    }

    /**
     *get random unit without repeats
     */
    public T getNext() {
        int startIndex = random.nextInt(units.size());
        for (int resultIndex = startIndex; resultIndex < units.size(); resultIndex++) {
            if (!isChecked.get(resultIndex)) {
                if(isCheckedControl(resultIndex)){
                    continue;
                }
                return units.get(resultIndex);
            }
        }
        for(int resultIndex = 0; resultIndex < startIndex; resultIndex++) {
            if (!isChecked.get(resultIndex)) {
                if(isCheckedControl(resultIndex)){
                    continue;
                }
                return units.get(resultIndex);
            }
        }
        return null;
    }

    /**
     * Method that check and mark as checked elements of units to avoid units repeats
     * @param prm Index of checked element
     * @return false if you need to return current element or true if you need to go to the next
     */
    protected abstract boolean isCheckedControl(int prm);
}
