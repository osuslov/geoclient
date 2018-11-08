package gov.nyc.doitt.gis.geoclient.jni.util;

//
// This class is copied from Facebook's RocksDB v5.9.2
// See https://github.com/facebook/rocksdb
//

public class Environment {
  private static String OS = System.getProperty("os.name").toLowerCase();
  private static String ARCH = System.getProperty("os.arch").toLowerCase();

  public static boolean isPowerPC() {
    return ARCH.contains("ppc");
  }

  public static boolean isWindows() {
    return (OS.contains("win"));
  }

  public static boolean isMac() {
    return (OS.contains("mac"));
  }

  public static boolean isAix() {
    return OS.contains("aix");
  }
  
  public static boolean isUnix() {
    return OS.contains("nix") ||
        OS.contains("nux");
  }

  public static boolean isSolaris() {
    return OS.contains("sunos");
  }

  public static boolean is64Bit() {
    if (ARCH.indexOf("sparcv9") >= 0) {
      return true;
    }
    return (ARCH.indexOf("64") > 0);
  }

  public static String getSharedLibraryName(final String name) {
    return name + "jni";
  }

  public static String getSharedLibraryFileName(final String name) {
	  String prefix = !isWindows() ? "lib" : "";
	  return appendLibOsSuffix(prefix + getSharedLibraryName(name), true);
  }

  public static String getJniLibraryName(final String name) {
    if (isUnix()) {
      final String arch = is64Bit() ? "64" : "32";
      if(isPowerPC()) {
        return String.format("%sjni-linux-%s", name, ARCH);
      } else {
        return String.format("%sjni-linux%s", name, arch);
      }
    } else if (isMac()) {
      return String.format("%sjni-osx", name);
    } else if (isAix() && is64Bit()) {
      return String.format("%sjni-aix64", name);
    } else if (isSolaris()) {
      final String arch = is64Bit() ? "64" : "32";
      return String.format("%sjni-solaris%s", name, arch);
    } else if (isWindows() && is64Bit()) {
      return String.format("%sjni-win64", name);
    }

    throw new UnsupportedOperationException(String.format("Cannot determine JNI library name for ARCH='%s' OS='%s' name='%s'", ARCH, OS, name));
  }

  public static String getJniLibraryFileName(final String name) {
	  String prefix = !isWindows() ? "lib" : "";
	  return appendLibOsSuffix(prefix + getJniLibraryName(name), false);
  }

  private static String appendLibOsSuffix(final String libraryFileName, final boolean shared) {
    if (isUnix() || isAix() || isSolaris()) {
      return libraryFileName + ".so";
    } else if (isMac()) {
      return libraryFileName + (shared ? ".dylib" : ".jnilib");
    } else if (isWindows()) {
      return libraryFileName + ".dll";
    }
    throw new UnsupportedOperationException();
  }

  public static String getJniLibraryExtension() {
    if (isWindows()) {
      return ".dll";
    }
    return (isMac()) ? ".jnilib" : ".so";
  }
}