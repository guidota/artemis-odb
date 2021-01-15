package com.artemis;

import org.gradle.api.DefaultTask;
import org.gradle.api.file.FileCollection;
import org.gradle.api.tasks.Input;
import org.gradle.api.tasks.InputFile;
import org.gradle.api.tasks.InputFiles;
import org.gradle.api.tasks.TaskAction;
import org.gradle.api.tasks.options.Option;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashSet;
import java.util.Set;

/**
 * Weaving wrapper for gradle.
 *
 * @author Adrian Papari
 * @author Daan van Yperen
 */
public class FluidApiGenerationTask extends DefaultTask {

    private File generatedSourcesDirectory;

    private FileCollection classpath;

    public FluidGeneratorPreferences preferences = new FluidGeneratorPreferences();

    @TaskAction
    public void fluid() {
        getLogger().info("Artemis Fluid api plugin started.");

        prepareGeneratedSourcesFolder();
        includeGeneratedSourcesInCompilation();

        new FluidGenerator().generate(
                classpathAsUrls(preferences),
                generatedSourcesDirectory, createLogAdapter(), preferences);
    }

    /**
     * bridge maven/internal logging.
     */
    private com.artemis.generator.util.Log createLogAdapter() {
        return new com.artemis.generator.util.Log() {
            @Override
            public void info(String msg) {
                getLogger().info(msg);
            }

            @Override
            public void error(String msg) {
                getLogger().error(msg);
            }
        };
    }

    /**
     * Setup generated sources folder if missing.
     */
    private void prepareGeneratedSourcesFolder() {
        if (!generatedSourcesDirectory.exists() && !generatedSourcesDirectory.mkdirs()) {
            getLogger().error("Could not create " + generatedSourcesDirectory);
        }
    }

    /**
     * Must include manually, or maven buids will fail.
     */
    private void includeGeneratedSourcesInCompilation() {
//		getProject().addCompileSourceRoot(generatedSourcesDirectory().getPath());
    }

    private Set<URL> classpathAsUrls(FluidGeneratorPreferences preferences) {
        try {
            Set<URL> urls = new HashSet<>();
            for (File element : classpath) {
                URL url = element.toURI().toURL();
                if (!preferences.matchesIgnoredClasspath(url.toString())) {
                    urls.add(url);
                    getLogger().info("Including: " + url);
                }
            }
            return urls;
        } catch (MalformedURLException e) {
            throw new RuntimeException("failed to complete classpathAsUrls.", e);
        }
    }

    @Input
    public FluidGeneratorPreferences getPreferences() {
        return preferences;
    }

    @Option(option = "preferences", description = "Configures the fluid generator preferences, you can filter classes to process using a regex (File).")
    public void setPreferences(FluidGeneratorPreferences preferences) {
        this.preferences = preferences;
    }

    @InputFile
    public File getGeneratedSourcesDirectory() {
        return generatedSourcesDirectory;
    }

    @Option(option = "generated-sources-directory", description = "Configures the source of generated sources (File).")
    public void setGeneratedSourcesDirectory(File generatedSourcesDirectory) {
        this.generatedSourcesDirectory = generatedSourcesDirectory;
    }

    @InputFiles
    public FileCollection getClasspath() {
        return classpath;
    }

    @Option(option = "classpath", description = "Configures the classpath (FileCollection).")
    public void setClasspath(FileCollection classpath) {
        this.classpath = classpath;
    }
}