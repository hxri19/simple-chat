package chatApp;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.*;
import java.io.*;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import java.net.*;
import java.text.SimpleDateFormat;
import java.util.*;

public class Server implements ActionListener {
	final int WIDTH=450,HEIGHT=700;
	//================Global Variables=============================
	JTextField typeBox;
	JPanel textBox;
	static Box vertical = Box.createVerticalBox();
	static JFrame frame = new JFrame();
	static DataOutputStream OUT;
	
	Server(){
		frame.setLayout(null);
		//============== Header JPanel setup=====================
		JPanel p1 = new JPanel();
		p1.setBackground(new Color(7,94,84));
		p1.setBounds(0, 0, WIDTH, 70);
		p1.setLayout(null);
		frame.add(p1);
		//====================Arrow icon setup=============================
		ImageIcon icon = new ImageIcon(Server.class.getResource("arrow.png"));
		Image arrowIcon = icon.getImage().getScaledInstance(20, 20, Image.SCALE_AREA_AVERAGING);
		ImageIcon arrowImg = new ImageIcon(arrowIcon);
		//===================JLable setup for Arrow icon=======================
		JLabel label =new JLabel(arrowImg);
		label.setBounds(5, 20, 25, 25);
		p1.add(label);
		//===================Profile icon setup=========================
		ImageIcon profileIcon = new ImageIcon(Server.class.getResource("Portfolio-modified.png"));
		Image ProfIcon = profileIcon.getImage().getScaledInstance(40, 40, Image.SCALE_AREA_AVERAGING);
		ImageIcon profImg = new ImageIcon(ProfIcon);
		//================JLable setup for Profile image===================
		JLabel ProfileLabel =new JLabel(profImg);
		ProfileLabel.setBounds(40, 8, 50, 50);
		p1.add(ProfileLabel);
		//================Phone Icon setup================================
		ImageIcon phone = new ImageIcon(Server.class.getResource("phone.png"));
		Image phoneIcon = phone.getImage().getScaledInstance(25, 25, Image.SCALE_AREA_AVERAGING);
		ImageIcon phoneImg = new ImageIcon(phoneIcon);
		//========================JLabel setup for Phone icon=============================
		JLabel phoneLabel =new JLabel(phoneImg);
		phoneLabel.setBounds(330, 18, 30, 30);
		p1.add(phoneLabel);
		//====================Video call Icon setup=====================================
		ImageIcon video = new ImageIcon(Server.class.getResource("video.png"));
		Image videoIcon = video.getImage().getScaledInstance(30, 30, Image.SCALE_AREA_AVERAGING);
		ImageIcon videoImg = new ImageIcon(videoIcon);
		//========================JLabel setup for video icon=============================
		JLabel videoLabel =new JLabel(videoImg);
		videoLabel.setBounds(280, 15, 40, 40);
		p1.add(videoLabel);
		//====================3 Dot call Icon setup=====================================
		ImageIcon dots = new ImageIcon(Server.class.getResource("dots.png"));
		Image dotIcon = dots.getImage().getScaledInstance(30, 30, Image.SCALE_AREA_AVERAGING);
		ImageIcon dotImage = new ImageIcon(dotIcon);
		//========================JLabel setup for video icon=============================
		JLabel DotLabel =new JLabel(dotImage);
		DotLabel.setBounds(400, 15, 40, 40);
		p1.add(DotLabel);
		//======================Name===============================
		JLabel name=new JLabel("Amit Kumar Ghosh");
		name.setBounds(95, 15, 150, 18);
		name.setForeground(Color.WHITE);
		name.setFont(new Font("SAH_SERIF",Font.BOLD,14));
		p1.add(name);
		//======================Status===============================
		JLabel status=new JLabel("Active Now");
		status.setBounds(95, 32, 100, 18);
		status.setForeground(Color.WHITE);
		status.setFont(new Font("SAH_SERIF",Font.BOLD,11));
		p1.add(status);
		//======================JPanel For text field=============================
		textBox = new JPanel();
		//textBox.setBackground(new Color(211,211,211));
		textBox.setBounds(5, 75, 440, 570);
		frame.add(textBox);
		//=====================Type Box=================================
		typeBox=new JTextField();
		typeBox.setBounds(5, 655, 310, 40);
		typeBox.addActionListener(this);
		typeBox.setFont(new Font("SAH_SERIF",Font.PLAIN,15));
		frame.add(typeBox);
		//======================Send Button===========================
		JButton send=new JButton("SEND");
		send.setBounds(320, 655, 123, 40);
		send.setBackground(new Color(7,94,84));
		send.setForeground(Color.WHITE);
		send.addActionListener(this);
		send.setFont(new Font("SAH_SERIF",Font.PLAIN,16));
		frame.add(send);
		//======================Action on mouse click(Arrow Icon)======================
		label.addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent e) {
				System.exit(0);
			}
		}
	);
		//=======================JFrame Setup==========================
		frame.setUndecorated(true);
		frame.setSize(WIDTH,HEIGHT);
		frame.setLocation(200,50);
		frame.getContentPane().setBackground(Color.WHITE);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}
	//=====================abstract method of actionListener=======================
	public void actionPerformed(ActionEvent e) { 
	   try {
		   String output = typeBox.getText();
		     //=================JPanel for my Texts====================
		     JPanel myMessage=formatLabel(output);
		     textBox.setLayout(new BorderLayout());
		     //==============JPanel for My Text and printing text on Text Box==========================
		     JPanel myText = new JPanel(new BorderLayout());
		     myText.add(myMessage,BorderLayout.LINE_END);
		     vertical.add(myText);
		     vertical.add(Box.createVerticalStrut(15));
		     textBox.add(vertical,BorderLayout.PAGE_START);
		     //=================Sending text to clint========================
		     OUT.writeUTF(output);
		     //==================Clearing Text from box===================
		     typeBox.setText("");
		     //================Refreshing text Box==============
		     frame.repaint();
		     frame.invalidate();
		     frame.validate();
	   }catch(Exception E) {
		   E.printStackTrace();
	   }
	}
	//==========================adding padding around message================
	public static JPanel formatLabel(String output) {
		
		JPanel panel=new JPanel();
		panel.setLayout(new BoxLayout(panel,BoxLayout.Y_AXIS));
		JLabel message=new JLabel(output);
		message.setFont(new Font("Tahoma",Font.PLAIN,16));
		message.setBackground(new Color(37,211,102));
		message.setBorder(new EmptyBorder(15,15,15,30));
		message.setOpaque(true);
		panel.add(message);
		//====================add Time=============================
		Calendar calendar = Calendar.getInstance();
		SimpleDateFormat SDF = new SimpleDateFormat("HH:mm"); 
		JLabel time = new JLabel();
		time.setText(SDF.format(calendar.getTime()));
		panel.add(time);
		return panel;
	}
	
	//======================this is Main class===================================
	public static void main(String[]args) {
		new Server();
		//=============exception handiling===============
		try {
			ServerSocket soket=new ServerSocket(8080);
			while(true) {
				Socket s = soket.accept();
				DataInputStream IN =new DataInputStream(s.getInputStream());
				OUT = new  DataOutputStream(s.getOutputStream());
				while(true) {
					String msg=IN.readUTF();
					JPanel panel = formatLabel(msg);
					JPanel clientMessage = new JPanel(new BorderLayout());
					clientMessage.add(panel,BorderLayout.LINE_START);
					vertical.add(clientMessage);
					frame.validate();
					
				}
			}
		}catch(Exception e) {
			
		}
	}

}


