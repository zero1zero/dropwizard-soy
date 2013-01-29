package com.github.dwsoy;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.dwsoy.reload.SoyFileAlterationObserver;
import com.google.template.soy.tofu.SoyTofu;
import com.yammer.dropwizard.json.ObjectMapperFactory;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.Map;

@Singleton
public class SoyTemplateRepository {

    private SoyCompiler soyCompiler;
    private final ObjectMapper mapper;

    @Inject
    public SoyTemplateRepository(SoyCompiler soyCompiler, ObjectMapperFactory objectMapperFactory) {
        this.soyCompiler = soyCompiler;
        this.mapper = objectMapperFactory.build();
    }

    @SuppressWarnings("unchecked")
    public <T> String renderSoyTemplate(String name, T data) {
        SoyTofu.Renderer renderer = soyCompiler.getTofu().newRenderer(name);

        //TODO hookup message bundle based on locale?
//        renderer.setMsgBundle()
        Map values = mapper.convertValue(data, Map.class);

        renderer.setData(values);

        return renderer.render();
    }
}
