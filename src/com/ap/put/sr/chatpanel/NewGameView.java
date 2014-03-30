package com.ap.put.sr.chatpanel;

import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import com.ap.put.sr.common.NewGameModel;

public class NewGameView extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public NewGameController controller;
	public JLabel turnLabel;
	public JButton send;
	public JButton ready;
	public JButton exit;
	public JTextField toSend;
	public JTextArea content;
	public JScrollPane sp;
	
	public static final String WAITING = "Oczekiwanie...";
	public static final String SEND_BUTTON_TEXT = "Wyœlij wiadomoœæ";
	public static final String READY_BUTTON_TEXT="Jestem gotowy!";
	public static final String DISCONNECT_CLIENT_TEXT = "Roz³¹cz siê";
	public static final String OPPONENT = "Przeciwnik: ";
	public static final String PLAYER = "Ty: ";
	public static final String YOUR_TURN = "Twoja kolej!";
	public static final String NOT_YOUR_TURN = "Kolej przeciwnika.";

	public NewGameView(NewGameModel ngm) {
		controller = new NewGameController(ngm, this);

		createGUI();
	}

	public void createGUI() {

		send = new JButton(SEND_BUTTON_TEXT);
		ready = new JButton(READY_BUTTON_TEXT);
		exit = new JButton(DISCONNECT_CLIENT_TEXT);
		
		toSend = new JTextField();
		turnLabel = new JLabel("");
		turnLabel.setHorizontalAlignment(JLabel.CENTER);
		turnLabel.setFont(new Font("Arial", Font.BOLD, 15));
		setDefaultTurnLabel();

		content = new JTextArea();

		sp = new JScrollPane(content);
		content.setLineWrap(true);
		content.setWrapStyleWord(false);

		content.setEditable(false);

		this.setLayout(new GridLayout(5, 0));

		toSend.addKeyListener(controller);
		ready.addActionListener(controller);
		send.addActionListener(controller);
		exit.addActionListener(controller);

		JPanel p = new JPanel(new GridLayout(5, 0));
		p.add(toSend);
		p.add(send);

		this.add(exit);
		this.add(ready);
		this.add(turnLabel);
		this.add(sp);
		this.add(p);
	}

	public String getChat() {
		return content.getText();

	}
	
	public void setDefaultTurnLabel(){
		turnLabel.setText(NewGameView.WAITING);
	}

	public void updateTurnLabel() {
		turnLabel.setText(controller.model.viewGame.YOUR_TURN ? YOUR_TURN
				: NOT_YOUR_TURN);
	}

	public void setChat(String txt) {
		content.setText(txt);
	}

	public void appendTextToChat(String txt) {
		setChat(getChat() + "\r\n" + OPPONENT + ": " + txt);
	}

	public String getContentToSend() {
		return toSend.getText();
	}

	public void setContentToSend(String msg) {
		toSend.setText(msg);
	}

}
