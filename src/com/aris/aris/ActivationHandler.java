package com.aris.aris;


import java.util.HashMap;

import org.bukkit.entity.Player;
import com.aris.aris.PlayerData;


import java.util.logging.Logger;

public class ActivationHandler {

	private static HashMap<String, Object> values = new HashMap<String, Object>();

	public static void main () {
		values.put("StrongMobs", (boolean) false);
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
    
    // Todo
    public void save() {
    	// TODO!
    }
    
}
