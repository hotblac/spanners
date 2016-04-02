package org.dontpanic.spanners.events;

import org.springframework.context.ApplicationEvent;

/**
 * Created by stevie on 02/04/16.
 */
public class SpannerEvent extends ApplicationEvent {

    public SpannerEvent(Object source) {
        super(source);
    }
}
