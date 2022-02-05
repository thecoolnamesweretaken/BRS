package olaVerwerking;

public class Sampler {
    public static void main(String[] args) throws Exception {
    	String pad = "C:\\pdf";
        AcceptGiro ag=new AcceptGiro(pad,pad);
        ag.createAcceptGiroPdfTester("1", 123, 5, "0000 0000 0000 0000", "P765672",
                "Dhr. Rico Pipo", "Scheen 44 9999 HW", "Gramingen",
                "987654", "Flipje Betuwe");
    }
}
