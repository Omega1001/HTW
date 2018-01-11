import static org.junit.Assert.*;

import java.util.Iterator;
import java.util.NoSuchElementException;

import org.junit.Before;
import org.junit.Test;


public class LagerTest {
	
	private Lager underTest;
	private Artikel mockArtikel01 = new Artikel(1234, "t",12,100.00);
	private Artikel mockArtikel02 = new Artikel(9876, "t2", 6, 50.00);

	@Before
	public void setUp() throws Exception {
		underTest = new Lager(3);
		underTest.lagereArtikel(mockArtikel01);
		underTest.lagereArtikel(mockArtikel02);
	}

	@Test
	public void testLagerInt() {
		Lager lager = new Lager(1);
		assertEquals(0, (lager).getArtikelAnzahl());
		lager.lagereArtikel(mockArtikel01);
		assertEquals(1, (lager).getArtikelAnzahl());
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testLagerInt2() {
		new Lager(0);
	}
	@Test(expected = IllegalArgumentException.class)
	public void testLagerInt3() {
		new Lager(10000);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testLagerInt4() {
		Lager lager = new Lager(1);
		assertEquals(0, (lager).getArtikelAnzahl());
		lager.lagereArtikel(mockArtikel01);
		assertEquals(1, (lager).getArtikelAnzahl());
		lager.lagereArtikel(mockArtikel02);
	}

	@Test
	public void testLager() {
		Lager lager = new Lager();
		for (int i = 0; i < 9000; i++) {
			assertEquals(i, (lager).getArtikelAnzahl());
			lager.lagereArtikel(mockArtikel01);
			assertEquals(i + 1, (lager).getArtikelAnzahl());
		}
		assertEquals(9000, (lager).getArtikelAnzahl());
	}

	@Test(expected = IllegalArgumentException.class)
	public void testLager2() {
		Lager lager = new Lager();
		for (int i = 0; i < 9001; i++) {
			lager.lagereArtikel(mockArtikel01);
		}
	}

	@Test
	public void testLagereArtikel() {
		int vorher = underTest.getArtikelAnzahl();
		Artikel mockArtikel03 = new Artikel(9012, "bez");
		try {
			underTest.findeArtikel(9012);
			fail("Element already added");
		}catch(NoSuchElementException e) {
			//Continue
		}
		underTest.lagereArtikel(mockArtikel03);
		assertEquals((vorher + 1), underTest.getArtikelAnzahl());
		assertEquals(mockArtikel03, underTest.findeArtikel(9012));
	}

	@Test(expected = IllegalArgumentException.class)
	public void testLagereArtikel2() {
		underTest.lagereArtikel(mockArtikel01);
		underTest.lagereArtikel(mockArtikel01);
	}

	@Test
	public void testLoescheArtikel() {
		int vorher = underTest.getArtikelAnzahl();
		assertEquals(mockArtikel01, underTest.findeArtikel(1234));
		underTest.loescheArtikel(1234);
		assertEquals((vorher - 1), underTest.getArtikelAnzahl());
		try {
			underTest.findeArtikel(9012);
			fail("Element still present");
		}catch(NoSuchElementException e) {
			//Continue
		}
	}

	@Test(expected = NoSuchElementException.class)
	public void testLoescheArtikel2() {
		underTest.loescheArtikel(3333);

	}

	@Test
	public void testBucheZugang() {
		int prev1 = mockArtikel01.getArtikelBestand();
		int prev2 = mockArtikel02.getArtikelBestand();
		underTest.bucheZugang(1234, 10);
		assertEquals(prev1+10, mockArtikel01.getArtikelBestand());
		assertEquals(prev2, mockArtikel02.getArtikelBestand());
	}

	@Test(expected = NoSuchElementException.class)
	public void testBucheZugang2() {
		underTest.bucheZugang(2222, 10);
	}

	@Test
	public void testBucheAbgang() {
		int prev1 = mockArtikel01.getArtikelBestand();
		int prev2 = mockArtikel02.getArtikelBestand();
		underTest.bucheAbgang(1234, 10);
		assertEquals(prev1-10, mockArtikel01.getArtikelBestand());
		assertEquals(prev2, mockArtikel02.getArtikelBestand());
	}

	@Test(expected = NoSuchElementException.class)
	public void testBucheAbgang2() {
		underTest.bucheAbgang(2222, 10);
	}

	@Test
	public void testPassePreiseAn() {
		underTest.passePreiseAn(20);
		assertEquals(120, mockArtikel01.getPreis(),0.0);
		assertEquals(60, mockArtikel02.getPreis(),0.0);
	}

	@Test
	public void testPassePreiseAn2() {
		underTest.passePreiseAn(-20);
		assertEquals(80, mockArtikel01.getPreis(),0.0);
		assertEquals(40, mockArtikel02.getPreis(),0.0);
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
		assertTrue(it.hasNext());
		assertEquals(mockArtikel01, it.next());
		assertEquals(mockArtikel02, it.next());
		assertFalse(it.hasNext());
		try {
			it.next();
			fail("Got more Elements then it should have!");
		}catch(NoSuchElementException e) {
			//Continue
		}
	}

}
