package var;

import domains.IntDomain;


public class IntCsp extends Variable {
	private int value;
	
	public IntCsp(String name, IntDomain domain) throws Exception {
		super(name, domain);
		this.value = this.getDomain().getBorneInf();
		this.setVal();
	}
	
	public IntCsp(String name, int borneMin, int borneMax) throws Exception {
		this(name, new IntDomain(borneMin, borneMax));
	}
	
	private IntDomain getDomain() {
		return (IntDomain) super.domain;
	}
	
	public void setVal() throws Exception {
		if(this.getDomain().hasOnlyOneValue())
			this.value = this.getDomain().getFirstValidValue();
	}
	
	public int getCurrentValue() {
		return this.value;
	}		
}
