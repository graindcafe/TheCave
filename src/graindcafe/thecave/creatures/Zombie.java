package graindcafe.thecave.creatures;

import graindcafe.thecave.rooms.Room;

import java.util.List;

import org.bukkit.entity.Entity;

/**
 * The Class Zombie.
 */
public class Zombie extends Creature {
	/**
	 * Version 1
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Instantiates a new zombie.
	 * 
	 * @param rooms
	 *            the rooms
	 */
	public Zombie(List<Room> rooms) {
		super(rooms);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see graindcafe.thecave.creatures.Creature#getEntityClass()
	 */
	@Override
	protected Class<? extends Entity> getEntityClass() {
		return org.bukkit.entity.Zombie.class;
	}

}
