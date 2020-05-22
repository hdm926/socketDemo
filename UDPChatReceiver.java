import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

public class UDPChatReceiver implements Runnable{
    //UDP开启多线程聊天
    //接收线程
    private int port;
    private String msgFrom;
    private DatagramSocket socket = null;
    public UDPChatReceiver(int port,String msgFrom) throws SocketException {
        this.port = port;
        this.msgFrom = msgFrom;
        socket = new DatagramSocket(port);
    }

    @Override
    public void run() {
        while(true){
            try {
                byte[] buffer = new byte[1024];
                DatagramPacket packet = new DatagramPacket(buffer,0,buffer.length);
                socket.receive(packet);//接收消息

                byte[] bytes = packet.getData();
                String receivedData = bytes.toString();
                System.out.print(this.msgFrom +":"+ receivedData);

                if(receivedData.equals("bye")){
                    break;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        socket.close();
    }
}
