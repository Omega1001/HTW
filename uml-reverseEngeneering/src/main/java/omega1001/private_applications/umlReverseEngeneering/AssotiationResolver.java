package omega1001.private_applications.umlReverseEngeneering;

import java.lang.reflect.Field;
import java.util.List;

import omega1001.private_applications.umlReverseEngeneering.entities.Assoziation;
import omega1001.private_applications.umlReverseEngeneering.entities.ClassEntry;

public class AssotiationResolver {

	public static void resolveAssotiations (List<ClassEntry> entries) {
		for (ClassEntry e : entries) {
			for (Field f : e.getManagedClass().getDeclaredFields()) {
				for (ClassEntry en : entries) {
					if (f.getType().equals(en.getManagedClass())) {
						Assoziation a = new Assoziation(e, en);
						e.getAssotiations().add(a );
					}
				}
			}
		}
	}
	
}
