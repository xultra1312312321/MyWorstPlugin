package org.hypernitemc.iloveeatmuffin.practice.game.handler;

import com.ericlam.mc.minigames.core.gamestats.GameStats;
import com.ericlam.mc.minigames.core.gamestats.GameStatsEditor;
import com.ericlam.mc.minigames.core.gamestats.GameStatsHandler;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

import javax.annotation.Nonnull;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

public class PracticeStatsHandler implements GameStatsHandler {
    @Nonnull
    @Override
    public GameStatsEditor loadGameStatsData(@Nonnull Player player) {
        return null;
    }

    @Override
    public CompletableFuture<Void> saveGameStatsData(OfflinePlayer player, GameStats gameStats) {
        return null;
    }

    @Override
    public CompletableFuture<Void> saveGameStatsData(Map<OfflinePlayer, GameStats> gameStatsMap) {
        return null;
    }
}
