package card;

import java.util.Random;

public class Cards {
	private Card[] cards;
	private int drawIndex = 0;
	
	public Cards() {
		setCards(new Card[52]);
		String[] suits = new String[] {"♠", "♥", "♦", "♣"};
		
		for(int i = 0; i < 52; i++) {
			// 0～12 = ♠, 13～25 = ♥, 26～38 = ♦, 39～51 = ♣
			String suit = suits[i / 13];
			// 1～13
			int number = (i % 13) + 1;
			// 各カードのインスタンスを作り、trump配列[52]のなかにそれぞれ入れる
			cards[i] = new Card(suit, number);
		}
	}

	public Card[] getCards() {
		return cards;
	}

	public void setCards(Card[] cards) {
		this.cards = cards;
	}
	
	public int getDrawIndex() {
		return drawIndex;
	}

	public void setDrawIndex(int drawIndex) {
		this.drawIndex = drawIndex;
	}
	
	public void suffle() {
		Random random = new Random();
		
		for(int i = cards.length - 1; i > 0; i--) {
			int randomIndex = random.nextInt(cards.length - i);
			int backIndex = cards.length - 1 - i;
			
			Card temp = cards[randomIndex];
			cards[randomIndex] = cards[backIndex];
			cards[backIndex] = temp;
		}
		// ドロー位置をリセット
		setDrawIndex(0);
	}

	public Card draw() {
		Card drawCard = null;
		if(drawIndex < cards.length) {
			drawCard = cards[drawIndex++];
		}
		return drawCard;
	}
	
	
}
