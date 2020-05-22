import java.net.SocketException;

public class UDPChatStudent {
    //开启两个线程
    public static void main(String[] args) throws SocketException {
        new Thread(new UDPChatSender(6666,"localhost",9999)).start();
        new Thread(new UDPChatReceiver(5555,"老师")).start();
    }

}
