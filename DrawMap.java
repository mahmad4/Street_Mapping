//Muhammad Ahmad


import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.HashMap;

import javax.swing.JComponent;

public class DrawMap extends JComponent{

	protected boolean draw_graph;
	protected boolean draw_mst;
	protected boolean draw_dijkstra;
	protected HashMap<String, road> minimumspanningtree;
	protected HashMap<String, road> roads_map;
	protected HashMap<String, road> dijkstra;
	
	protected double min_latitude;
	protected double max_latitude;

	protected double min_longitude;
	protected double max_longitude;
	
	public DrawMap(boolean draw_graph, HashMap<String, road> roads_map, boolean draw_mst, HashMap<String, road> minimumspanningtree, boolean draw_dijkstra, HashMap<String, road> dijkstra,  double min_latitude, double max_latitude, double min_longitude, double max_longitude){
		this.draw_graph = draw_graph;
		this.draw_mst = draw_mst;
		this.draw_dijkstra = draw_dijkstra;
		
		this.roads_map = roads_map;
		this.minimumspanningtree = minimumspanningtree;
		this.dijkstra = dijkstra;
		
		this.min_latitude = min_latitude;
		this.max_latitude = max_latitude;
		this.min_longitude = min_longitude;
		this.max_longitude = max_longitude;
		
		setFocusable(true);
	}
	
	public void paintComponent(Graphics g) {
		double x1, y1, x2, y2;
		Graphics2D g2 = (Graphics2D) g;
        g2.setStroke(new BasicStroke(1));
		
		
		// normal graph
	if (this.draw_graph){
		g2.setColor(Color.BLACK);
		for(String ee : this.roads_map.keySet()){
			road r = this.roads_map.get(ee);
			
			x1 = r.getI1().getLongitude();
			y1 = r.getI1().getLatitude();
			x2 = r.getI2().getLongitude();
			y2 = r.getI2().getLatitude();
			
			
			x1 = scale_up_x(x1);
			y1 = scale_up_y(y1);
			x2 = scale_up_x(x2);
			y2 = scale_up_y(y2);
			//System.out.println("x  " + x1 + "  y " + y1);
			g2.drawLine((int)x1, (int)y1, (int)x2, (int)y2);
		}
	}
	
		// MST
	if(this.draw_mst){
		g2.setColor(Color.GREEN);
		g2.setStroke(new BasicStroke(5));
		for(String ee : this.minimumspanningtree.keySet()){
			road r = this.minimumspanningtree.get(ee);
			
			x1 = r.getI1().getLongitude();
			y1 = r.getI1().getLatitude();
			x2 = r.getI2().getLongitude();
			y2 = r.getI2().getLatitude();
			
			
			x1 = scale_up_x(x1);
			y1 = scale_up_y(y1);
			x2 = scale_up_x(x2);
			y2 = scale_up_y(y2);
			//System.out.println("x  " + x1 + "  y " + y1);
			g2.drawLine((int)x1, (int)y1, (int)x2, (int)y2);
		}
		}

		// Dijkstra - shortest path
	if (this.draw_dijkstra){
		g2.setColor(Color.RED);
		g2.setStroke(new BasicStroke(5));
		for(String ee : this.dijkstra.keySet()){
			road r = this.dijkstra.get(ee);
			
			x1 = r.getI1().getLongitude();
			y1 = r.getI1().getLatitude();
			x2 = r.getI2().getLongitude();
			y2 = r.getI2().getLatitude();
			
			
			x1 = scale_up_x(x1);
			y1 = scale_up_y(y1);
			x2 = scale_up_x(x2);
			y2 = scale_up_y(y2);
			//System.out.println("x  " + x1 + "  y " + y1);
			g2.drawLine((int)x1, (int)y1, (int)x2, (int)y2);
		}
		}
	
	}
	
	public double scale_up_x(double val){
		val = ((val-this.min_longitude)*getWidth())/(this.max_longitude - this.min_longitude);
		return val;
	}
	public double scale_up_y(double val){
		
		val = getHeight() - ((val - this.min_latitude)*getHeight())/(this.max_latitude - this.min_latitude);
		return val;
	}
	

	
	
	
}
