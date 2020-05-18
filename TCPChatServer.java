import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class TCPChatServer {
    //使用tcp协议通信
    //服务器端
    public static void main(String[] msg) throws IOException {
        ServerSocket serverSocket = null;
        Socket socket = null;
        InputStream inputStream = null;
        ByteArrayOutputStream byteArrayOutputStream = null;
        try {
            //1.设置服务端端口
            serverSocket = new ServerSocket(9999);
            //2.等待客户端连接
            socket = serverSocket.accept();
            //3.读取客户端发送的消息
            inputStream = socket.getInputStream();

            //4.使用管道流输出
            byteArrayOutputStream = new ByteArrayOutputStream();
            byte[] buf = new byte[1024];
            int len;
            while(inputStream.read(buf)!=-1){
                len = buf.length;
                byteArrayOutputStream.write(buf,0,len);
            }
            System.out.print(byteArrayOutputStream.toString());
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if(byteArrayOutputStream!=null){
                byteArrayOutputStream.close();
            }
            if(inputStream!=null){
                inputStream.close();
            }
            if(socket!=null){
                socket.close();
            }
            if(serverSocket!=null){
                serverSocket.close();
            }
        }

    }
}
