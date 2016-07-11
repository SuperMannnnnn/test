package com.dabao.chatserver;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class ChatServer {
	private List<Socket> sockets = new ArrayList<Socket>();
	public void startServer() throws IOException {
		int port = 6666;
		ServerSocket ss = new ServerSocket(port);
		while(true){
		Socket socket = ss.accept();
		sockets.add(socket);
		System.out.println("���ӳɹ�"+socket.getInetAddress());
		new WorkThread(socket).start(); 
		}
	}
	
	class WorkThread extends Thread{
		private Socket socket;

		public WorkThread(Socket socket) {
			super();
			this.socket = socket;
		}
		
		public void run() {
			try {
				DataInputStream dis = new DataInputStream(socket.getInputStream());
				while(true){
				String message = dis.readUTF();
				for (Socket s : sockets) {
					DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
					dos.writeUTF(message);
					dos.flush();
				}
				}
			} catch (IOException e) {
				e.printStackTrace();
				//����˴�������ͻ�������ʱ������
				//��Ҫ�Ѽ����е�socket������Ƴ�
				sockets.remove(socket);
			}
		};
	}
	
	public static void main(String[] args) {
		ChatServer server = new ChatServer();
		try {
			System.out.println("�����������ɹ�������");
			server.startServer();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
