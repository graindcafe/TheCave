package graindcafe.thecave.rooms;

import graindcafe.thecave.creatures.Creature;
import graindcafe.thecave.plugin.Dungeon;
import graindcafe.thecave.plugin.TheCave;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;

// TODO: Auto-generated Javadoc
/**
 * The Class Portal.
 */
public class Portal extends Room {

	/** The living soul. */
	Integer livingSoul = 0;

	/** The max Living Souls. */
	final static int maxLivingSoul = 10;

	/**
	 * Instantiates a new portal.
	 * 
	 * @param plugin
	 *            the plugin
	 * @param p
	 *            the player
	 */
	public Portal(TheCave plugin, Player p) {
		this(plugin, plugin.getDungeon(p), p.getLocation());
	}

	/**
	 * Instantiates a new portal.
	 * 
	 * @param plugin
	 *            the plugin
	 * @param dungeon
	 *            the dungeon
	 * @param loc
	 *            the loc
	 */
	public Portal(TheCave plugin, Dungeon dungeon, Location loc) {
		super(plugin, dungeon, loc);
		Bukkit.getScheduler().scheduleSyncRepeatingTask(plugin, this, 100, 200);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Runnable#run()
	 */
	public void run() {
		Map<Class<? extends Creature>, List<Room>> possibles = new HashMap<Class<? extends Creature>, List<Room>>();

		for (Class<? extends Creature> race : dungeon.getPossibleCreatures())
			possibles.put(race, new LinkedList<Room>());

		if (possibles.isEmpty())
			return;
		Iterator<Class<? extends Creature>> it = possibles.keySet().iterator();
		while (it.hasNext()) {
			Class<? extends Creature> c = it.next();
			Collection<Class<? extends Room>> rRooms = Creature
					.getRequiredRooms(c);
			if (rRooms != null)
				for (Class<? extends Room> r : rRooms)
					if (!dungeon.hasRoom(r)) {
						it.remove();
						break;
					}
		}
		Iterator<Entry<Class<? extends Creature>, List<Room>>> it2 = possibles
				.entrySet().iterator();
		while (it2.hasNext()) {
			Entry<Class<? extends Creature>, List<Room>> e = it2.next();
			Class<? extends Creature> c = e.getKey();
			List<Room> satisfyingRooms = e.getValue();
			for (Collection<Room> rooms : dungeon.getRooms()) {
				Room satisfying = null;
				for (Room rm : rooms) {
					if (rm.canHost(c)) {
						satisfying = rm;
						break;
					}
				}

				if (satisfying == null)
					it2.remove();
				else
					satisfyingRooms.add(satisfying);
			}
		}
		it2 = possibles.entrySet().iterator();
		if (possibles.isEmpty())
			return;
		for (int i = dungeon.getRandom().nextInt(possibles.size()); i > 0; i--)
			it2.next();
		Entry<Class<? extends Creature>, List<Room>> chosen = it2.next();
		Creature.spawn(chosen.getKey(), chosen.getValue(), blocks.iterator()
				.next(), dungeon);
		// do not increase livingSoul here as it would be done with the
		// host(Creature) callback
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see graindcafe.thecave.rooms.Room#canHost(java.lang.Class)
	 */
	@Override
	public boolean canHost(Class<? extends Creature> c) {
		return livingSoul < maxLivingSoul;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * graindcafe.thecave.rooms.Room#host(graindcafe.thecave.creatures.Creature)
	 */
	@Override
	public void host(Creature c) {
		livingSoul++;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * graindcafe.thecave.rooms.Room#unhost(graindcafe.thecave.creatures.Creature
	 * )
	 */
	@Override
	public void unhost(Creature c) {
		livingSoul--;
	}

	@Override
	protected void decorate() {
		for (Location loc : blocks) {
			loc.getBlock().getRelative(BlockFace.DOWN)
					.setType(Material.OBSIDIAN);
		}
	}

}
