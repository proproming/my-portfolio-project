package character.player;

import java.util.Scanner;

import character.Character;
import character.staff.Staff;
import color.Color;

public class Player extends Character{
	private int chip;
	private Scanner scanner = new Scanner(System.in);
	
	public Player() {
		setAppearance(Color.YELLOW + "@" + Color.RESET);
		setPositionX(4);
		setPositionY(6);
		setChip(0);

	}

	public int getChip() {
		return chip;
	}

	public void setChip(int chip) {
		this.chip = chip;
	}
	
	public void addChip(int chip) {
		this.chip += chip;
	}
	
	public void betChip(int chip) {
		this.chip -= chip;
	}
	
	public String getDirection() {
		String input = "";
		boolean isUnvalidInput = true;
		while(isUnvalidInput) {
			System.out.println("移動方向を入力してください。w:↑  a:←  s:↓  d:→");
			input = scanner.nextLine();
			if(1 == input.length()) {
				isUnvalidInput = false;
			}
		}		
		return input;
	}

	public void move(Staff[] staff, int height, int width) throws InterruptedException {
		String direction = getDirection(); 
		boolean isEvent = false;
		
		int prevX = getPositionX();
		int prevY = getPositionY();
		
		if((direction.equals("w") || direction.equals("W") ) && getPositionY() > 0) {
			setPositionY(prevY - 1);
		}else if(direction.equals("s") || direction.equals("S") && getPositionY() < height - 1) {
			setPositionY(prevY + 1);
		}else if(direction.equals("a") || direction.equals("A")&& getPositionX() > 0) {
			setPositionX(prevX - 1);
		}else if(direction.equals("d") || direction.equals("D")&& getPositionX() < width - 1) {
			setPositionX(prevX + 1);
		}else {
			System.out.println("範囲外でその方向へは進めません");
			return;
		}
		
		for(int i = 0; i < staff.length; i++) {
			if(getPositionX() == staff[i].getPositionX() && getPositionY() == staff[i].getPositionY()) {
				staff[i].event(this);
				isEvent = true;
			}
		}
		if(isEvent) {
			setPositionX(prevX);
			setPositionY(prevY);
		}		
	}

	public int bet() throws InterruptedException {
		int bet = 0;
		
		System.out.println("ベット金額を入力してください。 あなたの所持チップ:" + getChip());
		System.out.println("0を入力するとゲームを中断できます");
		bet = scanner.nextInt();
		if(bet <= getChip()) {
			addChip(-bet);
			System.out.println(bet + "枚ベットしました。あなたの所持チップ" + getChip());
		}else {
			System.out.println("所持チップが足りません");
			bet = 0;
			Thread.sleep(1000);
		}
		// nextIntのバッファを捨て読み；
		scanner.nextLine();

		return bet;
	}
	
	public boolean playOrNot() {
		boolean isPlay = true;		
		if(scanner.nextLine().equals("n")) {
			isPlay = false;
		}		
		return isPlay;
	}
	
	public int jankenHandInput(){
		int handNum = 0;
		int tempNum = 0;
		boolean isUnvailidInput = true;
		while(isUnvailidInput) {
			tempNum = scanner.nextInt();
			if(1 > tempNum  || tempNum > 3) {
				System.out.println("範囲外です。再度入力してください。");
			}else {
				handNum = tempNum;
				isUnvailidInput = false;
			}
		}
		return handNum;
	}
	public String highAndLowInput() {
		String input = "";
		boolean isUnvalidInput = true;
		while(isUnvalidInput) {
			input = scanner.nextLine();
			if(1 == input.length() && 
			  (input.equals("l") || input.equals("L") || 
			   input.equals("h") || input.equals("H"))
			){
				isUnvalidInput = false;
			}else{
				System.out.println("エラー、再度入力してください");
			}
		}		
		
		return input;
	}
}
