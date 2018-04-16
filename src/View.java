import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

/*
 * CISC275
 * Section 10
 * Team 6
 */

@SuppressWarnings("serial")
public class View extends JPanel {

	JFrame frame = new JFrame();
	JButton moveButton;
	boolean isMoving = true;
	boolean pressedJump = false;
	boolean jumpLock = false;
	static int picNum = 0;
	static int frameCount = 10;

	public enum Direction {
		EAST("images/orc/orc_forward_east.png"), NORTHEAST("images/orc/orc_forward_northeast.png"), NORTH(
				"images/orc/orc_forward_north.png"), NORTHWEST("images/orc/orc_forward_northwest.png"), WEST(
						"images/orc/orc_forward_west.png"), SOUTHWEST("images/orc/orc_forward_southwest.png"), SOUTH(
								"images/orc/orc_forward_south.png"), SOUTHEAST("images/orc/orc_forward_southeast.png");
		private String name = null;

		private Direction(String s) {
			name = s;
		}

		public String getName() {
			return name;
		}
	}

	public enum Jump {
		EAST("images/orc/orc_jump_east.png"), NORTHEAST("images/orc/orc_jump_northeast.png"), NORTH(
				"images/orc/orc_jump_north.png"), NORTHWEST("images/orc/orc_jump_northwest.png"), WEST(
						"images/orc/orc_jump_west.png"), SOUTHWEST("images/orc/orc_jump_southwest.png"), SOUTH(
								"images/orc/orc_jump_south.png"), SOUTHEAST("images/orc/orc_jump_southeast.png");
		private String name = null;

		private Jump(String s) {
			name = s;
		}

		public String getName() {
			return name;
		}
	}

	BufferedImage[] pics_e = new BufferedImage[10];
	BufferedImage[] pics_ne = new BufferedImage[10];
	BufferedImage[] pics_n = new BufferedImage[10];
	BufferedImage[] pics_nw = new BufferedImage[10];
	BufferedImage[] pics_w = new BufferedImage[10];
	BufferedImage[] pics_sw = new BufferedImage[10];
	BufferedImage[] pics_s = new BufferedImage[10];
	BufferedImage[] pics_se = new BufferedImage[10];

	BufferedImage[][] pics2D = new BufferedImage[][] { pics_e, pics_ne, pics_n, pics_nw, pics_w, pics_sw, pics_s,
			pics_se };

	BufferedImage[] pics_jump_se;
	BufferedImage[] pics_jump_sw;
	BufferedImage[] pics_jump_ne;
	BufferedImage[] pics_jump_nw;

	final static int frameWidth = 1000;
	final static int frameHeight = 600;
	final static int imageWidth = 165;
	final static int imageHeight = 165;

	static int xloc = 0;
	static int yloc = 0;
	static int orient = 315;

	int jumpNCode = -9996;
	int jumpECode = -9997;
	int jumpSCode = -9998;
	int jumpWCode = -9999;

	public int getImageWidth() {
		return imageWidth;
	}

	public int getImageHeight() {
		return imageHeight;
	}

	public int getWidth() {
		return frameWidth;
	}

	public int getHeight() {
		return frameHeight;
	}

	public void changeFrame(int x) {
		frameCount = x;
	}

	public void isJumping(boolean spacebar) {
		pressedJump = spacebar;
		if (!pressedJump) {
			jumpLock = false;
		}
		if (pressedJump) {
			jumpLock = true;
		}
	}

	public boolean getJumpLock() {
		return jumpLock;
	}

	public void update(int x, int y, int dir, boolean move) {
		xloc = x;
		yloc = y;
		orient = dir;

		if (move) {
			moveButton.setText("stop");
		} else {
			moveButton.setText("start");
		}
		frame.repaint();

		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}

	public void paint(Graphics g) {
		picNum = (picNum + 1) % frameCount;
		g.drawImage(pics2D[4][picNum], xloc, yloc, Color.gray, this);
	}

	public void open() {
		frame.getContentPane().add(new View(moveButton));
		JPanel buttonPanel = new JPanel();
		buttonPanel.add(moveButton);
		frame.add(buttonPanel);
		frame.setBackground(Color.gray);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(frameWidth, frameHeight);
		frame.setVisible(true);
	}

	public View(JButton jb) {

		moveButton = jb;

		for (Direction d : Direction.values()) {
			BufferedImage img = createImage(d);
			int frames = img.getWidth() / imageWidth;

			for (int i = 0; i < frames; i++) {
				pics2D[d.ordinal()][i] = img.getSubimage(imageWidth * i, 0, imageWidth, imageHeight);
			}
		}
	}

	private BufferedImage createImage(Direction d) {

		try {
			return ImageIO.read(new File(d.getName()));
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}

}