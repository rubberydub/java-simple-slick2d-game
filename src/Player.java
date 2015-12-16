/* 433-294 Object Oriented Software Development
 * RPG Game Engine
 * Sample Solution
 * Author: Matt Giuca <mgiuca>
 */

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Color;
import java.util.ArrayList;
import java.util.Random;

/** The character which the user plays as.
*/
public class Player extends Unit
{
	/** The player's starting position. */
	private static final int PLAYER_START_X = 738, PLAYER_START_Y = 549;

	/** Stats. */
	public static final int MAX_HP = 100;
	public static final int MAX_DAMAGE = 20;
	public static final int COOLDOWN = 1000;
	private static final double SPEED = 0.5;

	/** The player's inventory. */

	private ArrayList<Item> inventory = new ArrayList<Item>();
	/** An image for the player's status panel. */
	public Image panel = new Image(RPG.ASSETS_PATH + "/panel.png");

	/** The path to the player's image file. */
	private static final String image_path = "/units/player.png";

	/** Create the player.
	 */
	public Player()
		throws SlickException
	{
		super(image_path, PLAYER_START_X, PLAYER_START_Y, SPEED, MAX_HP, MAX_DAMAGE, COOLDOWN);
	}

	/** Draw the player.
 	 * @param g The Slick graphics object, used for drawing.
	 */
	@Override
	public void render(Graphics g){
		renderPanel(g);
		super.render(g);
	}

	/** Declare this abstract method to prevent error.
	 * TODO not this!
	 */
	public void update(World world, Player player, double delta) {}

	/** Update the player.
	 * @param world the game world.
	 * @param dir_x the x direction the player is moving in.
	 * @param dir_y the y direction the player is moving in.
 	 * @param delta Time passed since last frame (milliseconds).
	 */
	public void updatePlayer(World world, double dir_x, double dir_y, double delta) {
		// If the player is dead then respawn.
		if (isDead()) {
			respawn();
		}
		move(world, dir_x, dir_y, delta);
		cooldown(delta);
	}
	
	/** Take damage.
	 * @param damage the amount of damage to take.
	 */
	public void takeDamage(int damage)
	{
		current_hp -= damage;
	}
	  
	/** Check if the player is hurt.
	 * @return bool, if the player is below max hp.
	 */
	public boolean isHurt()
	{
		return (current_hp < MAX_HP);
	}

	/** Restore the player to max hp.
	 */
	public void heal()
	{
		current_hp = MAX_HP;
	}

	/** Relocate the player to the starting position and heal them.
	 */
	private void respawn() {
		heal();
		x = PLAYER_START_X;
		y = PLAYER_START_Y;
	}
	
	/** Place an item in the player's inventory and modify stats.
	 * @param item the item to be picked up.
	 */
	public void pickUp(Item item)
	{
		// Add the item to the inventory.
		inventory.add(item);

		// If the sword has been picked up, increase damage.
		if ("Sword".equals(item.toString())) {
			max_damage += 10;
		}

		// If the tomb has been picked up, decrease cooldown.
		if ("Tomb".equals(item.toString())) {
			current_cooldown -= 300;
			max_cooldown -= 300;
		}

		// If the amulet has been picked up, increase health.
		if ("Amulet".equals(item.toString())){
			max_hp += 50;
		}
	}

	/** Check if the player is holding an item.
	 * @param String the name of item being checked if the player is holding.
	 * @return bool, true if the player is holding the parameter.
	 */
	public boolean hasItem(String item_name)
	{
		for(Item item : inventory) 
		{
			if (item_name.equals(item.toString()))
				return true;
		}
		return false;
	}
	
	/** Called if the player has succesfully attacked.
	 * Enables multiple attacks at once.
 	 * @param delta Time passed since last frame (milliseconds).
	 */
	public void attacked(double delta)
	{
		current_cooldown -= delta;
	}
		
	/** Check if the player is allowed to attack.
	 * Does not trigger cooldown.
	 * Enables multiple attacks at once.
 	 * @param delta Time passed since last frame (milliseconds).
	 * @return bool, if the player may attack.
	 */
	@Override
	public boolean checkCooldown(double delta)
	{
		// If the player is not cooling down, allow the attack.
		if (current_cooldown == max_cooldown) {
			return true;
		}
		// Else deny the attack.
		else
			return false;
	}

	/** Renders the player's status panel.
	 * @param g The current Slick graphics context.
	 */
	public void renderPanel(Graphics g)
	{
		// Panel colours
		Color LABEL = new Color(0.9f, 0.9f, 0.4f);          // Gold
		Color VALUE = new Color(1.0f, 1.0f, 1.0f);          // White
		Color BAR_BG = new Color(0.0f, 0.0f, 0.0f, 0.8f);   // Black, transp
		Color BAR = new Color(0.8f, 0.0f, 0.0f, 0.8f);      // Red, transp

		// Variables for layout
		int px, py;		    // Coordinates of panel (top left)
		String text;                // Text to display
		int text_x, text_y;         // Coordinates to draw text
		int bar_x, bar_y;           // Coordinates to draw rectangles
		int bar_width, bar_height;  // Size of rectangle to draw
		int hp_bar_width;           // Size of red (HP) rectangle
		int inv_x, inv_y;           // Coordinates to draw inventory item

		float health_percent;       // Player's health, as a percentage

		px = (int) (x - RPG.SCREEN_WIDTH/2);
		py = (int) (y + RPG.SCREEN_HEIGHT/2 - RPG.PANELHEIGHT);

		// Panel background image
		panel.draw(px, py);

		// Display the player's health
		text_x = px + 15;
		text_y = py + 25;
		g.setColor(LABEL);
		g.drawString("Health:", text_x, text_y);
		text = Integer.toString(current_hp) + "/" + Integer.toString(max_hp);

		bar_x = px + 90;
		bar_y = py + 20;
		bar_width = 90;
		bar_height = 30;
		health_percent = (float) current_hp / (float) max_hp; 
		hp_bar_width = (int) (bar_width * health_percent);
		text_x = bar_x + (bar_width - g.getFont().getWidth(text)) / 2;
		g.setColor(BAR_BG);
		g.fillRect(bar_x, bar_y, bar_width, bar_height);
		g.setColor(BAR);
		g.fillRect(bar_x, bar_y, hp_bar_width, bar_height);
		g.setColor(VALUE);
		g.drawString(text, text_x, text_y);

		// Display the player's damage and cooldown
		text_x = px + 200;
		g.setColor(LABEL);
		g.drawString("Damage:", text_x, text_y);
		text_x += 80;
		text = Integer.toString(max_damage); 
		g.setColor(VALUE);
		g.drawString(text, text_x, text_y);
		text_x += 40;
		g.setColor(LABEL);
		g.drawString("Rate:", text_x, text_y);
		text_x += 55;
		text = Integer.toString(max_cooldown); 
		g.setColor(VALUE);
		g.drawString(text, text_x, text_y);

		// Display the player's inventory
		g.setColor(LABEL);
		g.drawString("Items:", px + 420, text_y);
		bar_x = px + 490;
		bar_y = py + 10;
		bar_width = 288;
		bar_height = bar_height + 20;
		g.setColor(BAR_BG);
		g.fillRect(bar_x, bar_y, bar_width, bar_height);

		inv_x = px + 515;
		inv_y = py + 35;

		for(Item item : inventory)
		{
			// Render the item to (inv_x, inv_y)
			item.setX(inv_x);
			item.setY(inv_y);
			item.render(g);
			inv_x += 72;
		}
	}
}
