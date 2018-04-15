import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.Action;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;
import javax.swing.AbstractAction;

import javax.swing.JButton;

public class Controller implements ActionListener, KeyListener {

	private Model model;
	private View view;
	JButton moveButton = new JButton("stop");
	boolean moving = true;
	
	final int drawDelay = 30; // ms	
	
	Action drawAction;

	public Controller() {
		view = new View(moveButton);
		model = new Model(view.getWidth(), view.getHeight(), view.getImageWidth(), view.getImageHeight());
		drawAction = new AbstractAction(){
			
			public void actionPerformed(ActionEvent e){
				view.repaint();
				if (moving) {
					// increment the x and y coordinates, alter direction
					model.updateLocationAndDirection();
					// update the view
				}
				view.update(model.getX(), model.getY(), model.getDirect(), moving);
				}
			};  

		moveButton.addActionListener(this);

		view.frame.setFocusable(true);
		view.frame.addKeyListener(this);
		
		
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