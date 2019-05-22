package omega1001.private_applications.umlReverseEngeneering;

import java.io.Closeable;
import java.io.IOException;
import java.io.OutputStream;
import javax.xml.stream.FactoryConfigurationError;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

import omega1001.private_applications.umlReverseEngeneering.entities.Assoziation;
import omega1001.private_applications.umlReverseEngeneering.entities.ClassEntry;
import omega1001.private_applications.umlReverseEngeneering.entities.Coordinate;
import omega1001.private_applications.umlReverseEngeneering.entities.Position;

public class UMLetClassEntityWriter implements Closeable {

	private XMLStreamWriter writer;
	private boolean isFinished = true;

	public UMLetClassEntityWriter(OutputStream stream) {
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
		isFinished = false;
	}

	public void writeElement(ClassEntry entry) throws XMLStreamException {

		addUMLetElement("UMLClass",entry.getDimension(), entry.getContent(), "");
		for (Assoziation s : entry.getAssotiations()) {
			writeAssoziation(s);
		}

	}

	private void addUMLetElement(String type, Position pos, String content, String additional) throws XMLStreamException {
		writer.writeStartElement("element");

		writer.writeStartElement("id");
		writer.writeCharacters(type);
		writer.writeEndElement();

		writer.writeStartElement("coordinates");

		writer.writeStartElement("x");
		writer.writeCharacters(String.valueOf(pos.getX()));
		writer.writeEndElement();

		writer.writeStartElement("y");
		writer.writeCharacters(String.valueOf(pos.getY()));
		writer.writeEndElement();

		writer.writeStartElement("w");
		writer.writeCharacters(String.valueOf(pos.getWidth()));
		writer.writeEndElement();

		writer.writeStartElement("h");
		writer.writeCharacters(String.valueOf(pos.getHeigth()));
		writer.writeEndElement();

		writer.writeEndElement();

		writer.writeStartElement("panel_attributes");
		writer.writeCharacters(content);
		writer.writeEndElement();

		if ("".equals(additional)) {
			writer.writeEmptyElement("additional_attributes");
		} else {
			writer.writeStartElement("additional_attributes");
			writer.writeCharacters(additional);
			writer.writeEndElement();
		}
		writer.writeEndElement();
	}

	private void writeAssoziation(Assoziation assotiation) throws XMLStreamException {
		Position p = new Position();
		Position s = assotiation.getSource().getDimension();
		Position t = assotiation.getTarget().getDimension();

		if (s.getX() < t.getX()) {
			p.setX(s.getX());
			p.setWidth(t.getX() + t.getWidth() - s.getX());
		} else {
			p.setX(t.getX());
			p.setWidth(s.getX() + s.getWidth() - t.getX());
		}

		if (s.getY() < t.getY()) {
			p.setY(s.getY());
			p.setHeigth(t.getY() + t.getHeigth() - s.getY());
		} else {
			p.setY(t.getX());
			p.setHeigth(s.getY() + s.getHeigth() - t.getY());
		}

		Coordinate start = s.getConnectionPointCoordinates(assotiation.getSrcConnector());
		Coordinate end = t.getConnectionPointCoordinates(assotiation.getTrgConnector());
		start.subtract(p.getCoordinate());
		end.subtract(p.getCoordinate());

		StringBuilder sb = new StringBuilder();
		sb.append(start.getX()).append(';').append(start.getY()).append(';').append(end.getX()).append(';')
				.append(end.getY());
		addUMLetElement("Relation",p,"lt=->", sb.toString());
	}

	public void endUMLetDocument() throws XMLStreamException {
		writer.writeEndElement();
		writer.writeEndDocument();
		isFinished = true;
	}

	@Override
	public void close() throws IOException {
		if (!isFinished) {
			try {
				endUMLetDocument();
			} catch (XMLStreamException e) {
				new IOException(e);
			}
		}
		if (writer != null) {
			try {
				writer.close();
			} catch (XMLStreamException e) {
				new IOException(e);
			}
		}

	}

}
