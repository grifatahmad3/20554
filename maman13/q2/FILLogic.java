
public class FILLogic {
	
	public static final int WIDTH = 7; //static for the Controller to access them
	public static int HEIGHT = 5;
	public enum PLAYER  { None, Player1, Player2};
	
	private final int WIN = 4;
	private PLAYER turn = PLAYER.Player1;
	private PLAYER board[][];
	
	public FILLogic() {
		board = new PLAYER[WIDTH][HEIGHT];
		for (int i=0; i<WIDTH; i++) {
			for (int j=0; j<HEIGHT; j++) {
				board[i][j] = PLAYER.None;
			}
		}
	}
	
	//clears the board without creating a new one
	public void clearBoard() {
		for (int i=0; i<WIDTH; i++) {
			for (int j=0; j<HEIGHT; j++) {
				board[i][j] = PLAYER.None;
			}
		}
		this.turn = PLAYER.Player1;
	}
	
	//check if the cell can used
	public boolean isAllowed(int i, int j) {
		return (i>=0) && (j>=0) && (i<WIDTH) && (j<HEIGHT) && 
				!isFull() && (board[i][j] == PLAYER.None) && 
				isAllowedColumn(i, j);
	}
	
	//helper function for isAllowed, checks if the player is allowed to
	//use the cell in a given column
	private boolean isAllowedColumn(int i, int j) {
		if(j==HEIGHT-1) return true;
		return !(board[i][j+1] == PLAYER.None);
	}

	//check if the board is full
	public boolean isFull() {
		for (int i=0; i<WIDTH; i++) {
			for (int j=0; j<HEIGHT; j++) {
				if (board[i][j] == PLAYER.None)
					return false;
			}
		}
		return true;
	}

	//check if 4 cells in a row match to declare a winner
	public boolean checkWin(final int i, final int j, PLAYER player) {
		
		int k, s, count=0;
		
		//check vertical
		count = 0;
		k=j;
		while(k<HEIGHT && board[i][k]==player) {
			count++;
			k++;
		}
		if(count >= WIN) return true;
		
		
		//check horizontal
		count = 0;
		k = i;
		while(k<WIDTH && board[k][j]==player) {
			count++;
			k++;
		}
		if(count >= WIN) return true;
		
		k=i-1;
		while(k>=0 && k<WIDTH && board[k][j]==player) {
			count++;
			k--;
		}
		if(count >= WIN) return true;
		
		
		//check diagonal
		count = 0;
		k=i;
		s=j;
		while(k<WIDTH && s<HEIGHT && board[k][s] == player) {
			count++;
			k++;
			s++;
		}
		if(count >= WIN) return true;
		
		k=i-1;
		s=j-1;
		while(k>=0 && s>=0 && board[k][s] == player) {
			count++;
			k--;
			s--;
		}
		if(count >= WIN) return true;
		
		
		//check second diagonal
		count = 0;
		k=i;
		s=j;
		while(k<WIDTH && s>=0 && board[k][s] == player) {
			count++;
			k++;
			s--;
		}
		if(count >= WIN) return true;
		
		k=i-1;
		s=j+1;
		while(k>=0 && s>=0 && s<HEIGHT && board[k][s] == player) {
			count++;
			k--;
			s++;
		}
		if(count >= WIN) return true;
		
		
		return false;
	}
	
	//moves the game
	public boolean move(int i, int j, PLAYER player) {
		if(isAllowed(i,j)) {
			board[i][j] = player;
			return true;
		}
		return false;
	}
	
	//changes the turn
	public void changeTurn() {
		if(this.turn == PLAYER.Player1) turn = PLAYER.Player2;
		else turn = PLAYER.Player1;
	}
	
	
	//gets the current turn
	public PLAYER getTurn() {
		return this.turn;
	}
}
