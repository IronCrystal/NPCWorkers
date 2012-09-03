package com.volumetricpixels.supported.NPCWorkers.entities.component;

import org.spout.api.Source;
import org.spout.api.Spout;
import org.spout.api.entity.BasicComponent;
import org.spout.api.entity.Entity;
import org.spout.api.event.entity.EntityHealthChangeEvent;
import org.spout.api.tickable.TickPriority;
import org.spout.vanilla.data.VanillaData;
import org.spout.vanilla.data.effect.SoundEffect;
import org.spout.vanilla.data.effect.store.SoundEffects;
import org.spout.vanilla.entity.source.DamageCause;
import org.spout.vanilla.entity.source.HealthChangeReason;
import org.spout.vanilla.event.entity.EntityAnimationEvent;
import org.spout.vanilla.event.entity.EntityStatusEvent;
import org.spout.vanilla.protocol.msg.entity.EntityAnimationMessage;
import org.spout.vanilla.protocol.msg.entity.EntityStatusMessage;

import com.volumetricpixels.supported.NPCWorkers.entities.controller.NPC;

public class Health extends BasicComponent<NPC> {
	private static final int DEATH_TIME_TICKS = 30;
	private int deathTicks = -1;
	private int health = 1;
	private int maxHealth = 1;
	private boolean hasDeathAnimation = true;
	private SoundEffect hurtEffect = SoundEffects.NONE;
	// Damage
	private DamageCause lastDamageCause = DamageCause.UNKNOWN;
	private Entity lastDamager;

	public Health(TickPriority priority) {
		super(priority);
	}

	@Override
	public void onAttached() {
		health = getParent().getDataMap().get(VanillaData.HEALTH);
		maxHealth = getParent().getDataMap().get(VanillaData.MAX_HEALTH);
	}

	@Override
	public void onDetached() {
		getParent().getDataMap().put(VanillaData.HEALTH, health);
		getParent().getDataMap().put(VanillaData.MAX_HEALTH, maxHealth);
	}

	/**
	 * Gets the last cause of the damage
	 * @return the last damager
	 */
	public DamageCause getLastDamageCause() {
		return lastDamageCause;
	}

	/**
	 * Gets the last entity that damages this entity
	 * @return last damager
	 */
	public Entity getLastDamager() {
		return lastDamager;
	}

	/**
	 * Gets the maximum health this entity can have
	 * @return the maximum health
	 */
	public int getMaxHealth() {
		return maxHealth;
	}

	/**
	 * Sets the maximum health this entity can have
	 * @param maxHealth to set to
	 */
	public void setMaxHealth(int maxHealth) {
		this.maxHealth = maxHealth;
	}

	/**
	 * Sets the initial maximum health and sets the health to this value
	 * @param maxHealth of this health component
	 */
	public void setSpawnHealth(int maxHealth) {
		this.maxHealth = maxHealth;
		this.setHealth(maxHealth, HealthChangeReason.SPAWN);
	}

	/**
	 * Sets the Effect played when this Entity is hurt
	 * @param effect to play
	 */
	public void setHurtEffect(SoundEffect effect) {
		hurtEffect = effect;
	}

	/**
	 * Gets the Effect played when this Entity is hurt
	 * @return effect played
	 */
	public SoundEffect getHurtEffect() {
		return hurtEffect;
	}

	/**
	 * Gets the health of this entity (hitpoints)
	 * @return the health value
	 */
	public int getHealth() {
		return health;
	}

	/**
	 * Sets the current health value for this entity
	 * @param health hitpoints value to set to
	 * @param source of the change
	 */
	public void setHealth(int health, Source source) {
		EntityHealthChangeEvent event = new EntityHealthChangeEvent(getParent().getParent(), source, health - this.health);
		Spout.getEngine().getEventManager().callEvent(event);
		if (!event.isCancelled()) {
			if (this.health + event.getChange() > maxHealth) {
				this.health = maxHealth;
			} else {
				this.health = this.health + event.getChange();
			}
		}
	}

	/**
	 * Sets the health value to 0
	 * @param source of the change
	 */
	public void die(Source source) {
		setHealth(0, source);
	}

	/**
	 * Returns true if the entity is equal to or less than zero health remaining
	 * @return dead
	 */
	public boolean isDead() {
		return health <= 0;
	}

	/**
	 * Returns true if the entity is dying
	 * @return dying
	 */
	public boolean isDying() {
		return deathTicks > 0;
	}

	/**
	 * Damages this entity with the given {@link DamageCause}.
	 * @param amount amount the entity will be damaged by, can be modified based on armor and enchantments
	 */
	public void damage(int amount) {
		damage(amount, DamageCause.UNKNOWN);
	}

	/**
	 * Damages this entity with the given {@link DamageCause}.
	 * @param amount amount the entity will be damaged by, can be modified based on armor and enchantments
	 * @param cause cause of this entity being damaged
	 */
	public void damage(int amount, DamageCause cause) {
		damage(amount, cause, true);
	}

	/**
	 * Damages this entity with the given {@link DamageCause}.
	 * @param amount amount the entity will be damaged by, can be modified based on armor and enchantments
	 * @param cause cause of this entity being damaged
	 * @param sendHurtMessage whether to send the hurt packet to all players online
	 */
	public void damage(int amount, DamageCause cause, boolean sendHurtMessage) {
		damage(amount, cause, null, sendHurtMessage);
	}

	/**
	 * Damages this entity with the given {@link DamageCause} and damager.
	 * @param amount amount the entity will be damaged by, can be modified based on armor and enchantments
	 * @param cause cause of this entity being damaged
	 * @param damager entity that damaged this entity
	 * @param sendHurtMessage whether to send the hurt packet to all players online
	 */
	public void damage(int amount, DamageCause cause, Entity damager, boolean sendHurtMessage) {
		// TODO take potion effects into account
		setHealth(getHealth() - amount, HealthChangeReason.DAMAGE);
		lastDamager = damager;
		lastDamageCause = cause;
		if (sendHurtMessage) {
			getParent().callProtocolEvent(new EntityAnimationEvent(getParent().getParent(), EntityAnimationMessage.ANIMATION_HURT));
			getParent().callProtocolEvent(new EntityStatusEvent(getParent().getParent(), EntityStatusMessage.ENTITY_HURT));
			getHurtEffect().playGlobal(getParent().getParent().getPosition());
		}
	}

	public boolean hasDeathAnimation() {
		return hasDeathAnimation;
	}

	public void setDeathAnimation(boolean hasDeathAnimation) {
		this.hasDeathAnimation = hasDeathAnimation;
	}

	@Override
	public boolean canTick() {
		return true;
	}

	@Override
	public void onTick(float dt) {
		if (this.isDying()) {
			deathTicks--;
			if (deathTicks == 0) {
				getParent().getParent().kill();
			}
		} else if (this.getHealth() <= 0) {
			if (this.hasDeathAnimation()) {
				deathTicks = DEATH_TIME_TICKS;
				getParent().callProtocolEvent(new EntityStatusEvent(getParent().getParent(), EntityStatusMessage.ENTITY_DEAD));
			} else {
				getParent().getParent().kill();
			}
			getParent().onDeath();
		}
	}
}