/*     */ package com.Evilgeniuses.Hardcore;
/*     */ 
/*     */ import java.io.BufferedReader;
/*     */ import java.io.File;
/*     */ import java.io.FileReader;
/*     */ import java.io.FileWriter;
/*     */ import java.io.IOException;
/*     */ import java.text.ParseException;
/*     */ import java.text.SimpleDateFormat;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Calendar;
/*     */ import java.util.Date;
/*     */ import org.bukkit.Location;
/*     */ import org.bukkit.entity.Player;
/*     */ 
/*     */ public class DeadPlayerList
/*     */ {
/*  18 */   private ArrayList<DeadPlayer> _deadPlayers = new ArrayList();
/*  19 */   private HardcorePlugin _plugin = null;
/*     */ 
/*     */   DeadPlayerList(HardcorePlugin plugin) {
/*  22 */     this._plugin = plugin;
/*  23 */     load();
/*     */   }
/*     */ 
/*     */   public void addPlayer(Player player, String deathMessage) {
/*  27 */     String playerName = player.getName();
/*  28 */     this._deadPlayers.add(new DeadPlayer(playerName, new Date()));
/*     */ 
/*  30 */     save();
/*     */ 
/*  32 */     DeadLog dl = new DeadLog(this._plugin);
/*  33 */     dl.addDeadLog(playerName, new Date(), player.getLevel(), player.getTotalExperience(), player.getLocation().getX(), player.getLocation().getY(), player.getLocation().getZ(), deathMessage);
/*     */ 
/*  35 */     this._plugin.log(playerName + " was added to the list of the dead. They will be dead until " + whenWillPlayerLive(playerName).toString());
/*     */   }
/*     */ 
/*     */   public void removePlayer(String playerName) {
/*  39 */     boolean found = true;
/*     */ 
/*  42 */     while (found)
/*     */     {
/*  44 */       for (int i = 0; i < this._deadPlayers.size(); i++) {
/*  45 */         if (playerName.compareToIgnoreCase(((DeadPlayer)this._deadPlayers.get(i)).getPlayerName()) == 0) {
/*  46 */           this._plugin.log("Removing " + playerName + " from deadlist.");
/*  47 */           this._deadPlayers.remove(i);
/*  48 */           break;
/*     */         }
/*     */       }
/*     */ 
/*  52 */       found = i != this._deadPlayers.size();
/*     */     }
/*     */ 
/*  56 */     save();
/*     */   }
/*     */ 
/*     */   public boolean isPlayerDead(String playerName, boolean checkForFarewell) {
/*  60 */     boolean isFound = false;
/*     */ 
/*  62 */     for (int i = 0; i < this._deadPlayers.size(); i++)
/*     */     {
/*  66 */       if (playerName.compareToIgnoreCase(((DeadPlayer)this._deadPlayers.get(i)).getPlayerName()) != 0) {
/*     */         continue;
/*     */       }
/*  69 */       if ((this._plugin._config.getBoolean("finalFarewell")) && (checkForFarewell) && 
/*  72 */         (getFarewellDate((DeadPlayer)this._deadPlayers.get(i)).after(new Date())))
/*     */       {
/*     */         continue;
/*     */       }
/*     */ 
/*  80 */       if (getLiveDate((DeadPlayer)this._deadPlayers.get(i)).before(new Date()))
/*     */       {
/*  82 */         DeadPlayer p = (DeadPlayer)this._deadPlayers.get(i);
/*     */ 
/*  85 */         this._plugin.log(playerName + " is ready to come back. They died on " + p.getDeathDate().toString() + " and could come back any time after " + getLiveDate(p).toString() + ".");
/*  86 */         removePlayer(playerName);
/*  87 */         break;
/*     */       }
/*     */ 
/*  92 */       isFound = true;
/*  93 */       break;
/*     */     }
/*     */ 
/*  98 */     return isFound;
/*     */   }
/*     */ 
/*     */   public Date whenWillPlayerLive(String playerName) {
/* 102 */     for (int i = 0; i < this._deadPlayers.size(); i++)
/*     */     {
/* 105 */       if (playerName.compareToIgnoreCase(((DeadPlayer)this._deadPlayers.get(i)).getPlayerName()) == 0)
/*     */       {
/* 107 */         return getLiveDate((DeadPlayer)this._deadPlayers.get(i));
/*     */       }
/*     */     }
/*     */ 
/* 111 */     return new Date();
/*     */   }
/*     */ 
/*     */   private Date getLiveDate(DeadPlayer player) {
/* 115 */     if (this._plugin._config.getBoolean("useResurrection"))
/*     */     {
/* 117 */       if (player.getDeathDate().before(this._plugin._config.getDate("resurrectionDayStart"))) {
/* 118 */         return this._plugin._config.getDate("resurrectionDayStart");
/*     */       }
/*     */ 
/* 121 */       return this._plugin._config.getDate("resurrectionDayEnd");
/*     */     }
/*     */ 
/* 124 */     Calendar c = Calendar.getInstance();
/* 125 */     c.setTime(player.getDeathDate());
/* 126 */     c.add(13, this._plugin._config.getInt("deathDuration_inSeconds"));
/* 127 */     return c.getTime();
/*     */   }
/*     */ 
/*     */   private Date getFarewellDate(DeadPlayer player)
/*     */   {
/* 132 */     Calendar c = Calendar.getInstance();
/* 133 */     c.setTime(player.getDeathDate());
/* 134 */     c.add(13, this._plugin._config.getInt("finalFarewell_inSeconds"));
/* 135 */     return c.getTime();
/*     */   }
/*     */ 
/*     */   private void load() {
/* 139 */     File listFile = getDeadplayerFile();
/* 140 */     if (!listFile.exists())
/* 141 */       this._plugin.log("Dead players file not found, starting a new one.");
/*     */     else
/*     */       try {
/* 144 */         BufferedReader br = new BufferedReader(new FileReader(listFile));
/* 145 */         String line = "";
/* 146 */         while ((line = br.readLine()) != null)
/*     */         {
/* 149 */           if (line.trim() == "") {
/*     */             continue;
/*     */           }
/* 152 */           String[] segments = line.split(",");
/*     */ 
/* 154 */           SimpleDateFormat format = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy");
/*     */ 
/* 156 */           Date deaddate = new Date();
/*     */           try {
/* 158 */             deaddate = format.parse(segments[1]);
/*     */           } catch (ParseException e) {
/* 160 */             this._plugin.log("can't read the date:" + segments[1] + " resetting ban.");
/* 161 */             deaddate = new Date(1L);
/*     */           }
/*     */ 
/* 164 */           DeadPlayer player = new DeadPlayer(segments[0], deaddate);
/* 165 */           this._deadPlayers.add(player);
/*     */         }
/*     */ 
/* 168 */         this._plugin.log(this._deadPlayers.size() + " dead players loaded from the file.");
/*     */       }
/*     */       catch (IOException exc) {
/* 171 */         this._plugin.log("Unable to read dead players file. " + exc.getMessage());
/*     */       }
/*     */   }
/*     */ 
/*     */   private void save()
/*     */   {
/* 179 */     File listFile = getDeadplayerFile();
/*     */ 
/* 182 */     SimpleDateFormat format = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy");
/*     */     try
/*     */     {
/* 185 */       listFile.createNewFile();
/* 186 */       FileWriter fw = new FileWriter(listFile, false);
/*     */ 
/* 188 */       for (int i = 0; i < this._deadPlayers.size(); i++)
/*     */       {
/* 190 */         fw.write(((DeadPlayer)this._deadPlayers.get(i)).getPlayerName());
/* 191 */         fw.write(",");
/* 192 */         fw.write(format.format(((DeadPlayer)this._deadPlayers.get(i)).getDeathDate()));
/* 193 */         fw.write(System.getProperty("line.separator"));
/*     */       }
/*     */ 
/* 196 */       fw.close();
/*     */     } catch (IOException exc) {
/* 198 */       this._plugin.log("Unable to save dead players file. " + exc.getMessage());
/*     */     }
/*     */   }
/*     */ 
/*     */   private File getDeadplayerFile()
/*     */   {
/* 205 */     File directory = this._plugin.getDataFolder();
/* 206 */     File listFile = new File(directory, "deadplayers.txt");
/* 207 */     return listFile;
/*     */   }
/*     */ }

/* Location:           /Users/paul/Desktop/Minecraft/plugins/HardcoreMC_2.6.2.jar
 * Qualified Name:     com.Evilgeniuses.Hardcore.DeadPlayerList
 * JD-Core Version:    0.6.0
 */