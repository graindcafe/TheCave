package graindcafe.thecave.rooms;

import graindcafe.thecave.creatures.Creature;
import graindcafe.thecave.plugin.Dungeon;
import graindcafe.thecave.plugin.TheCave;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.bukkit.Location;

/**
 * The Class Room.
 */
abstract public class Room implements Runnable, Serializable {

	/**
	 * Version 1
	 */
	private static final long serialVersionUID = 1L;

	/** The plugin. */
	protected TheCave plugin;

	/** The dungeon. */
	protected Dungeon dungeon;

	/** The blocks. */
	protected Set<Location> blocks;

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
		this.blocks = new HashSet<Location>();
		this.blocks.add(location);
		this.decorate();
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

	/**
	 * Decorate.
	 */
	abstract protected void decorate();
}
