import java.net.SocketException;

public class UDPChatTeacher {
    //开启两个线程
    public static void main(String[] args) throws SocketException {
        new Thread(new UDPChatReceiver(9999,"学生")).start();
        new Thread(new UDPChatSender(7777,"localhost",5555)).start();
    }
}
