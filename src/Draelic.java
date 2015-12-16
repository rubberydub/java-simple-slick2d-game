/* SWEN20003 Object Oriented Software Development
 * RPG Game Engine
 * Author: Luther Carroll <lutherc> 
 */

import org.newdawn.slick.SlickException;

/** Draelic, the final boss, an aggressive enemy.
 */
public class Draelic extends AggressiveEnemy
{
	/** Draelic's stating position. */
	private static final int DRAELIC_START_X = 2060, DRAELIC_START_Y = 466;

	/** The path to Draelic's image. */
	private static final String IMAGE_PATH = "/units/necromancer.png";

	/** Create Draelic.
	 */
	public Draelic()
		throws SlickException
	{
		super(IMAGE_PATH, DRAELIC_START_X, DRAELIC_START_Y);
	}

	/** Return a string identifying Draelic.
	 * @return a string, "Draelic";
	 */
	public String toString() {
		return "Draelic";
	}
}
