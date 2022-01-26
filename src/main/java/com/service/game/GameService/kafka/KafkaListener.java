package com.service.game.GameService.kafka;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.service.game.GameService.model.Player;
import com.service.game.GameService.service.ScoreLoadService;
import com.service.game.GameService.util.KafkaProperties;

@Service
public class KafkaListener {
	
	final static Logger logger = LoggerFactory.getLogger(KafkaListener.class);
	
	@Autowired
	private ScoreLoadService scoreLoadService;
	
	@org.springframework.kafka.annotation.KafkaListener(topics = {KafkaProperties.TOPIC_SCORE}, groupId = KafkaProperties.GROUP_ID_CONFIG, containerFactory = "userKafkaListenerFactory")
	public void consume(Player player) {
		logger.info("Recieved message from topic {} with info {}", KafkaProperties.TOPIC_SCORE, player);
		scoreLoadService.savePlayer(player);
		logger.info("Data saved for player {}", player);
	}
}
