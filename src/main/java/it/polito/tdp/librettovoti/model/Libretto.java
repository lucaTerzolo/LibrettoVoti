package it.polito.tdp.librettovoti.model;

import java.util.ArrayList;
import java.util.List;

import it.polito.tdp.librettovoti.db.LibrettoDAO;

public class Libretto {

	private List<Voto> voti;
	
	public Libretto() {
		voti=new ArrayList<Voto>();
	}
	/**
	 * Funzione per aggiungere un voto che fa controllo di conflitto e duplicazione
	 * @param v Voto
	 * @return true se l'operazione ha avuto successo
	 */
	public boolean add(Voto v) {
		LibrettoDAO dao=new LibrettoDAO();
		boolean result=dao.createVoto(v);
		return result;
	}
	/**
	 * Filtra gli esami per il voto
	 * @param punti Punti dell'esame
	 * @return Libretto di voti con la valutazione indicata
	 */
	public Libretto filtraPunti(int punti) {
		Libretto result=new Libretto();
		for(Voto v: this.voti)
			if(v.getPunti()==punti)
				result.add(v);
		return result;
	}
	/**
	 * Restituisce il punteggio ottenuto all'esame di cui specifico il nome
	 * @param nome Nome dell'esame
	 * @return punteggio numerico, oppure null se non esiste
	 */
	public Integer puntiEsame(String nome) { // Integer per inserire come valore di ritorno 
		for(Voto v:this.voti)                // possibile anche null
			if(v.getNome().equals(nome))
				return v.getPunti();
		// return -1;
		return null;
		// throw new IllegalArgumentException("Corso non trovato");
	}

	public boolean isDuplicato(Voto v) {
		for(Voto v1:this.voti)
			if(v1.equals(v))
				return true;
		return false;
	}
	
	public boolean isConflitto (Voto v) {
		Integer punti=this.puntiEsame(v.getNome());
		if(punti!=null && punti!=v.getPunti())
			return true;
		else
			return false;
	}
	
	public List<Voto> getVoti(){
		LibrettoDAO dao=new LibrettoDAO();
		return dao.readAllVoto();
	}
	
	public Libretto votiMigliorati() {
		Libretto nuovo=new Libretto();
		for(Voto v:this.voti) {
			int punti=v.getPunti();
			if(punti>=24)
				punti+=2;
			else
				punti++;
			if(punti>30)
				punti=30;
			
			nuovo.add(new Voto(v.getNome(),punti));
		}
		return nuovo;
	}
	
	public void cancellaVotiMinori(int punti) {
		for(Voto v:this.voti)
			if(v.getPunti()<punti)
				this.voti.remove(v);
	}
	
	@Override
	public String toString() {
		return this.voti.toString();
	}
	
	
}
