package com.emrhmrc.isttabletcrm.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.emrhmrc.isttabletcrm.R;
import com.emrhmrc.isttabletcrm.models.ServApp.ServiceAppointments;

import java.util.List;

public class RcwServAppAdapter extends RecyclerView.Adapter<RcwServAppAdapter.MyViewHolder> {

    private List<ServiceAppointments> model;
    private Context context;

    public RcwServAppAdapter(List<ServiceAppointments> model, Context context) {
        this.model = model;
        this.context = context;
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater layoutInflater = LayoutInflater.from(viewGroup.getContext());
        View view = layoutInflater.inflate(R.layout.isemri_yedek_item, viewGroup, false);
        return new MyViewHolder(view);
    }

    @Override
    public int getItemViewType(int position) {
        final ServiceAppointments current = model.get(position);
        // if(current)
        return super.getItemViewType(position);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, final int i) {
        final ServiceAppointments current = model.get(i);
        myViewHolder.txt_info.setText(current.getSubject());
        myViewHolder.txt_start_date.setText(current.getActualStart());
        myViewHolder.txt_end_date.setText(current.getActualStart());
        myViewHolder.txt_oncelik.setText(current.getStatusCode().getValue().toString());

    }

    @Override
    public int getItemCount() {
        return model == null ? 0 : model.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView txt_info, txt_start_date, txt_end_date, txt_oncelik;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            txt_info = itemView.findViewById(R.id.txt_info);
            txt_start_date = itemView.findViewById(R.id.txt_start_date);
            txt_end_date = itemView.findViewById(R.id.txt_end_date);
            txt_oncelik = itemView.findViewById(R.id.txt_oncelik);
        }
    }
}
