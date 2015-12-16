/* SWEN20003 Object Oriented Software Development
 * RPG Game Engine
 * Author: Luther Carroll <lutherc> 
 */

import org.newdawn.slick.SlickException;

/** The Bandit, an aggressive enemy.
*/
public class Bandit extends AggressiveEnemy
{
	/** The path to the bandits image file. */
	private static final String IMAGE_PATH = "/units/bandit.png";

	/** Create a new bandit.
	 */
	public Bandit(double x, double y)
		throws SlickException
	{
		super(IMAGE_PATH, x, y);
	}

	/** Return a string identifying the bandit.
	 * @return a string, "Bandit".
	 */
	public String toString() {
		return "Bandit";
	}
}


