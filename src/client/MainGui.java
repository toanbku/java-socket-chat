package client;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JLabel;

import javax.swing.DefaultListModel;
import javax.swing.JButton;

import tags.Tags;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;

import java.awt.Color;
import javax.swing.JList;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MainGui {

	private Client clientNode;
	private static String IPClient = "", nameUser = "", dataUser = "";
	private static int portClient = 0;
	private JFrame frameMainGui;
	private JTextField txtNameFriend;
	private JButton btnChat, btnExit;
	private JLabel lblLogo;
	private JLabel lblActiveNow;
	private static JList<String> listActive;
	
	static DefaultListModel<String> model = new DefaultListModel<>();
	private JLabel lblUsername;


	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainGui window = new MainGui();
					window.frameMainGui.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public MainGui(String arg, int arg1, String name, String msg) throws Exception {
		IPClient = arg;
		portClient = arg1;
		nameUser = name;
		dataUser = msg;
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainGui window = new MainGui();
					window.frameMainGui.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public MainGui() throws Exception {
		initialize();
		clientNode = new Client(IPClient, portClient, nameUser, dataUser);
	}

	public static void updateFriendMainGui(String msg) {
		model.addElement(msg);
	}

	public static void resetList() {
		model.clear();
	} 
	
	private void initialize() {
		frameMainGui = new JFrame();
		frameMainGui.setTitle("Menu Chat");
		frameMainGui.setResizable(false);
		frameMainGui.setBounds(100, 100, 500, 560);
		frameMainGui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frameMainGui.getContentPane().setLayout(null);

		JLabel lblHello = new JLabel("Welcome");
		lblHello.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		lblHello.setBounds(12, 82, 70, 16);
		frameMainGui.getContentPane().add(lblHello);


		JLabel lblFriendsName = new JLabel("Name Friend: ");
		lblFriendsName.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		lblFriendsName.setBounds(12, 425, 110, 16);
		frameMainGui.getContentPane().add(lblFriendsName);
		
		txtNameFriend = new JTextField("");
		txtNameFriend.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		txtNameFriend.setColumns(10);
		txtNameFriend.setBounds(100, 419, 384, 28);
		frameMainGui.getContentPane().add(txtNameFriend);

		btnChat = new JButton("Chat");
		btnChat.setFont(new Font("Segoe UI", Font.PLAIN, 13));

		btnChat.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				String name = txtNameFriend.getText();
				if (name.equals("") || Client.clientarray == null) {
					Tags.show(frameMainGui, "Invaild username", false);
					return;
				}
				if (name.equals(nameUser)) {
					Tags.show(frameMainGui, "This software doesn't support chat yourself function", false);
					return;
				}
				int size = Client.clientarray.size();
				for (int i = 0; i < size; i++) {
					if (name.equals(Client.clientarray.get(i).getName())) {
						try {
							clientNode.intialNewChat(Client.clientarray.get(i).getHost(),Client.clientarray.get(i).getPort(), name);
							return;
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				}
				Tags.show(frameMainGui, "Friend is not found. Please wait to update your list friend", false);
			}
		});
		btnChat.setBounds(20, 465, 129, 44);
		frameMainGui.getContentPane().add(btnChat);
		btnChat.setIcon(new javax.swing.ImageIcon(MainGui.class.getResource("/image/chat.png")));
		btnExit = new JButton("Exit");
		btnExit.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int result = Tags.show(frameMainGui, "Are you sure ?", true);
				if (result == 0) {
					try {
						clientNode.exit();
						frameMainGui.dispose();
					} catch (Exception e) {
						frameMainGui.dispose();
					}
				}
			}
		});
		btnExit.setBounds(353, 465, 129, 44);
		btnExit.setIcon(new javax.swing.ImageIcon(MainGui.class.getResource("/image/stop.png")));
		frameMainGui.getContentPane().add(btnExit);
		
		lblLogo = new JLabel("CONNECT WITH EVERYONE IN THE WORLD");
		lblLogo.setForeground(new Color(0, 0, 205));
		lblLogo.setIcon(new javax.swing.ImageIcon(MainGui.class.getResource("/image/connect.png")));
		lblLogo.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblLogo.setBounds(51, 13, 413, 38);
		frameMainGui.getContentPane().add(lblLogo);
		
		lblActiveNow = new JLabel("List Account Active Now");
		lblActiveNow.setForeground(new Color(100, 149, 237));
		lblActiveNow.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		lblActiveNow.setBounds(10, 123, 156, 16);
		frameMainGui.getContentPane().add(lblActiveNow);
		
		listActive = new JList<>(model);
		listActive.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		listActive.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				String value = (String)listActive.getModel().getElementAt(listActive.locationToIndex(arg0.getPoint()));
				txtNameFriend.setText(value);
			}
		});
		listActive.setBounds(12, 152, 472, 251);
		frameMainGui.getContentPane().add(listActive);
		
		lblUsername = new JLabel(nameUser);
		lblUsername.setForeground(Color.RED);
		lblUsername.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		lblUsername.setBounds(75, 76, 156, 28);
		frameMainGui.getContentPane().add(lblUsername);
	
			
	}
		

	public static int request(String msg, boolean type) {
		JFrame frameMessage = new JFrame();
		return Tags.show(frameMessage, msg, type);
	}
}
