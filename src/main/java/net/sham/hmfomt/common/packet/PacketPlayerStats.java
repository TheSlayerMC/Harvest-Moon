package net.sham.hmfomt.common.packet;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.neoforged.neoforge.network.handling.IPayloadContext;
import net.sham.hmfomt.client.stats.ClientPlayerStats;
import net.sham.hmfomt.core.HarvestMoon;
import net.sham.hmfomt.core.data.enums.EnumTools;

public record PacketPlayerStats(int sentacoins, boolean woke, float xp, int level, EnumTools tools) implements CustomPacketPayload {

    public static final Type<PacketPlayerStats> TYPE = new Type<>(HarvestMoon.rl("player_stats"));

    public static PacketPlayerStats decode(FriendlyByteBuf buffer) {
        return new PacketPlayerStats(buffer.readInt(), buffer.readBoolean(), buffer.readFloat(), buffer.readInt(), buffer.readEnum(EnumTools.class));
    }

    public static final StreamCodec<RegistryFriendlyByteBuf, PacketPlayerStats> STREAM_CODEC = CustomPacketPayload.codec(PacketPlayerStats::write, PacketPlayerStats::decode);

    private void write(FriendlyByteBuf buf) {
        buf.writeInt(sentacoins);
        buf.writeBoolean(woke);
        buf.writeFloat(xp);
        buf.writeInt(level);
        buf.writeEnum(tools);
    }

    public static void handle(PacketPlayerStats payload, IPayloadContext context) {
        context.enqueueWork(() -> {
            ClientPlayerStats.setSentacoins(payload.sentacoins);
            ClientPlayerStats.setWoke(payload.woke);
            ClientPlayerStats.setClientXP(payload.tools, payload.xp);
            ClientPlayerStats.setClientLevel(payload.tools, payload.level);
        });
    }

    @Override
    public Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }
}
