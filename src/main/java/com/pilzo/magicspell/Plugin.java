package com.pilzo.magicspell;

import java.util.logging.Logger;

import org.bukkit.plugin.java.JavaPlugin;
/*
 * magicspell java plugin
 */
public class Plugin extends JavaPlugin
{
  private static final Logger LOGGER=Logger.getLogger("magicspell");
  @Override
  public void onEnable(){
    LOGGER.info("magicspell enabled");
    getServer().getPluginManager().registerEvents(new EventListeners(),this);
  }

  public void onDisable(){
    LOGGER.info("magicspell disabled");
  }

}
