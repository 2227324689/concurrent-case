package com.concurrent;

import java.io.*;
import java.net.Socket;

/**
 * 咕泡学院，只为更好的你
 * 咕泡学院-Mic: 2082233439
 * http://www.gupaoedu.com
 **/
public class SocketThread  implements Runnable{
    Socket socket;

    public SocketThread(Socket socket) {
        this.socket = socket;
    }
    @Override
    public void run() {
        System.out.println("客户端：" + socket.getPort() + "已连接");
        try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String clientStr = null;
            clientStr = bufferedReader.readLine();
            System.out.println("客户端发了一段消息：" + clientStr);
            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            bufferedWriter.write("我已经收到你的消息了\n");
            bufferedWriter.flush(); //清空缓冲区触发消息发送
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}