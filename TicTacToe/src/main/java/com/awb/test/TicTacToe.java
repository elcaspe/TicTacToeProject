package com.awb.test;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.core.JdbcTemplate;

import com.awb.test.repository.PlayerRepo;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

@SpringBootApplication
public class TicTacToe implements CommandLineRunner {
	
	@Autowired
    private JdbcTemplate jdbcTemplate;
	
	@Autowired
	private PlayerRepo repo;
	
	private Gson gson = new GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.UPPER_CAMEL_CASE).create();

	public static void main(String[] args) {
		SpringApplication.run(TicTacToe.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		createTable();
	}
	
	void createTable() {
		jdbcTemplate.execute("DROP TABLE IF EXISTS players");
		jdbcTemplate.execute("CREATE TABLE players(" + "id SERIAL, data jsonb)");
	}
	
	public void savePlayer(Player player) {
		if (repo.isPlayerExists(player.getName())) {
			repo.updatePlayer(player);
		} else {
			repo.addPlayer(player.toJsonString());
		}
	}
	
	public List<Player> getAllPlayers() {
		List<Player> ps = new ArrayList<>();
		for (String json : repo.getPlayers()) {
			ps.add(gson.fromJson(json, Player.class));
		}
		return ps;
	}

}
