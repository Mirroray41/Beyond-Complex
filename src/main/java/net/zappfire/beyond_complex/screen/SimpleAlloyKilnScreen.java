package net.zappfire.beyond_complex.screen;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;
import net.zappfire.beyond_complex.BeyondComplex;
import net.zappfire.beyond_complex.block.advanced.SimpleAlloyKiln;
import net.zappfire.beyond_complex.block.entity.advanced.SimpleAlloyKilnEntity;
import net.zappfire.beyond_complex.recipe.SimpleAlloyKilnRecipe;

public class SimpleAlloyKilnScreen extends AbstractContainerScreen<SimpleAlloyKilnMenu> {
    private static final ResourceLocation TEXTURE =
            new ResourceLocation(BeyondComplex.MODID, "textures/gui/simple_alloy_kiln_gui.png");

    public SimpleAlloyKilnScreen(SimpleAlloyKilnMenu p_97741_, Inventory p_97742_, Component p_97743_) {
        super(p_97741_, p_97742_, p_97743_);
    }

    @Override
    protected void renderBg(PoseStack poseStack, float v, int i, int i1) {
        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.setShaderColor(1.0f, 1.0f, 1.0f, 1.0f);
        RenderSystem.setShaderTexture(0,TEXTURE);
        int x = (width - imageWidth) / 2;
        int y = (height - imageHeight) / 2;

        this.blit(poseStack, x, y, 0, 0, imageWidth, imageHeight);

        this.blit(poseStack, x, y, 0, 0, imageWidth, imageHeight);



        if(menu.isCrafting()) {
            blit(poseStack, x + 79, y + 35, 176, 14, menu.getScaledProgress(), 17);
        }
    }

    @Override
    public void render(PoseStack pPoseStack, int mouseX, int mouseY, float delta) {
        renderBackground(pPoseStack);
        super.render(pPoseStack,mouseX,mouseY,delta);
        renderTooltip(pPoseStack, mouseX, mouseY);
    }
}
