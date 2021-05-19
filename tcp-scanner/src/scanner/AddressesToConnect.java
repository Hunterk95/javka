package scanner;

import java.util.List;

public class AddressesToConnect extends ListToConnect<Address> {

    public AddressesToConnect(List<Address> addresses) {
        super(addresses);
    }

    @Override
    protected boolean isCheckedControl(int resultIndex) {
        if (units.get(resultIndex) == null) {
            isChecked.set(resultIndex, true);
            return true;
        }
        return false;
    }
}

