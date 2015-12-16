/* SWEN20003 Object Oriented Software Development
 * RPG Game Engine
 * Author: Luther Carroll <lutherc> 
 */

import org.newdawn.slick.SlickException;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Color;
import java.util.Random;

/** The abstract class that all units inherit.
 */
public abstract class Unit extends GameObject 
{
	/** Stats. */
	protected int max_hp, max_damage, max_cooldown;
	protected int current_hp, current_cooldown;
	protected double speed;

	/** Create a new Unit.
	 * @param image_path the path to the units image.
	 * @param x the starting x position.
	 * @param y the starting y position.
	 * @param speed the unit's speed.
	 * @param max_hp the units maximum hit points.
	 * @param max_damage the units damage cap.
	 * @param max_cooldown the units cooldown time.
	 */
	public Unit(String image_path, double x, double y, double speed, int max_hp, int max_damage, int max_cooldown)
		throws SlickException
	{
		super(image_path, x, y);
		this.speed = speed;
		this.max_hp = max_hp;
		this.max_damage = max_damage;
		this.max_cooldown = max_cooldown;

		// Set the current stats to their maximum on construction.
		current_hp = max_hp;
		current_cooldown = max_cooldown;
	}

	/** Abstract update method that all units require.
	 * @param world the game world.
	 * @param player all units interact with the player.
 	 * @param delta Time passed since last frame (milliseconds).
	 */
	abstract void update(World world, Player player, double delta);

	/** Render the unit, extends method in GameObject.
 	 * @param g The Slick graphics object, used for drawing.
	 */
	@Override
	public void render(Graphics g)
	{
		super.render(g);

		// Do not render the health bar if this unit is the player.
		if (!(this instanceof Player)){
			renderHealthBar(g);
		}
	}

	/** Move the unit to a new position.
	 * @param world the game world.
	 * @param dir_x the direction in x to translate the unit. 
	 * @param dir_y the direction in y to translate the unit.
 	 * @param delta Time passed since last frame (milliseconds).
	 */
	public void move(World world, double dir_x, double dir_y, double delta)
	{
		// Change the facing depending on the change in x.
		// Non-ternary logic for an excluded middle if no change in x.
		if (dir_x < 0)
			face_left = true;
		else if (dir_x > 0)
			face_left = false;

		// Calculate the new coordinates for the unit by adding the 
		// 	product of the movement direction, delta and the 
		// 	units speed to the current position.
		double new_x = this.x + (dir_x * delta * this.speed);
		double new_y = this.y + (dir_y * delta * this.speed);

		// Check if the new coordinates are at a blocked location.
		// Must check at minimum and maximum x ie not at center.
		double x_sign = Math.signum(dir_x);
		// If x movement is valid and top of unit is not blocked.
		if(!world.blocked(this, new_x + x_sign * this.width / 4, this.y + this.height / 4) 
		// If x movement is valid and bottom of unit is not blocked.
		&& !world.blocked(this, new_x + x_sign * this.width / 4, this.y - this.height / 4)) 
		{
			this.x = new_x;
		}

		// ...And similar for y movement.
		double y_sign = Math.signum(dir_y);
		if(!world.blocked(this, this.x + this.width / 4, new_y + y_sign * this.height / 4) 
		&& !world.blocked(this, this.x - this.width / 4, new_y + y_sign * this.height / 4))
		{
			this.y = new_y;
		}
	}

	/** Abstract method for taking damage.
	 * @param damage the amount of damage recieved.
	 */
	abstract void takeDamage(int damage);

	/** Check if the unit is dead ie has no hit points.
	 * @return true if the unit is dead.
	 */
	public boolean isDead() {
		return (current_hp <= 0);
	}

	/** Attack another unit.
	 * @param unit the other unit this unit is attacking.
 	 * @param delta Time passed since last frame (milliseconds).
	 */
	public void attack(Unit unit, double delta)
	{
		// Allow the attack if the unit is not cooling down.
		if (checkCooldown(delta)) {
			// Calculate a random amount of damage up to damage cap.
			Random rand = new Random();	
			int damage = rand.nextInt(max_damage);

			// Deal that damage to the other unit.
			unit.takeDamage(damage);
		}
	}

	/** Check if the unit is allowed to attack.
 	 * @param delta Time passed since last frame (milliseconds).
	 * @return true if the unit can attack
	 */
	public boolean checkCooldown(double delta)
	{
		// If the unit is not cooling down ie at cooldown cap.
		if (current_cooldown == max_cooldown) {
			// Begin the cooldown.
			current_cooldown -= delta;
			// Return true to allow an attack.
			return true;
		}
		// If the unit is cooling down deny the attack.
		else
			return false;
	}

	/** Deincrement the cooldown when the Unit is updated. 
 	 * @param delta Time passed since last frame (milliseconds).
	 */
	public void cooldown(double delta)
	{
		// If the unit is cooling down deincrement the timer.
		if (current_cooldown < max_cooldown) {
			current_cooldown -= delta;
		}

		// If the unit has finished cooling down reset the timer.
		if (current_cooldown <= 0) {
			current_cooldown = max_cooldown;
		}
	}

	/** Render the units health bar.
	 * TODO create seperate class inheriting from GameObject.
 	 * @param g The Slick graphics object, used for drawing.
	 */
	public void renderHealthBar(Graphics g)
	{
		Color VALUE = new Color(1.0f, 1.0f, 1.0f);          // White
		Color BAR_BG = new Color(0.0f, 0.0f, 0.0f, 0.8f);   // Black, transp
		Color BAR = new Color(0.8f, 0.0f, 0.0f, 0.8f);      // Red, transp

		int px, py;		    // Coordinates of health bar (top, middle)
		String text;                // Text to display
		int text_x, text_y;         // Coordinates to draw text
		int bar_x, bar_y;           // Coordinates to draw rectangles
		int bar_width, bar_height;  // Size of rectangle to draw
		int hp_bar_width;           // Size of red (HP) rectangle

		float health_percent;       // Player's health, as a percentage

		// Coordinates of the bar based on the units position.
		px = (int) (x);
		py = (int) (y - 80);

		text_x = px - 30;
		text_y = py + 23;
		text = this.toString();

		bar_x = px - 40;
		bar_y = py + 25;
		bar_width = 80;
		bar_height = 15;
		health_percent = (float) current_hp / (float) max_hp;
		hp_bar_width = (int) (bar_width * health_percent);
		text_x = bar_x + (bar_width - g.getFont().getWidth(text)) / 2;

		g.setColor(BAR_BG);
		g.fillRect(bar_x, bar_y, bar_width, bar_height);
		g.setColor(BAR);
		g.fillRect(bar_x, bar_y, hp_bar_width, bar_height);
		g.setColor(VALUE);
		g.drawString(text, text_x, text_y);

	}
}

