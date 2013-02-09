package poweredby.sergey.pay.app;

import poweredby.sergey.pay.app.bll.ApiFacade;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;


public class AccountMenuActivity extends Activity {
    private static final int REQUEST_CODE = 0;

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.account_menu);

        final Button buttonBallance = (Button) findViewById(R.id.buttonBalance);
        buttonBallance.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
            	viewBallance();
            }
        });
        
        final Button buttonSendMoney = (Button) findViewById(R.id.buttonSendMoney);
        buttonSendMoney.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
            	viewSendMoney();
            }
        });

        final Button buttonTransactions = (Button) findViewById(R.id.buttonTransactions);
        buttonTransactions.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
            	viewTransactions();
            }
        });
        
        final Button buttonEncode = (Button) findViewById(R.id.buttonEncode);
        buttonEncode.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
            	viewEncode();
            }
        });
        
        final Button buttonLogout = (Button) findViewById(R.id.buttonLogout);
        buttonLogout.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
            	logout();
            }
        });

        final Button buttonPreferences = (Button) findViewById(R.id.buttonPreferences);
        buttonPreferences.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
            	showPreferences();
            }
        });
    }

	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
	    if (requestCode == REQUEST_CODE) {
	        //SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
	        //ApiFacade.userName = prefs.getString(PreferencesActivity.KEY_LOGIN, "");
	        //ApiFacade.password = prefs.getString(PreferencesActivity.KEY_PASSWORD, "");
	    }
    }   

	private void viewBallance() {
        Intent intent = new Intent().setClass(this, BalanceActivity.class);
        startActivity(intent);
	}

	private void viewSendMoney() {
        Intent intent = new Intent().setClass(this, SendMoneyActivity.class);
        startActivity(intent);
	}

	private void viewTransactions() {
        Intent intent = new Intent().setClass(this, TransactionsActivity.class);
        startActivity(intent);
	}

	private void viewEncode() {
        Intent intent = new Intent().setClass(this, EncodeMoneyActivity.class);
        startActivity(intent);
	}

	private void logout() {
		ApiFacade.logout();
		finish();
	}
	
	private void showPreferences() {
        Intent settingsActivity = new Intent(this, PreferencesActivity.class);
		startActivityForResult(settingsActivity, REQUEST_CODE);
	}
}
