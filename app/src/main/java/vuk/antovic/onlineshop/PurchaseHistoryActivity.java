package vuk.antovic.onlineshop;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

public class PurchaseHistoryActivity extends AppCompatActivity {

    ListView listViewPurchases;
    PurchaseAdapter adapter;
    DbHelper dbHelper;
    String DB = "OnlineShop.db";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_purchase_history);

        listViewPurchases = findViewById(R.id.listViewPurchases);
        adapter = new PurchaseAdapter(this);
        listViewPurchases.setEmptyView(findViewById(R.id.emptyView));
        listViewPurchases.setAdapter(adapter);

        String username = getIntent().getExtras().getString("username", "username");

        dbHelper = new DbHelper(this, DB, null, 1);
        PurchaseModel[] purchases = dbHelper.getPurchases(username);
        if(purchases != null)
        {
            for(PurchaseModel purchase: purchases)
            {
                adapter.add(purchase);
            }
        }



    }
}