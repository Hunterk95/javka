package scanner;

import java.net.InetAddress;
import java.util.ArrayList;
import java.util.List;

/**
 * Class for parse command line arguments
 */
public class CommandLineArgParser {
    final private String[] argv;
    private AddressesToConnect addressesToConnect;
    private String filePath;
    private int numOfThreads = 1;

    public CommandLineArgParser(String[] argv) {
        this.argv = argv;
    }

    public AddressesToConnect getAddresses() {
        return addressesToConnect;
    }

    public int getNumOfThreads() {
        return numOfThreads;
    }

    public void parse() {
        List<Integer> ports = new ArrayList<>();
        List<Address> addresses;
        List<InetAddress> inetAddresses = new ArrayList<>();

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
                        for (String argHost : argv[i].split(",")) {
                            if (argv[i].contains("-")) {
                                String addressRange = argv[i].split("\\.")[argv[i].split("\\.").length - 1];
                                String addressBeforeRange = argv[i].split(addressRange)[0];
                                int firstAddress = Integer.parseInt(addressRange.split("-")[0]);
                                int lastAddress = Integer.parseInt(addressRange.split("-")[1]);
                                for (int lastAdrByte = firstAddress; lastAdrByte <= lastAddress; lastAdrByte++) {
                                    inetAddresses.add(InetAddress.getByName(addressBeforeRange + lastAdrByte));
                                }
                            } else {
                                inetAddresses.add(InetAddress.getByName(argHost));
                            }
                        }
                        break;

                    case ("-p"):
                        if (argv.length <= i + 1) {
                            throw new NotFoundArgumentKeyValueException("-p");
                        }
                        i++;
                        for (String argPort : argv[i].split(",")) {
                            if (argv[i].contains("-")) {
                                int firsPort = Integer.parseInt(argv[i].split("-")[0]);
                                int lastPort = Integer.parseInt(argv[i].split("-")[1]);
                                for (int port = firsPort; port <= lastPort; port++) {
                                    ports.add(port);
                                }
                            } else {
                                ports.add(Integer.parseInt(argPort));
                            }
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

