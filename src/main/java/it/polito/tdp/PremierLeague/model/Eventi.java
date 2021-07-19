package it.polito.tdp.PremierLeague.model;

public class Eventi {
	
	enum TipoEventi{
		GOAL,
		INFORTUNIO,
		ESPULSIONE
	};
	
	
	private TipoEventi tipo;
	private Team squadra; 





	public Eventi(TipoEventi tipo, Team squadra) {
		super();
		this.tipo = tipo;
		this.squadra = squadra;
	}


	public TipoEventi getTipo() {
		return tipo;
	}


	public void setTipo(TipoEventi tipo) {
		this.tipo = tipo;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((tipo == null) ? 0 : tipo.hashCode());
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
		Eventi other = (Eventi) obj;
		if (tipo != other.tipo)
			return false;
		return true;
	} 
	
	
	
	
	

}

