
// War class represents and controls a game of war.

import java.util.ArrayList;

import javax.swing.JOptionPane;

public class War {
	
	//Used for saving the result when having multiple ties that lead to not enough cards
	enum Result {NONE, TIE, PLAYER1, PLAYER2}; 
	
	// execute application
	public static void main(String[] args) {

		Result result = Result.NONE;
		DeckOfCards deck = new DeckOfCards();
		deck.shuffle(); // place Cards in random order
		ArrayList<Card> player1 = new ArrayList<Card>(); // A player is just a set of cards.
		ArrayList<Card> player2 = new ArrayList<Card>();

		// Splitting the deck
		for (int i=0; i<deck.size()/2; i++) {
			player1.add(deck.dealCard());
			player2.add(deck.dealCard());
		}
		// Welcoming message
		String welcome = "Welcome to WAR!\n\n" + "The deck has " + deck.size() + 
				" cards and will be\nsplitted in half for each player.\n\nClick OK to start";
		JOptionPane.showMessageDialog(null, welcome);

		
		// Start the game
		while(player1.size()>0 && player2.size()>0) {
			String winner = ""; // for JOptionPane 
			String cards = ""; // for JOptionPane 
			Card card1 = player1.get(player1.size()-1); // Taking out a card
			Card card2 = player2.get(player2.size()-1);
			
			//  If player1 has a more valuable card
			if(card1.value()>card2.value()) {
				cards = "Player1: " + card1 + ",\nPlayer2: " + card2 + ",";
				winner = "\n\nPlayer1 won the round!";
				Card temp = new Card(card1);
				player1.remove(card1);
				player1.add(0, temp);
				player1.add(0, new Card(card2));
				player2.remove(card2);
			}
			
			//  If player2 has a more valuable card
			else if(card2.value()>card1.value()){
				cards = "Player1 drew: " + card1 + ",\nPlayer2 drew: " + card2 + ",";
				winner = "\n\nPlayer2 won the round!";
				Card temp = new Card(card2);
				player2.remove(card2);
				player2.add(0, temp);
				player2.add(0, new Card(card1));
				player1.remove(card1);
			}
			
			// It's a tie
			else {
				// Card ArrayList for collecting the cards to be won by any of the players
				ArrayList<Card> cardsToWin = new ArrayList<Card>(); 
				cardsToWin.add(0, card1);
				cardsToWin.add(1, card2);
				player1.remove(card1);
				player2.remove(card2);
				
				// While both can still draw 3 cards
				while(player1.size()>=3 && player2.size()>=3) {
					
					// Drawing cards and collecting them in the cardToWin array
					Card card11 = player1.get(player1.size()-1);
					cardsToWin.add(card11);
					Card card21 = player2.get(player2.size()-1);
					cardsToWin.add(card21);
					Card card12 = player1.get(player1.size()-2);
					cardsToWin.add(card12);
					Card card22 = player2.get(player2.size()-2);
					cardsToWin.add(card22);
					Card card13 = player1.get(player1.size()-3);
					cardsToWin.add(card13);
					Card card23 = player2.get(player2.size()-3);
					cardsToWin.add(card23);
					
					
					cards = "\nOld cards:\n" + "Player1 drew: " + cardsToWin.get(0) + ",\nPlayer2 drew: " 
							+ cardsToWin.get(1) + ",\n\nNew cards:\n"+
							"Player1 drew: " + card13 + ",\nPlayer2 drew: " + card23 + ",\n";
					
					JOptionPane.showMessageDialog(null, "It's WAR!\nPlayers will draw 3 cards each\n" + cards + 
							"\n\nPlayer1 has " + player1.size()+ " cards" +
							"\nPlayer2 has " + player2.size()+" cards\n");

					//  If player1 has a more valuable card
					if(card13.value()>card23.value()) {
						
						winner = "\n\nPlayer1 won the round!";
						
						player1.remove(card11);
						player1.remove(card12);
						player1.remove(card13);
						player2.remove(card21);
						player2.remove(card22);
						player2.remove(card23);
						while(cardsToWin.size()>0) {
							player1.add(0, new Card(cardsToWin.remove(0)));
						}
						
						break;
					}
					
					//  If player1 has a more valuable card
					else if(card23.value()>card13.value()) {
						
						winner = "\n\nPlayer2 won the round!";
						
						player1.remove(card11);
						player1.remove(card12);
						player1.remove(card13);
						player2.remove(card21);
						player2.remove(card22);
						player2.remove(card23);
						while(cardsToWin.size()>0) {
							player2.add(0, new Card(cardsToWin.remove(0)));
						}
						break;
					}
					
					// Ugh... Another tie...
					else {
						player1.remove(card11);
						player1.remove(card12);
						player1.remove(card13);
						player2.remove(card21);
						player2.remove(card22);
						player2.remove(card23);
						
						//Tie again but no enough cards for one of the players
						if(player1.size()<3 && player2.size()>=3) {
							winner = "\n\nPlayer1 doesn't have enough cards, Player2 won!";
							result = Result.PLAYER2;
							break;
						}
						else if(player2.size()<3 && player1.size()>=3) {
							winner = "\n\nPlayer2 doesn't have enough cards, Player1 won!";
							result = Result.PLAYER1;
							break;
						}
						else if(player2.size()<3 && player1.size()<3){
							winner = "\n\nNeither have enough cards, it's a tie!";
							result = Result.TIE;
							break;
						}
						continue;
					}
				}
			}	

			String message = cards + winner + "\n\nPlayer1 has " + player1.size()+ " cards" +
					"\nPlayer2 has " + player2.size()+" cards\n";
			JOptionPane.showMessageDialog(null, message);
			


		}

		
		if(result==Result.NONE) {
			if(player1.size()<=0 && player2.size()>0) {
				JOptionPane.showMessageDialog(null, "Player2 won the game!");
			}
			else if(player1.size()>0 && player2.size()<=0) {
				JOptionPane.showMessageDialog(null, "Player1 won the game!");
			}
			else {
				JOptionPane.showMessageDialog(null, "The game finished in a tie!");
			}
		}
		else {
			switch(result) {
			case TIE: {JOptionPane.showMessageDialog(null, "The game finished in a tie!");
			return;
			}
			case PLAYER1: {JOptionPane.showMessageDialog(null, "Player1 won the game!");
			return;
			}
			case PLAYER2: {JOptionPane.showMessageDialog(null, "Player2 won the game!");
			return;
			}
			default: return;
			}
		}
	} 
} 
