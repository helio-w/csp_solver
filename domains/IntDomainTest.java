package domains;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class IntDomainTest {

	IntDomain testDomain;
	int size;
	
	@BeforeEach
	void setUp() throws Exception {
		testDomain = new IntDomain(-2, 5);
		size = 8; // Calcul à la main
	}

	@Test
	@DisplayName("Test du constructeur avec des valeurs incohérentes")
	final void testIntDomain() {
		assertThrows(IllegalArgumentException.class, () -> {new IntDomain(4, 2);});
	}

	@Test
	final void testIsIn() {
		assertEquals(true, testDomain.isIn(this.testDomain.getBorneSup()), "Vérification de la borne supérieure");
		assertEquals(true, testDomain.isIn(this.testDomain.getBorneInf()), "Vérification de la borne inférieure");
	}

	@Test
	final void testSetVal() {
		testDomain.setValueState(this.testDomain.getBorneInf(), false);
		testDomain.setValueState(this.testDomain.getBorneSup(), false);
		assertEquals(false, testDomain.isIn(this.testDomain.getBorneInf()));
		assertEquals(false, testDomain.isIn(this.testDomain.getBorneSup()));
	}
	
	@Test
	final void testGetSize() {
		assertEquals(size, this.testDomain.getSize());
	}
	
	@Test
	final void testGetSize2() {
		testDomain.setValueState(this.testDomain.getBorneInf(), false);
		assertEquals(this.size-1, testDomain.getSize());
	}
	
	@Test
	final void testIsEmptyFalse() {
		assertEquals(false, testDomain.isEmpty());
	}
	
	@Test
	final void testIsEmptyTrue() throws Exception {
		this.testDomain = new IntDomain(2, 2);
		this.testDomain.setValueState(2, false);
		assertEquals(true, testDomain.isEmpty());
	}
	
	@Test
	final void testHasOnlyOneValueTrue() throws Exception{
		this.testDomain = new IntDomain(2, 2);
		assertEquals(true, testDomain.hasOnlyOneValue());
	}
	
	@Test
	final void testHasOnlyOneValueFalse() throws Exception{
		assertEquals(false, testDomain.hasOnlyOneValue());
	}
	
	@Test
	final void testGetOnlyOneVal() throws Exception{
		this.testDomain = new IntDomain(0, 2);
		this.testDomain.setValueState(0, false);
		this.testDomain.setValueState(1, false);
		assertEquals(2, testDomain.getFirstValidValue());
	}
	
	@Test
	final void testGetRangeSize() {
		assertEquals(1, IntDomain.getRangeSize(2, 2));
		assertEquals(2, IntDomain.getRangeSize(2, 3));
		assertEquals(2, IntDomain.getRangeSize(-3, -2));
		assertEquals(5, IntDomain.getRangeSize(0, 4));
		assertEquals(5, IntDomain.getRangeSize(2, 6));
		assertEquals(8, IntDomain.getRangeSize(-2, 5));
	}
	
	@Test
	final void testGetFirstValidValue() {
		assertEquals(-2, this.testDomain.getFirstValidValue());
		this.testDomain.setValueState(-2, false);
		assertEquals(-1, this.testDomain.getFirstValidValue());
	}
	
	@Test
	final void testSetUniqueVal() {
		this.testDomain.setUniqueVal(0);
		assertEquals(0, this.testDomain.getFirstValidValue());
		assertEquals(1, this.testDomain.getSize());
	}
}
