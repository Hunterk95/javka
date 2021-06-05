package scanner;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;

/**
 * Class for parse command line arguments
 */
public class CommandLineArgParser {

    private static final Logger logger = LogManager.getLogger(CommandLineArgParser.class.getName());

    final private String[] argv;
    final private ArrayList<InetAddress> hosts = new ArrayList<>();
    final private ArrayList<Integer> ports = new ArrayList<>();
    private String filePath;
    private int numOfThreads = 1;

    public ArrayList<InetAddress> getHosts() {
        return hosts;
    }

    public ArrayList<Integer> getPorts() {
        return ports;
    }

    public int getNumOfThreads() {
        return numOfThreads;
    }

    public CommandLineArgParser(String[] argv) {
        StringBuilder parsedString = new StringBuilder();
        for (String arg : argv) {
            parsedString.append(arg);
            parsedString.append(' ');
        }
        logger.info("Create parser to string: " + parsedString);
        this.argv = argv;
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
                        logger.debug("parse " + argv[i] + ' ' + argv[i + 1]);
                        parseHosts(argv[++i]);
                        break;

                    case ("-p"):
                        if (argv.length <= i + 1) {
                            throw new NotFoundArgumentKeyValueException("-p");
                        }
                        logger.debug("parse " + argv[i] + ' ' + argv[i + 1]);
                        parsePorts(argv[++i]);
                        break;

                    case ("-t"):
                        if (argv.length <= i + 1) {
                            throw new NotFoundArgumentKeyValueException("-t");
                        }
                        logger.debug("parse " + argv[i] + ' ' + argv[i + 1]);
                        numOfThreads = Integer.parseInt(argv[++i]);
                        logger.debug("parsed: " + numOfThreads + " threads");
                        break;

                    default:
                        throw new IllegalArgumentException("Not correct scan arguments");
                }
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            logger.error("WTF " + e.getMessage(), e);
        } catch (IllegalArgumentException e) {
            logger.warn(e.getMessage());
        } catch (UnknownHostException e) {
            logger.warn(e.getMessage());
        }catch (Exception e) {
            logger.error(e.getMessage(), e);
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
        logger.debug("parsed: " + this.hosts.size() + " hosts");
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
        logger.debug("parsed: " + this.ports.size() + " ports");
    }
}

