package com.example.contacts;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.ListFragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import java.util.ArrayList;
import java.util.List;


public class ItemFragment extends ListFragment {

    itemSelected selected;
    public interface itemSelected
    {
        void onItemSelected(int index);
    }

    public ItemFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

  //  @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container,
//                             Bundle savedInstanceState) {
//        View view = inflater.inflate(R.layout.fragment_list, container, false);
//
//        // Set the adapter
//        return view;
//    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        selected = (itemSelected)context;

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        ArrayList<String> data = new ArrayList<String>();
        data.add("1.this is item 1");
        data.add("2.this is item 2");
        data.add("3.this is item 3");
        setListAdapter(new ArrayAdapter<String>(getActivity(),android.R.layout.simple_list_item_1,data));
    }
}
