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
			 System.out.println("����Server�ɹ� :"+socket.getInetAddress());
			 //�����д���ͻ����ַ������� ʹ��Outputstream                           
			 DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
			 dos.writeUTF("�͹٣���Ҫ�Ե�ʲô��");
			 dos.flush();
			 DataInputStream dis = new DataInputStream(socket.getInputStream());
			 while(true){
			 String str = dis.readUTF();
			 if(str.equals("������!")){
					break;
				}
			 dos.writeUTF("ľ��"+str);
			 dos.flush();
			 }
			 dos.writeUTF("һ·�ߺ�.");
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
