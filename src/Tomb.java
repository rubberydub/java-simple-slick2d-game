/* SWEN20003 Object Oriented Software Development
 * RPG Game Engine
 * Author: Luther Carroll <lutherc> 
 */

import org.newdawn.slick.SlickException;

/** The tomb, an item.
 */
public class Tomb extends Item
{
	/** The tomb's starting position. */
	private static final int TOMB_START_X = 6650, TOMB_START_Y = 242;

	/** The path to the tomb's image file. */
	private static final String IMAGE_PATH = "/items/book.png";

	/** Create the tomb.
	 */
	public Tomb()
		throws SlickException
	{
		super(IMAGE_PATH, TOMB_START_X, TOMB_START_Y);
	}

	/** Return a string identifying the tomb.
	 * @return a string, "Tomb".
	 */
	public String toString() {
		return "Tomb";
	}
}
