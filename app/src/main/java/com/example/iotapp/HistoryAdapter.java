package com.example.iotapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class HistoryAdapter extends RecyclerView.Adapter {

    List<CarHistory> carHistoryList;

    public HistoryAdapter(List<CarHistory> carHistoryList) {
        this.carHistoryList = carHistoryList;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_layout, parent, false);
        ViewHolderClass viewHolderClass = new ViewHolderClass(view);

        return viewHolderClass;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ViewHolderClass viewHolderClass = (ViewHolderClass)holder;

        CarHistory carHistory = carHistoryList.get(position);
        viewHolderClass.owned.setText(carHistory.getOwnedBy());
        viewHolderClass.plate.setText(carHistory.getPlateNo());
        viewHolderClass.time.setText(carHistory.getTime());
        viewHolderClass.allowed.setText(carHistory.getIsAllowed());
        viewHolderClass.date.setText(carHistory.getDate());
    }

    @Override
    public int getItemCount() {
        return carHistoryList.size();
    }

    public class ViewHolderClass extends RecyclerView.ViewHolder{
        TextView owned, plate, time, allowed, date;

        public ViewHolderClass(@NonNull View itemView) {
            super(itemView);

            owned = itemView.findViewById(R.id.emailTxt);
            plate = itemView.findViewById(R.id.plateTxt);
            time = itemView.findViewById(R.id.timeTxt);
            allowed = itemView.findViewById(R.id.isAllowedTxt);
            date = itemView.findViewById(R.id.dateTxt);
        }
    }
}
