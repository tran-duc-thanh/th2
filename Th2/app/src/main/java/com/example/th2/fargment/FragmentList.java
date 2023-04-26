package com.example.th2.fargment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.th2.ActivityUpdateDelete;
import com.example.th2.MainActivity;
import com.example.th2.R;
import com.example.th2.adapter.SearchAdapter;
import com.example.th2.dal.SQLiteHelper;
import com.example.th2.model.Cat;

import java.util.List;

public class FragmentList extends Fragment implements SearchAdapter.CatItemListener {

    private SearchAdapter adapter;
    private RecyclerView recyclerView;
    private SQLiteHelper db;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_list, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView = view.findViewById(R.id.reViewSearch);
        adapter = new SearchAdapter();
        adapter.setCatItemListener(this);
        LinearLayoutManager manager = new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false);
        recyclerView.setLayoutManager(manager);
        db = new SQLiteHelper(getContext());
        List<Cat> mList = db.getAll();
        if (mList == null || mList.isEmpty()) {
            Toast.makeText(getContext(), "No data", Toast.LENGTH_SHORT).show();
        } else {
            adapter.setListCat(mList);
        }
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onResume() {
        super.onResume();
        adapter.setListCat(db.getAll());
    }

    @Override
    public void onItemClick(View view, int position) {
        Cat cat = adapter.getItem(position);
        Intent intent = new Intent(getActivity(), ActivityUpdateDelete.class);
        intent.putExtra("cat", cat);
        startActivity(intent);
    }
}
