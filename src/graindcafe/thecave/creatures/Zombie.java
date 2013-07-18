package graindcafe.thecave.creatures;

import graindcafe.thecave.rooms.Room;

import java.util.List;

import org.bukkit.entity.Entity;

public class Zombie extends Creature {

	public Zombie(List<Room> rooms) {
		super(rooms);
	}

	@Override
	protected Class<? extends Entity> getEntityClass() {
		return org.bukkit.entity.Zombie.class;
	}

}
