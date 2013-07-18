package graindcafe.thecave.creatures;

import graindcafe.thecave.rooms.Room;

import java.util.List;

import org.bukkit.entity.Entity;

// TODO: Auto-generated Javadoc
/**
 * The Class Ghost.
 */
public class Ghost extends Creature {

	/**
	 * Instantiates a new ghost.
	 * 
	 * @param rooms
	 *            the rooms
	 */
	public Ghost(List<Room> rooms) {
		super(rooms);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see graindcafe.thecave.creatures.Creature#getEntityClass()
	 */
	@Override
	protected Class<? extends Entity> getEntityClass() {
		return org.bukkit.entity.Ghast.class;
	}

}
