package poweredby.sergey.pay.app.dal;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import poweredby.sergey.pay.app.entities.SendMoneyResponseEntity;



public class SendMoneyClient extends BaseClient {
	private final static String METHOD = "/sendmoney";
	
    public SendMoneyClient(String strUsername, String strPassword, String strServer, String strUrl)
    {
        super(strUsername, strPassword, strServer, strUrl + METHOD);
    }

    public SendMoneyResponseEntity sendMoney(
    		String amountPaid, 
    		String currency, 
    		String receiverEmail, 
    		String senderEmail,
            int purchaseType, 
            String note, 
            int testMode)
    {
    	buildPostVariables(    		
    			amountPaid, 
        		currency, 
        		receiverEmail, 
        		senderEmail,
                purchaseType, 
                note, 
                testMode);
    	
    	return parceResponce(super.parseVaribles(super.postData()));
    }

    private void buildPostVariables(
    		String amountPaid, 
    		String currency, 
    		String receiverEmail, 
    		String senderEmail,
            int purchaseType, 
            String note, 
            int testMode) 
    {
        dataToSend = new ArrayList<NameValuePair>(9);
        super.addIdentityPostVariables(dataToSend);	// user name and password
        dataToSend.add(new BasicNameValuePair("AMOUNT", amountPaid));
        dataToSend.add(new BasicNameValuePair("CURRENCY", currency));
        dataToSend.add(new BasicNameValuePair("RECEIVEREMAIL", receiverEmail));
        dataToSend.add(new BasicNameValuePair("SENDEREMAIL", senderEmail));
        dataToSend.add(new BasicNameValuePair("PURCHASETYPE", Integer.toString(purchaseType)));
        dataToSend.add(new BasicNameValuePair("NOTE", note));
        dataToSend.add(new BasicNameValuePair("TESTMODE", Integer.toString(testMode)));
    }
    
    private SendMoneyResponseEntity parceResponce(List<NameValuePair> varibles) {
    	SendMoneyResponseEntity sendMoneyResponse = new SendMoneyResponseEntity();
    	
    	for (NameValuePair pair: varibles) {
    		if (pair.getName().equals("RETURNCODE")) {
    			sendMoneyResponse.setRetyrnCode(pair.getValue());
    		}
    		else if (pair.getName().equals("REFERENCENUMBER")) {
    			sendMoneyResponse.setReferenceNumber(pair.getValue());
			}
    		else if (pair.getName().equals("DESCRIPTION")) {
    			sendMoneyResponse.setDescription(pair.getValue());
			}
    		else if (pair.getName().equals("TESTMODE")) {
    			sendMoneyResponse.setTestMode(pair.getValue());
			}
    	}
    	
    	return sendMoneyResponse;
    }
}
