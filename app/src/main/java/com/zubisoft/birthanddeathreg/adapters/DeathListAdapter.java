package com.zubisoft.birthanddeathreg.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.zubisoft.birthanddeathreg.R;
import com.zubisoft.birthanddeathreg.model.birthmodels.BirthRegData;
import com.zubisoft.birthanddeathreg.model.deathmodels.DeathRegData;

import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Locale;

public class DeathListAdapter extends RecyclerView.Adapter<DeathListAdapter.DeathItemViewHolder> {

    private ArrayList<DeathRegData> deathList=new ArrayList<>();
    private DeathListItemListener deathListItemListener;

    @NonNull
    @Override
    public DeathItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.death_list_item, parent,false);
        return new DeathItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DeathItemViewHolder holder, int position) {
        DeathRegData deathRegData=deathList.get(position);
        if (deathRegData!=null){
            holder.txtName.setText(deathRegData.getDeceasedData().getName());
            holder.txtAddress.setText(deathRegData.getDeceasedData().getAddress());
            holder.txtDate.setText(MessageFormat.format("{0} years", deathRegData.getDeceasedData().getAge()));

            holder.itemView.setOnClickListener(view->deathListItemListener.onItemClicked(deathRegData));
            holder.btnMore.setOnClickListener(view->deathListItemListener.onOptionsItemClicked(deathRegData, holder.btnMore));
        }
    }

    @Override
    public int getItemCount() {
        return deathList.size();
    }

    public void setDeathList(ArrayList<DeathRegData> deathList) {
        this.deathList = deathList;
        notifyDataSetChanged();
    }

    public void setDeathListItemListener(DeathListItemListener deathListItemListener) {
        this.deathListItemListener = deathListItemListener;
    }

    class DeathItemViewHolder extends RecyclerView.ViewHolder{

        private final TextView txtName;
        private final TextView txtAddress;
        private final TextView txtDate;
        private final ImageView btnMore;

        public DeathItemViewHolder(@NonNull View itemView) {
            super(itemView);

            txtName = itemView.findViewById(R.id.txtName);
            txtAddress=itemView.findViewById(R.id.txtAddress);
            txtDate=itemView.findViewById(R.id.txtDate);
            btnMore=itemView.findViewById(R.id.btnMore);
        }
    }

    public interface DeathListItemListener{
        public void onItemClicked(DeathRegData deathRegData);
        public void onOptionsItemClicked(DeathRegData deathRegData, View view);
    }
}
