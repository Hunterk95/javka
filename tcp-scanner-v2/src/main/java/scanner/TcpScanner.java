package scanner;

import java.io.IOException;
import java.net.*;
import java.util.ArrayList;
import java.util.concurrent.*;

public class TcpScanner {
    AddressesToConnect addresses;

    public TcpScanner(AddressesToConnect addresses) {
        this.addresses = addresses;
    }

    public void scan(int numOfTreads) {
        LinkedBlockingQueue<Runnable> queue = new LinkedBlockingQueue<>();
        try {
            for (Address nextAddress = addresses.getNext(); nextAddress != null; nextAddress = addresses.getNext()) {
                queue.put(new ThreadConnect(nextAddress));
            }
        } catch (Exception e){

        }
        ThreadPoolExecutor executor = new ThreadPoolExecutor(numOfTreads, numOfTreads, 100, TimeUnit.MILLISECONDS, queue);
        executor.prestartAllCoreThreads();
        while (executor.getTaskCount() != executor.getCompletedTaskCount()){

        }
        executor.shutdown();
        printScanResult();

    }

    public static void connect(Address address, int port) {
        try (Socket client = new Socket()) {
            client.connect(new InetSocketAddress(address.getAddress(), port), 10);
            address.setSuccessConnect(true);
        } catch (IOException e) {
            //e.printStackTrace();
            address.setSuccessConnect(false);
        }
    }

    public void printScanResult(){
        for (Address address : addresses.getAllUnits()) {
            ArrayList<Integer> ports = (ArrayList<Integer>) address.getAllPorts();
            ArrayList<Boolean> connectionsResult = (ArrayList<Boolean>) address.getAllConnectionsResult();

            for (int i = 0; i < address.getPortsNum(); i++) {
                System.out.println(address.getAddress() + ":" + ports.get(i) + " connection " +
                        (connectionsResult.get(i) ? "successful" : "unsuccessful"));
            }
        }
    }

    public void saveScanResult(){
        for (Address address : addresses.getAllUnits()) {
            ArrayList<Integer> ports = (ArrayList<Integer>) address.getAllPorts();
            ArrayList<Boolean> connectionsResult = (ArrayList<Boolean>) address.getAllConnectionsResult();

            for (int i = 0; i < address.getPortsNum(); i++) {
                System.out.println(address.getAddress() + ":" + ports.get(i) + " connection " +
                        (connectionsResult.get(i) ? "successful" : "unsuccessful"));
            }
        }
    }
}
