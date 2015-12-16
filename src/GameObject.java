/* SWEN20003 Object Oriented Software Development
 * RPG Game Engine
 * Author: Luther Carroll <lutherc> 
 */

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Graphics;

/** The abstract class that all items and units inherit from.
 */
public abstract class GameObject
{
	/** Images. */
	protected Image img = null;
	protected Image img_flipped = null;
	protected boolean face_left = false;

	/** In pixels. */
	protected double x, y;
	protected double width, height;

	/** The metric of adjacentcy between 2 objects. */
	private static final int RANGE = 50;

	/** Get the x position.
	 * @return double, x position.
	 */
	public double getX()
	{
		return x;
	}

	/** Get the y position.
	 * @return double, y position.
	 */
	public double getY()
	{
		return y;
	}

	/** Create a new GameObject.
	 * @param image_path the path to the object's image.
	 * @param x the starting x position.
	 * @param y the starting y position.
	 */
	public GameObject(String image_path, double x, double y)
		throws SlickException
	{
		img = new Image(RPG.ASSETS_PATH + image_path);
		img_flipped = img.getFlippedCopy(true, false);
		this.x = x;
		this.y = y;
		this.width = img.getWidth();
		this.height = img.getHeight();
	}

	/** Draw the object to the graphics object.
 	 * @param g The Slick graphics object, used for drawing.
	 */
	public void render(Graphics g)
	{
		// Check if the object must be drawn facing left.
		Image which_img;
		which_img = this.face_left ? this.img_flipped : this.img;

		// Draw.
		which_img.drawCentered((int) x, (int) y);
	}

	/** Calculate the distance between this object and another object.
	 * @param object the other object, who distance to from this object is calculated.
	 * @return double, the distance between this object and the parameter.
	 */
	public double range(GameObject object)
	{
		// Euclidean norm.
		return Math.sqrt(Math.pow(this.x - object.getX(), 2) + Math.pow(this.y - object.getY(), 2));
	}

	/** Check if another object is adjacent to this object.
	 * @param object the other object, check if adjacent to this object.
	 * @return boolean, if this is in range of the parameter.
	 */
	public boolean inRange(GameObject object)
	{
		return (range(object) <= RANGE);
	}
}
