/*
 * Muhammad Ahmad
 * mahmad4
 * mahmad4@u.rochester.edu
 */

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.swing.JComponent;
import javax.swing.JPanel;

public class Graph {

	/*public double min_latitude;
	public double max_latitude;

	public double min_longitude;
	public double max_longitude;*/
	public HashMap <String, intersection> intersections_map;
	public HashMap <String, road> roads_map;
	public HashMap<String, road> minimum_tree;
	MinimumSpanningTree MST;
	

	public Graph(HashMap <String, intersection> intersections_map, HashMap <String, road> roads_map){
		this.intersections_map = intersections_map;
		this.roads_map = roads_map;
	/*	this.min_latitude = min_latitude;
		this.max_latitude = max_latitude;
		this.min_longitude = min_longitude;
		this.max_longitude = max_longitude;
	*/
		//setFocusable(true);
		//MinimumSpanningTree MST = new MinimumSpanningTree(this);
		//this.minimum_tree = MST.getMinimumspanningtree();
			
	}
	
	
	
	public road find_road(intersection i1, intersection i2){
		HashMap<String, road> temp_adjacency_list = i1.getAdjacency_list();
		road temp_road = null;
		for (String ee : temp_adjacency_list.keySet()){
			if (i2.getID().equals(ee)){
				temp_road = temp_adjacency_list.get(ee);
			}
		}
		
		return temp_road;
	}
		

	
		
/*
		public double getMin_latitude() {
			return min_latitude;
		}

		public double getMax_latitude() {
			return max_latitude;
		}

		public double getMin_longitude() {
			return min_longitude;
		}

		public double getMax_longitude() {
			return max_longitude;
		}
*/

		public HashMap<String, intersection> getIntersections_map() {
			return intersections_map;
		}

		public HashMap<String, road> getRoads_map() {
			return roads_map;
		}

	

}
