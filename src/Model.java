/**
 * Model: Contains all the state and logic Does not contain anything about
 * images or graphics, must ask view for that
 *
 * has methods to detect collision with boundaries decide next direction provide
 * direction provide location
 **/

public class Model {

	static int xloc = 0;
	static int yloc = 0;
	static int xIncr = 8;
	static int yIncr = 2;

	static int paneWidth = 500;
	static int paneHeight = 300;
	static int spriteWidth = 165;
	static int spriteHeight = 165;

	static boolean faceSouth;
	static boolean faceEast;
	static boolean cardinal;

	int dir = 315;

	public Model(int width, int height, int imageWidth, int imageHeight) {
		paneWidth = width;
		paneHeight = height;
		spriteWidth = imageWidth;
		spriteHeight = imageHeight;
	}

	public int getX() {
		return xloc;
	}

	public int getY() {
		return yloc;
	}

	public void hitBounds() {
		// To protect from going out of x bounds
		if (spriteWidth + xloc > paneWidth) {
			xIncr = -xIncr;
		} else if (xloc < 0) {
			xIncr = -xIncr;
		}
		// To protect from going out of y bounds
		if (spriteHeight + yloc > paneHeight) {
			yIncr = -yIncr;
		} else if (yloc < 0) {
			yIncr = -yIncr;
		}
	}

	public void updateLocationAndDirection() {
		faceSouth = (yIncr > 0);
		faceEast = (xIncr > 0);

		hitBounds();
		xloc += xIncr;
		yloc += yIncr;
	}
}