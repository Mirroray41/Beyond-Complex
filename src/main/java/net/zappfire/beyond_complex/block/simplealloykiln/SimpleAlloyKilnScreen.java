package net.zappfire.beyond_complex.block.simplealloykiln;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Vector3f;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;
import net.zappfire.beyond_complex.BeyondComplex;
import net.zappfire.beyond_complex.screen.HeatInfoArea;
import net.zappfire.beyond_complex.util.MouseUtil;

import java.util.Optional;

public class SimpleAlloyKilnScreen extends AbstractContainerScreen<SimpleAlloyKilnMenu> {
    private static final ResourceLocation TEXTURE =
            new ResourceLocation(BeyondComplex.MODID, "textures/gui/simple_alloy_kiln_gui.png");
    private HeatInfoArea heatInfoArea;

    public SimpleAlloyKilnScreen(SimpleAlloyKilnMenu p_97741_, Inventory p_97742_, Component p_97743_) {
        super(p_97741_, p_97742_, p_97743_);
    }

    @Override
    protected void init() {
        super.init();
        assignHeatInfoArea();
    }



    private void assignHeatInfoArea() {
        int x = (width - imageWidth) / 2;
        int y = (height - imageWidth) /2;
        heatInfoArea = new HeatInfoArea(x + 10, y + 13, menu.getBlockEntity().data.get(2), menu.getBlockEntity().data.get(3));
    }
    @Override
    protected void renderLabels(PoseStack pPoseStack, int pMouseX, int pMouseY) {
        int x = (width - imageWidth) / 2;
        int y = (height - imageHeight) / 2;

        renderHeatAreaTooltips(pPoseStack, pMouseX, pMouseY, x, y);
    }

    private void renderHeatAreaTooltips(PoseStack pPoseStack, int pMouseX, int pMouseY, int x, int y) {
        if (isMouseAboveArea(pMouseX, pMouseY, x, y, 10, 13, 8, 64)) {
            renderTooltip(pPoseStack, heatInfoArea.getTooltips(), Optional.empty(), pMouseX - x, pMouseY - y);
        }
    }

    @Override
    protected void renderBg(PoseStack poseStack, float v, int i, int i1) {
        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.setShaderColor(1.0f, 1.0f, 1.0f, 1.0f);
        RenderSystem.setShaderTexture(0,TEXTURE);
        int x = (width - imageWidth) / 2;
        int y = (height - imageHeight) / 2;

        this.blit(poseStack, x, y, 0, 0, imageWidth, imageHeight);

        renderProgressArrow(poseStack, x, y);
        renderHeatBar(poseStack, x, y);

    }
    private void renderHeatBar(PoseStack stack, int x, int y) {
        // pose stack, location x, location y, x loc on png, y loc on png, width, height

        blit(stack, x+8, y+69 - menu.getScaledHeat(), 176, 84 - menu.getScaledHeat(), 14, menu.getScaledHeat());
    }
    private void renderProgressArrow(PoseStack stack, int x, int y) {
        if(menu.isCrafting()) {
            blit(stack, x + 79, y + 35, 176, 14, menu.getScaledProgress(), 17);
        }
    }

    @Override
    public void render(PoseStack pPoseStack, int mouseX, int mouseY, float delta) {
        renderBackground(pPoseStack);
        super.render(pPoseStack,mouseX,mouseY,delta);
        renderTooltip(pPoseStack, mouseX, mouseY);
    }


    private boolean isMouseAboveArea(int pMouseX, int pMouseY, int x, int y, int offsetX, int offsetY, int width, int height) {
        return MouseUtil.isMouseOver(pMouseX, pMouseY, x + offsetX, y + offsetY, width, height);
    }
}
