package com.github.dwsoy.reload;

import com.github.dwsoy.SoyCompiler;
import org.apache.commons.io.monitor.FileAlterationListener;
import org.apache.commons.io.monitor.FileAlterationObserver;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.io.File;

@Singleton
public class SoyFileAlterationListener implements FileAlterationListener {

    private SoyCompiler soyCompiler;

    @Inject
    public SoyFileAlterationListener(SoyCompiler soyCompiler) {
        this.soyCompiler = soyCompiler;
    }

    @Override
    public void onStart(FileAlterationObserver observer) {}

    @Override
    public void onDirectoryCreate(File directory) {}

    @Override
    public void onDirectoryChange(File directory) {}

    @Override
    public void onDirectoryDelete(File directory) {}

    @Override
    public void onFileCreate(File file) {
        soyCompiler.compileAll();
    }

    @Override
    public void onFileChange(File file) {
        soyCompiler.compileAll();
    }

    @Override
    public void onFileDelete(File file) {
        soyCompiler.compileAll();
    }

    @Override
    public void onStop(FileAlterationObserver observer) {}
}
