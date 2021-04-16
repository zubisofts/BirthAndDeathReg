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
import com.zubisoft.birthanddeathreg.ui.birth.BirthViewModel;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Locale;

public class BirthListAdapter extends RecyclerView.Adapter<BirthListAdapter.BirthItemViewHolder> {

    private ArrayList<BirthRegData> birthList=new ArrayList<>();

    @NonNull
    @Override
    public BirthItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.birth_list_item, parent,false);
        return new BirthItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BirthItemViewHolder holder, int position) {
        BirthRegData birthRegData=birthList.get(position);
        if (birthRegData!=null){
            holder.txtName.setText(birthRegData.getChildBirthData().getChildName());
            holder.txtAddress.setText(birthRegData.getChildBirthData().getPlaceOfBirth());
            String dob=new SimpleDateFormat("EEE, d MMM yyyy", Locale.getDefault()).format(birthRegData.getChildBirthData().getDateOfBirth());
            holder.txtDate.setText(dob);
        }
    }

    @Override
    public int getItemCount() {
        return birthList.size();
    }

    public void setBirthList(ArrayList<BirthRegData> birthList) {
        this.birthList = birthList;
        notifyDataSetChanged();
    }

    class BirthItemViewHolder extends RecyclerView.ViewHolder{

        private final TextView txtName;
        private final TextView txtAddress;
        private final TextView txtDate;
        private final ImageView btnMore;

        public BirthItemViewHolder(@NonNull View itemView) {
            super(itemView);

            txtName = itemView.findViewById(R.id.txtName);
            txtAddress=itemView.findViewById(R.id.txtAddress);
            txtDate=itemView.findViewById(R.id.txtDate);
            btnMore=itemView.findViewById(R.id.btnMore);
        }
    }
}
