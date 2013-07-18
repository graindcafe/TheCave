package graindcafe.thecave.creatures;

import graindcafe.thecave.rooms.Room;

import java.util.List;

import org.bukkit.entity.Entity;

public class Skeleton extends Creature {

	public Skeleton(List<Room> rooms) {
		super(rooms);
	}

	@Override
	protected Class<? extends Entity> getEntityClass() {
		return org.bukkit.entity.Skeleton.class;
	}

}
