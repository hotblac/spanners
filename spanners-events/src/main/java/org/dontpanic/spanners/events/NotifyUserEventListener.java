package org.dontpanic.spanners.events;

import org.springframework.context.ApplicationListener;

import java.util.logging.Logger;

/**
 * Notify users on updates to spanners
 * Created by stevie on 02/04/16.
 */
public class NotifyUserEventListener implements ApplicationListener<SpannerEvent> {

    final static Logger logger = Logger.getLogger(NotifyUserEventListener.class.getName());

    @Override
    public void onApplicationEvent(SpannerEvent spannerEvent) {
        logger.info("***SL Event recieved!");
    }
}
