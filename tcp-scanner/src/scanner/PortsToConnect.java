package scanner;

import java.util.ArrayList;
import java.util.List;

public class PortsToConnect extends ListToConnect<Integer> {
    private List<Boolean> successConnect;
    private int currentPort;

    public PortsToConnect(List<Integer> ports) {
        super(ports);
        successConnect = new ArrayList<>(ports.size());
        for (int i = 0; i < units.size(); i++) {
            successConnect.add(false);
        }
    }

    public void setSuccessConnect(boolean success) {
        successConnect.set(currentPort, success);
    }

    public List<Boolean> getAllConnectionsResult() {
        return successConnect;
    }

    @Override
    protected boolean isCheckedControl(int resultIndex) {
        isChecked.set(resultIndex, true);
        currentPort = resultIndex;
        return false;
    }
}
