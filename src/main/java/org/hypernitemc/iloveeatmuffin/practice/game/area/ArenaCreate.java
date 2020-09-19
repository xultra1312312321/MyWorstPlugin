package org.hypernitemc.iloveeatmuffin.practice.game.area;


import com.ericlam.mc.minigames.core.arena.CreateArena;
import org.apache.commons.lang.ArrayUtils;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.World;

import java.util.*;
import java.util.stream.Collectors;

public class ArenaCreate implements CreateArena {

    private String author;
    private String arenaName;
    private String displayName;
    private Map<String, List<Location>> warpMap;
    private boolean changed;
    private World world;
    private List<String> description;

    public ArenaCreate(String author, String arenaName, String displayName, Map<String, List<Location>> warpMap, World world, List<String> description) {
        this.author = author;
        this.arenaName = arenaName;
        this.displayName = displayName;
        this.warpMap = warpMap;
        this.changed = false;
        this.world = world;
        this.description = description;
    }

    public ArenaCreate(String author, String arenaName, World world) {
        this(author, arenaName, arenaName, new HashMap<>(), world, new ArrayList<>());
    }

    @Override
    public void setLocationMap(Map<String, List<Location>> map) {
        this.warpMap = map;
    }

    @Override
    public boolean isChanged() {
        return changed;
    }

    @Override
    public void setChanged(Boolean aBoolean) {
        this.changed = aBoolean;
    }

    @Override
    public boolean isSetupCompleted() {
        boolean game = Optional.ofNullable(warpMap.get("game")).map(w -> w.size() == 24).orElse(false);
        boolean dm = Optional.ofNullable(warpMap.get("deathmatch")).map(w -> w.size() >= 4).orElse(false);
        return game && dm;
    }

    @Override
    public String getAuthor() {
        return author;
    }

    @Override
    public void setAuthor(String s) {
        this.author = s;
    }

    @Override
    public World getWorld() {
        return world;
    }

    @Override
    public void setWorld(World world) {
        this.world = world;
    }

    @Override
    public String getArenaName() {
        return arenaName;
    }

    @Override
    public void setArenaName(String s) {
        this.arenaName = s;
    }

    @Override
    public String getDisplayName() {
        return displayName;
    }

    @Override
    public void setDisplayName(String s) {
        this.displayName = ChatColor.translateAlternateColorCodes('&', s);
    }

    @Override
    public Map<String, List<Location>> getLocationsMap() {
        return warpMap;
    }

    @Override
    public List<String> getDescription() {
        return null;
    }


    @Override
    public String[] getInfo() {
        String[] info = new String[]{
                "§e場地名稱: §7".concat(arenaName),
                "§e顯示名稱: §7".concat(displayName),
                "§e場地作者: §7".concat(author),
                "§e世界: §7".concat(world.getName()),
                "§e坐標: §7".concat(warpMap.keySet().stream().map(w -> w.concat("(" + warpMap.get(w).size() + ")")).collect(Collectors.joining(", "))),
                "§e描述: §7"
        };

        String[] desp = description.stream().map(s -> " ".repeat(5).concat(s)).toArray(String[]::new);

        return (String[]) ArrayUtils.addAll(info, desp);
    }
}
