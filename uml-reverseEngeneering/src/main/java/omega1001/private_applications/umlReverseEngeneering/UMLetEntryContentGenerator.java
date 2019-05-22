package omega1001.private_applications.umlReverseEngeneering;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

import omega1001.private_applications.umlReverseEngeneering.entities.ClassEntry;

public class UMLetEntryContentGenerator {

	private UMLetEntryContentGenerator() {
	}

	public static void setContent(ClassEntry entry) {
		StringBuilder builder = new StringBuilder();
		int lineCount = 0;

		lineCount = appendHead(entry.getManagedClass(), builder, 0);
		builder.append("--").append("\r\n");
		lineCount = appendeFields(entry.getManagedClass(), builder, lineCount);
		builder.append("--").append("\r\n");
		lineCount = appendMethods(entry.getManagedClass(), builder, lineCount);
		entry.setContent(builder.toString());
		entry.setLineCount(lineCount);

	}

	private static int appendHead(Class<?> cls, StringBuilder builder, int lineCount) {
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

		// Print Classname

		// Prefix Modifier
		if (Modifier.isAbstract(cls.getModifiers())) {
			builder.append("/");
		}

		// Classname
		builder.append(cls.getSimpleName());

		// Prefix Modifier end
		if (Modifier.isAbstract(cls.getModifiers())) {
			builder.append("/");
		}
		builder.append("\r\n");
		lineCount++;
		return lineCount;
	}

	private static int appendeFields(Class<?> cls, StringBuilder builder, int lineCount) {
		Field[] fields = cls.getDeclaredFields();
		for (Field f : fields) {
			if (Modifier.isPrivate(f.getModifiers())) {
				builder.append('-');
			} else if (Modifier.isPublic(f.getModifiers())) {
				builder.append('+');
			} else if (Modifier.isProtected(f.getModifiers())) {
				builder.append('#');
			}
			builder.append(' ').append(f.getName()).append(" ").append(f.getType().getSimpleName()).append("\r\n");
			lineCount++;
		}
		return lineCount;
	}

	private static int appendMethods(Class<?> cls, StringBuilder builder, int lineCount) {

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
				builder.append(m.getName()).append(" (");
				boolean first = true;
				for (Class<?> p : m.getParameterTypes()) {
					if (first) {
						first = false;
					}else {
						builder.append(", ");
					}
					builder.append(p.getSimpleName());
				}
				builder.append(")").append("\r\n");
				lineCount++;
			}
		}
		return lineCount;
	}

}
