package com.pilzo.magicspell.listeners;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.player.PlayerToggleSneakEvent;
import java.util.UUID;
import java.util.HashMap;
import java.util.Map;

import com.pilzo.magicspell.MagicSpell;
import com.pilzo.magicspell.handlers.userSpells;
import com.pilzo.magicspell.spells.Jail;

public class EventListeners implements Listener {
    private MagicSpell plugin;
    private final Map <UUID, userSpells> users = new HashMap<>();

    public EventListeners(MagicSpell plugin){
        this.plugin = plugin;
    }
    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event){
        if(event.getAction() == Action.RIGHT_CLICK_BLOCK ||event.getAction() == Action.RIGHT_CLICK_AIR && event.getPlayer().isSneaking() == false){
          //clic droit blaze rod
          if(event.getItem().getType() == Material.BLAZE_ROD){
            Player player = event.getPlayer();
            if(!users.containsKey(player.getUniqueId())){
              users.put(player.getUniqueId(), new userSpells(player));
            }
            if(this.plugin.cooldownHander.checkCooldown(player)){
              this.plugin.cooldownHander.removeCooldown(player);
              switch(users.get(player.getUniqueId()).getCurrentSpell()){
                case "null":
                  player.sendMessage(ChatColor.RED + "Vous ne possedez pas de sorts");
                  break;
                case "jail":
                  if(event.getClickedBlock() == null){
                    player.sendMessage(ChatColor.RED + "Vous devez cibler un bloc");
                  }else{
                  Jail jail = new Jail(event.getClickedBlock(),player);
                  this.plugin.cooldownHander.addCooldown(player);
                  player.sendMessage(ChatColor.DARK_PURPLE+"Woosh !");
                  }
                  break;
                case "heal":
                  player.setHealth(20);
                  player.sendMessage(ChatColor.GREEN + "Vous avez été soigné");
                  this.plugin.cooldownHander.addCooldown(player);
                  break;
                case "longjump":
                  player.setVelocity(player.getLocation().getDirection().multiply(this.plugin.getConfig().getDouble("longjump.velocity")).setY(1));
                  this.plugin.cooldownHander.addCooldown(player);
                  player.sendMessage(ChatColor.DARK_PURPLE+"Woosh !");
                  break;
                default:
                  player.sendMessage(ChatColor.RED + "You don't have any spell selected");
                  break;
              }
            }else{
              player.sendMessage(ChatColor.RED + "You need to wait " + this.plugin.cooldownHander.getCooldown(player) + " seconds to cast spell again!");
            }
          }
        }
        if((event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_BLOCK) && event.getPlayer().isSneaking() == true){
            if(event.getItem() != null && event.getItem().getType() == Material.BLAZE_ROD){
              Player player = event.getPlayer();
              if(users.containsKey(player.getUniqueId())){
                String currentSpell = users.get(player.getUniqueId()).changeCurrentSpell();
                player.sendMessage("Vous utilisez maintenant le sort " + currentSpell);
              }else{
                users.put(player.getUniqueId(), new userSpells(player));
                String currentSpell = users.get(player.getUniqueId()).changeCurrentSpell();
                if(currentSpell != "null"){
                  player.sendMessage("Vous utilisez maintenant le sort " + currentSpell);
              }else{
                player.sendMessage(ChatColor.RED + "Vous ne possedez pas de sorts");
              }
            }
          } 
        }
      }
      @EventHandler
      public void onPlayerJoin(PlayerJoinEvent event){
        Player player = event.getPlayer();
        this.users.put(player.getUniqueId(), new userSpells(player));
      }

      @EventHandler
      public void onPlayerLeave(PlayerQuitEvent event){
        Player player = event.getPlayer();
        if(users.containsKey(event.getPlayer().getUniqueId())){
          users.remove(player.getUniqueId());
        }
      }
}
