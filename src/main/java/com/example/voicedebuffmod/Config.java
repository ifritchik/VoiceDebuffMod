package com.example.voicedebuffmod;

import net.minecraftforge.common.ForgeConfigSpec;

public class Config {
    public static final ForgeConfigSpec.Builder BUILDER = new ForgeConfigSpec.Builder();
    public static final ForgeConfigSpec SPEC;

    public static final ForgeConfigSpec.BooleanValue WEAKNESS;
    public static final ForgeConfigSpec.BooleanValue BLINDNESS;
    public static final ForgeConfigSpec.BooleanValue DARKNESS;

    static {
        BUILDER.push("Voice Debuff Mod Config");

        WEAKNESS = BUILDER.comment("Отключать голос при Weakness").define("weakness", true);
        BLINDNESS = BUILDER.comment("Отключать голос при Blindness").define("blindness", true);
        DARKNESS = BUILDER.comment("Отключать голос при Darkness").define("darkness", true);

        BUILDER.pop();
        SPEC = BUILDER.build();
    }
}
