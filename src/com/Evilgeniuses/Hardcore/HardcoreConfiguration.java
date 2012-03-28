/*     */ package com.Evilgeniuses.Hardcore;
/*     */ 
/*     */ import java.text.DateFormat;
/*     */ import java.text.ParseException;
/*     */ import java.util.Date;
/*     */ import org.bukkit.configuration.Configuration;
/*     */ 
/*     */ public class HardcoreConfiguration
/*     */ {
/*  12 */   private int DEFAULT_DEATH_DURATION_SECONDS = 604800;
/*  13 */   private int DEFAULT_REAPER_CHECK_SECONDS = 5;
/*  14 */   private String DEFAULT_KICK_MESSAGE = "You have died. You will be dead until";
/*  15 */   private String DEFAULT_KICK_DATE_MESSAGE = "You have died. You will be dead until";
/*     */   private String livedate;
/*  16 */   private boolean DEFAULT_FINAL_FAREWELL = false;
/*  17 */   private int DEFAULT_FINAL_FAREWELL_SECONDS = 60;
/*  18 */   private boolean DEFAULT_THUNDER_LIGHTNING = true;
/*  19 */   private int DEFAULT_THUNDER_LENGTH_SECONDS = 1;
/*  20 */   private boolean DEFAULT_USE_RESURRECTION_DAY = false;
/*  21 */   private String DEFAULT_RESURRECTION_DAY_START = DateFormat.getInstance().format(new Date(0L));
/*  22 */   private String DEFAULT_RESURRECTION_DAY_END = DateFormat.getInstance().format(new Date(1L));
/*     */   public int deathSeconds;
/*     */   public int reaperCheckSeconds;
/*     */   public String kickmessage;
/*     */   public String kickwithdatemessage;
/*     */   public boolean finalFarewell;
/*     */   public int finalFarewellSeconds;
/*     */   public boolean doThunderAndLightningOnDeath;
/*     */   public int thunderLengthSeconds;
/*     */   public boolean useResurrectionDay;
/*     */   public Date resurrectionDayStart;
/*     */   public Date resurrectionDayEnd;
/*     */ 
/*     */   HardcoreConfiguration(HardcorePlugin plugin)
/*     */   {
/*  37 */     Configuration config = plugin.getConfig();
/*     */ 
/*  39 */     this.deathSeconds = config.getInt("deathSeconds", this.DEFAULT_DEATH_DURATION_SECONDS);
/*  40 */     if (this.deathSeconds == 0) {
/*  41 */       config.set("deathSeconds", Integer.valueOf(this.DEFAULT_DEATH_DURATION_SECONDS));
/*     */     }
/*  43 */     this.kickmessage = config.getString("kickmessage, DEFAULT_KICK_MESSAGE");
/*  44 */     if (this.kickmessage == "You have died. You will be dead until") {
/*  45 */       config.set("kickmessage", this.DEFAULT_KICK_MESSAGE);
/*     */     }
/*  47 */     this.kickwithdatemessage = config.getString("kickdatemessage", this.DEFAULT_KICK_DATE_MESSAGE);
/*  48 */     if (this.kickwithdatemessage == "You have died. You will be dead until" + this.livedate) {
/*  49 */       config.set("kickdatemessage", this.DEFAULT_KICK_DATE_MESSAGE);
/*     */     }
/*  51 */     this.reaperCheckSeconds = config.getInt("reaperCheckSeconds", this.DEFAULT_REAPER_CHECK_SECONDS);
/*  52 */     if (this.reaperCheckSeconds == 0) {
/*  53 */       config.set("reaperCheckSeconds", Integer.valueOf(this.DEFAULT_REAPER_CHECK_SECONDS));
/*     */     }
/*     */ 
/*  56 */     this.finalFarewell = config.getBoolean("finalFarewell", this.DEFAULT_FINAL_FAREWELL);
/*  57 */     if (this.finalFarewell) {
/*  58 */       config.set("deathSeconds", Integer.valueOf(this.DEFAULT_DEATH_DURATION_SECONDS));
/*     */     }
/*  60 */     this.finalFarewellSeconds = config.getInt("finalFarewellSeconds", this.DEFAULT_FINAL_FAREWELL_SECONDS);
/*  61 */     if (this.deathSeconds == 0) {
/*  62 */       config.set("deathSeconds", Integer.valueOf(this.DEFAULT_DEATH_DURATION_SECONDS));
/*     */     }
/*  64 */     this.doThunderAndLightningOnDeath = config.getBoolean("doThunderAndLightningOnDeath", this.DEFAULT_THUNDER_LIGHTNING);
/*  65 */     if (this.deathSeconds == 0) {
/*  66 */       config.set("deathSeconds", Integer.valueOf(this.DEFAULT_DEATH_DURATION_SECONDS));
/*     */     }
/*  68 */     this.thunderLengthSeconds = config.getInt("thunderLengthSeconds", this.DEFAULT_THUNDER_LENGTH_SECONDS);
/*  69 */     if (this.deathSeconds == 0) {
/*  70 */       config.set("deathSeconds", Integer.valueOf(this.DEFAULT_DEATH_DURATION_SECONDS));
/*     */     }
/*  72 */     this.useResurrectionDay = config.getBoolean("useResurrectionDay", this.DEFAULT_USE_RESURRECTION_DAY);
/*  73 */     if (this.deathSeconds == 0) {
/*  74 */       config.set("deathSeconds", Integer.valueOf(this.DEFAULT_DEATH_DURATION_SECONDS));
/*     */     }
/*  76 */     String resDateRawStart = config.getString("resurrectionDayStart", this.DEFAULT_RESURRECTION_DAY_START);
/*  77 */     String resDateRawEnd = config.getString("resurrectionDayEnd", this.DEFAULT_RESURRECTION_DAY_END);
/*     */     try
/*     */     {
/*  80 */       this.resurrectionDayStart = DateFormat.getInstance().parse(resDateRawStart);
/*     */     } catch (ParseException exc) {
/*  82 */       plugin.log("Cound not read resurectionDayStart setting: (" + resDateRawStart + "), it needs to be in MM/DD/YYYY HH:MM am/pm. Resurection Day is turned off, defaulting to rolling kill date.");
/*  83 */       this.useResurrectionDay = false;
/*     */     }
/*     */     try
/*     */     {
/*  87 */       this.resurrectionDayEnd = DateFormat.getInstance().parse(resDateRawEnd);
/*     */     } catch (ParseException exc) {
/*  89 */       plugin.log("Cound not read resurectionDayEnd setting: (" + resDateRawEnd + "), it needs to be in MM/DD/YYYY HH:MM am/pm. Resurection Day is turned off, defaulting to rolling kill date.");
/*  90 */       this.useResurrectionDay = false;
/*     */     }
/*     */ 
/*  94 */     if (this.useResurrectionDay) {
/*  95 */       if (!this.resurrectionDayStart.before(this.resurrectionDayEnd)) {
/*  96 */         plugin.log("The start of the Resurrection must be before the end. Resurection Day is turned off, defaulting to rolling kill date.");
/*  97 */         this.useResurrectionDay = false;
/*     */       }
/*     */       else {
/* 100 */         plugin.log("Resurrection is between: " + this.resurrectionDayStart.toString() + " and " + this.resurrectionDayEnd.toString());
/*     */       }
/*     */     }
/* 103 */     plugin.saveConfig();
/*     */   }
/*     */ }

/* Location:           /Users/paul/Desktop/Minecraft/plugins/HardcoreMC_2.6.2.jar
 * Qualified Name:     com.Evilgeniuses.Hardcore.HardcoreConfiguration
 * JD-Core Version:    0.6.0
 */