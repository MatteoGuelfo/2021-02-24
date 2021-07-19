package it.polito.tdp.PremierLeague.model;

import java.util.LinkedList;
import java.util.List;

import org.jgrapht.util.DoublyLinkedList;

import it.polito.tdp.PremierLeague.model.Eventi.TipoEventi;

public class Simulatore {
	
	
	private int numeroAzioni; 
	
	
	private double PROBABILITA_GOAL = 0.5;
	private double PROBABILITA_ESPULSIONE = 0.3;
	private double PROBABILITA_ESPULSIONE_inBest = 0.6;
	private double PROBABILITA_ESPULSIONE_notBest = 0.4;
	private double PROBABILITA_INRFORTUNIO = 20;
	private int numeroGiocatoriPartenza = 11; 
	
	private int numeroGoal1;
	private int numeroGoal2;
	private int numeroInfortuni; 
	private int numeroEspulsioni;
	
	private int numeroGiocatori1; 
	private int numeroGiocatori2; 
	
	private Team squadraConBest;  // squadra 1 
	private Team squadraNoBest;   // squadra 2 
	
	private int azioniGiocate; 
	
	private List<Eventi> listaEventi; 
	
	public Simulatore(int numeroAzioni, Team squadraBest, Team squadraNoBest) {
		super();
		this.numeroAzioni = numeroAzioni;
		this.numeroGiocatori1 = numeroGiocatoriPartenza;
		this.numeroGiocatori2 = numeroGiocatoriPartenza; 
		this.numeroInfortuni= 0;
		this.numeroGoal1 = 0;
		this.numeroGoal2 = 0;
		this.azioniGiocate = 0; 
		this.codSquadraConBest = codSquadraBest;
		this.listaEventi = new LinkedList<>(); 
	
	}
	
	
	public void  generaEventi() {
		for(int i=0; i<numeroAzioni; i++) {
			double random = Math.random(); 
			//
			if(random<= PROBABILITA_GOAL) {
				if(numeroGiocatori1<numeroGiocatori2) {
					listaEventi.add(new Eventi(TipoEventi.GOAL, squadraNoBest)); 
				}else {
					listaEventi.add(new Eventi(TipoEventi.GOAL, squadraConBest));
				}	
				//ESPULSIONI
			}else if(random>PROBABILITA_GOAL && random<PROBABILITA_GOAL+PROBABILITA_ESPULSIONE){
					  double randomEsp = Math.random(); 
				
					  if(randomEsp<PROBABILITA_ESPULSIONE_inBest)
						 listaEventi.add(new Eventi(TipoEventi.ESPULSIONE, squadraConBest));
				      else 
						 listaEventi.add(new Eventi(TipoEventi.ESPULSIONE, squadraConBest));
					//INFORTUNI  
				   }else if(random>PROBABILITA_GOAL+PROBABILITA_ESPULSIONE) {
					   			double randomInf = Math.random(); 
					   			if(randomInf<0.5) {
					   				listaEventi.add(new Eventi(TipoEventi.INFORTUNIO, squadraConBest));
					   			}else {
					   				listaEventi.add(new Eventi(TipoEventi.INFORTUNIO, squadraNoBest));
					   			}
					   			
					   			double randomPiuRec = Math.random(); 
					   			if(randomPiuRec <0.5)
					   				i+=2;
					   			else 
					   				i+=3;
					   				
					   			}
		}
		
	}
	
}
