package flitskast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.util.Random;
import org.apache.commons.codec.binary.Base64;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.json.simple.JSONObject;

import persistence.FlitsRegistratie;
import persistence.FlitserPersistence;
import services.GetResponses;

public class PostBoete
{
	private FlitserPersistence flitserPersistence;
	
    public static void main(String args[])
    {
        PostBoete postboete = new PostBoete();
        postboete.doeFlits();
    }
    
    public void doeFlits() {
        String restUrl="http://localhost:8081/BoeteRegistratie";
        String username="myusername";
        String password="mypassword";
        FlitsRegistratie flitser = getFlitsRegistratie();
        String jsonData = getJSONBericht(flitser);
        HttpPost httpPost = createConnectivity(restUrl , username, password);
        executeReq( jsonData, httpPost);
        flitserPersistence = new FlitserPersistence();
        flitserPersistence.createFlitsRegistratie(flitser);
        flitserPersistence.close();    	
    }
    
    HttpPost createConnectivity(String restUrl, String username, String password)
    {
        HttpPost post = new HttpPost(restUrl);
        String auth=new StringBuffer(username).append(":").append(password).toString();
        byte[] encodedAuth = Base64.encodeBase64(auth.getBytes(Charset.forName("US-ASCII")));
        String authHeader = "Basic " + new String(encodedAuth);
        post.setHeader("AUTHORIZATION", authHeader);
        post.setHeader("Content-Type", "application/json");
            post.setHeader("Accept", "application/json");
            post.setHeader("X-Stream" , "true");
        return post;
    }
     
    void executeReq(String jsonData, HttpPost httpPost)
    {
        try{
            executeHttpRequest(jsonData, httpPost);
        }
        catch (UnsupportedEncodingException e){
            System.out.println("error while encoding api url : "+e);
        }
        catch (IOException e){
            System.out.println("ioException occured while sending http request : "+e);
        }
        catch(Exception e){
            System.out.println("exception occured while sending http request : "+e);
        }
        finally{
            httpPost.releaseConnection();
        }
    }
     
    void executeHttpRequest(String jsonData,  HttpPost httpPost)  throws UnsupportedEncodingException, IOException
    {
        HttpResponse response=null;
        String line = "";
        StringBuffer result = new StringBuffer();
        httpPost.setEntity(new StringEntity(jsonData));
        HttpClient client = HttpClientBuilder.create().build();
        response = client.execute(httpPost);
        System.out.println("Post parameters : " + jsonData );
        System.out.println("Response Code : " +response.getStatusLine().getStatusCode());
        BufferedReader reader = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
        while ((line = reader.readLine()) != null){ result.append(line); }
        System.out.println(result.toString());
    }
    
    FlitsRegistratie getFlitsRegistratie() {
    	FlitsRegistratie flitser = new FlitsRegistratie();
    	flitser.randomFlitser();
    	return flitser;
    }
    
    String getJSONBericht(FlitsRegistratie flitser){
    	JSONObject overtreding = new JSONObject();    	
        overtreding.put("Snelheid", ""+flitser.getSnelheid());
        overtreding.put("Max_Toegestaan", ""+flitser.getMaxtoegestaan());
        overtreding.put("Kenteken", flitser.getKenteken());
        overtreding.put("Kenmerk", flitser.getKenmerk());
        return overtreding.toString();
    }
}

