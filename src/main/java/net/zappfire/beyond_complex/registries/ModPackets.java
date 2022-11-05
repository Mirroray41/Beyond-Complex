package net.zappfire.beyond_complex.registries;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.network.NetworkDirection;
import net.minecraftforge.network.NetworkRegistry;
import net.minecraftforge.network.PacketDistributor;
import net.minecraftforge.network.simple.SimpleChannel;
import net.zappfire.beyond_complex.BeyondComplex;
import net.zappfire.beyond_complex.networking.packet.EnergySyncS2CPacket;
import net.zappfire.beyond_complex.networking.packet.FluidSyncS2CPacket;

public class ModPackets {
    private static SimpleChannel INSTANCE;
    private static int packedId = 0;
    private static int id() {
        return packedId++;
    }

    public static void register() {
        SimpleChannel net = NetworkRegistry.ChannelBuilder.named(new ResourceLocation(BeyondComplex.MODID, "packets"))
                .networkProtocolVersion(() -> "1.0").clientAcceptedVersions(s -> true).serverAcceptedVersions(s -> true).simpleChannel();
        INSTANCE = net;
        /* Register Packets here */
        net.messageBuilder(EnergySyncS2CPacket.class, id(), NetworkDirection.PLAY_TO_CLIENT)
                .decoder(EnergySyncS2CPacket::new).encoder(EnergySyncS2CPacket::toBytes).consumerMainThread(EnergySyncS2CPacket::handle).add();

        net.messageBuilder(FluidSyncS2CPacket.class, id(), NetworkDirection.PLAY_TO_CLIENT)
                .decoder(FluidSyncS2CPacket::new).encoder(FluidSyncS2CPacket::toBytes).consumerMainThread(FluidSyncS2CPacket::handle).add();


    }

    public static <MSG> void sendToServer(MSG message) {
        INSTANCE.sendToServer(message);
    }

    public static <MSG> void sendToPlayer(MSG message, ServerPlayer player) {
        INSTANCE.send(PacketDistributor.PLAYER.with(() -> player), message);
    }

    public static <MSG> void sendToClients(MSG message) {
        INSTANCE.send(PacketDistributor.ALL.noArg(), message);
    }
}
