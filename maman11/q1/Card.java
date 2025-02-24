
// Card class represents a playing card.
// Copied from the book with some modifications.

public class Card {
   private final String face; // face of card ("Ace", "Deuce", ...)
   private final String suit; // suit of card ("Hearts", "Diamonds", ...)

   // two-argument constructor initializes card's face and suit
   public Card(String cardFace, String cardSuit) {
      this.face = cardFace; // initialize face of card
      this.suit = cardSuit; // initialize suit of card
   }
   
   //Copy constructor
   public Card(Card other) {
	   this.face = other.getFace();
	   this.suit = other.getSuit();
   }
   
   public String getFace() {
	   return new String(this.face);
   }
   
   public String getSuit() {
	   return new String(this.suit);
   }
   
   //returns the value of the card at index i
   public int value() {
	   switch (this.face) {
	   case "Ace": return 14;
	   case "King": return 13;
	   case "Queen": return 12;
	   case "Jack": return 11;
	   case "Ten": return 10;
	   case "Nine": return 9;
	   case "Eight": return 8;
	   case "Seven": return 7;
	   case "Six": return 6;
	   case "Five": return 5;
	   case "Four": return 4;
	   case "Three": return 3;
	   case "Deuce": return 2;
	   default: return 1;
	   }
   }

   // return String representation of Card
   public String toString() {             
      return face + " of " + suit;        
   }                
} 
