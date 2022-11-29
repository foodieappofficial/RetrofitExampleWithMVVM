package com.example.retrofitexamplewithmvvm;




import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;


import com.example.retrofitexamplewithmvvm.R;


import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class RetroPostListAdapter extends RecyclerView.Adapter<RetroPostListAdapter.ViewHolder> {
    Context context;
    ArrayList<ResultModel> noteList = new ArrayList<>();


    public RetroPostListAdapter(Context context) {
        this.context = context;
    }

    @NonNull

    @Override
    public ViewHolder onCreateViewHolder(@NonNull   ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.post_item,parent,false);
        ViewHolder viewHolder = new ViewHolder(view);

        return viewHolder;
    }



    @Override
    public void onBindViewHolder(@NonNull @NotNull ViewHolder holder, int position) {


        ResultModel current = noteList.get(position);
        holder.postTitle.setText(current.getTitle().toString());
        holder.postBody.setText(current.getBody());
        holder.postUserId.setText(current.getUserId());
        holder.postId.setText(Integer.toString(current.getId()));







    }


    @Override
    public int getItemCount() {
        return noteList.size();
    }



    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private final TextView postTitle;
        private final TextView postId;
        private final TextView postUserId;
        private final TextView postBody;



        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            postTitle = itemView.findViewById(R.id.tv_title);
            postId = itemView.findViewById(R.id.tv_userid);
            postUserId = itemView.findViewById(R.id.tv_id);
            postBody = itemView.findViewById(R.id.tv_body);




        }




        @Override
        public void onClick(View view) {

        }



    }


    public void setWords(List<ResultModel> newList){
        noteList.clear();
        noteList.addAll(newList);
        notifyDataSetChanged();
    }




    public static class WordDiff extends DiffUtil.ItemCallback<ResultModel> {

        @Override
        public boolean areItemsTheSame(@NonNull ResultModel oldItem, @NonNull ResultModel newItem) {
            return oldItem == newItem;
        }

        @Override
        public boolean areContentsTheSame(@NonNull ResultModel oldItem, @NonNull ResultModel newItem) {
            return oldItem.getTitle().equals(newItem.getTitle());
        }
    }




    /*
    public void removeItem(int position){
        dharamshalaList.remove(position);
        notifyItemRemoved(position);
        // notifyItemRangeChanged(position, WishListAdapter.this.dharamshalaList.size());
        notifyDataSetChanged();

    }

     */





}