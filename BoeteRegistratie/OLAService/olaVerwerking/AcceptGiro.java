package olaVerwerking;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;
import java.awt.Graphics2D;
import java.awt.image.*;
import java.io.*;
import javax.imageio.*;
import java.awt.Font;


public class AcceptGiro {
    private static final int W=500;
    private static final int H=294;
    private static final int LINE1=63;
    private static final int LINE2=100;
    private static final int LINE3=132;
    private static final int LINE4=150;
    private static final int LINE5=168;
    private static final int LINE6=196;
    private static final int LINE7=210;
    private static final int LINE8=272;
    private static final int COLINCREMENT=19;
    private static final int COLSTART=21;
    private static final int COLBKLINE1=186;
    private static final int MAXEUROLENGTH=5;
    private static final int MAXCENTLENGTH=2;
    private static final int CENTCOLOFFSET=6;
    private static final int MAXREKNRLENGTH=10;
    private static final int GENERALOFFSET=70;
    private static final int FONTSIZE1=10;
    private static final int FONTSIZE2=14;

    private String imageDir;
    private String baseDir;

    private int euro;
    private int cent;
    private String kenmerk;
    private String betalingsKenmerk;
    private String rekeningNummer;
    private String naam;
    private String adresPC;
    private String plaats;
    private String rekeningNummerNaar;
    private String naamNaar;

    public AcceptGiro(String imageDir, String baseDir) {
        this.imageDir=imageDir;
        this.baseDir=baseDir;
    }
    public void transformeerBericht(Bericht bericht){
    	// Transformeer hier het bericht naar de OLA gegevens
    }
    
    public void createAcceptGiroPdf() throws Exception {
        Document document = new Document(new Rectangle(W, H));
        PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(baseDir+File.separatorChar+kenmerk+".pdf"));
        document.open();
        PdfContentByte cb = writer.getDirectContent();
        Graphics2D g2 = cb.createGraphics(W, H);
        paint(g2);
        g2.dispose();
        document.close();    
    }
      
    public void createAcceptGiroPdfTester(String kenmerk, int euro, int cent, String betalingsKenmerk,
                                    String rekeningNummer, String naam, String adresPC,
                                    String plaats, String rekeningNummerNaar, String naamNaar
                                   ) throws Exception {
        this.euro=euro;
        this.cent=cent;
        this.kenmerk=kenmerk;
        this.betalingsKenmerk=betalingsKenmerk;
        this.rekeningNummer=rekeningNummer;
        this.naam=naam;
        this.adresPC=adresPC;
        this.plaats=plaats;
        this.rekeningNummerNaar=rekeningNummerNaar;
        this.naamNaar=naamNaar;
        createAcceptGiroPdf();
    }

    public void paint(Graphics2D g2) throws Exception {
        BufferedImage bg=ImageIO.read(new File(imageDir+File.separatorChar+"acceptgiroeuro.jpg"));
        g2.drawImage(bg, null, 0, 0);
        g2.setFont(new Font("Arial", Font.PLAIN, FONTSIZE2));
        printAmountLine1(g2);
        g2.setFont(new Font("Arial", Font.PLAIN, FONTSIZE1));
        printBKLine1(g2);
        g2.setFont(new Font("Arial", Font.PLAIN, FONTSIZE2));
        printRekNrLine2(g2);
        g2.setFont(new Font("Arial", Font.PLAIN, FONTSIZE1));
        printNaamLine3(g2);
        printAdresLine4(g2);
        printPlaatsLine5(g2);
        printRekNrNaarLine6(g2);
        printNaamNaarLine7(g2);
    }

    private void printAmountLine1(Graphics2D g2) {
        String euroS=prependWith(MAXEUROLENGTH, Integer.toString(euro), 'X');
        String centS=prependWith(MAXCENTLENGTH, Integer.toString(cent), '0');
        for(int i=0;i<MAXEUROLENGTH;i++)
            printDigitInPigeonHole(g2, i, euroS.substring(i, i+1), LINE1);
        for(int i=0;i<MAXCENTLENGTH;i++)
            printDigitInPigeonHole(g2, i+CENTCOLOFFSET, centS.substring(i, i+1), LINE1);
    }

    private void printBKLine1(Graphics2D g2) {
        g2.drawString(betalingsKenmerk, COLBKLINE1, LINE1);
    }

    private void printRekNrLine2(Graphics2D g2) {
        String rekNumS=prependWith(MAXREKNRLENGTH, rekeningNummer, ' ');
        for(int i=0;i<MAXREKNRLENGTH;i++)
            printDigitInPigeonHole(g2, i, rekNumS.substring(i, i+1), LINE2);
    }

    private void printNaamLine3(Graphics2D g2) {
        g2.drawString(naam, GENERALOFFSET, LINE3);
    }

    private void printAdresLine4(Graphics2D g2) {
        g2.drawString(adresPC, GENERALOFFSET, LINE4);
    }

    private void printPlaatsLine5(Graphics2D g2) {
        g2.drawString(plaats, GENERALOFFSET, LINE5);
    }

    private void printRekNrNaarLine6(Graphics2D g2) {
        g2.drawString(rekeningNummerNaar, GENERALOFFSET, LINE6);
    }

    private void printNaamNaarLine7(Graphics2D g2) {
        g2.drawString(naamNaar, GENERALOFFSET, LINE7);
    }

    private void printOLALine8(Graphics2D g2) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    private String prependWith(int length, String s, char c) {
        while(s.length()<length) {
            s=c+s;
        }
        return s;
    }

    private void printDigitInPigeonHole(Graphics2D g2, int colnumber, String digit, int y) {
        g2.drawString(digit, COLSTART+colnumber*COLINCREMENT, y);
    }
}
