package com.github.dwsoy.view;

import com.github.dwsoy.view.SoyView;
import com.google.common.annotations.VisibleForTesting;
import com.yammer.metrics.core.TimerContext;

import javax.inject.Inject;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.ext.MessageBodyWriter;
import javax.ws.rs.ext.Provider;
import java.io.IOException;
import java.io.OutputStream;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Locale;

@Provider
@Produces({MediaType.TEXT_HTML, MediaType.APPLICATION_XHTML_XML})
public class SoyViewMessageBodyWriter implements MessageBodyWriter<SoyView> {

    @Context
    @SuppressWarnings("FieldMayBeFinal")
    private HttpHeaders headers;

    private SoyViewRenderer renderer;

    @SuppressWarnings("UnusedDeclaration")
    @Inject
    public SoyViewMessageBodyWriter(SoyViewRenderer renderer) {
        this(null, renderer);
    }

    @VisibleForTesting
    public SoyViewMessageBodyWriter(HttpHeaders headers, SoyViewRenderer renderer) {
        this.headers = headers;
        this.renderer = renderer;
    }

    @Override
    public boolean isWriteable(Class<?> type, Type genericType, Annotation[] annotations, MediaType mediaType) {
        return SoyView.class.isAssignableFrom(type);
    }

    @Override
    public long getSize(SoyView t,
                        Class<?> type,
                        Type genericType,
                        Annotation[] annotations,
                        MediaType mediaType) {
        return -1;
    }

    @Override
    public void writeTo(SoyView t,
                        Class<?> type,
                        Type genericType,
                        Annotation[] annotations,
                        MediaType mediaType,
                        MultivaluedMap<String, Object> httpHeaders,
                        OutputStream entityStream) throws IOException, WebApplicationException {
        final TimerContext context = t.getRenderingTimer().time();
        try {
            renderer.render(t, detectLocale(headers), entityStream);
        } finally {
            context.stop();
        }
    }

    private Locale detectLocale(HttpHeaders headers) {
        final List<Locale> languages = headers.getAcceptableLanguages();
        for (Locale locale : languages) {
            return locale;
        }

        return Locale.getDefault();
    }
}
