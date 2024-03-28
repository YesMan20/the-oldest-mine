package com.yesman_pancakes.the_oldest_mine.server.entities.goals;

import com.yesman_pancakes.the_oldest_mine.server.entities.GiantEntity;
import net.minecraft.client.Minecraft;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.ai.goal.RandomStrollGoal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.phys.Vec2;
import net.minecraft.world.phys.Vec3;

public class WatchPlayerGoal extends Goal {
    private final GiantEntity giantEntity;
    private final float ticksTillLeave;
    private float currentTicksTillLeave;
    private boolean shouldLeave;

    public WatchPlayerGoal(GiantEntity pGiant, float pTicksTillLeave) {
        this.giantEntity = pGiant;
        this.ticksTillLeave = pTicksTillLeave;
        this.currentTicksTillLeave = pTicksTillLeave;
    }

    public boolean canUse() {
        if (this.giantEntity.hasTarget()) {
            return false;
        } else {
            return this.giantEntity.RollResult == 1;
        }
    }

    @Override
    public boolean canContinueToUse() {
        return this.giantEntity.RollResult == 1;
    }

    public void setShouldLeave() {
        this.shouldLeave = false;
    }

    public void tickStareClock() {
        --this.currentTicksTillLeave;
        if (this.currentTicksTillLeave <= 0.0F) {
            this.shouldLeave = true;
        }

    }

    public double loopAngle(double angle) {
        if (angle > 360.0) {
            return angle -= 360.0;
        } else {
            return angle < 0.0 ? (angle += 360.0) : angle;
        }
    }

    public boolean isPlayerLookingTowards() {
        LivingEntity pendingTarget = this.giantEntity.getTarget();
        Minecraft minecraft = Minecraft.getInstance();
        boolean yawPlayerLookingTowards = false;
        //float fov = (float)(Integer)minecraft.f_91066_.m_231837_().m_231551_();
        assert minecraft.player != null;
        float fov = minecraft.player.getFieldOfViewModifier();
        float yFovMod = 0.65F;
        float fovMod = (35.0F / fov - 1.0F) * 0.4F + 1.0F;
        fov *= fovMod;
        assert pendingTarget != null;
        Vec3 a = pendingTarget.getEyePosition();
        Vec3 b = this.giantEntity.getEyePosition();
        Vec2 dist = new Vec2((float)b.x - (float)a.x, (float)b.y - (float)a.y);
        dist = dist.normalized();
        double newAngle = Math.toDegrees(Math.atan2((double)dist.x, (double)dist.y));
        float lookX = (float)pendingTarget.getViewVector(1.0F).x;
        float lookZ = (float)pendingTarget.getViewVector(1.0F).y;
        double newLookAngle = Math.toDegrees(Math.atan2((double)lookX, (double)lookZ));
        double newNewAngle = this.loopAngle(newAngle - newLookAngle) + (double)fov;
        newNewAngle = this.loopAngle(newNewAngle);
        if (newNewAngle > 0.0 && newNewAngle < (double)(fov * 2.0F)) {
            yawPlayerLookingTowards = true;
        }

        boolean pitchPlayerLookingTowards = false;
        boolean shouldOnlyUsePitch = false;
        float yFov = fov * yFovMod;
        Vec2 yDist = new Vec2((float)Math.sqrt((b.x - a.x) * (b.x - a.x) + (b.y - a.y) * (b.y - a.y)), (float)(b.z - a.z));
        yDist = yDist.normalized();
        double yAngle = Math.toDegrees(Math.atan2((double)yDist.x, (double)yDist.y));
        float lookY = (float)pendingTarget.getViewVector(1.0F).z;
        Vec2 lookDist = new Vec2((float)Math.sqrt((double)(lookX * lookX + lookZ * lookZ)), lookY);
        lookDist = lookDist.normalized();
        double yLookAngle = Math.toDegrees(Math.atan2((double)lookDist.x, (double)lookDist.y));
        double newYAngle = this.loopAngle(yAngle - yLookAngle) + (double)yFov;
        newYAngle = this.loopAngle(newYAngle);
        if (newYAngle > 0.0 && newYAngle < (double)(yFov * 2.0F)) {
            pitchPlayerLookingTowards = true;
        }

        if (!(yLookAngle < (double)(180.0F - yFov)) || !(yLookAngle > (double)yFov)) {
            shouldOnlyUsePitch = true;
        }

        return (yawPlayerLookingTowards || shouldOnlyUsePitch) && pitchPlayerLookingTowards;
    }

    public boolean inPlayerLineOfSight() {
        LivingEntity pendingTarget = this.giantEntity.getTarget();
        return pendingTarget != null ? pendingTarget.is(this.giantEntity) : false;
    }

    public void tick() {
        this.tickStareClock();
        if (this.shouldLeave && (!this.isPlayerLookingTowards() || !this.inPlayerLineOfSight())) {
            this.giantEntity.goalSelector.addGoal(16, new RandomStrollGoal(this.giantEntity, 1.0D));
            this.giantEntity.goalSelector.addGoal(16, new GiantEntity.RunFromPlayerGoal<>(this.giantEntity, Player.class, 1000.0F, 1.0D, 1.0D));
        }
    }
}
