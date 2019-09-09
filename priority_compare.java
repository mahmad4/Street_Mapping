/*
 * Muhammad Ahmad
 * mahmad4
 * mahmad4@u.rochester.edu
 */

public class priority_compare implements Comparable<priority_compare>{

	public String ID;
	public Double value;
	
	public priority_compare(String ID, Double value){
		this.ID = ID;
		this.value = value;
	}
	
	
	public String getID() {
		return ID;
	}

	public Double getValue() {
		return value;
	}
	@Override
	public int compareTo(priority_compare com) {
		return this.value.compareTo(com.getValue());
	}
	

}
