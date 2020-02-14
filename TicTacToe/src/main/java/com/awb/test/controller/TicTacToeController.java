package com.awb.test.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.awb.test.Player;
import com.awb.test.Result;
import com.awb.test.Result.WinningSet;
import com.awb.test.TicTacToe;

@Controller
@RequestMapping("/TicTacToe")
public class TicTacToeController {
	
	@Autowired
	private TicTacToe ticTacToe;
	
	@RequestMapping("")
	public String ticTacToe(Model model) {
		model.addAttribute("tictactoe");
		return "tictactoe";
	}
	
	@PostMapping("/save")
	public @ResponseBody void save(@RequestParam String playerName) {
		try {
			Player player = new Player();
			player.setName(playerName);
			player.setScore(1);
			ticTacToe.savePlayer(player);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@PostMapping("/postBoard")
	public @ResponseBody Result postBoard(@RequestParam Map<String, String> data) {
		Result result = new Result();
		if ((!data.get("1").isEmpty() && !data.get("2").isEmpty() && !data.get("3").isEmpty()) && (data.get("1").equals(data.get("2")) && data.get("2").equals(data.get("3")))) {
			result.setWinningSet(WinningSet.S123);
			result.setWinningPlayer(data.get("1"));
		} else if ((!data.get("4").isEmpty() && !data.get("5").isEmpty() && !data.get("6").isEmpty()) && (data.get("4").equals(data.get("5")) && data.get("5").equals(data.get("6")))) {
			result.setWinningSet(WinningSet.S456);
			result.setWinningPlayer(data.get("4"));
		} else if ((!data.get("7").isEmpty() && !data.get("8").isEmpty() && !data.get("9").isEmpty()) && (data.get("7").equals(data.get("8")) && data.get("8").equals(data.get("9")))) {
			result.setWinningSet(WinningSet.S789);
			result.setWinningPlayer(data.get("7"));
		} else if ((!data.get("1").isEmpty() && !data.get("4").isEmpty() && !data.get("7").isEmpty()) && (data.get("1").equals(data.get("4")) && data.get("4").equals(data.get("7")))) {
			result.setWinningSet(WinningSet.S147);
			result.setWinningPlayer(data.get("1"));
		} else if ((!data.get("2").isEmpty() && !data.get("5").isEmpty() && !data.get("8").isEmpty()) && (data.get("2").equals(data.get("5")) && data.get("5").equals(data.get("8")))) {
			result.setWinningSet(WinningSet.S258);
			result.setWinningPlayer(data.get("2"));
		} else if ((!data.get("3").isEmpty() && !data.get("6").isEmpty() && !data.get("9").isEmpty()) && (data.get("3").equals(data.get("6")) && data.get("6").equals(data.get("9")))) {
			result.setWinningSet(WinningSet.S369);
			result.setWinningPlayer(data.get("3"));
		} else if ((!data.get("1").isEmpty() && !data.get("5").isEmpty() && !data.get("9").isEmpty()) && (data.get("1").equals(data.get("5")) && data.get("5").equals(data.get("9")))) {
			result.setWinningSet(WinningSet.S159);
			result.setWinningPlayer(data.get("1"));
		} else if ((!data.get("3").isEmpty() && !data.get("5").isEmpty() && !data.get("7").isEmpty()) && (data.get("3").equals(data.get("5")) && data.get("5").equals(data.get("7")))) {
			result.setWinningSet(WinningSet.S357);
			result.setWinningPlayer(data.get("3"));
		}
		
		result.setTableFull(isBoardFull(data));
		
		return result;
	}
	
	private boolean isBoardFull(Map<String, String> data) {
		if (!data.get("1").isEmpty() && 
				!data.get("2").isEmpty() && 
				!data.get("3").isEmpty() && 
				!data.get("4").isEmpty() && 
				!data.get("5").isEmpty() && 
				!data.get("6").isEmpty() && 
				!data.get("7").isEmpty() && 
				!data.get("8").isEmpty() && 
				!data.get("9").isEmpty()) {
			return true;
		}
		
		return false;
	}
	
}
