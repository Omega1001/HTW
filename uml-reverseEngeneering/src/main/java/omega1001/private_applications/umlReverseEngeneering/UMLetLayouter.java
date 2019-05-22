package omega1001.private_applications.umlReverseEngeneering;

import java.util.List;

import omega1001.private_applications.umlReverseEngeneering.entities.Assoziation;
import omega1001.private_applications.umlReverseEngeneering.entities.ClassEntry;
import omega1001.private_applications.umlReverseEngeneering.entities.ConnectionPoint;
import omega1001.private_applications.umlReverseEngeneering.entities.Coordinate;
import omega1001.private_applications.umlReverseEngeneering.entities.Position;

public class UMLetLayouter {
	
	private static final int LEFT_OFFSET  =20;
	private static final int TOP_OFFSET = 20;
	private static final int LINE_HEIGTH = 25;
	
	private static final int ELEMENT_WIDTH = 200;
	private static final int ELEMENT_SPACING = 50;
	
	private static final int MAX_LINE_WIDTH = 1000;

	public static final void layout(List<ClassEntry> entries) {
		new UMLetLayouter().doLayout(entries);
	}

	private UMLetLayouter() {

	}

	private void doLayout(List<ClassEntry> entries) {
		layoutElements(entries);
		processAssotiationConnectors(entries);
	}

	private void layoutElements(List<ClassEntry> entries) {
		int x = LEFT_OFFSET;
		int y = TOP_OFFSET;
		int currentLineHeigth = 0;
		
		for (ClassEntry e : entries) {
			Position d = e.getDimension();
			int heigth = e.getLineCount() * LINE_HEIGTH;
			d.set(x, y, heigth, ELEMENT_WIDTH);
			
			if (heigth > currentLineHeigth) {
				currentLineHeigth = heigth;
			}
			
			x += ELEMENT_WIDTH + ELEMENT_SPACING;
			if(x > MAX_LINE_WIDTH) {
				x = LEFT_OFFSET;
				y += ELEMENT_SPACING + currentLineHeigth;
			}
		}
	}

	private void processAssotiationConnectors(List<ClassEntry> entries) {
		for (ClassEntry e : entries) {
			for (Assoziation a : e.getAssotiations()) {

				setConnectors(a);
			}
		}
	}

	private void setConnectors(Assoziation a) {
		int shortest = Integer.MAX_VALUE;
		ConnectionPoint sPos = ConnectionPoint.values()[0];
		ConnectionPoint tPos = ConnectionPoint.values()[0];
		
		for (ConnectionPoint s : ConnectionPoint.values()) {
			for (ConnectionPoint t : ConnectionPoint.values()) {
				int d = computeDistance(a.getSource().getDimension().getConnectionPointCoordinates(s),
						a.getTarget().getDimension().getConnectionPointCoordinates(t));
				if(d < shortest) {
					sPos = s;
					tPos = t;
					shortest = d;
				}
			}
		}
		
		a.setSrcConnector(sPos);
		a.setTrgConnector(tPos);

	}

	private int computeDistance(Coordinate p1, Coordinate p2) {
		return (int) +Math.floor(Math.sqrt(Math.pow(p1.getX() - p2.getX(), 2) + Math.pow(p1.getY() - p2.getY(), 2)));
	}

}
