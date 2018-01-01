package ueb07;

import static org.junit.Assert.*;

import java.io.InputStream;
import java.util.List;
import org.junit.Test;

public class XMLLinkFinderTest {

	private static List<LinkContainer> runTestFile(String testFileName) {
		InputStream in = XMLLinkFinderTest.class.getClassLoader()
				.getResourceAsStream("./"+testFileName);
		return XMLLinkFinder.findLinksInStream(in);
	}

	@Test
	public void testRun1() {
		List<LinkContainer> underTest = runTestFile("SimpleHTML.html");
		assertEquals("Result List contains to many items",2, underTest.size());
		assertTrue("Missing link to Google",underTest.contains(new LinkContainer("Link To Google", "http://www.google.com")));
		assertTrue("Missing link to Stackoverflow",underTest.contains(new LinkContainer("Link To Stackoverflow", "http://stackoverflow.com")));
	}

	@Test
	public void testRun2() {
		List<LinkContainer> underTest = runTestFile("HTMLAndSubElement.html");
		assertEquals("Result List contains to many items",1, underTest.size());
		assertTrue("Missing link to Stackoverflow",underTest.contains(new LinkContainer("Link", "http://stackoverflow.com")));
	}
	
	@Test
	public void testRun3() {
		List<LinkContainer> underTest = runTestFile("HTMLAndText.html");
		assertEquals("Result List contains to many items",2, underTest.size());
		assertTrue("Missing link to Google",underTest.contains(new LinkContainer("Link", "http://www.google.com")));
		assertTrue("Missing link to Stackoverflow",underTest.contains(new LinkContainer("vergrabenen Link", "http://stackoverflow.com")));
	}
	
	@Test
	public void testRun4() {
		List<LinkContainer> underTest = runTestFile("HTMLmitMehrerenAttributen.html");
		assertEquals("Result List contains to many items",2, underTest.size());
		assertTrue("Missing link to Google",underTest.contains(new LinkContainer("Dies", "http://www.google.com")));
		assertTrue("Missing link to Stackoverflow",underTest.contains(new LinkContainer("Das", "http://stackoverflow.com")));
	}
	
	@Test
	public void testRun5() {
		List<LinkContainer> underTest = runTestFile("HTMLNoHREF.html");
		assertEquals("Result List contains to many items",1, underTest.size());
		assertTrue("Missing link to Stackoverflow",underTest.contains(new LinkContainer("Dies", "http://stackoverflow.com")));
	}
	
	@Test
	public void testRun6() {
		List<LinkContainer> underTest = runTestFile("HTMLWithAnchorLink.html");
		assertEquals("Result List contains to many items",1, underTest.size());
		assertTrue("Missing link to top",underTest.contains(new LinkContainer("Das", "#top")));
	}
	
	@Test
	public void testRun7() {
		List<LinkContainer> underTest = runTestFile("HTMLwithCrumyURLS.html");
		assertEquals("Result List contains to many items",3, underTest.size());
		assertTrue("Missing link to Google",underTest.contains(new LinkContainer("Voll", "http://www.google.com")));
		assertTrue("Missing link to Stackoverflow",underTest.contains(new LinkContainer("Unvoll", "www.stackoverflow.com")));
		assertTrue("Missing link to relative page",underTest.contains(new LinkContainer("Relativer", "/somepage")));
	}

}
