package org.dontpanic.spanners.events;

import org.elasticsearch.client.Client;
import org.springframework.context.ApplicationListener;

import java.util.HashMap;
import java.util.Map;

/**
 * Index the result of an event for future queries
 * Created by stevie on 24/04/16.
 */
public class IndexEventListener implements ApplicationListener<SpannerEvent> {

    public static final String INDEX = "spannersEvents";
    public static final String SPANNER_DOCUMENT = "spanner";
    public static final String USER_DOCUMENT = "user";

    public static final String KEY_USERNAME = "username";
    public static final String KEY_SPANNER_ID = "spannerId";
    public static final String KEY_EVENT_TYPE = "eventType";

    Client client = null;

    public void setClient(Client client) {
        this.client = client;
    }

    @Override
    public void onApplicationEvent(SpannerEvent spannerEvent) {
        Map<String, Object> doc = new HashMap<>();
        doc.put(KEY_USERNAME, "***SL USERNAME");
        doc.put(KEY_SPANNER_ID, spannerEvent.getSpannerId());
        doc.put(KEY_EVENT_TYPE, spannerEvent.getEventType());
        client.prepareIndex(INDEX, SPANNER_DOCUMENT).setSource(doc).get();
    }
}
