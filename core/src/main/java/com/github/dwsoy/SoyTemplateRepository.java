package com.github.dwsoy;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.dwsoy.reload.SoyFileAlterationListener;
import com.github.dwsoy.reload.SoyFileAlterationObserver;
import com.google.common.base.Optional;
import com.google.template.soy.tofu.SoyTofu;
import com.yammer.dropwizard.json.ObjectMapperFactory;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.io.File;
import java.util.Map;

@Singleton
public class SoyTemplateRepository {

    private final SoyCompiler soyCompiler;
    private final ObjectMapper mapper;
    private Optional<SoyFileAlterationObserver> fileAlterationObserver = Optional.absent();

    @Inject
    public SoyTemplateRepository(SoyCompiler soyCompiler, ObjectMapperFactory objectMapperFactory) {
        this.soyCompiler = soyCompiler;
        this.mapper = objectMapperFactory.build();

        File soyDir = this.soyCompiler.getSoyDir();
        //if the soy file directory exists, lets monitor it for file changes to recompile
        if (soyDir.exists()) {
            this.fileAlterationObserver = Optional.of(new SoyFileAlterationObserver(soyDir, new SoyFileAlterationListener(soyCompiler)));
        }
    }

    @SuppressWarnings("unchecked")
    public <T> String renderSoyTemplate(String name, T data) {
        //for every template call, we are checking for updates.  This is meant to only be called when in local dev mode.
        if (fileAlterationObserver.isPresent()) {
            fileAlterationObserver.get().checkAndNotify();
        }

        SoyTofu.Renderer renderer = soyCompiler.getTofu().newRenderer(name);

        //TODO hookup message bundle based on locale?
//        renderer.setMsgBundle()
        Map values = mapper.convertValue(data, Map.class);

        renderer.setData(values);

        return renderer.render();
    }
}
