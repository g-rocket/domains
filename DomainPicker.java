import java.util.*;
public class DomainPicker{
	private int maxScoreSoFar = 0;
	private Set<Domain[]> highScoringCombos = new HashSet<Domain[]>();
	private Domain[] domains;
	private Camper[] campers;
	public void recurse(int campernum){
		if(campernum < campers.length){
			// System.out.println("testing " + campers[campernum]);
			for(Domain d: domains){
				// System.out.println("  testing " + d.name);
				if(! d.isFull()){
					// System.out.println("adding " + campers[campernum] + " to " + d.name);
					d.addCamper(campers[campernum]);
				}else continue;
				recurse(campernum + 1);
				d.removeCamper(campers[campernum]);
			}
		}else{
			// System.out.println("testing");
			int score = score();
			if(score > maxScoreSoFar){
				System.out.println("best score was " + maxScoreSoFar + ", now is " + score);
				maxScoreSoFar = score;
				highScoringCombos.clear();
			}
			if(score == maxScoreSoFar){
				System.out.println("adding to list of good choices");
				Domain[] currentSituation = new Domain[domains.length];
				for(int i = 0; i < domains.length; i++){
					currentSituation[i] = new Domain(domains[i]);
				}
				highScoringCombos.add(currentSituation);
			}
		}
	}
	private int score(){
		int score = 0;
		for(Domain d: domains){
			for(Camper c: d.getCampers()){
				score += c.score(d);
			}
		}
		// System.out.println("score() = " + score);
		return score;
	}
	private void inputDomainsAndCampers(){
		InputJSON ij = new InputJSON("data.json");
		ij.input();
		this.domains = ij.getDomains();
		this.campers = ij.getCampers();
	}
	public void run(){
		inputDomainsAndCampers();
		// System.out.println("campers = {");
		// for(Camper c: campers){
		// 	System.out.println("  " + c + "{");
		// 	for(String d: c.priorities) System.out.println("    " + d);
		// 	System.out.println("  }");
		// }
		// System.out.println("}");
		// System.out.println("domains = {");
		// for(Domain d: domains) System.out.println("  " + d.name + ": " + d.size);
		// System.out.println("}");
		recurse(0);
		System.out.println("maxScore = " + maxScoreSoFar);
		System.out.println("gotten using:\n");
		// for(Iterator<Domain[]> i = highScoringCombos.iterator(); i.hasNext();){
		// 	Domain[] da = i.next();
		// 	for(Domain d: da){
		// 		System.out.println(d);
		// 	}
		// 	if(i.hasNext()) System.out.println("\n-------- OR --------\n");
		// }
		Iterator<Domain[]> i = highScoringCombos.iterator();
		Domain[] da = i.next();
		for(Domain d: da){
			System.out.println(d);
		}
		if(i.hasNext()){
			System.out.println("\n-------- OR --------\n");
			da = i.next();
			for(Domain d: da){
				System.out.println(d);
			}
		}
		if(i.hasNext()){
			System.out.println("\n-------- OR --------\n");
			da = i.next();
			for(Domain d: da){
				System.out.println(d);
			}
		}
	}
	public static void main(String[] args){
		new DomainPicker().run();
	}
}