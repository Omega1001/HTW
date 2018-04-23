package ueb15.output;

import java.io.FilterWriter;
import java.io.IOException;
import java.io.Writer;

public class CSVWriter extends FilterWriter {

	private boolean indexesWritten = false;
	private int colCount = 0;
	private int colPointer = 0;

	public CSVWriter(Writer out) {
		super(out);
	}

	public void putColumn(String content) throws IOException {
		if (colPointer != 0) {
			write(',');
		}
		write(content);
		colPointer++;
		if (!indexesWritten) {
			colCount++;
		}
	}

	public void putColumns(String... contents) throws IOException {
		for (String content : contents) {
			putColumn(content);
		}
	}

	public void nextRow() throws IOException {
		if (colPointer == 0) {
			// Empty row, ignore
			return;
		}
		if (!indexesWritten) {
			indexesWritten = true;
		}
		while (colPointer < colCount) {
			putColumn("");
		}
		write('\r');
		write('\n');
		colPointer = 0;
		flush();
	}

	@Override
	public void close() throws IOException {
		// Finish Line
		nextRow();
		super.close();
	}

}
