/* SWEN20003 Object Oriented Software Development
 * RPG Game Engine
 * Author: Luther Carroll <lutherc> 
 */

import org.newdawn.slick.SlickException;

/** The Zombie, an aggressive enemy.
*/
public class Zombie extends AggressiveEnemy
{
	/** The path to the zombie's image. */
	private static final String image_path = "/units/zombie.png";

	/** Create a new zombie.
	 * @param x the starting x position.
	 * @param y the starting y position.
	 */
	public Zombie(double x, double y)
		throws SlickException
	{
		super(image_path, x, y);
	}

	/** Return a string identifying the zombie.
	 * @return a string, "Zombie".
	 */
	public String toString() {
		return "Zombie";
	}
}


