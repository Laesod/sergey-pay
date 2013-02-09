package poweredby.sergey.pay.app;

import java.util.ArrayList;
import java.util.List;

import poweredby.sergey.pay.app.bll.ApiFacade;
import poweredby.sergey.pay.app.entities.BalanceEntity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.widget.ArrayAdapter;
import android.widget.ListView;



public class BalanceActivity extends Activity implements Runnable {
	private ProgressDialog pDialog;
	List<BalanceEntity> balances;
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.balance);
        
        downLoadData();
    }

	private void downLoadData() {
		CharSequence pleaseWait = getResources().getText(R.string.lc_please_wait);
		pDialog = ProgressDialog.show(this, "", pleaseWait, true, false);
        Thread thread = new Thread(this);
        thread.start();
	}

	@Override
	public void run() {
        balances = ApiFacade.getAllBalances();
		handler.sendEmptyMessage(0);
	}

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            pDialog.dismiss();
            showBallance();
        }
    };

    private void showBallance() {
        final ListView listView = (ListView) findViewById(R.id.listViewBalances);

        List<CharSequence> listViewItems = new ArrayList<CharSequence>(balances.size());
        for (BalanceEntity balance : balances) {
        	listViewItems.add(String.format("%s %s",
        			balance.getAmount(), 
            		balance.getCurrency()));
        }

        listView.setAdapter(new ArrayAdapter<String>(this, R.layout.list_item, listViewItems.toArray(new String[listViewItems.size()])));        
    }
}
