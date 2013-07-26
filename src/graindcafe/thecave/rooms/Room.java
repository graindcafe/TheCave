package graindcafe.thecave.rooms;

import graindcafe.thecave.creatures.Creature;
import graindcafe.thecave.plugin.Dungeon;
import graindcafe.thecave.plugin.TheCave;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;

/**
 * The Class Room.
 */
abstract public class Room implements Runnable, Serializable {

	/**
	 * Version 1
	 */
	private static final long serialVersionUID = 1L;

	/** The plugin. */
	protected TheCave plugin;

	/** The dungeon. */
	protected Dungeon dungeon;

	/** The blocks. */
	protected Set<Location> blocks;

	/**
	 * Instantiates a new room.
	 * 
	 * @param plugin
	 *            the plugin
	 * @param dungeon
	 *            the dungeon
	 * @param location
	 *            the location
	 */
	protected Room(TheCave plugin, Dungeon dungeon, Location location) {
		this.plugin = plugin;
		this.dungeon = dungeon;
		this.blocks = new HashSet<Location>();
		this.blocks.add(location);
		this.decorate();
	}

	/** The owned creatures. */
	Map<Class<? extends Creature>, Integer> ownedCreatures;

	/**
	 * Can host this creature
	 * 
	 * @param c
	 *            the creature
	 * @return true, if it can host this creature
	 */
	abstract public boolean canHost(Class<? extends Creature> c);

	/**
	 * Host the creature in this room
	 * 
	 * @param c
	 *            the creature
	 */
	abstract public void host(Creature c);

	/**
	 * Remove a creature from this room
	 * 
	 * @param c
	 *            the creature
	 */
	abstract public void unhost(Creature c);

	/**
	 * Decorate.
	 */
	abstract protected void decorate();

	protected static class SquareDecorator {

		static void border(Location center, Material mat, int distance) {
			border(center, mat, distance, true);
		}

		static void border(Location center, Material mat, int distance,
				boolean doCorner) {
			World w = center.getWorld();
			int x = center.getBlockX();
			int y = center.getBlockY();
			int z = center.getBlockZ();
			int limit = doCorner ? distance + 1 : distance;

			for (int i = -distance + (doCorner ? 0 : 1); i < limit; i++) {
				w.getBlockAt(x + distance, y, z + i).setType(mat);
				w.getBlockAt(x + i, y, z + distance).setType(mat);
				w.getBlockAt(x - distance, y, z + i).setType(mat);
				w.getBlockAt(x + i, y, z - distance).setType(mat);
			}
		}

		static void dig(Location center) {
			dig(center, 0);
		}

		static void dig(Location center, int distance) {
			dig(center, distance, 1);
		}

		static void dig(Location center, int distance, int depth) {
			dig(center, distance, depth, Material.AIR);
		}

		static void dig(Location center, int distance, int depth,
				Material gapMat) {
			dig(center, distance, depth - 1, gapMat, gapMat);
		}

		static void dig(Location center, int distance, int depth,
				Material gapMat, Material ground) {
			World w = center.getWorld();
			int x = center.getBlockX();
			int y = center.getBlockY();
			int z = center.getBlockZ();
			for (int d = 1; d <= depth; d++)
				for (int i = -distance + 1; i < distance; i++) {
					for (int j = -distance + 1; j < distance; j++) {
						w.getBlockAt(x + j, y - d, z + i).setType(gapMat);
						w.getBlockAt(x + i, y - d, z + j).setType(gapMat);
					}
				}
			for (int i = -distance + 1; i < distance; i++) {
				for (int j = -distance + 1; j < distance; j++) {
					w.getBlockAt(x + j, y - depth, z + i).setType(ground);
					w.getBlockAt(x + i, y - depth, z + j).setType(ground);
				}
			}
		}
	}
}
