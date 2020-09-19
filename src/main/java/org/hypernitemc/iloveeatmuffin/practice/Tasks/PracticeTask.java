package org.hypernitemc.iloveeatmuffin.practice.Tasks;

import com.ericlam.mc.minigames.core.SectionTask;
import com.ericlam.mc.minigames.core.manager.PlayerManager;
import com.hypernite.mc.hnmc.core.managers.YamlManager;
import org.hypernitemc.iloveeatmuffin.practice.Configs.LangConfig;
import org.hypernitemc.iloveeatmuffin.practice.Configs.MotdConfig;
import org.hypernitemc.iloveeatmuffin.practice.Configs.PracticeConfig;
import org.hypernitemc.iloveeatmuffin.practice.Practice;

public abstract class PracticeTask implements SectionTask {
    YamlManager configManager;
    PlayerManager playerManager;
    PracticeConfig practiceConfig;
    MotdConfig motdConfig;
    LangConfig msg;
    private boolean running;

    public PracticeTask() {
        this.configManager = Practice.getYamlManager();
        this.practiceConfig = configManager.getConfigAs(PracticeConfig.class);
        this.motdConfig = configManager.getConfigAs(MotdConfig.class);
        this.msg = configManager.getConfigAs(LangConfig.class);
        this.running = false;
    }

    @Override
    public void initTimer(PlayerManager playerManager) {
        this.playerManager = playerManager;
        this.initRun(playerManager);
    }

    public abstract void initRun(PlayerManager playerManager);

    @Override
    public boolean isRunning() {
        return running;
    }

    @Override
    public void setRunning(boolean running) {
        this.running = running;
    }
}
