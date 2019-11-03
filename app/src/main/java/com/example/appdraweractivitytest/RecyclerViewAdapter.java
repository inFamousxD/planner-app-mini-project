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
    public static String status;

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
                status = mData.get(mHolder.getAdapterPosition()).getStatus();
                positionOfRemoval = pos;

                TextView taskTitleView = dialog.findViewById(R.id.taskExpandTaskName);
                Button okButton = dialog.findViewById(R.id.okButton);
                Button delButton = dialog.findViewById(R.id.delButton);
                final Button markButton = dialog.findViewById(R.id.markButton);
                taskTitleView.setText(taskTitle);

                if (status == "complete") {
                    markButton.setText("Set as incomplete");
                    okButton.setText("Share");
                }
                else if (status == "pending") {
                    okButton.setText("Share");
                    markButton.setText("Set as complete");
                }
                else {
                    markButton.setText("Add to personal tasks");
                }

                okButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (status != "shared") {
                            databaseReference = FirebaseDatabase.getInstance().getReference("tasks").child("shared");
                            String taskIdString = databaseReference.push().getKey();
                            Tasks task = new Tasks(taskTitle, taskIdString);

                            databaseReference.child(taskIdString).setValue(taskTitle);
                            Toast.makeText(mContext, "Done", Toast.LENGTH_SHORT).show();
                            dialog.dismiss();
                        }
                        else
                            dialog.dismiss();
                    }
                });
                delButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Toast.makeText(mContext, "Delete pressed", Toast.LENGTH_SHORT).show();
                        Toast.makeText(mContext, id, Toast.LENGTH_SHORT).show();

                        if (status == "all")
                            databaseReference = FirebaseDatabase.getInstance().getReference("tasks").child("shared").child(id);
                        else
                            databaseReference = FirebaseDatabase.getInstance().getReference("tasks").child(LoginPage.passwd).child("status").child(status).child(id);

                        databaseReference.removeValue();
                        mData.remove(positionOfRemoval);
                        dialog.dismiss();
                    }
                });
                markButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String tempCopy = taskTitle;
                        if (status == "complete") {
                            databaseReference = FirebaseDatabase.getInstance().getReference("tasks").child(LoginPage.passwd).child("status").child(status).child(id);
                            databaseReference.removeValue();
                            mData.remove(positionOfRemoval);

                            databaseReference = FirebaseDatabase.getInstance().getReference("tasks").child(LoginPage.passwd).child("status").child("pending");
                            String taskIdString = databaseReference.push().getKey();
                            Tasks task = new Tasks(tempCopy, taskIdString);

                            databaseReference.child(taskIdString).setValue(tempCopy);
                            Toast.makeText(mContext, "Done", Toast.LENGTH_SHORT).show();
                            dialog.dismiss();
                        }
                        else if (status == "pending") {
                            databaseReference = FirebaseDatabase.getInstance().getReference("tasks").child(LoginPage.passwd).child("status").child(status).child(id);
                            databaseReference.removeValue();
                            mData.remove(positionOfRemoval);

                            databaseReference = FirebaseDatabase.getInstance().getReference("tasks").child(LoginPage.passwd).child("status").child("complete");
                            String taskIdStringComp = databaseReference.push().getKey();
                            Tasks task = new Tasks(tempCopy, taskIdStringComp);

                            databaseReference.child(taskIdStringComp).setValue(tempCopy);
                            Toast.makeText(mContext, "Done", Toast.LENGTH_SHORT).show();
                            dialog.dismiss();
                        }
                        else {
                            databaseReference = FirebaseDatabase.getInstance().getReference("tasks").child(LoginPage.passwd).child("status").child("pending");
                            String taskIdStringComp = databaseReference.push().getKey();
                            Tasks task = new Tasks(tempCopy, taskIdStringComp);

                            databaseReference.child(taskIdStringComp).setValue(tempCopy);
                            Toast.makeText(mContext, "Done", Toast.LENGTH_SHORT).show();
                            dialog.dismiss();
                        }
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