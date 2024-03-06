package var;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import domains.IntDomain;

class IntCspTest {
	
	IntCsp varTest;
	int borneSup;
	int borneInf;
	
	@BeforeEach
	void setUp() throws Exception {
		borneInf = -2;
		borneSup = 5;
		
		varTest = new IntCsp("A", new IntDomain(borneInf, borneSup));
	}

	@Test
	final void testGetDomainBorneSup() {
		assertEquals(borneSup, varTest.getDomainBorneSup()); 
	}

	@Test
	final void testGetDomainBorneInf() {
		assertEquals(borneInf, varTest.getDomainBorneInf());
	}

	@Test
	final void testSetUniqueVal() {
		varTest.setUniqueVal(0);
		assertEquals(0, varTest.value);
	}

	@Test
	final void testIsInDomain() {
		assertEquals(true, varTest.isInDomain(0));
		assertEquals(false, varTest.isInDomain(-6));
	}

	@Test
	final void testGetDomainSize() {
		varTest.setUniqueVal(0);
		assertEquals(1, varTest.getDomainSize());
	}

	@Test
	final void testSetDomainVal() {
		varTest.setDomainVal(0, false);
		assertEquals(false, varTest.isInDomain(0));
	}

	@Test
	final void testIsDomainEmpty() {
		varTest.setUniqueVal(0);
		varTest.setDomainVal(0, false);
		assertEquals(true, varTest.isDomainEmpty());
	}

	@Test
	final void testFixWithFirstDomVal() {
		varTest.setDomainVal(borneInf, false);
		varTest.fixWithFirstDomVal();
		assertEquals(1, varTest.getDomainSize());
		assertEquals(true, varTest.isFixed);
		assertEquals(borneInf+1, varTest.value);
	}
	
}
