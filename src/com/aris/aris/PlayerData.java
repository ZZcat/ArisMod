package com.aris.aris;

import java.util.HashMap; // import the HashMap class

public class PlayerData {
	private boolean isGod = false;
	private HashMap<String, Boolean> values = new HashMap<String, Object>();

	// Values
	public void set(String name, Object value) {
		values.put(name, value);
	}
	
	public Object get(String name) {
		return values.get(name);
	}
	
	// God
    public boolean isGod() {
        return isGod;
    }	
    public void setGod(boolean value) {
    	isGod = value;
    }
    
    // Todo
    public void save() {
    	// TODO!
    }
}
