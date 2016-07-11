package com.dabao.socket;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

	public static void main(String[] args) {
		try {
			ServerSocket ss = new ServerSocket(6666);
			Socket socket =ss.accept();
			WorkThread wt = new WorkThread(socket);
			wt.start();
		
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	public static class WorkThread extends Thread{
		private Socket socket;
		
		
		public WorkThread(Socket socket) {
			super();
			this.socket = socket;
		}


		@Override
		public void run() {
			try{
			 System.out.println("连接Server成功 :"+socket.getInetAddress());
			 //服务端写给客户端字符串数据 使用Outputstream                           
			 DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
			 dos.writeUTF("客官，你要吃点什么？");
			 dos.flush();
			 DataInputStream dis = new DataInputStream(socket.getInputStream());
			 while(true){
			 String str = dis.readUTF();
			 if(str.equals("不吃了!")){
					break;
				}
			 dos.writeUTF("木有"+str);
			 dos.flush();
			 }
			 dos.writeUTF("一路走好.");
				dos.flush();
				dis.close();
				dos.close();
				socket.close();
			super.run();
			}catch (IOException e) {
				e.printStackTrace();
			}
		}
		
	}
	
	
}
