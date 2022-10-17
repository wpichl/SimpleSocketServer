public class Server {
    public static void main(String[] args) {
        ConnectionManager.getInstance().start(1337);
    }
}
