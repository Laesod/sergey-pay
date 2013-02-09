package poweredby.sergey.pay.app;

import android.os.Bundle;
import android.preference.PreferenceActivity;

public class PreferencesActivity extends PreferenceActivity {
	  public static final String KEY_REMEMBER_ME = "preferences_remember_me";
	  public static final String KEY_LOGIN = "preferences_login";
	  public static final String KEY_PASSWORD = "preferences_password";

	  @Override
	  protected void onCreate(Bundle icicle) {
	    super.onCreate(icicle);
	    addPreferencesFromResource(R.xml.preferences);
	  }
}
