package com.github.dwsoy.config;

import javax.inject.Singleton;

@Singleton
public class DefaultSoyCompilerConfiguration implements SoyCompilerConfiguration {

    @Override
    public String getSoyFilesPath() {
        return "soy";
    }
}
