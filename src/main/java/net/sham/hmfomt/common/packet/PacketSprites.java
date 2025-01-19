package net.sham.hmfomt.common.packet;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.neoforged.neoforge.network.handling.IPayloadContext;
import net.sham.hmfomt.client.sprites.ClientHarvestSprites;
import net.sham.hmfomt.core.data.enums.EnumSprites;
import net.sham.hmfomt.core.HarvestMoon;

public record PacketSprites(float hearts, float waterXP, float harvestXP, float animalXP, EnumSprites sprite) implements CustomPacketPayload {

    public static final Type<PacketSprites> TYPE = new Type<>(HarvestMoon.rl("harvest_sprites"));

    public static PacketSprites decode(FriendlyByteBuf buffer) {
        return new PacketSprites(buffer.readFloat(), buffer.readFloat(), buffer.readFloat(), buffer.readFloat(), buffer.readEnum(EnumSprites.class));
    }

    public static final StreamCodec<RegistryFriendlyByteBuf, PacketSprites> STREAM_CODEC = CustomPacketPayload.codec(PacketSprites::write, PacketSprites::decode);

    private void write(FriendlyByteBuf buf) {
        buf.writeFloat(hearts);
        buf.writeFloat(waterXP);
        buf.writeFloat(harvestXP);
        buf.writeFloat(animalXP);
        buf.writeEnum(sprite);
    }

    public static void handle(PacketSprites payload, IPayloadContext context) {
        context.enqueueWork(() -> {
            ClientHarvestSprites.setClientHeartFloat(payload.sprite, payload.hearts);
            ClientHarvestSprites.setClientWaterXP(payload.sprite, payload.waterXP);
            ClientHarvestSprites.setClientHarvestXP(payload.sprite, payload.harvestXP);
            ClientHarvestSprites.setClientAnimalXP(payload.sprite, payload.animalXP);
        });
    }

    @Override
    public Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }
}
