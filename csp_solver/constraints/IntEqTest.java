package csp_solver.constraints;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import csp_solver.domains.IntDomain;
import csp_solver.var.IntCsp;

class IntEqTest {
	IntCsp var1;
	IntCsp var2;
	
	IntEq cte1;
	IntEqCst cte2;
	
	@BeforeEach
	void setUp() throws Exception {
		var1 = new IntCsp("A", new IntDomain(-2, 5));
		var2 = new IntCsp("B", new IntDomain(-2, 5));
		
		cte1 = new IntEq(var1, var2);
		cte2 = new IntEqCst(var1, 0);
	}
	
	@Test
	final void testFilter1() {	
		cte2.filter();
		cte1.filter();
		
		var2.fixWithFirstDomVal();
		
		assertEquals(true, var2.isInDomain(var1.getValue()));
		assertEquals(0, var2.getValue());
		assertEquals(1, var2.getDomainSize());
	}
	
	@Test
	final void testFilter2() {
		try {
			IntCsp a = new IntCsp("A", -2, 5);
			a.fixWithFirstDomVal();
			IntCsp b = new IntCsp("B", 3, 5);
			IntEq cte = new IntEq(a, b);
			cte.filter();
			assertEquals(true, b.isDomainEmpty());
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	final void testFilter3() throws Exception {
		IntCsp a = new IntCsp("A", -2, 3);
		a.fixWithFirstDomVal();
		IntCsp b = new IntCsp("B", -4, 5);
		IntEq cte = new IntEq(a, b);
		cte.filter();
		b.fixWithFirstDomVal();
		assertEquals(-2, b.getValue());
	}

}
