package com.aris.aris;

import java.util.HashMap; // import the HashMap class

public class PlayerData {
	//private boolean isGod = false;
	private static HashMap<String, Object> values = new HashMap<String, Object>();

	public static void main () {
		values.put("god", (boolean) false);
	}
	
	// Values
	public void set(String name, Object value) {
		values.put(name, value);
	}
	
	public Object get(String name) {
		if (!values.containsKey(name)) { // Quick fix/patch attempt... fixme
			main();
		}
		return values.get(name);
	}
	
	/*/ God COMMENT OUT OLD
    public boolean isGod() {
        return isGod;
    }	
    public void setGod(boolean value) {
    	isGod = value;
    }*/
    
    // Todo
    public void save() {
    	// TODO!
    }
}
