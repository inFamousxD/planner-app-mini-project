package com.example.appdraweractivitytest;

import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.ArrayList;
import java.util.List;


public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder> {
    Context mContext;
    List<RecyclerViewItems> mData;
    public static String taskTitle, id;
    DatabaseReference databaseReference;
    LinearLayout taskItem;
    Dialog dialog;
    public static int positionOfRemoval;

    public RecyclerViewAdapter(Context mContext, List<RecyclerViewItems> mData) {
        this.mContext = mContext;
        this.mData = mData;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull final ViewGroup parent, int viewType) {
        View view;
        view = LayoutInflater.from(mContext).inflate(R.layout.task_item, parent, false);

        taskItem = view.findViewById(R.id.taskItem);
        final MyViewHolder mHolder = new MyViewHolder(view);

        dialog = new Dialog(mContext);
        dialog.setContentView(R.layout.expand_task);

        mHolder.fragmentTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                taskTitle = mData.get(mHolder.getAdapterPosition()).getTaskTitleRV();
                id = mData.get(mHolder.getAdapterPosition()).getTaskDescRV();
                final int pos = mHolder.getAdapterPosition();
                positionOfRemoval = pos;

                TextView taskTitleView = dialog.findViewById(R.id.taskExpandTaskName);
                Button okButton = dialog.findViewById(R.id.okButton);
                Button delButton = dialog.findViewById(R.id.delButton);
                taskTitleView.setText(taskTitle);

                okButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                    }
                });
                delButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Toast.makeText(mContext, "Delete pressed", Toast.LENGTH_SHORT).show();
                        Toast.makeText(mContext, id, Toast.LENGTH_SHORT).show();

                        databaseReference = FirebaseDatabase.getInstance().getReference("tasks").child(LoginPage.passwd).child("status").child("pending").child(id);
                        databaseReference.removeValue();
                        mData.remove(positionOfRemoval);
                        dialog.dismiss();
                    }
                });
                dialog.show();
            }
        });
        return mHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.fragmentTextView.setText(mData.get(position).getTaskTitleRV());
        holder.fragmentImageView.setImageResource(mData.get(position).getImageResource());
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView fragmentTextView;
        public ImageView fragmentImageView;

        public MyViewHolder(View view) {
            super(view);
            fragmentTextView = view.findViewById(R.id.all_fragment_taskTitle);
            fragmentImageView = view.findViewById(R.id.all_fragment_taskStatusImage);
        }
    }
}