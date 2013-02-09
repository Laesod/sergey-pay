package poweredby.sergey.pay.app.dal;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

public class BaseClient {
    private static final String varibleDivider = "&";
    private static final String valueDivider = "=";
    
    protected List<NameValuePair> dataToSend;
    protected String response;

    private String myUserName;
    private String apiPassword;
    private String server;
    private String url;

    public BaseClient(String strUsername, String strPassword, String strServer, String strUrl)
    {
        dataToSend = new ArrayList<NameValuePair>();

        myUserName = strUsername;
        apiPassword = strPassword;
        server = strServer;
        url = strUrl;
    }

    protected void addIdentityPostVariables(List<NameValuePair> data)
    {
        data.add(new BasicNameValuePair("USER", myUserName));
        data.add(new BasicNameValuePair("PASSWORD", apiPassword));
    }
    
    public String postData()
    {
        response = "";

        // Create a new HttpClient and Post Header
        HttpClient httpclient = new DefaultHttpClient();
        HttpPost httppost = new HttpPost(server + url);

        try {
            // Add your data
            httppost.setEntity(new UrlEncodedFormEntity(dataToSend));
            // Execute HTTP Post Request
            HttpResponse httpresponse = httpclient.execute(httppost);
            response = inputStreamToString(httpresponse.getEntity().getContent());
            response = URLDecoder.decode(response);
        } catch (ClientProtocolException e) {
            // TODO Auto-generated catch block
        } catch (IOException e) {
            // TODO Auto-generated catch block
        }

        return response;
    }

    private String inputStreamToString(InputStream is) throws IOException {
        String line = "";
        StringBuilder total = new StringBuilder();
        BufferedReader rd = new BufferedReader(new InputStreamReader(is));
        while ((line = rd.readLine()) != null) {
            total.append(line);
        }
        return total.toString();
    }

    public static List<NameValuePair> parseVaribles(String responce) {
    	if (responce == null) {
    		return new ArrayList<NameValuePair>();
    	}
    	if (responce.equals("")) {
    		return new ArrayList<NameValuePair>();
    	}
    	
        String[] variblePairs = responce.split(varibleDivider);
        List<NameValuePair> result = new ArrayList<NameValuePair>(variblePairs.length);

        for(String pair : variblePairs) {
            String[] values = pair.split(valueDivider);
            result.add(new BasicNameValuePair(values[0], values.length == 1 ? "" : values[1]));
        }

        return result;
    }
}
