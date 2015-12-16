/* SWEN20003 Object Oriented Software Development
 * RPG Game Engine
 * Author: Luther Carroll <lutherc> 
 */

import org.newdawn.slick.SlickException;

/** The abstract class that all items inherit from.
 */
public abstract class Item extends GameObject
{
	/** For checking if the item has been picked up by the player. */
	private boolean picked_up = false;

	/** Setter for x position.
	 */
	public void setX(int x)
       	{
		this.x = x;
	}

	/** Setter for y position.
	 */
	public void setY(int y)
       	{
		this.y = y;
	}

	/** Check if the item has been picked up by the player.
	 * @return bool, if parameter is picked up.
	 */
	public boolean isPickedUp()
	{
		return picked_up;
	}

	/** Create a new item object.
	 * @param image_path the path to the items image.
	 * @param x the stating x position.
	 * @param y the starting y position.
	 */
	public Item(String image_path, double x, double y)
		throws SlickException
	{
		super(image_path, x, y);
	}

	/** Update the object, check if the player has picked it up.
	 * @param world the game world.
	 * @param player the player.
	 */
	public void update(World world, Player player)
       	{
		// If the player is adjacent to the item, pick it up.
		if (player.inRange(this)){
			picked_up = true;
			// Pass the object to the player to add to the inventory.
			player.pickUp(this);
		}
	}
}
