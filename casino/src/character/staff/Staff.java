package character.staff;

import character.player.Player;

public abstract class Staff {
	private String name;
	// appearance 見た目 
	private String appearance;
	private int positionX;
	private int positionY;
	
	
	
	public String getName() {
		return name;
	}



	public void setName(String name) {
		this.name = name;
	}



	public String getAppearance() {
		return appearance;
	}



	public void setAppearance(String appearance) {
		this.appearance = appearance;
	}



	public int getPositionX() {
		return positionX;
	}



	public void setPositionX(int positionX) {
		this.positionX = positionX;
	}

	
	
	public int getPositionY() {
		return positionY;
	}



	public void setPositionY(int positionY) {
		this.positionY = positionY;
	}



	public abstract void introduce(Player p) throws InterruptedException ;



	public abstract void event(Player player) throws InterruptedException;
}
