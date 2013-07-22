package graindcafe.thecave.rooms;

import graindcafe.thecave.creatures.Creature;
import graindcafe.thecave.plugin.Dungeon;
import graindcafe.thecave.plugin.TheCave;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;

public class Nexus extends Room {

	private static final long serialVersionUID = 1L;

	protected Nexus(TheCave plugin, Dungeon dungeon, Location location) {
		super(plugin, dungeon, location);
	}

	public Nexus(TheCave plugin, Player p) {
		this(plugin, plugin.getDungeon(p), p.getLocation());
	}

	public void run() {

	}

	@Override
	public boolean canHost(Class<? extends Creature> c) {
		return true;
	}

	@Override
	public void host(Creature c) {

	}

	@Override
	public void unhost(Creature c) {

	}

	@Override
	protected void decorate() {
		Location loc = blocks.iterator().next();
		SquareDecorator.border(loc, Material.NETHER_BRICK, 3, false);
		SquareDecorator.border(loc.getBlock().getRelative(BlockFace.UP, 1)
				.getLocation(), Material.NETHER_BRICK, 2, false);
		SquareDecorator.border(loc.getBlock().getRelative(BlockFace.UP, 2)
				.getLocation(), Material.NETHER_BRICK, 1, true);
		loc.getBlock().getRelative(BlockFace.WEST, 3)
				.setType(Material.NETHER_BRICK_STAIRS);
		loc.getBlock().getRelative(BlockFace.UP).getRelative(BlockFace.WEST, 2)
				.setType(Material.NETHER_BRICK_STAIRS);
		loc.getBlock().getRelative(BlockFace.UP, 2)
				.getRelative(BlockFace.WEST, 1)
				.setType(Material.NETHER_BRICK_STAIRS);
		loc.getBlock().getRelative(BlockFace.WEST, 2)
				.setType(Material.NETHER_BRICK);
		loc.getBlock().getRelative(BlockFace.UP).getRelative(BlockFace.WEST, 1)
				.setType(Material.NETHER_BRICK);
		loc.getBlock().getRelative(BlockFace.UP, 2)
				.getRelative(BlockFace.WEST, 0).setType(Material.NETHER_BRICK);
	}

}
