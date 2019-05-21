package omega1001.private_applications.umlReverseEngeneering;

import java.io.OutputStream;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

import javax.xml.stream.FactoryConfigurationError;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

public class UMLetWriter {

	private static final int LEFT_OFFSET = 10;
	private static final int TOP_OFFSET = 10;
	private static final int DOC_WIDTH = 800;

	private static final int EL_WIDTH = 150;
	private static final int EL_LINE_HEIGTH = 40;

	private static final int GRID_COLCOUNT = 2;
	private static final int GRID_HGAP = 40;

	private XMLStreamWriter writer;

	private int x = LEFT_OFFSET;
	private int y = TOP_OFFSET;

	public UMLetWriter(OutputStream stream) {
		try {
			writer = XMLOutputFactory.newFactory().createXMLStreamWriter(stream);
		} catch (XMLStreamException | FactoryConfigurationError e) {
			// should never happen
			throw new RuntimeException("Error initialising factory", e);
		}
	}

	public void startUMLetDocument() throws XMLStreamException {
		writer.writeStartDocument();
		writer.writeStartElement("diagram");
		writer.writeAttribute("program", "umlet");
		writer.writeAttribute("version", "14.3.0");
		writer.writeStartElement("zoom_level");
		writer.writeCharacters("10");
		writer.writeEndElement();
	}

	public void endUMLetDocument() throws XMLStreamException {
		writer.writeEndElement();
		writer.writeEndDocument();
		writer.close();
	}

	public void writeClass(Class<?> cls) throws XMLStreamException {
		StringBuilder builder = new StringBuilder();
		int lineCount = 0;

		// Find Stereotype
		if (cls.isAnnotation()) {
			builder.append("<<Annotation>>").append("\r\n");
			lineCount++;
		} else if (cls.isInterface()) {
			builder.append("<<Interface>>").append("\r\n");
			lineCount++;
		} else if (cls.isEnum()) {
			builder.append("<<Enum>>").append("\r\n");
			lineCount++;
		}

		// Classname
		if (Modifier.isAbstract(cls.getModifiers())) {
			builder.append("/");
		}
		builder.append(cls.getSimpleName());
		if (Modifier.isAbstract(cls.getModifiers())) {
			builder.append("/");
		}
		builder.append("\r\n");
		builder.append("--").append("\r\n");
		Field[] fields = cls.getFields();
		for (Field f : fields) {
			if (Modifier.isPrivate(f.getModifiers())) {
				builder.append('-');
			} else if (Modifier.isPublic(f.getModifiers())) {
				builder.append('+');
			} else if (Modifier.isProtected(f.getModifiers())) {
				builder.append('#');
			}
			builder.append(f.getName()).append("\r\n");
			lineCount++;
		}
		builder.append("--").append("\r\n");
		Method[] methods = cls.getDeclaredMethods();
		for (Method m : methods) {
			if (cls.equals(m.getDeclaringClass())) {
				if (Modifier.isPrivate(m.getModifiers())) {
					builder.append("- ");
				} else if (Modifier.isPublic(m.getModifiers())) {
					builder.append("+ ");
				} else if (Modifier.isProtected(m.getModifiers())) {
					builder.append("# ");
				}
				builder.append(m.getName()).append("\r\n");
				lineCount++;
			}
		}

		insertElement(builder.toString(), lineCount);
		x += 200;
	}

	private void insertElement(String content, int lineCount) throws XMLStreamException {
		writer.writeStartElement("element");

		writer.writeStartElement("id");
		writer.writeCharacters("UMLClass");
		writer.writeEndElement();

		writer.writeStartElement("coordinates");

		writer.writeStartElement("x");
		writer.writeCharacters(String.valueOf(x));
		writer.writeEndElement();

		writer.writeStartElement("y");
		writer.writeCharacters(String.valueOf(y));
		writer.writeEndElement();

		writer.writeStartElement("w");
		writer.writeCharacters(String.valueOf(EL_WIDTH));
		writer.writeEndElement();

		writer.writeStartElement("h");
		writer.writeCharacters(String.valueOf(EL_LINE_HEIGTH * lineCount));
		writer.writeEndElement();

		writer.writeEndElement();

		writer.writeStartElement("panel_attributes");
		writer.writeCharacters(content);
		writer.writeEndElement();

		writer.writeEmptyElement("additional_attributes");
		writer.writeEndElement();

	}

}
