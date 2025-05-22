package gameController;

import java.util.Scanner;

import character.cardGameStaff.HighAndLowStaff;
import character.gameStaff.JankenStaff;
import character.player.Player;
import character.staff.Receptionist;
import character.staff.Staff;

public class GameController {
	private int width;
	private int height;
	private String[][] grid;
	private String msg;
	private Scanner scanner = new Scanner(System.in);
	private Player player;
	private Receptionist receptionist = new Receptionist();
	private JankenStaff jankenStaff = new JankenStaff();
	private HighAndLowStaff highAndLowStaff = new HighAndLowStaff();
	private Staff[] staff = new Staff[] {receptionist, jankenStaff, highAndLowStaff}; 
	
	
	public GameController() {
		player = new Player();
		setWidth(10);
		setHeight(8);
		setGrid(new String[getHeight()][getWidth()]);
		setMsg("");
	}
	
	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public String[][] getGrid() {
		return grid;
	}

	public void setGrid(String[][] grid) {
		this.grid = grid;
	}
	
	public String getGridCell(int y, int x) {
		return grid[y][x];
	}
	
	public void setGridCell(int y, int x, String value) {
		grid[y][x] = value;
	} 
		
	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public void mapClear() {
		for(int i = 0; i < getHeight(); i++) {
			for(int j = 0; j < getWidth(); j++) {
				setGridCell(i, j, ".");
			}
		}
	}
	public void setPlayerPosition(Player player) {
		grid[player.getPositionY()][player.getPositionX()] = player.getAppearance();
	}
	public void setNpcPosition(Staff[] staff) {
		for(int i = 0; i < staff.length; i++) {
			grid[staff[i].getPositionY()][staff[i].getPositionX()] = staff[i].getAppearance();
		}
	}
	
	public void start(GameController game) throws InterruptedException {
		showIntroMsg();
		player.setName(inputName());
		gameLoop();
	}	
	
	public void gameLoop() throws InterruptedException {
		boolean isPlay = true;
		while(isPlay) {
			if(!receptionist.isFirstSpeak()&& player.getChip() == 0) {
				System.out.println("所持チップが０になってしまった…");
				System.out.println("ゲームオーバー");
				// メソッド終了
				return;
			}else if(player.getChip() >= 50000) {
				System.out.println("おめでとう！所持チップが5万を超えた！");
				System.out.println("ゲームクリア");
				// メソッド終了
				return;
			}
			mapClear();
			setCharacterPosition();
			display(player);
			player.move(staff, this.getHeight(), this.getWidth());
		}
	}
	public void showIntroMsg() {
		System.out.println("【カジノゲーム】");
		System.out.println("あなたは数年前に友人から頼まれ、\n借金の保証人になったが");
		System.out.println("友人が蒸発\n代わりに返済することになってしまった…");
		System.out.println("『こうなったら闇カジノでお金を手に入れるしかない…』\n");
		System.out.println("--------------");
		System.out.println("↓主人公の名前を入力してください");
	}
		
	public String inputName() {
		String name = scanner.nextLine();
		return name;
	}
	
	public void setCharacterPosition() {
		setPlayerPosition(player);
		setNpcPosition(staff);
	}
	
	public void display(Player player) {
		// 大量に改行して疑似的に上書きしたように見せる
		for(int i = 0; i < 20; i++) {
			System.out.println();
		}
		System.out.println();
		for(int i = 0; i < height; i++) {
			for(int j = 0; j < width; j++) {
				System.out.print(grid[i][j]);
			}
			if(i == 2) {
				System.out.print("   " + highAndLowStaff.getAppearance() + " = "
						 + highAndLowStaff.getName());
			}else if(i == 3) {
				System.out.print("   " + receptionist.getAppearance() + " = "
						 + receptionist.getName());
			}else if(i == 4) {
				System.out.print("   " + jankenStaff.getAppearance() + " = " 
				         + jankenStaff.getName());
			}else if (i == 5) {
				System.out.print("   " + player.getAppearance() + " = " + "あなた");
			}
			
			System.out.println();
		}

		System.out.println("ゲームクリアに必要なチップ: 50000枚");
		// チップ表示
		System.out.println("あなたの所持チップ:" + player.getChip());
		System.out.println(this.msg);
	}
}
