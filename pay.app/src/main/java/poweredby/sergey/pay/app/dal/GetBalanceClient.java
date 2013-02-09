package poweredby.sergey.pay.app.dal;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import poweredby.sergey.pay.app.entities.BalanceEntity;


public class GetBalanceClient extends BaseClient {
	private final static String METHOD = "/getbalance";
	
    public GetBalanceClient(String strUsername, String strPassword, String strServer, String strUrl)
    {
        super(strUsername, strPassword, strServer, strUrl + METHOD);
    }
    
    public List<BalanceEntity> getBalanses(String strCurrency) {
    	buildPostVariables(strCurrency);
    	List<NameValuePair> varibles = super.parseVaribles(super.postData());
    	
    	return parseBalanses(varibles);
    }

    private void buildPostVariables(String strCurrency)
    {
        dataToSend = new ArrayList<NameValuePair>(3);
        super.addIdentityPostVariables(dataToSend);	// user name and password
        dataToSend.add(new BasicNameValuePair("CURRENCY", strCurrency));
    }
    
    private List<BalanceEntity> parseBalanses(List<NameValuePair> varibles) {
    	if (varibles.size() == 0) {
    		return new ArrayList<BalanceEntity>();
    	}
    	
        List<BalanceEntity> result = new ArrayList<BalanceEntity>(varibles.size() / 2);

        for(int i = 0; i < varibles.size(); i += 2) {
        	BalanceEntity balance = new BalanceEntity();
        	
        	balance.setAmount(varibles.get(i).getValue());
        	balance.setCurrency(varibles.get(i + 1).getValue());
            
        	result.add(balance);
        }

        return result;
    }
}
