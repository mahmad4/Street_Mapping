//Muhammad Ahmad


import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;
import java.util.Vector;

public class Dijkstra {

	// For Dijkstra
	/*private Set<intersection> settledNodes;
    private Set<intersection> unSettledNodes;
    private Map<intersection, Double> distance;
    private Map<intersection, intersection> predecessors;
    */
	public HashMap<String, DistanceandID> distance_table = new HashMap<>();
	public PriorityQueue<priority_compare> notVisited = new PriorityQueue<priority_compare>();
	public Vector<String> final_path;
	public double total_distance = 0.0;
    public Graph graph;
    public intersection i1;
    public intersection i2;
    protected HashMap<String, road> dijkstra = new HashMap<>();
    
    
	public Dijkstra(intersection i1, intersection i2, Graph graph){
		this.graph = graph;
		this.i1 = i1;
		this.i2 = i2;
		
		System.out.println("Path from " + i1.ID + " to " + i2.ID);
		initialize_table();
		distance_table.get(i1.ID).dist = 0.0;
		notVisited.offer(new priority_compare(i1.getID(), 0.0));
		String nextElement = null;
		while (true){
			
			try{
				priority_compare xx = notVisited.remove();
				nextElement = xx.getID();
			}catch(Exception e){	nextElement = null;  }
			
			// break if null
			if (nextElement == null){	break;	}
			
			HashMap<String, road> temp_adjacency_list = graph.intersections_map.get(nextElement).getAdjacency_list();
			for (String ee : temp_adjacency_list.keySet()){
				
				double d = distance_table.get(nextElement).dist + graph.find_road(graph.intersections_map.get(nextElement), graph.intersections_map.get(ee)).weight;
				//System.out.println("dis : " + distance_table.get(ee).dist);
				if (distance_table.get(ee).dist > d){
					distance_table.get(ee).dist = d;
					distance_table.get(ee).previous_i_ID = nextElement;
					priority_compare tempQueue = new priority_compare(ee, d);
					notVisited.offer(tempQueue);
				}
				
			}
		}
		//System.out.println("ID 1 " + i1.ID + " ID 2 " + i2.ID);
		
		final_path = find_path(i1, i2);
		
		
	}

	public Vector<String> find_path(intersection i1, intersection i2){
		final_path = new Vector<String>();
		String temp = i2.ID;
		//System.out.println("HERE ID 1 " + i1.ID + " ID 2 " + i2.ID);
		//System.out.println("ID " + temp );
		try{
			while(!temp.equals(i1.getID())){
				//System.out.println("ID " + temp );
				final_path.add(temp);
				try{
				road r = graph.find_road(graph.intersections_map.get(distance_table.get(temp).previous_i_ID), graph.intersections_map.get(temp));
				// find total distance
				total_distance += r.weight;
				// dijkstra roads HashMap
				dijkstra.put(r.getID(), r);
				
				}catch(Exception e){	System.out.println("No such road exists");	}
				
				
				// moving to previous ID now
				temp = distance_table.get(temp).previous_i_ID;
				//System.out.println("ID " + temp );
			}
			
			final_path.add(i1.getID());
			total_distance = Math.cos(Math.toRadians(i1.latitude)) * 69 * total_distance;
			System.out.println("Total Distance traveled in miles : " + total_distance);
			System.out.println("Final Path " + final_path);
			// print total distance
			
		}catch(Exception e){e.printStackTrace();}
		
		return final_path;
	}
	
	
	
	
	
	public HashMap<String, road> getDijkstra() {
		return dijkstra;
	}


	public void initialize_table(){
		for (String ee : graph.intersections_map.keySet()){
			distance_table.put(ee, new DistanceandID(ee, Double.POSITIVE_INFINITY));
		}
	}
	
	class DistanceandID{
		public double dist;
		public String ID;
		public String previous_i_ID;
		public DistanceandID(String ID, double dist){
			this.dist = dist;
			this.ID = ID;
		}
	}
	
	
    
	
}
