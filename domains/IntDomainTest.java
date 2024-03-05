package domains;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertThrows;
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
		assertEquals(false, testDomain.isIn(this.testDomain.getBorneInf()));
		assertEquals(true, testDomain.isIn(this.testDomain.getBorneSup()));
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
		this.testDomain = new IntDomain(2, 2);
		assertEquals(2, testDomain.getFirstValidValue());
	}
}
