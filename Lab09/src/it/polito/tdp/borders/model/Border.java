package it.polito.tdp.borders.model;

public class Border {
	private Country state1;
	private Country state2;
	private int year;
	private int id;
	
	public Border(int id,Country state1, Country state2, int year) {
		this.id=id;
		this.state1 = state1;
		this.state2 = state2;
		this.year = year;
	}
	
	


	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Country getState1() {
		return state1;
	}
	public void setState1(Country state1) {
		this.state1 = state1;
	}
	public Country getState2() {
		return state2;
	}
	public void setState2(Country state2) {
		this.state2 = state2;
	}
	public int getYear() {
		return year;
	}
	public void setYear(int year) {
		this.year = year;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Border other = (Border) obj;
		if (id != other.id)
			return false;
		return true;
	}
	
	
	
	
	

}
