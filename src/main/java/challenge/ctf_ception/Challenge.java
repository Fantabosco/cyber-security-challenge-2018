package challenge.ctf_ception;

public class Challenge {
	public String id;
	public CategoriesEnum category;
	public int score;
	public int eta;
	public double value;
	
	public Challenge(String id, String category, String score, String era) {
		this.id = id;
		this.category = CategoriesEnum.decode(category);
		this.score = Integer.parseInt(score);
		this.eta = Integer.parseInt(era);
		this.value = this.score / (this.eta * this.category.timeCoeff);
	}
}