package pobj.pinboard.document;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class ClipRect extends AbstractClip implements Clip  {
	public ClipRect(double left, double top, double right, double bottom, Color color) {
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
		if(initX > dragX && initY > dragY) ctx.fillRect(dragX, dragY, initX-dragX, initY-dragY);
		if(initX > dragX && initY < dragY) ctx.fillRect(dragX, initY, initX-dragX, dragY-initY);
		if(initX < dragX && initY > dragY) ctx.fillRect(initX, dragY, dragX-initX, initY-dragY);
		if(initX < dragX && initY < dragY) ctx.fillRect(initX, initY, dragX-initX, dragY-initY);
	}


	@Override
	public Clip copy() {

		return new ClipRect(this.getLeft(), this.getTop(), this.getRight(), this.getBottom(), this.getColor());
	}

}
