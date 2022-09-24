package com.pilzo.magicspell.handlers;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;

public class userSpells {
    private Player player;
    public Map <Integer, String> spells = new HashMap<>();
    public String currentSpell = null;
    public userSpells(Player player){
        this.player = player;
        int i = 0;
        if(player.hasPermission("magicspell.cast.jail")){
            this.spells.put(i,"jail");
            i++;
          }
        if(player.hasPermission("magicspell.cast.longjump")){
            this.spells.put(i,"longjump");
            i++;
        }
        if(player.hasPermission("magicspell.cast.heal")){
            this.spells.put(i,"heal");
            i++;
        }
        if(!this.spells.isEmpty()){
            this.currentSpell = this.spells.get(0);
        }
    }
    public String getCurrentSpell(){
        return this.currentSpell;
    }
    public String changeCurrentSpell(){
        int i = 0;
        for (Map.Entry<Integer, String> entry : this.spells.entrySet()) {
            if(entry.getValue().equals(this.currentSpell)){
                if(i == this.spells.size()-1){
                    this.currentSpell = this.spells.get(0);
                    return this.currentSpell;
                }else{
                    this.currentSpell = this.spells.get(i+1);
                    return this.currentSpell;
                }
            }
            i++;
        }
        return this.currentSpell;
    }
}
