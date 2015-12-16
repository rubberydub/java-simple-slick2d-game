/* SWEN20003 Object Oriented Software Development
 * RPG Game Engine
 * Author: Luther Carroll <lutherc> 
 */

import org.newdawn.slick.SlickException;

/** The amulet, an item.
 */
public class Amulet extends Item
{
	/** The amulet's starting position. */
	private static final int AMULET_START_X = 540, AMULET_START_Y = 6720;

	/** The path to the amulet's image file. */
	private static final String IMAGE_PATH = "/items/amulet.png";

	/** Create a new amulet.
	 */
	public Amulet()
		throws SlickException
	{
		super(IMAGE_PATH, AMULET_START_X, AMULET_START_Y);
	}

	/** Return a string identifying the amulet.
	 * @return a string, "Amulet".
	 */
	public String toString() {
		return "Amulet";
	}
}
