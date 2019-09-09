import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
import java.util.StringTokenizer;

import javax.swing.JFrame;
import java.util.HashMap;

public class P_4 {
	
	public static int total_intersections = 0;			// total number of intersections
	public static int total_roads = 0;					// total number of roads
	public static double min_latitude = Double.MAX_VALUE;				// minimum latitude
	public static double max_latitude = - Double.MAX_VALUE;				// maximum latitude
	public static double min_longitude = Double.MAX_VALUE;				// minimum longitude
	public static double max_longitude = - Double.MAX_VALUE;			// maximum longitude
	public static HashMap <String, intersection> intersections_map = new HashMap<>();			//HashMap for intersection
	public static HashMap <String, road> roads_map = new HashMap<>();	// HashMap for roads
	public static HashMap<String, road> minimum_tree = new HashMap<String, road>();
	public static HashMap<String, road> shortest_path = new HashMap<String, road>();
	public static intersection i;
	public static road r; 
	public static String intersection1, intersection2;
	public static boolean draw_graph = false;
	public static boolean draw_mst = false;
	public static boolean draw_dijkstra = false;
	

	public static void initialize (File file) throws IOException{
		String type; String temp_ID; double temp_latitude; double temp_longitude; StringTokenizer st;
		String line = "";
		/* BufferedReader and StringTokenizer 
		 * http://www.geeksforgeeks.org/fast-io-in-java-in-competitive-programming/ */
		try {
			BufferedReader bf = new BufferedReader(new FileReader(file));
			line = bf.readLine();
			while (line != null){			
				st = new StringTokenizer(line);
				type = st.nextToken();// if intersection or road
				// Intersection
				if (type.equals("i")){
					//System.out.println("intersection");
					total_intersections++;
					temp_ID = st.nextToken();
					temp_latitude = Double.parseDouble(st.nextToken());
					temp_longitude = Double.parseDouble(st.nextToken());
					if (min_latitude > temp_latitude){ min_latitude = temp_latitude; }			// minimum latitude
					if (max_latitude < temp_latitude){ max_latitude = temp_latitude; }			// maximum latitude
					if (min_longitude > temp_longitude){ min_longitude = temp_longitude; }		// minimum longitude
					if (max_longitude < temp_longitude){ max_longitude = temp_longitude; }		// maximum longitude
					// Updating HashMap for intersections
					i = new intersection(temp_ID, temp_latitude, temp_longitude);
					intersections_map.put(temp_ID, i);
				}
				// Roads
				else {
					//System.out.println("road");
					total_roads++;
					temp_ID = st.nextToken();
					intersection temp_i1 = intersections_map.get(st.nextToken());
					intersection temp_i2 = intersections_map.get(st.nextToken());
					// Updating HashMap for roads
					r = new road(temp_ID, temp_i1, temp_i2);
					roads_map.put(temp_ID, r);
					// updating adjacency list
					temp_i1.addIntersection(temp_i2, r);
					temp_i2.addIntersection(temp_i1, r);
					
				}
				line = bf.readLine();
			}
			bf.close();  // closing buffer reader
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		System.out.println("here");
		Graph graph = new Graph(intersections_map, roads_map);
		
		if (draw_mst){
		System.out.println("here2");
		MinimumSpanningTree MST = new MinimumSpanningTree(graph);
		minimum_tree = MST.getMinimumspanningtree();
		}
		if (draw_dijkstra){
		System.out.println("here3");
		Dijkstra dijkstra = new Dijkstra(intersections_map.get(intersection1), intersections_map.get(intersection2), graph);
		shortest_path = dijkstra.getDijkstra();
		}
		
		if(draw_graph){
		JFrame frame = new JFrame();
		frame.setSize(550, 550);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		DrawMap drawmap = new DrawMap(draw_graph, roads_map, draw_mst, minimum_tree, draw_dijkstra, shortest_path, min_latitude, max_latitude, min_longitude,  max_longitude);
		frame.add(drawmap);
		}
		
	}
	
	public static void main(String[] args) throws IOException {
		String filename = args[0];
		String xx;
		for(int i = 1; i < args.length; i++ ){
			System.out.println(args[i]);
			xx = args[i];
			if (xx.equals("-show")){
				draw_graph = true;
				
			}
			else if (xx.equals("-meridianmap")){
				draw_mst = true;
				System.out.println("yolo");
			}
			else if (xx.equals("-directions")){
				draw_dijkstra = true;
				
				i++;
				xx = args[i];
				intersection1 = xx;
				i++;
				xx = args[i];
				intersection2 = xx;
			}
		}
		
		File file = new File(filename);
		try{
		initialize(file);}catch(Exception e){}
		
		
		/*try{
			System.out.println(find_road(intersections_map.get("i4160"), intersections_map.get("i4161")).getID());
		}catch(Exception e){
			System.out.println("no such road exists");
		}
		//road ii = roads_map.get("r181");
		//intersection iii = ii.getI2();
		System.out.println("min lati : " + min_latitude + "   max lati : " + max_latitude);
		 */		
	}

	
	
	
	
	
		
}