package com.imadelfetouh.adminservice.rabbit;

import java.util.logging.Level;
import java.util.logging.Logger;

public class RabbitNonStopConsumer extends ChannelHelper{

    private static final Logger logger = Logger.getLogger(RabbitNonStopConsumer.class.getName());

    public void consume(NonStopConsumer consumer) {
        try {
            consumer.consume(getChannel());
        }
        catch (Exception e){
            logger.log(Level.ALL, e.getMessage());
        }
        finally {
            closeChannel();
        }
    }
}
