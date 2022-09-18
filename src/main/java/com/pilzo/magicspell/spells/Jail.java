package com.pilzo.magicspell.spells;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
public class Jail {
    public Block[] blocks = {};

    public Jail(Block block, Player player){
        blocks = new Block[]{block.getRelative(0, 3, 0),block.getRelative(1, 1, 0),block.getRelative(1, 2, 0),block.getRelative(-1, 1, 0),block.getRelative(-1, 2, 0),block.getRelative(0, 1, 1),block.getRelative(0, 2, 1),block.getRelative(0, 1, -1),block.getRelative(0, 2, -1)};
        for(Block b : blocks){
            if(b.getType() == Material.AIR){
                b.setType(Material.GLASS);
            }
        }
        player.sendMessage("Spell casted");
        Bukkit.getScheduler().runTaskLater((Bukkit.getPluginManager().getPlugin("magicspell")),new Runnable() {
            public void run(){
              for(Block b : blocks){
                if(b.getType() == Material.GLASS){
                  b.setType(Material.AIR);
                }
              }
            }
          }, 100L);

    }
}