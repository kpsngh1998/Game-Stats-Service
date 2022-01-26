package com.service.game.GameService.config;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;

import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.common.TopicPartition;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.listener.ContainerAwareErrorHandler;
import org.springframework.kafka.listener.MessageListenerContainer;
import org.springframework.kafka.support.serializer.DeserializationException;

public class KafkaExceptionHandler implements ContainerAwareErrorHandler{

	final static Logger logger = LoggerFactory.getLogger(KafkaExceptionHandler.class);
	
	@Override
	public void handle(Exception thrownException, List<ConsumerRecord<?, ?>> records, Consumer<?, ?> consumer,
			MessageListenerContainer container) {
		
		doSeeks(records, consumer);
		if (!records.isEmpty()) {
            ConsumerRecord<?, ?> record = records.get(0);
            String topic = record.topic();
            long offset = record.offset();
            int partition = record.partition();
            if (thrownException.getClass().equals(DeserializationException.class)) {
                DeserializationException exception = (DeserializationException) thrownException;
                String malformedMessage = new String(exception.getData());
                logger.info("Skipping message with topic {} and offset {} " +
                        "- malformed message: {} , exception: {}", topic, offset, malformedMessage, exception.getLocalizedMessage());
            } else {
                logger.info("Skipping message with topic {} - offset {} - partition {} - exception {}", topic, offset, partition, thrownException);
            }
        } else {
            logger.info("Consumer exception - cause: {}", thrownException.getMessage());
        }
	}
	
	private void doSeeks(List<ConsumerRecord<?, ?>> records, Consumer<?, ?> consumer) {
        Map<TopicPartition, Long> partitions = new LinkedHashMap<>();
        AtomicBoolean first = new AtomicBoolean(true);
        records.forEach(record ->  {
            if (first.get()) {
                partitions.put(new TopicPartition(record.topic(), record.partition()), record.offset() + 1);
            } else {
                partitions.computeIfAbsent(new TopicPartition(record.topic(), record.partition()),
                        offset -> record.offset());
            }
            first.set(false);
        });
        partitions.forEach(consumer::seek);
    }

}
