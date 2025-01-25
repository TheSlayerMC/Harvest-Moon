package net.sham.hmfomt.common.packet;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.neoforged.neoforge.network.handling.IPayloadContext;
import net.sham.hmfomt.client.world.ClientWorldEvents;
import net.sham.hmfomt.core.HarvestMoon;
import net.sham.hmfomt.core.data.enums.Seasons;

public record PacketWorldEvents(int ticks, int currentSeason, int weekDay, int monthDay) implements CustomPacketPayload {

    public static final Type<PacketWorldEvents> TYPE = new Type<>(HarvestMoon.rl("world_events"));

    public static PacketWorldEvents decode(FriendlyByteBuf buffer) {
        return new PacketWorldEvents(buffer.readInt(), buffer.readInt(), buffer.readInt(), buffer.readInt());
    }

    public static final StreamCodec<RegistryFriendlyByteBuf, PacketWorldEvents> STREAM_CODEC = CustomPacketPayload.codec(PacketWorldEvents::write, PacketWorldEvents::decode);

    private void write(FriendlyByteBuf buf) {
        buf.writeInt(ticks);
        buf.writeInt(currentSeason);
        buf.writeInt(weekDay);
        buf.writeInt(monthDay);
    }

    public static void handle(PacketWorldEvents payload, IPayloadContext context) {
        context.enqueueWork(() -> {
            ClientWorldEvents.setTickTimer(payload.ticks);
            ClientWorldEvents.setCurrentSeason(payload.currentSeason);
            ClientWorldEvents.setCurrentDay(payload.weekDay);
            ClientWorldEvents.setCurrentDayOfMonth(payload.monthDay);
        });
    }

    @Override
    public Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }
}
