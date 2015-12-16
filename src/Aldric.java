import org.newdawn.slick.SlickException;

/** Prince Aldric whom the player must return the elixer to, a villager.
*/
public class Aldric extends Villager
{
	/** Aldric's starting position. */
	private static final int ALDRIC_START_X = 540, ALDRIC_START_Y = 610;

	/** The path to Aldric's image file. */
	private static final String image_path = "/units/prince.png";

	/** Create Aldric.
	 */
	public Aldric()
		throws SlickException
	{
		super(image_path, ALDRIC_START_X, ALDRIC_START_Y);
	
	}

	/** Update Aldric, performs speech if in adjacent to the player.
	 * @param world the game world.
	 * @param player the player.
	 * @param delta Time passed since last frame (milliseconds).
	 */
	public void update(World world, Player player, double delta)
	{
		// If the player is adjacent to Aldric.
		if (player.inRange(this)){
			// If the player has the elixir.
			if (!player.hasItem("Elixir"))
				speak("Please seek out the Elixir of Life to cure the king.", delta);
			else
				speak("The elixir! My father is cured! Thankyou!", delta);
		}
		updateVillager(delta);
	}

	/** Return a string identifying Aldric.
	 * @return a string, "Aldric".
	 */
	public String toString() {
		return "Aldric";
	}
}
