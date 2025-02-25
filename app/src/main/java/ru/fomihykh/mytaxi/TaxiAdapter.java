package ru.fomihykh.mytaxi;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class TaxiAdapter extends ArrayAdapter<Taxi> {
    private LayoutInflater inflater;
    private int layout;
    private List<Taxi> taxis;

    public TaxiAdapter(@NonNull Context context, int resource,List<Taxi>taxis) {
        super(context, resource, taxis);
        this.taxis=taxis;
        this.layout=resource;
        this.inflater=LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = inflater.inflate(this.layout,parent,false);
        TextView profitView = view.findViewById(R.id.valueProfit);
        TextView mileageView = view.findViewById(R.id.valueMileage);
        TextView gsmView = view.findViewById(R.id.valueGsm);
        TextView lossView = view.findViewById(R.id.valueLoss);
        TextView dateView = view.findViewById(R.id.valueDate);
        TextView commentView = view.findViewById(R.id.valueComment);
        Taxi taxi = taxis.get(position);
        profitView.setText(String.valueOf(taxi.getProfit()));
        mileageView.setText(String.valueOf(taxi.getMileage()));
        gsmView.setText(String.valueOf(taxi.getGsm()));
        lossView.setText(String.valueOf(taxi.getNp()));
        dateView.setText(String.valueOf(taxi.getDate()));
        commentView.setText(String.valueOf(taxi.getComment()));
        return view;
    }
}
