import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

public class Controller implements ActionListener {

	private Model model;
	private View view;

	JButton moveButton = new JButton("stop");
	boolean moving = true;

	public Controller() {
		view = new View(moveButton);
		model = new Model(view.getWidth(), view.getHeight(), view.getImageWidth(), view.getImageHeight());

		moveButton.addActionListener(this);
		moveButton.setPreferredSize(new Dimension(30, 30));
	}

	// run the simulation
	public void start() {
		view.open();
		for (int i = 0; i < 5000; i++) {
			if (moving) {
				// increment the x and y coordinates, alter direction
				model.updateLocationAndDirection();
				// update the view
			}
			System.out.println(moving);
			view.update(model.getX(), model.getY(), model.getDirect(), moving);

		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (moving) {
			moving = false;
		} else {
			moving = true;
		}

	}
}