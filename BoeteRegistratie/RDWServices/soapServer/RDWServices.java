package soapServer;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

import persistence.Info;
import persistence.Kenteken;
import services.*;

@WebService
public class RDWServices {
	@WebMethod
	public Info getKentekenInfo(@WebParam(name = "kenteken")Kenteken kenteken) {
		GetResponses gir=new GetResponses();
		return gir.getInfoReturn(kenteken);
	}
	@WebMethod
	public String getRandomKenteken() {
		GetResponses gir=new GetResponses();
		return gir.getRandomKenteken();
	}
}