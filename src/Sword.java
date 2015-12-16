/* SWEN20003 Object Oriented Software Development
 * RPG Game Engine
 * Author: Luther Carroll <lutherc> 
 */

import org.newdawn.slick.SlickException;

/** The sword, an item.
 */
public class Sword extends Item
{
	/** The sword's starting position. */
	private static final int SWORD_START_X = 6665, SWORD_START_Y = 6725;

	/** The path to the sword's image. */
	private static final String IMAGE_PATH = "/items/sword.png";

	/** Create a new sword.
	 */
	public Sword()
		throws SlickException
	{
		super(IMAGE_PATH, SWORD_START_X, SWORD_START_Y);
	}

	/** Return a string identifying the sword.
	 * @return a string, "Sword.
	 */
	public String toString() {
		return "Sword";
	}
}
