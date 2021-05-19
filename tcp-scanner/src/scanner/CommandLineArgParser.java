package scanner;

import java.net.InetAddress;
import java.util.ArrayList;
import java.util.List;

/**
 * Class for parse command line arguments
 */
public class CommandLineArgParser {
    final private String[] argv;
    private List<Integer> ports = new ArrayList<>();
    private List<Address> addresses;
    private List<InetAddress> inetAddresses = new ArrayList<>();

    public AddressesToConnect getAddresses() {
        return addressesToConnect;
    }

    private AddressesToConnect addressesToConnect;


    public CommandLineArgParser(String[] argv) {
        this.argv = argv;
    }

    //private String filePath;

    public void parse() {
        try {
            if (argv[0].compareTo("scan") != 0) {
                throw new IllegalArgumentException("Not found command: scan");
            }
            for (int i = 1; i < argv.length; i++) {
                switch (argv[i]) {
                    case ("-h"):
                        if (argv.length <= i + 1) {
                            throw new NotFoundArgumentKeyValueException("-h");
                        }
                        i++;
                        if (argv[i].matches(",")) {

                        } else if (argv[i].matches("-")) {

                        } else {
                            inetAddresses.add(InetAddress.getByName(argv[i]));
                        }

                        break;
                    case ("-p"):
                        if (argv.length <= i + 1) {
                            throw new NotFoundArgumentKeyValueException("-p");
                        }
                        i++;
                        if (argv[i].matches(",")) {

                        } else if (argv[i].matches("-")) {

                        } else {
                            ports.add(new Integer(argv[i]));
                        }

                        break;
                    case ("-t"):
                        if (argv.length <= i + 1) {
                            throw new NotFoundArgumentKeyValueException("-t");
                        }
                        i++;

                }
            }
            addresses = new ArrayList<>(inetAddresses.size());
            for (InetAddress currentAddress : inetAddresses) {
                addresses.add(new Address(currentAddress, ports));
            }
            addressesToConnect = new AddressesToConnect(addresses);


        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("WTF " + e.getMessage());
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}

