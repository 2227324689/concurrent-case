package com.concurrent;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 咕泡学院，只为更好的你
 * 咕泡学院-Mic: 2082233439
 * http://www.gupaoedu.com
 **/
public class ServerSocketExample {
    public static void main(String[] args) throws IOException, InterruptedException {
        final int DEFAULT_PORT=8080;
        ServerSocket serverSocket=null;
        serverSocket=new ServerSocket(DEFAULT_PORT);
        System.out.println("启动服务，监听端口："+DEFAULT_PORT);
        while(true) {
            Socket socket = serverSocket.accept();
            new Thread(new SocketThread(socket)).start();
        }
    }
}
