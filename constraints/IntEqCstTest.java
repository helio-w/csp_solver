package constraints;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import var.IntCsp;
import domains.IntDomain;

class IntEqCstTest {
	IntCsp testVar;
	IntEqCst ctrainteTest;
	
	@BeforeEach
	void setUp() throws Exception {
		testVar = new IntCsp("Test", new IntDomain(2, 5));
		ctrainteTest = new IntEqCst(testVar, 5);
	}

	@Test
	final void testFilter1() throws Exception {
		ctrainteTest.filter();
		assertEquals(5, testVar.value);
	}

}
