package omega1001.private_applications.blueJProjectBuilder;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;

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

import freemarker.cache.ClassTemplateLoader;
import freemarker.cache.TemplateLoader;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.TemplateExceptionHandler;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Mojo(name = "buildBlueJ", defaultPhase = LifecyclePhase.PROCESS_SOURCES)
public class BlueJBuilder extends AbstractMojo {
	private static final Template TEMPLATE;
	private static IOException initException;

	static {
		TemplateLoader templateLoader = new ClassTemplateLoader(BlueJBuilder.class, "");
		Configuration cfg = new Configuration();
		cfg.setTemplateLoader(templateLoader);
		cfg.setDefaultEncoding("UTF-8");
		cfg.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
		Template tmp = null;
		try {
			tmp = cfg.getTemplate("package.blueJ.ftlh");
		} catch (IOException e) {
			initException = e;
		}
		TEMPLATE = tmp;
	}
	/**
	 * Location of the file.
	 */
	@Parameter(defaultValue = "${project.build.directory}/blueJ", property = "outputDir", required = true)
	private File outputDirectory;
	@Parameter(defaultValue = "${project.build.directory}/classes", property = "outputClassDir", required = true)
	private File classDirectory;
	@Parameter(defaultValue = "${project.build.directory}/test-classes", property = "outputTestClassDir", required = true)
	private File testClassDirectory;

	@Parameter(defaultValue = "${project.compileSourceRoots}", required = true, readonly = true)
	private List<String> compileSourceRoots;
	@Parameter(defaultValue = "${project.testCompileSourceRoots}", required = true, readonly = true)
	private List<String> testCompileSourceRoots;

	public void execute() throws MojoExecutionException {
		if (!outputDirectory.exists()) {
			getLog().debug("Blue J output directory does not exists, creating ...");
			if (!outputDirectory.mkdir()) {
				getLog().error("Error during creating blueJ output directory");
				throw new MojoExecutionException("Could not create blueJ output directory");
			}
		}
		
		getLog().info("Generating BuleJ Project ...");
		if (classDirectory.exists() ) {
			if(classDirectory.isDirectory()) {
				getLog().info("Cloning classes into blueJ Project");
				try {
					FileUtils.copyDirectory(classDirectory, outputDirectory);
				} catch (IOException e) {
					getLog().error("Unable to copy files : ", e);
				}
			}else {
				getLog().warn("Not Cloning classes, not a directory");
			}
		}else {
			getLog().warn("Unable to locate class directory, does not exists");
		}
		
		if (testClassDirectory.exists() ) {
			if(testClassDirectory.isDirectory()) {
				getLog().info("Cloning test classes into blueJ Project");
				try {
					FileUtils.copyDirectory(testClassDirectory, outputDirectory);
				} catch (IOException e) {
					getLog().error("Unable to copy files : ", e);
				}
			}else {
				getLog().warn("Not Cloning classes, not a directory");
			}
		}else {
			getLog().warn("Unable to locate class directory, does not exists");
		}
		List<String> sources = new ArrayList<>();
		if(compileSourceRoots != null) {
			sources.addAll(compileSourceRoots);
		}
		if(testCompileSourceRoots != null) {
			sources.addAll(testCompileSourceRoots);
		}
		for (String src : sources) {
			File srcDir = new File(src);
			if (srcDir.exists()) {
				getLog().info("Including source directory '" + src + "'");
				processDirectory(srcDir, outputDirectory);
			} else {
				getLog().debug("Found declared, but non existent source directory '" + src + "'");
			}
		}
		getLog().info("Finished creating BlueJ Project");
	}

	private File createDir(File parent, String name) throws MojoExecutionException {
		File newDir = new File(parent, name);
		if (!newDir.exists()) {
			getLog().debug("Creating directory '" + name + "' in '" + parent.getAbsolutePath() + "'");
			if (!newDir.mkdir()) {
				getLog().debug("Error creating directory '" + name + "' in '" + parent.getAbsolutePath() + "'");
				throw new MojoExecutionException("Could not create blueJ output directory");
			}
		}
		return newDir;
	}

	private void processDirectory(File dir, File outputDirectory) throws MojoExecutionException {
		if (dir.exists() && outputDirectory.exists() && outputDirectory.isDirectory() && dir.isDirectory()) {
			getLog().debug("Processing '" + dir.getName() + "'");
			List<String> fileList = new ArrayList<>();
			List<String> directoryList = new ArrayList<>();
			File[] files = dir.listFiles();
			for (File file : files) {
				if (file.isFile()) {
					copyTo(file, outputDirectory);
					fileList.add(file.getName());
				} else {
					processDirectory(file, createDir(outputDirectory, file.getName().replaceAll("\\..+?$", "")));
					directoryList.add(file.getName());
				}
			}
			getLog().debug("Writing package File for " + fileList.size() + directoryList.size() + " entries");
			writePackageDescriptor(fileList, directoryList, outputDirectory);
		}
	}

	private void writePackageDescriptor(List<String> fileList, List<String> directoryList, File outputDirectory)
			throws MojoExecutionException {

		Writer out = null;
		try {
			Map<String, Object> model = new HashMap<>();
			model.put("packageTargets", directoryList);
			model.put("fileTargets", fileList);
			model.put("concount", directoryList.size() + fileList.size());
			out = new OutputStreamWriter(new FileOutputStream(new File(outputDirectory, "package.bluej")));
			if (TEMPLATE != null) {
				TEMPLATE.process(model, out);
			}else {
				throw initException;
			}
		} catch (IOException e) {
			throw new MojoExecutionException("Error witing package File", e);
		} catch (TemplateException e) {
			throw new MojoExecutionException("Unable to load template", e);
		} finally {
			if (out != null) {
				try {
					out.close();
				} catch (IOException e) {
					getLog().error("Unable to close File Pipe", e);
				}
			}
		}
	}

	private void copyTo(File file, File outputDirectory) throws MojoExecutionException {
		FileInputStream in = null;
		FileOutputStream out = null;
		try {
			in = new FileInputStream(file);
			out = new FileOutputStream(new File(outputDirectory, file.getName()));
			IOUtils.copy(in, out);
		} catch (IOException e) {
			throw new MojoExecutionException("Error during copping File", e);
		} finally {
			if (in != null) {
				try {
					in.close();
				} catch (IOException e) {
					getLog().warn("Could not close File Pipe", e);
				}
			}
			if (out != null) {
				try {
					out.close();
				} catch (IOException e) {
					getLog().warn("Could not close File Pipe", e);
				}
			}
		}
	}
}
