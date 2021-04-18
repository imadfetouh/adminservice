package com.imadelfetouh.adminservice.rabbit;

import com.rabbitmq.client.Channel;

public interface NonStopConsumer {

    void consume(Channel channel);
}
