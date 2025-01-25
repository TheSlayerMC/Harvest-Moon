package net.sham.hmfomt.core;

import net.neoforged.neoforge.attachment.AttachmentType;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.NeoForgeRegistries;
import net.sham.hmfomt.common.capability.HarvestSprites;
import net.sham.hmfomt.common.capability.PlayerStats;
import net.sham.hmfomt.common.capability.WorldEvents;

public class HMDataAttachments {

    public static final DeferredRegister<AttachmentType<?>> REGISTRY = DeferredRegister.create(NeoForgeRegistries.Keys.ATTACHMENT_TYPES, HarvestMoon.MOD_ID);

    public static final DeferredHolder<AttachmentType<?>, AttachmentType<PlayerStats>> PLAYER_STATS = REGISTRY.register(
            "player_stats", () -> AttachmentType.serializable(PlayerStats::new).build());

    public static final DeferredHolder<AttachmentType<?>, AttachmentType<HarvestSprites>> HARVEST_SPRITES = REGISTRY.register(
            "harvest_sprites", () -> AttachmentType.serializable(HarvestSprites::new).build());

    public static final DeferredHolder<AttachmentType<?>, AttachmentType<WorldEvents>> WORLD_EVENTS = REGISTRY.register(
            "world_events", () -> AttachmentType.serializable(WorldEvents::new).build());
}
