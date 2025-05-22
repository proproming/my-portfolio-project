package character.gameStaff;

import character.player.Player;
import character.staff.Staff;

public abstract class GameStaff extends Staff{
	public abstract void game(Player p) throws InterruptedException;
}
