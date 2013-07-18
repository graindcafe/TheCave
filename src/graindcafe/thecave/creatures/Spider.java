package graindcafe.thecave.creatures;

import graindcafe.thecave.rooms.Room;

import java.util.List;

import org.bukkit.entity.Entity;

public class Spider extends Creature {

	public Spider(List<Room> rooms) {
		super(rooms);
	}

	@Override
	protected Class<? extends Entity> getEntityClass() {
		return org.bukkit.entity.Spider.class;
	}

}
