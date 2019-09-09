/*
 * Muhammad Ahmad
 * mahmad4
 * mahmad4@u.rochester.edu
 */

import java.util.HashMap;

public class intersection {

	public String ID;
	public double latitude;
	public double longitude;
	
	public HashMap <String, road> adjacency_list = new HashMap<>();							// HashMap for adjacency list of each intersection
	
	public double current_value;
	public String parent;
	
	public intersection(String ID, double latitude, double longitude){
		this.ID = ID;
		this.latitude = latitude;
		this.longitude = longitude;
		
		this.current_value = Double.MAX_VALUE;
		this.parent = null;
	}
	
	public void addIntersection(intersection i, road r){
		adjacency_list.put(i.getID(),  r);
	}
	
	
	public HashMap<String, road> getAdjacency_list() {
		return adjacency_list;
	}
	

	public String getID() {
		return ID;
	}

	public double getLatitude() {
		return latitude;
	}

	public double getLongitude() {
		return longitude;
	}

	public double getCurrent_value() {
		return current_value;
	}

	public void setCurrent_value(double d) {
		this.current_value = d;
	}

	public String getParent() {
		return parent;
	}

	public void setParent(String parent) {
		this.parent = parent;
	}
	
	
	
	
}
