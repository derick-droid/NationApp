package com.example.nation;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MinistryRvAdapter extends RecyclerView.Adapter<MinistryRvAdapter.ViewHolder>{
    private ArrayList<MinistryRVModal> ministryRVModalArrayList;
    private Context context;
    int lastpos = -1;
    private MinistryClickInterface ministryClickInterface;

    public MinistryRvAdapter(ArrayList<MinistryRVModal> ministryRVModalArrayList, Context context, MinistryClickInterface ministryClickInterface) {
        this.ministryRVModalArrayList = ministryRVModalArrayList;
        this.context = context;
        this.ministryClickInterface = ministryClickInterface;
    }

    @NonNull
    @Override
    public MinistryRvAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.ministry_rv_iyem, parent,false);
        return new ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull MinistryRvAdapter.ViewHolder holder, int position) {
         MinistryRVModal ministryRVModal = ministryRVModalArrayList.get(position);
         holder.MinistryNameTV.setText(ministryRVModal.getMinistryName());
         holder.MinistryCodeTV.setText("Rs " + ministryRVModal.getMinistryCode());
         Picasso.get().load(ministryRVModal.getMinistryImage()).into(holder.MinistryIV);
         setAnimation(holder.itemView, position);
         holder.itemView.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 ministryClickInterface.onMinistryClick(position);
             }
         });


    }
    private void setAnimation(View itemView, int position ){
        if(position > lastpos){
            Animation animation = AnimationUtils.loadAnimation(context, android.R.anim.slide_in_left);
            itemView.setAnimation(animation);
            lastpos = position;


        }

    }

    @Override
    public int getItemCount() {

        return ministryRVModalArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView MinistryNameTV, MinistryCodeTV;
        private ImageView MinistryIV;

        public ViewHolder(@NonNull View ItemView){

            super(ItemView);
            MinistryNameTV = itemView.findViewById(R.id.idTVMnistryName);
            MinistryCodeTV = itemView.findViewById(R.id.idTVCode);
            MinistryIV = itemView.findViewById(R.id.idIVMnistry);

        }

    }
    public  interface MinistryClickInterface{
        void onMinistryClick(int position);
    }

}
