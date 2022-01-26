package com.service.game.GameService.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.service.game.GameService.model.TopScorePlayers;
import com.service.game.GameService.service.ScoreLoadService;

@RestController
@RequestMapping(value = "/game/v1")
public class GameController {

	final static Logger logger = LoggerFactory.getLogger(GameController.class);
	
	@Autowired
	private ScoreLoadService scoreLoadService;
	
	@RequestMapping(method = RequestMethod.GET, path = "/topscorer")
	public ResponseEntity<TopScorePlayers> getTop5Scorers(){
		try {
			TopScorePlayers topPlayers = new TopScorePlayers(scoreLoadService.getTop5PlayersByScore());
			logger.debug("Returing topscorers response: {}", topPlayers);
			return new ResponseEntity<TopScorePlayers>(topPlayers, HttpStatus.ACCEPTED);	
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@RequestMapping(method = RequestMethod.GET, path = "/topscorer/unique")
	public ResponseEntity<TopScorePlayers> getTop5UniqueScorers(){
		try {
			TopScorePlayers topPlayers = new TopScorePlayers(scoreLoadService.getTop5PlayersByUniqueScore());
			logger.debug("Returing unique topscorers response : {}", topPlayers);
			return new ResponseEntity<TopScorePlayers>(topPlayers, HttpStatus.ACCEPTED);	
		} catch(Exception e){
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@RequestMapping(method = RequestMethod.GET, path = "/topscorer/{k}")
	public ResponseEntity<TopScorePlayers> getTop5Scorers(Integer k){
		try {
			TopScorePlayers topPlayers = new TopScorePlayers(scoreLoadService.getTopKPlayersByScore(k));
			logger.debug("Returing k topscorers response: {}", topPlayers);
			return new ResponseEntity<TopScorePlayers>(topPlayers, HttpStatus.ACCEPTED);	
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}	
	}
	
}
