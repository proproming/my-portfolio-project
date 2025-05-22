package character.cardGameStaff;

import card.Card;
import card.Cards;
import character.player.Player;
import character.staff.Staff;

public abstract class CardsGameStaff extends Staff{
	private Cards trump;

	public CardsGameStaff() {
		setTrump(new Cards());
		// 初期状態でシャッフル
		cardsShuffle();
	}
	
	public Cards getTrump() {
		return trump;
	}

	public void setTrump(Cards trump) {
		this.trump = trump;
	}
	
	public void cardsShuffle() {
		trump.suffle();
	}
	
	// 1枚カードを引く
	public Card drawCard() {
		return trump.draw();
	}
	
	public abstract void game(Player p) throws InterruptedException;

}
		

