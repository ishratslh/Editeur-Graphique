package pobj.pinboard.document;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class ClipEllipse extends AbstractClip implements Clip {

	public ClipEllipse(double left, double top, double right, double bottom, Color color) {
		super(left, top, right, bottom, color);
	}

	@Override
	public void draw(GraphicsContext ctx) {
		double initX = super.getLeft();
		double initY = super.getTop();
		double dragX = super.getRight();
		double dragY = super.getBottom();

		ctx.setFill(this.getColor());
		                                               //left   top      bottom        right
		if(initX > dragX && initY > dragY) ctx.fillOval(dragX, dragY, initX-dragX, initY-dragY);
		if(initX > dragX && initY < dragY) ctx.fillOval(dragX, initY, initX-dragX, dragY-initY);
		if(initX < dragX && initY > dragY) ctx.fillOval(initX, dragY, dragX-initX, initY-dragY);
		if(initX < dragX && initY < dragY) ctx.fillOval(initX, initY, dragX-initX, dragY-initY);
	}

	@Override
	public boolean isSelected(double x, double y) {
		//détermine si un point (x, y) est dans l'ellipse
		double cx = (this.getLeft() + this.getRight())/2; //cx = (left + right)/2
		double cy = (this.getTop() + this.getBottom())/2;
		double rx = (this.getRight()-this.getLeft())/2;
		double ry = (this.getBottom() - this.getTop())/2;
		double tot = Math.pow((x-cx)/rx, 2) + Math.pow((y-cy)/ry, 2); //cf formule dans sujet
		return tot<=1;
	}

	@Override
	public Clip copy() {
		return  new ClipEllipse(this.getLeft(), this.getTop(), this.getRight(), this.getBottom(), this.getColor());
	}

}
