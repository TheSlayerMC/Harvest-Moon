package net.sham.hmfomt.core.data;

import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.server.level.ServerPlayer;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.network.PacketDistributor;
import net.neoforged.neoforge.network.event.RegisterPayloadHandlersEvent;
import net.neoforged.neoforge.network.registration.PayloadRegistrar;
import net.sham.hmfomt.common.packet.PacketPlayerStats;
import net.sham.hmfomt.common.packet.PacketSprites;
import net.sham.hmfomt.common.packet.PacketWorldEvents;
import net.sham.hmfomt.core.HarvestMoon;

public class HMNetworkRegistry {

    public static void init(IEventBus eventBus) {
        eventBus.addListener(HMNetworkRegistry::registerPackets);
    }

    private static void registerPackets(final RegisterPayloadHandlersEvent ev) {
        PayloadRegistrar registry = ev.registrar(HarvestMoon.MOD_ID);

        registry.playBidirectional(PacketPlayerStats.TYPE, PacketPlayerStats.STREAM_CODEC, PacketPlayerStats::handle);
        registry.playBidirectional(PacketSprites.TYPE, PacketSprites.STREAM_CODEC, PacketSprites::handle);
        registry.playBidirectional(PacketWorldEvents.TYPE, PacketWorldEvents.STREAM_CODEC, PacketWorldEvents::handle);
    }

    public static void sendToPlayer(ServerPlayer player, CustomPacketPayload packet) {
        PacketDistributor.sendToPlayer(player, packet);
    }
}
