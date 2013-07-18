package graindcafe.thecave.rooms;

import graindcafe.thecave.creatures.Creature;
import graindcafe.thecave.plugin.Dungeon;
import graindcafe.thecave.plugin.TheCave;

import java.util.Map;

import org.bukkit.Location;

abstract public class Room implements Runnable {
	protected TheCave plugin;
	protected Dungeon dungeon;
	protected Location loc;

	protected Room(TheCave plugin, Dungeon dungeon, Location location) {
		this.plugin = plugin;
		this.dungeon = dungeon;
		this.loc = location;
	}

	Map<Class<? extends Creature>, Integer> ownedCreatures;

	abstract public boolean canHost(Class<? extends Creature> c);

}
