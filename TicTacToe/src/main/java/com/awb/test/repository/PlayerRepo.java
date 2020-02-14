package com.awb.test.repository;

import java.util.List;

import com.awb.test.Player;

public interface PlayerRepo {

	List<String> getPlayers();
	
	boolean isPlayerExists(String playerName);
	
	void addPlayer(String data);
	
	void updatePlayer(Player player);
	
}
