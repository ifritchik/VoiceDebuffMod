package com.example.voicedebuffmod;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

import java.util.HashSet;
import java.util.Set;

public class PlayerDebuffHandler {
    private static final Set<MobEffect> BLOCKED_EFFECTS = new HashSet<>();

    static {
        // Дебаффы из конфига
        if (Config.WEAKNESS.get()) BLOCKED_EFFECTS.add(MobEffects.WEAKNESS);
        if (Config.BLINDNESS.get()) BLOCKED_EFFECTS.add(MobEffects.BLINDNESS);
        if (Config.DARKNESS.get()) BLOCKED_EFFECTS.add(MobEffects.DARKNESS);
    }

    @SubscribeEvent
    public void onPlayerTick(TickEvent.PlayerTickEvent event) {
        if (event.phase != TickEvent.Phase.END) return;

        Player player = event.player;
        boolean shouldMute = BLOCKED_EFFECTS.stream().anyMatch(player::hasEffect);

        if (shouldMute) {
            // Логика отключения голоса (пример для Simple Voice Chat API)
            try {
                Class<?> apiClass = Class.forName("de.maxhenkel.voicechat.api.ForgeVoicechatApi");
                Object api = apiClass.getMethod("getInstance").invoke(null);
                apiClass.getMethod("mutePlayer", java.util.UUID.class).invoke(api, player.getUUID());
            } catch (Exception e) {
                System.err.println("Не удалось отключить голос: " + e.getMessage());
            }
        }
    }
}
