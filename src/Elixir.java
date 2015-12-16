/* SWEN20003 Object Oriented Software Development
 * RPG Game Engine
 * Author: Luther Carroll <lutherc> 
 */

import org.newdawn.slick.SlickException;

/** The elixer of life, an item.
 */
public class Elixir extends Item
{
	/** The exlixer's starting position. */
	private static final int ELIXIR_START_X = 1975, ELIXIR_START_Y = 415;

	/** The path to the elixer's image. */
	private static final String IMAGE_PATH = "/items/elixir.png";

	/** Create the elixer. */
	public Elixir()
		throws SlickException
	{
		super(IMAGE_PATH, ELIXIR_START_X, ELIXIR_START_Y);
	}

	/** Return a string identifying the elixer.
	 * @return a string, "Elixir".
	 */
	public String toString() {
		return "Elixir";
	}
}
