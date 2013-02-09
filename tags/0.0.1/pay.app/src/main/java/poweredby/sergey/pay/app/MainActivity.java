package poweredby.sergey.pay.app;

import java.util.Locale;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.text.method.LinkMovementMethod;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;


public class MainActivity extends Activity {
	Boolean shouldExit = false;
	String[] languagesArray;


	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
    	languagesArray = getResources().getStringArray(R.array.supported_country_code_array);
        
        final Button buttonAccount = (Button) findViewById(R.id.buttonAccount);
        buttonAccount.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
            	viewLogin();
            }
        });

        final Button buttonLanguage = (Button) findViewById(R.id.buttonLanguage);
        buttonLanguage.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
            	setLanguage();
            }
        });

        final Button buttonAbout = (Button) findViewById(R.id.buttonAbout);
        buttonAbout.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
            	viewAbout();
            }
        });
        
        // Make the textview clickable.   
        TextView textViewAlertPayCom = (TextView) findViewById(R.id.textViewSite);
        textViewAlertPayCom.setMovementMethod(LinkMovementMethod.getInstance());
    }

	private void viewLogin() {
		Intent intent = new Intent().setClass(this, LoginActivity.class);
	    startActivity(intent);
	}

	private void setLanguage() {
		Dialog dialog = new Dialog(this);
		AlertDialog.Builder builder = new AlertDialog.Builder(this);

		ListView modeList = new ListView(this);
		ArrayAdapter<String> modeAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, android.R.id.text1, languagesArray);
		modeList.setAdapter(modeAdapter);

		modeList.setOnItemClickListener(new OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view,
            						int position, long id) {
            	changeLocale(languagesArray[position]);
            }
        });
		
		builder.setView(modeList);
		dialog = builder.create();
		dialog.show();
	}

	private void changeLocale(String language) {
		Locale myLocale = new Locale(language); 
		Locale.setDefault(myLocale);

		Configuration config2 = new Configuration(); 
		config2.locale = myLocale;
		Resources res = getBaseContext().getResources();
		res.updateConfiguration(config2, res.getDisplayMetrics());
		
        refresh();
	}

	private void refresh() {
	    finish();
	    Intent myIntent = new Intent(MainActivity.this, MainActivity.class);
	    startActivity(myIntent);
	}

	private void viewAbout() {
		Intent intent = new Intent().setClass(this, AboutActivity.class);
	    startActivity(intent);
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
	    if (keyCode == KeyEvent.KEYCODE_BACK) {
			shouldExit = true;
	      	finish();
	      	return true;
	    }
	    return super.onKeyDown(keyCode, event);
	}

	public void onDestroy() {
	    super.onDestroy();

	    if (shouldExit) {
		    System.runFinalizersOnExit(true);
		    System.exit(0);
	    }
	}
}
