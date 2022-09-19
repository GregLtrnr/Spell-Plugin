package com.pilzo.magicspell.handlers;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import org.bukkit.entity.Player;
public class CooldownHander {
    private final Map<UUID, Long> cooldowns = new HashMap<>();
    public void addCooldown(Player player){
        if(!cooldowns.containsKey(player.getUniqueId())){
            cooldowns.put(player.getUniqueId(), System.currentTimeMillis());
        }
    }
    public void removeCooldown(Player player){
        if(cooldowns.containsKey(player.getUniqueId())){
            cooldowns.remove(player.getUniqueId());
        }
    }
    public boolean hasCooldown(Player player){
        return cooldowns.containsKey(player.getUniqueId());
    }
}
