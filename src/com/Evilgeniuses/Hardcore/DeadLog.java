/*    */ package com.Evilgeniuses.Hardcore;
/*    */ 
/*    */ import java.io.File;
/*    */ import java.io.FileWriter;
/*    */ import java.io.IOException;
/*    */ import java.util.Date;
/*    */ 
/*    */ public class DeadLog
/*    */ {
/*    */   private HardcorePlugin _plugin;
/*    */ 
/*    */   DeadLog(HardcorePlugin plugin)
/*    */   {
/* 13 */     this._plugin = plugin;
/*    */   }
/*    */ 
/*    */   public void addDeadLog(String playerName, Date deadDate, int expLevel, int points, double playerX, double playerY, double playerZ, String deathMessage)
/*    */   {
/* 19 */     File logFile = getDeadlogFile();
/*    */     try
/*    */     {
/*    */       FileWriter fw;
/* 25 */       if (!logFile.exists())
/*    */       {
/* 27 */         FileWriter fw = new FileWriter(logFile, false);
/* 28 */         writeHeader(fw);
/*    */       }
/*    */       else {
/* 31 */         fw = new FileWriter(logFile, true);
/*    */       }
/*    */ 
/* 35 */       fw.write(playerName + ",");
/* 36 */       fw.write(deadDate.toString() + ",");
/* 37 */       fw.write(expLevel + ",");
/* 38 */       fw.write(points + ",");
/* 39 */       fw.write(playerX + ",");
/* 40 */       fw.write(playerY + ",");
/* 41 */       fw.write(playerZ + ",");
/* 42 */       fw.write(deathMessage.replaceAll(",", ""));
/*    */ 
/* 44 */       fw.write(System.getProperty("line.separator"));
/*    */ 
/* 46 */       fw.close();
/*    */     }
/*    */     catch (IOException exc) {
/* 49 */       this._plugin.log("Unable to save dead players file. " + exc.getMessage());
/*    */     }
/*    */   }
/*    */ 
/*    */   private void writeHeader(FileWriter fw) throws IOException {
/* 54 */     fw.write("PlayerName,");
/* 55 */     fw.write("DeadDate,");
/* 56 */     fw.write("ExperienceLevel,");
/* 57 */     fw.write("Points,");
/* 58 */     fw.write("X,");
/* 59 */     fw.write("Y,");
/* 60 */     fw.write("Z,");
/* 61 */     fw.write("DeathMessage");
/* 62 */     fw.write(System.getProperty("line.separator"));
/*    */   }
/*    */ 
/*    */   private File getDeadlogFile() {
/* 66 */     File directory = this._plugin.getDataFolder();
/* 67 */     File listFile = new File(directory, "deadlog.csv");
/* 68 */     return listFile;
/*    */   }
/*    */ }

/* Location:           /Users/paul/Desktop/Minecraft/plugins/HardcoreMC_2.6.2.jar
 * Qualified Name:     com.Evilgeniuses.Hardcore.DeadLog
 * JD-Core Version:    0.6.0
 */