package com.imadelfetouh.adminservice.rabbit;

import com.rabbitmq.client.Channel;

import java.io.IOException;
import java.util.concurrent.TimeoutException;
import java.util.logging.Level;
import java.util.logging.Logger;

public abstract class ChannelHelper {

    private static final Logger logger = Logger.getLogger(ChannelHelper.class.getName());

    private final Channel channel;

    protected ChannelHelper() {
        this.channel = RabbitConfiguration.getInstance().getChannel();
    }

    protected Channel getChannel() {
        return channel;
    }

    protected void closeChannel() {
        try {
            if(channel != null && channel.isOpen()) {
                channel.close();
            }
        } catch (IOException | TimeoutException e) {
            logger.severe(e.getMessage());
        }
    }
}
