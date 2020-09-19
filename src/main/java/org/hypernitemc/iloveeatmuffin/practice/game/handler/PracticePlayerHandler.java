package org.hypernitemc.iloveeatmuffin.practice.game.handler;

import com.ericlam.mc.minigames.core.character.GamePlayer;
import com.ericlam.mc.minigames.core.character.GamePlayerHandler;
import org.bukkit.GameMode;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Player;
import org.hypernitemc.iloveeatmuffin.practice.Configs.PracticeConfig;
import org.hypernitemc.iloveeatmuffin.practice.Practice;
import org.hypernitemc.iloveeatmuffin.practice.game.player.PracticePlayer;

import java.util.Optional;

public class PracticePlayerHandler implements GamePlayerHandler {
    @Override
    public void onPlayerStatusChange(GamePlayer gamePlayer, GamePlayer.Status status) {
        Player player = gamePlayer.getPlayer();
        player.setGameMode(gamePlayer.getStatus() == GamePlayer.Status.SPECTATING ? GameMode.SPECTATOR : GameMode.ADVENTURE);
        Optional.ofNullable(player.getAttribute(Attribute.GENERIC_MAX_HEALTH)).ifPresent(attr -> player.setHealth(attr.getBaseValue()));
        player.getActivePotionEffects().forEach(effect -> player.removePotionEffect(effect.getType()));
        player.setLevel(0);
        player.setFireTicks(0);
        player.setGlowing(false);
        if (Practice.getPlugin(Practice.class).getGameBoard() != null && status == GamePlayer.Status.SPECTATING) {
            Practice.getPlugin(Practice.class).getGameBoard().addPlayer(gamePlayer);
        }
    }

    @Override
    public void onPlayerRemove(GamePlayer gamePlayer) {

    }

    @Override
    public GamePlayer createGamePlayer(Player player) {
        return new PracticePlayer(player, null);
    }

    @Override
    public int requireStart() {
        return Practice.getYamlManager().getConfigAs(PracticeConfig.class).requiredPlayers;
    }


}
