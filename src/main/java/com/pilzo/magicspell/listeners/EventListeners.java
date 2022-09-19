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
    public void EventListeners(MagicSpell plugin){
        this.plugin = plugin;
    }

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event){
        if(event.getAction() == Action.RIGHT_CLICK_BLOCK){
          if(event.getItem().getType() == Material.BLAZE_ROD){
            Player player = event.getPlayer();
            if(player.hasPermission("magicspell.cast.jail")){
              if(!this.plugin.cooldownHander.hasCooldown(player)){
                Block block = player.getTargetBlock(null, 10);
                Jail jail = new Jail(block,player);
                this.plugin.cooldownHander.addCooldown(player);
              }
            }else{
              player.sendMessage(ChatColor.RED+"You don't have permission to cast this spell.");
            }
          }
          if(event.getItem().getType() == Material.STICK){
            Player player = event.getPlayer();
            Block block = player.getTargetBlock(null, 10);
            player.sendMessage("Target block type: " + block.getType());
            player.sendMessage("Target block location: " + block.getLocation());
            player.sendMessage("Target block world: " + block.getWorld());
            player.sendMessage("Target block x: " + block.getX());
            player.sendMessage("Target block y: " + block.getY());
            player.sendMessage("Target block z: " + block.getZ());
          }
        }
      }
  public Runnable breakGlass(Block[] blocks, Player player){
    player.sendMessage("Spell ended");
    for(Block b : blocks){
      if(b.getType() == Material.GLASS){
        b.setType(Material.AIR);
      }
    }
    return null;
  }
}
