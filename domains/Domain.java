package domains;

import java.util.HashMap;

public abstract class Domain {
	private HashMap<?, Boolean> boolTab;
	
	// Methodes 
	public HashMap<?, Boolean> getValues(){
		return boolTab;
	}
}
