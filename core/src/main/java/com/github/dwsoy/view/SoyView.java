package com.github.dwsoy.view;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.yammer.metrics.Metrics;
import com.yammer.metrics.core.Timer;

public class SoyView<T> {

    private final String templateName;
    private final T data;
    private final Timer renderingTimer;

    public SoyView(String templateName, T data) {
        this.templateName = templateName;
        this.data = data;
        this.renderingTimer = Metrics.defaultRegistry().newTimer(getClass(), "rendering");
    }

    @JsonIgnore
    public String getTemplateName() {
        return templateName;
    }

    @JsonIgnore
    public Timer getRenderingTimer() {
        return renderingTimer;
    }

    public T getData() {
        return data;
    }
}
