package com.pilzo.magicspell.listeners;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

import com.pilzo.magicspell.MagicSpell;
import com.pilzo.magicspell.spells.Jail;

public class EventListeners implements Listener {
    private MagicSpell plugin;

    public EventListeners(MagicSpell plugin){
        this.plugin = plugin;
    }

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event){
        if(event.getAction() == Action.RIGHT_CLICK_BLOCK){
          //clic droit blaze rod
          if(event.getItem().getType() == Material.BLAZE_ROD){
            Player player = event.getPlayer();
            if(player.hasPermission("magicspell.cast.jail")){
              if(this.plugin.cooldownHander.hasCooldown(player)){
                if(this.plugin.cooldownHander.checkCooldown(player)){
                  this.plugin.cooldownHander.removeCooldown(player);
                  Jail jail = new Jail(event.getClickedBlock(),player);
                  this.plugin.cooldownHander.addCooldown(player);
              }else{
                player.sendMessage(ChatColor.RED + "You need to wait " + this.plugin.cooldownHander.getCooldown(player) + " seconds to cast spell again!");
              }
            }else{
              Jail jail = new Jail(event.getClickedBlock(),player);
              this.plugin.cooldownHander.addCooldown(player);
            }
          }else{
              player.sendMessage(ChatColor.RED+"You don't have permission to cast this spell.");
            }
          }
        }
      }
}
