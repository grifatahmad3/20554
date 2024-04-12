
// DeckOfCards class represents a deck of playing cards.
// Copied from the book with some modifications.

import java.security.SecureRandom;
import java.util.ArrayList;

public class DeckOfCards {
   // random number generator
   private static final SecureRandom randomNumbers = new SecureRandom();
   private static final int NUMBER_OF_CARDS = 52; // constant # of Cards

   private ArrayList<Card> deck = new ArrayList<Card>(); 
   private int currentCard = 0; // index of next Card to be dealt (0-51)

   // constructor fills deck of Cards
   public DeckOfCards() {
      String[] faces = {"Deuce", "Three", "Four", "Five", "Six",
         "Seven", "Eight", "Nine", "Ten", "Jack", "Queen", "King", "Ace"};    
      String[] suits = {"Hearts", "Diamonds", "Clubs", "Spades"};      

      // populate deck with Card objects                   
      for (int count = 0; count < NUMBER_OF_CARDS; count++) {
    	 deck.add(count, new Card(faces[count % 13], suits[count / 13]));
      }
   }
   

   // Returns the number of cards in the deck
   public int size() {
	   return NUMBER_OF_CARDS;
   }

   // shuffle deck of Cards with one-pass algorithm
   public void shuffle() {
      // next call to method dealCard should start at deck[0] again
      currentCard = 0; 

      // for each Card, pick another random Card (0-51) and swap them
      for (int first = 0; first < deck.size(); first++) {
         // select a random number between 0 and 51 
         int second = randomNumbers.nextInt(NUMBER_OF_CARDS);

         // swap current Card with randomly selected Card
         Card temp = new Card(deck.get(first));   
         deck.set(first, deck.get(second));
         deck.set(second, temp);      
      } 
   } 

   // deal one Card
   public Card dealCard() {
      // determine whether Cards remain to be dealt
      if (currentCard < deck.size()) {
         return deck.get(currentCard++); // return current Card in array
      } 
      else {
         return null; // return null to indicate that all Cards were dealt
      } 
   }
} 

