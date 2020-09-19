package org.hypernitemc.iloveeatmuffin.practice.Tasks;


import com.ericlam.mc.minigames.core.arena.Arena;
import com.ericlam.mc.minigames.core.factory.scoreboard.GameBoard;
import com.ericlam.mc.minigames.core.game.GameState;
import com.ericlam.mc.minigames.core.main.MinigamesCore;
import com.ericlam.mc.minigames.core.manager.PlayerManager;
import org.hypernitemc.iloveeatmuffin.practice.Practice;


public class InGameTask extends PracticeTask {

    private GameBoard gameBoard;
    private Arena arena;


    @Override
    public void initRun(PlayerManager playerManager) {
        MinigamesCore.getApi().getGameManager().setState(GameState.IN_GAME);
        arena = MinigamesCore.getApi().getArenaManager().getFinalArena();
        gameBoard = Practice.getPlugin(Practice.class).getGameBoard();
        gameBoard.setLine("stats", "&7遊戲狀態: ".concat(motdConfig.starting));
    }

    @Override
    public void onCancel() {
        MinigamesCore.getApi().getGameManager().endGame(playerManager.getGamePlayer(), null, true);
    }

    @Override
    public void onFinish() {

    }

    @Override
    public long run(long remain) {
        return 0;
    }

    @Override
    public long getTotalTime() {
        return practiceConfig.gameTime;
    }

    @Override
    public boolean shouldCancel() {
        return playerManager.getGamePlayer().size() <= 1;
    }
}
