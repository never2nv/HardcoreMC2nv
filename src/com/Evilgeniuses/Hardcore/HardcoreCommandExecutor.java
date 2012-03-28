/*     */ package com.Evilgeniuses.Hardcore;
/*     */ 
/*     */ import org.bukkit.Server;
/*     */ import org.bukkit.command.Command;
/*     */ import org.bukkit.command.CommandExecutor;
/*     */ import org.bukkit.command.CommandSender;
/*     */ import org.bukkit.entity.Player;
/*     */ 
/*     */ public class HardcoreCommandExecutor
/*     */   implements CommandExecutor
/*     */ {
/*     */   private HardcorePlugin _plugin;
/*     */ 
/*     */   HardcoreCommandExecutor(HardcorePlugin plugin)
/*     */   {
/*  14 */     this._plugin = plugin;
/*     */   }
/*     */ 
/*     */   public boolean onCommand(CommandSender sender, Command command, String label, String[] args)
/*     */   {
/*  21 */     Player player = null;
/*  22 */     if ((sender instanceof Player)) {
/*  23 */       player = (Player)sender;
/*     */     }
/*     */ 
/*  26 */     if (command.getName().equalsIgnoreCase("hardcore"))
/*     */     {
/*  28 */       if (args.length == 0) {
/*  29 */         return false;
/*     */       }
/*  31 */       if (args[0].equalsIgnoreCase("res"))
/*  32 */         return runResCommand(sender, player, args);
/*  33 */       if (args[0].equalsIgnoreCase("slay"))
/*  34 */         return runSlayCommand(sender, player, args);
/*  35 */       if (args[0].equalsIgnoreCase("info")) {
/*  36 */         return runInfoCommand(sender, player, args);
/*     */       }
/*  38 */       return false;
/*     */     }
/*     */ 
/*  42 */     return true;
/*     */   }
/*     */ 
/*     */   private boolean runSlayCommand(CommandSender sender, Player player, String[] args)
/*     */   {
/*  49 */     if ((player == null) || (player.hasPermission("hardcoremc.slay")) || (player.isOp()))
/*     */     {
/*  51 */       if (args.length == 2)
/*     */       {
/*  53 */         String playerName = args[1];
/*     */ 
/*  56 */         if (this._plugin.getDeadPlayerList().isPlayerDead(playerName, false))
/*     */         {
/*  60 */           sender.sendMessage(playerName + " is already on the dead list. Nothing to do.");
/*  61 */           return false;
/*     */         }
/*     */ 
/*  67 */         Player victim = this._plugin.getServer().getPlayerExact(playerName);
/*  68 */         if (victim == null) {
/*  69 */           sender.sendMessage(playerName + " was not found, they must be online to slay.");
/*  70 */           return false;
/*     */         }
/*  72 */         victim.damage(10000);
/*  73 */         sender.sendMessage(playerName + " slain. When the reaper comes, they will be kicked.");
/*  74 */         return true;
/*     */       }
/*     */ 
/*  81 */       sender.sendMessage("You must supply a playername.");
/*  82 */       return false;
/*     */     }
/*     */ 
/*  89 */     sender.sendMessage("You don't have permission to run this command.");
/*  90 */     return true;
/*     */   }
/*     */ 
/*     */   private boolean runResCommand(CommandSender sender, Player player, String[] args)
/*     */   {
/*  98 */     if ((player == null) || (player.hasPermission("hardcoremc.res")) || (player.isOp()))
/*     */     {
/* 100 */       if (args.length == 2)
/*     */       {
/* 102 */         String playerName = args[1];
/*     */ 
/* 105 */         if (this._plugin.getDeadPlayerList().isPlayerDead(playerName, false))
/*     */         {
/* 108 */           this._plugin.getDeadPlayerList().removePlayer(playerName);
/* 109 */           sender.sendMessage(playerName + " was removed from the dead list. They should be able to log on again.");
/* 110 */           return true;
/*     */         }
/*     */ 
/* 115 */         sender.sendMessage(playerName + " was not on the dead list. Nothing to do.");
/* 116 */         return false;
/*     */       }
/*     */ 
/* 122 */       sender.sendMessage("You must supply a playername.");
/* 123 */       return false;
/*     */     }
/*     */ 
/* 130 */     sender.sendMessage("You don't have permission to run this command.");
/* 131 */     return true;
/*     */   }
/*     */ 
/*     */   private boolean runInfoCommand(CommandSender sender, Player player, String[] args)
/*     */   {
/* 141 */     if ((player == null) || (player.hasPermission("hardcoremc.info")) || (player.isOp()))
/*     */     {
/* 143 */       if (args.length == 2)
/*     */       {
/* 145 */         String playerName = args[1];
/*     */ 
/* 147 */         if (this._plugin.getDeadPlayerList().isPlayerDead(playerName, false))
/*     */         {
/* 149 */           sender.sendMessage(playerName + " is dead and will be allowed back on " + this._plugin.getDeadPlayerList().whenWillPlayerLive(playerName));
/*     */         }
/*     */         else
/*     */         {
/* 153 */           sender.sendMessage(playerName + " is not on the dead list.");
/*     */         }
/*     */ 
/*     */       }
/*     */       else
/*     */       {
/* 159 */         sender.sendMessage("You must supply a playername.");
/* 160 */         return false;
/*     */       }
/*     */ 
/*     */     }
/*     */     else
/*     */     {
/* 167 */       sender.sendMessage("You don't have permission to run this command.");
/* 168 */       return true;
/*     */     }
/*     */ 
/* 171 */     return true;
/*     */   }
/*     */ }

/* Location:           /Users/paul/Desktop/Minecraft/plugins/HardcoreMC_2.6.2.jar
 * Qualified Name:     com.Evilgeniuses.Hardcore.HardcoreCommandExecutor
 * JD-Core Version:    0.6.0
 */