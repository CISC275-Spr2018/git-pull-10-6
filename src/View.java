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

	static int picNum = 0;
	static int frameCount = 10;

	static boolean jump = false;
	static boolean fire = false;

	public enum Direction {

		EAST("images/orc/orc_forward_east.png"), NORTHEAST("images/orc/orc_forward_northeast.png"), NORTH(
				"images/orc/orc_forward_north.png"), NORTHWEST("images/orc/orc_forward_northwest.png"), WEST(
						"images/orc/orc_forward_west.png"), SOUTHWEST("images/orc/orc_forward_southwest.png"), SOUTH(
								"images/orc/orc_forward_south.png"), SOUTHEAST(
										"images/orc/orc_forward_southeast.png"), SOUTHEAST_JUMP(
												"images/orc/orc_jump_southeast.png"), SOUTHEAST_FIRE(
														"images/orc/orc_fire_southeast.png");

		private String name;

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
						"images/orc/orc_jump_west.png"), SOUTHWEST(
								"images/orc/orc_jump_southwest.png"), SOUTH("images/orc/orc_jump_south.png"),;
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
	BufferedImage[] pics_se_jump = new BufferedImage[10];
	BufferedImage[] pics_se_fire = new BufferedImage[10];

	BufferedImage[][] pics2D = new BufferedImage[][] { pics_e, pics_ne, pics_n, pics_nw, pics_w, pics_sw, pics_s,
													  pics_se, pics_se_jump, pics_se_fire };

	BufferedImage[] pics_jump_se;
	BufferedImage[] pics_jump_sw;
	BufferedImage[] pics_jump_ne;
	BufferedImage[] pics_jump_nw;
	
	BufferedImage background;

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

	public boolean jump() {
		return jump = !jump;
	}

	public boolean fire() {
		return fire = !fire;
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

	public void update(int x, int y, boolean move) {
		xloc = x;
		yloc = y;

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
		g.drawImage(background, 0, 0, this);
		
		picNum = (picNum + 1) % frameCount;
		if (!jump && !fire) {
			g.drawImage(pics2D[Direction.SOUTHEAST.ordinal()][picNum], xloc, yloc, Color.gray, this);
		} else if (jump) {
			g.drawImage(pics2D[Direction.SOUTHEAST_JUMP.ordinal()][picNum], xloc, yloc, Color.gray, this);
		} else if (fire && !jump) {
			g.drawImage(pics2D[Direction.SOUTHEAST_FIRE.ordinal()][picNum], xloc, yloc, Color.gray, this);
		}
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

		try {
			BufferedImage background = ImageIO.read(new File("/Users/willwalker/Downloads/background.jpg"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		moveButton = jb;

		for (Direction d : Direction.values()) {

			BufferedImage img = createImage(d);
			int frames = img.getWidth() / imageWidth;

			for (int i = 0; i < frames; i++) {
				pics2D[d.ordinal()][i] = img.getSubimage(imageWidth * i, 0, imageWidth, imageHeight);
			}

			System.out.println(frames);
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