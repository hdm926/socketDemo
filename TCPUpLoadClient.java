import java.io.*;
import java.net.InetAddress;
import java.net.Socket;

public class TCPUpLoadClient {
    //TCP实现文件上传
    //客户端
    public static void main(String[] msg) throws IOException {
        Socket socket = null;
        OutputStream outputStream = null;
        FileInputStream fileInputStream = null;
        InputStream inputStream = null;
        ByteArrayOutputStream byteArrayOutputStream = null;
        try {
            //1.和服务端建立连接
            socket = new Socket(InetAddress.getByName("127.0.0.1"),9000);
            //2.创建输出流
            outputStream = socket.getOutputStream();
            //3.读取文件流
            fileInputStream = new FileInputStream(new File("PIC11.jpg"));
            //4.写出文件
            byte[] buffer = new byte[1024];
            int len;
            while((len=fileInputStream.read(buffer))!=-1){
                outputStream.write(buffer,0,len);
            }
            //5.通知服务端已经发送完毕
            socket.shutdownOutput();
            //6.确认服务端接收完毕
            inputStream = socket.getInputStream();

            byteArrayOutputStream = new ByteArrayOutputStream();
            byte[] buffer2 = new byte[1024];
            int len2;
            while((len2=inputStream.read(buffer2))!=-1){
                byteArrayOutputStream.write(buffer2,0,len2);
            }
            System.out.print(byteArrayOutputStream.toString());

        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            //7.断开连接
            byteArrayOutputStream.close();
            inputStream.close();
            fileInputStream.close();
            outputStream.close();
            socket.close();
        }
    }

}
