package it.polito.tdp.borders.model;

import java.util.HashMap;
import java.util.Map;

public class BorderIdMap {
	private Map<Integer,Border> map;

	public BorderIdMap() {
		map = new HashMap<Integer,Border>();
	}
	
	public Border getCountry(int borderId) {
		return map.get(borderId);
	}
	
	public Border get(Border border) {
		Border old = map.get(border.getId());
		if (old == null) {
			map.put(border.getId(), border);
			return border;
		}
		return old;
	}
	
	public void put(Border border, int borderId) {
		map.put(borderId, border);
	}
	
	

}
