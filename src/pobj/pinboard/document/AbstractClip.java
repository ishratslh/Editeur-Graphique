package pobj.pinboard.document;

import javafx.scene.paint.Color;

public abstract class AbstractClip {
	private double top, left,right, bottom;
	private Color color;
	
	public AbstractClip(double left, double top, double right, double bottom, Color color) {
		this.left = left;
		this.top = top;
		this.right = right;
		this.bottom = bottom;
		this.color = color;
	}
	public void setTop(double top) {
		this.top = top;
	}
	public void setLeft(double left) {
		this.left = left;
	}
	public void setRight(double right) {
		this.right = right;
	}
	public void setBottom(double bottom) {
		this.bottom = bottom;
	}

	public double getTop() {
		
		return top;
	}

	public double getLeft() {
		// TODO Auto-generated method stub
		return left;
	}

	public double getBottom() {
		// TODO Auto-generated method stub
		return bottom;
	}

	public double getRight() {
		// TODO Auto-generated method stub
		return right;
	}

	public void setGeometry(double left, double top, double right, double bottom) {
		this.left = left;
		this.top = top;
		this.right= right;
		this.bottom = bottom;

	}

	public void move(double x, double y) {
		this.left += x;
		this.right += x;
		this.top += y;
		this.bottom += y;

	}

	public boolean isSelected(double x, double y) {

		return x<= right && x>= left && y<= bottom && y>= top;
	}

	public void setColor(Color c) {
		color = c;

	}


	public Color getColor() {
		// TODO Auto-generated method stub
		return color;
	}
}
