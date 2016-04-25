package org.dontpanic.spanners.events;

import org.elasticsearch.action.index.IndexAction;
import org.elasticsearch.action.index.IndexRequestBuilder;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.Client;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static com.jayway.jsonpath.matchers.JsonPathMatchers.*;
import static org.dontpanic.spanners.events.IndexEventListener.*;
import static org.junit.Assert.*;
import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.*;

/**
 * Tests for Elasticsearch index creation on events
 * Created by stevie on 24/04/16.
 */
@RunWith(MockitoJUnitRunner.class)
public class IndexEventListenerTest {

    @Mock private Client client;
    @InjectMocks private IndexEventListener eventListener = new IndexEventListener();
    private IndexRequestBuilder requestBuilder = null;

    @Before
    public void stubRequestBuilder() {
        requestBuilder = new StubIndexRequestBuilder(client);
        when(client.prepareIndex(anyString(), anyString())).thenReturn(requestBuilder);
    }

    @Test
    public void testIndexEvent() {

        // Trigger an event
        SpannerEvent event = new SpannerEvent(this, 42, SpannerEvent.EventType.UPDATE);
        eventListener.onApplicationEvent(event);

        // Verify that Elasticsearch index occurred
        verify(client).prepareIndex(INDEX, SPANNER_DOCUMENT);
    }


    @Test
    public void testDocumentContent() {

        // Trigger an event
        SpannerEvent event = new SpannerEvent(this, 42, SpannerEvent.EventType.UPDATE);
        eventListener.onApplicationEvent(event);

        // Verify that document contains required fields
        String docJson = requestBuilder.request().source().toUtf8();
        assertThat(docJson, hasJsonPath("$.eventType", equalTo("UPDATE")));
        assertThat(docJson, hasJsonPath("$.spannerId", equalTo(42)));
        assertThat(docJson, hasJsonPath("$.username", equalTo("***SL USERNAME")));
    }


    private class StubIndexRequestBuilder extends IndexRequestBuilder {
        public StubIndexRequestBuilder(Client client) {
            super(client, IndexAction.INSTANCE);
        }

        /**
         * Override get to prevent actual requests from being made
         */
        public IndexResponse get() {
            System.out.println("Stub request: " + request().toString());
            return null;
        }
    }

}
