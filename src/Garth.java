/* SWEN20003 Object Oriented Software Development
 * RPG Game Engine
 * Author: Luther Carroll <lutherc> 
 */

import org.newdawn.slick.SlickException;

/** Garth, a villager, gives the player advice on where to find the items. 
*/
public class Garth extends Villager
{
	/** Garth's starting position. */
	private static final int GARTH_START_X = 830, GARTH_START_Y = 820;

	/** The path to Garth's image. */
	private static final String image_path = "/units/peasant.png";

	/** Create Garth.
	 */
	public Garth()
		throws SlickException
	{
		super(image_path, GARTH_START_X, GARTH_START_Y);
	}

	/** Update Garth, proform speech if the player is adjacent.
	 * @param world the game world.
	 * @param player the player.
 	 * @param g The Slick graphics object, used for drawing.
	 */
	public void update(World world, Player player, double delta)
	{
		// If the player is in range, speak.
		if (player.inRange(this)){
			// If the player does not have the amulet, give them a tip.
			if (!player.hasItem("Amulet"))
		        	speak("Find the Amulet of Vitality, across the river to the west.", delta);
			// If the player does not have the sword, give them a tip.
			else if (!player.hasItem("Sword"))
		                speak("Find the Sword of Strength - cross the river and back, on the east side.", delta);
			// etc. for the tomb.
			else if (!player.hasItem("Tomb"))
			        speak("Find the Tome of Agility, in the Land of Shadows.", delta);
			// Hence the player has all the items (except perhaps the elixer).
        		else
			        speak("You have found all the treasure I know of.", delta);
		}
		updateVillager(delta);
	}

	/** Return a string identifying Garth.
	 * @return a string, "Garth".
	 */
	public String toString() {
		return "Garth";
	}
}
