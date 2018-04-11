import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JButton;

/*
 * CISC275
 * Section 10
 * Team 6
 */

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
	public void actionPerformed(ActionEvent ae) {
		if (moving) {
			moving = false;
			pressedJump = !pressedJump;
			view.isJumping(pressedJump);
			view.changePicArray();
			view.stopFrame(1);
		} else {
			moving = true;
			if(pressedJump){
				view.stopFrame(8);
			}
			else{
				view.stopFrame(10);
			}
		}

	}

	@Override
	public void keyPressed(KeyEvent ke) {
		System.out.println("Pressed J");
	}

	@Override
	public void keyReleased(KeyEvent ke) {
		System.out.println("Released J");
		if('j' == ke.getKeyChar()){
			;
		}
		
	}

	@Override
	public void keyTyped(KeyEvent ke) {
		System.out.println("Typed J");
	}
}