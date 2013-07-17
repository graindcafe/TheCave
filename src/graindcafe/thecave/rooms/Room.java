package graindcafe.thecave.rooms;

import graindcafe.thecaves.creatures.Creature;
import graindcafe.thecaves.plugin.Dungeon;

import java.util.Map;

import org.bukkit.Location;

abstract public class Room implements Runnable {
	protected Dungeon dungeon;
	protected Location loc;
	Map<Class<? extends Creature>, Integer> ownedCreatures;

	abstract public boolean canHost(Class<? extends Creature> c);

}
