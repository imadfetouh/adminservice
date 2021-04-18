package com.imadelfetouh.adminservice.rabbit;

import com.rabbitmq.client.Channel;

public interface Producer {

    void produce(Channel channel);
}
