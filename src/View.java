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

	BufferedImage pics2D[][];
	static BufferedImage[] activePics;

	BufferedImage[] pics_e;
	BufferedImage[] pics_ne;
	BufferedImage[] pics_n;
	BufferedImage[] pics_nw;
	BufferedImage[] pics_w;
	BufferedImage[] pics_sw;
	BufferedImage[] pics_s;
	BufferedImage[] pics_se;

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

	public void changePicArray() {
		if (!getJumpLock()) {
			// faceSouthEast
			if (orient == 315)
				activePics = pics_se;
			// faceNorthEast
			else if (orient == 45)
				activePics = pics_ne;
			// faceSouthWest
			else if (orient == 225)
				activePics = pics_sw;
			// faceNorthWest
			else if (orient == 135)
				activePics = pics_nw;
		} else {
			if (orient == 315)
				activePics = pics_jump_se;
			// faceNorthEast
			else if (orient == 45)
				activePics = pics_jump_ne;
			// faceSouthWest
			else if (orient == 225)
				activePics = pics_jump_sw;
			// faceNorthWest
			else if (orient == 135)
				activePics = pics_jump_nw;
		}
	}

	public void update(int x, int y, int dir, boolean move) {
		xloc = x;
		yloc = y;
		orient = dir;
		changePicArray();

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
		g.drawImage(activePics[picNum], xloc, yloc, Color.gray, this);
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

		BufferedImage faceEast = createImage(Direction.EAST);
		BufferedImage faceNorthEast = createImage(Direction.NORTHEAST);
		BufferedImage faceNorth = createImage(Direction.NORTH);
		BufferedImage faceNorthWest = createImage(Direction.NORTHWEST);
		BufferedImage faceWest = createImage(Direction.WEST);
		BufferedImage faceSouthWest = createImage(Direction.SOUTHWEST);
		BufferedImage faceSouth = createImage(Direction.SOUTH);
		BufferedImage faceSouthEast = createImage(Direction.SOUTHEAST);
		pics_e = new BufferedImage[10];
		pics_ne = new BufferedImage[10];
		pics_n = new BufferedImage[10];
		pics_nw = new BufferedImage[10];
		pics_w = new BufferedImage[10];
		pics_sw = new BufferedImage[10];
		pics_s = new BufferedImage[10];
		pics_se = new BufferedImage[10];

		//BufferedImage jumpSouthEast = createImage(Jump.EAST);
		//BufferedImage jumpNorthWest = createImage(jumpWCode);
		//BufferedImage jumpNorthEast = createImage(jumpNCode);
		//BufferedImage jumpSouthWest = createImage(jumpSCode);
		//pics_jump_se = new BufferedImage[8];
		//pics_jump_nw = new BufferedImage[8];
		//pics_jump_ne = new BufferedImage[8];
		//pics_jump_sw = new BufferedImage[8];

		for (int i = 0; i < frameCount; i++) {
			pics_e[i] = faceEast.getSubimage(imageWidth * i, 0, imageWidth, imageHeight);
			pics_ne[i] = faceNorthEast.getSubimage(imageWidth * i, 0, imageWidth, imageHeight);
			pics_n[i] = faceNorth.getSubimage(imageWidth * i, 0, imageWidth, imageHeight);
			pics_nw[i] = faceNorthWest.getSubimage(imageWidth * i, 0, imageWidth, imageHeight);
			pics_w[i] = faceWest.getSubimage(imageWidth * i, 0, imageWidth, imageHeight);
			pics_sw[i] = faceSouthWest.getSubimage(imageWidth * i, 0, imageWidth, imageHeight);
			pics_s[i] = faceSouth.getSubimage(imageWidth * i, 0, imageWidth, imageHeight);
			pics_se[i] = faceSouthEast.getSubimage(imageWidth * i, 0, imageWidth, imageHeight);
		}

		//for (int j = 0; j < 8; j++) {
		//	pics_jump_se[j] = jumpSouthEast.getSubimage(imageWidth * j, 0, imageWidth, imageHeight);
		//	pics_jump_nw[j] = jumpNorthWest.getSubimage(imageWidth * j, 0, imageWidth, imageHeight);
		//	pics_jump_ne[j] = jumpNorthEast.getSubimage(imageWidth * j, 0, imageWidth, imageHeight);
		//	pics_jump_sw[j] = jumpSouthWest.getSubimage(imageWidth * j, 0, imageWidth, imageHeight);
		//}

		//activePics = pics_se; // initialize

	}

	private BufferedImage createImage(Direction d) {

	try { 
		return ImageIO.read(new File(d.getName()));
	}
	catch(IOException e) {
		e.printStackTrace();
		return null;
		}
	}

}