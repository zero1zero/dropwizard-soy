package com.github.dwsoy.reload;

import org.apache.commons.io.monitor.FileAlterationObserver;

import java.io.File;

public class SoyFileAlterationObserver extends FileAlterationObserver {

    public SoyFileAlterationObserver(File soyFileDir, SoyFileAlterationListener listener) {
        super(soyFileDir);
        addListener(listener);
    }
}
