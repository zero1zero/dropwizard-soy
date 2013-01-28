package com.github.dwsoy.view;

import com.github.dwsoy.SoyTemplateRepository;
import com.github.dwsoy.view.SoyView;
import com.google.inject.Inject;

import javax.inject.Singleton;
import javax.ws.rs.WebApplicationException;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.Charset;
import java.util.Locale;

@Singleton
public class SoyViewRenderer {

    private SoyTemplateRepository templateRepository;

    @Inject
    public SoyViewRenderer(SoyTemplateRepository templateRepository) {
        this.templateRepository = templateRepository;
    }

    public void render(SoyView view, Locale locale, OutputStream output) throws IOException, WebApplicationException {

        String rendered = templateRepository.renderSoyTemplate(view.getTemplateName(), view.getData());

        output.write(rendered.getBytes(Charset.forName("UTF-8")));
    }
}
