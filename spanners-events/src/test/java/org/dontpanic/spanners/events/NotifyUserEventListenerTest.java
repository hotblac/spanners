package org.dontpanic.spanners.events;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;

import static org.junit.Assert.fail;
import static org.mockito.Mockito.*;
import static org.dontpanic.spanners.events.SpannerEvent.EventType;

/**
 * Created by stevie on 02/04/16.
 */
@RunWith(MockitoJUnitRunner.class)
public class NotifyUserEventListenerTest {

    private static final String NOTIFICATION_URL = "http://example.com/notificationService";

    @Mock private RestTemplate restTemplate;
    @InjectMocks private NotifyUserEventListener listener = new NotifyUserEventListener();

    @Before
    public void onSetup() {
        listener.setNotificationServiceUrl(NOTIFICATION_URL);
    }

    @Test
    public void testNotification() {
        int spannerId = 42;
        SpannerEvent event = new SpannerEvent(this, spannerId, EventType.UPDATE);
        listener.onApplicationEvent(event);

        // Verify call through REST template
        verify(restTemplate).postForObject(NOTIFICATION_URL, Integer.toString(spannerId), String.class);
    }


    @Test
    public void testNetworkError() {
        ResourceAccessException networkError = new ResourceAccessException("Connection refused");
        when(restTemplate.postForObject(anyString(), anyString(), any(Class.class))).thenThrow(networkError);

        try {
            SpannerEvent event = new SpannerEvent(this, 42, EventType.UPDATE);
            listener.onApplicationEvent(event);
        } catch (Exception e) {
            // Network errors should not be rethrown
            fail("Unexpected exception: " + e);
        }



    }


}
