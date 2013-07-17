package graindcafe.thecaves.creatures;

import org.bukkit.entity.Entity;

public class Ghost extends Creature {

	@Override
	protected Class<? extends Entity> getEntityClass() {
		return org.bukkit.entity.Ghast.class;
	}

}
