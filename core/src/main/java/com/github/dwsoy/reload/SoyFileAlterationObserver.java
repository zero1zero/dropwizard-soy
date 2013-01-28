package com.github.dwsoy.reload;

import com.github.dwsoy.SoyCompiler;
import org.apache.commons.io.monitor.FileAlterationObserver;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.inject.Singleton;
import java.io.File;

public class SoyFileAlterationObserver extends FileAlterationObserver {

    public SoyFileAlterationObserver(File soyFileDir, SoyFileAlterationListener listener) {
        super(soyFileDir);
        addListener(listener);
    }
}
