package models;

import play.db.jpa.Model;

public class School extends Model{
	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
