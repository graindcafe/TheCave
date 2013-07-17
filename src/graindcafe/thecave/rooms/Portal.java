package graindcafe.thecave.rooms;

import graindcafe.thecaves.creatures.Creature;
import graindcafe.thecaves.creatures.Zombie;
import graindcafe.thecaves.plugin.Dungeon;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import org.bukkit.Location;

public class Portal extends Room {
	Dungeon dungeon;
	Location loc;
	Integer livingSoul;
	final static int maxLivingSoul = 10;
	static final ArrayList<Class<? extends Creature>> possibleCreatures = new ArrayList<Class<? extends Creature>>() {
		private static final long serialVersionUID = 1L;
		{
			add(Zombie.class);
		}
	};

	public Portal(Location loc) {
		this.loc = loc;
	}

	public void run() {
		List<Class<? extends Creature>> possibles = new ArrayList<Class<? extends Creature>>();
		possibles.addAll(possibleCreatures);
		Iterator<Class<? extends Creature>> it = possibles.iterator();
		while (it.hasNext()) {
			Class<? extends Creature> c = it.next();
			for (Class<? extends Room> r : Creature.getRequieredRooms(c))
				if (!dungeon.hasRoom(r)) {
					it.remove();
					break;
				}
		}
		it = possibles.iterator();
		while (it.hasNext()) {
			Class<? extends Creature> c = it.next();

			for (Collection<Room> rooms : dungeon.getRooms()) {
				boolean satisfyAny = false;
				for (Room rm : rooms) {
					if (rm.canHost(c)) {
						satisfyAny = true;
					}

				}
				if (!satisfyAny)
					it.remove();
			}
		}
		Creature.spawn(
				possibles.get(dungeon.getRandom().nextInt(possibles.size())),
				loc);
	}

	@Override
	public Integer getMaxOf(Class<? extends Creature> c) {
		return null;
	}

	@Override
	public boolean canHost(Class<? extends Creature> c) {
		return livingSoul < maxLivingSoul;
	}
	/*
	 * public Collection<Constraint> getMaxConstraints() { List<Constraint> list
	 * = new LinkedList<Constraint>(); list.add(new Constraint(Creature.class,
	 * 10)); return list; }
	 */
}
