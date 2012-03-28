/*    */ package com.Evilgeniuses.Hardcore;
/*    */ 
/*    */ import java.util.Date;
/*    */ 
/*    */ public class DeadPlayer
/*    */ {
/*    */   private String _playerName;
/*    */   private Date DeathDate;
/*    */ 
/*    */   DeadPlayer(String playerName, Date deathDate)
/*    */   {
/* 10 */     setPlayerName(playerName);
/* 11 */     setDeathDate(deathDate);
/*    */   }
/*    */ 
/*    */   public String getPlayerName() {
/* 15 */     return this._playerName;
/*    */   }
/*    */ 
/*    */   public void setPlayerName(String playerName) {
/* 19 */     this._playerName = playerName;
/*    */   }
/*    */ 
/*    */   public Date getDeathDate() {
/* 23 */     return this.DeathDate;
/*    */   }
/*    */ 
/*    */   public void setDeathDate(Date deathDate) {
/* 27 */     this.DeathDate = deathDate;
/*    */   }
/*    */ }

/* Location:           /Users/paul/Desktop/Minecraft/plugins/HardcoreMC_2.6.2.jar
 * Qualified Name:     com.Evilgeniuses.Hardcore.DeadPlayer
 * JD-Core Version:    0.6.0
 */