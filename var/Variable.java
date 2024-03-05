package var;

import domains.Domain;

public abstract class Variable {
	public final String name;
	public final Domain domain;
	
	public Variable(String name, Domain domain) throws Exception {
		this.name = name;
		this.domain = domain;
	}
}
