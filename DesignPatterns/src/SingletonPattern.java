import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;

class Cards {
	private String[] suits = {"Diamonds", "Spades", "Hearts", "Clubs"};
	private String[] rank = {"A", "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "K", "Q"};
	
	public Cards() {
	}
	
	public String createCard(int i, int j){
		return suits[i]+" "+rank[j];
	}
}

class Singleton {
	private static Singleton firstInstance = null;
	private ArrayList<String> cardList;
	private Cards cards;
	private LinkedList<String> cardDeck;
	private static boolean firstThread = true;
	private Singleton() {
		cardList = new ArrayList<>();
		cards = new Cards();
		for(int i=0; i<4; i++){
			for(int j=0;j<13; j++){
				cardList.add(cards.createCard(i, j));
			}
		}
		cardDeck = new LinkedList<>(cardList);
	}
	
	public static Singleton getInstance() {
		if(firstThread){
			firstThread = false;
			Thread.currentThread();
			
			try{
			Thread.sleep(1000);
			} catch(Exception e){
				e.printStackTrace();
			}
			
		}
		
		synchronized (Singleton.class){
			if (null == firstInstance) {
				firstInstance = new Singleton();
				Collections.shuffle(firstInstance.cardDeck);
			}
		}
		return firstInstance;
	}
	
	public LinkedList<String> getCardDeck(){
		return firstInstance.cardDeck;
	}
	
	public LinkedList<String> getCardsFromDeck(int count){
		LinkedList<String> cardHand = new LinkedList<>();
		for(int i=0; i<count;++i){
			cardHand.add(firstInstance.cardDeck.remove(0));
		}
		return cardHand;
	}
}

class GetTheCards implements Runnable {
	public void run() {
		Singleton newInstance = Singleton.getInstance();
		System.out.println("PLAYER: "+System.identityHashCode(newInstance));
		System.out.println("CARD DECK FOR PLAYER " +newInstance.getCardDeck());
		System.out.println("DRAWING 5 CARDS FOR PLAYER");
		LinkedList<String> playerHand = newInstance.getCardsFromDeck(5);
		System.out.println("PLAYER HAND: "+playerHand);
		System.out.println();
	}
}


public class SingletonPattern {
	public static void main (String args[]) {
		System.out.println("\n WITHOUT THREADS \n");
		Singleton instanceOne = Singleton.getInstance();
		System.out.println("PLAYER 1: "+System.identityHashCode(instanceOne));
		System.out.println("CARD DECK FOR PLAYER 1" +instanceOne.getCardDeck());
		System.out.println("DRAWING 5 CARDS FOR PLAYER 1");
		LinkedList<String> player1Hand = instanceOne.getCardsFromDeck(5);
		System.out.println("PLAYER 1 HAND: "+player1Hand);
		System.out.println();
		Singleton instanceTwo = Singleton.getInstance();
		System.out.println("PLAYER 2: "+System.identityHashCode(instanceTwo));
		System.out.println("CARD DECK FOR PLAYER 2" +instanceTwo.getCardDeck());
		System.out.println("DRAWING 5 CARDS FOR PLAYER 2");
		LinkedList<String> player2Hand = instanceOne.getCardsFromDeck(5);
		System.out.println("PLAYER 2 HAND: "+player2Hand);
		
		System.out.println("\n WITH THREADS \n");
		Runnable getCards = new GetTheCards();
		Runnable getCardsAgain = new GetTheCards();
		new Thread(getCards).start();
		new Thread(getCardsAgain).start();
	
	}

}
