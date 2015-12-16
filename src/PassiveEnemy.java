/* SWEN20003 Object Oriented Software Development
 * RPG Game Engine
 * Author: Luther Carroll <lutherc> 
 */

import org.newdawn.slick.SlickException;
import java.util.Random;

/** The base class for passive enemies..
*/
public class PassiveEnemy extends Unit
{
	/** Stats, identical for all passive enemies. */
	public static final int MAX_HP = 100;
	public static final int MAX_DAMAGE = 0;
	public static final int COOLDOWN = 1000;
	public static final double SPEED = 0.25;

	/** Variables for wandering functionality */
	private static final double WANDER_TIME = 3000;
	private double wander_timer = WANDER_TIME;
	Random rand;
	int rand_int_x;
	int rand_int_y;

	/** Variables for retreating functionality. */
	private static final double RETREAT_TIME = 5000;
 	private double retreat_timer = 0;

	/** Create a new passive enemy object.
	 * @param image_path the path to the enemy's image.
	 * @param x the enemy's starting x position.
	 * @param y the enemy's starting y position.
	 */
	public PassiveEnemy(String image_path, double x, double y)
		throws SlickException
	{
		super(image_path, x, y, SPEED, MAX_HP, MAX_DAMAGE, COOLDOWN);
	}

	/** Update the enemy, choose behaviour from retreat timer.
	 * @param world the game world.
	 * @param player the player.
 	 * @param delta Time passed since last frame (milliseconds).
	 */
	public void update(World world, Player player, double delta)
	{
		// If the unit is not retreating, wander.
		if (retreat_timer <= 0)
			wander(world, delta);
		// If the unit is retreating, continue retreating.
		else
			retreat(world, player, delta);
	}

	/** Take damage, overrides method in Unit.
	 * @param damage the amount of damage to take.
	 */
	@Override
	public void takeDamage(int damage)
	{
		// Take damage and begin retreating.
		retreat_timer = RETREAT_TIME;
		current_hp -= damage;
	}

	/** Calculate random movement.
	 * @param world the game world.
 	 * @param delta Time passed since last frame (milliseconds).
	 */
	public void wander(World world, double delta)
	{
		// For calculating random directions.
		Random rand = new Random();	

		// If it is time to select a new direction to wander in.
		if (wander_timer <= 0){
			// Select some random directions.
			// -1, 0 or 1 ie backwards, no movement or forwards.
			rand_int_x = rand.nextInt(3) - 1;
			rand_int_y = rand.nextInt(3) - 1;
			// Reset the wander_timer.
			wander_timer = WANDER_TIME;
		}

		// Deincrement the timer.
		wander_timer -= delta;

		this.move(world, rand_int_x, rand_int_y, delta);

	}

	/** Retreat away from the player
	 * @param world the game world.
	 * @param player the player.
 	 * @param delta Time passed since last frame (milliseconds).
	 */
	public void retreat(World world, Player player, double delta)
	{
		// Calculate the unit vectors pointing away from the player.
		double norm = range(player);
		double ux = (this.x - player.getX())/norm;
		double uy = (this.y - player.getY())/norm;

		// Deincrement the timer.
		retreat_timer -= delta;

		this.move(world, ux, uy, delta);
	}
}

