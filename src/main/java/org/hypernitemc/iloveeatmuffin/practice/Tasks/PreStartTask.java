package org.hypernitemc.iloveeatmuffin.practice.Tasks;


import com.ericlam.mc.minigames.core.arena.Arena;
import com.ericlam.mc.minigames.core.factory.scoreboard.GameBoard;
import com.ericlam.mc.minigames.core.game.GameState;
import com.ericlam.mc.minigames.core.main.MinigamesCore;
import com.ericlam.mc.minigames.core.manager.PlayerManager;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.hypernitemc.iloveeatmuffin.practice.Configs.PracticeConfig;
import org.hypernitemc.iloveeatmuffin.practice.Practice;

import java.util.List;

public class PreStartTask extends PracticeTask {

    private GameBoard gameBoard;

    @Override
    public void initRun(PlayerManager playerManager) {
        MinigamesCore.getApi().getGameManager().setState(GameState.PRESTART);
        Arena arena = MinigamesCore.getApi().getArenaManager().getFinalArena();
        List<Location> spawns = arena.getWarp("game");
        MinigamesCore.getApi().getGameUtils().noLagTeleport(playerManager.getGamePlayer(), spawns, 2L);
        gameBoard = Practice.getPlugin(Practice.class).getGameBoard();
        gameBoard.setLine("stats", "&7遊戲狀態: ".concat(motdConfig.preStart));

    }

    @Override
    public void onCancel() {
        MinigamesCore.getApi().getGameManager().endGame(playerManager.getGamePlayer(), null, true);
    }

    @Override
    public void onFinish() {
        playerManager.getGamePlayer().forEach(g -> {
            Player player = g.getPlayer();
            player.sendTitle("", msg.getPure("start-title"), 0, 30, 20);
        });
    }

    @Override
    public long run(long remain) {
        return 0;
    }

    @Override
    public long getTotalTime() {
        return PracticeConfig.preStartTime;
    }

    @Override
    public boolean shouldCancel() {
        return playerManager.getGamePlayer().size() <= 1;
    }
}

