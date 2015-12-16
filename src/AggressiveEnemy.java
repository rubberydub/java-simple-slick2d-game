/* SWEN20003 Object Oriented Software Development
 * RPG Game Engine
 * Author: Luther Carroll <lutherc> 
 */

import org.newdawn.slick.SlickException;

/** The class all aggressive enemies inherit from.
 */
public class AggressiveEnemy extends Unit
{
	/** Stats. */
	public static final int MAX_HP = 50;
	public static final int MAX_DAMAGE = 20;
	public static final int MAX_COOLDOWN = 1000;
	public static final double SPEED = 0.5;
	private static final int ATTACK_DISTANCE = 150;

	/** Create a new aggressive enemy object.
	 * @param image_path the path to the enemies image.
	 * @param x the enemies starting x position.
	 * @param y the enemies starting y position.
	 */
	public AggressiveEnemy(String image_path, double x, double y)
		throws SlickException
	{
		super(image_path, x, y, SPEED, MAX_HP, MAX_DAMAGE, MAX_COOLDOWN);
	}

	/** Update the enemy.
	 * @param world the game world.
	 * @param player the player.
	 * @param delta Time passed since last frame (milliseconds).
	 */
	public void update(World world, Player player, double delta) 
	{
		move(world, player, delta);
		cooldown(delta);
	}

	/** Take damage.
	 * @param damage the amount of damage to take.
	 */
	public void takeDamage(int damage)
	{
		current_hp -= damage;
	}

	/** Move the enemy.
	 * @param world the game world.
	 * @param player the player.
	 * @param delta Time passed since last frame (milliseconds).
	 */
	public void move(World world, Player player, double delta)
	{
		// Calculate the unit vectors towards the player.	
		double norm = range(player);
		double ux = (this.x - player.getX())/norm;
		double uy = (this.y - player.getY())/norm;

		// If the player is within the attack distance, attack.
		if (norm < ATTACK_DISTANCE)
			move(world, -ux, -uy, delta);
	}	
}

