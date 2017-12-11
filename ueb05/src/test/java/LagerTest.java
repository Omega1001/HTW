import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.util.Iterator;
import java.util.NoSuchElementException;

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
		Lager lager = new Lager(1);
		lager.lagereArtikel(mockArtikel01);
		assertEquals(1,(lager).getArtikelAnzahl());
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testLagerInt2() {
		Lager lager = new Lager(1);
		lager.lagereArtikel(mockArtikel01);
		lager.lagereArtikel(mockArtikel02);
	
	}

	@Test
	public void testLager() {
		Lager lager = new Lager();
		for(int i=0; i<9000;i++) {
		lager.lagereArtikel(mockArtikel01);
		}
		assertEquals(9000,(lager).getArtikelAnzahl());
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testLager2() {
		Lager lager = new Lager();
		for(int i=0; i<9001;i++) {
		lager.lagereArtikel(mockArtikel01);
		}
	}

	@Test
	public void testLagereArtikel() {
		int vorher = underTest.getArtikelAnzahl();
		underTest.lagereArtikel(mockArtikel01);
		assertEquals((vorher + 1), underTest.getArtikelAnzahl());
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void testLagereArtikel2() {
		underTest.lagereArtikel(mockArtikel01);
		underTest.lagereArtikel(mockArtikel01);
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void testLagereArtikel3() {
		when(mockArtikel01.getArtikelNummer()).thenReturn(1111);
		underTest.lagereArtikel(mockArtikel01);
	}

	@Test
	public void testLoescheArtikel() {
		when(mockArtikel01.getArtikelNummer()).thenReturn(1111);
		int vorher = underTest.getArtikelAnzahl();
		underTest.loescheArtikel(1111);
		assertEquals((vorher - 1), underTest.getArtikelAnzahl());
		
	}
	
	@Test (expected = NoSuchElementException.class)
	public void testLoescheArtikel2() {
		when(mockArtikel01.getArtikelNummer()).thenReturn(1111);
		when(mockArtikel02.getArtikelNummer()).thenReturn(2222);
		underTest.loescheArtikel(3333);
		
	}

	@Test
	public void testBucheZugang() {
		when(mockArtikel01.getArtikelNummer()).thenReturn(1111);
		when(mockArtikel01.getArtikelBestand()).thenReturn(10);// Diese Zeile #$@&%*! mich so hart!!!
		underTest.bucheZugang(1111,10);
		assertEquals((20), mockArtikel01.getArtikelBestand());
	}
	
	@Test (expected = NoSuchElementException.class)
	public void testBucheZugang2() {
		when(mockArtikel01.getArtikelNummer()).thenReturn(1111);
		underTest.bucheZugang(2222,10);
	}


	@Test
	public void testBucheAbgang() {
		when(mockArtikel01.getArtikelNummer()).thenReturn(1111);
		when(mockArtikel01.getArtikelBestand()).thenReturn(10);// Diese Zeile #$@&%*! mich so hart!!!
		underTest.bucheAbgang(1111,10);
		assertEquals((0), mockArtikel01.getArtikelBestand());
	}
	
	@Test (expected = NoSuchElementException.class)
	public void testBucheAbgang2() {
		when(mockArtikel01.getArtikelNummer()).thenReturn(1111);
		underTest.bucheAbgang(2222,10);
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
