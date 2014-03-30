package com.ap.put.sr.game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

import javax.swing.JComponent;

import com.ap.put.sr.common.Arrow;
import com.ap.put.sr.common.NewGameModel;

public class GameComponent extends JComponent {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public boolean PLAYER_BLACK;
	public boolean YOUR_TURN;
	public static final int GAME_BOARD_BORDER = 20;
	public static final int GAME_FIELD_SIZE = 60;
	public static final int GAME_FIELDS_TOTAL_WIDTH = GAME_FIELD_SIZE * 8;
	public Rectangle2D fieldsArea;
	public Rectangle2D boardArea;
	public Board board;
	public ArrayList<PawnBlack> black;
	public ArrayList<PawnWhite> white;
	public ArrayList<Field> doubleKillPossibleFields;
	public Pawn pawnOnRage;
	
	// controllers:
	public MouseMoving controllerMouseMoving;
	public MouseTyping controllerMouseTyping;

	public Pawn currentPawn;
	public Field currentPawnField;

	public Arrow arrow;
	// public NewGameModel newGameModel;

	public GameComponent(NewGameModel ngm, boolean playerIsBlack) {
		// newGameModel=ngm;
		PLAYER_BLACK = playerIsBlack;

		// model=ngm;

		controllerMouseMoving = new MouseMoving(ngm, this);
		controllerMouseTyping = new MouseTyping(ngm, this);

		addMouseListener(controllerMouseTyping);
		addMouseMotionListener(controllerMouseMoving);

		// poleGry = new Rectangle2D.Double(120, 120, 480, 480);

		boardArea = new Rectangle2D.Double(0, 0, GAME_FIELDS_TOTAL_WIDTH + 2
				* GAME_BOARD_BORDER, GAME_FIELDS_TOTAL_WIDTH + 2
				* GAME_BOARD_BORDER);
		fieldsArea = new Rectangle2D.Double(GAME_BOARD_BORDER,
				GAME_BOARD_BORDER, GAME_FIELDS_TOTAL_WIDTH,
				GAME_FIELDS_TOTAL_WIDTH);

		createGameObjects();

	}

	public void changeTurn() {
		YOUR_TURN = !YOUR_TURN;
	}

	public void restartGame() {

		createGameObjects();
		repaint();
	}

	public void printPawns() {
		System.out.println("blacks");
		for (PawnBlack b : black) {
			// System.out.println(b.currentField.topleftpoint);
			System.out.println(b.currentField.topleftpoint.toString() + " k: "
					+ b.currentField.column + " w: " + b.currentField.row
					+ " black: " + b.currentField.isBlack);

		}
	}

	public Pawn getPawnByPoint(Point2D point) {

		for (Pawn p : PLAYER_BLACK ? black : white) {
			if (p.currentField.square.contains(point)) {
				return p;
			}
		}

		// for (Pawn p : black) {
		// if (p.currentField.square.contains(point)) {
		// return p;
		// }
		// }
		// for (Pawn p : white) {
		// if (p.currentField.square.contains(point)) {
		// return p;
		// }
		// }

		return null;
	}

	public boolean isFieldFree(int column, int row) {

		if (column >= 0 && column <= 7 && row <= 7 && row >= 0) {
			if (board.fields[column][row].pawn == null) {
				return true;
			}
		}
		return false;

	}

	public boolean isValidPoint(Point2D p) {

		return (fieldsArea.contains(p)) ? true : false;
	}

	public Field getFieldByPoint(Point2D p) {
		// printPawns();

		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				if (board.fields[i][j].square.contains(p)) {
					return board.fields[i][j];
				}
			}
		}
		// 111
		return null;
	}

	public boolean containsBlackPawn(Field f) {
		for (Pawn p : black) {
			if (p.currentField == f) {
				return true;
			}
		}
		return false;
	}

	public void createGameObjects() {
		YOUR_TURN = false;
		board = new Board(new Point2D.Double(fieldsArea.getX(),
				fieldsArea.getY()));
		arrow=new Arrow();
		currentPawn = null;
		currentPawnField = null;
		doubleKillPossibleFields=null;
		pawnOnRage=null;
		white = new ArrayList<PawnWhite>();
		black = new ArrayList<PawnBlack>();
		int id_black = 0;
		int id_white = 23;
		for (int i = 0; i < 3; i++) {
			for (int j = (i % 2 == 0) ? 1 : 0; j < 8; j += 2) { // kolumna -
																// wiersz

				if (PLAYER_BLACK) {
					white.add(new PawnWhite(board.fields[j][i], id_white));
				} else {
					black.add(new PawnBlack(board.fields[j][i], id_black));
				}
				id_black++;
				id_white--;
			}
		}

		for (int i = 5; i < 8; i++) {
			for (int j = (i % 2 == 0) ? 1 : 0; j < 8; j += 2) {
				id_black--;
				id_white++;
				if (PLAYER_BLACK) {
					black.add(new PawnBlack(board.fields[j][i], id_black));
				} else {
					white.add(new PawnWhite(board.fields[j][i], id_white));
				}

			}
		}

		// printPawns();
		board.sendPawnRef(black, white);

	}

	public void paintComponent(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		// ----------RYSUJ PLANSZE
		g2.setColor(Color.gray);
		g2.fill(boardArea);
		g2.draw(boardArea);
		// ----------RYSUJ POLA
		for (int i = 0; i < 8; i++) {// kolumna
			for (int j = 0; j < 8; j++) {// wiersz

				g2.drawImage(board.fields[i][j].pattern,
						(int) board.fields[i][j].topleftpoint.getX(),
						(int) board.fields[i][j].topleftpoint.getY(), this);
			}
		}

		
		for (Pawn p : PLAYER_BLACK ? white : black) {
			g2.drawImage(p.pattern, (int) p.currentPossition.getX(),
					(int) p.currentPossition.getY(), this);

		}
		for (Pawn p : PLAYER_BLACK ? black : white) {
			g2.drawImage(p.pattern, (int) p.currentPossition.getX(),
					(int) p.currentPossition.getY(), this);
		}
		if (currentPawn != null) {
			g2.drawImage(currentPawn.pattern,
					(int) currentPawn.currentPossition.getX(),
					(int) currentPawn.currentPossition.getY(), this);
		}

		
		System.out.println(arrow.print());
		if(arrow.isset()){
			 g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
				        RenderingHints.VALUE_ANTIALIAS_ON);
			g2.setColor(Color.CYAN); 
			g2.drawLine((int)arrow.source.getX(), (int)arrow.source.getY(), (int)arrow.destination.getX(), (int)arrow.destination.getY());
			//g2.fillOval((int)arrow.destination.getX()-5, (int)arrow.destination.getY()-5, 10, 10);
		}

	}// repaint

}
