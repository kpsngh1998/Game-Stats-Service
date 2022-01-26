package com.service.game.GameService.controller;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.service.game.GameService.model.Player;
import com.service.game.GameService.model.PlayerGameCompositeKey;
import com.service.game.GameService.model.ScoreBoardRecord;
import com.service.game.GameService.service.ScoreLoadService;
import com.service.game.GameService.service.ScoreLoadServiceImpl;

public class GameControllerTest {

	@Mock
	private ScoreLoadServiceImpl scoreLoadService;
	
	@InjectMocks
	private GameController gameController;
	
	@BeforeEach
    public void init(){
        MockitoAnnotations.initMocks(this);
    }
	
	private List<Player> getTop5Players() {
		List<Player> players = new ArrayList<>();
		for(int i=0; i<5; i++){
			Player player = new Player();
			player.setPlayerGameCompositeKey(new PlayerGameCompositeKey(String.valueOf(i), String.valueOf(i)));
			player.setPlayerName("PLayer" + i );
			player.setScore((long)i);
			players.add(player);
		}
		return players;
	}
	
	private static List<ScoreBoardRecord> convert(List<Player> players) {
		List<ScoreBoardRecord> sbr = new ArrayList<>();
		for(Player p : players){
			ScoreBoardRecord s = new ScoreBoardRecord();
			s.setPlayerId(p.getPlayerGameCompositeKey().getPlayerId());
			s.setGameId(p.getPlayerGameCompositeKey().getGameId());
			s.setPlayerName(p.getPlayerName());
			s.setScore(p.getScore());
			sbr.add(s);
		}
		return sbr;
	}
	
	 @Test
	    public void getTop5ScoresSuccess(){
	        List<Player> players = getTop5Players();
	        Mockito.when(scoreLoadService.getTop5PlayersByUniqueScore()).thenReturn(players);
	        List<ScoreBoardRecord> scoreBoard = convert(players);
	        System.out.println(scoreBoard);
	        Assertions.assertEquals(scoreBoard.size(), gameController.getTop5UniqueScorers().getTopPlayersScoreBoard().size());
	}

	@Test
    public void getTop5ScoresFailure(){
        List<Player> players = new ArrayList<>();
        Mockito.when(scoreLoadService.getTop5PlayersByUniqueScore()).thenReturn(players);
        Assertions.assertEquals(players,gameController.getTop5UniqueScorers().getTopPlayersScoreBoard());
    }

   
}
