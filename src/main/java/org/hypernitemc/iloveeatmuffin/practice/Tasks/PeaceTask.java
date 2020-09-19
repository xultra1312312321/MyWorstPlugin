package org.hypernitemc.iloveeatmuffin.practice.Tasks;


import com.ericlam.mc.minigames.core.factory.scoreboard.GameBoard;
import com.ericlam.mc.minigames.core.main.MinigamesCore;
import com.ericlam.mc.minigames.core.manager.PlayerManager;
import org.bukkit.Bukkit;
import org.hypernitemc.iloveeatmuffin.practice.Configs.PracticeConfig;
import org.hypernitemc.iloveeatmuffin.practice.Practice;

public class PeaceTask extends PracticeTask {

    private GameBoard gameBoard;


    @Override
    public void initRun(PlayerManager playerManager) {
        Bukkit.broadcastMessage(msg.get("start"));
        gameBoard = Practice.getPlugin(Practice.class).getGameBoard();
        gameBoard.setLine("stats", "&7遊戲狀態: " + motdConfig.peace);
    }

    @Override
    public void onCancel() {
        MinigamesCore.getApi().getGameManager().endGame(playerManager.getGamePlayer(), null, true);
    }

    @Override
    public void onFinish() {
        Bukkit.broadcastMessage(msg.get("peace-finish"));
    }

    @Override
    public long run(long l) {
        return 0;
    }

    @Override
    public long getTotalTime() {
        return PracticeConfig.peaceTime;
    }

    @Override
    public boolean shouldCancel() {
        return playerManager.getGamePlayer().size() <= 1;
    }
}

