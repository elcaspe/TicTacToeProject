package com.awb.test.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.awb.test.Player;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

@Repository
public class PlayerRepoImpl implements PlayerRepo {
	
	@Autowired
    private JdbcTemplate jdbcTemplate;
	
	private Gson gson = new GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.UPPER_CAMEL_CASE).create();

	@Override
	public List<String> getPlayers() {
		return jdbcTemplate.queryForList("select data from players order by data->'Score' desc", String.class);
	}

	@Override
	public boolean isPlayerExists(String playerName) {
		String sql = "select count(*) from players where data->>'Name' = '" + playerName + "'";
		return jdbcTemplate.queryForObject(sql, Integer.class) == 0 ? false : true;
	}

	@Override
	public void addPlayer(String data) {
		String sql = "insert into players(data) values('" + data + "')";
		jdbcTemplate.update(sql);
	}

	@Override
	public void updatePlayer(Player player) {
		String sql = "select data from players where data->>'Name' = '" + player.getName() + "'";
		Player op = gson.fromJson(jdbcTemplate.queryForObject(sql, String.class), Player.class);
		op.setScore(op.getScore() + 1);
		sql = "update players set data='" + op.toJsonString() + "' where data->>'Name' = '" + player.getName() + "'";
		jdbcTemplate.update(sql);
	}

}
