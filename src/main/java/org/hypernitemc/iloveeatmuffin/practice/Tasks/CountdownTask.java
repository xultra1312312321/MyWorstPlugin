package org.hypernitemc.iloveeatmuffin.practice.Tasks;


import com.ericlam.mc.minigames.core.arena.Arena;
import com.ericlam.mc.minigames.core.factory.scoreboard.GameBoard;
import com.ericlam.mc.minigames.core.game.GameState;
import com.ericlam.mc.minigames.core.main.MinigamesCore;
import com.ericlam.mc.minigames.core.manager.PlayerManager;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.hypernitemc.iloveeatmuffin.practice.Configs.PracticeConfig;

import java.util.List;


public class CountdownTask extends PracticeTask {

    private boolean cancel = true;


    @Override
    public void initRun(PlayerManager playerManager) {
        Bukkit.broadcastMessage(msg.get("countdown-start"));
    }

    @Override
    public void onCancel() {
        Bukkit.broadcastMessage(msg.get("countdown-cancel"));
        playerManager.getWaitingPlayer().forEach(p -> p.getPlayer().setLevel(0));
    }

    @Override
    public void onFinish() {
        playerManager.getWaitingPlayer().forEach(g -> playerManager.setGamePlayer(g));
    }

    @Override
    public long run(long l) {
        if (l == 6) {
            MinigamesCore.getApi().getLobbyManager().runFinalResult();
            Arena arena = MinigamesCore.getApi().getArenaManager().getFinalArena();
            List<Location> spawns = arena.getWarp("game");
            MinigamesCore.getApi().getGameUtils().unLagIterate(spawns, location -> location.getChunk().load(true), 5L);

        }
        int boost = PracticeConfig.boostPlayers;
        long boostTime = PracticeConfig.boostTime;
        if (playerManager.getWaitingPlayer().size() >= boost && l > boostTime + 1) {
            Bukkit.broadcastMessage(msg.get("lobby-boost"));
            l -= boostTime;
        }
        int level = (int) l;
        playerManager.getWaitingPlayer().forEach(g -> g.getPlayer().setLevel(level));
        return l;
    }

    @Override
    public long getTotalTime() {
        return PracticeConfig.countDownTime;
    }

    @Override
    public boolean shouldCancel() {
        return cancel && playerManager.getWaitingPlayer().size() < PracticeConfig.requiredPlayers;
    }
}