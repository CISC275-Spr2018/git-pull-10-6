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
	
	static int tempXIncr = 8;
	static int tempYIncr = 2;

	static int paneWidth = 500;
	static int paneHeight = 300;
	static int spriteWidth = 165;
	static int spriteHeight = 165;
	
	static boolean pressedUP = false;
	static boolean pressedDOWN = false;
	static boolean pressedLEFT = false;
	static boolean pressedRIGHT = false;

	static boolean faceSouth;
	static boolean faceEast;
	static boolean faceNorth;
	static boolean faceWest;
	static boolean cardinal;

	int dir = 315;

	public Model(int width, int height, int imageWidth, int imageHeight) {
		paneWidth = width;
		paneHeight = height;
		spriteWidth = imageWidth;
		spriteHeight = imageHeight;
	}
	
	public int getPaneWidth(){
		return paneWidth;
	}
	
	public int getPaneHeight(){
		return paneHeight;
	}

	public int getX() {
		return xloc;
	}

	public int getY() {
		return yloc;
	}
	
	public void setUP(){
		pressedUP = true;
	}
	
	public void setDOWN(){
		pressedDOWN = true;
	}
	
	public void setLEFT(){
		pressedLEFT = true;
	}
	
	public void setRIGHT(){
		pressedRIGHT = true;
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
	
	public void hitKeys(){
		if(pressedUP && yIncr >= 0){	
			if(tempYIncr > 0){
				tempYIncr = -tempYIncr;
			}
			// yIncr = -yIncr;
			yIncr = tempYIncr;
			xIncr = 0;
			pressedUP = false;
		}
		if(pressedDOWN && yIncr <= 0){
			// yIncr = -yIncr;
			if(tempYIncr < 0){
				tempYIncr = -tempYIncr;
			}
			yIncr = tempYIncr;
			xIncr = 0;
			pressedDOWN = false;
			
		}
		if(pressedLEFT && xIncr >= 0){		
			// xIncr = -xIncr;
			if(tempXIncr >  0){
				tempXIncr = -tempXIncr;
			}
			xIncr = tempXIncr;
			yIncr = 0;
			pressedLEFT = false;
			
		}
		if(pressedRIGHT && xIncr <= 0){
			// xIncr = -xIncr;
			if(tempXIncr < 0){
				tempXIncr = -tempXIncr;
			}
			xIncr = tempXIncr;
			yIncr = 0;
			pressedRIGHT = false;
			
		}
	}

	public void updateLocationAndDirection() {
		System.out.println(faceNorth);
		faceNorth = (yIncr < 0);
		faceSouth = (yIncr > 0);
		faceWest = (xIncr < 0);
		faceEast = (xIncr > 0);

		hitKeys();
		hitBounds();
		xloc += xIncr;
		yloc += yIncr;
	}

	public int getDirect() {
		return (faceSouth && faceEast) ? 315
				: (faceNorth && faceEast) ? 45 : (faceSouth && faceWest) ? 225 : (faceWest && faceNorth) ? 135
				: (!(faceSouth && faceWest && faceEast) && faceNorth) ? 90 : (!(faceWest && faceEast && faceNorth) && faceSouth) ? 270
				: (!(faceNorth && faceSouth && faceWest) && faceEast) ? 0 : (!(faceNorth && faceSouth && faceEast) && faceWest) ? 180 : 0;
	}
}