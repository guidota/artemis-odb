package com.artemis;

import org.gradle.api.Plugin;
import org.gradle.api.Project;

/**
 * @author Daan van Yperen
 */
public class FluidArtemisGradlePlugin implements Plugin<Project> {

	@Override
	public void apply(Project target) {
		FluidApiGenerationTask fluidTask = target.getTasks().create("fluid", FluidApiGenerationTask.class);
		fluidTask.setGroup("artemis");
	}

}