import java.awt.EventQueue;

import javax.swing.Timer;

public class Main {

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable(){
			public void run(){
				Controller game = new Controller();
				Timer t = new Timer(game.drawDelay, game.drawAction);
				t.start();
			}
		});
		
		
	}

}