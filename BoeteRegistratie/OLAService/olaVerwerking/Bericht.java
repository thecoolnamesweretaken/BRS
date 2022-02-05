package olaVerwerking;

public class Bericht {

    private String kenmerk;
    private Float bedrag;
    private String betalingskenmerk;
    private String ibannummer;
    private String geslacht;
    private String initialen;
    private String achternaam;
    private String straatnaam;
    private Integer straatnummer;
    private String plaatsnaam;
    private String postcode;
    private String naamnaar;
    private String ibannummernaar;

    public boolean valideerBericht(){
    	// TODO
    	return true;
    }
    
    public String getKenmerk() {
        return kenmerk;
    }

    public void setKenmerk(String value) {
        this.kenmerk = value;
    }

    public Float getBedrag() {
        return bedrag;
    }
    
    
    public void setBedrag(Float value) {
        this.bedrag = value;
    }

    public String getBetalingskenmerk() {
        return betalingskenmerk;
    }

    public void setBetalingskenmerk(String value) {
        this.betalingskenmerk = value;
    }

    public String getIbannummer() {
        return ibannummer;
    }

    public void setIbannummer(String value) {
        this.ibannummer = value;
    }

    public String getGeslacht() {
        return geslacht;
    }

    public void setGeslacht(String value) {
        this.geslacht = value;
    }

    public String getInitialen() {
        return initialen;
    }

    public void setInitialen(String value) {
        this.initialen = value;
    }

    public String getAchternaam() {
        return achternaam;
    }

    public void setAchternaam(String value) {
        this.achternaam = value;
    }

    public String getStraatnaam() {
        return straatnaam;
    }

    public void setStraatnaam(String value) {
        this.straatnaam = value;
    }

    public Integer getStraatnummer() {
        return straatnummer;
    }

    public void setStraatnummer(Integer value) {
        this.straatnummer = value;
    }

    public String getPlaatsnaam() {
        return plaatsnaam;
    }

    public void setPlaatsnaam(String value) {
        this.plaatsnaam = value;
    }

    public String getPostcode() {
        return postcode;
    }

    public void setPostcode(String value) {
        this.postcode = value;
    }

    public String getNaamnaar() {
        return naamnaar;
    }

    public void setNaamnaar(String value) {
        this.naamnaar = value;
    }
    public String getIbannummernaar() {
        return ibannummernaar;
    }

    public void setIbannummernaar(String value) {
        this.ibannummernaar = value;
    }

}
