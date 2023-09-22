package plusfile;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.LineNumberReader;
import java.io.PrintWriter;
import java.util.HashMap;

public class PlusFile {
	
	private final File file;
	private boolean console;
	private final String split;
	
	public PlusFile(String path, boolean console) {
		this(path, console, "=");
	}
	
	public PlusFile(String path, boolean console, String split) {
		this.file = new File(path);
		this.console = console;
		this.split = split;
		
		this.printToConsole("New File: " + file.getAbsolutePath());
	}
	
	public File getFile() {
		return this.file;
	}
	
	public boolean isConsoleEnabled() {
		return this.console;
	}
	public void setConsoleEnabled(boolean console) {
		this.console = console;
	}
	
	public String getPath() {
		String path = this.file.getAbsolutePath().replace("\\", "/");
		return path.substring(0, path.lastIndexOf('/'));
	}
	public String getName() {
		return this.file.getName();
	}
	
	/**
	 * Creating File
	 */
	private void create() {
		try {
			if (!file.getCanonicalFile().exists()) {
				File dirs = new File(this.getPath());
				dirs.mkdirs();
	        }
			
			if(this.file.createNewFile()) {
				this.printToConsole("Creating File: " + this.file.getAbsolutePath());
			}
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Deleting File
	 */
	public void deleteFile() {
		if(this.file.exists()) {
			this.printToConsole("Deleting File: " + this.file.getAbsolutePath());
			this.file.delete();
		}
	}
	
	/**
	 * File Exists
	 */
	public boolean exists() {
		return this.file.exists();
	}
	
	/**
	 * Writing Message in Console if allowed
	 * @param message Message to write
	 */
	private void printToConsole(String message) {
		if(this.console) System.out.println(message);
	}
	
	/**
	 * Get value of Element in file
	 * @param id String Element
	 * @return value of Element as Object
	 */
	public String getValue(final String id) {
		HashMap<String, String> data = this.updateData();
		
		if(!data.containsKey(id)) return null;
		
		return data.get(id);
	}
	
	/**
	 * Gets Integer Value of Element in file
	 * @param id String Element
	 * @return Integer value of Element
	 */
	public int getInt(final String id) {
		return Integer.parseInt(getValue(id));
	}
	
	/**
	 * Set Integer Value of Element in file
	 * @param id String Element
	 * @param value Integer Value to set
	 */
	public void setInt(final String id, final int value) {
		this.setValue(id, String.valueOf(value));
	}
	
	/**
	 * Adds Integer to (int) value of id
	 * @param id String Element
	 * @param amount Adds this value
	 */
	public void addInt(final String id, final int amount) {
		
		int current = 0;
		
		try {
			current = getInt(id);
		}catch(NumberFormatException e) {}
		
		int now = current + amount;
		
		this.setValue(id, String.valueOf(now));
	}
	
	/**
	 * Removes Integer from (int) value of id
	 * @param id String Element
	 * @param amount removes this value
	 */
	public void removeInt(final String id, final int amount) {
		
		int current = 0;
		
		try {
			current = getInt(id);
		}catch(NumberFormatException e) {}
		
		int now = current - amount;
		
		this.setValue(id, String.valueOf(now));
	}
	
	/**
	 * Set Boolean Value of Element in file
	 * @param id String Element
	 * @param value Boolean Value to set
	 */
	public void setBoolean(final String id, final boolean value) {
		this.setValue(id, String.valueOf(value));
	}
	
	/**
	 * Get Boolean Value of Element in file
	 * @param id String Element
	 * @return Boolean value of Element
	 */
	public boolean getBoolean(final String id) {
		return Boolean.parseBoolean(getValue(id));
	}
	
	/**
	 * Writes line on specific Row
	 * @param row Row as Object
	 * @param newData Writes this value in specific row
	 */
	public void setValue(String key, String value) {
		HashMap<String, String> data = this.updateData();
		
		data.put(key, value);
		
		try {
			// Clear the file contents
	        PrintWriter clearFile = new PrintWriter(this.file);
	        clearFile.close();
	        
			// Write into File
		    FileWriter fw = new FileWriter(this.file, true);
			BufferedWriter bw = new BufferedWriter(fw);
			PrintWriter pw = new PrintWriter(bw);
			
			for(String entry : data.keySet()) {
				pw.println(entry + this.split + data.get(entry));
			}
			
			pw.close();
		    
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * @return HashMap<String, String> of the data in the file
	 */
	private HashMap<String, String> updateData() {
		this.create();
		
		HashMap<String, String> data = new HashMap<String, String>();
		
		FileReader fr = null;
		LineNumberReader lines = null;
		
		if(this.file != null) {
			
			if(!this.file.exists()) {
				this.create();
			}

			try {
				fr = new FileReader(this.file);
				lines = new LineNumberReader(fr);
				
				String line = null;
				data.clear();
				
				while ((line = lines.readLine()) != null) {
					String[] key_value = line.split(this.split);
					data.put(key_value[0], key_value[1]);
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		return data;
	}
}
