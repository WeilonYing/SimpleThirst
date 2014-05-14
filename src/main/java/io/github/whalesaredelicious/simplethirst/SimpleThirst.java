/*
 * Copyright (C) 2014 Weilon
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the Creative Commons Attribution-ShareAlike 4.0
 * International as published by the Creative Commons Organisation.
 *
 * You are free to copy, redistribute and create derivative works
 * from the material under the condition appropriate credit is
 * given to the creator. In addition, if you remix, transform or
 * build upon the material, you must distribute your contributions under
 * the same license as the original.
 *
 * You may get a copy of the license here: https://creativecommons.org/licenses/by-sa/4.0/
 */

package io.github.whalesaredelicious.simplethirst;

import java.util.logging.Logger;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Server;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.FileConfigurationOptions;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerChangedWorldEvent;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitScheduler;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Score;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.ScoreboardManager;

public final class SimpleThirst
  extends JavaPlugin
  implements Listener
{
  Logger logger = Logger.getLogger("Minecraft");
  
  @EventHandler
  public void onConsume(PlayerItemConsumeEvent event)
  {
    if (event.getPlayer().getItemInHand().getType().equals(Material.POTION))
    {
      int integerValue = getConfig().getInt(event.getPlayer().getName()) + getConfig().getInt("replenish-water");
      
      getConfig().set(event.getPlayer().getName(), Integer.valueOf(integerValue));
      saveConfig();
      event.getPlayer().sendMessage(ChatColor.LIGHT_PURPLE + "You drank water and gained " + getConfig().getInt("replenish-water") + " thirst levels");
      if (getConfig().getInt(event.getPlayer().getName()) > 10)
      {
        getConfig().set(event.getPlayer().getName(), Integer.valueOf(10));
        saveConfig();
      }
      else if (getConfig().getInt(event.getPlayer().getName()) < 0)
      {
        getConfig().set(event.getPlayer().getName(), Integer.valueOf(10));
        saveConfig();
      }
    }
    else if (event.getPlayer().getItemInHand().getType().equals(Material.MILK_BUCKET))
    {
      int integerValue1 = getConfig().getInt(event.getPlayer().getName()) + getConfig().getInt("replenish-milk");
      
      getConfig().set(event.getPlayer().getName(), Integer.valueOf(integerValue1));
      saveConfig();
      event.getPlayer().sendMessage(ChatColor.LIGHT_PURPLE + "You drank milk and gained " + getConfig().getInt("replenish-milk") + " thirst levels");
      if (getConfig().getInt(event.getPlayer().getName()) > 10)
      {
        getConfig().set(event.getPlayer().getName(), Integer.valueOf(10));
        saveConfig();
      }
      else if (getConfig().getInt(event.getPlayer().getName()) < 0)
      {
        getConfig().set(event.getPlayer().getName(), Integer.valueOf(10));
        saveConfig();
      }
    }
    else if (event.getPlayer().getItemInHand().getType().equals(Material.MELON))
    {
      int integerValue2 = getConfig().getInt(event.getPlayer().getName()) + getConfig().getInt("replenish-melon");
      

      getConfig().set(event.getPlayer().getName(), Integer.valueOf(integerValue2));
      saveConfig();
      event.getPlayer().sendMessage(ChatColor.LIGHT_PURPLE + "You ate a melon and gained " + getConfig().getInt("replenish-melon") + " thirst levels");
      if (getConfig().getInt(event.getPlayer().getName()) > 10)
      {
        getConfig().set(event.getPlayer().getName(), Integer.valueOf(10));
        saveConfig();
      }
      else if (getConfig().getInt(event.getPlayer().getName()) < 0)
      {
        getConfig().set(event.getPlayer().getName(), Integer.valueOf(10));
        saveConfig();
      }
    }
    else if (event.getPlayer().getItemInHand().getType().equals(Material.MUSHROOM_SOUP))
    {
      int integerValue2 = getConfig().getInt(event.getPlayer().getName()) + getConfig().getInt("replenish-mushroom-soup");
      

      getConfig().set(event.getPlayer().getName(), Integer.valueOf(integerValue2));
      saveConfig();
      event.getPlayer().sendMessage(ChatColor.LIGHT_PURPLE + "You drank a mushroom soup and gained " + getConfig().getInt("replenish-mushroom-soup") + " thirst levels");
      if (getConfig().getInt(event.getPlayer().getName()) > 10)
      {
        getConfig().set(event.getPlayer().getName(), Integer.valueOf(10));
        saveConfig();
      }
      else if (getConfig().getInt(event.getPlayer().getName()) < 0)
      {
        getConfig().set(event.getPlayer().getName(), Integer.valueOf(10));
        saveConfig();
      }
    }
    ScoreboardManager manager = Bukkit.getScoreboardManager();
    Scoreboard board = manager.getNewScoreboard();
    
    Objective objective = board.registerNewObjective(ChatColor.AQUA + getConfig().getString("scoreboard-text"), "dummy");
    objective.setDisplaySlot(DisplaySlot.SIDEBAR);
    objective.setDisplayName(ChatColor.AQUA + getConfig().getString("scoreboard-text"));
    
    Score score = objective.getScore(event.getPlayer());
    score.setScore(getConfig().getInt(event.getPlayer().getName()));
    event.getPlayer().getPlayer().setScoreboard(board);
    event.getPlayer().setScoreboard(board);
  }
  
  public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args)
  {
    Player player = (Player)sender;
    if (commandLabel.equalsIgnoreCase("thirst") && sender instanceof Player) {
      if (args.length == 0)
      {
        if (!getConfig().contains(player.getName()))
        {
          getConfig().set(player.getName(), Integer.valueOf(10));
          saveConfig();
        }
        if (getConfig().getInt(sender.getName()) > 10)
        {
          getConfig().set(sender.getName(), Integer.valueOf(10));
          saveConfig();
        }
        else if (getConfig().getInt(sender.getName()) > 6)
        {
          player.sendMessage(ChatColor.GREEN + "Your thirst level is " + 
            getConfig().getString(player.getName()) + "/10");
        }
        else if (getConfig().getInt(sender.getName()) > 4)
        {
          player.sendMessage(ChatColor.GOLD + "Your thirst level is " + 
            getConfig().getString(player.getName()) + "/10");
        }
        else if (getConfig().getInt(sender.getName()) > 0)
        {
          player.sendMessage(ChatColor.RED + "Your thirst level is " + 
            getConfig().getString(player.getName()) + "/10");
        }
        else if (getConfig().getInt(sender.getName()) < 0)
        {
          getConfig().set(sender.getName(), Integer.valueOf(10));
          saveConfig();
          player.sendMessage(ChatColor.GREEN + "Your thirst level is " + 
            getConfig().getString(player.getName()) + "/10");
        }
      }
      else if (args.length == 3)
      {
        if (sender.hasPermission("simplethirst.admin"))
        {
          if (args[0].equalsIgnoreCase("set"))
          {
            int lol = Integer.parseInt(args[2]);
            
            Player targetPlayer = player.getServer().getPlayer(args[1]);
            if (lol > 10)
            {
              getConfig().set(targetPlayer.getName(), Integer.valueOf(10));
              saveConfig();
            }
            else
            {
              getConfig().set(targetPlayer.getName(), Integer.valueOf(lol));
              saveConfig();
            }
            ScoreboardManager manager = Bukkit.getScoreboardManager();
            Scoreboard board = manager.getNewScoreboard();
            
            Objective objective = board.registerNewObjective(ChatColor.AQUA + getConfig().getString("scoreboard-text"), "dummy");
            objective.setDisplaySlot(DisplaySlot.SIDEBAR);
            objective.setDisplayName(ChatColor.AQUA + getConfig().getString("scoreboard-text"));
            
            Score score = objective.getScore(targetPlayer.getPlayer());
            score.setScore(getConfig().getInt(targetPlayer.getPlayer().getName()));
            sender.sendMessage(targetPlayer.getName() + " thirst level has been set to: " + args[2] + "/10");
            targetPlayer.getPlayer().setScoreboard(board);
            if (!targetPlayer.getPlayer().hasPermission("simplethirst.nothirst")) {
              targetPlayer.getPlayer().setScoreboard(board);
            }
            targetPlayer.getPlayer().sendMessage(ChatColor.GREEN + "Your thirst level has been set to: " + getConfig().getInt(targetPlayer.getPlayer().getName()) + "/10");
          }
          else if (sender.hasPermission("simplethirst.admin"))
          {
            sender.sendMessage(ChatColor.RED + "Usage: /thirst or /thirst set <player> <level>");
          }
          else
          {
            sender.sendMessage(ChatColor.RED + "Usage: /thirst");
          }
        }
        else {
          sender.sendMessage(ChatColor.RED + "You don't have permission.");
        }
      }
      else
      {
        sender.sendMessage(ChatColor.RED + "Usage: /thirst or /thirst set <player> <level>");
      }
    }
    return false;
  }
  
  @EventHandler
  public void normalLogin(PlayerJoinEvent event)
  {
    if (1 > getConfig().getInt(event.getPlayer().getName()))
    {
      getConfig().set(event.getPlayer().getName(), Integer.valueOf(10));
      saveConfig();
    }
    if (!getConfig().contains(event.getPlayer().getName()))
    {
      getConfig().set(event.getPlayer().getName(), Integer.valueOf(10));
      saveConfig();
    }
    ScoreboardManager manager = Bukkit.getScoreboardManager();
    Scoreboard board = manager.getNewScoreboard();
    
    Objective objective = board.registerNewObjective(ChatColor.AQUA + getConfig().getString("scoreboard-text"), "dummy");
    objective.setDisplaySlot(DisplaySlot.SIDEBAR);
    objective.setDisplayName(ChatColor.AQUA + getConfig().getString("scoreboard-text"));
    

    Score score = objective.getScore(event.getPlayer());
    score.setScore(getConfig().getInt(event.getPlayer().getName()));
    if (!event.getPlayer().hasPermission("simplethirst.nothirst")) {
      event.getPlayer().setScoreboard(board);
    }
  }
  
  @EventHandler
  public void onChange(PlayerChangedWorldEvent event)
  {
    ScoreboardManager manager = Bukkit.getScoreboardManager();
    event.getPlayer().setScoreboard(manager.getNewScoreboard());
    

    Player p = event.getPlayer();
    



    boolean result1 = false;
    boolean result2 = false;
    boolean result3 = false;
    boolean result4 = false;
    boolean result5 = false;
    boolean result6 = false;
    boolean result7 = false;
    boolean result8 = false;
    boolean result9 = false;
    boolean result10 = false;
    if (getConfig().getBoolean("multiple-world-support"))
    {
      if ((getConfig().contains("multi-world1")) && 
        (getConfig().getString("multi-world1").contains(p.getWorld().getName())) && (getConfig().getString("multi-world1").length() == p.getWorld().getName().length())) {
        result1 = true;
      }
      if ((getConfig().contains("multi-world2")) && 
        (getConfig().getString("multi-world2").contains(p.getWorld().getName())) && (getConfig().getString("multi-world2").length() == p.getWorld().getName().length())) {
        result2 = true;
      }
      if ((getConfig().contains("multi-world3")) && 
        (getConfig().getString("multi-world3").contains(p.getWorld().getName())) && (getConfig().getString("multi-world3").length() == p.getWorld().getName().length())) {
        result3 = true;
      }
      if ((getConfig().contains("multi-world4")) && 
        (getConfig().getString("multi-world4").contains(p.getWorld().getName())) && (getConfig().getString("multi-world4").length() == p.getWorld().getName().length())) {
        result4 = true;
      }
      if ((getConfig().contains("multi-world5")) && 
        (getConfig().getString("multi-world5").contains(p.getWorld().getName())) && (getConfig().getString("multi-world5").length() == p.getWorld().getName().length())) {
        result4 = true;
      }
      if ((getConfig().contains("multi-world6")) && 
        (getConfig().getString("multi-world6").contains(p.getWorld().getName())) && (getConfig().getString("multi-world6").length() == p.getWorld().getName().length())) {
        result4 = true;
      }
      if ((getConfig().contains("multi-world7")) && 
        (getConfig().getString("multi-world7").contains(p.getWorld().getName())) && (getConfig().getString("multi-world7").length() == p.getWorld().getName().length())) {
        result4 = true;
      }
      if ((getConfig().contains("multi-world8")) && 
        (getConfig().getString("multi-world8").contains(p.getWorld().getName())) && (getConfig().getString("multi-world8").length() == p.getWorld().getName().length())) {
        result4 = true;
      }
      if ((getConfig().contains("multi-world9")) && 
        (getConfig().getString("multi-world9").contains(p.getWorld().getName())) && (getConfig().getString("multi-world9").length() == p.getWorld().getName().length())) {
        result4 = true;
      }
      if ((getConfig().contains("multi-world10")) && 
        (getConfig().getString("multi-world10").contains(p.getWorld().getName())) && (getConfig().getString("multi-world10").length() == p.getWorld().getName().length())) {
        result4 = true;
      }
    }
    if ((result1) || (result2) || (result3) || (result4) || (result5) || (result6) || (result7) || (result8) || (result9) || (result10) || (!getConfig().getBoolean("multiple-world-support")))
    {
      Scoreboard board = manager.getNewScoreboard();
      
      Objective objective = board.registerNewObjective(ChatColor.AQUA + getConfig().getString("scoreboard-text"), "dummy");
      objective.setDisplaySlot(DisplaySlot.SIDEBAR);
      objective.setDisplayName(ChatColor.AQUA + getConfig().getString("scoreboard-text"));
      

      Score score = objective.getScore(p.getPlayer());
      score.setScore(getConfig().getInt(p.getPlayer().getName()));
      if (!p.getPlayer().hasPermission("simplethirst.nothirst")) {
        p.getPlayer().setScoreboard(board);
      }
    }
  }
  
  public void onEnable()
  {
    
    getServer().getPluginManager().registerEvents(this, this);
    for (Player p : Bukkit.getOnlinePlayers())
    {
      if (!getConfig().contains(p.getName()))
      {
        getConfig().set(p.getName(), Integer.valueOf(10));
        saveConfig();
      }
      ScoreboardManager manager = Bukkit.getScoreboardManager();
      Scoreboard board = manager.getNewScoreboard();
      
      Objective objective = board.registerNewObjective(ChatColor.AQUA + getConfig().getString("scoreboard-text"), "dummy");
      objective.setDisplaySlot(DisplaySlot.SIDEBAR);
      objective.setDisplayName(ChatColor.AQUA + getConfig().getString("scoreboard-text"));
      

      Score score = objective.getScore(p.getPlayer());
      score.setScore(getConfig().getInt(p.getPlayer().getName()));
      if (!p.getPlayer().hasPermission("simplethirst.nothirst")) {
        p.getPlayer().setScoreboard(board);
      }
    }
    FileConfiguration config = getConfig();
    
    PluginDescriptionFile descFile = this.getDescription();
    getLogger().info(descFile.getName() + " " + descFile.getVersion() + " is enabled.");
    
    config.addDefault("time-in-seconds-to-remove-1", Integer.valueOf(180));
    config.addDefault("scoreboard-text", "Thirst");
    config.addDefault("ops-get-thirsty", Boolean.valueOf(false));
    config.addDefault("replenish-water", Integer.valueOf(8));
    config.addDefault("replenish-milk", Integer.valueOf(6));
    config.addDefault("replenish-melon", Integer.valueOf(2));
    config.addDefault("replenish-mushroom-soup", Integer.valueOf(3));
    config.addDefault("multiple-world-support", Boolean.valueOf(false));
    config.addDefault("multi-world1", "world");
    config.addDefault("multi-world2", "world_nether");
    config.addDefault("multi-world3", "world_the_end");
    config.addDefault("level9-message", "Keep an eye on your thirst!");
    config.addDefault("level8-message", "Keep an eye on your thirst!");
    config.addDefault("level7-message", "Keep an eye on your thirst!");
    config.addDefault("level6-message", "Consider finding a drink soon!");
    config.addDefault("level5-message", "You are starting to feel dehydrated!");
    config.addDefault("level4-message", "DANGER Find a drink fast");
    config.addDefault("level3-message", "DANGER Find a drink fast");
    config.addDefault("level2-message", "DANGER Find a drink fast");
    config.addDefault("level1-message", "DANGER FIND A DRINK NOW!");
    config.addDefault("death-message", "YOU HAVE DIED FROM DEHYDRATION!");
    config.options()
      .header("#====== ONLY EDIT THE CONFIGURATION IN THIS FILE. NEVER EDIT USER DATA FROM HERE! ======\r\n======= THIS FILE STORES USER DATA ========");
    config.options().copyHeader(true);
    
    config.options().copyDefaults(true);
    saveConfig();
    
    /*Timed event*/
    Bukkit.getServer().getScheduler().runTaskTimer(this, new Runnable()
    {
      public void run()
      {
        Player[] playerList = Bukkit.getOnlinePlayers();
        for (Player p : playerList)
        {
          boolean result1 = false;
          boolean result2 = false;
          boolean result3 = false;
          boolean result4 = false;
          boolean result5 = false;
          boolean result6 = false;
          boolean result7 = false;
          boolean result8 = false;
          boolean result9 = false;
          boolean result10 = false;
          if (SimpleThirst.this.getConfig().getBoolean("multiple-world-support"))
          {
            if ((SimpleThirst.this.getConfig().contains("multi-world1")) && 
              (SimpleThirst.this.getConfig().getString("multi-world1").contains(p.getWorld().getName())) && (SimpleThirst.this.getConfig().getString("multi-world1").length() == p.getWorld().getName().length())) {
              result1 = true;
            }
            if ((SimpleThirst.this.getConfig().contains("multi-world2")) && 
              (SimpleThirst.this.getConfig().getString("multi-world2").contains(p.getWorld().getName())) && (SimpleThirst.this.getConfig().getString("multi-world2").length() == p.getWorld().getName().length())) {
              result2 = true;
            }
            if ((SimpleThirst.this.getConfig().contains("multi-world3")) && 
              (SimpleThirst.this.getConfig().getString("multi-world3").contains(p.getWorld().getName())) && (SimpleThirst.this.getConfig().getString("multi-world3").length() == p.getWorld().getName().length())) {
              result3 = true;
            }
            if ((SimpleThirst.this.getConfig().contains("multi-world4")) && 
              (SimpleThirst.this.getConfig().getString("multi-world4").contains(p.getWorld().getName())) && (SimpleThirst.this.getConfig().getString("multi-world4").length() == p.getWorld().getName().length())) {
              result4 = true;
            }
            if ((SimpleThirst.this.getConfig().contains("multi-world5")) && 
              (SimpleThirst.this.getConfig().getString("multi-world5").contains(p.getWorld().getName())) && (SimpleThirst.this.getConfig().getString("multi-world5").length() == p.getWorld().getName().length())) {
              result5 = true;
            }
            if ((SimpleThirst.this.getConfig().contains("multi-world6")) && 
              (SimpleThirst.this.getConfig().getString("multi-world6").contains(p.getWorld().getName())) && (SimpleThirst.this.getConfig().getString("multi-world6").length() == p.getWorld().getName().length())) {
              result6 = true;
            }
            if ((SimpleThirst.this.getConfig().contains("multi-world7")) && 
              (SimpleThirst.this.getConfig().getString("multi-world7").contains(p.getWorld().getName())) && (SimpleThirst.this.getConfig().getString("multi-world7").length() == p.getWorld().getName().length())) {
              result7 = true;
            }
            if ((SimpleThirst.this.getConfig().contains("multi-world8")) && 
              (SimpleThirst.this.getConfig().getString("multi-world8").contains(p.getWorld().getName())) && (SimpleThirst.this.getConfig().getString("multi-world8").length() == p.getWorld().getName().length())) {
              result8 = true;
            }
            if ((SimpleThirst.this.getConfig().contains("multi-world9")) && 
              (SimpleThirst.this.getConfig().getString("multi-world9").contains(p.getWorld().getName())) && (SimpleThirst.this.getConfig().getString("multi-world9").length() == p.getWorld().getName().length())) {
              result9 = true;
            }
            if ((SimpleThirst.this.getConfig().contains("multi-world10")) && 
              (SimpleThirst.this.getConfig().getString("multi-world10").contains(p.getWorld().getName())) && (SimpleThirst.this.getConfig().getString("multi-world10").length() == p.getWorld().getName().length())) {
              result10 = true;
            }
          }
          if ((result1) || (result2) || (result3) || (result4) || (result5) || (result6) || (result7) || (result8) || (result9) || (result10) || (!SimpleThirst.this.getConfig().getBoolean("multiple-world-support"))) {
            if (!p.getPlayer().hasPermission("simplethirst.nothirst")) {
              if (!p.getPlayer().hasPermission("simplethirst.slowthirst")) {
                if ((!p.getPlayer().isOp()) || (SimpleThirst.this.getConfig().getBoolean("ops-get-thirsty")))
                {
                  if (!SimpleThirst.this.getConfig().contains(p.getName()))
                  {
                    SimpleThirst.this.getConfig().set(p.getName(), Integer.valueOf(10));
                    SimpleThirst.this.saveConfig();
                  }
                  int integerValue = SimpleThirst.this.getConfig().getInt(p.getName()) - 1;

                  SimpleThirst.this.getConfig().set(p.getName(), Integer.valueOf(integerValue));
                  SimpleThirst.this.saveConfig();
                  if (SimpleThirst.this.getConfig().getInt(p.getName()) > 10)
                  {
                    SimpleThirst.this.getConfig().set(p.getName(), Integer.valueOf(10));
                    SimpleThirst.this.saveConfig();
                  }
                  else if (SimpleThirst.this.getConfig().getInt(p.getName()) < 0)
                  {
                    SimpleThirst.this.getConfig().set(p.getName(), Integer.valueOf(10));
                    SimpleThirst.this.saveConfig();
                  }
                  ScoreboardManager manager = Bukkit.getScoreboardManager();
                  Scoreboard board = manager.getNewScoreboard();

                  Objective objective = board.registerNewObjective(ChatColor.AQUA + SimpleThirst.this.getConfig().getString("scoreboard-text"), "dummy");
                  objective.setDisplaySlot(DisplaySlot.SIDEBAR);
                  objective.setDisplayName(ChatColor.AQUA + SimpleThirst.this.getConfig().getString("scoreboard-text"));


                  Score score = objective.getScore(p.getPlayer());
                  score.setScore(SimpleThirst.this.getConfig().getInt(p.getPlayer().getName()));
                  if (!p.getPlayer().hasPermission("simplethirst.nothirst")) {
                    p.getPlayer().setScoreboard(board);
                  }
                  if (SimpleThirst.this.getConfig().getInt(p.getName()) != 10) {
                    if (SimpleThirst.this.getConfig().getInt(p.getName()) == 9)
                    {
                      p.sendMessage(ChatColor.GREEN + 
                        SimpleThirst.this.getConfig().getString("level9-message") + " 9/10");
                    }
                    else if (SimpleThirst.this.getConfig().getInt(p.getName()) == 8)
                    {
                      p.sendMessage(ChatColor.GREEN + 
                        SimpleThirst.this.getConfig().getString("level8-message") + " 8/10");
                    }
                    else if (SimpleThirst.this.getConfig().getInt(p.getName()) == 7)
                    {
                      p.sendMessage(ChatColor.GREEN + 
                        SimpleThirst.this.getConfig().getString("level7-message") + " 7/10");
                    }
                    else if (SimpleThirst.this.getConfig().getInt(p.getName()) == 6)
                    {
                      p.sendMessage(ChatColor.GOLD + 
                        SimpleThirst.this.getConfig().getString("level6-message") + " 6/10");
                    }
                    else if (SimpleThirst.this.getConfig().getInt(p.getName()) == 5)
                    {
                      p.sendMessage(ChatColor.GOLD + 
                        SimpleThirst.this.getConfig().getString("level5-message") + " 5/10");
                    }
                    else if (SimpleThirst.this.getConfig().getInt(p.getName()) == 4)
                    {
                      p.sendMessage(ChatColor.RED + 
                        SimpleThirst.this.getConfig().getString("level4-message") + " 4/10");
                    }
                    else if (SimpleThirst.this.getConfig().getInt(p.getName()) == 3)
                    {
                      p.sendMessage(ChatColor.RED + 
                        SimpleThirst.this.getConfig().getString("level3-message") + " 3/10");
                    }
                    else if (SimpleThirst.this.getConfig().getInt(p.getName()) == 2)
                    {
                      PotionEffect nausea = new PotionEffect(
                        PotionEffectType.CONFUSION, 100, 50);
                      p.addPotionEffect(nausea, true);
                      p.sendMessage(ChatColor.RED + 
                        SimpleThirst.this.getConfig().getString("level2-message") + " 2/10");
                    }
                    else if (SimpleThirst.this.getConfig().getInt(p.getName()) == 1)
                    {
                      PotionEffect nausea = new PotionEffect(
                        PotionEffectType.CONFUSION, 100, 50);
                      p.addPotionEffect(nausea, true);
                      p.sendMessage(ChatColor.RED + 
                        SimpleThirst.this.getConfig().getString("level1-message") + " 1/10");
                    }
                    else if (SimpleThirst.this.getConfig().getInt(p.getName()) == 0)
                    {
                      p.setHealth(0.0D);
                      p.sendMessage(ChatColor.RED + 
                        SimpleThirst.this.getConfig().getString("death-message"));
                    }
                  }
                }
              }
            }
          }
        }
      }
    }, 20 * getConfig().getInt("time-in-seconds-to-remove-1"), 20 * getConfig().getInt("time-in-seconds-to-remove-1"));
    
    /*Timed Event for players with slowed thirst*/
    Bukkit.getServer().getScheduler().runTaskTimer(this, new Runnable()
    {
      public void run()
      {
        Player[] playerList = Bukkit.getOnlinePlayers();
        for (Player p : playerList)
        {
          boolean result1 = false;
          boolean result2 = false;
          boolean result3 = false;
          boolean result4 = false;
          boolean result5 = false;
          boolean result6 = false;
          boolean result7 = false;
          boolean result8 = false;
          boolean result9 = false;
          boolean result10 = false;
          if (SimpleThirst.this.getConfig().getBoolean("multiple-world-support"))
          {
            if ((SimpleThirst.this.getConfig().contains("multi-world1")) && 
              (SimpleThirst.this.getConfig().getString("multi-world1").contains(p.getWorld().getName())) && (SimpleThirst.this.getConfig().getString("multi-world1").length() == p.getWorld().getName().length())) {
              result1 = true;
            }
            if ((SimpleThirst.this.getConfig().contains("multi-world2")) && 
              (SimpleThirst.this.getConfig().getString("multi-world2").contains(p.getWorld().getName())) && (SimpleThirst.this.getConfig().getString("multi-world2").length() == p.getWorld().getName().length())) {
              result2 = true;
            }
            if ((SimpleThirst.this.getConfig().contains("multi-world3")) && 
              (SimpleThirst.this.getConfig().getString("multi-world3").contains(p.getWorld().getName())) && (SimpleThirst.this.getConfig().getString("multi-world3").length() == p.getWorld().getName().length())) {
              result3 = true;
            }
            if ((SimpleThirst.this.getConfig().contains("multi-world4")) && 
              (SimpleThirst.this.getConfig().getString("multi-world4").contains(p.getWorld().getName())) && (SimpleThirst.this.getConfig().getString("multi-world4").length() == p.getWorld().getName().length())) {
              result4 = true;
            }
            if ((SimpleThirst.this.getConfig().contains("multi-world5")) && 
              (SimpleThirst.this.getConfig().getString("multi-world5").contains(p.getWorld().getName())) && (SimpleThirst.this.getConfig().getString("multi-world5").length() == p.getWorld().getName().length())) {
              result5 = true;
            }
            if ((SimpleThirst.this.getConfig().contains("multi-world6")) && 
              (SimpleThirst.this.getConfig().getString("multi-world6").contains(p.getWorld().getName())) && (SimpleThirst.this.getConfig().getString("multi-world6").length() == p.getWorld().getName().length())) {
              result6 = true;
            }
            if ((SimpleThirst.this.getConfig().contains("multi-world7")) && 
              (SimpleThirst.this.getConfig().getString("multi-world7").contains(p.getWorld().getName())) && (SimpleThirst.this.getConfig().getString("multi-world7").length() == p.getWorld().getName().length())) {
              result7 = true;
            }
            if ((SimpleThirst.this.getConfig().contains("multi-world8")) && 
              (SimpleThirst.this.getConfig().getString("multi-world8").contains(p.getWorld().getName())) && (SimpleThirst.this.getConfig().getString("multi-world8").length() == p.getWorld().getName().length())) {
              result8 = true;
            }
            if ((SimpleThirst.this.getConfig().contains("multi-world9")) && 
              (SimpleThirst.this.getConfig().getString("multi-world9").contains(p.getWorld().getName())) && (SimpleThirst.this.getConfig().getString("multi-world9").length() == p.getWorld().getName().length())) {
              result9 = true;
            }
            if ((SimpleThirst.this.getConfig().contains("multi-world10")) && 
              (SimpleThirst.this.getConfig().getString("multi-world10").contains(p.getWorld().getName())) && (SimpleThirst.this.getConfig().getString("multi-world10").length() == p.getWorld().getName().length())) {
              result10 = true;
            }
          }
          if ((result1) || (result2) || (result3) || (result4) || (result5) || (result6) || (result7) || (result8) || (result9) || (result10) || (!SimpleThirst.this.getConfig().getBoolean("multiple-world-support"))) {
            if (!p.getPlayer().hasPermission("simplethirst.nothirst")) {
              if (p.getPlayer().hasPermission("simplethirst.slowthirst")) {
                if ((!p.getPlayer().isOp()) || (SimpleThirst.this.getConfig().getBoolean("ops-get-thirsty")))
                {
                  if (!SimpleThirst.this.getConfig().contains(p.getName()))
                  {
                    SimpleThirst.this.getConfig().set(p.getName(), Integer.valueOf(10));
                    SimpleThirst.this.saveConfig();
                  }
                  int integerValue = SimpleThirst.this.getConfig().getInt(p.getName()) - 1;

                  SimpleThirst.this.getConfig().set(p.getName(), Integer.valueOf(integerValue));
                  SimpleThirst.this.saveConfig();
                  if (SimpleThirst.this.getConfig().getInt(p.getName()) > 10)
                  {
                    SimpleThirst.this.getConfig().set(p.getName(), Integer.valueOf(10));
                    SimpleThirst.this.saveConfig();
                  }
                  else if (SimpleThirst.this.getConfig().getInt(p.getName()) < 0)
                  {
                    SimpleThirst.this.getConfig().set(p.getName(), Integer.valueOf(10));
                    SimpleThirst.this.saveConfig();
                  }
                  ScoreboardManager manager = Bukkit.getScoreboardManager();
                  Scoreboard board = manager.getNewScoreboard();

                  Objective objective = board.registerNewObjective(ChatColor.AQUA + SimpleThirst.this.getConfig().getString("scoreboard-text"), "dummy");
                  objective.setDisplaySlot(DisplaySlot.SIDEBAR);
                  objective.setDisplayName(ChatColor.AQUA + SimpleThirst.this.getConfig().getString("scoreboard-text"));


                  Score score = objective.getScore(p.getPlayer());
                  score.setScore(SimpleThirst.this.getConfig().getInt(p.getPlayer().getName()));
                  if (!p.getPlayer().hasPermission("simplethirst.nothirst")) {
                    p.getPlayer().setScoreboard(board);
                  }
                  if (SimpleThirst.this.getConfig().getInt(p.getName()) != 10) {
                    if (SimpleThirst.this.getConfig().getInt(p.getName()) == 9)
                    {
                      p.sendMessage(ChatColor.GREEN + 
                        SimpleThirst.this.getConfig().getString("level9-message") + " 9/10");
                    }
                    else if (SimpleThirst.this.getConfig().getInt(p.getName()) == 8)
                    {
                      p.sendMessage(ChatColor.GREEN + 
                        SimpleThirst.this.getConfig().getString("level8-message") + " 8/10");
                    }
                    else if (SimpleThirst.this.getConfig().getInt(p.getName()) == 7)
                    {
                      p.sendMessage(ChatColor.GREEN + 
                        SimpleThirst.this.getConfig().getString("level7-message") + " 7/10");
                    }
                    else if (SimpleThirst.this.getConfig().getInt(p.getName()) == 6)
                    {
                      p.sendMessage(ChatColor.GOLD + 
                        SimpleThirst.this.getConfig().getString("level6-message") + " 6/10");
                    }
                    else if (SimpleThirst.this.getConfig().getInt(p.getName()) == 5)
                    {
                      p.sendMessage(ChatColor.GOLD + 
                        SimpleThirst.this.getConfig().getString("level5-message") + " 5/10");
                    }
                    else if (SimpleThirst.this.getConfig().getInt(p.getName()) == 4)
                    {
                      p.sendMessage(ChatColor.RED + 
                        SimpleThirst.this.getConfig().getString("level4-message") + " 4/10");
                    }
                    else if (SimpleThirst.this.getConfig().getInt(p.getName()) == 3)
                    {
                      p.sendMessage(ChatColor.RED + 
                        SimpleThirst.this.getConfig().getString("level3-message") + " 3/10");
                    }
                    else if (SimpleThirst.this.getConfig().getInt(p.getName()) == 2)
                    {
                      PotionEffect nausea = new PotionEffect(
                        PotionEffectType.CONFUSION, 100, 50);
                      p.addPotionEffect(nausea, true);
                      p.sendMessage(ChatColor.RED + 
                        SimpleThirst.this.getConfig().getString("level2-message") + " 2/10");
                    }
                    else if (SimpleThirst.this.getConfig().getInt(p.getName()) == 1)
                    {
                      PotionEffect nausea = new PotionEffect(
                        PotionEffectType.CONFUSION, 100, 50);
                      p.addPotionEffect(nausea, true);
                      p.sendMessage(ChatColor.RED + 
                        SimpleThirst.this.getConfig().getString("level1-message") + " 1/10");
                    }
                    else if (SimpleThirst.this.getConfig().getInt(p.getName()) == 0)
                    {
                      p.setHealth(0.0D);
                      p.sendMessage(ChatColor.RED + 
                      SimpleThirst.this.getConfig().getString("death-message"));
                      getLogger().info("Player " + p + " has died from thirst.");
                    }
                  }
                }
              }
            }
          }
        }
      }
    }, 20 * 2 * getConfig().getInt("time-in-seconds-to-remove-1"), 20 * 2 * getConfig().getInt("time-in-seconds-to-remove-1"));
  }
  
  @EventHandler
  public void onRespawn(PlayerRespawnEvent event)
  {
    getConfig().set(event.getPlayer().getName(), Integer.valueOf(10));
    saveConfig();
    
    ScoreboardManager manager = Bukkit.getScoreboardManager();
    Scoreboard board = manager.getNewScoreboard();
    
    Objective objective = board.registerNewObjective(ChatColor.AQUA + getConfig().getString("scoreboard-text"), "dummy");
    objective.setDisplaySlot(DisplaySlot.SIDEBAR);
    objective.setDisplayName(ChatColor.AQUA + getConfig().getString("scoreboard-text"));
    
    Score score = objective.getScore(event.getPlayer());
    score.setScore(getConfig().getInt(event.getPlayer().getName()));
    if (!event.getPlayer().hasPermission("hydrate.nothirst")) {
      event.getPlayer().setScoreboard(board);
    }
  }
}
