/* SWEN20003 Object Oriented Software Development
 * RPG Game Engine
 * Author: Luther Carroll <lutherc> 
 */

import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.tiled.TiledMap;
import java.util.ArrayList;
import java.lang.Math;

/** Behold! The game world.
 */
public class World
{
	/** The items. */
	private Sword sword = null;
	private Tomb tomb = null;
	private Amulet amulet = null;
	private Elixir elixir = null;

	/** Lists for managing the items. */
	private ArrayList<Item> items = null; 
	private ArrayList<Item> picked_up = null;

	/** The villagers. */
	private Aldric aldric = null;
	private Garth garth = null;
	private Elvira elvira = null;

	/** The boss. */
	private Draelic draelic = null;

	/** The stating positions of the enemies.
	 * TODO declare and construct by iterating over lists.
	 */
	private static final int SKELETON_1_START_X = 6140, SKELETON_1_START_Y = 2080;
	private static final int BANDIT_1_START_X = 1253, BANDIT_1_START_Y = 1382;
	private static final int ZOMBIE_1_START_X = 736, ZOMBIE_1_START_Y = 2707;
	private static final int SKELETON_2_START_X = 2700, SKELETON_2_START_Y = 1455;
	private static final int BANDIT_2_START_X = 2075, BANDIT_2_START_Y = 6245;
	private static final int ZOMBIE_2_START_X = 3195, ZOMBIE_2_START_Y = 5455;
	private static final int SKELETON_3_START_X = 2350, SKELETON_3_START_Y = 830;
	private static final int BANDIT_3_START_X = 6595, BANDIT_3_START_Y = 390;
	private static final int ZOMBIE_3_START_X = 615, ZOMBIE_3_START_Y = 6370;
	private static final int SKELETON_4_START_X = 1680, SKELETON_4_START_Y = 3325;
	private static final int BANDIT_4_START_X = 6515, BANDIT_4_START_Y = 6575;
	private static final int ZOMBIE_4_START_X = 4770, ZOMBIE_4_START_Y = 6255;
	private static final int BAT_1_START_X = 1465, BAT_1_START_Y = 2340;
	private static final int BAT_2_START_X = 1563, BAT_2_START_Y = 4322;
	private static final int BAT_3_START_X = 2450, BAT_3_START_Y = 6455;
	private static final int BAT_4_START_X = 6165, BAT_4_START_Y = 5455;

	/** Declare the enemies. */
	private Skeleton skeleton_1 = null, skeleton_2 = null, skeleton_3 = null, skeleton_4 = null;
	private Bandit bandit_1 = null, bandit_2 = null, bandit_3 = null, bandit_4 = null;
	private Zombie zombie_1 = null, zombie_2 = null, zombie_3 = null, zombie_4 = null;
	private Bat bat_1 = null, bat_2 = null, bat_3 = null, bat_4 = null;

	/** The player. */
	private Player player = null;
	
	/** Lists for managing the units. */
	private ArrayList<Unit> units = null; 
	private ArrayList<Unit> to_remove = null;

	/** The map. */
	private TiledMap map = null;

	/** The camera. */
	private Camera camera = null;

	/** Map width, in pixels. */
	private int getMapWidth()
	{
		return map.getWidth() * getTileWidth();
	}

	/** Map height, in pixels. */
	private int getMapHeight()
	{
		return map.getHeight() * getTileHeight();
	}

	/** Tile width, in pixels. */
	private int getTileWidth()
	{
		return map.getTileWidth();
	}

	/** Tile height, in pixels. */
	private int getTileHeight()
	{
		return map.getTileHeight();
	}

	/** Create a new World object. */
	public World()
		throws SlickException
	{
		map = new TiledMap(RPG.ASSETS_PATH + "/map.tmx", RPG.ASSETS_PATH);

		// Construct the items and add them to the item list.
		items = new ArrayList<Item>(); 
		picked_up = new ArrayList<Item>();

		sword = new Sword();
		tomb = new Tomb();
		amulet = new Amulet();
		elixir = new Elixir();

		items.add(sword);
		items.add(tomb);
		items.add(amulet);	
		items.add(elixir);	

		// Construct the units and add them to the units list.
		units = new ArrayList<Unit>(); 
		to_remove = new ArrayList<Unit>();

		aldric = new Aldric();
		elvira = new Elvira();
		garth = new Garth();

		units.add(aldric);
		units.add(elvira);
		units.add(garth);

		draelic = new Draelic();

		units.add(draelic);

		skeleton_1 = new Skeleton(SKELETON_1_START_X, SKELETON_1_START_Y);
		skeleton_2 = new Skeleton(SKELETON_2_START_X, SKELETON_2_START_Y);
		skeleton_3 = new Skeleton(SKELETON_3_START_X, SKELETON_3_START_Y);
		skeleton_4 = new Skeleton(SKELETON_4_START_X, SKELETON_4_START_Y);
		bandit_1 = new Bandit(BANDIT_1_START_X, BANDIT_1_START_Y);
		bandit_2 = new Bandit(BANDIT_2_START_X, BANDIT_2_START_Y);
		bandit_3 = new Bandit(BANDIT_3_START_X, BANDIT_3_START_Y);
		bandit_4 = new Bandit(BANDIT_4_START_X, BANDIT_4_START_Y);
		zombie_1 = new Zombie(ZOMBIE_1_START_X, ZOMBIE_1_START_Y);
		zombie_2 = new Zombie(ZOMBIE_2_START_X, ZOMBIE_2_START_Y);
		zombie_3 = new Zombie(ZOMBIE_3_START_X, ZOMBIE_3_START_Y);
		zombie_4 = new Zombie(ZOMBIE_4_START_X, ZOMBIE_4_START_Y);
		bat_1 = new Bat(BAT_1_START_X, BAT_1_START_Y);
		bat_2 = new Bat(BAT_2_START_X, BAT_2_START_Y);
		bat_3 = new Bat(BAT_3_START_X, BAT_3_START_Y);
		bat_4 = new Bat(BAT_4_START_X, BAT_4_START_Y);

		units.add(skeleton_1);
		units.add(skeleton_2);
		units.add(skeleton_3);
		units.add(skeleton_4);
		units.add(bat_1);
		units.add(bat_2);
		units.add(bat_3);
		units.add(bat_4);
		units.add(bandit_1);
		units.add(bandit_2);
		units.add(bandit_3);
		units.add(bandit_4);
		units.add(zombie_1);
		units.add(zombie_2);
		units.add(zombie_3);
		units.add(zombie_4);

		player = new Player();
		units.add(player);

		camera = new Camera(player, RPG.SCREEN_WIDTH, RPG.SCREEN_HEIGHT);
	}

	/** Update the game state for a frame.
	 * @param dir_x The player's movement in the x axis (-1, 0 or 1).
	 * @param dir_y The player's movement in the y axis (-1, 0 or 1).
	 * @param attacking true if the player is pressing A to attack.
	 * @param delta Time passed since last frame (milliseconds).
	 */
	public void update(int dir_x, int dir_y, boolean attacking, int delta)
		throws SlickException
	{
		// If the player is attacking, pass to playerCombat to manage combat.
		if (attacking)
			playerCombat(delta);

		// Pass to enemyCombat to manage combat.
	        enemyCombat(delta);

		// Item management:
		for(Item item : items) {
			// If an item has been picked up, add it to the picked_up list.
			if (item.isPickedUp())
				picked_up.add(item);
			// Else update the item to see if it is being picked up this frame.
			else
				item.update(this, player);
		}

		// If an item has been picked up, remove it from the items list.
		for(Item item : picked_up) {
			items.remove(item);
		}

		// Update the player.
		player.updatePlayer(this, dir_x, dir_y, delta);

		// Unit management.
		for(Unit unit : units) {
			// If a unit is dead, add it to the to_remove list.
			if (unit.isDead())
				to_remove.add(unit);
			// Else update the unit (but not the player! already updated).
			else if (!(unit.equals(player)))
				unit.update(this, player, delta);
		}

		// Remove the dead units.
		for(Unit unit : to_remove) {
			units.remove(unit);
		}

		// Update the camera.
		camera.update();
	}

	/** Render the entire screen, so it reflects the current game state.
	 * @param g The Slick graphics object, used for drawing.
	 */
	public void render(Graphics g)
		throws SlickException
	{
		// Variables for rendering the correct section of the map.
		int x = -(camera.getMinX() % getTileWidth());
		int y = -(camera.getMinY() % getTileHeight());
		int sx = camera.getMinX() / getTileWidth();
		int sy = camera.getMinY()/ getTileHeight();
		int w = (camera.getMaxX() / getTileWidth()) - (camera.getMinX() / getTileWidth()) + 1;
		int h = (camera.getMaxY() / getTileHeight()) - (camera.getMinY() / getTileHeight()) + 1;
		map.render(x, y, sx, sy, w, h);

		// Translate the Graphics object
		g.translate(-camera.getMinX(), -camera.getMinY());

		// Render the items.
		for(Item item : items) {
			item.render(g);
		}

		// Render the units.
		for(Unit unit : units) {
			unit.render(g);
		}
	}

	/** Determines whether a particular map coordinate blocks movement.
	 * @param mover the unit that is attepting to move.
	 * @param x Map x coordinate (in pixels).
	 * @param y Map y coordinate (in pixels).
	 * @return true if the coordinate blocks movement.
	 */
	public boolean blocked(Unit mover, double x, double y) {
		// Check we are within the bounds of the map
		if (x < 0 || y < 0 || x > getMapWidth() || y > getMapHeight()) {
			return true;
		}
		
		// Check if the mover is not moving into another unit.
		for(Unit unit : units) {
			// Exclude the mover from the loop.
		        if (!unit.equals(mover))
				// Custom distances for aesthetics and for coordinate-wise checking.
				if ((Math.abs(x - unit.getX()) < (unit.width/3))
				&& ((Math.abs(y - unit.getY())) < (unit.height/3))) {
					return true;
				}
		}

		// Check the tile properties
		int tile_x = (int) x / getTileWidth();
		int tile_y = (int) y / getTileHeight();
		int tileid = map.getTileId(tile_x, tile_y, 0);
		String block = map.getTileProperty(tileid, "block", "0");
		return !block.equals("0");
	}

	/** Manages when the player attacks.
	 * @param delta Time passed since last frame (milliseconds).
	 */
	private void playerCombat(double delta)
	{
		// Bool for flagging if the player has successfully attacked a unit.
		// Required to enable attacking multiple enemies.
		boolean attacked = false;

		// Iterate through all units.
		for(Unit unit : units)
		{
			// Exclude the player themselves and all villagers.
			if (!(unit instanceof Player) && !(unit instanceof Villager)) {
				if (player.inRange(unit)) {
					player.attack(unit, delta);
					attacked = true;
				}
			}
		}
		// If the player attacked, pass to attacked method to begin cooldown.
		if (attacked)
			player.attacked(delta);
	}

	/** Manages when enemies attack.
	 * @param delta Time passed since last frame (milliseconds).
	 */
	private void enemyCombat(double delta)
	{
		// Iterate through all units.
		for(Unit unit : units) {
			// Exclude the player themselves, passive enemies and all villagers.
			if (!(unit instanceof Player) && !(unit instanceof Villager) 
			&& !(unit instanceof PassiveEnemy)) {
				if (unit.inRange(player))
					unit.attack(player, delta);
			}
		}
	}
}
