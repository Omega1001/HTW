package ueb17;

import static org.junit.Assert.*;

import javax.xml.ws.Holder;

import org.junit.Before;
import org.junit.Test;

public class MainTest {
	
	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testApplyAndPrint() {
		final Holder<Integer> i = new Holder<Integer>(1);
		final Holder<Integer> turns = new Holder<Integer>(0);
		Main.applyAndPrint(i.value, 5, n->{
			assertEquals((int)i.value, n);
			i.value++;
			turns.value++;
			return n;
		});
		assertEquals(4,(int) turns.value);
	}

	@Test
	public void testIterate() {
		final Holder<Integer> turn = new Holder<Integer>(5);
		final Holder<Integer> i = new Holder<Integer>(0);
		Main.iterate(i.value, turn.value, (n)->{
			assertEquals(i.value, n,0);
			turn.value--;
			i.value++;
			return (double) i.value;
		});
	}

}
