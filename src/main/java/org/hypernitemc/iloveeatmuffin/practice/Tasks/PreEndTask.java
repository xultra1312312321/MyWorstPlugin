package org.hypernitemc.iloveeatmuffin.practice.Tasks;


import com.ericlam.mc.minigames.core.arena.Arena;
import com.ericlam.mc.minigames.core.factory.scoreboard.GameBoard;
import com.ericlam.mc.minigames.core.game.GameState;
import com.ericlam.mc.minigames.core.main.MinigamesCore;
import com.ericlam.mc.minigames.core.manager.FireWorkManager;
import com.ericlam.mc.minigames.core.manager.PlayerManager;
import org.bukkit.Bukkit;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Firework;
import org.bukkit.entity.Player;
import org.hypernitemc.iloveeatmuffin.practice.Practice;

public class PreEndTask extends PracticeTask {

    private Player survivor;
    private FireWorkManager fireWorkManager;
    private Arena arena;
    private GameBoard gameBoard;

    @Override
    public void initRun(PlayerManager playerManager) {
        Bukkit.getOnlinePlayers().forEach(p -> p.setLevel(0));
        playerManager.getGamePlayer().forEach(p -> p.getPlayer().getInventory().clear());
        this.fireWorkManager = MinigamesCore.getApi().getFireWorkManager();
        arena = MinigamesCore.getApi().getArenaManager().getFinalArena();
        fireWorkManager.spawnFireWork(arena.getWarp("game"));
        this.survivor = playerManager.getGamePlayer().size() == 1 ? playerManager.getGamePlayer().get(0).getPlayer() : null;
        if (survivor != null) {
            if (survivor.isOnline()) survivor.sendTitle(msg.getPure("win-title"), "", 20, 100, 20);
            double win = practiceConfig.rewardWins;

        }
        Bukkit.broadcastMessage(msg.get("game-end").replace("<arena>", arena.getDisplayName()).replace("<player>", (survivor == null ? "無" : survivor.getDisplayName())));

        MinigamesCore.getApi().getGameStatsManager().saveAll().whenComplete((v, ex) -> {
            if (ex != null) {
                ex.printStackTrace();
                return;
            }

            Practice.getProvidingPlugin(Practice.class).getLogger().info("All player stats data has been saved");
        });
        gameBoard = Practice.getPlugin(Practice.class).getGameBoard();
        gameBoard.setTitle("&b遊戲已完結 &f- 00:00");
        gameBoard.setLine("stats", "&7遊戲狀態: &b已完結");
    }

    @Override
    public void onCancel() {
        this.onFinish();
    }

    @Override
    public void onFinish() {
        gameBoard.destroy();
        arena.getWorld().getEntitiesByClasses(Firework.class).forEach(Entity::remove);
        MinigamesCore.getApi().getGameManager().setState(GameState.ENDED);
    }

    @Override
    public long run(long l) {
        if (l % 3 == 0) {
            if (survivor != null) fireWorkManager.spawnFireWork(survivor);
        }
        gameBoard.setLine("game", "&e存活者: &f".concat(playerManager.getGamePlayer().size() + ""));
        gameBoard.setLine("spec", "&e觀戰者: &f".concat(playerManager.getSpectators().size() + ""));
        return l;
    }

    @Override
    public long getTotalTime() {
        return 10;
    }

    @Override
    public boolean shouldCancel() {
        return false;
    }
}