package com.pilzo.magicspell;

import java.util.logging.Logger;

import org.bukkit.plugin.java.JavaPlugin;

import com.pilzo.magicspell.handlers.CooldownHander;
import com.pilzo.magicspell.listeners.EventListeners;
/*
 * magicspell java plugin
 */
public class MagicSpell extends JavaPlugin
{
  private static final Logger LOGGER=Logger.getLogger("magicspell");
  private CooldownHander cooldownHander;
  @Override
  public void onEnable(){
    LOGGER.info("magicspell enabled");
    cooldownHander = new CooldownHander();
    getServer().getPluginManager().registerEvents(new EventListeners(),this);
  }

  public void onDisable(){
    LOGGER.info("magicspell disabled");
  }

}
