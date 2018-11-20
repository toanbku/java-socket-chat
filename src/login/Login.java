package login;

import java.awt.EventQueue;
import java.util.Random;
import java.util.regex.Pattern;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import client.MainGui;
import tags.Encode;
import tags.Tags;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.awt.Font;
import javax.swing.UIManager;

public class Login {
 private static String NAME_FAILED = "THIS NAME CONTAINS INVALID CHARACTER. PLEASE TRY AGAIN";
 private static String NAME_EXSIST = "THIS NAME IS ALREADY USED. PLEASE TRY AGAIN";
 private static String SERVER_NOT_START = "TURN ON SERVER BEFORE START";

 private Pattern checkName = Pattern.compile("[_a-zA-Z][_a-zA-Z0-9]*");

 private JFrame frameLoginForm;
 private JTextField txtPort;
 private JLabel lblError;
 private String name = "", IP = "";
 private JTextField txtIP;	
 private JTextField txtUsername;
 private JButton btnLogin;

 public static void main(String[] args) {
  EventQueue.invokeLater(new Runnable() {
   public void run() {
    try {
     Login window = new Login();
     window.frameLoginForm.setVisible(true);
    } catch (Exception e) {
     e.printStackTrace();
    }
   }
  });
 }

 public Login() {
  initialize();
 }

 private void initialize() {
  frameLoginForm = new JFrame();
  frameLoginForm.setTitle("Login Form");
  frameLoginForm.setResizable(false);
  frameLoginForm.setBounds(100, 100, 517, 343);
  frameLoginForm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
  frameLoginForm.getContentPane().setLayout(null);

  JLabel lblWelcome = new JLabel("Connect With Server\r\n");
  lblWelcome.setForeground(UIManager.getColor("RadioButtonMenuItem.selectionBackground"));
  lblWelcome.setFont(new Font("Segoe UI", Font.PLAIN, 18));
  lblWelcome.setBounds(27, 13, 312, 48);
  frameLoginForm.getContentPane().add(lblWelcome);

  JLabel lblHostServer = new JLabel("IP Server");
  lblHostServer.setFont(new Font("Segoe UI", Font.PLAIN, 13));
  lblHostServer.setBounds(47, 74, 86, 20);
  frameLoginForm.getContentPane().add(lblHostServer);

  JLabel lblPortServer = new JLabel("Port Server");
  lblPortServer.setFont(new Font("Segoe UI", Font.PLAIN, 13));
  lblPortServer.setBounds(349, 77, 79, 14);
  frameLoginForm.getContentPane().add(lblPortServer);

  txtPort = new JTextField();
  txtPort.setFont(new Font("Segoe UI", Font.PLAIN, 13));
  txtPort.setText("8080");
  txtPort.setEditable(false);
  txtPort.setColumns(10);
  txtPort.setBounds(429, 70, 65, 28);
  frameLoginForm.getContentPane().add(txtPort);

  JLabel lblUserName = new JLabel("User Name");
  lblUserName.setFont(new Font("Segoe UI", Font.PLAIN, 13));
  lblUserName.setBounds(10, 134, 106, 38);
  frameLoginForm.getContentPane().add(lblUserName);
  lblUserName.setIcon(new javax.swing.ImageIcon(Login.class.getResource("/image/user.png")));

  lblError = new JLabel("");
  lblError.setBounds(66, 287, 399, 20);
  frameLoginForm.getContentPane().add(lblError);

  txtIP = new JTextField();
  txtIP.setBounds(128, 70, 185, 28);
  frameLoginForm.getContentPane().add(txtIP);
  txtIP.setColumns(10);

  txtUsername = new JTextField();
  txtUsername.setFont(new Font("Segoe UI", Font.PLAIN, 13));
  txtUsername.setColumns(10);
  txtUsername.setBounds(128, 138, 366, 30);
  frameLoginForm.getContentPane().add(txtUsername);

  btnLogin = new JButton("Login");
  btnLogin.setFont(new Font("Segoe UI", Font.PLAIN, 13));
  btnLogin.setIcon(new javax.swing.ImageIcon(Login.class.getResource("/image/login.png")));
  btnLogin.addActionListener(new ActionListener() {

   public void actionPerformed(ActionEvent arg0) {
    name = txtUsername.getText();
    lblError.setVisible(false);
    IP = txtIP.getText();


    //must edit here
    if (checkName.matcher(name).matches() && !IP.equals("")) {
     try {
      Random rd = new Random();
      int portPeer = 10000 + rd.nextInt() % 1000;
      InetAddress ipServer = InetAddress.getByName(IP);
      int portServer = Integer.parseInt("8080");
      Socket socketClient = new Socket(ipServer, portServer);

      String msg = Encode.getCreateAccount(name, Integer.toString(portPeer));
      ObjectOutputStream serverOutputStream = new ObjectOutputStream(socketClient.getOutputStream());
      serverOutputStream.writeObject(msg);
      serverOutputStream.flush();
      ObjectInputStream serverInputStream = new ObjectInputStream(socketClient.getInputStream());
      msg = (String) serverInputStream.readObject();

      socketClient.close();
      if (msg.equals(Tags.SESSION_DENY_TAG)) {
       lblError.setText(NAME_EXSIST);
       lblError.setVisible(true);
       return;
      }
      new MainGui(IP, portPeer, name, msg);
      //						new menuGUI(IP, portPeer, "toan", msg);
      frameLoginForm.dispose();
     } catch (Exception e) {
      lblError.setText(SERVER_NOT_START);
      lblError.setVisible(true);
      e.printStackTrace();
     }
    }
    else {
     lblError.setText(NAME_FAILED);
     lblError.setVisible(true);
     lblError.setText(NAME_FAILED);
    }
   }
  });
  
  btnLogin.setBounds(325, 217, 169, 63);
  frameLoginForm.getContentPane().add(btnLogin);
  lblError.setVisible(false);


 }
}