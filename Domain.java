import java.util.*;
public class Domain{
	public final String name;
	public final int size;
	private List<Camper> campersInDomain;
	public Domain(String name, int size){
		this.size = size;
		this.name = name;
		campersInDomain = new ArrayList<Camper>(size);
	}
	public Domain(Domain d){
		this.size = d.size;
		this.name = d.name;
		campersInDomain = new ArrayList<Camper>(d.campersInDomain);
	}
	public void addCamper(Camper c){
		campersInDomain.add(c);
	}
	public void removeCamper(Camper c){
		campersInDomain.remove(c);
	}
	public boolean isFull(){
		// System.out.println("campersInDomain.size() = " + campersInDomain.size());
		// System.out.println("this.size = " + this.size);
		return campersInDomain.size() >= (this.size - 1);
	}
	public void reset(){
		campersInDomain.clear();
	}
	public List<Camper> getCampers(){
		return campersInDomain;
	}
	public String toString(){
		StringBuilder sb = new StringBuilder(name);
		sb.append(" = {\n");
		for(Camper c: campersInDomain){
			sb.append("  ");
			sb.append(c);
			sb.append("\n");
		}
		sb.append("}");
		return sb.toString();
	}
}