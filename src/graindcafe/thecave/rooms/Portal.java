package graindcafe.thecave.rooms;

import graindcafe.thecave.creatures.Creature;
import graindcafe.thecave.plugin.Dungeon;
import graindcafe.thecave.plugin.TheCave;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;

public class Portal extends Room {
	Integer livingSoul;
	final static int maxLivingSoul = 10;

	public Portal(TheCave plugin, Player p) {
		this(plugin, plugin.getDungeon(p), p.getLocation());
	}

	public Portal(TheCave plugin, Dungeon dungeon, Location loc) {
		super(plugin, dungeon, loc);

		Bukkit.getScheduler().scheduleSyncRepeatingTask(plugin, this, 100,
				10000);
	}

	public void run() {
		List<Class<? extends Creature>> possibles = new ArrayList<Class<? extends Creature>>();

		possibles.addAll(dungeon.getPossibleCreatures());
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
	public boolean canHost(Class<? extends Creature> c) {
		return livingSoul < maxLivingSoul;
	}
	/*
	 * public Collection<Constraint> getMaxConstraints() { List<Constraint> list
	 * = new LinkedList<Constraint>(); list.add(new Constraint(Creature.class,
	 * 10)); return list; }
	 */
}
