package com.awb.test.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.awb.test.Player;
import com.awb.test.TicTacToe;

@Controller
@RequestMapping("/TicTacToe")
public class HighScoresController {
	
	@Autowired
	private TicTacToe ticTacToe;

	@RequestMapping("/high-scores")
	public String ticTacToe(Model model) {
		model.addAttribute("high-scores");
		return "high-scores";
	}
	
	@GetMapping("/high-scores/getScores")
	public @ResponseBody List<Player> getScores() {
		try {
			return ticTacToe.getAllPlayers();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
}
