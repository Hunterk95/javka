package scanner;

public class ThreadConnect implements Runnable {
    Address address;

    public ThreadConnect (Address address) {
        this.address = address;
    }

    @Override
    public void run() {
        TcpScanner.connect(address, address.getPort());
    }
}
