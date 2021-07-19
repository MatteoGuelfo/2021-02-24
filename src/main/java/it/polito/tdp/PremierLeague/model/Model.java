package it.polito.tdp.PremierLeague.model;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.jgrapht.Graph;
import org.jgrapht.Graphs;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleDirectedWeightedGraph;

import it.polito.tdp.PremierLeague.db.PremierLeagueDAO;

public class Model {

	List<Match> match;
	PremierLeagueDAO dao;
	private Graph<Player,  DefaultWeightedEdge> grafo;
	Map<Integer, Player>  giocatori;
	Map<Integer, Team> teamMap;

	public Model() {
		this.match = new LinkedList<>();
		dao = new PremierLeagueDAO(); 
	}
	
	public List<Match> getAllMatch(){
		return dao.listAllMatches();
	}
	
	public void creaGrafo(Match m) {
		giocatori = new HashMap<>(dao.getPlayerMatch(m));
		teamMap = new HashMap<>();
		grafo = new SimpleDirectedWeightedGraph<>(DefaultWeightedEdge.class);
		List<Archi> archi = new LinkedList<>(dao.listAllActionsMatch(m,giocatori));
		System.out.print(giocatori.size());
		
		Graphs.addAllVertices(grafo, giocatori.values());
		for(Archi a: archi) {
			if(a.getGiocatore1().getEfficenza()>a.getGiocatore2().getEfficenza())
				Graphs.addEdgeWithVertices(grafo, a.getGiocatore1(), a.getGiocatore2(), a.getPeso());
			else
				Graphs.addEdgeWithVertices(grafo, a.getGiocatore2(), a.getGiocatore1(), a.getPeso());
		}
	}
	
	public Player bestPlayer() {
		if(grafo == null) {
			return null;
		}
		
		Player best = null;
		Double maxDelta = (double) Integer.MIN_VALUE;
		
		for(Player p : this.grafo.vertexSet()) {
			// calcolo la somma dei pesi degli archi uscenti
			double pesoUscente = 0.0;
			for(DefaultWeightedEdge edge : this.grafo.outgoingEdgesOf(p)) {
				pesoUscente += this.grafo.getEdgeWeight(edge);
			}
			
			// calcolo la somma dei pesi degli archi entranti
			double pesoEntrante = 0.0;
			for(DefaultWeightedEdge edge : this.grafo.incomingEdgesOf(p)) {
				pesoEntrante += this.grafo.getEdgeWeight(edge);
			}
			
			double delta = pesoUscente - pesoEntrante;
			p.setDeltaEfficenza(delta);
			if(delta > maxDelta) {
				best = p;
				maxDelta = delta;
			}
		}
		
		return best ;
		
		
	}
	
	public Team getBestPlayerTeam(Match match) {
		return teamMap.get(dao.getTeamPlayerMatch(match, bestPlayer()));
	}
	
	public void loadTeamMap() {
		List<Team> team = new LinkedList<>(dao.listAllTeams()); 
		for(Team t: team)
			teamMap.put(t.getTeamID(), t);
	}
	
	public Team getMatchTeam(Match match) {
		List<Team> ritorno = new LinkedList<>(); 
		ritorno.add(teamMap.get(match.teamAwayID)); 
		ritorno.add(teamMap.get(match.teamHomeID));
		
		if(ritorno.get(0).getTeamID() ==getBestPlayerTeam(match).getTeamID())
			return ritorno.get(1);
		return ritorno.get(0);
	}
	


	public String proprietaGrafo() {
		return "Numero vertici: "+Integer.toString(grafo.vertexSet().size())+ "\nNumero archi: "+Integer.toString(grafo.edgeSet().size());
	}
	
	
	
}
