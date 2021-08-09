package activitytest.com.example.serverchat;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.List;

class openThread implements Runnable{
    Socket socket;
    List<Socket> socketList;
    InputStream is;
    OutputStream os;

    public openThread(Socket s, List<Socket> list) {
        this.socket=s;
        socketList=list;
    }
    @Override
    public void run() {
        byte[] arr=new byte[1024*4];
        int len;
        try {
            is=socket.getInputStream();                 //获取与客户端相连的IO流
            os=socket.getOutputStream();
        } catch (IOException e) {
            e.printStackTrace();
        }
        while(true){                                            //进行收发数据
            try {
                while ((len = is.read(arr)) != -1) {
                    for (Socket sock : socketList) {
                        if (sock.equals(socket)) {
                            continue;
                        } else {
                            OutputStream outputStream = sock.getOutputStream();           //向客户端发送信息
                            outputStream.write(arr);
                        }
                    }
                }
            }
            catch(IOException e){
                e.printStackTrace();
            }
        }
    }
}
