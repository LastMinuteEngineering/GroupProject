package data.storage;

import java.util.HashMap;
import data.entry.*;

public class Storage {

	private Integer capacity;
	private String address;
	private HashMap<String, Entry> entries;
	private static Storage instance;
	
	private static String module = "Storage:\t";  
	
	private Storage(String address, Integer capacity) {
		this.address = address;
		this.capacity = capacity;
		this.entries= new HashMap<>();
	};
	
	public static synchronized void createDatabase() {
		// create instance if necessary.
		if (instance == null) {
			instance = new Storage("www.LastMinuteEngineering.com/storage", 100);
		}
	}
	
	public static Storage getInstance() {
		return instance;
	}
	
	public Entry getEntry(String id){
		// return entry if available.
		Entry entry = entries.getOrDefault(id, null);
		if (entry == null) {
			System.out.println(module+ "Failed to find entry with id: {" + id + "}." );
		}
		
		return entry;
	};
	
	public void addEntry(Entry entry) {
		
		// check for duplicate id; warn user of overwrite.
		if (entries.getOrDefault(entry.getId(), null) != null) {
			
			System.out.println(module+ "Overwriting existing entry." );
		}
		// check available storage; warn user if storage full.
		else if (entries.size()>= capacity) {
			System.out.println(module+ "Storage full; cancelling operation." );
			return;
		}
		
		entries.put(entry.getId(), entry);
	};
	
	// TODO : Add support for role-based entry access.
}
