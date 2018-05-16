package it.polito.tdp.borders.model;

import java.util.ArrayList;
import java.util.List;

public class Country {
	private int cCode;
	private String stateAbb;
	private String stateName;
	private List<Country> borders;
	
	public Country(int cCode, String stateAbb, String stateName) {
		borders= new ArrayList<Country>();
		this.cCode = cCode;
		this.stateAbb = stateAbb;
		this.stateName = stateName;
	}
	
	

	public List<Country> getBorders() {
		return borders;
	}

	public void setBorders(List<Country> borders) {
		this.borders = borders;
	}



	public int getcCode() {
		return cCode;
	}

	public void setcCode(int cCode) {
		this.cCode = cCode;
	}

	public String getStateAbb() {
		return stateAbb;
	}

	public void setStateAbb(String stateAbb) {
		this.stateAbb = stateAbb;
	}

	public String getStateName() {
		return stateName;
	}

	public void setStateName(String stateName) {
		this.stateName = stateName;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + cCode;
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
		Country other = (Country) obj;
		if (cCode != other.cCode)
			return false;
		return true;
	}
	
	public String toString() {
		return this.getStateName();
	}
	

}
