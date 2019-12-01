package com.example.kudos.adapter;

import android.content.Context;
import android.content.Intent;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerView.ViewHolder;

import com.example.kudos.MainActivity;
import com.example.kudos.R;
import com.example.kudos.WritePostActivity;
import com.example.kudos.data.DatabaseHandler;
import com.example.kudos.model.UserPost;
import com.google.gson.Gson;

import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>{
    private Context context;
    private List<UserPost> postList;
    private UserPost post;

    public RecyclerViewAdapter(Context context, List<UserPost> postList){
        this.context=context;
        this.postList=postList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
      View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.post,parent,false);
      return new ViewHolder(view);

    }


    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {
        post=postList.get(position);
        holder.postTv.setText(post.getPost());

        holder.editBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //pass object from the selected view through an intent
                Intent intent= new Intent(context, WritePostActivity.class);
                Gson gson= new Gson();
                String userPostString=gson.toJson(post);

                intent.putExtra("thePost",userPostString);
                context.startActivity(intent);

            }
        });
        holder.deleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //delete the post selected
           DatabaseHandler db= new DatabaseHandler(context);
           db.deletePost(post);

           //refresh the page after deletion
           Intent refresh = new Intent(context, MainActivity.class);
           context.startActivity(refresh);

            }
        });



    }

    @Override
    public int getItemCount() {
        return postList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder  {

    public TextView postTv;
    public Button editBtn;
    public Button deleteBtn;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            postTv=itemView.findViewById(R.id.post_content);
            editBtn=itemView.findViewById(R.id.edit_btn);
            deleteBtn=itemView.findViewById(R.id.delete_btn);
        }



    }

}

