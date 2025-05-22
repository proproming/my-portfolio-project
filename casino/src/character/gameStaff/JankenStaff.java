package character.gameStaff;

import java.util.Random;

import character.player.Player;
import character.staff.Receptionist;
import color.Color;

public class JankenStaff extends Receptionist {
	private final int ROCK = 1;
	private final int SCISSORS = 2;
	private final int PAPER = 3;
		
	public JankenStaff() {
		setName("ジャンケンスタッフ");
		setAppearance(Color.CYAN +"J" + Color.RESET);
		setPositionX(7);
		setPositionY(2);
		
	}
	
	public void event(Player player) throws InterruptedException {
		introduce(player);
		game(player);
	}
	
	public void introduce(Player player) {
		System.out.println("『ようこそ" + player.getName() + "様』");
		System.out.println("『こちらはジャンケンゲームエリアです。』");
		System.out.println("『勝利した場合 掛け金の倍の額が手に入ります!』");
		System.out.println("\nプレイしますか？ \nプレイする場合はEnterキー \nやめる場合は n を入力してください");	
		System.out.println();
	}
	
	public void game(Player player) throws InterruptedException {
		boolean isEven = true;
		boolean isWin = false;
		String resultMsg = "";
		int p1Hand = 0;
		int cpuHand = 0;
		int bet = 0;
		
		boolean isPlay = player.playOrNot();
		if(!isPlay) {
			// メソッド終了
			return;
		
		}else{
			bet = player.bet();
			if(bet == 0) {
				// メソッド終了
				return;
			}
		}
		System.out.println("ジャンケンゲームスタート");
		System.out.println("じゃんけーん・・・");
		while(isEven) {
			System.out.println("ジャンケンの手を数字で入力してください 1.グー 2.チョキ. 3.パー");
			p1Hand = player.jankenHandInput();
			cpuHand = new Random().nextInt(3) + 1;
			if((p1Hand == ROCK && cpuHand == SCISSORS) ||
			  (p1Hand == PAPER && cpuHand == ROCK) ||
			  (p1Hand == SCISSORS && cpuHand == PAPER)){
				resultMsg = "あなたの勝ち！ チップ" + bet * 2 + "枚をゲットした！";
				player.addChip(bet * 2);
				isEven = false;
				isWin = true;
				
			}else if(p1Hand == cpuHand) {
				resultMsg = "あいこ！";
			}else {
				resultMsg = "あなたの負け…";
				isEven = false;
			}
			printStgHand(p1Hand, cpuHand);
			Thread.sleep(500);
			System.out.println(resultMsg);
			Thread.sleep(1000);
			if(isWin) {

			}else {

			}
		}
	}

	public void printStgHand(int playerHand, int cpuHand) throws InterruptedException {
		String stgPlayerHand = "";
		String stgCpuHand = "";
		if(playerHand == 1) {
			stgPlayerHand = "グー";
		}else if(playerHand == 2) {
			stgPlayerHand = "チョキ";
		}else if(playerHand == 3) {
			stgPlayerHand = "パー";
		}
		
		if(cpuHand == 1) {
			stgCpuHand = "グー";
		}else if(cpuHand == 2){
			stgCpuHand = "チョキ";
		}else if(cpuHand == 3) {
			stgCpuHand = "パー";
		}
		System.out.println("あなたは" + stgPlayerHand);
		Thread.sleep(600);
		System.out.println("相手は" + stgCpuHand + "を出してきたので");
	}
}
