package com.github.dwsoy.view;

import com.github.dwsoy.SoyCompiler;
import com.github.dwsoy.SoyTemplateRepository;
import com.github.dwsoy.config.DefaultSoyCompilerConfiguration;
import com.github.dwsoy.config.SoyCompilerConfiguration;
import com.yammer.dropwizard.Bundle;
import com.yammer.dropwizard.config.Bootstrap;
import com.yammer.dropwizard.config.Environment;

public class SoyViewBundle implements Bundle {

    private SoyCompilerConfiguration soyCompilerConfiguration;

    public SoyViewBundle() {
        this.soyCompilerConfiguration = new DefaultSoyCompilerConfiguration();
    }

    public SoyViewBundle(SoyCompilerConfiguration soyCompilerConfiguration) {
        this.soyCompilerConfiguration = soyCompilerConfiguration;
    }

    @Override
    public void initialize(Bootstrap<?> bootstrap) {}

    @Override
    public void run(Environment environment) {
        //author's note: I am a member of the IoC camp. However, I would like to keep this library and as thin as possible.
        environment.addProvider(
            new SoyViewMessageBodyWriter(
                    new SoyViewRenderer(
                            new SoyTemplateRepository(
                                    new SoyCompiler(this.soyCompilerConfiguration),
                                    environment.getObjectMapperFactory()))));
    }
}
