package org.hypernitemc.iloveeatmuffin.practice;

import com.ericlam.mc.minigames.core.arena.Arena;
import com.ericlam.mc.minigames.core.event.section.GamePreStartEvent;
import com.ericlam.mc.minigames.core.factory.scoreboard.GameBoard;
import com.ericlam.mc.minigames.core.game.InGameState;
import com.ericlam.mc.minigames.core.main.MinigamesCore;
import com.ericlam.mc.minigames.core.registable.Compulsory;
import com.ericlam.mc.minigames.core.registable.Voluntary;
import com.hypernite.mc.hnmc.core.builders.InventoryBuilder;
import com.hypernite.mc.hnmc.core.main.HyperNiteMC;
import com.hypernite.mc.hnmc.core.managers.YamlManager;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;
import org.hypernitemc.iloveeatmuffin.practice.Commands.PracticeArenaCommand;
import org.hypernitemc.iloveeatmuffin.practice.Configs.LangConfig;
import org.hypernitemc.iloveeatmuffin.practice.Configs.MotdConfig;
import org.hypernitemc.iloveeatmuffin.practice.Configs.PracticeConfig;
import org.hypernitemc.iloveeatmuffin.practice.Manager.KitManager;
import org.hypernitemc.iloveeatmuffin.practice.Tasks.*;
import org.hypernitemc.iloveeatmuffin.practice.game.area.PracticeArenaMechanic;
import org.hypernitemc.iloveeatmuffin.practice.game.handler.PracticePlayerHandler;
import org.hypernitemc.iloveeatmuffin.practice.game.area.PracticeArenaConfig;
import org.hypernitemc.iloveeatmuffin.practice.game.handler.PracticeStatsHandler;

public final class Practice extends JavaPlugin implements Listener {
    private static YamlManager yamlManager;
    private KitManager kitmanager;
    private InGameState peaceState;
    private GameBoard gameBoard;



    public static YamlManager getYamlManager() {
        return yamlManager;
    }

    public KitManager getKitManager() {
        return kitmanager;
    }

    public boolean isPeaceState(InGameState state) {
        return state == peaceState;
    }

    public GameBoard getGameBoard() {
        return gameBoard;
    }



    @Override
    public void onEnable() {
        yamlManager = HyperNiteMC.getAPI().getFactory().getConfigFactory(this)
                .register("config.yml", PracticeConfig.class)
                .register("lang.yml", LangConfig.class)
                .register("motd.yml", MotdConfig.class)
                .dump();
        final PracticeConfig practiceConfig = yamlManager.getConfigAs(PracticeConfig.class);
        MotdConfig motd = yamlManager.getConfigAs(MotdConfig.class);
        peaceState = new InGameState("peace", motd.peace);
        this.getServer().getPluginManager().registerEvents(this, this);
        Compulsory compulsory = MinigamesCore.getRegistration().getCompulsory();
        compulsory.registerGamePlayerHandler(new PracticePlayerHandler());
        compulsory.registerGameStatsHandler(new PracticeStatsHandler());
        compulsory.registerArenaConfig(new PracticeArenaConfig(practiceConfig, this));
        compulsory.registerArenaMechanic(new PracticeArenaMechanic());
        compulsory.registerArenaCommand(new PracticeArenaCommand(), this);
        compulsory.registerVoteGUI(new InventoryBuilder(3, "&9地圖投票").ring(new ItemStack(Material.YELLOW_STAINED_GLASS_PANE)), 11, 13, 15);
        compulsory.registerLobbyTask(new CountdownTask());
        compulsory.registerEndTask(new PreEndTask());
        Voluntary voluntary = MinigamesCore.getRegistration().getVoluntary();
        voluntary.registerGameTask(new InGameState("preStart", motd.preStart), new PreStartTask());
        voluntary.registerGameTask(peaceState, new PeaceTask());
        voluntary.registerGameTask(new InGameState("starting", motd.starting), new InGameTask());

    }

    @Override
    public void onDisable() {
        super.onDisable();
    }
    @EventHandler
    public void onGamePreStart(GamePreStartEvent e) {
        Arena arena = MinigamesCore.getApi().getArenaManager().getFinalArena();
        if (arena == null) return;
        gameBoard = MinigamesCore.getProperties().getGameFactory().getScoreboardFactory().setTitle(arena.getDisplayName())
                .addLine("&f ", 12)
                .setLine("stats", "&7遊戲狀態: &f", 11)
                .addLine("&b ", 10)
                .setLine("game", "&e存活者: &f", 9)
                .setLine("spec", "&e觀戰者: &f", 8)
                .addLine("&e ", 7)
                .addLine("&r&8&l&m-----------", 6)
                .build();
        e.getGamingPlayer().forEach(gameBoard::addPlayer);

    }
}


