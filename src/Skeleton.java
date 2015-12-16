/* SWEN20003 Object Oriented Software Development
 * RPG Game Engine
 * Author: Luther Carroll <lutherc> 
 */

import org.newdawn.slick.SlickException;

/** The skeleton, an aggressive enemy.
*/
public class Skeleton extends AggressiveEnemy
{
	/** The path to the skeleton's image file. */
	private static final String image_path = "/units/skeleton.png";

	/** Create a new skeleton.
	 */
	public Skeleton(double x, double y)
		throws SlickException
	{
		super(image_path, x, y);
	}

	/** Return a string identifying the skeleton.
	 * @return a string, "Skeleton".
	 */
	public String toString() {
		return "Skeleton";
	}
}


