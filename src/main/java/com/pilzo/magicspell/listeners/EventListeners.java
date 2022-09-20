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
      //Si la personne fait un clic droit sur un block
        if(event.getAction() == Action.RIGHT_CLICK_BLOCK){
          //Si le clic droit est fait avec une blaze rod en main
          if(event.getItem().getType() == Material.BLAZE_ROD){
            Player player = event.getPlayer();
            //Si la personne a la permission pour le spell jail
            if(player.hasPermission("magicspell.cast.jail")){
              //Si la personne a un cooldown
              if(this.plugin.cooldownHander.hasCooldown(player)){
                //Si le cooldown est fini
                if(this.plugin.cooldownHander.checkCooldown(player)){
                  //On enlève le cooldown
                  this.plugin.cooldownHander.removeCooldown(player);
                  //On cast le spell
                  Jail jail = new Jail(event.getClickedBlock(),player);
                  //On reset le cooldown
                  this.plugin.cooldownHander.addCooldown(player);
              }else{
                player.sendMessage(ChatColor.RED + "You need to wait " + this.plugin.cooldownHander.getCooldown(player) + " seconds to cast spell again!");
              }
            }
          }else{
              player.sendMessage(ChatColor.RED+"You don't have permission to cast this spell.");
            }
          }
        }
      }
}
