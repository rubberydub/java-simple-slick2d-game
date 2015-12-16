/* SWEN20003 Object Oriented Software Development
 * RPG Game Engine
 * Author: Luther Carroll <lutherc> 
 */

import org.newdawn.slick.SlickException;

/** Elvira, the villager who heals the player.
*/
public class Elvira extends Villager
{
	/** Elvira's starting position. */
	private static final int ELVIRA_START_X = 972, ELVIRA_START_Y = 600;

	/** The path to Elvira's image. */
	private static final String image_path = "/units/shaman.png";

	/** Create Elvira. */
	public Elvira()
		throws SlickException
	{
		super(image_path, ELVIRA_START_X, ELVIRA_START_Y);
	}

	/** Update Elvira.
	 * Speaks and heals the player if the player is adjacent to Elvira.
	 * @param world the world.
	 * @param player the player.
 	 * @param delta Time passed since last frame (milliseconds).
	 */
	public void update(World world, Player player, double delta)
	{
		// Proform actions if the player is adjacent.
		if (player.inRange(this)){
			// If the player is hurt, heal them and speak appropriatly.
			if (player.isHurt()){
				player.heal();
				speak("You're looking much healthier now.", delta);
			}
			// If the player is unhurt, remind them to return for healing.
			else if (!this.is_speaking)
				speak("Return to me if you ever need healing.", delta);
		}
		// Pass to the superclass update method.
		updateVillager(delta);
	}

	/** Return a string identifying Elvira.
	 * @return a string, "Elvira";
	 */
	public String toString() {
		return "Elvira";
	}
}
