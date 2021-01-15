package com.artemis;

import com.artemis.Weaver;
import com.artemis.WeaverLog;

import org.gradle.api.DefaultTask;
import org.gradle.api.file.FileCollection;
import org.gradle.api.logging.Logger;
import org.gradle.api.tasks.Input;
import org.gradle.api.tasks.Optional;
import org.gradle.api.tasks.OutputDirectories;
import org.gradle.api.tasks.OutputDirectory;
import org.gradle.api.tasks.TaskAction;
import org.gradle.api.tasks.options.Option;

import java.io.File;

/**
 * Weaving wrapper for gradle.
 *
 * @author Adrian Papari
 * @author Daan van Yperen
 */
public class ArtemisWeavingTask extends DefaultTask {

    /**
     * Root folder for class files.
     *
     * @deprecated use classesDirs
     */
    @Optional
    @Deprecated
    private File classesDir;

    /**
     * Root directories for class files.
     */
    @Optional
    private FileCollection classesDirs;

    private boolean enablePooledWeaving;

    private boolean enableArtemisPlugin;

    private boolean optimizeEntitySystems;

    private boolean generateLinkMutators;

    @TaskAction
    public void weave() {
        getLogger().info("Artemis plugin started.");

        if (!enableArtemisPlugin) {
            getLogger().info("Plugin disabled via 'enableArtemisPlugin' set to false.");
            return;
        }

        long start = System.currentTimeMillis();
        //@todo provide gradle alternative.
        //if (context != null && !context.hasDelta(sourceDirectory)) return;

        Logger log = getLogger();

//		log.info("");
        log.info("CONFIGURATION");
        log.info(WeaverLog.LINE.replaceAll("\n", ""));
        log.info(WeaverLog.format("enablePooledWeaving", enablePooledWeaving));
        log.info(WeaverLog.format("generateLinkMutators", generateLinkMutators));
        log.info(WeaverLog.format("optimizeEntitySystems", optimizeEntitySystems));
        if (classesDirs != null && !classesDirs.isEmpty()) {
            log.info(WeaverLog.format("outputDirectories", classesDirs.getFiles()));
        } else {
            log.info(WeaverLog.format("outputDirectory", classesDir));
        }
        log.info(WeaverLog.LINE.replaceAll("\n", ""));

        Weaver.enablePooledWeaving(enablePooledWeaving);
        Weaver.generateLinkMutators(generateLinkMutators);
        Weaver.optimizeEntitySystems(optimizeEntitySystems);

        Weaver weaver;
        if (classesDirs != null && !classesDirs.isEmpty()) {
            weaver = new Weaver(classesDirs.getFiles());
        } else {
            weaver = new Weaver(classesDir);
        }
        WeaverLog processed = weaver.execute();
        for (String s : processed.getFormattedLog().split("\n")) {
            log.info(s);
        }
    }

    @Input
    public boolean isEnableArtemisPlugin() {
        return enableArtemisPlugin;
    }

    @Input
    public boolean isGenerateLinkMutators() {
        return generateLinkMutators;
    }

    @Input
    public boolean isEnablePooledWeaving() {
        return enablePooledWeaving;
    }

    @Input
    public boolean isOptimizeEntitySystems() {
        return optimizeEntitySystems;
    }

    @Option(option = "enable-artemis-plugin", description = "If false, no weaving will take place (useful for debugging).")
    public void setEnableArtemisPlugin(boolean enableArtemisPlugin) {
        this.enableArtemisPlugin = enableArtemisPlugin;
    }

    @Option(option = "enable-pooled-weaving", description = "Enabled weaving of pooled components (more viable on Android than JVM).")
    public void setEnablePooledWeaving(boolean enablePooledWeaving) {
        this.enablePooledWeaving = enablePooledWeaving;
    }

    @Option(option = "generate-link-mutators", description = "Generate optimized read/write classes for entity link fields, used")
    public void setGenerateLinkMutators(boolean generateLinkMutators) {
        this.generateLinkMutators = generateLinkMutators;
    }

    @Option(option = "optimize-entity-systems", description = "Compile Optimized Systems")
    public void setOptimizeEntitySystems(boolean optimizeEntitySystems) {
        this.optimizeEntitySystems = optimizeEntitySystems;
    }

    @OutputDirectory
    public File getClassesDir() {
        return classesDir;
    }

    public void setClassesDir(File classesDir) {
        this.classesDir = classesDir;
    }

    @OutputDirectories
    public FileCollection getClassesDirs() {
        return classesDirs;
    }

    public void setClassesDirs(FileCollection classesDirs) {
        this.classesDirs = classesDirs;
    }
}