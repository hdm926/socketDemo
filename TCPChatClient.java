import java.io.IOException;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.Socket;


public class TCPChatClient {
    //socket编程，使用TCP协议通信
    //客户端
    public static void main(String[] msg) throws IOException {
        Socket socket = null;
        OutputStream outputStream = null;
        try{
            //1.获取服务端地址
            InetAddress serverIp = InetAddress.getByName("127.0.0.1");
            //2.和服务端建立连接
            socket = new Socket(serverIp,9999);
            //3.发送消息（往外输出消息流）,IO流
            outputStream = socket.getOutputStream();
            outputStream.write("This is a msg from client.".getBytes());
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if(outputStream!=null){
                outputStream.close();
            }
            if(socket!=null){
                socket.close();
            }
        }
    }
}
