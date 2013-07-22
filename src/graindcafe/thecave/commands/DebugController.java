package graindcafe.thecave.commands;

import graindcafe.thecave.plugin.Dungeon;
import graindcafe.thecave.plugin.TheCave;
import graindcafe.thecave.rooms.Portal;

import java.util.List;

import org.bukkit.block.BlockFace;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;

// TODO: Auto-generated Javadoc
/**
 * The Class DebugController.
 */
public class DebugController implements CommandExecutor {

	/** The plugin. */
	TheCave plugin;

	/**
	 * Instantiates a new debug controller.
	 * 
	 * @param plugin
	 *            the plugin
	 */
	public DebugController(TheCave plugin) {
		this.plugin = plugin;
	}

	/**
	 * Usage.
	 * 
	 * @param sender
	 *            the sender
	 * @return true, if successful
	 */
	public boolean usage(CommandSender sender) {
		// Display a specific usage for op
		if (sender.isOp()) {
			TheCave.message(sender, "usage");
			return true;
		}
		// Return usage wrote in "plugin.yml"
		return false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.bukkit.command.CommandExecutor#onCommand(org.bukkit.command.CommandSender
	 * , org.bukkit.command.Command, java.lang.String, java.lang.String[])
	 */
	public boolean onCommand(final CommandSender sender, final Command command,
			final String label, final String[] args) {
		if (args.length == 0)
			return usage(sender);
		args[0] = args[0].toLowerCase();

		if (args[0].equalsIgnoreCase("create") && args.length > 1) {
			if (args[1].equalsIgnoreCase("dungeon")) {
				if (sender instanceof Player) {
					new Dungeon(plugin, (Player) sender);
					return true;
				} else if (args.length > 2) {
					new Dungeon(plugin, args[2]);
					return true;
				} else
					return usage(sender);
			} else if (args[1].equalsIgnoreCase("portal")
					&& sender instanceof Player) {
				Player p = (Player) sender;
				Dungeon d = plugin.getDungeon(p);
				if (d == null) {
					new Portal(plugin, p);
					p.sendMessage("The portal was not added to any dungeon as you don't have any.");
				} else {
					d.addRoom(new Portal(plugin, p));
				}
				p.teleport(p.getLocation().getBlock()
						.getRelative(BlockFace.NORTH, 3).getLocation());
				return true;
			}
		} else if (args[0].equalsIgnoreCase("kill") && args.length > 1) {
			if (args[1].equalsIgnoreCase("other")) {
				List<Entity> all = ((Player) sender).getWorld().getEntities();
				all.removeAll(plugin.getDungeon(((Player) sender))
						.getManagedEntities());
				for (Entity e : all)
					if (!(e instanceof Player))
						e.remove();
				return true;
			} else if (args[1].equalsIgnoreCase("dungeon")) {
				for (Entity e : plugin.getDungeon(((Player) sender))
						.getManagedEntities())
					e.remove();
				return true;
			} else if (args[1].equalsIgnoreCase("all")) {
				for (Entity e : ((Player) sender).getWorld().getEntities())
					if (!(e instanceof Player))
						e.remove();
				return true;
			}
		} else if (args[0].equalsIgnoreCase("dungeon") && args.length > 1) {
			if (args[1].equalsIgnoreCase("stat")) {
				sender.sendMessage(plugin.getDungeon(((Player) sender)).stat());
				return true;
			}
		}
		return false;
	}
}
