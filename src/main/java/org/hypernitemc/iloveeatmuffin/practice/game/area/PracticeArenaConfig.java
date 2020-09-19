package org.hypernitemc.iloveeatmuffin.practice.game.area;

import com.ericlam.mc.minigames.core.arena.ArenaConfig;
import com.google.common.collect.ImmutableMap;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.plugin.Plugin;
import org.hypernitemc.iloveeatmuffin.practice.Configs.LangConfig;
import org.hypernitemc.iloveeatmuffin.practice.Configs.PracticeConfig;
import org.hypernitemc.iloveeatmuffin.practice.Practice;

import javax.annotation.Nonnull;
import java.io.File;
import java.io.IOException;
import java.util.concurrent.CompletableFuture;

public class PracticeArenaConfig implements ArenaConfig {
    private final PracticeConfig practiceConfig;
    private final Plugin plugin;
    public PracticeArenaConfig(PracticeConfig practiceConfig, Plugin plugin) {
        this.practiceConfig = practiceConfig;
        this.plugin = plugin;
    }

    @Override
    public File getArenaFolder() {
        return new File(plugin.getDataFolder(), "Arenas");
    }

    @Override
    public int getMaxLoadArena() {
        return practiceConfig.maxLoadArenas;
    }

    @Override
    public void setExtraWorldSetting(@Nonnull World world) {

    }

    @Override
    public ImmutableMap<String, Integer> getAllowWarps() {
        return null;
    }

    @Override
    public Location getLobbyLocation() {
        return practiceConfig.lobbyLocation;
    }

    @Override
    public String getFallBackServer() {
        return practiceConfig.fallbackServer;
    }

    @Override
    public String getGamePrefix() {
        return Practice.getYamlManager().getConfigAs(LangConfig.class).getPrefix();
    }

    @Override
    public CompletableFuture<Boolean> setLobbyLocation(Location location) {
        return CompletableFuture.supplyAsync(() -> {
        practiceConfig.lobbyLocation = location;
        try {
            practiceConfig.save();
            return true;
        } catch (IOException e) {
            return false;
        }
    });
    }
}
