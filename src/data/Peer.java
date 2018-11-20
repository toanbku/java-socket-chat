package data;

public class Peer {

	private String namePeer = "";
	private String hostPeer = "";
	private int portPeer = 0;

	public void setPeer(String name, String host, int port) {
		namePeer = name;
		hostPeer = host;
		portPeer = port;
	}

	public void setName(String name) {
		namePeer = name;
	}

	public void setHost(String host) {
		hostPeer = host;
	}

	public void setPort(int port) {
		portPeer = port;
	}

	public String getName() {
		return namePeer;
	}

	public String getHost() {
		return hostPeer;
	}

	public int getPort() {
		return portPeer;
	}
}

