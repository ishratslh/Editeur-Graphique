package pobj.pinboard.editor;
import java.util.List;

import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.ToolBar;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import pobj.pinboard.document.Board;
import pobj.pinboard.editor.tools.Tool;
import pobj.pinboard.editor.tools.ToolEllipse;
import pobj.pinboard.editor.tools.ToolRect;

public class EditorWindow implements EditorInterface {
	private Board board;
	Tool outil; //outil courant
	private final Canvas canvas = new Canvas(1000, 800);

	public EditorWindow(Stage stage) { //mise en place les éléments de l'interface :
		board = new Board(); //crée une nouvelle planche vide
		stage.setTitle("PinBoard - <untitled>"); //titre de la fenetre

		//y associer une scène
		Menu file = new Menu("File");

		MenuItem newwin = new MenuItem("New");
		newwin.setOnAction( (e)->new EditorWindow(new Stage()) );
		MenuItem close = new MenuItem("Close");
		close.setOnAction((e)-> stage.close());
		file.getItems().addAll(newwin, close);

		Menu edit = new Menu("Edit");
		Menu tools = new Menu("Tools");
		MenuBar menu = new MenuBar(file, edit, tools);

		Button box = new Button("Box");
		Button ellipse = new Button("Ellipse");
		Button img = new Button("Img...");
		ToolBar tool = new ToolBar(box, ellipse, img);
		board.draw(canvas.getGraphicsContext2D());
		EditorWindow i = this;
		Separator sep = new Separator();
		Label statut = new Label("");
		VBox vbox = new VBox();
		vbox.getChildren().add(menu);
		vbox.getChildren().add(tool);
		vbox.getChildren().add(canvas);
		vbox.getChildren().add(sep);
		vbox.getChildren().add(statut);
		Scene scene = new Scene(vbox) ;

		 //évenements liés à l'appui de bouton
		 box.setOnMousePressed(new EventHandler<>() { //
				@Override
				public void handle(MouseEvent arg0) {
					outil = new ToolRect();
					statut.setText(outil.getName(i));

				}
			});
		ellipse.setOnMousePressed(new EventHandler<>() {
			@Override
			public void handle(MouseEvent arg0) {
				outil = new ToolEllipse();
				statut.setText(outil.getName(i));
			}
		});
		 //évenements liés à l'appui de bouton sur le canvas :
		canvas.setOnMousePressed(new EventHandler<MouseEvent>(){
			@Override
			public void handle(MouseEvent arg0) {
				outil.press(i, arg0);
			}
		});

		//évenements liés au déplacement de souris avec un bouton appuyé
		canvas.setOnMouseDragged(new EventHandler<MouseEvent>(){
			@Override
			public void handle(MouseEvent arg0) {
				outil.drag(i, arg0);
				draw();
			}
		});
		////évenements liés au relachage de bouton
		canvas.setOnMouseReleased(new EventHandler<MouseEvent>(){
			@Override
			public void handle(MouseEvent arg0) {
				outil.release(i , arg0);
				board.draw(canvas.getGraphicsContext2D());


			}
		});

		 stage.setScene(scene) ;
		 stage.show();
	}
	public void draw(GraphicsContext ctx) {
		outil.drawFeedback(this, ctx);
	}

	public void draw() {
		GraphicsContext gc = canvas.getGraphicsContext2D();
		board.draw(gc);
		outil.drawFeedback(this, gc);
	}

	@Override
	public Board getBoard() {
		return board;
	}

	@Override
	public Selection getSelection() {
		return null;
	}

	@Override
	public CommandStack getUndoStack() {
		return null;
	}

}
