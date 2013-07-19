package graindcafe.thecave.plugin;

import graindcafe.thecave.creatures.Creature;
import graindcafe.thecave.creatures.Zombie;
import graindcafe.thecave.rooms.Portal;
import graindcafe.thecave.rooms.Room;

import java.io.Serializable;
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

/**
 * The Class Dungeon.
 */
public class Dungeon implements Listener, Serializable {

	/**
	 * Version 1
	 */
	private static final long serialVersionUID = 1L;

	/** The registered players. */
	List<Player> registeredPlayers = new LinkedList<Player>();
	/*
	 * The keys are the set of available creatures When a creature is newly
	 * available the value is set to 0
	 */
	/** The creatures. */
	Map<Class<? extends Creature>, List<Creature>> creatures = new HashMap<Class<? extends Creature>, List<Creature>>();

	/** Association with Bukkit's entities. */
	Map<Entity, Creature> bukkitAssociation = new HashMap<Entity, Creature>();
	/*
	 * The keys are the set of available rooms
	 */
	/** The rooms. */
	Map<Class<? extends Room>, List<Room>> rooms = new HashMap<Class<? extends Room>, List<Room>>();

	/** A new random. */
	Random rand = new Random();

	/** The plugin. */
	TheCave plugin;

	/**
	 * Instantiates a new dungeon.
	 * 
	 * @param plugin
	 *            the plugin
	 */
	private Dungeon(TheCave plugin) {
		this.plugin = plugin;
		/*
		 * Room available by default
		 */
		registerRoom(Portal.class);
		// registerRoom(Den.class);
		registerCreature(Zombie.class);
	}

	/**
	 * Instantiates a new dungeon.
	 * 
	 * @param plugin
	 *            the plugin
	 * @param owner
	 *            the owner
	 */
	public Dungeon(TheCave plugin, Player owner) {
		this(plugin);
		addPlayer(owner);
		plugin.registerDungeon(owner, this);
	}

	/**
	 * Instantiates a new dungeon.
	 * 
	 * @param plugin
	 *            the plugin
	 * @param playerName
	 *            the player name
	 */
	public Dungeon(TheCave plugin, String playerName) {
		this(plugin, Bukkit.getPlayer(playerName));
	}

	/**
	 * Gets the rooms.
	 * 
	 * @return the rooms
	 */
	public Collection<List<Room>> getRooms() {
		return rooms.values();
	}

	/**
	 * Register creature.
	 * 
	 * @param c
	 *            the creature
	 */
	public void registerCreature(Class<? extends Creature> c) {
		creatures.put(c, new LinkedList<Creature>());
	}

	/**
	 * Register room.
	 * 
	 * @param r
	 *            the room
	 */
	public void registerRoom(Class<? extends Room> r) {
		rooms.put(r, new LinkedList<Room>());
	}

	/**
	 * Adds the player.
	 * 
	 * @param p
	 *            the player
	 */
	public void addPlayer(Player p) {
		registeredPlayers.add(p);
	}

	/**
	 * Adds the creature.
	 * 
	 * @param c
	 *            the creature
	 */
	public void addCreature(Creature c) {
		bukkitAssociation.put(c.getEntity(), c);
		creatures.get(c.getClass()).add(c);
	}

	/**
	 * Adds the room.
	 * 
	 * @param r
	 *            the room
	 */
	public void addRoom(Room r) {
		List<Room> similarRooms = this.rooms.get(r.getClass());
		similarRooms.add(r);
	}

	/**
	 * Gets the creatures.
	 * 
	 * @return the creatures
	 */
	public Collection<List<Creature>> getCreatures() {
		return creatures.values();
	}

	/**
	 * Checks for room.
	 * 
	 * @param room
	 *            the room
	 * @return true, if successful
	 */
	public boolean hasRoom(Class<? extends Room> room) {
		return rooms.keySet().contains(room);
	}

	/**
	 * Number of creature in the dungeon
	 * 
	 * @param creature
	 *            the creature
	 * @return the number of this creature
	 */
	public int numberOf(Class<? extends Creature> creature) {
		return creatures.get(creature).size();
	}

	/**
	 * Gets the random.
	 * 
	 * @return the random
	 */
	public Random getRandom() {
		return rand;
	}

	/**
	 * Gets the managed entities.
	 * 
	 * @return the managed entities
	 */
	public Set<Entity> getManagedEntities() {
		return bukkitAssociation.keySet();
	}

	/**
	 * Gets the possible creatures.
	 * 
	 * @return the possible creatures
	 */
	public Collection<? extends Class<? extends Creature>> getPossibleCreatures() {
		return creatures.keySet();
	}

	/**
	 * On entity death.
	 * 
	 * @param event
	 *            the event
	 */
	@EventHandler
	public void onEntityDeath(final EntityDeathEvent event) {
		Creature c = bukkitAssociation.get(event.getEntity());
		if (c != null) {
			creatures.get(c.getClass()).remove(c);
			c.died();
		}
	}

	/**
	 * Stat.
	 * 
	 * @return the string
	 */
	public String stat() {
		String plist = "";
		String clist = "";
		String rlist = "";

		for (Player p : registeredPlayers)
			plist += p.getName() + ", ";
		plist = plist.substring(0, plist.length() - 2);
		for (Entry<Class<? extends Creature>, List<Creature>> e : creatures
				.entrySet())
			clist += "\n  " + e.getKey().getSimpleName() + ": "
					+ e.getValue().size();
		for (Entry<Class<? extends Room>, List<Room>> e : rooms.entrySet())
			rlist += "\n  " + e.getKey().getSimpleName() + ": "
					+ e.getValue().size();
		return String.format(plugin.getLocale("Message.Stat"), plist, clist,
				rlist);
	}
}
