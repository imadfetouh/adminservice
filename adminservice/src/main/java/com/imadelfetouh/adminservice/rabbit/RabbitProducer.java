package com.imadelfetouh.adminservice.rabbit;

import java.util.logging.Level;
import java.util.logging.Logger;

public class RabbitProducer extends ChannelHelper {

    private static final Logger logger = Logger.getLogger(RabbitProducer.class.getName());

    public void produce(Producer producer) {
        try {
            producer.produce(getChannel());
        }
        catch (Exception e){
            logger.severe(e.getMessage());
        }
        finally {
            closeChannel();
        }
    }
}
