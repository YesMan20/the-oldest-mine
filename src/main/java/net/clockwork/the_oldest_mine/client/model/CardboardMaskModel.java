package net.clockwork.the_oldest_mine.client.model;

import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;

public class CardboardMaskModel extends HumanoidModel {

	public CardboardMaskModel(ModelPart root) {
		super(root);
	}

	public static LayerDefinition createArmorLayer(CubeDeformation deformation) {
		MeshDefinition meshdefinition = HumanoidModel.createMesh(deformation, 0.0F);
		PartDefinition partdefinition = meshdefinition.getRoot();
		PartDefinition head = partdefinition.getChild("head");

		head.addOrReplaceChild("helmet", CubeListBuilder.create().texOffs(11, 31).addBox(-5.5F, -29.0F, -4.7F, 11.0F, 11.0F, 1.0F, deformation)
				.texOffs(11, 43).addBox(-4.5F, -18.0F, -4.7F, 9.0F, 4.0F, 1.0F, deformation)
				.texOffs(11, 20).addBox(-4.5F, -24.0F, -3.7F, 9.0F, 3.0F, 8.0F, deformation), PartPose.offset(0.0F, 18.0F, 0.0F));


		return LayerDefinition.create(meshdefinition, 64, 64);
	}
}