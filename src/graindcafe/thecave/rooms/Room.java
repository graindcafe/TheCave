package graindcafe.thecave.rooms;

import graindcafe.thecave.creatures.Creature;
import graindcafe.thecave.plugin.Dungeon;
import graindcafe.thecave.plugin.TheCave;

import java.util.Map;

import org.bukkit.Location;

// TODO: Auto-generated Javadoc
/**
 * The Class Room.
 */
abstract public class Room implements Runnable {

	/** The plugin. */
	protected TheCave plugin;

	/** The dungeon. */
	protected Dungeon dungeon;

	/** The loc. */
	protected Location loc;

	/**
	 * Instantiates a new room.
	 * 
	 * @param plugin
	 *            the plugin
	 * @param dungeon
	 *            the dungeon
	 * @param location
	 *            the location
	 */
	protected Room(TheCave plugin, Dungeon dungeon, Location location) {
		this.plugin = plugin;
		this.dungeon = dungeon;
		this.loc = location;
	}

	/** The owned creatures. */
	Map<Class<? extends Creature>, Integer> ownedCreatures;

	/**
	 * Can host this creature
	 * 
	 * @param c
	 *            the creature
	 * @return true, if it can host this creature
	 */
	abstract public boolean canHost(Class<? extends Creature> c);

	/**
	 * Host the creature in this room
	 * 
	 * @param c
	 *            the creature
	 */
	abstract public void host(Creature c);

	/**
	 * Remove a creature from this room
	 * 
	 * @param c
	 *            the creature
	 */
	abstract public void unhost(Creature c);

}
