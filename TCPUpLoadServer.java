import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class TCPUpLoadServer {
    //TCP实现文件上传
    //服务端
    public static void main(String[] args) throws IOException {
        //1.设置服务端端口
        ServerSocket serverSocket = new ServerSocket(9000);
        //2.等待客户端连接
        Socket socket = serverSocket.accept();
        //3.获取输入流
        InputStream inputStream = socket.getInputStream();
        //4.创建文件输出流
        FileOutputStream fileOutputStream = new FileOutputStream("received.jpg");
        //5.输出文件流
        byte[] buffer = new byte[1024];
        int len;
        while ((len=inputStream.read(buffer))!=-1){
            fileOutputStream.write(buffer,0,len);
        }
        //6.通知客户端接收完毕
        OutputStream outputStream = socket.getOutputStream();
        outputStream.write("服务端接收完毕".getBytes());

        outputStream.close();
        fileOutputStream.close();
        inputStream.close();
        socket.close();
        serverSocket.close();
    }
}
