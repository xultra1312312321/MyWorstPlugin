package org.hypernitemc.iloveeatmuffin.practice.game.area;

import com.ericlam.mc.minigames.core.arena.Arena;
import com.ericlam.mc.minigames.core.arena.ArenaMechanic;
import com.ericlam.mc.minigames.core.arena.CreateArena;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import javax.annotation.Nonnull;

public class PracticeArenaMechanic implements ArenaMechanic {
    @Override
    public CreateArena loadCreateArena(FileConfiguration fileConfiguration, Arena arena) {
        return new ArenaCreate(arena.getAuthor(), arena.getArenaName(), arena.getDisplayName(), arena.getLocationsMap(), arena.getWorld(), arena.getDescription());
    }

    @Override
    public CreateArena createArena(@Nonnull String name, @Nonnull Player player) {
        return null;
    }

    @Override
    public void saveExtraArenaSetting(FileConfiguration preSaveYml, Arena arena) {

    }
}
