package com.example.retrofitexamplewithmvvm;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.ListAdapter;
import com.example.retrofitexamplewithmvvm.RetroPostListAdapter;

import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import java.util.List;
import java.util.Objects;
import java.util.Scanner;

public class RetroFitPostFragment extends Fragment {


    //RetroPostListAdapter adapter;
    View view = null;
    RetroViewModel retroViewModel;
    public RetroFitPostFragment() {
        // Required empty public constructor
    }

    public static RetroFitPostFragment newInstance(String param1, String param2) {
        RetroFitPostFragment fragment = new RetroFitPostFragment();
        Bundle args = new Bundle();

        fragment.setArguments(args);
        return fragment;
    }




    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);




        //new ViewModelProvider(this).get(NoteViewModel.class);
        retroViewModel = new ViewModelProvider(getActivity()).get(RetroViewModel.class);
        //Toast.makeText(getContext(), "12ka4", Toast.LENGTH_SHORT).show();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view =  inflater.inflate(R.layout.fragment_retro_fit_post, container, false);
        initViews(view);


        setAdapter();


        //adapter.setWords(users);

        retroViewModel.getProjectRetroListObservable().observe(this, new Observer<List<ResultModel>>() {
            @Override
            public void onChanged(@Nullable final List<ResultModel> users) {
                Toast.makeText(getContext(), "12ka4", Toast.LENGTH_SHORT).show();
                // Update the cached copy of the words in the adapter.

               // Log.i("listitem", users.get(0).getBody());
                adapter.setWords(users);

            }
        });
        Toast.makeText(getContext(), "size: " + StaticJavaClass.variable, Toast.LENGTH_SHORT).show();
        return  view;
    }


    RecyclerView recyclerView;
    private void initViews(View view){
        recyclerView = (RecyclerView)view.findViewById(R.id.post_list);
    }


    RetroPostListAdapter adapter = null;
    private void setAdapter(){
        adapter = new RetroPostListAdapter(getActivity());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
    }

/*

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);



    }

 */
}