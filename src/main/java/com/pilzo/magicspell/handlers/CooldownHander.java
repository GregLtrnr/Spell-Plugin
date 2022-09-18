package com.pilzo.magicspell.handlers;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import org.bukkit.entity.Player;
public class CooldownHander {
    private final Map<String, Map<UUID, Long>> cooldowns = new HashMap<>();
}
