package org.ruhr.architecture.client;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.UnknownHostException;

public class Test {

	public static void main(String[] args) throws UnknownHostException, IOException {

		Socket socket = new Socket();
		// 接收缓冲区很小
		System.out.println("默认接收缓冲区大小" + socket.getReceiveBufferSize());
		socket.setReceiveBufferSize(32);
		System.out.println("默认接收缓冲区大小" + socket.getReceiveBufferSize());
		socket.connect(new InetSocketAddress("localhost", 9999));
		System.out.println("客户端连接远程服务端完毕");
		OutputStream outputStream = socket.getOutputStream();
		//
		while (true) {
			outputStream.write("GET /123456 HTTP/1.1\r\n".getBytes());
			outputStream.write("\r\n".getBytes());
			outputStream.flush();
			try {
				Thread.currentThread().sleep(1000000);
			} catch (InterruptedException e) {

			}
		}

	}
}
