package graindcafe.thecave.plugin;

import graindcafe.thecave.commands.DebugController;

import java.util.HashMap;
import java.util.Map;

import me.graindcafe.gls.DefaultLanguage;
import me.graindcafe.gls.Language;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

// TODO: Auto-generated Javadoc
/**
 * The Class TheCave.
 */
public class TheCave extends JavaPlugin {

	/** The registred dungeons. */
	Map<Player, Dungeon> registredDungeons = new HashMap<Player, Dungeon>();

	/** The language. */
	static Language language;

	/**
	 * Message a player
	 * 
	 * @param player
	 *            the player
	 * @param key
	 *            the locale's key
	 */
	public static void message(CommandSender player, String key) {
		player.sendMessage(language.get(key));
	}

	/**
	 * Initialize the language.
	 */
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
				put("usage", "Usage: /cavedebug create dungeon|portal ");
				put("Players: %s\nCreatures: %sRooms: %s",
						"Players: %s\nCreatures: %sRooms: %s");
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.bukkit.plugin.java.JavaPlugin#onDisable()
	 */
	@Override
	public void onDisable() {
		super.onDisable();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.bukkit.plugin.java.JavaPlugin#onEnable()
	 */
	@Override
	public void onEnable() {
		initLanguage();
		getCommand("cavedebug").setExecutor(new DebugController(this));
		super.onEnable();
	}

	/**
	 * Register dungeon.
	 * 
	 * @param p
	 *            the p
	 * @param dungeon
	 *            the dungeon
	 */
	public void registerDungeon(Player p, Dungeon dungeon) {
		registredDungeons.put(p, dungeon);
	}

	/**
	 * Gets the dungeon.
	 * 
	 * @param p
	 *            the p
	 * @return the dungeon
	 */
	public Dungeon getDungeon(Player p) {
		return registredDungeons.get(p);
	}

	/**
	 * Gets the locale.
	 * 
	 * @param string
	 *            the string
	 * @return the locale
	 */
	public String getLocale(String string) {
		return language.get(string);
	}

}
