package com.ap.put.sr.game;

import java.io.IOException;

import javax.imageio.ImageIO;

public class PawnBlack extends Pawn {

	public PawnBlack(Field currentField, int id) {
		super(currentField, id);
		isPawnBlack = true;
		try {
			pattern = ImageIO.read(getClass().getResource(
					//"..\\images\\pawn_black.png"));
					"/images/pawn_black.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}

		
	}// construct
	
	public void setAsDamka(){
		
		try {
			pattern = ImageIO.read(getClass().getResource(
					//"..\\images\\pawn_black_damka.png"));
					"/images/pawn_black_damka.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		this.isPawnDamka=true;
	}

}
