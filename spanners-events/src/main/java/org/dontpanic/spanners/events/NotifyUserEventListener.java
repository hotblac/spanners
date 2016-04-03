package org.dontpanic.spanners.events;

import org.springframework.context.ApplicationListener;
import org.springframework.web.client.RestTemplate;

import java.util.logging.Logger;

/**
 * Notify users on updates to spanners
 * Created by stevie on 02/04/16.
 */
public class NotifyUserEventListener implements ApplicationListener<SpannerEvent> {

    final static Logger logger = Logger.getLogger(NotifyUserEventListener.class.getName());

    private String notificationServiceUrl;
    private RestTemplate restTemplate;

    public void setNotificationServiceUrl(String notificationServiceUrl) {
        this.notificationServiceUrl = notificationServiceUrl;
    }

    public void setRestTemplate(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public void onApplicationEvent(SpannerEvent spannerEvent) {
        logger.info("Event: notification of " + spannerEvent);
        restTemplate.postForObject(notificationServiceUrl, Integer.toString(spannerEvent.getSpannerId()), String.class);
    }
}
