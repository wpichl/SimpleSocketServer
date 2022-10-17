import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.Socket;

public class SocketHandler {
    private static SocketHandler mSingleton = null;
    private Socket mSocket = null;

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
            this.mSocket = new Socket(host, port);
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

    public void sendMessage(String message) {
        try {
            DataOutputStream out = new DataOutputStream(this.mSocket.getOutputStream());
            out.writeBytes(message + "\n");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }

    public String receiveMessage() {
        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(this.mSocket.getInputStream()));
            return in.readLine();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
