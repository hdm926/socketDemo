import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.*;


public class UDPChatSender implements Runnable {
    //UDP开启多线程聊天
    //发送线程
    private int fromPort;
    private String toIP;
    private  int toPort;

    DatagramSocket socket = null;
    BufferedReader reader = null;

    public UDPChatSender(int fromPort, String toIP, int toPort) throws SocketException {
        this.fromPort = fromPort;
        this.toIP = toIP;
        this.toPort = toPort;

        try {
            socket = new DatagramSocket(fromPort);
            reader = new BufferedReader(new InputStreamReader(System.in));
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    @Override
    public void run() {
        while (true){
            try {
                String msg = reader.readLine();
                byte[] msgs = msg.getBytes();
                DatagramPacket packet = new DatagramPacket(msgs,0,msgs.length,new InetSocketAddress(this.toIP,this.toPort));
                socket.send(packet);

                if(msg.equals("bye")){
                    break;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        socket.close();

    }
}
