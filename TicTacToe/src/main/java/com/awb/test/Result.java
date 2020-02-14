package com.awb.test;

public class Result {

	public boolean isTableFull;
	public String winningPlayer;
	public WinningSet winningSet;
	
	public boolean isTableFull() {
		return isTableFull;
	}
	
	public void setTableFull(boolean isTableFull) {
		this.isTableFull = isTableFull;
	}
	
	public String getWinningPlayer() {
		return winningPlayer;
	}
	
	public void setWinningPlayer(String winningPlayer) {
		this.winningPlayer = winningPlayer;
	}
	
	public WinningSet getWinningSet() {
		return winningSet;
	}

	public void setWinningSet(WinningSet winningSet) {
		this.winningSet = winningSet;
	}
	
	public enum WinningSet {
		S123, S456, S789, S159, S357, S147, S258, S369
	}
}
