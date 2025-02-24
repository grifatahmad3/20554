

import javax.swing.JOptionPane;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class FourInLineController {

    @FXML
    private GridPane grid;
    private FILLogic game;
    private Pane pane[][];
    
    // Height here equals: FILLogic.HEIGHT + 1, that's because of the need of
    // a bottom line for the column numbers
    private final int WIDTH = FILLogic.WIDTH;
    private final int HEIGHT = FILLogic.HEIGHT+1;
    
    
    //Initializes the board and starts the game
    public void initialize() {
    	pane = new Pane[WIDTH][HEIGHT];
    	game = new FILLogic();
    	
    	for(int i=0; i<WIDTH; i++) {
    		for(int j=0; j<HEIGHT; j++) {
    			pane[i][j] = new Pane();
    			pane[i][j].setPrefSize(grid.getPrefWidth()/WIDTH, 
    					grid.getPrefHeight()/HEIGHT);
    			// even though css is not in the course material, this is the fastest 
    			// way to set up borders for each cell
    			pane[i][j].setStyle("-fx-border-color: black");
    			grid.add(pane[i][j], i, j);
    			
    			final int mi = i; //apparently can't use normal int for MouseEvent?
    			final int mj = j;
    			pane[i][j].setOnMouseClicked(new EventHandler<MouseEvent>() {
    				@Override
    				public void handle(MouseEvent event) {
    					if(game.move(mi, mj, game.getTurn())) {
    						handler(event, mi, mj);
    					}
    				}
    			});
    		}
    	}
    	// writing column numbers in the last row
    	for(int k=0; k<WIDTH; k++) {
    		Text text = new Text(""+ (k+1));
    		text.setFont(new Font(50));
			grid.add(text, k, HEIGHT-1);		
    	}
    }
    
    //helper function for pane[i][j].setOnMouseClicked in initialize()
    private void handler(MouseEvent event, int i, int j) {
    	
    	//can't use the last row as it is taken for the column numbers
    	if(j==HEIGHT-1) return;
    	
    	//filling up a circle with a color according to whose turn it is
    	if(game.getTurn()==FILLogic.PLAYER.Player1) {
    		pane[i][j].getChildren().add(new Circle(pane[i][j].getPrefWidth()/2, 
    				pane[i][j].getPrefHeight()/2, pane[i][j].getPrefWidth()/4, Color.RED));
    	}
    	else {
    		pane[i][j].getChildren().add(new Circle(pane[i][j].getPrefWidth()/2, 
    				pane[i][j].getPrefHeight()/2, pane[i][j].getPrefWidth()/4, Color.LIGHTSKYBLUE));
    	}
    	
    	// checking if there is a winner
    	if(game.checkWin(i, j, game.getTurn())) {
    		String winner;
    		if(game.getTurn() == FILLogic.PLAYER.Player1) winner = "Player1";
    		else winner = "Player2";
    		JOptionPane.showMessageDialog(null, winner + " Won!");
    		// restarting the game without reallocation of resources
    		clear();
    		return;
		}
    	
    	// if no winner and the board is full then it's a draw
    	if(game.isFull()) {
    		JOptionPane.showMessageDialog(null, "Draw!");
    		clear();
    		return;
    	}
    	game.changeTurn();
    	
    }

    //clears the board with a button
    @FXML
    void clearPressed(ActionEvent event) {
    	clear();
    }
    
    //helper function for clearPressed, restarts the game while recycling resources
    private void clear() {
    	for(int i=0; i<WIDTH; i++) {
    		for(int j=0; j<HEIGHT; j++) {
    			pane[i][j].getChildren().clear();
    		}
    	}
    	game.clearBoard();
    }
    

}
