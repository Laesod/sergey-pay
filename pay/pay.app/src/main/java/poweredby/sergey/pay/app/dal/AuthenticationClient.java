package poweredby.sergey.pay.app.dal;

import java.util.ArrayList;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

public class AuthenticationClient extends BaseClient {
	private final static String METHOD = "/Authentication";

	public AuthenticationClient(String strServer, String strUrl) {
        super("", "", strServer, strUrl + METHOD);
	}
	
    public String getAppSessionKey(String strApiKey, String strUsername, String strPassword) {
    	buildPostVariables(strApiKey, strUsername, strPassword);
    	return super.postData();
    }

    private void buildPostVariables(String strApiKey, String strUsername, String strPassword)
    {
        dataToSend = new ArrayList<NameValuePair>(5);
        dataToSend.add(new BasicNameValuePair("APIKEY", strApiKey));
        dataToSend.add(new BasicNameValuePair("USER", "seller_1_sergeydobryn@gmail.com"));
        dataToSend.add(new BasicNameValuePair("PASSWORD", "JxoyaU7zfeU0xB8W"));
        dataToSend.add(new BasicNameValuePair("CLIENTEMAIL", strUsername));
        dataToSend.add(new BasicNameValuePair("CLIENTPASSWORD", strPassword));
    }
}
