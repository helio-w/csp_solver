package constraints;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import var.CspInt;
import domains.IntDomain;

class EqCstIntTest {
	CspInt testVar;
	EqCstInt ctrainteTest;
	
	@BeforeEach
	void setUp() throws Exception {
		testVar = new CspInt("Test", new IntDomain(2, 5));
		ctrainteTest = new EqCstInt(testVar, 5);
	}

	@Test
	final void testFilter1() throws Exception {
		ctrainteTest.filter();
		assertEquals(5, testVar.getCurrentValue());
	}

}
