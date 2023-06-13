package pobj.pinboard.editor;

import java.util.ArrayList;
import java.util.List;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import pobj.pinboard.document.Board;
import pobj.pinboard.document.Clip;
public class Selection {
private List<Clip> contents;
	public Selection() {
		this.contents = new ArrayList<>();
	}

	public void select(Board board, double x, double y) {
		//vide d’abord la sélection, puis y ajoute le premier élément
		//graphique de la planche qui contient le pixel à la position spécifiée
		contents.clear();
		for(Clip elem : board.getContents()) {
			if(elem.isSelected(x, y)) {
				contents.add(elem);
			}
		}
	}

	public void toogleSelect(Board board, double x, double y) {
		//élément est ajouté à la sélection s’il n’était pas déjà dans la
		//sélection, et le retire sinon
		for(Clip elem : board.getContents()) {
			if(elem.isSelected(x, y)) {
				if(contents.contains(elem)) {
					contents.remove(elem);
				}
				else {
					contents.add(elem);
				}
			}
		}
	}
	public void clear() {
		contents.clear();
	}

	public List<Clip> getContents(){
		return contents;
	}

	public void drawFeedback(GraphicsContext gc) {
		//dessine le rectangle englobant de tous les éléments de la sélection.
		gc.setStroke(Color.BLACK);
		double left,top,right,bottom;
		left = contents.get(0).getLeft();
		top = contents.get(0).getTop();
		right = contents.get(0).getRight();
		bottom = contents.get(0).getBottom();
		for(Clip elem : contents) {
			if(elem.getLeft() < left) left = elem.getLeft();
			if(elem.getTop() < top) top = elem.getTop();
			if(elem.getRight() > right) right = elem.getRight();
			if(elem.getBottom() > bottom) bottom = elem.getBottom();
		}
		gc.strokeRect(left, top, right-left, bottom-top);
	}
}
