/*
Steven Li
31647656
Project 2
Lab Days: Tuesday, Thursday 9:40-10:55
I did not collaborate with anyone on this assignment

Class that makes a player who has a hand of 5 cards
 */
public class Player {
	public int ranking;//never ended up using it
	public int playerNum;
	public Card[] hand = new Card[5];
	
	public Player(int playerNum) {
		ranking = 0;
		this.playerNum = playerNum;
	}
	
	public void printHand() {
		System.out.println("\nPlayer " + playerNum + "|| " + hand[0].printCard() + "||" + hand[1].printCard()
				+ "||" + hand[2].printCard()+ "\n||" + hand[3].printCard()+ "||" + hand[4].printCard());
	}
	
	public int getRanking() {
		return ranking;
	}
	
	public Card[] getHand() {
		return hand;
	}
	
	public void setRanking(int ranking) {
		this.ranking = ranking;
	}
	
	public void setHand(Card[] hand) {
		this.hand = hand;
	}
	
	
	public Card[] copyArray(Card[] hand) {
		Card returnArray[] = new Card[5];
		for(int i = 0; i< hand.length; i++) {
			Card add = new Card(hand[i].number, hand[i].suit);
			returnArray[i] = add;
		}
		return returnArray;
	}
}
