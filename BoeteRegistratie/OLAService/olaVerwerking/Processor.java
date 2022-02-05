package olaVerwerking;

public class Processor {
	private AcceptGiro ac;
	private Bericht bericht;
	private String padImage;
	private String padUit;
	
	public void AcceptGiroProcessor(){
		try {
			// 1. Kijk of er een nieuw bestand is 
			// en lees het bestand in, er is nu een bericht binnengehaald met de gegevens voor een OLA
			bericht = new Bericht(); //vervang dit
			// 2. Valideer het binnengekomen bericht en als dat gelukt is transformeer het bericht
			if (bericht.valideerBericht()) {
				ac = new AcceptGiro(padImage,padUit);
				ac.transformeerBericht(bericht);
				// 3. Maak de pdf aan
				ac.createAcceptGiroPdf();
				// 4. Verwijder het ingekomen bestand.
				// TODO
			}
		}
		catch (Exception e){
			e.printStackTrace();
		}
	}
}
