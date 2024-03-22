package csp_solver.constraints;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import csp_solver.domains.IntDomain;
import csp_solver.var.IntCsp;

class IntNotEqTest {
	IntCsp var1;
	IntCsp var2;
	
	IntNotEq cte1;
	IntEqCst cte2;
	
	@BeforeEach
	void setUp() throws Exception {
		var1 = new IntCsp("A", new IntDomain(-2, 5));
		var2 = new IntCsp("B", new IntDomain(-2, 5));
		
		cte1 = new IntNotEq(var1, var2);
		cte2 = new IntEqCst(var1, 0);
	}

	@Test
	void testFilter() throws Exception {
		cte2.filter();
		cte1.filter();
		
		assertEquals(var2.isInDomain(0), false);
	}

}
