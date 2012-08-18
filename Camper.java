public class Camper{
	public final String[] priorities;
	private int maxPriorityRanking;
	public final String name;
	public Camper(String name, int maxPriorityRanking, String... priorities){
		this.name = name;
		this.maxPriorityRanking = maxPriorityRanking;
		this.priorities = priorities;
	}
	public int score(String domain){
		for(int i = 0; i < priorities.length; i++){
			if(domain.equals(priorities[i])){
				return maxPriorityRanking - i;
			}
		}
		return 0;
	}
	public int score(Domain domain){
		return this.score(domain.name);
	}
	public String toString(){
		return name;
	}
}