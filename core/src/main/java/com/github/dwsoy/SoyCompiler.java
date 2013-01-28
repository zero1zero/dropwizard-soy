package com.github.dwsoy;

import com.github.dwsoy.config.SoyCompilerConfiguration;
import com.github.dwsoy.reload.SoyFileAlterationListener;
import com.github.dwsoy.reload.SoyFileAlterationObserver;
import com.google.template.soy.SoyFileSet;
import com.google.template.soy.tofu.SoyTofu;
import com.sun.tools.javac.util.Assert;
import org.reflections.Reflections;
import org.reflections.scanners.ResourcesScanner;
import org.reflections.util.ClasspathHelper;
import org.reflections.util.ConfigurationBuilder;

import javax.inject.Singleton;
import java.io.File;
import java.net.URL;
import java.util.*;
import java.util.regex.Pattern;

@Singleton
public class SoyCompiler {

    private SoyCompilerConfiguration soyCompilerConfiguration;

    @SuppressWarnings("unused")
    private SoyFileAlterationObserver fileAlterationObserver;

    private SoyTofu tofu;

    public SoyCompiler(SoyCompilerConfiguration soyCompilerConfiguration) {
        this.soyCompilerConfiguration = soyCompilerConfiguration;

        URL soyDirURL = SoyCompiler.class.getClassLoader().getResource(this.soyCompilerConfiguration.getSoyFilesPath());
        if (soyDirURL == null) {
            throw new IllegalStateException("Cannot find directory containing soy files on the classpath (" +
                    this.soyCompilerConfiguration.getSoyFilesPath() + ")");
        }

        compileAll();

        File soyDir = new File(soyDirURL.getPath());
        if (soyDir.exists()) {
            this.fileAlterationObserver = new SoyFileAlterationObserver(soyDir, new SoyFileAlterationListener(this));
        }
    }

    public void compileAll() {
        SoyFileSet.Builder builder = new SoyFileSet.Builder();

        Reflections reflections = new Reflections(
                new ConfigurationBuilder()
                        .setUrls(ClasspathHelper.forPackage(soyCompilerConfiguration.getSoyFilesPath()))
                        .setScanners(new ResourcesScanner()));

        Set<String> soyFiles = reflections.getResources(Pattern.compile(".*\\.soy"));

        for (String file : soyFiles) {
            URL fileURL = SoyCompiler.class.getClassLoader().getResource(file);

            if (fileURL == null) {
                throw new IllegalStateException("Classpath lookup failed to find file previously discovered by reflections: " + file);
            }

            builder.add(new File(fileURL.getPath()));
        }

        SoyFileSet soyFileSet = builder.build();
        tofu = soyFileSet.compileToTofu();
    }

    public SoyTofu getTofu() {
        return tofu;
    }
}
