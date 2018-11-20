package client;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

import tags.Decode;
import tags.Tags;
import client.Client;

public class ClientServer {

	private String username = "";
	private ServerSocket serverPeer;
	private int port;
	private boolean isStop = false;

//	public void stopServerPeer() {
//		isStop = true;
//	}
//
//	public boolean getStop() {
//		return isStop;
//	}

	public ClientServer(String name) throws Exception {
		username = name;
		port = Client.getPort();
		serverPeer = new ServerSocket(port);
		(new WaitPeerConnect()).start();
	}
	
	public void exit() throws IOException {
		isStop = true;
		serverPeer.close();
	}

	class WaitPeerConnect extends Thread {

		Socket connection;
		ObjectInputStream getRequest;

		@Override
		public void run() {
			super.run();
			while (!isStop) {
				try {
					connection = serverPeer.accept();
					getRequest = new ObjectInputStream(connection.getInputStream());
					String msg = (String) getRequest.readObject();
					String name = Decode.getNameRequestChat(msg);
					int res = MainGui.request("Account: " + name + " want to connect with you !", true);
					ObjectOutputStream send = new ObjectOutputStream(connection.getOutputStream());
					if (res == 1) {
						send.writeObject(Tags.CHAT_DENY_TAG);

					} else if (res == 0) {
						send.writeObject(Tags.CHAT_ACCEPT_TAG);
						new ChatGui(username, name, connection, port);
					}
					send.flush();
				} catch (Exception e) {
					break;
				}
			}
			try {
				serverPeer.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
