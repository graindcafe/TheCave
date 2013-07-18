package graindcafe.thecave.creatures;

import graindcafe.thecave.plugin.Dungeon;
import graindcafe.thecave.rooms.Room;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.bukkit.Location;
import org.bukkit.entity.Entity;

// TODO: Auto-generated Javadoc
/**
 * The Class Creature.
 */
abstract public class Creature {

	/** The entity. */
	Entity entity;

	/** The living rooms. */
	List<Room> livingRooms;

	/**
	 * Instantiates a new creature.
	 * 
	 * @param rooms
	 *            the rooms
	 */
	public Creature(List<Room> rooms) {
		livingRooms = rooms;
		for (Room rm : rooms) {
			rm.host(this);
		}
	}

	/**
	 * This is a callback when the entity died.
	 */
	public void died() {
		for (Room rm : livingRooms)
			rm.unhost(this);
	}

	/**
	 * Gets the entity.
	 * 
	 * @return the entity
	 */
	public Entity getEntity() {
		return entity;
	}

	/**
	 * Sets the entity.
	 * 
	 * @param justSpawned
	 *            the new entity
	 */
	protected void setEntity(Entity justSpawned) {
		entity = justSpawned;
	}

	/**
	 * Gets the entity class.
	 * 
	 * @return the entity class
	 */
	abstract protected Class<? extends Entity> getEntityClass();

	/**
	 * Spawn.
	 * 
	 * @param klass
	 *            the kind of creature to spawn
	 * @param hostingRooms
	 *            the hosting rooms
	 * @param loc
	 *            the location
	 * @param dungeon
	 *            the dungeon
	 * @return the creature
	 */
	public static Creature spawn(Class<? extends Creature> klass,
			List<Room> hostingRooms, Location loc, Dungeon dungeon) {
		Creature newCreature;
		try {
			newCreature = klass.getConstructor(List.class).newInstance(
					hostingRooms);
			newCreature.setEntity(loc.getWorld().spawn(loc,
					newCreature.getEntityClass()));
			dungeon.addCreature(newCreature);
			return newCreature;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * Gets the rooms that the specified creature need to live
	 * 
	 * @param klass
	 *            the kind of creature
	 * @return the required rooms
	 */
	public static Collection<Class<? extends Room>> getRequiredRooms(
			Class<? extends Creature> klass) {
		if (klass.equals(Creature.class))
			return new ArrayList<Class<? extends Room>>();
		return null;
	}
}
