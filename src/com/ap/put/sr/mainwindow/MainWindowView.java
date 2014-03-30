package com.ap.put.sr.mainwindow;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerListModel;

public class MainWindowView extends JPanel{
	

	private static final long serialVersionUID = 1L;
	public MainWindowController controller;
	public JButton serverbtn;
	public JSpinner serverport;
	public static final String START_SERVER="Utwórz nowy pokój";
	public JButton connectbtn;
	public JSpinner connectport;
	public static final String CONNECT_TO_SERVER="Po³¹cz siê";
	public JTextField connectip;
	public static final String DEFAULT_IP="127.0.0.1";
	
	public MainWindowView(MainWindowModel m){
		
		controller = new MainWindowController(m,this);
		createUI();
	}
	
	public void createUI(){
		
		serverbtn=new JButton(START_SERVER);
		serverbtn.addActionListener(controller);
		
		connectbtn=new JButton(CONNECT_TO_SERVER);
		connectbtn.addActionListener(controller);
		
		connectip = new JTextField(DEFAULT_IP);
		
		
		serverport = new JSpinner(new SpinnerListModel(controller.model.getPortList()));
		connectport =  new JSpinner(new SpinnerListModel(controller.model.getPortList()));
		
		setLayout(new BorderLayout());
		
		JPanel serverpanel = new JPanel(new GridLayout(2, 1));
		JPanel serverpaneltop = new JPanel(new GridLayout(0, 2));
		serverpaneltop.add(new JLabel("Port:"));
		serverpaneltop.add(serverport);
		
		serverpanel.add(serverpaneltop);
		serverpanel.add(serverbtn);
		
		
		JPanel connectpanel = new JPanel(new GridLayout(2, 1));
		JPanel connectpaneltop = new JPanel(new GridLayout(2, 2));
		
		connectpaneltop.add(new JLabel("IP:"));
		connectpaneltop.add(connectip);
		connectpaneltop.add(new JLabel("Port:"));
		connectpaneltop.add(connectport);
		
		connectpanel.add(connectpaneltop);
		connectpanel.add(connectbtn);
		
	
		BufferedImage myPicture=null;
		try {
			myPicture = ImageIO.read(getClass().getResource(
					"/images/bg.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		JLabel picLabel = new JLabel(new ImageIcon( myPicture ));
	
		add(serverpanel,BorderLayout.PAGE_END);
		add(picLabel, BorderLayout.CENTER);
		add(connectpanel, BorderLayout.PAGE_START);
		
	
	}
	
	public int getServerPort(){
		return (Integer) serverport.getValue();
	}
	
	public int getConnectPort(){
		return (Integer) connectport.getValue();
	}
	
	public String getConnectIp(){
		return connectip.getText();
	}
	

}
