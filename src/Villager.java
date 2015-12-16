/* SWEN20003 Object Oriented Software Development
 * RPG Game Engine
 * Author: Luther Carroll <lutherc> 
 */

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Color;
import org.newdawn.slick.SlickException;

/** The abstract class all villagers inherit from.
*/
public class Villager extends Unit
{
	/** Stats. */
	public static final int SPEED = 0;
	public static final int MAX_HP = 100;
	public static final int MAX_DAMAGE = 0;
	public static final int COOLDOWN = 0;

	/** For dialoge functionality. */
	protected boolean is_speaking = false;
	protected String current_dialoge = null;
	private static int DIALOGE_TIME = 5000;
	private int dialoge_timer = DIALOGE_TIME;

	/** Create a new villager.
	 * @param image_path the path to the villager's image file.
	 * @param x the starting x position.
	 * @param y the starting y position.
	 */
	public Villager(String image_path, double x, double y)
		throws SlickException
	{
		super(image_path, x, y, SPEED, MAX_HP, MAX_DAMAGE, COOLDOWN);
	}

	/** Draw the player, overrides method in Unit.
 	 * @param g The Slick graphics object, used for drawing.
	 */
	public void render(Graphics g)
	{
		// If the unit is speaking, render the current dialoge.
		if (is_speaking){
			renderDialoge(g, current_dialoge);
		}
		super.render(g);
	}

	/** Declare this abstract method to prevent error.
	 * TODO not this!
	 */
	public void update(World world, Player player, double delta) {}

	/** Update the villager, adjust the dialoge timer.
 	 * @param delta Time passed since last frame (milliseconds).
	 */
	public void updateVillager(double delta) 
	{
		// If the villager has finished speaking, reset the timer.
		if (dialoge_timer <= 0) {
			dialoge_timer = DIALOGE_TIME;
			is_speaking = false;
		}
		// Keep speaking.
		else if (dialoge_timer <= DIALOGE_TIME)
			dialoge_timer -= delta;
	}

	/** Declare this abstract method to prevent error.
	 * TODO not this!
	 */
	public void attack(Unit unit, double delta) {}

	/** Declare this abstract method to prevent error.
	 * TODO not this!
	 */
	public void takeDamage(int damage) {}

	/** Pass the villager some dialog and begin the speaking timer.
	 * @param dialoge what to say.
 	 * @param delta Time passed since last frame (milliseconds).
	 */
	public void speak(String dialoge, double delta)
	{
		is_speaking = true;
		current_dialoge = dialoge;
		dialoge_timer -= delta;
	}

	/** Draw the dialoge.
	 * @param dialoge what to say.
 	 * @param g The Slick graphics object, used for drawing.
         */	
	public void renderDialoge(Graphics g, String dialoge)
	{
		Color VALUE = new Color(1.0f, 1.0f, 1.0f);          // White
		Color BAR_BG = new Color(0.0f, 0.0f, 0.0f, 0.8f);   // Black, transp

		int px, py;		    // Coordinates of panel (top left)
		int text_x, text_y;         // Coordinates to draw text
		int bar_x, bar_y;           // Coordinates to draw rectangles
		int bar_width, bar_height;  // Size of rectangle to draw

		px = (int) (x);
		py = (int) (y - 100);

		text_x = px - 30;
		text_y = py + 23;

		bar_x = px - 350;
		bar_y = py + 25;
		bar_width = 700;
		bar_height = 15;
		text_x = bar_x + (bar_width - g.getFont().getWidth(dialoge)) / 2;
		g.setColor(BAR_BG);
		g.fillRect(bar_x, bar_y, bar_width, bar_height);
		g.setColor(VALUE);
		g.drawString(dialoge, text_x, text_y);

	}
}


