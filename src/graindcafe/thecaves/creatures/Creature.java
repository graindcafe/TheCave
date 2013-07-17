package graindcafe.thecaves.creatures;

import graindcafe.thecave.rooms.Room;

import java.util.ArrayList;
import java.util.Collection;

import org.bukkit.Location;
import org.bukkit.entity.Entity;

abstract public class Creature {
	Entity entity;

	public Entity getEntity() {
		return entity;
	}

	protected void setEntity(Entity justSpawned) {
		entity = justSpawned;
	}

	abstract protected Class<? extends Entity> getEntityClass();

	public static Creature spawn(Class<? extends Creature> klass, Location loc) {
		Creature c;
		try {
			c = klass.getConstructor().newInstance();
			c.setEntity(loc.getWorld().spawn(loc, c.getEntityClass()));
			return c;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public static Collection<Class<? extends Room>> getRequieredRooms(
			Class<? extends Creature> klass) {
		if (klass.equals(Creature.class))
			return new ArrayList<Class<? extends Room>>();
		return null;
	}
}
