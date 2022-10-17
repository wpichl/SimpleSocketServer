import java.io.*;
import java.net.Socket;
import java.time.LocalTime;

public class ClientHandler implements Runnable {
    private Socket mSocket;
    private boolean mRunning = true;

    public ClientHandler(Socket socket) {
        this.mSocket = socket;
    }

    @Override
    public void run() {
        try {
            DataOutputStream out = new DataOutputStream(this.mSocket.getOutputStream());
            BufferedReader in = new BufferedReader(new InputStreamReader(this.mSocket.getInputStream()));

            while (this.mRunning) {
                String input = in.readLine();

                if (input.equalsIgnoreCase("identify yourself")) {
                    out.writeBytes("I am The Server test\n");
                }
                else if (input.equalsIgnoreCase("how late is it")) {
                    LocalTime time = LocalTime.now();
                    out.writeBytes("It is currently " + time.getHour() + ":" + time.getMinute() + "\n");
                }
                else if (input.equalsIgnoreCase("exit")) {
                    out.writeBytes("Bye\n");
                    this.mRunning = false;
                    out.close();
                    in.close();
                    this.mSocket.close();
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
