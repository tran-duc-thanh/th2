package com.example.th2.fargment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.th2.MainActivity;
import com.example.th2.R;
import com.example.th2.adapter.CatAdapter;
import com.example.th2.adapter.SpinnerAdapter;
import com.example.th2.dal.SQLiteHelper;
import com.example.th2.model.Cat;

public class FragmentAdd extends Fragment implements CatAdapter.CatItemListener {

    private CatAdapter adapter;
    private Spinner spinner;
    private EditText eName, ePrice, eInfo;
    private Button btnAdd, btnUpdate;
    private RecyclerView recyclerView;
    private SQLiteHelper db;
    private int pcurr;
    private int[] imgs = {R.drawable.cat2,R.drawable.cat3,R.drawable.cat4,R.drawable.cat5,R.drawable.cat6,R.drawable.cat7,R.drawable.cat8,R.drawable.cat9,R.drawable.cat10};

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_add, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView(view);
        adapter = new CatAdapter((MainActivity) getActivity());
        adapter.setmList(db.getAll());
        LinearLayoutManager manager = new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false);
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(adapter);
        adapter.setCatItemListener(this);
        btnAdd.setOnClickListener(view1 -> {
            if (!this.validate()) return;
            String i = spinner.getSelectedItem().toString();
            int img;
            try {
                img = imgs[Integer.parseInt(i)];
                double price = Double.parseDouble(ePrice.getText().toString());
                Cat cat = new Cat(img, eName.getText().toString(), price, eInfo.getText().toString());
                db.add(cat);
                this.resetForm();
                adapter.notifyDataSetChanged();
            } catch (NumberFormatException e) {
                ePrice.setError("Giá trị trường này là số");
            }
        });
        btnUpdate.setOnClickListener(view1 -> {
            if (!this.validate()) return;
            String i = spinner.getSelectedItem().toString();
            int img;
            try {
                img = imgs[Integer.parseInt(i)];
                double price = Double.parseDouble(ePrice.getText().toString());
                Cat cat = new Cat(pcurr, img, eName.getText().toString(), price, eInfo.getText().toString());
                db.update(cat);
                btnUpdate.setVisibility(View.INVISIBLE);
                btnAdd.setVisibility(View.VISIBLE);
                this.resetForm();
                adapter.notifyDataSetChanged();
            } catch (NumberFormatException e) {
                ePrice.setError("Giá trị trường này là số");
            }
        });
    }

    private void initView(View view) {
        db = new SQLiteHelper(getContext());
        spinner = view.findViewById(R.id.spinner);
        SpinnerAdapter spinnerAdapter = new SpinnerAdapter(getContext(), imgs);
        spinner.setAdapter(spinnerAdapter);
        eName = view.findViewById(R.id.eName);
        ePrice = view.findViewById(R.id.ePrice);
        eInfo = view.findViewById(R.id.eDesc);
        btnAdd = view.findViewById(R.id.btnAdd);
        btnUpdate = view.findViewById(R.id.btnUpdate);
        recyclerView = view.findViewById(R.id.reView);
        btnUpdate.setVisibility(View.INVISIBLE);
    }

    private boolean validate () {
        if ("".equals(eName.getText().toString().trim()) || "".equals(ePrice.getText().toString().trim())) {
            eName.setError("Không được bỏ trống");
            return false;
        }
        return true;
    }

    private void resetForm () {
        eName.setText("");
        ePrice.setText("");
        eInfo.setText("");
        spinner.setSelection(0);
    }

    @Override
    public void onItemClick(View view, int position) {
        btnAdd.setVisibility(View.INVISIBLE);
        btnUpdate.setVisibility(View.VISIBLE);
        Cat cat = adapter.getItem(position);
        pcurr = cat.getId();
        int img = cat.getImg();
        int p = 0;
        for (int i = 0; i <imgs.length; i++) {
            if(img == imgs[i]) {
                p = i;
                break;
            }
        }
        spinner.setSelection(p);
        eName.setText(cat.getName());
        ePrice.setText(cat.getPrice()+"");
        eInfo.setText(cat.getInfo());
    }

    @Override
    public void onResume() {
        super.onResume();
        adapter.setmList(db.getAll());
    }
}
