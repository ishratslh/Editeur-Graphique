package pobj.pinboard.editor.tools;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import pobj.pinboard.document.ClipEllipse;
import pobj.pinboard.document.ClipRect;
import pobj.pinboard.editor.EditorInterface;

public class ToolEllipse implements Tool {
	double initX, initY;
	double dragX, dragY;

	public ToolEllipse() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void press(EditorInterface i, MouseEvent e) {
		initX = e.getX();
		initY = e.getY();
	}

	@Override
	public void drag(EditorInterface i, MouseEvent e) {
		dragX= e.getX();
		dragY = e.getY();
	}

	@Override
	public void release(EditorInterface i, MouseEvent e) {
		if (dragX<initX || dragY < initY) {
			ClipEllipse r = new ClipEllipse(e.getX(), e.getY(), initX, initY, Color.BLUE);
			i.getBoard().addClip(r);
		}
		else{
			ClipEllipse r = new ClipEllipse(initX, initY, e.getX(), e.getY(), Color.BLUE);
			i.getBoard().addClip(r);
		}
	}
	
	@Override
	public void drawFeedback(EditorInterface i, GraphicsContext gc) {
		gc.setStroke(Color.BLUE);
		if(initX > dragX && initY > dragY) gc.strokeOval(((dragX+initX)/2)-((initX-dragX)/2),((dragY+initY)/2)-((initY-dragY)/2),(initX-dragX),(initY-dragY));
		if(initX > dragX && initY < dragY) gc.strokeOval(((dragX+initX)/2)-((initX-dragX)/2),((initY+dragY)/2)-((dragY-initY)/2),(initX-dragX),(dragY-initY));
		if(initX < dragX && initY > dragY) gc.strokeOval(((initX+dragX)/2)-((dragX-initX)/2),((dragY+initY)/2)-((initY-dragY)/2),(dragX-initX),(initY-dragY));
		if(initX < dragX && initY < dragY) gc.strokeOval(((initX+dragX)/2)-((dragX-initX)/2),((initY+dragY)/2)-((dragY-initY)/2),(dragX-initX),(dragY-initY));

	}

	@Override
	public String getName(EditorInterface editor) {
		return "Filled ellipse tool";
	}

}
