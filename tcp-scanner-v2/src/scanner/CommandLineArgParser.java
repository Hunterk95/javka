package scanner;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;

/**
 * Class for parse command line arguments
 */
public class CommandLineArgParser {
    final private String[] argv;
    private ArrayList<InetAddress> hosts = new ArrayList<>();
    private ArrayList<Integer> ports = new ArrayList<>();
    private String filePath;
    private int numOfThreads = 1;

    public ArrayList<InetAddress> getHosts() {
        return hosts;
    }

    public ArrayList<Integer> getPorts() {
        return ports;
    }

    public CommandLineArgParser(String[] argv) {
        this.argv = argv;
    }

    public int getNumOfThreads() {
        return numOfThreads;
    }


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
                        parseHosts(argv[++i]);
                        break;

                    case ("-p"):
                        if (argv.length <= i + 1) {
                            throw new NotFoundArgumentKeyValueException("-p");
                        }
                        parsePorts(argv[++i]);
                        break;

                    case ("-t"):
                        if (argv.length <= i + 1) {
                            throw new NotFoundArgumentKeyValueException("-t");
                        }
                        numOfThreads = Integer.parseInt(argv[++i]);
                        break;

                    default:
                        throw new IllegalArgumentException("Not correct scan arguments");
                }
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("WTF " + e.getMessage());
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private void parseHosts(String hosts) throws UnknownHostException {
        for (String argHost : hosts.split(",")) {
            if (argHost.contains("-")) {
                String addressRange = argHost.split("\\.")[argHost.split("\\.").length - 1];
                String addressBeforeRange = argHost.split(addressRange)[0];
                int firstAddress = Integer.parseInt(addressRange.split("-")[0]);
                int lastAddress = Integer.parseInt(addressRange.split("-")[1]);
                for (int lastAdrByte = firstAddress; lastAdrByte <= lastAddress; lastAdrByte++) {
                    this.hosts.add(InetAddress.getByName(addressBeforeRange + lastAdrByte));
                }
            } else {
                this.hosts.add(InetAddress.getByName(argHost));
            }
        }
    }

    private void parsePorts(String ports) {
        for (String argPort : ports.split(",")) {
            if (argPort.contains("-")) {
                int firsPort = Integer.parseInt(argPort.split("-")[0]);
                int lastPort = Integer.parseInt(argPort.split("-")[1]);
                for (int port = firsPort; port <= lastPort; port++) {
                    this.ports.add(port);
                }
            } else {
                this.ports.add(Integer.parseInt(argPort));
            }
        }
    }
}

