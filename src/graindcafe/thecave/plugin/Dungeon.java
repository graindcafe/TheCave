package graindcafe.thecave.plugin;

import graindcafe.thecave.creatures.Creature;
import graindcafe.thecave.creatures.Zombie;
import graindcafe.thecave.rooms.Portal;
import graindcafe.thecave.rooms.Room;

import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class Dungeon {

	List<Player> registredPlayers = new LinkedList<Player>();
	/*
	 * The keys are the set of available creatures When a creature is newly
	 * available the value is set to 0
	 */
	Map<Class<? extends Creature>, List<Creature>> creatures = new HashMap<Class<? extends Creature>, List<Creature>>();
	/*
	 * The keys are the set of available rooms
	 */
	Map<Class<? extends Room>, List<Room>> rooms = new HashMap<Class<? extends Room>, List<Room>>();
	Random rand = new Random();

	private Dungeon() {
		/*
		 * Room available by default
		 */
		registerRoom(Portal.class);
		// registerRoom(Den.class);
		registerCreature(Zombie.class);
	}

	public Dungeon(TheCave plugin, Player owner) {
		this();
		addPlayer(owner);
		plugin.registerDungeon(owner, this);
	}

	public Dungeon(TheCave plugin, String playerName) {
		this(plugin, Bukkit.getPlayer(playerName));
	}

	public Collection<List<Room>> getRooms() {
		return rooms.values();
	}

	public void registerCreature(Class<? extends Creature> c) {
		creatures.put(c, new LinkedList<Creature>());
	}

	public void registerRoom(Class<? extends Room> c) {
		rooms.put(c, new LinkedList<Room>());
	}

	public void addPlayer(Player p) {
		registredPlayers.add(p);
	}

	public void addCreature(Creature c) {
		creatures.get(c.getClass()).add(c);
	}

	public void addRoom(Room r) {
		List<Room> similarRooms = this.rooms.get(r.getClass());
		similarRooms.add(r);
	}

	public Collection<List<Creature>> getCreatures() {
		return creatures.values();
	}

	public boolean hasRoom(Class<? extends Room> room) {
		return rooms.keySet().contains(room);
	}

	public int numberOf(Class<? extends Creature> creature) {
		return creatures.get(creature).size();
	}

	public Random getRandom() {
		return rand;
	}

	public Collection<? extends Class<? extends Creature>> getPossibleCreatures() {
		return creatures.keySet();
	}
}
