package graindcafe.thecave.creatures;

import graindcafe.thecave.rooms.Room;

import java.util.List;

import org.bukkit.entity.Entity;

/**
 * The Class Skeleton.
 */
public class Skeleton extends Creature {
	/**
	 * Version 1
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Instantiates a new skeleton.
	 * 
	 * @param rooms
	 *            the rooms
	 */
	public Skeleton(List<Room> rooms) {
		super(rooms);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see graindcafe.thecave.creatures.Creature#getEntityClass()
	 */
	@Override
	protected Class<? extends Entity> getEntityClass() {
		return org.bukkit.entity.Skeleton.class;
	}

}
