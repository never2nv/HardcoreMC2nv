/*    */ package com.Evilgeniuses.Hardcore;
/*    */ 
/*    */ import org.bukkit.ChatColor;
/*    */ import org.bukkit.Location;
/*    */ import org.bukkit.World;
/*    */ import org.bukkit.entity.Player;
/*    */ import org.bukkit.event.EventHandler;
/*    */ import org.bukkit.event.EventPriority;
/*    */ import org.bukkit.event.Listener;
/*    */ import org.bukkit.event.entity.EntityDeathEvent;
/*    */ import org.bukkit.event.entity.PlayerDeathEvent;
/*    */ 
/*    */ public class DeathEventListener
/*    */   implements Listener
/*    */ {
/* 15 */   private HardcorePlugin _plugin = null;
/*    */ 
/*    */   DeathEventListener(HardcorePlugin plugin) {
/* 18 */     this._plugin = plugin;
/*    */   }
/*    */ 
/*    */   @EventHandler(priority=EventPriority.NORMAL)
/*    */   public void onEntityDeath(EntityDeathEvent event) {
/* 24 */     if (!(event instanceof PlayerDeathEvent)) {
/* 25 */       return;
/*    */     }
/* 27 */     Player player = (Player)event.getEntity();
/* 28 */     PlayerDeathEvent de = (PlayerDeathEvent)event;
/*    */ 
/* 30 */     if (player.hasPermission("hardcoremc.exempt")) {
/* 31 */       return;
/*    */     }
/*    */ 
/* 34 */     if (!this._plugin.getDeadPlayerList().isPlayerDead(player.getName(), false)) {
/* 35 */       this._plugin.getDeadPlayerList().addPlayer(player, de.getDeathMessage());
/*    */ 
/* 38 */       if (this._plugin._config.getBoolean("finalFarewell")) {
/* 39 */         doFinalFarewellMessage(player);
/*    */       }
/*    */     }
/*    */ 
/* 43 */     if (this._plugin._config.getBoolean("Thunder&Lightning"))
/* 44 */       doSoundAndFury(player.getLocation(), player.getWorld());
/*    */   }
/*    */ 
/*    */   private void doFinalFarewellMessage(Player player)
/*    */   {
/* 49 */     player.sendMessage(ChatColor.RED + "You have died. You have been granted " + this._plugin._config.getInt("finalFarewell_inSeconds") + " seconds to say your final farewell.");
/*    */   }
/*    */ 
/*    */   public void doSoundAndFury(Location where, World whatWorld)
/*    */   {
/* 54 */     whatWorld.strikeLightningEffect(where);
/* 55 */     whatWorld.setThunderDuration(this._plugin._config.getInt("ThunderLength_inSeconds") * 20);
/* 56 */     whatWorld.setThundering(true);
/*    */   }
/*    */ }

/* Location:           /Users/paul/Desktop/Minecraft/plugins/HardcoreMC_2.6.2.jar
 * Qualified Name:     com.Evilgeniuses.Hardcore.DeathEventListener
 * JD-Core Version:    0.6.0
 */