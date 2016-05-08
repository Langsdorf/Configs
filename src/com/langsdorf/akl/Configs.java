package com.langsdorf.akl;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.Plugin;

public class Configs {
	
	private Plugin pl;
	private File f;
	private FileConfiguration fc;
	private String fname;
	private String path;
	
	public Configs(Plugin plugin, String fileName, String path) {
		this.pl = plugin;
		this.fname = fileName;
		this.path = path;
	}
	
	public Configs(Plugin plugin, String fileName) {
		this.pl = plugin;
		this.fname = fileName;
		this.path = pl.getDataFolder().getPath();
	}
	
	public Configs(String fileName, String path) {
		this.pl = null;
		this.fname = fileName;
		this.path = path;
	}
	
	/**
	 * Crie o arquivo.
	 * 
	 * @param isYML Coloque "true" se o arquivo for YML, se não, coloque "false";
	 * @param copyDefaults Coloque "true" se o arquivo for YML e você queria copiar o padrão que está no projeto.
	 * 
	 */
	public boolean saveDefault(boolean isYML, boolean copyDefaults) throws IOException, NullPointerException{
		if (isYML && pl != null) {
			if (f == null) f = new File(path + "/" + fname);
			fc = YamlConfiguration.loadConfiguration(f);
			if (!f.exists()) {
				if (copyDefaults == true) {
					@SuppressWarnings("deprecation")
					YamlConfiguration df = YamlConfiguration.loadConfiguration(pl.getResource(fname));
					if (df != null) {
						fc.addDefaults(df);
						fc.options().copyDefaults(true);
					}
				}
				pl.saveResource(fname, true);
			}
			return true;
		}
		if (pl == null || !isYML) {
			if (f == null) f = new File(path + "/" + fname);
			fc = null;
			if (!f.exists()) f.createNewFile();
			return true;
		}
		return false;
	}
	
	public File getFile() throws NullPointerException{
		return f;
	}
	
	public FileConfiguration getFileConfiguration() throws NullPointerException {
		return fc;
	}
	
	public boolean save() throws IOException, NullPointerException {
		if (pl != null && fc != null) fc.save(f);
		if (pl == null || fc == null || f == null) return false;
		return true;
	}
	
	public boolean write(boolean isYML, String key, String... values) throws NullPointerException, IOException {
		if (isYML && pl != null && fc != null && f != null) {
			fc.set(key, values);
			save();
			return true;
		}
		if (!isYML || pl == null || fc == null && f != null) {
			FileWriter w = new FileWriter(f.getAbsoluteFile(), true);
			for (String s : values) w.write(s.trim());
			w.close();
			return true;
		}
		return false;
	}
	
	public boolean write(boolean isYML, String... values) throws NullPointerException, IOException {
		return write(isYML, "", values);
	}
	
	public boolean write(boolean isYML, String values) throws NullPointerException, IOException {
		return write(isYML, "", values);
	}
	
	public boolean write(boolean isYML, Object... values) throws NullPointerException, IOException {
		return write(isYML, "", values);
	}
	
	public boolean write(boolean isYML, Object values) throws NullPointerException, IOException {
		return write(isYML, "", values);
	}
	
	public boolean write(boolean isYML, String key, Object values) throws NullPointerException, IOException {
		if (isYML && pl != null && fc != null && f != null) {
			fc.set(key, values);
			save();
			return true;
		}
		if (!isYML || pl == null || fc == null && f != null) {
			FileWriter w = new FileWriter(f.getAbsoluteFile(), true);
			w.write(values.toString().trim());
			w.close();
			return true;
		}
		return false;
	}
	
	public boolean write(boolean isYML, String key, Object... values) throws NullPointerException, IOException {
		if (isYML && pl != null && fc != null && f != null) {
			fc.set(key, values);
			save();
			return true;
		}
		if (!isYML || pl == null || fc == null && f != null) {
			FileWriter w = new FileWriter(f.getAbsoluteFile(), true);
			for (Object o : values) w.write(o.toString().trim());
			w.close();
			return true;
		}
		return false;
	}
	
	public boolean write(boolean isYML, String key, String values) throws NullPointerException, IOException {
		if (isYML && pl != null && fc != null && f != null) {
			fc.set(key, values);
			save();
			return true;
		}
		if (!isYML || pl == null || fc == null && f != null) {
			FileWriter w = new FileWriter(f.getAbsoluteFile(), true);
			w.write(values);
			w.close();
			return true;
		}
		return false;
	}
	
	
	public boolean write(boolean isYML, List<String> list) throws NullPointerException, IOException {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < list.size(); i++) sb.append(list.get(i) + "@%adsw%@");
		return write(isYML, "", sb.toString().split("@%adsw%@"));
	}
	
	public boolean write(boolean isYML, String key, List<String> list) throws NullPointerException, IOException {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < list.size(); i++) sb.append(list.get(i) + "@%adsw%@");
		return write(isYML, key, sb.toString().split("@%adsw%@"));
	}
	
	public boolean reloadConfig() throws NullPointerException, IOException {
		if (pl != null && fc != null) saveDefault(true, false);
		return true;
	}
	
	public String getPath() {
		return path;
	}
	
	public String getFileName() {
		return fname;
	}
	
	public String getFileType() throws NullPointerException{
		if (fname.contains(".") && fname.substring(fname.length() - 5).contains(".")) return fname.substring(fname.length() - 4).replace(".", "");
		return "";
	}
	
	public Plugin getPlugin() {
		return pl;
	}
	
}