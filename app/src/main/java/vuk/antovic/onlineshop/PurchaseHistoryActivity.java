package vuk.antovic.onlineshop;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

public class PurchaseHistoryActivity extends AppCompatActivity {

    ListView listViewPurchases;
    PurchaseAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_purchase_history);

        listViewPurchases = findViewById(R.id.listViewPurchases);
        adapter = new PurchaseAdapter(this);
        listViewPurchases.setEmptyView(findViewById(R.id.emptyView));
        listViewPurchases.setAdapter(adapter);

        adapter.add(new PurchaseModel(PurchaseModel.State.DELIVERED, 1312, "1.1.2002."));
        adapter.add(new PurchaseModel(PurchaseModel.State.WAITING_FOR_DELIVERY, 1500, "4.3.2022."));
        adapter.add(new PurchaseModel(PurchaseModel.State.DELIVERED, 4000, "3.3.2019."));
        adapter.add(new PurchaseModel(PurchaseModel.State.CANCELLED, 132213, "5.5.2020."));
        adapter.add(new PurchaseModel(PurchaseModel.State.WAITING_FOR_DELIVERY, 123123123, "3.5.2003."));
        adapter.add(new PurchaseModel(PurchaseModel.State.DELIVERED, 231132, "6.6.2002."));
        adapter.add(new PurchaseModel(PurchaseModel.State.CANCELLED, 1231, "1.1.2000."));
        adapter.add(new PurchaseModel(PurchaseModel.State.DELIVERED, 21313, "1.3.1999."));
        adapter.add(new PurchaseModel(PurchaseModel.State.WAITING_FOR_DELIVERY, 1312312, "21.6.2014."));
        adapter.add(new PurchaseModel(PurchaseModel.State.DELIVERED, 213123, "12.12.1985."));
        adapter.add(new PurchaseModel(PurchaseModel.State.DELIVERED, 12312, "6.11.2018."));
        adapter.add(new PurchaseModel(PurchaseModel.State.WAITING_FOR_DELIVERY, 3112312, "5.7.2021."));
        adapter.add(new PurchaseModel(PurchaseModel.State.DELIVERED, 32112, "7.6.2024."));
        adapter.add(new PurchaseModel(PurchaseModel.State.CANCELLED, 231, "13.2.1984."));
        adapter.add(new PurchaseModel(PurchaseModel.State.WAITING_FOR_DELIVERY, 12312, "15.10.2024."));
        adapter.add(new PurchaseModel(PurchaseModel.State.WAITING_FOR_DELIVERY, 12312321, "27.12.2016."));
        adapter.add(new PurchaseModel(PurchaseModel.State.WAITING_FOR_DELIVERY, 777777, "27.12.2016."));
        adapter.add(new PurchaseModel(PurchaseModel.State.WAITING_FOR_DELIVERY, 888888, "27.12.2016."));
        adapter.add(new PurchaseModel(PurchaseModel.State.WAITING_FOR_DELIVERY, 999999, "27.12.2016."));


    }
}