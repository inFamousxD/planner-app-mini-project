package com.example.appdraweractivitytest;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class FragmentIncomplete extends Fragment {
    View view;
    private List<RecyclerViewItems> recyclerViewItemsList;
    public static int countPending;
    private RecyclerViewAdapter recyclerViewAdapter;

    public FragmentIncomplete() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.all_fragment, container, false);
        RecyclerView recyclerView = view.findViewById(R.id.recycler_all_fragment);
        recyclerViewAdapter = new RecyclerViewAdapter(getContext(), recyclerViewItemsList);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(recyclerViewAdapter);
        return view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference("tasks").child(LoginPage.passwd).child("status").child("pending");
        recyclerViewItemsList = new ArrayList<>();

        mDatabase.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                String string = dataSnapshot.getValue(String.class);
                String taskId = dataSnapshot.getKey();
                recyclerViewItemsList.add(new RecyclerViewItems(R.drawable.ic_status_pending, string, taskId, "pending"));
                countPending = (int) dataSnapshot.getChildrenCount();
                recyclerViewAdapter.notifyDataSetChanged();
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                recyclerViewAdapter.notifyDataSetChanged();
            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {
                recyclerViewAdapter.notifyItemRemoved(RecyclerViewAdapter.positionOfRemoval);
            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                recyclerViewAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                recyclerViewAdapter.notifyDataSetChanged();
            }
        });

    }
}
