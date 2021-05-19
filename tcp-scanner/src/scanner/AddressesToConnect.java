package scanner;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class AddressesToConnect {
    private List<Address> addresses;
    private List<Boolean> isChecked;
    private final Random random = new Random();


    public AddressesToConnect(List<Address> addresses) {
        this.addresses = addresses;
        isChecked = new ArrayList<Boolean>(addresses.size());
        for(int i = 0; i < addresses.size(); i++){
            isChecked.add(false);
        }
    }

    public Address getNext() {
        Address resultAddress;
        int startIndex = random.nextInt(addresses.size());
        for (int resultIndex = startIndex; resultIndex < addresses.size(); resultIndex++) {
            if (!isChecked.get(resultIndex)) {
                resultAddress = addresses.get(resultIndex);
                if(resultAddress == null){
                    isChecked.set(resultIndex, true);
                    continue;
                }
                return addresses.get(resultIndex);
            }
        }
        for(int resultIndex = 0; resultIndex < startIndex; resultIndex++) {
            if (!isChecked.get(resultIndex)) {
                resultAddress = addresses.get(resultIndex);
                if(resultAddress == null){
                    isChecked.set(resultIndex, true);
                    continue;
                }
                return addresses.get(resultIndex);
            }
        }
        return null;
    }
}

