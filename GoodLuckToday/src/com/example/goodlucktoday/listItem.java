package com.example.goodlucktoday;

public class listItem {
	 
	private String name;
	private int imageId;
	private int score;
 
	public listItem(String name, int score) {
		this.name = name;
		this.score = score;
	}
	
 
	public void setImageId(int imageId) {
		this.imageId = imageId;
	}
 
	public String getName() {
		return name;
	}
 
	public void setName(String name) {
		this.name = name;
	}
 
	public int getScore() {
		return score;
	}
 
	public void setScore(int score) {
		this.score = score;
	}
 
	@Override
	public String toString() {
		return this.name + " : " + this.score;
	}
 
}
