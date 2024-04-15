package vuk.antovic.onlineshop;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class PurchaseAdapter extends BaseAdapter {
    Context context;
    ArrayList<PurchaseModel> purchases;

    public PurchaseAdapter(Context context) {
        this.context = context;
        purchases = new ArrayList<PurchaseModel>();
    }

    public void add(PurchaseModel item)
    {
        purchases.add(item);
        notifyDataSetChanged();
    }


    @Override
    public int getCount() {
        return purchases.size();
    }

    @Override
    public Object getItem(int i) {
        return purchases.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    private class ViewHolder {
        TextView status;
        TextView price;
        TextView date;
    }


    @SuppressLint("SetTextI18n")
    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        PurchaseAdapter.ViewHolder viewHolder = null;
        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.row_purchase, null);
            viewHolder = new PurchaseAdapter.ViewHolder();
            viewHolder.status = view.findViewById(R.id.textViewStatus);
            viewHolder.price = view.findViewById(R.id.textViewPrice);
            viewHolder.date = view.findViewById(R.id.textViewDate);
            view.setTag(viewHolder);
        } else {
            viewHolder = (PurchaseAdapter.ViewHolder) view.getTag();
        }
        PurchaseModel data = (PurchaseModel) getItem(i);
        switch(data.getStatus())
        {
            case CANCELLED:
                viewHolder.status.setText("CANCELLED");
                break;
            case DELIVERED:
                viewHolder.status.setText("DELIVERED");
                break;
            case WAITING_FOR_DELIVERY:
                viewHolder.status.setText("WAITING_FOR_DELIVERY");
                break;
        }
        viewHolder.price.setText(String.valueOf(data.getPrice()) + " RSD");
        viewHolder.date.setText(data.getDate().toString());
        return view;
    }
}

























