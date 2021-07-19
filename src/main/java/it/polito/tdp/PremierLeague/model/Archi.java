package it.polito.tdp.PremierLeague.model;

public class Archi {
	
	private Player giocatore1;
	private Player giocatore2; 
	private Float peso;
	
	public Archi(Player giocatore1, Player giocatore2, Float peso) {
		super();
		this.giocatore1 = giocatore1;
		this.giocatore2 = giocatore2;
		if (peso<0)
			this.peso = peso* (-1);
		else 
			this.peso= peso;
	}

	public Player getGiocatore1() {
		return giocatore1;
	}

	public void setGiocatore1(Player giocatore1) {
		this.giocatore1 = giocatore1;
	}

	public Player getGiocatore2() {
		return giocatore2;
	}

	public void setGiocatore2(Player giocatore2) {
		this.giocatore2 = giocatore2;
	}

	public Float getPeso() {
		return peso;
	}

	public void setPeso(Float peso) {
		this.peso = peso;
	} 
	
	
	
	
	

}
