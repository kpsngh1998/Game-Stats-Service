package com.service.game.GameService.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.service.game.GameService.model.Player;
import com.service.game.GameService.model.PlayerGameCompositeKey;
import com.service.game.GameService.repository.PlayerScoreRepository;

public class ScoreLoadServiceTest {

	 @Mock
	 private PlayerScoreRepository playerScoreRepository;

	 @InjectMocks
	 private ScoreLoadServiceImpl scoreLoadingService;

	 @BeforeEach
	 public void init(){
	     MockitoAnnotations.initMocks(this);
	 }
	 
	 /*
	  * 
	  * 
	  @Test
    public void getTop5PlayerTest(){
        List<Player> players = getTop5Player();
        Mockito.when(playerRepository.findTop5ByOrderByScoreDesc()).thenReturn(players);
        Assertions.assertEquals(players,scoreLoadingService.getTop5PlayersByScore());
    }

    @Test
    public void getScoreOfPlayerSuccess(){
        Player player = new Player();
        player.setPlayerId("123");
        player.setPlayerName("abc");
        player.setScore(20l);
        Mockito.when(playerRepository.findById(Mockito.anyString())).thenReturn(Optional.of(player));
        Assertions.assertEquals(player.getScore(),scoreLoadingService.getScoreOfPlayer(player));
    }

    @Test
    public void getScoreOfPlayerFailure(){
        Player player = new Player();
        Mockito.when(playerRepository.findById(Mockito.anyString())).thenReturn(Optional.empty());
        Assertions.assertEquals(0,scoreLoadingService.getScoreOfPlayer(player));
    }

    private List<Player> getTop5Player(){
        List<Player> players = new ArrayList<>();
        for(int i=0;i<5;i++){
            Player player = new Player();
            player.setPlayerId(String.valueOf(i));
            player.setScore((long)i);
            player.setPlayerName("str"+i);
            players.add(player);
        }
        return players;
    }
	  */

	@Test
	public void getTop5PlayerTest() {
		List<Player> players = getTop5Player();
		Mockito.when(playerScoreRepository.findTop5ByOrderByScoreDesc()).thenReturn(players);
		Assertions.assertEquals(players, scoreLoadingService.getTop5PlayersByScore());
	}
	 
	@Test
	public void getScoreOfPlayerSuccess() {
		Player player = new Player();
		player.setPlayerGameCompositeKey(new PlayerGameCompositeKey("1", "1"));
		player.setPlayerName("Player1");
		player.setScore((long) 1);
		Mockito.when(playerScoreRepository.findById(Mockito.any())).thenReturn(Optional.of(player));
		Assertions.assertEquals(player.getScore(), scoreLoadingService.getScore(player));
	}

	@Test
	public void getScoreOfPlayerFailure() {
		Player player = new Player();
		Mockito.when(playerScoreRepository.findById(Mockito.any())).thenReturn(Optional.empty());
		Assertions.assertEquals(0, scoreLoadingService.getScore(player));
	}
	
	private List<Player> getTop5Player() {
		List<Player> players = new ArrayList<>();
		for (int i = 0; i < 5; i++) {
			Player player = new Player();
			player.setPlayerGameCompositeKey(new PlayerGameCompositeKey(String.valueOf(i), String.valueOf(i)));
			player.setScore((long) i);
			player.setPlayerName("Player" + i);
			players.add(player);
		}
		return players;
	}
}
