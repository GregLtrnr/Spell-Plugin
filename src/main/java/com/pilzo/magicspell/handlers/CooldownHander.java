package com.pilzo.magicspell.handlers;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import org.bukkit.entity.Player;
import com.pilzo.magicspell.MagicSpell;
public class CooldownHander {
    private MagicSpell plugin;
    private final Map<UUID, Long> cooldowns = new HashMap<>();
    public CooldownHander(MagicSpell magicSpell) {
        this.plugin = magicSpell;
    }
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
    public long getCooldown(Player player){
        if(cooldowns.containsKey(player.getUniqueId())){
            return (cooldowns.get(player.getUniqueId()) - (System.currentTimeMillis()) + (plugin.getConfig().getInt("spells.cooldown"))*1000)/1000;
            
        }
        return 0;
    }
    public boolean checkCooldown(Player player){
        if(cooldowns.containsKey(player.getUniqueId())){
            long cooldown = cooldowns.get(player.getUniqueId());
            long time = System.currentTimeMillis();
            if(time - cooldown >= plugin.getConfig().getInt("spells.cooldown") * 1000){
                return true;
            }else{
                return false;
            }
        }else{
            return true;
        }
    }
    public boolean hasCooldown(Player player){
        return cooldowns.containsKey(player.getUniqueId());
    }
}
