package com.ap.put.sr.timing;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class ProgressBarController implements PropertyChangeListener {

	public ProgressBarModel model;
	public ProgressBarView view;

	public ProgressBarController(ProgressBarModel m, ProgressBarView v) {

		model = m;
		view = v;

	}

	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		if ("progress" == evt.getPropertyName()) {
			// int progress = (Integer) evt.getNewValue();
			view.progressBar.setValue((Integer) evt.getNewValue());
			view.progressBar.setString(model.task.getProgress() + " sek.");
			// System.out.println(model.task.getProgress());
		}

	}

}
