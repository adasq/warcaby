package com.ap.put.sr.timing;

import javax.swing.SwingWorker;

import com.ap.put.sr.common.Config;
import com.ap.put.sr.common.NewGameModel;
import com.ap.put.sr.protocol.Protocol;

public class ProgressBarModel {

	public ProgressBarView view;
	public Task task;
	public NewGameModel model;

	class Task extends SwingWorker<Void, Void> {
		/*
		 * Main task. Executed in background thread.
		 */
		public boolean breakIt;

		Task() {
			breakIt = false;
		}

		@Override
		public Void doInBackground() {

			int progress = Config.TURN_TIME;
			setProgress(progress);
			while (progress > 0) {

				
				for(int i=0;i<10;i++){
					if (this.breakIt)break;
					try {	Thread.sleep(100);	} catch (InterruptedException e) {}
				}
				if (this.breakIt)break;
					
				progress -= 1;
				setProgress(progress);
			}

			return null;

		}

		/*
		 * Executed in event dispatching thread
		 */
		@Override
		public void done() {
			task = null;

			
			if(!this.breakIt){
				model.progressBarModel.oponentTurn();
				model.viewGame.changeTurn();
				model.view.updateTurnLabel();
				model.sendMessage(Protocol.createNoActionMessage());	
			}
		//	System.out.println("done, break: ");
		}
	}

	public ProgressBarModel(NewGameModel m) {
		model=m;
		task = null;
		// view.pr
	}

	public void stop() {
		if (task != null) {
			task.breakIt = true;
		}

	}

	public void addView(ProgressBarView v) {
		view = v;
		notReadyState();
	}

	public void notReadyState() {
		stop();
		view.progressBar.setString("");
		view.progressBar.setValue(Config.TURN_TIME);
	}

	public void oponentTurn() {
		stop();
		model.viewGame.doubleKillPossibleFields=null;
		model.viewGame.pawnOnRage=null;
		view.progressBar.setString("Kolej przeciwnika");
		view.progressBar.setValue(Config.TURN_TIME);
	}

	public void yourTurn() {
		stop();
		task = new Task();
		task.addPropertyChangeListener(view.controller);
		task.execute();

	}

}
