package graindcafe.thecave.rooms;

import graindcafe.thecaves.creatures.Creature;

import java.util.Map;

abstract public class Room implements Runnable {
	Map<Class<? extends Creature>, Integer> ownedCreatures;

	abstract public boolean canHost(Class<? extends Creature> c);

	abstract public Integer getMaxOf(Class<? extends Creature> c);

}
