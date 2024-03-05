package var;

import domains.IntDomain;


public class CspInt extends Variable {
	private int value;
	
	public CspInt(String name, IntDomain domain) throws Exception {
		super(name, domain);
		this.value = this.getDomain().getBorneInf();
		this.setVal();
	}
	
	public CspInt(String name, int borneMin, int borneMax) throws Exception {
		this(name, new IntDomain(borneMin, borneMax));
	}
	
	public IntDomain getDomain() {
		return (IntDomain)(this.domain);
	}
	
	// Set/Get 
	
	public void setVal() throws Exception {
		if(this.getDomain().hasOnlyOneValue())
			this.value = this.getDomain().getFirstValidValue();
	}
	
	public int getCurrentValue() {
		return this.value;
	}		
}
