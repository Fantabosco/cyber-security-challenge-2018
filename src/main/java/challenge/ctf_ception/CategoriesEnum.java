package challenge.ctf_ception;

public enum CategoriesEnum {	
	binary(2),
	coding(1),
	crypto(1),
	miscellaneous(1),
	web(0.5);
	
	public double timeCoeff;
	
	CategoriesEnum(double timeCoeff){
		this.timeCoeff = timeCoeff;
	}
	
	public static CategoriesEnum decode(String c) {
		for(CategoriesEnum ce: CategoriesEnum.values()) {
			if(ce.name().equals(c)) {
				return ce;
			}
		}
		return null;
	}
}