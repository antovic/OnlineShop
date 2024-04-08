package vuk.antovic.onlineshop;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class ItemAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<ItemModel> list;


    public ItemAdapter(Context context) {
        this.context = context;
        list = new ArrayList<>();
    }

    @Override
    public int getCount() {
        return list.size();
    }

    public void add(ItemModel item)
    {
        list.add(item);
        notifyDataSetChanged();
    }

    public void clear()
    {
        list.clear();
        notifyDataSetChanged();
    }


    @Override
    public Object getItem(int position) {
        ItemModel item = null;
        try {
            item = list.get(position);
        } catch (IndexOutOfBoundsException e) {
            e.printStackTrace();
        }
        return item;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    private class ViewHolder{
        ImageView imageView;
        TextView nameTextView;
        TextView costTextView;
        Button button;
    }

    @SuppressLint("SetTextI18n")
    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder = null;
        if(view == null)
        {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.row_item, null);
            viewHolder = new ViewHolder();
            viewHolder.imageView = view.findViewById(R.id.imageViewItemImage);
            viewHolder.nameTextView = view.findViewById(R.id.textViewItemName);
            viewHolder.costTextView = view.findViewById(R.id.textViewItemCost);
            viewHolder.button = view.findViewById(R.id.buttonItemAdd);
            view.setTag(viewHolder);
        }
        else
        {
            viewHolder = (ViewHolder) view.getTag();
        }
        ItemModel data = (ItemModel) getItem(i);
        viewHolder.imageView.setImageDrawable(data.getImage());
        viewHolder.nameTextView.setText(data.getName());
        String rsd = context.getString(R.string.rsd);
        viewHolder.costTextView.setText(String.valueOf(data.getCost()) + rsd);
        viewHolder.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Todo Button logic
            }
        });
        return view;
    }
}
