package it.polito.tdp.borders.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.jgrapht.Graph;
import org.jgrapht.Graphs;
import org.jgrapht.alg.ConnectivityInspector;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleGraph;
import org.jgrapht.traverse.DepthFirstIterator;

import it.polito.tdp.borders.db.BordersDAO;

public class Model {
	
	private List<Border> borders;
	private List<Country> countries;
	private CountryIdMap cIdMap;
	private BorderIdMap bIdMap;
	private BordersDAO bDAO= null;
	Graph<Country,DefaultEdge> graph=null;
	
	public Model() {
		bDAO= new BordersDAO();
		cIdMap= new CountryIdMap();
		bIdMap= new BorderIdMap();
		
		
		countries= new ArrayList<Country>(bDAO.loadAllCountries(cIdMap));
	}
	
	public List<Country> getCountry(){
		return countries;
	}
	public String calcolaConfini(int anno) {
		
		this.creaGraph(anno);
		StringBuilder sb = new StringBuilder(this.printStats());
		sb.append("Elenco degli stati:\n\n");
		for(Country c: countries)
			sb.append("Stato:  "+c.getStateName()+" Grado:  "+graph.degreeOf(c)+"\n");
		
		
		return sb.toString();
	}
	
	private void creaGraph(int anno) {
		borders=new ArrayList<Border>(bDAO.getCountryPairs(anno, bIdMap, cIdMap));
		graph = new SimpleGraph<Country,DefaultEdge>(DefaultEdge.class);
		Graphs.addAllVertices(graph, countries);
		this.addEdge();
		
		return;
		
	}
	
	private void addEdge() {
//		for(Country c : countries)
//			for(Country p:countries)
//				if (!c.equals(p) && c.getcCode() < p.getcCode())
//					if(c.getBorders().contains(p)&&p.getBorders().contains(c))
//						graph.addEdge(c, p);
		for(Border b:borders) {
			if(!b.getState1().equals(b.getState2())&& b.getState1().getcCode()<b.getState2().getcCode())
				graph.addEdge(b.getState1(), b.getState2());
		}

		
		return;
	}
	
	public String printStats() {
		if (graph == null) {
			return "ERRORE: GRAFO NON ANCORA CREATO";
		}
		
		ConnectivityInspector<Country, DefaultEdge> ci = new ConnectivityInspector<Country, DefaultEdge> (graph);
		return "Il numero di componenti connesse è: "+String.valueOf(ci.connectedSets().size())+"\n\n";
	}
	
	public String trovaCompari(Country c,int anno) {
		if (graph == null)
			this.creaGraph(anno);
		Set<Country> cc = new HashSet<Country>();
		DepthFirstIterator<Country, DefaultEdge> dfv = new DepthFirstIterator<>(this.graph,c);
		while (dfv.hasNext())
			cc.add(dfv.next());

		StringBuilder sb = new StringBuilder("Gli elementi connessi a "+c.getStateName()+" sono:\n");
		sb.append(cc);
		return sb.toString();
	}

}
