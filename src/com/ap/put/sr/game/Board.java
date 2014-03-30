package com.ap.put.sr.game;

import java.awt.geom.Point2D;
import java.util.ArrayList;

public class Board {

	public Field[][] fields;
	public Point2D topleftpoint;
	public ArrayList<PawnBlack> black;
	public ArrayList<PawnWhite> white;

	public void sendPawnRef(ArrayList<PawnBlack> black,
			ArrayList<PawnWhite> white) {
		this.black = black;
		this.white = white;
	}

	public Board(Point2D topleftpoint) {

		this.topleftpoint = topleftpoint;

		fields = new Field[8][8];
		int z = 0;
		for (int i = 0; i < 8; i++) {
			z++;
			for (int j = 0; j < 8; j++) {
				fields[i][j] = new Field(((z % 2 == 0) ? true : false),
						new Point2D.Double((int) topleftpoint.getX() + i
								* GameComponent.GAME_FIELD_SIZE,
								(int) topleftpoint.getX() + j
										* GameComponent.GAME_FIELD_SIZE), i, j);
				z++;
			}
		}

	}// constructor

	public void printBoard() {
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				System.out.print("  (" + fields[j][i].column + ","
						+ fields[j][i].row + ") ");

			}
			System.out.println("");
		}
	}

}
