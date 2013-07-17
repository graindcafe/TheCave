package graindcafe.thecaves.plugin;

import graindcafe.thecave.rooms.Room;
import graindcafe.thecaves.creatures.Creature;

import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;

public class Dungeon {
	Set<List<Room>> rooms;
	List<Creature> creatures;
	Map<Class<? extends Creature>, Integer> ownedCreatures;
	Set<Class<? extends Room>> ownedRoomKind;
	Random rand = new Random();

	public Set<List<Room>> getRooms() {
		return rooms;
	}

	public void addCreature(Creature c) {
		Integer count = ownedCreatures.get(c.getClass());
		count++;
		creatures.add(c);
	}

	public List<Creature> getCreatures() {
		return creatures;
	}

	public boolean hasRoom(Class<? extends Room> room) {
		return ownedRoomKind.contains(room);
	}

	public int numberOf(Class<? extends Creature> creature) {
		Integer number = ownedCreatures.get(creature);
		if (number == null)
			return 0;
		else
			return number;
	}

	public Random getRandom() {
		return rand;
	}
}
