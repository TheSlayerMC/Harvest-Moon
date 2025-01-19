package net.sham.hmfomt.core.data;

import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.event.config.ModConfigEvent;
import net.neoforged.neoforge.common.ModConfigSpec;
import net.sham.hmfomt.core.HarvestMoon;

public class Config {

    private static final ModConfigSpec.Builder BUILDER = new ModConfigSpec.Builder();
    public static final ModConfigSpec SPEC;
    public static final ModConfigSpec.BooleanValue UPDATE_MESSAGE;

    static {
        BUILDER.push("Client configs for JITL");

        UPDATE_MESSAGE = BUILDER
                .comment("If set to 'true', you will get a message on every login with an update check.")
                .define("Send Player update Messages: ", true);

        BUILDER.pop();
        SPEC = BUILDER.build();
    }
    @SubscribeEvent
    static void onLoad(final ModConfigEvent event) {

    }
}
