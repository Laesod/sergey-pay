package poweredby.sergey.pay.app;

import poweredby.sergey.pay.app.bll.ApiFacade;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;


public class EncodeMoneyActivity extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.encode_money);
        
        final Button buttonEncode = (Button) findViewById(R.id.buttonEncode);
        buttonEncode.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
            	encode();
            }
        });
    }

	private void encode() {
        final EditText etAmount = (EditText) findViewById(R.id.editTextAmount);
        String moneyRequest = String.format(
        		"RECEIVEREMAIL=%s&AMOUNT=%s", 
        		ApiFacade.getUserName(),
	    		etAmount.getText());
		
		Intent intent = new Intent().setClass(this, EncodeActivity.class);
	    intent.putExtra("ENCODE_DATA", moneyRequest);
	    intent.putExtra("ENCODE_TYPE", "TEXT_TYPE");
	    startActivity(intent);
	}
}
