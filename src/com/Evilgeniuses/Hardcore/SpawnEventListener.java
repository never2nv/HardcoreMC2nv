/*    */ package com.Evilgeniuses.Hardcore;
/*    */ 
/*    */ import java.util.Date;
/*    */ import org.bukkit.entity.Player;
/*    */ import org.bukkit.event.EventHandler;
/*    */ import org.bukkit.event.EventPriority;
/*    */ import org.bukkit.event.Listener;
/*    */ import org.bukkit.event.player.PlayerLoginEvent;
/*    */ import org.bukkit.event.player.PlayerLoginEvent.Result;
/*    */ 
/*    */ public class SpawnEventListener
/*    */   implements Listener
/*    */ {
/* 10 */   private HardcorePlugin _plugin = null;
/*    */ 
/*    */   SpawnEventListener(HardcorePlugin plugin) {
/* 13 */     this._plugin = plugin;
/*    */   }
/*    */   @EventHandler(priority=EventPriority.NORMAL)
/*    */   public void onPlayerLogin(PlayerLoginEvent event) {
/* 18 */     String playerName = event.getPlayer().getName();
/*    */ 
/* 20 */     this._plugin.log(playerName + " is trying to log in.");
/*    */ 
/* 23 */     if (this._plugin.getDeadPlayerList().isPlayerDead(playerName, true))
/*    */     {
/* 25 */       String livedate = this._plugin.getDeadPlayerList().whenWillPlayerLive(playerName).toString();
/* 26 */       event.setKickMessage("You will be dead until " + livedate);
/* 27 */       event.setResult(PlayerLoginEvent.Result.KICK_BANNED);
/* 28 */       this._plugin.log(playerName + " was not allowed to login for being dead until " + livedate + ".");
/*    */     }
/*    */   }
/*    */ }

/* Location:           /Users/paul/Desktop/Minecraft/plugins/HardcoreMC_2.6.2.jar
 * Qualified Name:     com.Evilgeniuses.Hardcore.SpawnEventListener
 * JD-Core Version:    0.6.0
 */