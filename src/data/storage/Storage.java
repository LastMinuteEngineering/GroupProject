package data.storage;

import java.util.HashMap;
import data.entry.*;

public class Storage {

	private Integer capacity;
	private String address;
	private static HashMap<String, Entry> entries= new HashMap<>();
	
	private static String module = "Storage:\t";  
	
	public Storage(String address, Integer capacity) {
		this.address = address;
		this.capacity = capacity;
	};
	
	public static Entry getEntry(String id){
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
			System.out.println(module+ "Cannot add entry; Storage full" );
			return;
		}
		
		entries.put(entry.getId(), entry);
	};
}
