package it.polito.tdp.librettovoti.model;

public class testLibretto {

	public static void main(String[] args) {
		
		Libretto lib=new Libretto();
		
		
		lib.add(new Voto("Analisi 1",30));
		lib.add(new Voto("Fisica 1", 18));
		lib.add(new Voto("Informatica",25));
		lib.add(new Voto("Algebra lineare",25));
		
		System.out.println(lib);
		
		Libretto lib25=lib.filtraPunti(25);
		System.out.println("Stampa voti pari a 25:");
		System.out.println(lib25);
		
		System.out.println("Stampa voti di Informatica:");
		System.out.println(lib.puntiEsame("Informatica"));

		lib.add(new Voto("Algebra lineare",25));
		System.out.println("Voto di Algebra duplicato: "+lib.isDuplicato(new Voto("Algebra lineare",25)));
		
	}

}
