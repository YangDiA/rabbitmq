package com.book.rabbitmq.service;

import com.book.rabbitmq.message.QueueMessage;

public interface IAckMessageSendService {
     void send(QueueMessage message);
}
