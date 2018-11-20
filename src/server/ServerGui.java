package server;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.net.Inet4Address;
import java.net.UnknownHostException;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import javax.swing.JButton;
import java.awt.TextArea;
import java.awt.Font;

import java.awt.Color;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.UIManager;

public class ServerGui {

	public static int port = 8080;
	private JFrame frmServerMangement;
	private JTextField txtIP, txtPort;
	private JLabel lblStatus;
	private static TextArea txtMessage;
	public static JLabel lblUserOnline;
	ServerCore server;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ServerGui window = new ServerGui();
					window.frmServerMangement.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public ServerGui() {
		initialize();
	}
	
	public static String getLabelUserOnline() {
		return lblUserOnline.getText();
	}
	public static void updateMessage(String msg) {
		txtMessage.append(msg + "\n");
	}

	public static void updateNumberClient() {
		int number = Integer.parseInt(lblUserOnline.getText());
		lblUserOnline.setText(Integer.toString(number + 1));
	}
	
	public static void decreaseNumberClient() {
		int number = Integer.parseInt(lblUserOnline.getText());
		lblUserOnline.setText(Integer.toString(number - 1));

	}

	private void initialize() {
		frmServerMangement = new JFrame();
		frmServerMangement.setForeground(UIManager.getColor("RadioButtonMenuItem.foreground"));
		frmServerMangement.getContentPane().setFont(new Font("Segoe UI", Font.PLAIN, 13));
		frmServerMangement.getContentPane().setForeground(UIManager.getColor("RadioButtonMenuItem.acceleratorSelectionForeground"));
		frmServerMangement.setTitle("Server Mangement");
		frmServerMangement.setResizable(false);
		frmServerMangement.setBounds(200, 200, 730, 686);
		frmServerMangement.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmServerMangement.getContentPane().setLayout(null);
		frmServerMangement.setBackground(Color.ORANGE);

		JLabel lblIP = new JLabel("IP ADDRESS");
		lblIP.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		lblIP.setBounds(26, 120, 89, 16);					////// Vi tri lbl IP
		frmServerMangement.getContentPane().add(lblIP);

		txtIP = new JTextField();
		txtIP.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		txtIP.setEditable(false);
		txtIP.setBounds(126, 114, 176, 28);				////// Vi tri text Ip
		frmServerMangement.getContentPane().add(txtIP);
		txtIP.setColumns(10);
		try {
			txtIP.setText(Inet4Address.getLocalHost().getHostAddress());
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}

		JLabel lblNewLabel = new JLabel("PORT");
		lblNewLabel.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		lblNewLabel.setBounds(428, 120, 48, 16);			////// Vi tri lbl Port
		frmServerMangement.getContentPane().add(lblNewLabel);

		txtPort = new JTextField();
		txtPort.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		txtPort.setEditable(false);
		txtPort.setColumns(10);
		txtPort.setBounds(488, 114, 224, 28);
		frmServerMangement.getContentPane().add(txtPort);			///// Vi tri cua text Port
		txtPort.setText("8080");

		JButton btnStart = new JButton("START");
		btnStart.setBackground(UIManager.getColor("RadioButtonMenuItem.selectionBackground"));
		btnStart.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		
		btnStart.setBounds(416, 155, 143, 43);			/////// Vi tri button START
		frmServerMangement.getContentPane().add(btnStart);
		btnStart.setIcon(new javax.swing.ImageIcon(ServerGui.class.getResource("/image/start.png")));
		
//		BufferedImage img = null;
//		try {
//		    img = ImageIO.read(new File(ServerGui.class.getResource("/image/serverManager.png").getFile()));
//		} catch (IOException e) {
//		    e.printStackTrace();
//		}
//		Image dimg = img.getScaledInstance(64, 64,
//		        Image.SCALE_SMOOTH);
//		ImageIcon imageIcon = new ImageIcon(dimg);

		
		JLabel lblNhom = new JLabel("Server Management");
		lblNhom.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		lblNhom.setBounds(169, 13, 268, 76);
		lblNhom.setIcon(new javax.swing.ImageIcon(ServerGui.class.getResource("/image/servermanager64x64.png")));
		frmServerMangement.getContentPane().add(lblNhom);

		txtMessage = new TextArea();					
		txtMessage.setBackground(Color.BLACK);
		txtMessage.setForeground(Color.GREEN);
		txtMessage.setFont(new Font("Consolas", Font.PLAIN, 14));
		txtMessage.setEditable(false);
		txtMessage.setBounds(0, 267, 714, 358);		////// Vi tri textArea
		frmServerMangement.getContentPane().add(txtMessage);

		JButton btnStop = new JButton("STOP");
		btnStop.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		btnStop.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				lblUserOnline.setText("0");
				try {
					server.stopserver();
					ServerGui.updateMessage("STOP SERVER");
					lblStatus.setText("<html><font color='red'>OFF</font></html>");
				} catch (Exception e) {
					e.printStackTrace();
					ServerGui.updateMessage("STOP SERVER");
					lblStatus.setText("<html><font color='red'>OFF</font></html>");
				}
			}
		});
		btnStop.setBounds(571, 155, 143, 43);						//// Vi tri button Stop
		frmServerMangement.getContentPane().add(btnStop);
		btnStop.setIcon(new javax.swing.ImageIcon(ServerGui.class.getResource("/image/stop.png")));
		
		JLabel lblnew111 = new JLabel("STATUS");
		lblnew111.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		lblnew111.setBounds(26, 168, 89, 16);
		frmServerMangement.getContentPane().add(lblnew111);
		
		lblStatus = new JLabel("New label");
		lblStatus.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		lblStatus.setBounds(126, 168, 98, 16);


		frmServerMangement.getContentPane().add(lblStatus);
		lblStatus.setText("<html><font color='red'>OFF</font></html>");
		
		JLabel lblRecord = new JLabel("LOG");
		lblRecord.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		lblRecord.setBounds(26, 245, 89, 16);
		frmServerMangement.getContentPane().add(lblRecord);
		
		JLabel lbllabelUserOnline = new JLabel("USER ONLINE");
		lbllabelUserOnline.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		lbllabelUserOnline.setBounds(26, 207, 89, 16);
		frmServerMangement.getContentPane().add(lbllabelUserOnline);
		
		lblUserOnline = new JLabel("0");
		lblUserOnline.setForeground(Color.BLUE);
		lblUserOnline.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		lblUserOnline.setBounds(126, 207, 56, 16);
		frmServerMangement.getContentPane().add(lblUserOnline);
		
		JMenuBar menuBar = new JMenuBar();
		frmServerMangement.setJMenuBar(menuBar);
		
		JMenu mnNewMenu = new JMenu("About");
		menuBar.add(mnNewMenu);
		
		JMenuItem mntmMe = new JMenuItem("Us");
		mnNewMenu.add(mntmMe);
		
		JMenuItem mntmSoftware = new JMenuItem("Software");
		mnNewMenu.add(mntmSoftware);
		btnStart.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				try {
					server = new ServerCore(8080);
					ServerGui.updateMessage("START SERVER");
					lblStatus.setText("<html><font color='green'>RUNNING...</font></html>");
				} catch (Exception e) {
					ServerGui.updateMessage("START ERROR");
					e.printStackTrace();
				}
			}
		});
	}
}

