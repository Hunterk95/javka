package scanner;

import java.util.List;

public class PortsToConnect extends ListToConnect<Integer> {

    public PortsToConnect(List<Integer> ports) {
        super(ports);
    }

    @Override
    protected boolean isCheckedControl(int resultIndex) {
        isChecked.set(resultIndex, true);
        return false;
    }
}
