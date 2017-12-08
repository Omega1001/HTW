import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.util.Iterator;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

public class LagerTest {

	@Rule
	public MockitoRule mockitoRule = MockitoJUnit.rule();

	private Lager underTest;
	@Mock
	private Artikel mockArtikel01;
	@Mock
	private Artikel mockArtikel02;

	@Before
	public void setUp() throws Exception {
		underTest = new Lager(3);
		underTest.lagereArtikel(mockArtikel01);
		underTest.lagereArtikel(mockArtikel02);
	}

	@Test
	public void testLagerInt() {
		fail("Not yet implemented");
	}

	@Test
	public void testLager() {
		fail("Not yet implemented");
	}

	@Test
	public void testLagereArtikel() {
		fail("Not yet implemented");
	}

	@Test
	public void testLoescheArtikel() {
		fail("Not yet implemented");
	}

	@Test
	public void testBucheZugang() {
		fail("Not yet implemented");
	}

	@Test
	public void testBuchAbgang() {
		fail("Not yet implemented");
	}

	@Test
	public void testPassePreiseAn() {
		when(mockArtikel01.getPreis()).thenReturn(100d);
		underTest.passePreiseAn(20);
		verify(mockArtikel01).setPreis(120);
	}
	
	@Test
	public void testPassePreiseAn2() {
		when(mockArtikel01.getPreis()).thenReturn(100d);
		underTest.passePreiseAn(-20);
		verify(mockArtikel01).setPreis(80);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testPassePreiseAn3() {
		underTest.passePreiseAn(-100);
	}

	@Test
	public void testIterator() {
		Iterator<Artikel> it = underTest.iterator();
		assertNotNull(it);
		assertTrue(it.hasNext());
		assertEquals(mockArtikel01, it.next());
		assertEquals(mockArtikel02, it.next());
		assertFalse(it.hasNext());
	}

}
