import java.net.InetAddress;
import java.net.UnknownHostException;

public class Client {
    public static void main(String[] args) {
        InetAddress address = null;
        try {
            address = InetAddress.getLocalHost();
        } catch (UnknownHostException e) {
            throw new RuntimeException(e);
        }

        SocketHandler.getInstance().startConnection(address, 1337);
    }
}
