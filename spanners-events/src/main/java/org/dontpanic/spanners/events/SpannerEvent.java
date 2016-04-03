package org.dontpanic.spanners.events;

import org.springframework.context.ApplicationEvent;

/**
 * Created by stevie on 02/04/16.
 */
public class SpannerEvent extends ApplicationEvent {

    public enum EventType {
        CREATE,
        UPDATE,
        DELETE
    }

    private int spannerId;
    private EventType eventType;

    public int getSpannerId() {
        return spannerId;
    }

    public EventType getEventType() {
        return eventType;
    }

    public SpannerEvent(Object source, int spannerId, EventType eventType) {
        super(source);
        this.spannerId = spannerId;
        this.eventType = eventType;
    }

    @Override
    public String toString() {
        return "Event: " + getSource();
    }
}
