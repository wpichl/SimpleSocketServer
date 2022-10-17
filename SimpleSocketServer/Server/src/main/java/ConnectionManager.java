import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ConnectionManager {
    private static ConnectionManager mSingleton = null;
    private ServerSocket mServerSocket = null;
    private boolean mRunning = true;
    private boolean mSocketSetup = false;

    private ConnectionManager() {

    }

    public static ConnectionManager getInstance() {
        if (mSingleton == null) {
            mSingleton = new ConnectionManager();
        }

        return mSingleton;
    }

    public void start(int port) {
        while (this.mRunning) {
            if (!this.mSocketSetup) {
                try {
                    this.mServerSocket = new ServerSocket(port);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }

                this.mSocketSetup = true;
                System.out.println("Socket bound to port " + port);
            }

            Socket client;

            try {
                client = this.mServerSocket.accept();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            System.out.println("New client from " + client.getRemoteSocketAddress().toString().replace("/", "") + " accepted");
            new Thread(new ClientHandler(client)).start();
        }
    }
}
