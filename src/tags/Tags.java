package tags;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class Tags {

	public static int IN_VALID = -1;

	public static int MAX_MSG_SIZE = 1024000; // ~1MB

	public static String SESSION_OPEN_TAG = "<SESSION_REQ>";// 1
	public static String SESSION_CLOSE_TAG = "</SESSION_REQ>";// 2
	public static String PEER_NAME_OPEN_TAG = "<PEER_NAME>";// 3
	public static String PEER_NAME_CLOSE_TAG = "</PEER_NAME>";// 4
	public static String PORT_OPEN_TAG = "<PORT>";// 5
	public static String PORT_CLOSE_TAG = "</PORT>";// 6
	public static String SESSION_KEEP_ALIVE_OPEN_TAG = "<SESSION_KEEP_ALIVE>";// 7
	public static String SESSION_KEEP_ALIVE_CLOSE_TAG = "</SESSION_KEEP_ALIVE>";// 8
	public static String STATUS_OPEN_TAG = "<STATUS>";// 9
	public static String STATUS_CLOSE_TAG = "</STATUS>";// 10
	public static String SESSION_DENY_TAG = "<SESSION_DENY />";// 11
	public static String SESSION_ACCEPT_OPEN_TAG = "<SESSION_ACCEPT>";// 12
	public static String SESSION_ACCEPT_CLOSE_TAG = "</SESSION_ACCEPT>";// 13
	public static String CHAT_REQ_OPEN_TAG = "<CHAT_REQ>";// 14
	public static String CHAT_REQ_CLOSE_TAG = "</CHAT_REQ>";// 15
	public static String IP_OPEN_TAG = "<IP>";// 16
	public static String IP_CLOSE_TAG = "</IP>";// 17
	public static String CHAT_DENY_TAG = "<CHAT_DENY />";// 18
	public static String CHAT_ACCEPT_TAG = "<CHAT_ACCEPT />";// 19
	public static String CHAT_MSG_OPEN_TAG = "<CHAT_MSG>";// 20
	public static String CHAT_MSG_CLOSE_TAG = "</CHAT_MSG>";// 21
	public static String PEER_OPEN_TAG = "<PEER>";// 22
	public static String PEER_CLOSE_TAG = "</PEER>";// 23
	public static String FILE_REQ_OPEN_TAG = "<FILE_REQ>";// 24
	public static String FILE_REQ_CLOSE_TAG = "</FILE_REQ>";// 25
	public static String FILE_REQ_NOACK_TAG = "<FILE_REQ_NOACK />";// 26
	public static String FILE_REQ_ACK_OPEN_TAG = "<FILE_REQ_ACK>";// 27
	public static String FILE_REQ_ACK_CLOSE_TAG = "</FILE_REQ_ACK>";// 28
	public static String FILE_DATA_BEGIN_TAG = "<FILE_DATA_BEGIN />";// 29
	public static String FILE_DATA_OPEN_TAG = "<FILE_DATA>";// 30
	public static String FILE_DATA_CLOSE_TAG = "</FILE_DATA>";// 31
	public static String FILE_DATA_END_TAG = "<FILE_DATA_END />";// 32
	public static String CHAT_CLOSE_TAG = "<CHAT_CLOSE />";// 33
	// ------------------
	public static String SERVER_ONLINE = "RUNNING";
	public static String SERVER_OFFLINE = "STOP";


	public static int show(JFrame frame, String msg, boolean type) {
		if (type)
			return JOptionPane.showConfirmDialog(frame, msg, null, JOptionPane.YES_NO_OPTION);
		JOptionPane.showMessageDialog(frame, msg);
		return IN_VALID;
	}
}

