/* SWEN20003 Object Oriented Software Development
 * RPG Game Engine
 * Author: Luther Carroll <lutherc> 
 */

import org.newdawn.slick.SlickException;

/** The bat, a passive enemy.
*/
public class Bat extends PassiveEnemy {
	/** The path to the bat's image file. */
	private static final String image_path = "/units/dreadbat.png";

	/** Create a new bat. */
	public Bat(double x, double y)
		throws SlickException
	{
		super(image_path, x, y);
	}

	/** Return a string identifying the bat.
	 * @return a string, "Bat".
	 */
	public String toString() {
		return "Bat";
	}
}


