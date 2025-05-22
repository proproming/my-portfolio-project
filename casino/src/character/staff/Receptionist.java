package character.staff;

import character.player.Player;
import color.Color;

public class Receptionist extends Staff {
	private boolean isFirstSpeak;
	
	public Receptionist() {
		setName("受付");
		setAppearance(Color.GREEN + "U" + Color.RESET);
		setPositionX(4);
		setPositionY(2);
		setFirstSpeak(true);
		
	}

	public void setFirstSpeak(boolean firstSpeak) {
		this.isFirstSpeak = firstSpeak;
	}
	
	public boolean isFirstSpeak() {
		return isFirstSpeak;
	}

	@Override
	public void event(Player player) throws InterruptedException {
		introduce(player);
		
	}
	
	public void introduce(Player player) throws InterruptedException {
		String msg = "";
		if(isFirstSpeak()) {
			msg = "『ようこそ " + player.getName() + "様』\n『初回ボーナスでチップを500枚差し上げます』";
			System.out.println(msg);
			Thread.sleep(1000);
			player.addChip(500);
			setFirstSpeak(false);
		}else {
			System.out.println("お楽しみください");
			Thread.sleep(1000);
		}
	}

}
