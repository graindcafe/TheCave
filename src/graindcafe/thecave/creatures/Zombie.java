package graindcafe.thecave.creatures;

import org.bukkit.entity.Entity;

public class Zombie extends Creature {

	@Override
	protected Class<? extends Entity> getEntityClass() {
		return org.bukkit.entity.Zombie.class;
	}

}
