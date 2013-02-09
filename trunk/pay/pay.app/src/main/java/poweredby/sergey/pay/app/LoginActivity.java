package poweredby.sergey.pay.app;

import poweredby.sergey.pay.app.bll.ApiFacade;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.text.method.LinkMovementMethod;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

public class LoginActivity extends Activity {
    private static final int REQUEST_CODE = 0;

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        loadPreferences();

        final Button buttonLogin = (Button) findViewById(R.id.buttonLogin);
        buttonLogin.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
            	login();
            }
        });
        
        // If logged in then don't show login activity
        if (ApiFacade.IsLoggedIn()) {
    		viewAccount();
        }

        // Make the textview clickable.   
        TextView textViewSignup = (TextView) findViewById(R.id.textViewSignup);
        textViewSignup.setMovementMethod(LinkMovementMethod.getInstance());
    }

	private void loadPreferences() {
		SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        Boolean rememberMe = prefs.getBoolean(PreferencesActivity.KEY_REMEMBER_ME, true);
        if (rememberMe) {
            final CheckBox checkBoxRememberMe = (CheckBox) findViewById(R.id.checkBoxRememberMe);
            checkBoxRememberMe.setChecked(true);
            final EditText editTextLogin = (EditText) findViewById(R.id.editTextLogin);
            editTextLogin.setText(prefs.getString(PreferencesActivity.KEY_LOGIN, ""));
            final EditText editTextPassword = (EditText) findViewById(R.id.editTextPassword);
            editTextPassword.setText(prefs.getString(PreferencesActivity.KEY_PASSWORD, ""));
    	}
	}

	private void savePreferences(String login, String password) {
		final CheckBox checkBoxRememberMe = (CheckBox) findViewById(R.id.checkBoxRememberMe);
        Boolean rememberMe = checkBoxRememberMe.isChecked();
        if (!rememberMe) {
        	login = "";
        	password = "";
        }
		SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putBoolean(PreferencesActivity.KEY_REMEMBER_ME, rememberMe);
        editor.putString(PreferencesActivity.KEY_LOGIN, login);
        editor.putString(PreferencesActivity.KEY_PASSWORD, password);
        editor.commit();
	}

	private void login() {
        final EditText editTextLogin = (EditText) findViewById(R.id.editTextLogin);
        String login = editTextLogin.getText().toString();
        final EditText editTextPassword = (EditText) findViewById(R.id.editTextPassword);
        String password = editTextPassword.getText().toString();
        
        ApiFacade.login(login, password);
        savePreferences(login, password);
		viewAccount();
	}

	private void viewAccount() {
		Intent intent = new Intent().setClass(this, AccountMenuActivity.class);
	    startActivityForResult(intent, REQUEST_CODE);
	}

	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
	    if (requestCode == REQUEST_CODE) {
	        // If logged in then don't show login activity
	        if (ApiFacade.IsLoggedIn()) {
	        	finish();
	        }
	    }
	}
}
