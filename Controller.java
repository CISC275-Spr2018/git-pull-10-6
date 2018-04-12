import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JButton;

public class Controller implements ActionListener, KeyListener {

	private Model model;
	private View view;
	JButton moveButton = new JButton("stop");
	boolean moving = true;
	boolean pressedJump = false;

	public Controller() {
		view = new View(moveButton);
		model = new Model(view.getWidth(), view.getHeight(), view.getImageWidth(), view.getImageHeight());
		moveButton.addActionListener(this);
		moveButton.addKeyListener(this);
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
			view.update(model.getX(), model.getY(), model.getDirect(), moving);

		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (moving) {
			moving = false;
			view.changeFrame(1);
		} else {
			moving = true;
			view.changeFrame(10);
		}

	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyPressed(KeyEvent e) {
		char c = e.getKeyChar();
		if (c == 'j') {
			view.changeFrame(8);
			pressedJump = !pressedJump;
			view.isJumping(pressedJump);
			view.changePicArray();
			System.out.println("Jump!");
		} else if (c == 'f') {
			view.changeFrame(8);
			pressedJump = !pressedJump;
			view.isJumping(pressedJump);
			view.changePicArray();
			System.out.println("Fire!");
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub

	}
}