package com.example.th2.fargment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.th2.MainActivity;
import com.example.th2.R;
import com.example.th2.adapter.CatAdapter;
import com.example.th2.adapter.SearchAdapter;
import com.example.th2.adapter.SpinnerAdapter;
import com.example.th2.dal.SQLiteHelper;
import com.example.th2.model.Cat;

import java.util.ArrayList;
import java.util.List;

public class FragmentDetail extends Fragment implements SearchAdapter.CatItemListener{

    private RecyclerView recyclerView;
    private SearchAdapter adapter;
    private SearchView search;
    private SQLiteHelper db;
    private ImageView img;
    private TextView name, price, info;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_add, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView(view);
        search.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                filter(newText);
                return false;
            }

            private void filter (String s) {
                List<Cat> filterList = new ArrayList<>();
                filterList = db.search(s);
                if (filterList.isEmpty()) {
                    Toast.makeText(getContext(), "No data", Toast.LENGTH_SHORT).show();
                } else {
                    adapter.setListCat(filterList);
                }
            }
        });
    }

    private void initView(View view) {
        db = new SQLiteHelper(getContext());
        search = view.findViewById(R.id.search);
        adapter = new SearchAdapter();
        adapter.setCatItemListener(this);
        img = view.findViewById(R.id.detailImg);
        name = view.findViewById(R.id.detailName);
        price = view.findViewById(R.id.detailPrice);
        info = view.findViewById(R.id.detailInfo);
        recyclerView = view.findViewById(R.id.reViewDetail);
        LinearLayoutManager manager = new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false);
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(adapter);
    }


    @Override
    public void onItemClick(View view, int position) {
        Cat cat = adapter.getItem(position);
        this.setText(cat);
    }

    private void setText (Cat cat) {
        img.setImageResource(cat.getImg());
        name.setText(cat.getName());
        price.setText(String.valueOf(cat.getPrice()));
        info.setText(cat.getInfo());
    }
}
