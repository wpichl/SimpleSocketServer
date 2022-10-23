import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Scanner;

public class RuntimeEngine {
    private static RuntimeEngine mSingleton = null;
    private boolean mRunning = true;

    private RuntimeEngine() {

    }

    public static RuntimeEngine getInstance() {
        if (mSingleton == null) {
            mSingleton = new RuntimeEngine();
        }

        return mSingleton;
    }

    private String getUserInput() {
        System.out.println("User input: ");
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();

        return input;
    }

    public void start() {
        InetAddress address;
        try {
            address = InetAddress.getLocalHost();
        } catch (UnknownHostException e) {
            throw new RuntimeException(e);
        }

        SocketHandler.getInstance().startConnection(address, 1337);

        while (this.mRunning) {
            String input = this.getUserInput();

            SocketHandler.getInstance().sendMessage(input);
            if (input.equalsIgnoreCase("exit")) {
                this.mRunning = false;
            }

            System.out.println(SocketHandler.getInstance().receiveMessage());
        }

        SocketHandler.getInstance().stopConnection();
    }
}
