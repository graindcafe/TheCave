package graindcafe.thecave.plugin;

import graindcafe.thecave.commands.DebugController;

import java.util.HashMap;
import java.util.Map;

import me.graindcafe.gls.DefaultLanguage;
import me.graindcafe.gls.Language;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class TheCave extends JavaPlugin {
	Map<Player, Dungeon> registredDungeons = new HashMap<Player, Dungeon>();
	static Language language;

	public static void message(CommandSender player, String key) {
		player.sendMessage(language.get(key));
	}

	private void initLanguage() {
		byte langVersion = 1;
		String langFolder = getDataFolder().getAbsolutePath() + "language/";
		DefaultLanguage.setAuthor("Graindcafe");
		DefaultLanguage.setName("English");
		DefaultLanguage.setVersion(langVersion);
		DefaultLanguage.setLanguagesFolder(langFolder);
		DefaultLanguage.setLocales(new HashMap<String, String>() {
			private static final long serialVersionUID = 1L;
			{
				put("usage", "Usage: ");
			}
		});
		/*
		 * if (config != null) { language =
		 * Language.init(config.PluginModeLanguage); if (language.isLoaded())
		 * LogWarning(language.get("Warning.LanguageFileMissing")); if
		 * (language.isOutdated())
		 * LogWarning(language.get("Warning.LanguageOutdated"));
		 * LogInfo(String.format(language.get("Info.ChosenLanguage"),
		 * language.getName(), language.getAuthor())); } else
		 */
		language = new DefaultLanguage();
		language.setPrefix("Message.", language.get("Prefix.Message"));
		language.setPrefix("Broadcast.", language.get("Prefix.Broadcast"));
		language.setPrefix("Info.", language.get("Prefix.Info"));
		language.setPrefix("Warning.", language.get("Prefix.Warning"));
		language.setPrefix("Severe.", language.get("Prefix.Severe"));
	}

	@Override
	public void onDisable() {
		super.onDisable();
	}

	@Override
	public void onEnable() {
		initLanguage();
		getCommand("debug").setExecutor(new DebugController(this));
		super.onEnable();
	}

	public void registerDungeon(Player p, Dungeon dungeon) {
		registredDungeons.put(p, dungeon);
	}

	public Dungeon getDungeon(Player p) {
		return registredDungeons.get(p);
	}

}
