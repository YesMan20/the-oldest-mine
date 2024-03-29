package com.yesman_pancakes.the_oldest_mine.client.model;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.world.entity.Entity;

public class GiantModel<T extends Entity> extends EntityModel<T> {
	private final ModelPart body;

	public GiantModel(ModelPart root) {
		this.body = root.getChild("body");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition body = partdefinition.addOrReplaceChild("body", CubeListBuilder.create().texOffs(0, 0).addBox(-10.5F, -41.0F, -5.0F, 21.0F, 36.0F, 16.0F, new CubeDeformation(0.01F))
				.texOffs(182, 99).addBox(-10.5F, -11.0F, -5.0F, 21.0F, 1.0F, 16.0F, new CubeDeformation(0.0F))
				.texOffs(115, 3).addBox(-7.0F, -16.0F, -4.0F, 14.0F, 15.0F, 13.0F, new CubeDeformation(0.5F))
				.texOffs(159, 105).addBox(-3.0F, -9.5F, 19.2F, 6.0F, 5.0F, 1.0F, new CubeDeformation(0.15F))
				.texOffs(159, 105).addBox(-3.0F, -9.5F, 14.0F, 6.0F, 5.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(111, 121).addBox(0.0F, -7.5F, 7.0F, 1.0F, 1.0F, 13.0F, new CubeDeformation(0.0F))
				.texOffs(0, 8).addBox(5.0F, -1.0F, 5.0F, 2.0F, 4.0F, 4.0F, new CubeDeformation(0.0F))
				.texOffs(74, 16).addBox(5.0F, -1.0F, -4.0F, 2.0F, 4.0F, 4.0F, new CubeDeformation(0.0F))
				.texOffs(39, 52).addBox(-7.0F, -1.0F, -4.0F, 2.0F, 4.0F, 4.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(-7.0F, -1.0F, 5.0F, 2.0F, 4.0F, 4.0F, new CubeDeformation(0.0F))
				.texOffs(244, 16).addBox(4.0F, -5.0F, -4.0F, 2.0F, 4.0F, 4.0F, new CubeDeformation(0.0F))
				.texOffs(170, 8).addBox(4.0F, -5.0F, 5.0F, 2.0F, 4.0F, 4.0F, new CubeDeformation(0.0F))
				.texOffs(209, 52).addBox(-8.0F, -5.0F, -4.0F, 2.0F, 4.0F, 4.0F, new CubeDeformation(0.0F))
				.texOffs(170, 0).addBox(-8.0F, -5.0F, 5.0F, 2.0F, 4.0F, 4.0F, new CubeDeformation(0.0F))
				.texOffs(244, 16).addBox(4.0F, -9.0F, -4.0F, 2.0F, 4.0F, 4.0F, new CubeDeformation(0.0F))
				.texOffs(170, 8).addBox(4.0F, -9.0F, 5.0F, 2.0F, 4.0F, 4.0F, new CubeDeformation(0.0F))
				.texOffs(209, 52).addBox(-8.0F, -9.0F, -4.0F, 2.0F, 4.0F, 4.0F, new CubeDeformation(0.0F))
				.texOffs(170, 0).addBox(-8.0F, -9.0F, 5.0F, 2.0F, 4.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 21.0F, -3.0F));

		PartDefinition cube_r1 = body.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(58, 0).addBox(-1.8745F, -3.4184F, -4.25F, 22.0F, 8.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-18.9504F, -36.346F, 2.25F, 0.0F, 0.0F, 1.0472F));

		PartDefinition cube_r2 = body.addOrReplaceChild("cube_r2", CubeListBuilder.create().texOffs(50, 79).addBox(-20.5351F, -10.5927F, -0.25F, 23.0F, 24.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-18.9504F, -36.346F, 2.25F, 0.0F, 0.0F, 1.2654F));

		PartDefinition cube_r3 = body.addOrReplaceChild("cube_r3", CubeListBuilder.create().texOffs(0, 85).addBox(-4.4676F, -13.2174F, -0.25F, 23.0F, 24.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(19.1967F, -30.6867F, 2.25F, 0.0F, 0.0F, -0.6109F));

		PartDefinition cube_r4 = body.addOrReplaceChild("cube_r4", CubeListBuilder.create().texOffs(90, 96).addBox(-14.073F, -4.9536F, -4.25F, 14.0F, 8.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(19.1967F, -30.6867F, 2.25F, 0.0F, 0.0F, -0.9163F));

		PartDefinition head = body.addOrReplaceChild("head", CubeListBuilder.create().texOffs(50, 52).addBox(-6.5F, -13.2F, -6.7F, 13.0F, 16.0F, 11.0F, new CubeDeformation(0.5F))
				.texOffs(89, 36).addBox(-6.5F, 3.8F, -6.7F, 13.0F, 9.0F, 11.0F, new CubeDeformation(0.5F)), PartPose.offsetAndRotation(0.0F, -38.8F, 0.0F, 0.0F, 0.0F, 0.0873F));

		return LayerDefinition.create(meshdefinition, 256, 256);
	}

	@Override
	public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {

	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		body.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
	}
}