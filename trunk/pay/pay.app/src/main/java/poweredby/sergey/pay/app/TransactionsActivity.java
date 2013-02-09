package poweredby.sergey.pay.app;

import java.util.ArrayList;
import java.util.List;

import poweredby.sergey.pay.app.bll.ApiFacade;
import poweredby.sergey.pay.app.entities.TransactionEntity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;


public class TransactionsActivity extends Activity implements Runnable {
	private ProgressDialog pDialog;
	private List<TransactionEntity> transactions;
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);
        setContentView(R.layout.transactions);
        
        final ListView listView = (ListView) findViewById(R.id.listViewTransactions);
        listView.setOnItemClickListener(new OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view,
            						int position, long id) {
            	showTransactionDetails(transactions.get(position));
            }
        });

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
        transactions = ApiFacade.getRecentTransactions();
		handler.sendEmptyMessage(0);
	}

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            pDialog.dismiss();
            showTransactions();
        }
    };
    
    private void showTransactions() {
        final ListView listView = (ListView) findViewById(R.id.listViewTransactions);
        
        List<CharSequence> listViewItems = new ArrayList<CharSequence>(transactions.size());
        for (TransactionEntity transaction : transactions) {
        	listViewItems.add(String.format("%s %s %s %s %s",
            		transaction.getAmount(),
            		transaction.getCurrency(),
            		transaction.getDate(),
            		transaction.getTransactionType(),
            		transaction.getNameEmail()).replace("<br/>", "\n"));
        }

        listView.setAdapter(new ArrayAdapter<String>(this, R.layout.list_item, listViewItems.toArray(new String[listViewItems.size()])));        
    }
    
    private void showTransactionDetails(TransactionEntity transaction) {
        Intent intent = new Intent().setClass(this, TransactionDetailsActivity.class);
        intent.putExtra(TransactionEntity.NAME, transaction.serializeToStrings());
        startActivity(intent);
    }
}
