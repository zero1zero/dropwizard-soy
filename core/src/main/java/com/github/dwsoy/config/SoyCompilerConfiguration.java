package com.github.dwsoy.config;

public interface SoyCompilerConfiguration {

    /**
     * Determines where on the classpath soy files live.  This path will be scanned for .soy files to be shoved into
     * the tofu object
     *
     * Example: "soy"
     *
     * @return path on the classpath to find soy files (excluding slash)
     */
    String getSoyFilesPath();
}
