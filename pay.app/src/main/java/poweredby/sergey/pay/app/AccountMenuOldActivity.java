package poweredby.sergey.pay.app;

import android.app.TabActivity;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.widget.TabHost;

public class AccountMenuOldActivity extends TabActivity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.account_menu);
        
        Resources res = getResources(); // Resource object to get Drawables
        TabHost tabHost = getTabHost();  // The activity TabHost
        TabHost.TabSpec spec;  // Resusable TabSpec for each tab
        Intent intent;  // Reusable Intent for each tab

        // Create an Intent to launch an Activity for the tab (to be reused)
        intent = new Intent().setClass(this, BalanceActivity.class);

        // Initialize a TabSpec for each tab and add it to the TabHost
        spec = tabHost.newTabSpec("ballance").setIndicator(
        					res.getText(R.string.lc_balance),
        					res.getDrawable(R.drawable.ic_tab_ballance))
                      .setContent(intent);
        tabHost.addTab(spec);

        // Do the same for the other tabs
        intent = new Intent().setClass(this, SendMoneyActivity.class);
        spec = tabHost.newTabSpec("sendmoney").setIndicator(
        					res.getText(R.string.lc_send_money),
        					res.getDrawable(R.drawable.ic_tab_send_money))
                      .setContent(intent);
        tabHost.addTab(spec);
        
        intent = new Intent().setClass(this, TransactionsActivity.class);
        spec = tabHost.newTabSpec("transactions").setIndicator(
        					res.getText(R.string.lc_transactions),
        					res.getDrawable(R.drawable.ic_tab_transactions))
                      .setContent(intent);
        tabHost.addTab(spec);
        }
}
