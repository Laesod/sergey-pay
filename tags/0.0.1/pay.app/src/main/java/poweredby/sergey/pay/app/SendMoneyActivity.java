package poweredby.sergey.pay.app;

import java.util.Calendar;
import java.util.List;

import org.apache.http.NameValuePair;

import poweredby.sergey.pay.app.bll.ApiFacade;
import poweredby.sergey.pay.app.bll.CurrencyEnum;
import poweredby.sergey.pay.app.bll.PurchaseTypeEnum;
import poweredby.sergey.pay.app.dal.BaseClient;
import poweredby.sergey.pay.app.entities.SendMoneyResponseEntity;
import poweredby.sergey.pay.app.entities.TransactionEntity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.google.zxing.client.android.CaptureActivity;
import com.google.zxing.client.android.Intents;

public class SendMoneyActivity extends Activity implements Runnable {
    private static final int REQUEST_CODE = 0;
	private ProgressDialog pDialog;
	SendMoneyResponseEntity sendMoneyResponse;
    String amount;
    String reciverEmail;
	String currency;
	String notes;
	int purchaseType;
	String purchaseTypeName;
	
	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);
        setContentView(R.layout.send_money);

        final Button buttonScan = (Button) findViewById(R.id.buttonScan);
        buttonScan.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                scanQrCode();
            }
        });
        
        final Button buttonSend = (Button) findViewById(R.id.buttonSend);
        buttonSend.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                sendMoney();
            }
        });

        final Spinner spinnerCurrency = (Spinner) findViewById(R.id.spinnerCurrency);
        ArrayAdapter<CurrencyEnum> adapterCurrency = new ArrayAdapter<CurrencyEnum>(
                this, android.R.layout.simple_spinner_item, CurrencyEnum.values());
        adapterCurrency.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerCurrency.setAdapter(adapterCurrency);
        
        final Spinner spinnerPurchaseType = (Spinner) findViewById(R.id.spinnerPurchaseType);
        ArrayAdapter<PurchaseTypeEnum> adapterPurchaseType = new ArrayAdapter<PurchaseTypeEnum>(
                this, android.R.layout.simple_spinner_item, PurchaseTypeEnum.values());
        adapterPurchaseType.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerPurchaseType.setAdapter(adapterPurchaseType);
        
        final EditText etReciverEmail = (EditText) findViewById(R.id.editTextReciverEmail);
        etReciverEmail.setText("seller_1_sergeydobryn@gmail.com");
    }
    
	protected void sendMoney() {
        final EditText etAmount = (EditText) findViewById(R.id.editTextAmount);
        amount = etAmount.getText().toString();
        final EditText etReciverEmail = (EditText) findViewById(R.id.editTextReciverEmail);
        reciverEmail = etReciverEmail.getText().toString();
        final EditText etNote = (EditText) findViewById(R.id.editTextNote);
        notes = etNote.getText().toString();
        final Spinner spinnerCurrency = (Spinner) findViewById(R.id.spinnerCurrency);
		currency = ((CurrencyEnum)spinnerCurrency.getSelectedItem()).name();
        final Spinner spinnerPurchaseType = (Spinner) findViewById(R.id.spinnerPurchaseType);
		purchaseType = ((PurchaseTypeEnum)spinnerPurchaseType.getSelectedItem()).getValue();
		purchaseTypeName = ((PurchaseTypeEnum)spinnerPurchaseType.getSelectedItem()).getDescription();
		
		sendLoadData();
	}

	private void showTransactionDetails() {
		TransactionEntity transaction = new TransactionEntity();
		
		final Calendar c = Calendar.getInstance();
        transaction.setDate(String.format("%d-%d-%d",
        		c.get(Calendar.MONTH), 
        		c.get(Calendar.DAY_OF_MONTH), 
        		c.get(Calendar.YEAR)));
        transaction.setReferenceNumber(sendMoneyResponse.getReferenceNumber());
        transaction.setTransactionType("Transfer Sent To");
        transaction.setNameEmail(reciverEmail);
        transaction.setTransactionState("Completed");
        transaction.setAmount(amount);
        transaction.setCurrency(currency);
        transaction.setPurchaseType(purchaseTypeName);
        transaction.setShippingDetails("shippingDetails");
        transaction.setNote(notes);
		
        Intent intent = new Intent().setClass(this, TransactionDetailsActivity.class);
        intent.putExtra(TransactionEntity.NAME, transaction.serializeToStrings());
        startActivity(intent);
	}

	private void sendLoadData() {
		CharSequence pleaseWait = getResources().getText(R.string.lc_please_wait);
		pDialog = ProgressDialog.show(this, "", pleaseWait, true, false);
        Thread thread = new Thread(this);
        thread.start();
	}

	@Override
	public void run() {
		sendMoneyResponse = ApiFacade.sendMoney(
        		amount, 
        		currency, 
        		reciverEmail, 
        		purchaseType, 
        		notes);		
		handler.sendEmptyMessage(0);
	}   

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            pDialog.dismiss();
    		showTransactionDetails();
        }
    };

	private void scanQrCode() {
		Intent intent = new Intent(this, CaptureActivity.class);
		startActivityForResult(intent, REQUEST_CODE);
	}

	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
	    if (requestCode == REQUEST_CODE) {
	        if (resultCode == Activity.RESULT_OK && data != null) {
	        	String contents = data.getStringExtra(Intents.Scan.RESULT);
	        	List<NameValuePair> varibles = BaseClient.parseVaribles(contents);
	        	// AMOUNT=5&RECEIVEREMAIL=seller_1_sergeydobryn@gmail.com&NOTE=Sandwich
	        	for(NameValuePair varible : varibles) {
	        		if (varible.getName().equals("AMOUNT")) {
	        	        final EditText etAmount = (EditText) findViewById(R.id.editTextAmount);
	        	        etAmount.setText(varible.getValue());
	        		} else if (varible.getName().equals("CURRENCY")) {
	        	        //final Spinner spinnerCurrency = (Spinner) findViewById(R.id.spinnerCurrency);
	        			//spinnerCurrency.setSelection(position);
	        		} else if (varible.getName().equals("RECEIVEREMAIL")) {
	        	        final EditText etReciverEmail = (EditText) findViewById(R.id.editTextReciverEmail);
	        	        etReciverEmail.setText(varible.getValue());
	        		} else if (varible.getName().equals("NOTE")) {
	        			final EditText etNote = (EditText) findViewById(R.id.editTextNote);
	    	          	etNote.setText(varible.getValue());
	        		}
	        	}
	        }
	    }
    }
}
