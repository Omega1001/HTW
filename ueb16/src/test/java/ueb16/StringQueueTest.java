package ueb16;

import org.junit.Before;

public class StringQueueTest extends AbstractQueueTest<String> {
	private StringQueue underTest;

	@Before
	public void inti() {
		underTest = new StringQueue(maxSize());
	}

	@Override
	protected String getTestObject(int no) {
		return "test"+no;
	}

	@Override
	protected AbstractQueue<String> ut() {
		return underTest;
	}

	@Override
	protected int maxSize() {
		return 5;
	}

}
