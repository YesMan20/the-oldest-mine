package net.clockwork.the_oldest_mine.client.model;
// Made with Blockbench 4.9.4
// Exported for Minecraft version 1.17 or later with Mojang mappings
// Paste this class into your mod and generate all required imports


import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.world.entity.Entity;

public class GiantModel<T extends Entity> extends EntityModel<T> {
	private final ModelPart all;

	public GiantModel(ModelPart root) {
		this.all = root.getChild("all");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition all = partdefinition.addOrReplaceChild("all", CubeListBuilder.create().texOffs(0, 0).addBox(-11.5F, -49.0F, -8.0F, 22.0F, 43.0F, 15.0F, new CubeDeformation(0.0F))
		.texOffs(0, 86).addBox(-43.5F, -56.0F, 0.0F, 23.0F, 22.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(74, 18).addBox(16.0F, -49.4F, 0.0F, 23.0F, 22.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(0, 58).addBox(-7.5F, -13.0F, -7.0F, 14.0F, 15.0F, 13.0F, new CubeDeformation(0.0F))
		.texOffs(33, 119).addBox(-11.5F, -13.0F, -8.0F, 22.0F, 0.0F, 15.0F, new CubeDeformation(0.0F))
		.texOffs(5, 9).addBox(-6.5F, 1.0F, -6.0F, 1.0F, 3.0F, 3.0F, new CubeDeformation(0.0F))
		.texOffs(0, 6).addBox(4.5F, 1.0F, -6.0F, 1.0F, 3.0F, 3.0F, new CubeDeformation(0.0F))
		.texOffs(48, 107).addBox(-6.5F, 0.0F, -6.0F, 12.0F, 1.0F, 11.0F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(4.5F, 1.0F, 2.0F, 1.0F, 3.0F, 3.0F, new CubeDeformation(0.0F))
		.texOffs(5, 3).addBox(-6.5F, 1.0F, 2.0F, 1.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(0.5F, 20.0F, 0.0F));

		PartDefinition cube_r1 = all.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(74, 0).addBox(-14.0F, -9.5F, -4.0F, 20.0F, 10.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-15.0F, -28.5F, 0.0F, 0.0F, 0.0F, 0.5672F));

		PartDefinition cube_r2 = all.addOrReplaceChild("cube_r2", CubeListBuilder.create().texOffs(48, 89).addBox(-8.0F, -9.5F, -4.0F, 15.0F, 10.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(16.0F, -25.5F, 0.0F, 0.0F, 0.0F, -0.5672F));

		PartDefinition head = all.addOrReplaceChild("head", CubeListBuilder.create().texOffs(92, 41).addBox(-6.5F, 1.5F, -4.75F, 13.0F, 13.0F, 7.0F, new CubeDeformation(0.0F))
		.texOffs(54, 58).addBox(-6.5F, -17.5F, -4.75F, 13.0F, 19.0F, 12.0F, new CubeDeformation(0.0F))
		.texOffs(104, 61).addBox(-6.5F, -17.5F, -4.75F, 13.0F, 19.0F, 12.0F, new CubeDeformation(0.5F))
		.texOffs(132, 41).addBox(-6.5F, 1.5F, -4.75F, 13.0F, 13.0F, 7.0F, new CubeDeformation(0.5F)), PartPose.offsetAndRotation(-0.5F, -40.5F, -9.25F, 0.0F, 0.0F, 0.0436F));

		return LayerDefinition.create(meshdefinition, 256, 256);
	}

	@Override
	public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {

	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		all.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
	}
}