package com.example.todo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.todo.R;

import java.util.List;

public class Notesadapter extends RecyclerView.Adapter<Notesadapter.notesViewHolder> {
    List<Notesmodel> notesmodel;
    Context context;

    public Notesadapter(List<Notesmodel> notesmodel, Context context) {
        this.notesmodel = notesmodel;
        this.context = context;
    }

    @NonNull
    @Override
    public notesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.recycler,parent,false);
        notesViewHolder nv = new notesViewHolder(view);
        return nv;
    }

    @Override
    public void onBindViewHolder(notesViewHolder nv, int position) {
        Notesmodel model = notesmodel.get(position);
        nv.titleN.setText(model.gettitle());
        nv.descD.setText(model.getdesc());

    }


    @Override
    public int getItemCount() {
        return notesmodel.size();
    }

    public class notesViewHolder extends RecyclerView.ViewHolder {

        TextView titleN;
        TextView descD;

        public notesViewHolder(@NonNull View itemView) {
            super(itemView);
            titleN = itemView.findViewById(R.id.titleone);
            descD = itemView.findViewById(R.id.titleotwo);
        }
    }
}





