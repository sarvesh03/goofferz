package com.goofferz.framework;

import java.io.File;

import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.DefaultLogger;
import org.apache.tools.ant.Project;
import org.apache.tools.ant.ProjectHelper;
import org.apache.tools.ant.Task;

public class ExecuteAntTarget extends Task {

	private Project project = new Project();
	private File buildFile;
	private File baseDir;

	public ExecuteAntTarget(File buildFile) {
		this.buildFile = buildFile;
	}

	public ExecuteAntTarget(File buildFile, File baseDir) {
		this.buildFile = buildFile;
		this.baseDir = baseDir;
	}

	public final boolean execute(String target) throws BuildException {
		System.out.println("--- Executing ant Task ----");
		return callAntBuildTarget(target);
	}

	private boolean callAntBuildTarget(String target) {
		boolean status = true;
		try {
			if (baseDir != null) {
				project.setBaseDir(baseDir);
			}
			project.setUserProperty("ant.file", buildFile.getAbsolutePath());
			project.init();
			ProjectHelper helper = ProjectHelper.getProjectHelper();
			helper.parse(project, buildFile);

			project.addReference("ant.projectHelper", helper);
			project.addBuildListener(getDefaultLogger());
			project.executeTarget(target);
			project.log("=== Build Completed Successfully ===",
					Project.MSG_INFO);
		} catch (Exception e) {
			status = false;
			e.printStackTrace();
		}
		return status;
	}

	private static DefaultLogger getDefaultLogger() {
		DefaultLogger consoleLogger = new DefaultLogger();
		consoleLogger.setErrorPrintStream(System.err);
		consoleLogger.setOutputPrintStream(System.out);
		consoleLogger.setMessageOutputLevel(Project.MSG_VERBOSE);
		return consoleLogger;
	}

}