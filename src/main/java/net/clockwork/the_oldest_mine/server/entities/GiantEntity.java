package net.clockwork.the_oldest_mine.server.entities;

import net.clockwork.the_oldest_mine.client.sounds.TOMSoundsRegistry;
import net.clockwork.the_oldest_mine.server.effects.TOMEffects;
import net.minecraft.core.BlockPos;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntitySelector;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.AvoidEntityGoal;
import net.minecraft.world.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;
import java.util.HashMap;
import java.util.Objects;
import java.util.UUID;

public class GiantEntity extends Monster {
    private static final UUID SPEED_MODIFIER_UUID = UUID.fromString("B9766B59-9566-4402-BC1F-2EE2A276D836");
    private static final AttributeModifier SPEED_MODIFIER = new AttributeModifier(SPEED_MODIFIER_UUID, "Speed boost", 0.9D, AttributeModifier.Operation.MULTIPLY_BASE);
    private static final EntityDataAccessor<Boolean> DATA_HAS_TARGET = SynchedEntityData.defineId(GiantEntity.class, EntityDataSerializers.BOOLEAN);
    public boolean canRun = false;
    private final HashMap<Integer, Behaviour> behaviours = new HashMap<>();
    private Behaviour currentBehaviour;
    public final MeleeAttackGoal attackGoal = new MeleeAttackGoal(this, 1.0, true);
    public final SlowlyChaseGoal slowlyChaseGoal = new SlowlyChaseGoal(this, 0.0D, false);

    protected GiantEntity(EntityType<? extends Monster> pEntity, Level pLevel) {
        super(pEntity, pLevel);
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Monster.createMonsterAttributes()
                .add(Attributes.MOVEMENT_SPEED, 0.2F)
                .add(Attributes.KNOCKBACK_RESISTANCE, 100.0D)
                .add(Attributes.MAX_HEALTH, 900.0D)
                .add(Attributes.FOLLOW_RANGE, 600.0D)
                .add(Attributes.ATTACK_DAMAGE, 10.0F);
    }

    public boolean doHurtTarget(Entity pEntity) {
        if (!super.doHurtTarget(pEntity)) {
            return false;
        } else {
            if (pEntity instanceof LivingEntity) {
                ((LivingEntity) pEntity).addEffect(new MobEffectInstance(TOMEffects.BOTANY.get(), 700), this);
            }

            return true;
        }
    }

    @Override
    protected void playStepSound(@NotNull BlockPos pPos, @NotNull BlockState pState) {
        this.playSound(TOMSoundsRegistry.ROLLING.get(), 1.0F, 1.0F);
    }

    @Override
    public boolean isPersistenceRequired() {
        return true;
    }

    /**
     * Goals
     */

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(1, new MeleeAttackGoal(this, 1.0D, true));
        this.targetSelector.addGoal(1, new NearestAttackableTargetGoal<>(this, Player.class, true));
        super.registerGoals();

        this.behaviours.put(1, new AgressiveBehaviour(this));
    }

    /**
     * Has Target
     */

    @Override
    public void setTarget(@Nullable LivingEntity pTarget) {
        super.setTarget(pTarget);
        this.setHasTarget(pTarget != null);

        this.behaviours.compute(this.hasTarget() ? this.random.nextInt(5) : this.random.nextInt(4) * -1, (k, v) -> {
            if (this.currentBehaviour != null) this.currentBehaviour.onDetach();
            if (v != null) v.onAttach();
            return this.currentBehaviour = v != null ? v : this.currentBehaviour;
        });
    }

    public static class RunFromPlayerGoal<T extends LivingEntity> extends AvoidEntityGoal<T> {
        final GiantEntity giantEntity;

        public RunFromPlayerGoal(GiantEntity pGiant, Class<T> pEntityClassToAvoid, float pMaxDist, double pWalkSpeedModifier, double pSprintSpeedModifier) {
            super(pGiant, pEntityClassToAvoid, pMaxDist, pWalkSpeedModifier, pSprintSpeedModifier, EntitySelector.NO_CREATIVE_OR_SPECTATOR::test);
            this.giantEntity = pGiant;
        }

        public boolean canUse() {
            return super.canUse();
        }

        public boolean canContinueToUse() {
            return super.canContinueToUse();
        }
    }

    public static class ChasePlayerGoal extends MeleeAttackGoal {
        final GiantEntity giantEntity;

        public ChasePlayerGoal(GiantEntity pGiant, double pSpeedModifier, boolean pFollowingTargetEvenIfNotSeen) {
            super(pGiant, pSpeedModifier, pFollowingTargetEvenIfNotSeen);
            this.giantEntity = pGiant;
            this.giantEntity.level();
        }

        public boolean canUse() {
            return super.canUse();
        }

        public boolean canContinueToUse() {
            return super.canContinueToUse();
        }
    }

    static class SlowlyChaseGoal extends MeleeAttackGoal {
        final GiantEntity giantEntity;

        public SlowlyChaseGoal(GiantEntity pGiant, double pSpeedModifier, boolean pFollowingTargetEvenIfNotSeen) {
            super(pGiant, pSpeedModifier, pFollowingTargetEvenIfNotSeen);
            this.giantEntity = pGiant;
        }


        public boolean canUse() {
            return super.canUse();
        }

        public boolean canContinueToUse() {
            return super.canContinueToUse();
        }
    }

    public boolean hasTarget() {
        return this.entityData.get(DATA_HAS_TARGET);
    }

    public void setHasTarget(boolean hasTarget) {
        this.entityData.set(DATA_HAS_TARGET, hasTarget);
    }

    @Override
    public void tick() {
        if (!this.level().isClientSide && this.hasTarget()) {
            AttributeInstance attributeinstance = this.getAttribute(Attributes.MOVEMENT_SPEED);
            assert attributeinstance != null;
            attributeinstance.removeModifier(SPEED_MODIFIER);
            if ((Objects.requireNonNull(this.getTarget())).isSprinting() && this.canRun) {
                attributeinstance.addTransientModifier(SPEED_MODIFIER);
            }
        }

        this.canRun = true;
        tickCount = 1;

//        if (tickCount == 650) {
//            Random random = new Random();
//            if (!this.hasTarget()) {
//                switch (random.nextInt(5)) {
//                    case 1:
//                        this.goalSelector.removeGoal(new ChasePlayerGoal(this, 1.0D, false));
//                        this.goalSelector.removeGoal(new SlowlyChaseGoal(this, 1.0D, false));
//                        this.goalSelector.addGoal(16, new RunFromPlayerGoal<>(this, Player.class, 100.0F, 1.0D, 1.0D));
//                        this.goalSelector.addGoal(16, new RandomStrollGoal(this, 1.0D));
//                        this.targetSelector.removeGoal(new NearestAttackableTargetGoal<>(this, Player.class, true));
//                        this.canRun = true;
//                        setNoAi(false);
//                        break;
//                    case 2:
//                        this.goalSelector.addGoal(16, new ChasePlayerGoal(this, 1.0D, false));
//                        this.goalSelector.removeGoal(new SlowlyChaseGoal(this, 1.0D, false));
//                        this.goalSelector.removeGoal(new RunFromPlayerGoal<>(this, Player.class, 100.0F, 1.0D, 1.0D));
//                        this.goalSelector.addGoal(16, new RandomStrollGoal(this, 1.0D));
//                        this.targetSelector.addGoal(18, new NearestAttackableTargetGoal<>(this, Player.class, true));
//                        this.canRun = true;
//                        setNoAi(false);
//                        break;
//                    case 3:
//                        this.goalSelector.addGoal(16, new SlowlyChaseGoal(this, 0.0D, false));
//                        this.goalSelector.removeGoal(new RunFromPlayerGoal<>(this, Player.class, 100.0F, 1.0D, 1.0D));
//                        this.goalSelector.removeGoal(new ChasePlayerGoal(this, 1.0D, false));
//                        this.goalSelector.addGoal(16, new RandomStrollGoal(this, 1.0D));
//                        this.targetSelector.addGoal(16, new NearestAttackableTargetGoal<>(this, Player.class, true));
//                        this.canRun = false;
//                        setNoAi(false);
//                        break;
//                    case 4:
//                        setNoAi(true);
//                }
//            } else if (this.hasTarget()) {
//                switch (random.nextInt(4)) {
//                    case 1:
//                        this.goalSelector.removeGoal(new ChasePlayerGoal(this, 1.0D, false));
//                        this.goalSelector.removeGoal(new SlowlyChaseGoal(this, 1.0D, false));
//                        this.goalSelector.addGoal(16, new RunFromPlayerGoal<>(this, Player.class, 100.0F, 1.0D, 1.0D));
//                        this.goalSelector.addGoal(16, new RandomStrollGoal(this, 1.0D));
//                        this.targetSelector.removeGoal(new NearestAttackableTargetGoal<>(this, Player.class, true));
//                        this.canRun = true;
//                        setNoAi(false);
//                        break;
//                    case 2:
//                        this.goalSelector.addGoal(16, new ChasePlayerGoal(this, 1.0D, false));
//                        this.goalSelector.removeGoal(new SlowlyChaseGoal(this, 1.0D, false));
//                        this.goalSelector.removeGoal(new RunFromPlayerGoal<>(this, Player.class, 100.0F, 1.0D, 1.0D));
//                        this.goalSelector.addGoal(16, new RandomStrollGoal(this, 1.0D));
//                        this.targetSelector.addGoal(18, new NearestAttackableTargetGoal<>(this, Player.class, true));
//                        this.canRun = true;
//                        setNoAi(false);
//                        break;
//                    case 3:
//                        this.goalSelector.addGoal(16, new SlowlyChaseGoal(this, 0.0D, false));
//                        this.goalSelector.removeGoal(new RunFromPlayerGoal<>(this, Player.class, 100.0F, 1.0D, 1.0D));
//                        this.goalSelector.removeGoal(new ChasePlayerGoal(this, 1.0D, false));
//                        this.goalSelector.addGoal(16, new RandomStrollGoal(this, 1.0D));
//                        this.targetSelector.addGoal(16, new NearestAttackableTargetGoal<>(this, Player.class, true));
//                        this.canRun = false;
//                        setNoAi(false);
//                }
//            }
//            tickCount = 0;
//        }

        super.tick();
    }

    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(DATA_HAS_TARGET, false);
    }
}
