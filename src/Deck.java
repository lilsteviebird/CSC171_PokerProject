import java.lang.*;

import java.util.Arrays;
import java.util.Random;
import java.util.*;
/*
Steven Li
31647656
Project 2
Lab Days: Tuesday, Thursday 9:40-10:55
I did not collaborate with anyone on this assignment

Class that makes Defines a Deck of 52 cards, along with comparing hands
 */
public class Deck {
	public Card[] cardDeck = new Card[52];
	public int cardsLeft;
	public boolean running = true;
	public Deck() {
		createDeck();
		cardsLeft = 52;
	}

	//creates deck or resets deck
	public void createDeck() {
		int total = 0;
		for(int i = 0; i<4; i++) {
			for(int j = 1; j<=13; j++) {
				if(i==0) {
					Card add = new Card(j, "Hearts");
					cardDeck[total] = add;
					total++;
				}
				if(i==1) {
					Card add = new Card(j, "Diamonds");
					cardDeck[total] = add;
					total++;

				}
				if(i==2) {
					Card add = new Card(j, "Spades");
					cardDeck[total] = add;
					total++;

				}
				if(i==3) {
					Card add = new Card(j, "Clubs");
					cardDeck[total] = add;
					total++;

				}
			}
		}


	}
	
	//prints deck
	public void printDeck() {
		int counter = 0;
		for(int i = 0; i< cardsLeft; i++) {
			counter++;
			if(cardDeck[i].number == 1) {
				System.out.println("Suit: " + cardDeck[i].suit + " Number: Ace");

			}
			else if(cardDeck[i].number == 11) {
				System.out.println("Suit: " + cardDeck[i].suit + " Number: Jack");

			}
			else if(cardDeck[i].number == 12) {
				System.out.println("Suit: " + cardDeck[i].suit + " Number: Queen");

			}
			else if(cardDeck[i].number == 13) {
				System.out.println("Suit: " + cardDeck[i].suit + " Number: King");

			}else {
				System.out.println("Suit: " + cardDeck[i].suit + " Number: " + cardDeck[i].number);

			}
		}
		System.out.println("There are " + counter + " cards in this deck");
	}

	//shuffles, and makes sure that the first card (card at index 51) gets touched and shuffled
	public void shuffle() {
		Random rand = new Random(); 
		int makeSure = 51;
		for(int i = 0; i < cardsLeft-1; i++) {
			makeSure = 51;
			for(int j = cardsLeft-1; j>0 ; j--) {
				if(makeSure == 51) {
					int rando = 51;
					makeSure++;
					int randoTwo = rand.nextInt(j);
					Card holder = new Card(this.cardDeck[rando].number, this.cardDeck[rando].suit);
					this.cardDeck[rando] = this.cardDeck[randoTwo];
					this.cardDeck[randoTwo] = holder;
				}else {
					int rando = rand.nextInt(j);
					int randoTwo = rand.nextInt(j);
					Card holder = new Card(this.cardDeck[rando].number, this.cardDeck[rando].suit);
					this.cardDeck[rando] = this.cardDeck[randoTwo];
					this.cardDeck[randoTwo] = holder;
				}

			}
		}	


	}

	//Gives players hands
	public void dealHands(Player p) {
		Scanner scn = new Scanner(System.in);
		if(cardsLeft < 10) {
			System.out.println("There are not enough cards left in the deck.");
			System.out.println("There are only " + cardsLeft + " cards left.");
			System.out.println("Would you like to shuffle and reset the deck? Press 1 for yes");
			int cont = scn.nextInt();
			if(cont == 1) {
				System.out.println("...resetting deck...");
				cardsLeft = 52;
				createDeck();
				shuffle();
			}else if(cont != 1){
				running =false;
			}
		}else if(cardsLeft > 10 && running == true){
			Card[] give = new Card[5];
			for(int i = 0; i < give.length;i++) {
				give[i] = cardDeck[cardsLeft - (i+1)];
			}
			//			give[0] = cardDeck[cardsLeft - 1];
			//			give[1] = cardDeck[cardsLeft - 2];
			p.setHand(give);
			cardsLeft = cardsLeft - 5;
		}

	}

	public Card[] copyArray(Card[] hand) {
		Card returnArray[] = new Card[5];
		for(int i = 0; i< hand.length; i++) {
			Card add = new Card(hand[i].number, hand[i].suit);
			returnArray[i] = add;
		}
		return returnArray;
	}

	//Below is the methods to check the hands, and each method returns the 
	//relative number depending on how good the hand is, otherwise if the hand
	//is not there, it returns 0
	
	public int checkStraightFlush(Player p) {
		int suitChecker = 0;
		int[] cardNums = new int[5];
		boolean incrementsOne = true;
		for(int i = 0; i < p.hand.length; i++) {
			if(p.hand[0].suit.equals(p.hand[i].suit)) {
				suitChecker++;
			}
			cardNums[i] = p.hand[i].number;
		}
		Arrays.sort(cardNums);


		for(int j = 0; j< cardNums.length-1; j++) {
			if(cardNums[0] + 1+ j != cardNums[j+1]) {
				incrementsOne = false;
			}
		}
		if(incrementsOne && suitChecker == 5) {
			return 9;
		}else {
			return 0;
		}
	}

	public int checkfourKind(Player p) {
		int[] cardNums = new int[5];
		int counter = 0;
		for(int i = 0; i < p.hand.length; i++) {
			cardNums[i] = p.hand[i].number;
		}
		Arrays.sort(cardNums);
		for(int j = 0; j< cardNums.length-1; j++) {
			if(cardNums[0] == cardNums[j+1]) {
				counter++;
			}
		}
		if(counter == 4) {
			return 8;
		}else {
			return 0;
		}
	}

	public int checkFullHouse(Player p) {
		int[] cardNums = new int[5];
		int counter = 0;
		boolean full = true;
		for(int i = 0; i < p.hand.length; i++) {
			cardNums[i] = p.hand[i].number;
		}

		Arrays.sort(cardNums);
		//checks if the array is first two is doubles, and last third is a triplet
		for(int j = 0; j< cardNums.length-1; j++) {
			if(j<1 && cardNums[0] != cardNums[j+1]) {
				full = false;
			}
			if(j>=2 && cardNums[2] != cardNums[j+1]) {
				full = false;
			}
		}
		if(!full) {
			full = true;
			//checks if the array is first three are triplets and the last two are doubles
			for(int j = 0; j< cardNums.length-1; j++) {
				if(j<2 && cardNums[0] != cardNums[j+1]) {
					full = false;
				}
				if(j>=3 && cardNums[3] != cardNums[j+1]) {
					full = false;
				}
			}
		}
		if(full) {
			return 7;
		}else {
			return 0;
		}
	}

	public int checkFlush(Player p) {
		int suitChecker = 0;
		int[] cardNums = new int[5];
		for(int i = 0; i < p.hand.length; i++) {
			if(p.hand[0].suit.equals(p.hand[i].suit)) {
				suitChecker++;
			}
			cardNums[i] = p.hand[i].number;
		}
		if(suitChecker == 5) {
			return 6;
		}else {
			return 0;
		}
	}

	public int checkStraight(Player p) {
		int[] cardNums = new int[5];
		boolean incrementsOne = true;
		for(int i = 0; i < p.hand.length; i++) {
			cardNums[i] = p.hand[i].number;
		}
		Arrays.sort(cardNums);

		for(int j = 0; j< cardNums.length-1; j++) {
			if(cardNums[0] + 1+ j != cardNums[j+1]) {
				incrementsOne = false;
			}
		}
		if(incrementsOne) {
			return 5;
		}else {
			return 0;
		}
	}

	public int checkThreeKind(Player p) {
		int[] cardNums = new int[5];
		boolean threeKind = true;
		for(int i = 0; i < p.hand.length; i++) {
			cardNums[i] = p.hand[i].number;
		}
		Arrays.sort(cardNums);


		for(int j = 0; j < 2; j++) {
			if(cardNums[0] != cardNums[j+1]) {
				threeKind = false;
			}
		}
		if(!threeKind) {
			threeKind = true;

			for(int j = 1; j < 3; j++) {
				if(cardNums[1] != cardNums[j+1]) {
					threeKind = false;
				}
			}
		}
		if(!threeKind) {
			threeKind = true;
			for(int j = 2; j < 4; j++) {
				if(cardNums[2] != cardNums[j+1]) {
					threeKind = false;
				}
			}
		}
		if(threeKind) {
			return 4;
		}else {
			return 0;
		}
	}

	public int checkTwoPair(Player p) {
		int[] cardNums = new int[5];
		int totalPairs = 0;
		for(int i = 0; i < p.hand.length; i++) {
			cardNums[i] = p.hand[i].number;
		}
		Arrays.sort(cardNums);

		for(int j = 0; j < cardNums.length-1; j++) {
			if(cardNums[j] == cardNums[j+1]) {
				totalPairs++;
			}
		}
		if(totalPairs>=2) {
			return 3;
		}else {
			return 0;
		}


	}

	public int checkPair(Player p) {
		int[] cardNums = new int[5];
		int totalPairs = 0;
		for(int i = 0; i < p.hand.length; i++) {
			cardNums[i] = p.hand[i].number;
		}
		Arrays.sort(cardNums);

		for(int j = 0; j < cardNums.length-1; j++) {
			if(cardNums[j] == cardNums[j+1]) {
				totalPairs++;
			}
		}
		if(totalPairs>=1) {
			return 2;
		}else {
			return 0;
		}


	}

	public int checkHighCard(Player p) {
		return 1;	
	}

	public Card returnHighCard(Player p) {
		int[] cardNums = new int[5];
		Card high = new Card(0, "");
		for(int i = 0; i < p.hand.length; i++) {
			cardNums[i] = p.hand[i].number;
		}
		Arrays.sort(cardNums);
		if(cardNums[0]==1) {
			for(int j = 0; j < p.hand.length;j++) {
				if(p.hand[j].number == 1) {
					high.setNumber(p.hand[j].number);
					high.setSuit(p.hand[j].suit);
				}
			}
		}else {
			for(int o = 0; o < p.hand.length;o++) {
				if(p.hand[o].number == cardNums[4]) {
					high.setNumber(p.hand[o].number);
					high.setSuit(p.hand[o].suit);
				}
			}
		}
		return high;
	}

	public int returnHighCardNumber(Player p) {
		int[] cardNums = new int[5];
		for(int i = 0; i <p.hand.length; i++) {
			cardNums[i] = p.hand[i].number;
		}
		Arrays.sort(cardNums);
		if(cardNums[0] == 1) {
			return cardNums[0];
		}else {
			return cardNums[4];
		}
	}

	//returns the number of the highestHand
	public int highestHand(Player p) {
		int[] winningHand = new int[9];
		winningHand[0] = checkStraightFlush(p);
		winningHand[1] = checkfourKind(p);
		winningHand[2] = checkFullHouse(p);
		winningHand[3] = checkFlush(p);
		winningHand[4] = checkStraight(p);
		winningHand[5] = checkThreeKind(p);
		winningHand[6] = checkTwoPair(p);
		winningHand[7] = checkPair(p);
		winningHand[8] = checkHighCard(p);

		Arrays.sort(winningHand);
		return winningHand[8];
	}

	//compares two hands

	public void compareHands(Player p1, Player p2) {
		if(highestHand(p1) > highestHand(p2)) {
			if(highestHand(p1) == 1) {
				System.out.println("\nPlayer " + p1.playerNum + " wins with a "
						+ "high card of..." + returnHighCard(p1).printCard());
			}else if(highestHand(p1) == 2) {
				System.out.println("\nPlayer " + p1.playerNum + " wins with a "
						+ "pair");
			}else if(highestHand(p1) == 3) {
				System.out.println("\nPlayer " + p1.playerNum + " wins with a "
						+ "two pair");
			}else if(highestHand(p1) == 4) {
				System.out.println("\nPlayer " + p1.playerNum + " wins with a "
						+ "three of a kind");
			}else if(highestHand(p1) == 5) {
				System.out.println("\nPlayer " + p1.playerNum + " wins with a "
						+ "straight");
			}else if(highestHand(p1) == 6) {
				System.out.println("\nPlayer " + p1.playerNum + " wins with a "
						+ "flush");
			}else if(highestHand(p1) == 7) {
				System.out.println("\nPlayer " + p1.playerNum + " wins with a "
						+ "full house");
			}else if(highestHand(p1) == 8) {
				System.out.println("\nPlayer " + p1.playerNum + " wins with a "
						+ "four of a kind");
			}else if(highestHand(p1) == 9) {
				System.out.println("\nPlayer " + p1.playerNum + " wins with a "
						+ "straight flush");
			}	
		}else if(highestHand(p1) == highestHand(p2)) {
			System.out.println("\nYou guys have the same winning hand, but");
			if(returnHighCardNumber(p1) > returnHighCardNumber(p2)) {
				System.out.println("\nPlayer " + p1.playerNum + " wins with a "
						+ "high card of... "+ returnHighCard(p1).printCard());
			}else if(returnHighCardNumber(p1) < returnHighCardNumber(p2)) {
				System.out.println("\nPlayer " + p2.playerNum + " wins with a "
						+ "high card of... "+ returnHighCard(p2).printCard());
			}else {
				System.out.println("Because you guys BOTH had a next high card of " + returnHighCard(p2).printNumber()
						+ ", so it's a tie");
			}
		}else {
			if(highestHand(p2) == 1) {
				System.out.println("\nPlayer " + p2.playerNum + " wins with a "
						+ "high card of..." + returnHighCard(p2).printCard());
			}else if(highestHand(p2) == 2) {
				System.out.println("\nPlayer " + p2.playerNum + " wins with a "
						+ "pair");
			}else if(highestHand(p2) == 3) {
				System.out.println("\nPlayer " + p2.playerNum + " wins with a "
						+ "two pair");
			}else if(highestHand(p2) == 4) {
				System.out.println("\nPlayer " + p2.playerNum + " wins with a "
						+ "three of a kind");
			}else if(highestHand(p2) == 5) {
				System.out.println("\nPlayer " + p2.playerNum + " wins with a "
						+ "straight");
			}else if(highestHand(p2) == 6) {
				System.out.println("\nPlayer " + p2.playerNum + " wins with a "
						+ "flush");
			}else if(highestHand(p2) == 7) {
				System.out.println("\nPlayer " + p1.playerNum + " wins with a "
						+ "full house");
			}else if(highestHand(p2) == 8) {
				System.out.println("\nPlayer " + p2.playerNum + " wins with a "
						+ "four of a kind");
			}else if(highestHand(p2) == 9) {
				System.out.println("\nPlayer " + p2.playerNum + " wins with a "
						+ "straight flush");
			}
		}
	}


	public void playGame() {
		Scanner scn = new Scanner(System.in);
		System.out.println("Hello, would you like to play poker? Press 1 to play!");
		int play = scn.nextInt();
		boolean playGame = true;
		Player p1 = new Player(1);
		Player p2 = new Player(2);
		createDeck();
		shuffle();
		while(playGame) {
			if(play == 1) {
				dealHands(p1);
				if(running == true) {
					dealHands(p2);
					p1.printHand();
					p2.printHand();
					compareHands(p1,p2);
				}	
				System.out.println("Keep playing? Press 1 for yes");
				play = scn.nextInt();
				if(play != 1) {
					System.out.println("Okay goodbye!");
					playGame = false;
				}else {
					running = true;
				}
			}
		}


	}
	
	//For Problem 4
	public void driverProgram(String one, String two) {
		char[] first = one.toCharArray();
		char[] second = one.toCharArray();
		
		Card[] firstHand = new Card[5];
		Card[] secondHand = new Card[5];
		
		int total = 0;
		
		for(int j = 0; j<first.length; j++) {
			first[j] = Character.toLowerCase(first[j]);
			second[j] = Character.toLowerCase(second[j]);
			System.out.println(first[j]);
		}
		
		
		for(int i = 0; i <first.length-1; i +=2) {
			Card toAdd = new Card(0,"addMe");
			if(first[i]==('a')) {
				toAdd.setNumber(1);
			}
			if(first[i]=='t') {
				toAdd.setNumber(10);
			}
			if(first[i]==('j')) {
				toAdd.setNumber(11);
			}
			if(first[i]==('q')) {
				toAdd.setNumber(12);
			}
			if(first[i]==('k')) {
				toAdd.setNumber(13);
			}
			if(first[i+1]==('h')) {
				toAdd.setSuit("Hearts");
			}
			if(first[i+1]==('c')) {
				toAdd.setSuit("Clubs");
			}
			if(first[i+1]==('s')) {
				toAdd.setSuit("Spades");
			}
			if(first[i+1]==('d')) {
				toAdd.setSuit("Diamonds");
			}
			if(first[i] != 'a' && first[i] != 't') {
				toAdd.setNumber(Character.getNumericValue(first[i]));
			}
			
			firstHand[total] = toAdd;
			total++;
		}
		
		total = 0;
		
		for(int i = 0; i <second.length-1; i +=2) {
			Card toAdd = new Card(0,"addMe");
			if(second[i]==('a')) {
				toAdd.setNumber(1);
			}
			if(first[i]=='t') {
				toAdd.setNumber(10);
			}
			if(second[i]==('j')) {
				toAdd.setNumber(11);
			}
			if(second[i]==('q')) {
				toAdd.setNumber(12);
			}
			if(second[i]==('k')) {
				toAdd.setNumber(13);
			}
			if(second[i+1]==('h')) {
				toAdd.setSuit("Hearts");
			}
			if(second[i+1]==('c')) {
				toAdd.setSuit("Clubs");
			}
			if(second[i+1]==('s')) {
				toAdd.setSuit("Spades");
			}
			if(second[i+1]==('d')) {
				toAdd.setSuit("Diamonds");
			}
			if(second[i] != 'a' && second[i] != 't') {
				toAdd.setNumber(Character.getNumericValue(first[i]));
			}
			
			secondHand[total] = toAdd;
			total++;
		}
		
		Player fPlayer = new Player(1);
		Player sPlayer = new Player(2);
		fPlayer.setHand(firstHand);
		sPlayer.setHand(secondHand);
		
		fPlayer.printHand();
		sPlayer.printHand();
		
		compareHands(fPlayer, sPlayer);
	}

}
