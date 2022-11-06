package net.zappfire.beyond_complex.screen;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.Rect2i;
import net.minecraft.network.chat.Component;
import net.minecraftforge.energy.IEnergyStorage;
import net.zappfire.beyond_complex.util.InfoArea;

import java.util.List;


/*
 *  BluSunrize
 *  Copyright (c) 2021
 *
 *  This code is licensed under "Blu's License of Common Sense"
 *  Details can be found in the license file in the root folder of this project
 */
public class HeatInfoArea extends InfoArea {
    private final int temp;
    private final int maxTemp;

    public HeatInfoArea(int xMin, int yMin)  {
        this(xMin, yMin, 0, 0,8,64);
    }

    public HeatInfoArea(int xMin, int yMin, int temp, int maxTemp)  {
        this(xMin, yMin, temp, maxTemp,8,64);
    }

    public HeatInfoArea(int xMin, int yMin, int temp, int maxTemp, int width, int height)  {
        super(new Rect2i(xMin, yMin, width, height));
        this.temp = temp;
        this.maxTemp = maxTemp;
    }

   public List<Component> getTooltips() {
        return List.of(Component.literal(temp+ "/"+maxTemp+" C"));
   }

    @Override
    public void draw(PoseStack transform) {
        final int height = area.getHeight();
        int stored = (int)(height*(temp/(float) maxTemp));
        fillGradient(
                transform,
                area.getX(), area.getY()+(height-stored),
                area.getX() + area.getWidth(), area.getY() +area.getHeight(),
                0xffb51500, 0xff600b00
        );
    }
}
