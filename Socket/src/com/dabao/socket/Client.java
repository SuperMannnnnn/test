package com.dabao.socket;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class Client {

	public static void main(String[] args) {
		Socket socket;
		try {
			String ip = "176.3.16.123";
			int port = 6666;
			socket = new Socket(ip, port);
			//System.out.println("Client连接Server完成。。。。");
			DataInputStream dis = new DataInputStream(socket.getInputStream());
			Scanner sc = new Scanner(System.in);
			DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
			while(true){
			String line = dis.readUTF();
			System.out.println("server:"+line);
			if(line.equals("一路走好.")){
				break;
			}
			String str = sc.nextLine();
			dos.writeUTF(str);
			dos.flush();
			}
			dos.close();
			dis.close();
			socket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
