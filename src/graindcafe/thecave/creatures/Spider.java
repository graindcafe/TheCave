package graindcafe.thecave.creatures;

import org.bukkit.entity.Entity;

public class Spider extends Creature {

	@Override
	protected Class<? extends Entity> getEntityClass() {
		return org.bukkit.entity.Spider.class;
	}

}
