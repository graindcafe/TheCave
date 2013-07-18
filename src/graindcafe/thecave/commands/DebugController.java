package graindcafe.thecave.commands;

import graindcafe.thecave.plugin.Dungeon;
import graindcafe.thecave.plugin.TheCave;
import graindcafe.thecave.rooms.Portal;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class DebugController implements CommandExecutor {
	TheCave plugin;

	public DebugController(TheCave plugin) {
		this.plugin = plugin;
	}

	public boolean usage(CommandSender sender) {
		TheCave.message(sender, "usage");
		return false;
	}

	public boolean onCommand(final CommandSender sender, final Command command,
			final String label, final String[] args) {
		if (args.length == 0)
			return usage(sender);
		args[0] = args[0].toLowerCase();

		if (args[0].equalsIgnoreCase("create") && args.length > 1)
			if (args[1].equalsIgnoreCase("dungeon")) {
				if (sender instanceof Player)
					new Dungeon(plugin, (Player) sender);
				else if (args.length > 2)
					new Dungeon(plugin, args[2]);
				else
					return usage(sender);
			} else if (args[1].equalsIgnoreCase("portal")
					&& sender instanceof Player)
				plugin.getDungeon((Player) sender).addRoom(
						new Portal(plugin, ((Player) sender)));

		return false;
	}
}
