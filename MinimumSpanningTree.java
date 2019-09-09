/*
 * Muhammad Ahmad
 * mahmad4
 * mahmad4@u.rochester.edu
 */

import java.awt.Color;
import java.awt.Graphics;
import java.util.HashMap;

import javax.swing.JComponent;

public class MinimumSpanningTree {
	
	public Graph graph;
	HashMap<String, road> minimumspanningtree = new HashMap<>();
	
	
	public MinimumSpanningTree(Graph graph){
		this.graph = graph;
		this.minimumspanningtree = method(graph);
	}
	
	
	

	public HashMap<String, road> method(Graph graph){
		// Initializing every vertex to infinity and its parent to be null 
		/* for (String ee : graph.getIntersections_map().keySet()){
			intersection i = graph.getIntersections_map().get(ee);
			i.setCurrent_value(Double.MAX_VALUE);
			i.setParent(null);
		} */
		
		// random root 
		//try{
			String random_root = graph.intersections_map.get("i1").getID();
			graph.getIntersections_map().get(random_root).setCurrent_value(0.0); 			// setting key of root to be 0
		//}catch(Exception e){System.out.println("Index out of bounds");}
		// a new HashMap Q with all the intersections
		HashMap<String, intersection> Q = new HashMap<>();
		Q = (HashMap) graph.getIntersections_map().clone();
		
		// While loop
		double temp_i_current_value = Double.MAX_VALUE;
		String string_temp_i = null;
		
		while(!Q.isEmpty()){
			
			// checking for intersection with the smallest current value
			intersection i1 = null;
			for (String ee : Q.keySet()){
				if (Q.get(ee).getCurrent_value() <= temp_i_current_value){
					i1 = Q.get(ee);
					temp_i_current_value = Q.get(ee).getCurrent_value();
					System.out.println("here");
					string_temp_i = ee;
				}
			}
			// setting the current value back to infinity
			temp_i_current_value = Double.MAX_VALUE;
			
			// removing intersection with the smallest key
			Q.remove(string_temp_i);
			if (Q.isEmpty()){
				System.out.println("yolooo");
				break;
			}
			// Adding roads to the minimum spanning tree HashMap
			if (i1.getParent() != null){
				road temp_road = graph.find_road(i1, graph.getIntersections_map().get(i1.getParent()));
				minimumspanningtree.put(temp_road.getID(), temp_road);	
			}
			
			// All vertices adjacent to the current vertex
			HashMap<String, road> temp_adjacency_list = i1.getAdjacency_list();
			for (String ee : temp_adjacency_list.keySet()){			
				// adjacent vertex(intersection)
				try{
				intersection i2 = Q.get(ee);
				System.out.println("here5 " + i2.getID());
				if ((Q.containsValue(i2)) && (graph.find_road(i1, i2).getWeight() < i2.getCurrent_value())){
					i2.setParent(i1.getID());	// change parent
					
					//System.out.println(i2.getID() + " ID  " + i2.getParent() + " here");
					i2.setCurrent_value(graph.find_road(i1, i2).getWeight()); // and also changes the key to the weight
					//System.out.println(i2.getCurrent_value() + " here2");
				}
				}catch(Exception e){
					
				}
			}
		}
		System.out.println("List of all roads visited : ");
		for (String ee : minimumspanningtree.keySet()){
			road r = graph.roads_map.get(ee);
			System.out.print(r.ID + "  ");
		}
		System.out.println();
		return minimumspanningtree;
	}
	
	public HashMap<String, road> getMinimumspanningtree() {
		return minimumspanningtree;
	}
		
}
