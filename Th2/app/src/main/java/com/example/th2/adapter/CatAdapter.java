package com.example.th2.adapter;

import android.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.th2.MainActivity;
import com.example.th2.R;
import com.example.th2.model.Cat;

import java.util.ArrayList;
import java.util.List;

public class CatAdapter extends RecyclerView.Adapter<CatAdapter.CatViewHolder> {

    private List<Cat> mList;
    private MainActivity mainActivity;
    private CatItemListener catItemListener;

    public CatAdapter(MainActivity mainActivity) {
        this.mainActivity = mainActivity;
        mList = new ArrayList<>();
    }

    public void setCatItemListener(CatItemListener catItemListener) {
        this.catItemListener = catItemListener;
    }

    public Cat getItem (int i) {
        return mList.get(i);
    }

    public List<Cat> getmList() {
        return mList;
    }

    @NonNull
    @Override
    public CatViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item,parent, false);
        return new CatViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CatViewHolder holder, int position) {
        Cat cat = mList.get(position);
        holder.img.setImageResource(cat.getImg());
        holder.name.setText(cat.getName());
        holder.price.setText(cat.getPrice()+"");
        holder.info.setText(cat.getInfo());
        holder.btnRemove.setOnClickListener(view -> {
            AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
            builder.setTitle("Ban co chac chan xoa "+cat.getName()+" hay khong?");
            builder.setIcon(R.drawable.img);
            builder.setPositiveButton("Co", (dialogInterface, i) -> {
                mList.remove(position);
                notifyDataSetChanged();
                mainActivity.list = mList;
            });
            builder.setNegativeButton("Khong", (dialogInterface, i) -> {});
            AlertDialog dialog = builder.create();
            dialog.show();
        });

    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public class CatViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView img;
        TextView name, price, info;
        Button btnRemove;
        public CatViewHolder(@NonNull View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.item_img);
            name = itemView.findViewById(R.id.item_name);
            price = itemView.findViewById(R.id.item_price);
            info = itemView.findViewById(R.id.item_desc);
            btnRemove = itemView.findViewById(R.id.item_btn_remove);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (catItemListener != null) {catItemListener.onItemClick(view, getAdapterPosition());}
        }
    }

    public void add (Cat cat) {
        mList.add(cat);
        notifyDataSetChanged();
        mainActivity.list = mList;
    }

    public void update (Cat cat, int position) {
        mList.set(position, cat);
        notifyDataSetChanged();
        mainActivity.list = mList;
    }

    public interface CatItemListener {
        void onItemClick (View view, int position);
    }
}
