/*    */ package com.Evilgeniuses.Hardcore;
/*    */ 
/*    */ import java.util.Date;
/*    */ import java.util.logging.Logger;
/*    */ import org.bukkit.Server;
/*    */ import org.bukkit.command.PluginCommand;
/*    */ import org.bukkit.entity.Player;
/*    */ import org.bukkit.plugin.PluginDescriptionFile;
/*    */ import org.bukkit.plugin.PluginManager;
/*    */ import org.bukkit.plugin.java.JavaPlugin;
/*    */ import org.bukkit.scheduler.BukkitScheduler;
/*    */ 
/*    */ public class HardcorePlugin extends JavaPlugin
/*    */ {
/* 13 */   private Logger _logger = Logger.getLogger("Minecraft");
/*    */   private String _pluginName;
/* 15 */   private DeadPlayerList _deadPlayerList = null;
/* 16 */   public ConfigFile _config = null;
/*    */   public HardcorePlugin _thisPlugin;
/*    */ 
/*    */   public void onDisable()
/*    */   {
/* 22 */     PluginDescriptionFile yml = getDescription();
/* 23 */     this._logger.info(yml.getName() + " is now disabled.");
/*    */   }
/*    */ 
/*    */   public void onEnable()
/*    */   {
/* 28 */     PluginDescriptionFile yml = getDescription();
/* 29 */     this._pluginName = yml.getName();
/* 30 */     log(yml.getVersion() + " loading.");
/*    */ 
/* 32 */     PluginManager pm = getServer().getPluginManager();
/* 33 */     DeathEventListener deathListener = new DeathEventListener(this);
/* 34 */     SpawnEventListener spawnListener = new SpawnEventListener(this);
/*    */ 
/* 37 */     pm.registerEvents(deathListener, this);
/*    */ 
/* 39 */     pm.registerEvents(spawnListener, this);
/*    */ 
/* 41 */     this._config = new ConfigFile();
/* 42 */     this._config.create();
/* 43 */     this._deadPlayerList = new DeadPlayerList(this);
/*    */ 
/* 46 */     HardcoreCommandExecutor cmd = new HardcoreCommandExecutor(this);
/* 47 */     getCommand("hardcore").setExecutor(cmd);
/*    */ 
/* 50 */     this._thisPlugin = this;
/* 51 */     getServer().getScheduler().scheduleSyncRepeatingTask(this._thisPlugin, new Runnable()
/*    */     {
/*    */       public void run()
/*    */       {
/* 55 */         HardcorePlugin.this._thisPlugin.reapDeadPlayers();
/*    */       }
/*    */     }
/*    */     , 200L, this._config.getInt("reaperCheck_inSeconds") * 20L);
/*    */ 
/* 59 */     log(yml.getVersion() + " is now enabled!");
/*    */   }
/*    */ 
/*    */   public void log(String message)
/*    */   {
/* 64 */     this._logger.info("[" + this._pluginName + "] " + message);
/*    */   }
/*    */ 
/*    */   public DeadPlayerList getDeadPlayerList() {
/* 68 */     return this._deadPlayerList;
/*    */   }
/*    */ 
/*    */   public ConfigFile getHardcoreConfiguration() {
/* 72 */     return this._config;
/*    */   }
/*    */ 
/*    */   public void reapDeadPlayers()
/*    */   {
/* 79 */     Player[] onlinePlayers = getServer().getOnlinePlayers();
/*    */ 
/* 82 */     for (int i = 0; i < onlinePlayers.length; i++) {
/* 83 */       Player player = onlinePlayers[i];
/* 84 */       String playerName = player.getName();
/*    */ 
/* 86 */       if (!getDeadPlayerList().isPlayerDead(playerName, true)) {
/*    */         continue;
/*    */       }
/* 89 */       String livedate = getDeadPlayerList().whenWillPlayerLive(playerName).toString();
/* 90 */       if (this._config.getBoolean("showlivedate"))
/* 91 */         player.kickPlayer(this._config.getString("kickmessage") + livedate);
/*    */       else
/* 93 */         player.kickPlayer(this._config.getString("kickmessage"));
/* 94 */       this._thisPlugin.log("The reaper has caught up with " + playerName + " and taken them away.");
/*    */     }
/*    */   }
/*    */ }

/* Location:           /Users/paul/Desktop/Minecraft/plugins/HardcoreMC_2.6.2.jar
 * Qualified Name:     com.Evilgeniuses.Hardcore.HardcorePlugin
 * JD-Core Version:    0.6.0
 */