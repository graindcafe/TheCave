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
import java.util.Map.Entry;
import java.util.Random;
import java.util.Set;

import org.bukkit.Bukkit;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;

public class Dungeon implements Listener {

	List<Player> registredPlayers = new LinkedList<Player>();
	/*
	 * The keys are the set of available creatures When a creature is newly
	 * available the value is set to 0
	 */
	Map<Class<? extends Creature>, List<Creature>> creatures = new HashMap<Class<? extends Creature>, List<Creature>>();
	Map<Entity, Creature> bukkitAssociation = new HashMap<Entity, Creature>();
	/*
	 * The keys are the set of available rooms
	 */
	Map<Class<? extends Room>, List<Room>> rooms = new HashMap<Class<? extends Room>, List<Room>>();
	Random rand = new Random();
	TheCave plugin;

	private Dungeon(TheCave plugin) {
		this.plugin = plugin;
		/*
		 * Room available by default
		 */
		registerRoom(Portal.class);
		// registerRoom(Den.class);
		registerCreature(Zombie.class);
	}

	public Dungeon(TheCave plugin, Player owner) {
		this(plugin);
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
		bukkitAssociation.put(c.getEntity(), c);
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

	public Set<Entity> getManagedEntities() {
		return bukkitAssociation.keySet();
	}

	public Collection<? extends Class<? extends Creature>> getPossibleCreatures() {
		return creatures.keySet();
	}

	@EventHandler
	public void onEntityDeath(final EntityDeathEvent event) {
		Creature c = bukkitAssociation.get(event.getEntity());
		if (c != null)
			c.died();
	}

	public String stat() {
		String plist = "";
		String clist = "";
		String rlist = "";
		for (Player p : registredPlayers)
			plist += p.getName() + ", ";
		plist = plist.substring(0, plist.length() - 1);
		for (Entry<Class<? extends Creature>, List<Creature>> e : creatures
				.entrySet())
			clist += e.getKey().getName() + ": " + e.getValue().size() + "\n";
		for (Entry<Class<? extends Room>, List<Room>> e : rooms.entrySet())
			clist += e.getKey().getName() + ": " + e.getValue().size() + "\n";
		return String.format(
				plugin.getLocale("Players: %s\nCreatures: %sRooms: %s"), plist,
				clist, rlist);
	}
}
