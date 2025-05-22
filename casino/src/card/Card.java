package card;

public class Card {
	private int number;
	private String suit;
	
	public Card(String suit, int number) {
		setSuit(suit);
		setNumber(number);
		
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public String getSuit() {
		return suit;
	}

	public void setSuit(String suit) {
		this.suit = suit;
	}
	
	
}
