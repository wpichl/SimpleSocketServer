import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;

public class SocketHandler {
    private static SocketHandler mSingleton = null;
    private static Socket mSocket = null;

    private SocketHandler() {

    }

    public static SocketHandler getInstance() {
        if (mSingleton == null) {
            mSingleton = new SocketHandler();
        }

        return mSingleton;
    }

    public void startConnection(InetAddress host, Integer port) {
        try {
            this.mSocket =  new Socket(host, port);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void stopConnection() {
        try {
            this.mSocket.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
