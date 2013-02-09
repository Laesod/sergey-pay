package poweredby.sergey.pay.app;

import poweredby.sergey.pay.app.entities.TransactionEntity;
import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;


public class TransactionDetailsActivity extends Activity {
    /** Called when the activity is first created. */
    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);
        setContentView(R.layout.transaction_details);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
        	TransactionEntity sendMoneyResult = new TransactionEntity();
        	sendMoneyResult.deserializeFromStrings(
        			extras.getStringArray(TransactionEntity.NAME));
        	
            final TextView tvDate = (TextView) findViewById(R.id.textViewDate);
            tvDate.setText(sendMoneyResult.getDate());
            final TextView tvReferenceNumber = (TextView) findViewById(R.id.textViewReferenceNumber);
            tvReferenceNumber.setText(sendMoneyResult.getReferenceNumber());
            final TextView tvTransactionType = (TextView) findViewById(R.id.textViewTransactionType);
            tvTransactionType.setText(sendMoneyResult.getTransactionType());
            final TextView tvNameEmail = (TextView) findViewById(R.id.textViewNameEmail);
            tvNameEmail.setText(sendMoneyResult.getNameEmail());
            final TextView tvCurrentStatus = (TextView) findViewById(R.id.textViewCurrentStatus);
            tvCurrentStatus.setText(sendMoneyResult.getTransactionState());
            final TextView tvAmount = (TextView) findViewById(R.id.textViewAmount);
            tvAmount.setText(sendMoneyResult.getAmount());
            final TextView tvPurchaseType = (TextView) findViewById(R.id.textViewPurchaseType);
            tvPurchaseType.setText(sendMoneyResult.getPurchaseType());
            final TextView tvShippingDetails = (TextView) findViewById(R.id.textViewShippingDetails);
            tvShippingDetails.setText(sendMoneyResult.getShippingDetails());
            final TextView tvDetails = (TextView) findViewById(R.id.textViewDetails);
            tvDetails.setText(sendMoneyResult.getNote());
        }
    }
}
