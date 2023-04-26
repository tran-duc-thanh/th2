package com.example.th2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.th2.adapter.CatAdapter;
import com.example.th2.adapter.SpinnerAdapter;
import com.example.th2.dal.SQLiteHelper;
import com.example.th2.model.Cat;

public class ActivityUpdateDelete extends AppCompatActivity {

    private Cat cat;

    private Spinner spinner;
    private EditText eName, ePrice, eInfo;
    private Button btnDel, btnUpdate, btnBack;
    private SQLiteHelper db;
    private int[] imgs = {R.drawable.cat2,R.drawable.cat3,R.drawable.cat4,R.drawable.cat5,R.drawable.cat6,R.drawable.cat7,R.drawable.cat8,R.drawable.cat9,R.drawable.cat10};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);
        initView();
        Intent intent = getIntent();
        cat = (Cat) intent.getSerializableExtra("cat");
        eName.setText(cat.getName());
        ePrice.setText(String.valueOf(cat.getPrice()));
        eInfo.setText(cat.getInfo());
        btnUpdate.setOnClickListener(view -> {
            if (!this.validate()) return;
            String i = spinner.getSelectedItem().toString();
            int img;
            try {
                img = imgs[Integer.parseInt(i)];
                double price = Double.parseDouble(ePrice.getText().toString());
                Cat cat1 = new Cat(cat.getId(), img, eName.getText().toString(), price, eInfo.getText().toString());
                db.update(cat1);
                Toast.makeText(getApplicationContext(), "Edit success", Toast.LENGTH_SHORT).show();
                finish();
            } catch (NumberFormatException e) {
                ePrice.setError("Giá trị trường này là số");
            }
        });

        btnDel.setOnClickListener(view -> {
            db.delete(cat.getId());
            Toast.makeText(getApplicationContext(), "Delete success", Toast.LENGTH_SHORT).show();
            finish();
        });

        btnBack.setOnClickListener(view -> finish());
    }

    private void initView() {
        db = new SQLiteHelper(getApplicationContext());
        spinner = findViewById(R.id.spinner);
        SpinnerAdapter spinnerAdapter = new SpinnerAdapter(getApplicationContext(), imgs);
        spinner.setAdapter(spinnerAdapter);
        eName = findViewById(R.id.eName);
        ePrice = findViewById(R.id.ePrice);
        eInfo = findViewById(R.id.eDesc);
        btnDel = findViewById(R.id.btnDel);
        btnBack = findViewById(R.id.btnBack);
        btnUpdate = findViewById(R.id.btnUpdate);
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
}