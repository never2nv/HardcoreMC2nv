/*     */ package com.Evilgeniuses.Hardcore;
/*     */ 
/*     */ import java.io.File;
/*     */ import java.io.FileInputStream;
/*     */ import java.io.FileNotFoundException;
/*     */ import java.io.FileOutputStream;
/*     */ import java.io.IOException;
/*     */ import java.text.DateFormat;
/*     */ import java.text.ParseException;
/*     */ import java.util.Date;
/*     */ import java.util.Enumeration;
/*     */ import java.util.Hashtable;
import java.util.Properties;
/*     */ 
/*     */ public class ConfigFile extends Properties
/*     */ {
/*     */   private static final long serialVersionUID = 1L;
/*     */   private ConfigFile configuration;
/*  19 */   private static String mainDirectory = "plugins/Hardcore";
/*     */   private FileOutputStream out;
/*     */   private FileInputStream in;
/*  22 */   private File config = new File(mainDirectory + File.separator + "HCMC.properties");
/*  23 */   private Hashtable<String, String> defaults = new Hashtable<String, String>();
/*     */ 
/*     */   public ConfigFile()
/*     */   {
/*  27 */     this.defaults.put("deathDuration_inSeconds", "604800");
/*  28 */     this.defaults.put("reaperCheck_inSeconds", "5");
/*  29 */     this.defaults.put("finalFarewell", "no");
/*  30 */     this.defaults.put("finalFarewell_inSeconds", "60");
/*  31 */     this.defaults.put("Thunder&Lightning", "yes");
/*  32 */     this.defaults.put("ThunderLength_inSeconds", "1");
/*  33 */     this.defaults.put("useResurrection", "no");
/*  34 */     this.defaults.put("resurrectionDayStart", new String(DateFormat.getInstance().format(new Date(0L))));
/*  35 */     this.defaults.put("resurrectionDayEnd", new String(DateFormat.getInstance().format(new Date(1L))));
/*  36 */     this.defaults.put("kickmessage", "You have died. You will be dead until");
/*  37 */     this.defaults.put("showlivedate", "yes");
/*     */   }
/*     */ 
/*     */   public void setFile(File file)
/*     */   {
/*  42 */     this.config = file;
/*     */   }
/*     */ 
/*     */   public void create()
/*     */   {
/*     */     try {
/*  48 */       if (!new File(mainDirectory).exists())
/*  49 */         new File(mainDirectory).mkdir();
/*  50 */       if (!this.config.exists())
/*  51 */         this.config.createNewFile();
/*  52 */       this.configuration = new ConfigFile();
/*  53 */       this.in = new FileInputStream(this.config);
/*  54 */       getConfigurationSet().load(this.in);
/*  55 */       this.out = new FileOutputStream(this.config);
/*     */ 
/*  58 */       Enumeration<String> en = this.defaults.keys();
/*  59 */       while (en.hasMoreElements())
/*     */       {
/*  61 */         String key = ((String)en.nextElement()).toString();
/*  62 */         if (!getConfigurationSet().containsKey(key))
/*  63 */           getConfigurationSet().put(key, this.defaults.get(key));
/*     */       }
/*  65 */       getConfigurationSet().store(this.out, "HC-MC ConfigFile");
/*  66 */       this.out.flush();
/*     */     } catch (IOException e) {
/*  68 */       e.printStackTrace();
/*     */     }
/*     */   }
/*     */ 
/*     */   public ConfigFile getConfig()
/*     */   {
/*     */     try {
/*  75 */       this.in = new FileInputStream(this.config);
/*  76 */       getConfigurationSet().load(this.in);
/*     */     } catch (FileNotFoundException e) {
/*  78 */       e.printStackTrace();
/*     */     } catch (IOException e) {
/*  80 */       e.printStackTrace();
/*     */     }
/*  82 */     return getConfigurationSet();
/*     */   }
/*     */ 
/*     */   public Date getDate(String key)
/*     */   {
/*  87 */     String in = getConfigurationSet().getProperty(key);
/*     */     try {
/*  89 */       return DateFormat.getInstance().parse(in);
/*     */     } catch (ParseException localParseException) {
/*     */     }
/*  92 */     return null;
/*     */   }
/*     */ 
/*     */   public boolean getBoolean(String key)
/*     */   {
/*  97 */     String in = getConfigurationSet().getProperty(key, "yes");
/*     */ 
/*  99 */     return in.equalsIgnoreCase("yes");
/*     */   }
/*     */ 
/*     */   public String getString(String key)
/*     */   {
/* 105 */     String in = getConfigurationSet().getProperty(key, "Config option not found!");
/* 106 */     return in;
/*     */   }
/*     */ 
/*     */   public int getInt(String key) {
/* 110 */     String in = getConfigurationSet().getProperty(key, "0");
/*     */     try {
/* 112 */       int inte = Integer.parseInt(in.trim());
/* 113 */       return inte;
/*     */     }
/*     */     catch (Exception e) {
/*     */     }
/* 117 */     return 0;
/*     */   }
/*     */ 
/*     */   public void setBoolean(String key, boolean bool)
/*     */   {
/* 122 */     String out = "";
/* 123 */     if (bool)
/* 124 */       out = "yes";
/*     */     else
/* 126 */       out = "no";
/* 127 */     getConfigurationSet().setProperty(key, out);
/*     */     try {
/* 129 */       getConfigurationSet().store(this.out, "HC-MC");
/*     */     } catch (IOException e) {
/* 131 */       e.printStackTrace();
/*     */     }
/*     */   }
/*     */ 
/*     */   public void setString(String key, String out)
/*     */   {
/* 137 */     getConfigurationSet().setProperty(key, out);
/*     */     try {
/* 139 */       getConfigurationSet().store(this.out, "HC-MC");
/*     */     } catch (IOException e) {
/* 141 */       e.printStackTrace();
/*     */     }
/*     */   }
/*     */ 
/*     */   public void setInt(String key, int i) {
/* 146 */     String out = "";
/* 147 */     out = Integer.toString(i);
/* 148 */     getConfigurationSet().setProperty(key, out);
/*     */     try {
/* 150 */       getConfigurationSet().store(this.out, "HC-MC");
/*     */     } catch (IOException e) {
/* 152 */       e.printStackTrace();
/*     */     }
/*     */   }
/*     */ 
/*     */   public ConfigFile getConfigurationSet() {
/* 157 */     return this.configuration;
/*     */   }
/*     */ 
/*     */   public Hashtable<String, String> getDefaults()
/*     */   {
/* 162 */     return this.defaults;
/*     */   }
/*     */ }

/* Location:           /Users/paul/Desktop/Minecraft/plugins/HardcoreMC_2.6.2.jar
 * Qualified Name:     com.Evilgeniuses.Hardcore.ConfigFile
 * JD-Core Version:    0.6.0
 */