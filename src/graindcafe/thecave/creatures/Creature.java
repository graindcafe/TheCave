package graindcafe.thecave.creatures;

import graindcafe.thecave.plugin.Dungeon;
import graindcafe.thecave.rooms.Room;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.bukkit.Location;
import org.bukkit.entity.Entity;

abstract public class Creature {
	Entity entity;
	List<Room> livingRooms;

	public Creature(List<Room> rooms) {
		livingRooms = rooms;
		for (Room rm : rooms) {
			rm.host(this);
		}
	}

	public void died() {
		for (Room rm : livingRooms)
			rm.unhost(this);
	}

	public Entity getEntity() {
		return entity;
	}

	protected void setEntity(Entity justSpawned) {
		entity = justSpawned;
	}

	abstract protected Class<? extends Entity> getEntityClass();

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

	public static Collection<Class<? extends Room>> getRequieredRooms(
			Class<? extends Creature> klass) {
		if (klass.equals(Creature.class))
			return new ArrayList<Class<? extends Room>>();
		return null;
	}
}
