/*
Steven Li
31647656
Project 2
Lab Days: Tuesday, Thursday 9:40-10:55
I did not collaborate with anyone on this assignment

Class that makes defines a card to contain a number and a suit
 */
public class Card {
	public int number;
	public String suit;

	public Card(int number, String suit) {
		this.number = number;
		this.suit = suit;
	}

	public String printCard() {
		if(this.number==1) {
			return "Suit: " + this.suit + " " + "Number: Ace";
		}
		if(this.number == 11) {
			return "Suit: " + this.suit + " " + "Number: Jack";
		}
		if(this.number == 12) {
			return "Suit: " + this.suit + " " + "Number: Queen";
		}
		if(this.number == 13) {
			return "Suit: " + this.suit + " " + "Number: King";
		}
		else {
			return "Suit: " + this.suit + " " + "Number: " +this.number;
		}
	}
	
	public String printNumber() {
		if(this.number==1) {
			return  "Ace";
		}
		if(this.number == 11) {
			return "Jack";
		}
		if(this.number == 12) {
			return "Queen";
		}
		if(this.number == 13) {
			return "King";
		}
		else {
			return ""+ this.number;
		}
	}
	
	public int getNumber() {
		return this.number;
	}
	public String getSuit() {
		return this.suit;
	}

	public void setNumber(int number) {
		this.number = number;
	}
	public void setSuit(String suit) {
		this.suit = suit;
	}
}
