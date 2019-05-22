package omega1001.private_applications.umlReverseEngeneering;

/*
 * Copyright 2001-2005 The Apache Software Foundation.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;

import org.apache.maven.plugins.annotations.LifecyclePhase;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;

import omega1001.private_applications.umlReverseEngeneering.entities.ClassEntry;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.LinkedList;
import java.util.List;

import javax.xml.stream.XMLStreamException;

@Mojo(name = "uml", defaultPhase = LifecyclePhase.PROCESS_SOURCES)
public class Main extends AbstractMojo {

	/**
	 * Location of the file.
	 */
	@Parameter(defaultValue = "${project.build.directory}/uml", property = "outputDir", required = true)
	private File outputDirectory;
	@Parameter(defaultValue = "${project.build.directory}/classes", property = "classDir", required = true)
	private File classDirectory;

	public void execute() throws MojoExecutionException {
		if (!outputDirectory.exists()) {
			outputDirectory.mkdirs();
		}
		UMLetClassEntityWriter writer = null;
		try {
			URLClassLoader loader = new URLClassLoader(new URL[] { classDirectory.toURI().toURL() },
					Main.class.getClassLoader());

			List<ClassEntry> entries = new LinkedList<>();
			scann(classDirectory.listFiles(), entries, loader);
			AssotiationResolver.resolveAssotiations(entries);
			UMLetLayouter.layout(entries);
			writer = new UMLetClassEntityWriter(new FileOutputStream(outputDirectory.getAbsolutePath() + "/uml.uxf"));
			writer.startUMLetDocument();
			for (ClassEntry e : entries) {
				writer.writeElement(e);
			}
			writer.endUMLetDocument();

		} catch (Exception e) {
			e.printStackTrace();
			throw new MojoExecutionException("Error", e);
		} finally {
			if (writer != null) {
				try {
					writer.close();
				} catch (IOException e) {
					e.printStackTrace();
					throw new MojoExecutionException("Error", e);
				}
			}
		}

	}

	private void scann(File[] listFiles, List<ClassEntry> entries, URLClassLoader loader)
			throws ClassNotFoundException, XMLStreamException {
		for (File f : listFiles) {
			if (f.isDirectory()) {
				scann(f.listFiles(), entries, loader);
			} else if (f.getName().endsWith(".class")) {
				String path = f.getAbsolutePath().replace(classDirectory.getAbsolutePath() + File.separator, "")
						.replace(File.separatorChar, '.').replace(".class", "");
				getLog().debug(path);
				Class<?> cls = loader.loadClass(path);
				ClassEntry e = new ClassEntry(cls);
				UMLetEntryContentGenerator.setContent(e);
				entries.add(e);
			}
		}
	}

}
