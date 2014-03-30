package com.ap.put.sr.game;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import com.ap.put.sr.common.Config;
import com.ap.put.sr.common.NewGameModel;
import com.ap.put.sr.protocol.Protocol;

public class MouseTyping extends MouseAdapter {

	public NewGameModel model;
	public GameComponent viewGame;

	public MouseTyping(NewGameModel m, GameComponent v) {
		model = m;
		viewGame = v;

	}

	public boolean isPawnTryToBack(Pawn p, Field f) {

		return (p.currentField.row < f.row) ? model.viewGame.YOUR_TURN ? true
				: false : model.viewGame.YOUR_TURN ? false : true;
	}

	public boolean isPawnTryToFly(Pawn p, Field f) {

		return (Math.abs(p.currentField.column - f.column) >= 2 || Math
				.abs(p.currentField.row - f.row) >= 2) ? true : false;

	}

	public Pawn isPawnTryToKill(Pawn p, Field f) {

		int c = p.currentField.column;
		int r = p.currentField.row;

		for (Pawn s : (p.isPawnBlack ? viewGame.white : viewGame.black)) {
			int row = s.currentField.row;
			int column = s.currentField.column;
			if (Math.abs(c - column) == 1 && Math.abs(r - row) == 1) {
				System.out.println("zabijaka: " + p.toString() + " potent: "
						+ s.toString() + " zxabijaka skacze na: "
						+ f.toString());

				if (c - column == 1) {
					// lewy
					if (r - row == 1) {
						// gorny
						if (f.column == c - 2 && f.row == r - 2) {
							System.out.println("zabil: "
									+ s.currentField.column + " "
									+ s.currentField.row);
							return s;
						}
					} else {
						// dolny
						if (f.column == c - 2 && f.row == r + 2) {
							System.out.println("zabil: "
									+ s.currentField.column + " "
									+ s.currentField.row);
							return s;
						}
					}
				} else {
					// prawy
					if (r - row == 1) {
						// gorny
						if (f.column == c + 2 && f.row == r - 2) {
							System.out.println("zabil: "
									+ s.currentField.column + " "
									+ s.currentField.row);
							return s;
						}
					} else {
						// dolny
						if (f.column == c + 2 && f.row == r + 2) {
							System.out.println("zabil: "
									+ s.currentField.column + " "
									+ s.currentField.row);
							return s;
						}
					}
				}
			}
		}
		return null;

	}

	public boolean isGameOver() {

		if (viewGame.black.isEmpty()) {
		
			
			JOptionPane.showMessageDialog(null,
					viewGame.PLAYER_BLACK ? Config.MESSAGE_LOSE
							: Config.MESSAGE_WIN, null,
					JOptionPane.ERROR_MESSAGE);
			
			return true;
			
		}else if (viewGame.white.isEmpty()) {
			
			JOptionPane.showMessageDialog(null,
					viewGame.PLAYER_BLACK ? Config.MESSAGE_WIN
							: Config.MESSAGE_LOSE, null,
					JOptionPane.ERROR_MESSAGE);
			return true;
		}else{
			return false;
		}
		
	}
	
	
	public boolean damkaMove(Pawn p, Field f){
		
		if(!f.isBlack || f.pawn!= null){
			return false;
		}
		
		int targetCol = f.column;
		int targetRow = f.row;
			
		int pawnCol= p.currentField.column;
		int pawnRow= p.currentField.row;
			
		int fieldColumn = pawnCol;
		int fieldRow= pawnRow;
		
		 ArrayList<Field> fields= new ArrayList<Field>();

		if(pawnCol < targetCol){
			//prawa

			if(pawnRow > targetRow){
				//gora		
				 fieldColumn++;
				 fieldRow--;
				 while(fieldColumn<=7 && fieldRow<=7 && fieldColumn>=0 && fieldRow>=0){
					 fields.add( viewGame.board.fields[fieldColumn][fieldRow] );
					 fieldColumn++;
					 fieldRow--;
				}

				
				
			}else if (pawnRow < targetRow){
				//dol
				
				 fieldColumn++;
				 fieldRow++;
				 while(fieldColumn<=7 && fieldRow<=7 && fieldColumn>=0 && fieldRow>=0){
					 fields.add( viewGame.board.fields[fieldColumn][fieldRow] );
					 fieldColumn++;
					 fieldRow++;
				}
				
				
				
				
			}else{
				return false;
			}
			
			
			
			
		}else if(pawnCol > targetCol){
			//lewa
			
			if(pawnRow > targetRow){
				//gora
				 fieldColumn--;
				 fieldRow--;
				 while(fieldColumn<=7 && fieldRow<=7 && fieldColumn>=0 && fieldRow>=0){
					 fields.add( viewGame.board.fields[fieldColumn][fieldRow] );
					 fieldColumn--;
					 fieldRow--;
				}
				
			}else if (pawnRow < targetRow){
				//dol
				 fieldColumn--;
				 fieldRow++;
				 while(fieldColumn<=7 && fieldRow<=7 && fieldColumn>=0 && fieldRow>=0){
					 fields.add( viewGame.board.fields[fieldColumn][fieldRow] );
					 fieldColumn--;
					 fieldRow++;
				}
				
				
				
			}else{
				return false;
			}
			
			
			
		}else{
			return false;
		}
		//-------------------------------------
		
		if(fields.isEmpty()){
			System.out.print("pusto");	
			return false;
		}
				
		//wyswietlanie drogi
		for (Field field : fields) {
			System.out.print(field.column+"."+ field.row+" -> ");	
		}
		
		
		
		int target= fields.indexOf(f);
		System.out.print(target);
		if(target > -1){
			System.out.print("jest na linii!");
			
			
		
			if(target == 0){
				//moze ruszyc
				System.out.print("damka smiga bo to normalny ruch o jeden!");
				movePawnToField(p,f);
				return true;
			}else{
				
				//liczymy ile innych pionków znajduje siê na drodze od pionka do target miejsca
				int pawnCount=0;
				for(int i=0;i<=target-1;i++){
					Pawn p2=fields.get(i).pawn;		
					if(p2 != null ){//&& p2.isPawnBlack == p.isPawnBlack
						pawnCount++;
					}else{
				
					}
					
				}
				
				
				//sprawdzam, czy na polu przed miejscem na ktorye chce ruszyc gracz jest pionek przeciwnika
				Pawn beforeTargetPawn = fields.get(target-1).pawn;
				if( beforeTargetPawn != null && beforeTargetPawn.isPawnBlack != p.isPawnBlack){
					//miejsce przed target to pionek przeciwnika
					
					if(pawnCount == 1){
						//nie napotkalem nikogo tylko przeciwnika mozna bic go!
						System.out.print("Przed targetem jest przeciwnik i na drodze nikogo");
						
						movePawnToField(p,f);
						beforeTargetPawn.currentField.pawn = null;
						if (beforeTargetPawn.isPawnBlack) {
							viewGame.black.remove(beforeTargetPawn);
						} else {
							viewGame.white.remove(beforeTargetPawn);
						}
						if(model.viewGame.YOUR_TURN){
							canPawnKillSomeone(p);
						}
						return true;
						
						
					}else{
						System.out.print("Przed targetem jest przeciwnik i na drodze ktos :/");
					}
					
					
				}else{
					
					if(pawnCount == 0){
					//mozna ruszyc
						System.out.print("Na drodze nikogo mozna ruszac");
						movePawnToField(p,f);
						return true;
					}else{
						System.out.print("Na drodze jest chyba twój i nie mozna ruszac, albo za daleko chcesz jump");
					}
					
					
				}
				

				
				
			}

			
			
		}else{
			System.out.print("nie ma linii!");	
			
		}
		//koniec funkcji.
		return false;
		
		
	}
	
	

	public boolean move(Pawn p, Field f) {

		int pawnColumn = p.currentField.column;
		int pawnRow = p.currentField.row;

		int targetColumn = f.column;
		int targetRow = f.row;
		
		if(!viewGame.YOUR_TURN){
			viewGame.arrow.setSource(p.currentField);
			viewGame.arrow.setDestination(f);
		}
		
		if(p.isPawnDamka){
			return damkaMove(p,f);
		
		}
		
		

		if (Math.abs(pawnColumn - targetColumn) == 1
				&& Math.abs(pawnRow - targetRow) == 1) {
			if (isPawnTryToBack(p, f)) {
				System.out.println("nie mozna sie cofaæ :D");
				return false;
			}

		} else {
			Pawn ptk;
			if ((ptk = isPawnTryToKill(p, f)) == null) {

				if (isPawnTryToFly(p, f)) {
					System.out.println("nie mozna latac");
					return false;
				}

			} else {
				ptk.currentField.pawn = null;
				if (ptk.isPawnBlack) {
					viewGame.black.remove(ptk);
				} else {
					viewGame.white.remove(ptk);
				}

				movePawnToField(p,f);
				if(model.viewGame.YOUR_TURN){
					canPawnKillSomeone(p);
				}
				
				return true;

			}

		}

		movePawnToField(p,f);
		return true;
	}
	
	
	public boolean canPawnKillSomeone(Pawn p){
		
	
		int pawnCol= p.currentField.column;
		int pawnRow= p.currentField.row;
		System.out.println("       pawn poss-"+pawnCol+"-"+pawnRow);
		
		 ArrayList<Field> possiblefields= new ArrayList<Field>();
		 int col,row;
		 Pawn opponentPawn=null;
		 Field targetField=null;
		//lewa góra:
		 
		 col=pawnCol-1;
		 row=pawnRow-1;	
		 if(col-1<=7 && row-1<=7 && col-1>=0 && row-1>=0){		 
			opponentPawn = model.viewGame.board.fields[col][row].pawn;
			targetField= model.viewGame.board.fields[col-1][row-1];
			 if(opponentPawn != null && opponentPawn.isPawnBlack != viewGame.PLAYER_BLACK && targetField.pawn == null){
				 System.out.print("[lewa góra]");
				 possiblefields.add(targetField);			 
			 }		 
		 }
		
		
		//lewy dó³:
		 col=pawnCol-1;
		 row=pawnRow+1;	
		 if(col-1<=7 && row+1<=7 && col-1>=0 && row+1>=0){		 
			 opponentPawn = model.viewGame.board.fields[col][row].pawn;
			 targetField= model.viewGame.board.fields[col-1][row+1];
			 if(opponentPawn != null && opponentPawn.isPawnBlack != viewGame.PLAYER_BLACK && targetField.pawn == null){
				 System.out.print("[lewy dó³]");
				 possiblefields.add(targetField);			 
			 }		 
		 }
		
		//prawy dó³:
		 
		 col=pawnCol+1;
		 row=pawnRow+1;	
		 if(col+1<=7 && row+1<=7 && col+1>=0 && row+1>=0){		 
			 opponentPawn = model.viewGame.board.fields[col][row].pawn;
			 targetField= model.viewGame.board.fields[col+1][row+1];
			 if(opponentPawn != null && opponentPawn.isPawnBlack != viewGame.PLAYER_BLACK && targetField.pawn == null){
				 System.out.print("[prawy dó³]");
				 possiblefields.add(targetField);			 
			 }		 
		 }
		
		
		 //prawa góra:
		 
		 col=pawnCol+1;
		 row=pawnRow-1;	
		 if(col+1<=7 && row-1<=7 && col+1>=0 && row-1>=0){		 
			 opponentPawn = model.viewGame.board.fields[col][row].pawn;
			 targetField= model.viewGame.board.fields[col+1][row-1];
			 if(opponentPawn != null && opponentPawn.isPawnBlack != viewGame.PLAYER_BLACK && targetField.pawn == null){
				 System.out.print("[prawa góra]");
				 possiblefields.add(targetField);			 
			 }		 
		 }
		
		//-------------
		 if(!possiblefields.isEmpty()){
			 viewGame.doubleKillPossibleFields=possiblefields;
			 viewGame.pawnOnRage=p;
			 for (Field field : possiblefields) {
				System.out.println("!!!!! double kill:"+field.column+"."+field.row);
			}
			 return true;
		 }else{
			 viewGame.doubleKillPossibleFields=null;
			 return false;
		 }
		 
		 
		 
		 //doubleKillPossibleFields
		
	}
	
	
	public void movePawnToField(Pawn p, Field f){
		p.currentField.pawn = null;
		f.pawn = p;
		p.currentField = f;
		p.currentPossition = f.topleftpoint;
				
		if(f.row == 0){
			
		//System.out.println("DAMKA");
		System.out.println("ID DAMKI:"+p.id);
		
		if(!p.isPawnDamka){
			if(p.isPawnBlack){
				((PawnBlack)p).setAsDamka();
			}else{
				((PawnWhite)p).setAsDamka();
			}	
			model.sendMessage(Protocol.createDamkaMessage(p.id));
		}
		}
		
		
	}

	public void yourMove(Pawn p, Field f) {
		System.out.println("gracz:");

		if (move(p, f)) {
	
			boolean doubleKill = (viewGame.doubleKillPossibleFields !=null )?true:false;
			
			model.sendMessage(Protocol.createMoveMessage(p.id, f.column, f.row, doubleKill));
			if(!doubleKill){
				
				model.viewGame.changeTurn();
				model.view.updateTurnLabel();
				model.progressBarModel.oponentTurn();
			}
			
			
			if(isGameOver()){
				
				model.sendMessage(Protocol.createRestartMessage());
				model.OPPONENT_READY=false;
				model.view.ready.setEnabled(true);		
				model.progressBarModel.notReadyState();
				model.view.setDefaultTurnLabel();
				model.viewGame.restartGame();
				
			}
		} else {
			viewGame.currentPawn.changeCurrentField(viewGame.currentPawnField);
		}

	}

	public void oponentMove(int id, int targetColumn, int targetRow, boolean doubleKill) {
		System.out.println("przeciwnik:");
		Pawn p = null;
		for (Pawn p2 : (id <= 11) ? viewGame.black : viewGame.white) {
			if (p2.id == id) {
				p = p2;
			}
		}
		System.out.println("otrzymalem double kill:"+doubleKill);
		Field f = viewGame.board.fields[targetColumn][targetRow];
		if (move(p, f)) {
			
			if(!doubleKill){//jesli nie double kill to zmiana	
				
				model.viewGame.changeTurn();
				model.view.updateTurnLabel();
				
			}
			
			isGameOver();
			

		} else {
			JOptionPane.showMessageDialog(null,
					"B£¥D!", null,
					JOptionPane.ERROR_MESSAGE);
		}

	}

	// actioins:
	public void mousePressed(MouseEvent e) {
		

		if (!viewGame.YOUR_TURN)
			return;

		if (!viewGame.isValidPoint(e.getPoint()))
			return;

		Pawn p = viewGame.getPawnByPoint(e.getPoint());
		if (p == null)
			return;
		// System.out.println("mousePressed"+
		// ", id: "+p.id+" odpowiednik: c:"+(7-p.currentField.column)+"r: "+(7-p.currentField.row));
		// System.out.println("id pionka:"+p.id);
		viewGame.arrow.reset();
		
		
		if(viewGame.pawnOnRage!= null){
			 
			 if(viewGame.pawnOnRage == p){
				 viewGame.currentPawn = p;
					
			 }else{
				 
			 }
			
		}else{
		viewGame.currentPawn = p;
		}
		viewGame.currentPawnField = (p == null) ? null : p.currentField;

	}

	public void mouseReleased(MouseEvent e) {

		
		
		if (viewGame.currentPawn == null)
			return;
		
		if(!viewGame.YOUR_TURN){
			
			
			 viewGame.currentPawn.changeCurrentField(viewGame.currentPawnField);
			 viewGame.currentPawn=null;
			 viewGame.currentPawnField=null;
			 viewGame.repaint();
			 return;
		}
		
		Field f = viewGame.getFieldByPoint(e.getPoint());

		if (viewGame.isValidPoint(e.getPoint()) && f.pawn == null && f.isBlack) {
			// moze ruszac wstepnie

			if(viewGame.pawnOnRage != null){
				System.out.println("musi bic tego tam");
				 if(viewGame.doubleKillPossibleFields.contains(f)){
					 
					 viewGame.doubleKillPossibleFields=null;
					 viewGame.pawnOnRage=null;
					 yourMove(viewGame.currentPawn, f);
				 }else{
					 viewGame.currentPawn.changeCurrentField(viewGame.currentPawnField);
				 }
			}else{
				yourMove(viewGame.currentPawn, f);
			}
			
			
			

		} else {
			viewGame.currentPawn.changeCurrentField(viewGame.currentPawnField);
		}
		viewGame.repaint();
		viewGame.currentPawn = null;
		viewGame.currentPawnField = null;
		// System.out.println("==============================");

		// viewGame.board.printBoard();
	//	System.out.println("white: " + viewGame.white.size() + " black:"				+ viewGame.black.size());
	}

	public void mouseClicked(MouseEvent e) {

		// Pawn p = viewGame.getPawnByPoint(e.getPoint());
		// System.out.println(p.id);

	}

}
