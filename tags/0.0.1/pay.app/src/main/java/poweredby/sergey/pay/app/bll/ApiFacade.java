package poweredby.sergey.pay.app.bll;

import java.util.List;

import poweredby.sergey.pay.app.dal.GetBalanceClient;
import poweredby.sergey.pay.app.dal.GetTransactionsClient;
import poweredby.sergey.pay.app.dal.SendMoneyClient;
import poweredby.sergey.pay.app.entities.BalanceEntity;
import poweredby.sergey.pay.app.entities.SendMoneyResponseEntity;
import poweredby.sergey.pay.app.entities.TransactionEntity;


public class ApiFacade {
	private static String userName = "";//"client_1_sergeydobryn@gmail.com";
    private static String password = "";//"DWvjJ26ucZkF51Mt";
    private static String appSessionKey = "";
    //private static final String apiKey = "11AA6F1A-3F00-4F92-B880-567C1998B333"; 
    private static final String server = "https://sandbox.alertpay.com";
    private static final String serviceUrl = "/api/api.svc";

    public static String getUserName() {
    	return userName;
    }
    
    public static Boolean IsLoggedIn() {
    	return appSessionKey != "";
    }
    
    public static void logout() {
        userName = "";
        password = "";
        appSessionKey = "";
    }
    
    public static void login(String strUsername, String strPassword) {
    	//AuthenticationClient objAuthenticationClient = 
        //    	new AuthenticationClient(server, serviceUrl);
            
    	//appSessionKey = objAuthenticationClient.getAppSessionKey(apiKey, strUsername, strPassword);
    	userName = strUsername;
        password = strPassword;
        appSessionKey = "qwe"; 
    }
    
    public static List<BalanceEntity> getAllBalances()
    {
        GetBalanceClient objGetBalanceClient = 
        	new GetBalanceClient(userName, password, server, serviceUrl);
        
        return objGetBalanceClient.getBalanses("");
    }
    
    public static SendMoneyResponseEntity sendMoney(    		
    		String amountPaid, 
    		String currency, 
    		String receiverEmail, 
            int purchaseType, 
            String note)
	{
    	SendMoneyClient objSendMoneyClient = 
            new SendMoneyClient(userName, password, server, serviceUrl);
            
        return objSendMoneyClient.sendMoney(
        		amountPaid, 
        		currency, 
        		receiverEmail, 
        		"", 							// sender Email optional
        		purchaseType, 
        		note, 
        		TestModeEnum.OFF.getValue());	// not test mode
    }
    
    public static List<TransactionEntity> getRecentTransactions() {
    	return getTransactions("2011/04/08", "", "", "", "",  -1, -1, -1);
    }
    
    public static List<TransactionEntity> getTransactions(
    		String startDate,
    		String endDate,
    		String email,
    		String transactionReference,
    		String currency,
    		float amount,
    		int transactionType,
    		int transactionState)
    {
    	GetTransactionsClient objGetTransactionsClient = 
            new GetTransactionsClient(userName, password, server, serviceUrl);
    	
    	return objGetTransactionsClient.getTransactions(
    			startDate,
        		endDate,
        		email,
        		transactionReference,
        		currency,
        		amount,
        		transactionType,
        		transactionState);
    }
}
