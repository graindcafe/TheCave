package graindcafe.thecave.rooms;

import graindcafe.thecave.creatures.Creature;
import graindcafe.thecave.plugin.Dungeon;

import java.util.Map;

import org.bukkit.Location;

abstract public class Room implements Runnable {
	protected Dungeon dungeon;
	protected Location loc;
	Map<Class<? extends Creature>, Integer> ownedCreatures;

	abstract public boolean canHost(Class<? extends Creature> c);

}
