Steven Li
31647656
Project 2
Lab Days: Tuesday, Thursday 9:40-10:55
I did not collaborate with anyone on this assignment

1.) I solved the poker problem by dividing the project into three classes. Cards, Players, and Decks. 
I created the deck with a simple for loop adding a new Card object into a Card array. 
I shuffled the deck by using Math.random, exchanging two places in the Card array with each other from the random 
number generated. Comparing the hands was a little easier because I left the card numbers as numbers, even if it
was an Ace, Jack, Queen, or King. (It just displays those words when those cards come up, but there value is still 
1,11,12,13 respectively) I would take the hand, get the card numbers, and sort the array. I would check the number of times 
a certain suit popped up if necessary, otherwise, I would then check the number of times each number appeared, and this process
was easier, especially checking for straights, because the numbers were already sorted. 

2.) If you'd like to play a text based poker game, please uncomment deck.playGame() in the runner file. This will prompt
you simple text commands, and will keep going until you are out of cards, and then it will ask you if you'd like to reset the 
deck and reshuffle. (This game takes cards off the first five cards for each hand, which means 51,50,49... in the array) The 
output will show you the players hands, and will tell you who won. (If it's a tie, it goes to highest card)

3.) For user created test please comment out the playGame method in the runner class, and use the deck.driverMethod();
