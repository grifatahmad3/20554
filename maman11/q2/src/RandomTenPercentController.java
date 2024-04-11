import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.event.ActionEvent;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import java.util.Random;


/**
 * RandomTenPercentController class, draws a matrix along a canvas of JUMP*JUMP pixels cells
 * and then randomly fills (approximately) 10% of the generated cells.
 * */
public class RandomTenPercentController {

    @FXML
    private Canvas canvas;
    private GraphicsContext gc;
    
    final int JUMP = 10; // Jump in pixels between start and end of a cell, so a cell 
    					// will be a JUMP*JUMP pixels in size
    
    public void initialize() {
    	gc = canvas.getGraphicsContext2D();
    }
    
    
    /**
     * The generate function does all the work, as by pressing the button Generate 
     * it draws a matrix on top of the whole canvas and then randomly fills 10% of the cells
     * */
    @FXML
    void generate(ActionEvent event) {
    	
    	gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
        int WIDTH = (int) canvas.getWidth();
        int HEIGHT = (int) canvas.getHeight();
        Random r = new Random();
        
    	for(int i=0; i<WIDTH; i++) {
    		for(int j=0; j<HEIGHT; j++) {
    			gc.setFill(Color.BLACK);
    			gc.strokeRect(i*JUMP, j*JUMP, JUMP, JUMP);
    		}
    	}
    	
    	
    	
    	// I am keeping this loop as I wrote it by myself, but ChatGPT suggested
    	// it might not truly fill in 10% of the cells, so I used the modified version
    	// bellow based on ChatGPT's recommendation
    	
    	/*for(int i=0; i<WIDTH; i++) {
    		for(int j=r.nextInt(HEIGHT/10); j<HEIGHT; j+=r.nextInt(HEIGHT/10)) {
    			gc.setFill(Color.BLACK);
    			gc.fillRect(i*JUMP, j*JUMP, JUMP, JUMP);
    		}
    	}*/
   
    	for (int i = 0; i < WIDTH; i++) {
    	    for (int j = 0; j < HEIGHT; j++) {
    	        if (r.nextDouble() < 0.1) { // 10% probability of filling
    	            gc.setFill(Color.BLACK);
    	            gc.fillRect(i*JUMP, j*JUMP, JUMP, JUMP);
    	        }
    	    }
    	}
    }

}
