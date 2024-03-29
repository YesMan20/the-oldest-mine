package com.yesman_pancakes.the_oldest_mine.server.entities;

import com.yesman_pancakes.the_oldest_mine.client.sounds.TOMSoundsRegistry;
import net.minecraft.core.BlockPos;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.world.entity.EntitySelector;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.AvoidEntityGoal;
import net.minecraft.world.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.world.entity.ai.goal.RandomStrollGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;
import java.util.Objects;
import java.util.UUID;

public class GiantEntity extends Monster {
    private static final UUID SPEED_MODIFIER_UUID = UUID.fromString("B9766B59-9566-4402-BC1F-2EE2A276D836");
    private static final AttributeModifier SPEED_MODIFIER = new AttributeModifier(SPEED_MODIFIER_UUID, "Speed boost", 0.9D, AttributeModifier.Operation.MULTIPLY_BASE);
    private static final EntityDataAccessor<Boolean> DATA_HAS_TARGET = SynchedEntityData.defineId(GiantEntity.class, EntityDataSerializers.BOOLEAN);
    private GiantEntity.RunFromPlayerGoal<Player> runFromPlayerGoal;
    private GiantEntity.ChasePlayerGoal chasePlayerGoal;
    private GiantEntity.SlowlyChaseGoal slowlyChaseGoal;

    protected GiantEntity(EntityType<? extends Monster> pEntity, Level pLevel) {
        super(pEntity, pLevel);
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Monster.createMonsterAttributes()
                .add(Attributes.MOVEMENT_SPEED, 0.2F)
                .add(Attributes.KNOCKBACK_RESISTANCE, 100.0D)
                .add(Attributes.MAX_HEALTH, 100.0D)
                .add(Attributes.FOLLOW_RANGE, 500.0D)
                .add(Attributes.ATTACK_DAMAGE, 100.0F);
    }

    @Override
    protected float getJumpPower() {
        return 0.0F;
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
        super.registerGoals();
        this.goalSelector.addGoal(16, new RandomStrollGoal(this, 1.0D));
        this.targetSelector.addGoal(18, new NearestAttackableTargetGoal<>(this, Player.class, true));
        this.goalSelector.addGoal(16, new GiantEntity.ChasePlayerGoal(this, 1.0D, true));
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

    // Hiii

    static class ChasePlayerGoal extends MeleeAttackGoal {
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

    static class SlowlyChaseGoal extends ChasePlayerGoal {
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

    /**
     * Has Target
     */

    @Override
    public void setTarget(@Nullable LivingEntity pTarget) {
        super.setTarget(pTarget);
        this.setHasTarget(pTarget != null);
    }

    public boolean hasTarget() {
        return this.entityData.get(DATA_HAS_TARGET);
    }

    public void setHasTarget(boolean hasTarget) {
        this.entityData.set(DATA_HAS_TARGET, hasTarget);
    }

    @Override
    public void baseTick() {
        if (!this.level().isClientSide && this.hasTarget()) {
            AttributeInstance attributeinstance = this.getAttribute(Attributes.MOVEMENT_SPEED);
            assert attributeinstance != null;
            attributeinstance.removeModifier(SPEED_MODIFIER);
            if (Objects.requireNonNull(this.getTarget()).isSprinting()) {
                attributeinstance.addTransientModifier(SPEED_MODIFIER);
                ;
            }
        }
        super.baseTick();
    }

    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(DATA_HAS_TARGET, false);
    }
}
