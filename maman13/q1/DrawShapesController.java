import java.util.ArrayList;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;
import javafx.scene.input.MouseEvent;

public class DrawShapesController {

	private ArrayList<Shape> shapes = new ArrayList<Shape>();
    
	private double startingX = 0;
	private double startingY = 0;
	
    @FXML
    private RadioButton line;

    @FXML
    private ToggleGroup shape;

    @FXML
    private RadioButton rectangle;

    @FXML
    private RadioButton circle;

    @FXML
    private RadioButton full;

    @FXML
    private ToggleGroup filling;

    @FXML
    private RadioButton empty;

    @FXML
    private ColorPicker colorPick;

    @FXML
    private Pane pane;


    @FXML
    void clearPressed(ActionEvent event) {
    	if(shapes.size()>0) {
    		pane.getChildren().removeAll(shapes);
    		shapes.removeAll(shapes);
    	}
    }
    
    @FXML
    void mousePressed(MouseEvent event) {
    	startingX = event.getX();
    	startingY = event.getY();
    }
    
    @FXML
    void mouseReleased(MouseEvent event) {
    	
    	final double strokeWidth = 2; //stroke width around circle or rectangle
    	
    	if(shape.getSelectedToggle().equals(line)) {
    		Line l1 = new Line(startingX,startingY, event.getSceneX(), event.getSceneY());
    		shapes.add(l1);
    		pane.getChildren().add(l1);
    	}
    	
    	else if(shape.getSelectedToggle().equals(circle)) {
    		Color c = colorPick.getValue();
    		Circle c1 = new Circle((startingX+event.getSceneX())/2, (startingY+event.getSceneY())/2, 
    									Math.abs((startingX-event.getSceneX())/2));
    		if(filling.getSelectedToggle().equals(full)) c1.setFill(c);
    		else {
    			c1.setFill(Color.TRANSPARENT);
    			c1.setStroke(c);
    			c1.setStrokeWidth(strokeWidth);
    		}
    		shapes.add(c1);
    		pane.getChildren().add(c1);
    	}
    	
    	else if(shape.getSelectedToggle().equals(rectangle)) {
    		Color c = colorPick.getValue();
    		//recStartingX for when moving the mouse left or right, 
    		//to decide to which side the rectangle goes
    		double recStartingX = (startingX<event.getSceneX())?startingX:event.getSceneX();
    		Rectangle r1 = new Rectangle(recStartingX, startingY, 
    									Math.abs(startingX-event.getSceneX()),
    									Math.abs(startingY-event.getSceneY()));
    		if(filling.getSelectedToggle().equals(full)) r1.setFill(c);
    		else {
    			r1.setFill(Color.TRANSPARENT);
    			r1.setStroke(c);
    			r1.setStrokeWidth(strokeWidth);
    		}
    		shapes.add(r1);
    		pane.getChildren().add(r1);
    	}
    	else return;
    }

    @FXML
    void undoPressed(ActionEvent event) {
    	if(shapes.size()>0) {
    		pane.getChildren().remove(shapes.get(shapes.size()-1));
    		shapes.remove(shapes.size()-1);
    	}
    }

}
