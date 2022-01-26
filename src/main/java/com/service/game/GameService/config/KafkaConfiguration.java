package com.service.game.GameService.config;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.ErrorHandlingDeserializer;
import org.springframework.kafka.support.serializer.JsonDeserializer;

import com.service.game.GameService.model.Player;
import com.service.game.GameService.util.KafkaProperties;

@EnableKafka
@Configuration
public class KafkaConfiguration {
	
	@Bean
	public ConsumerFactory<String, Player> userConsumerFactory(){
	
		Map<String, Object> config = new HashMap<>();
		config.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, KafkaProperties.BROKER);
		config.put(ConsumerConfig.GROUP_ID_CONFIG, KafkaProperties.GROUP_ID_CONFIG);
		config.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
		config.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class);
		ErrorHandlingDeserializer<String> headerErrorHandlingDeserializer = new ErrorHandlingDeserializer<>(
				new StringDeserializer());
		ErrorHandlingDeserializer<Player> errorHandlingDeserializer = new ErrorHandlingDeserializer<>(
				new JsonDeserializer<>(Player.class));

		return new DefaultKafkaConsumerFactory<>(config, headerErrorHandlingDeserializer, errorHandlingDeserializer);

	}
	
	@Bean
	public ConcurrentKafkaListenerContainerFactory<String, Player> userKafkaListenerFactory(){
		
		ConcurrentKafkaListenerContainerFactory<String, Player> factory = new ConcurrentKafkaListenerContainerFactory<>();
		factory.setErrorHandler(new KafkaExceptionHandler());
		factory.setConsumerFactory(userConsumerFactory());
		//factory.setBatchListener(true);    // enable to support BatchProcessing
		return factory;
	}
	
}
