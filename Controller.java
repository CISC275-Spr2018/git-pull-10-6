import java.awt.Dimension;
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

	public Controller() {
		view = new View(moveButton);
		model = new Model(view.getWidth(), view.getHeight(), view.getImageWidth(), view.getImageHeight());

		moveButton.addActionListener(this);

		view.frame.setFocusable(true);
		view.frame.addKeyListener(this);
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
			view.stopFrame(1);
		} else {
			moving = true;
			view.stopFrame(10);
		}

	}
	
	@Override
	public void keyTyped(KeyEvent e) {
    
	}

	@Override
       public void keyPressed(KeyEvent e) {
			int keyPress = e.getKeyCode();
			if(keyPress == KeyEvent.VK_UP){
				model.setUP();
				
			}
			else if(keyPress == KeyEvent.VK_DOWN){
				model.setDOWN();
			}
			else if(keyPress == KeyEvent.VK_LEFT){
				model.setLEFT();
		
			}
			else if(keyPress == KeyEvent.VK_RIGHT){
				model.setRIGHT();
			}
	}
	
	@Override
	public void keyReleased(KeyEvent e) {

    }

}