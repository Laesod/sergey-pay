package poweredby.sergey.pay.app.dal;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import poweredby.sergey.pay.app.entities.TransactionEntity;


public class GetTransactionsClient extends BaseClient {
	private final static String METHOD = "/transactionhistory";
	
    public GetTransactionsClient(String strUsername, String strPassword, String strServer, String strUrl)
    {
        super(strUsername, strPassword, strServer, strUrl + METHOD);
    }
    
    public List<TransactionEntity> getTransactions(
    		String startDate,
    		String endDate,
    		String email,
    		String transactionReference,
    		String currency,
    		float amount,
    		int transactionType,
    		int transactionState)
    {
    	buildPostVariables(
    			startDate,
        		endDate,
        		email,
        		transactionReference,
        		currency,
        		amount,
        		transactionType,
        		transactionState);
    	
    	List<NameValuePair> varibles = super.parseVaribles(super.postData());
    	
    	return parseTransactions(varibles);
    }

    private void buildPostVariables(
    		String startDate,
    		String endDate,
    		String email,
    		String transactionReference,
    		String currency,
    		float amount,
    		int transactionType,
    		int transactionState)
    {
        dataToSend = new ArrayList<NameValuePair>(10);
        super.addIdentityPostVariables(dataToSend);	// user name and password
        dataToSend.add(new BasicNameValuePair("STARTDATE", startDate));
        dataToSend.add(new BasicNameValuePair("ENDDATE", endDate));
        dataToSend.add(new BasicNameValuePair("EMAIL", email));
        dataToSend.add(new BasicNameValuePair("TRANSACTIONREFERENCE", transactionReference));
        dataToSend.add(new BasicNameValuePair("CURRENCY", currency));

        String strAmount = amount == -1 ? "" : Float.toString(amount);
        dataToSend.add(new BasicNameValuePair("AMOUNT", strAmount));
        String strTransactionType = transactionType == -1 ? "" : Integer.toString(transactionType);
        dataToSend.add(new BasicNameValuePair("TRANSACTIONTYPE", strTransactionType));
        String strTransactionState = transactionState == -1 ? "" : Integer.toString(transactionState); 
        dataToSend.add(new BasicNameValuePair("TRANSACTIONSTATE", strTransactionState));
    }
    
    private List<TransactionEntity> parseTransactions(List<NameValuePair> varibles) {
    	if (varibles.size() == 0) {
    		return new ArrayList<TransactionEntity>();
    	}
        
    	List<TransactionEntity> result = new ArrayList<TransactionEntity>((varibles.size() - 2) / 12);

        //RETURNCODE
        //DESCRIPTION
        
        for(int i = 2; i < varibles.size(); i += 12) {
        	TransactionEntity transaction = new TransactionEntity();
        	
        	transaction.setReferenceNumber(varibles.get(i).getValue()); //REFERENCENUMBER_x
        	transaction.setNameEmail(varibles.get(i + 1).getValue()); 	//EMAIL_x
        	transaction.setCurrency(varibles.get(i + 2).getValue());	//CURRENCY_x
        	transaction.setTransactionType(varibles.get(i + 3).getValue());		//TRANSACTIONTYPE_x
        	transaction.setTransactionState(varibles.get(i + 4).getValue());	//TRANSACTIONSTATE_x
        	transaction.setNote(varibles.get(i + 5).getValue());		//NOTE_x
        	transaction.setDate(varibles.get(i + 6).getValue());		//DATE_x
        	transaction.setTime(varibles.get(i + 7).getValue());		//TIME_x
        	transaction.setAmount(varibles.get(i + 8).getValue());		//GROSSAMOUNT_x
        	//FEESAMOUNT_x
        	//NETAMOUNT_x
        	//SUBSCRIPTIONNUMBER_x
            
        	result.add(transaction);
        }

        return result;
    }
}
