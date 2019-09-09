
public class road {

	public String ID; 
	public intersection i1;
	public intersection i2;
	public double weight;
	
	public road(String ID, intersection i1, intersection i2){
		this.ID = ID;
		this.i1 = i1;
		this.i2 = i2;
		weight = CalcWeight(i1, i2);
	}

	public double CalcWeight(intersection i1, intersection i2){
		double w = Math.pow((Math.pow(((i2.getLatitude()) -(i1.getLatitude())), 2) + Math.pow(((i2.getLongitude()) -(i1.getLongitude())), 2)), 0.5);		
		return w;
	}
	

	public String getID() {
		return ID;
	}

	public intersection getI1() {
		return i1;
	}

	public intersection getI2() {
		return i2;
	}
	
	public double getWeight() {
		return weight;
	}
	
}
