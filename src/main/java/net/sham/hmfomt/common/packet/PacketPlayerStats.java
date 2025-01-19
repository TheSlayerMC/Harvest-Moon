package net.sham.hmfomt.common.packet;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.neoforged.neoforge.network.handling.IPayloadContext;
import net.sham.hmfomt.client.stats.ClientPlayerStats;
import net.sham.hmfomt.core.HarvestMoon;
import net.sham.hmfomt.core.data.enums.EnumTools;

public record PacketPlayerStats(int sentacoins, float copper, float silver, float gold, float mythril, EnumTools tools) implements CustomPacketPayload {

    public static final Type<PacketPlayerStats> TYPE = new Type<>(HarvestMoon.rl("player_stats"));

    public static PacketPlayerStats decode(FriendlyByteBuf buffer) {
        return new PacketPlayerStats(buffer.readInt(), buffer.readFloat(), buffer.readFloat(), buffer.readFloat(), buffer.readFloat(), buffer.readEnum(EnumTools.class));
    }

    public static final StreamCodec<RegistryFriendlyByteBuf, PacketPlayerStats> STREAM_CODEC = CustomPacketPayload.codec(PacketPlayerStats::write, PacketPlayerStats::decode);

    private void write(FriendlyByteBuf buf) {
        buf.writeInt(sentacoins);
        buf.writeFloat(copper);
        buf.writeFloat(silver);
        buf.writeFloat(gold);
        buf.writeFloat(mythril);
        buf.writeEnum(tools);
    }

    public static void handle(PacketPlayerStats payload, IPayloadContext context) {
        context.enqueueWork(() -> {
            ClientPlayerStats.setSentacoins(payload.sentacoins);
            ClientPlayerStats.setClientCopperXP(payload.tools, payload.copper);
            ClientPlayerStats.setClientSilverXP(payload.tools, payload.silver);
            ClientPlayerStats.setClientGoldXP(payload.tools, payload.gold);
            ClientPlayerStats.setClientMythrilXP(payload.tools, payload.mythril);
        });
    }

    @Override
    public Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }
}
