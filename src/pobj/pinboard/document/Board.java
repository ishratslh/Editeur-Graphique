package pobj.pinboard.document;

import java.util.ArrayList;
import java.util.List;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Board implements Clip {
	// planche : conteneur pour un ensemble d’éléments graphiques (List<Clip>)
	private List<Clip> elems;

	public Board() { //construisant une planche vide
		elems = new ArrayList<Clip>();
	}
	public List<Clip> getContents(){
		return elems;
	}

	public void addClip(Clip clip) {
		elems.add(clip);
	}
	public void addClip(List<Clip> clip) {
		elems.addAll(clip);
	}
	public void removeClip(Clip clip) {
		elems.remove(clip);
	}
	public void removeClip(List<Clip> clip) {
		elems.removeAll(clip);
	}

	@Override
	public void draw(GraphicsContext gc) {//dessiner le contenu de la planche
		//d'abord effacer la zone d’affichage
		


		for (Clip c : elems) {
			c.draw(gc);
		}

	}
	@Override
	public double getTop() {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public double getLeft() {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public double getBottom() {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public double getRight() {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public void setGeometry(double left, double top, double right, double bottom) {
		// TODO Auto-generated method stub

	}
	@Override
	public void move(double x, double y) {
		// TODO Auto-generated method stub

	}
	@Override
	public boolean isSelected(double x, double y) {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public void setColor(Color c) {
		// TODO Auto-generated method stub

	}
	@Override
	public Color getColor() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public Clip copy() {
		// TODO Auto-generated method stub
		return null;
	}



}
