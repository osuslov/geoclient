package gov.nyc.doitt.gis.geoclient.gradle;

import org.gradle.api.Action;
import org.gradle.api.Plugin;
import org.gradle.api.Project;
//import org.gradle.api.provider.Property;
import org.gradle.api.logging.Logger;
import org.gradle.api.logging.Logging;
import org.gradle.api.tasks.testing.Test;

public class GeoclientPlugin implements Plugin<Project> {

    private static final Logger logger = Logging.getLogger(GeoclientPlugin.class);

    public static final String SYSPROP_JAVA_IO_TEMPDIR = "java.io.tempdir";
    public static final String SYSPROP_JAVA_LIBRARY_PATH = "java.library.path";
    public static final String DEFAULT_SUBDIR_GS_GEOFILES = "fls"; // Requires trailing slash!
    public static final String DEFAULT_SUBDIR_GS_LIBRARY_PATH = "lib";
    public static final String ENV_VAR_GEOFILES = "GEOFILES";
    public static final String ENV_VAR_GEOSUPPORT_HOME = "GEOSUPPORT_HOME";
    public static final String ENV_VAR_GS_LIBRARY_PATH = "GS_LIBRARY_PATH";
    public static final String SYSPROP_GC_NATIVE_TEMP_DIR = "gc.jni.version";

    public void apply(Project project) {

        // @formatter:off
        logger.debug("Configuring GeoclientExtension...");
        final GeoclientExtension geoclient = project.getExtensions().create("geoclient", 
                                                                            GeoclientExtension.class,
                                                                            project);
        logger.info("GeoclientExtension configured successfully");
        logger.debug("Configuring GeosupportExtension...");
        final GeosupportExtension geosupport = project.getExtensions().create("geosupport",
                                                                              GeosupportExtension.class,
                                                                              project);
        logger.info("GeosupportExtension configured successfully");
        // @formatter:on

        final BuildConfigurationResolver resolver = new BuildConfigurationResolver(project, geoclient, geosupport);

        project.getTasks().withType(Test.class).configureEach(new Action<Test>() {
            public void execute(Test test) {
                test.systemProperty(GeoclientPlugin.SYSPROP_GC_NATIVE_TEMP_DIR,
                        resolver.resolveGeoclientNativeTempDir());
                test.systemProperty(SYSPROP_JAVA_LIBRARY_PATH, resolver.resolveGeosupportLibraryPath());
                test.environment(GeoclientPlugin.ENV_VAR_GEOFILES, resolver.resolveGeosupportGeofiles());
            }
        });
        // TODO fixme
        resolver.resolveGeoclientNativeTempDir();
        resolver.resolveGeosupportLibraryPath();
        resolver.resolveGeosupportGeofiles();
        project.getTasks().create("geosupportInfo", GeosupportInfo.class, project);
    }

    /*
     * task showMe { doLast() { logger.lifecycle("Gradle Properties:")
     * logger.lifecycle("      enableJavadoc: ${enableJavadoc}")
     * logger.lifecycle("             nojdoc: ${nojdoc}")
     * logger.lifecycle("       gcJniVersion: ${gcJniVersion}")
     * logger.lifecycle("      gsIncludePath: ${gsIncludePath}")
     * logger.lifecycle("      gsLibraryPath: ${gsLibraryPath}")
     * logger.lifecycle("         gsGeofiles: ${gsGeofiles}")
     * 
     * logger.lifecycle("Environment:")
     * logger.lifecycle("    GEOSUPPORT_HOME: ${System.getenv('GEOSUPPORT_HOME')}")
     * logger.lifecycle("           GEOFILES: ${System.getenv('GEOFILES')}")
     * logger.lifecycle("    LD_LIBRARY_PATH: ${System.getenv('LD_LIBRARY_PATH')}")
     * logger.lifecycle("               PATH: ${System.getenv('PATH')}")
     * 
     * logger.lifecycle("Java SystemProperties:") logger.
     * lifecycle("     gc.jni.version: ${System.getProperty('gc.jni.version')}")
     * logger.
     * lifecycle("  java.library.path: ${System.getProperty('java.library.path')}")
     * logger.
     * lifecycle("     java.io.tmpdir: ${System.getProperty('java.io.tmpdir')}") } }
     */
}
