package graindcafe.thecave.creatures;

import graindcafe.thecave.rooms.Room;

import java.util.List;

import org.bukkit.entity.Entity;

public class Ghost extends Creature {

	public Ghost(List<Room> rooms) {
		super(rooms);
	}

	@Override
	protected Class<? extends Entity> getEntityClass() {
		return org.bukkit.entity.Ghast.class;
	}

}
