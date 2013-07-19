package graindcafe.thecave.player;

import graindcafe.thecave.spells.Spell;

import java.io.Serializable;
import java.util.List;

abstract public class Player implements Serializable {
	/**
	 * Version 1
	 */
	private static final long serialVersionUID = 1L;
	/** The Gold amount */
	int gold;
	/** Available spells */
	List<Class<? extends Spell>> spells;
	/** Currently selected spell */
	Class<? extends Spell> selectedSpell;
}
