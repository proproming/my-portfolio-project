package character.cardGameStaff;

import java.util.Scanner;

import card.Card;
import character.player.Player;
import color.Color;

public class HighAndLowStaff extends CardsGameStaff {
	private Scanner scanner =  new Scanner(System.in);
	
	public HighAndLowStaff() {
		setName("ハイアンドロースタッフ");
		setAppearance(Color.RED + "H" + Color.RESET);
		setPositionX(1);
		setPositionY(2);
		
	}
	
	@Override
	public void event(Player player) throws InterruptedException {
		introduce(player);
		game(player);
	}
	
	public void introduce(Player p1) {
		System.out.println("『ようこそ" + p1.getName() + "様』");
		System.out.println("『こちらはハイアンドローゲームエリアです。』");
		System.out.println("\n" +"数字は、１～１３の数があり");
		System.out.println("あなたのカードがディーラ―より、数字が大きい(high)か小さい(low)を選択し");
		System.out.println("当たった場合はベット額の倍のチップが手に入ります");
		System.out.println("\n" + "(同じ数字だった場合は絵柄で強弱を判定します)");
		System.out.println("(←high     low→)");
		System.out.println("(♠ > ♥ > ♦ > ♣ )");
		System.out.println("\nプレイしますか？ \nプレイする場合はEnterキー \nやめる場合は n を入力してください");	
		System.out.println();
	}
	
	public void game(Player p1)  throws InterruptedException {
		int bet = 0;
		boolean isPlay = p1.playOrNot();
		if(isPlay) {
			bet = p1.bet();
			if(bet == 0) {
				// メソッド終了
				return;
			}
		}else {
			// メソッド終了
			return;
		}
		
		cardsShuffle();
		System.out.println("『ハイアンドローゲーム スタート!』");
		Card staffCard = drawCard();
		Card playerCard = drawCard();
		
		Thread.sleep(1000);
		System.out.println("ディーラーがカードをめくった");
		System.out.println("ディーラ―のカードは" + staffCard.getSuit() + "の" + staffCard.getNumber());
		Thread.sleep(1500);
		
		boolean isWin = false;
		System.out.println("\nあなたの手札が" + staffCard.getSuit() + "の" + staffCard.getNumber() + "より大きいか小さいか");
//		System.out.println("デバッグ用  あなたの手札:" + playerCard.getSuit() + "の" + playerCard.getNumber());

		System.out.println("↓\n大きい: h を入力　\n小さい:l(L) を入力");
		String input = p1.highAndLowInput();
		
		isWin = judge(playerCard, staffCard, input);

		String resultMsg;
		if(isWin) {
			resultMsg = "あなた:" + staffCard.getSuit() + "の" +playerCard.getNumber() + 
						"\n" + "ディーラー:" + staffCard.getSuit() + "の" +staffCard.getNumber() + 
						"あなたの勝ち！\n" + "チップ" + bet * 2 + "枚をゲットした！";
			p1.addChip(bet * 2);

		}else {
			resultMsg = "あなた" + playerCard.getNumber() + "ディーラ―" + staffCard.getNumber() + "あなたの負け…";
		}
		
		System.out.println(resultMsg);
		
		System.out.println("エンターキーで戻る");
		scanner.nextLine();
	}

	public boolean judge(Card playerCard, Card staffCard, String input) {
		boolean isWin = false;
		// カード比較用の変数
		String playerCardSuit = playerCard.getSuit();
		String staffCardSuit = staffCard.getSuit();
		int playerCardNum = playerCard.getNumber();
		int staffCardNum = staffCard.getNumber();
		
		if(input.equals("h") || input.equals("H")) {
			if(playerCardNum > staffCardNum) {
				isWin = true;
			}else if(playerCardNum == staffCardNum) {
				// highで、数字が同じだった場合の勝ちの条件
				// ←high     low→
				// ♠ > ♥ > ♦ > ♣ 
				if(playerCardSuit.equals("♠") ||
				  (playerCardSuit.equals("♥") && !staffCardSuit.equals("♠")) ||
				  (playerCardSuit.equals("♦") && staffCardSuit.equals("♣"))){
					isWin = true;
				}
			}
		}
		if(input.equals("l") || input.equals("L")) {
			if(playerCardNum < staffCardNum) {
				isWin = true;
			}else if(playerCardNum == staffCardNum) {
				// lowで、数字が同じだった場合の勝ちの条件
				if(playerCardSuit.equals("♣") ||
				  (playerCardSuit.equals("♦") && !staffCardSuit.equals("♣")) ||
				  (playerCardSuit.equals("♥") && staffCardSuit.equals("♠"))) {
					isWin = true;
				}
			}
		}	
		
		return isWin;
	}
}
