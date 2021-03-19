package com.example.androidtest;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class ValuteAdapter extends ArrayAdapter<JSONObject> {
    int listLayout;
    ArrayList<JSONObject> list;
    Context context;

    public ValuteAdapter(Context context, int listLayout, int field, ArrayList<JSONObject> list){
        super(context,listLayout,field,list);
        this.context = context;
        this.listLayout = listLayout;
        this.list = list;
    }

    public View getView(int position, View convertView, ViewGroup parent){
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View itemView = inflater.inflate(listLayout, parent, false);
        TextView id = itemView.findViewById(R.id.textViewID);
        TextView numCode = itemView.findViewById(R.id.textViewNumCode);
        TextView charCode = itemView.findViewById(R.id.textViewCharCode);
        TextView nominal = itemView.findViewById(R.id.textViewNominal);
        TextView name = itemView.findViewById(R.id.textViewName);
        TextView value = itemView.findViewById(R.id.textViewValue);
        TextView previous = itemView.findViewById(R.id.textViewPrevious);
        try{
            String stringId = id.getText().toString() + list.get(position).getString("ID");
            id.setText(stringId);

            String stringNumCode = numCode.getText().toString() + list.get(position).getString("NumCode");
            numCode.setText(stringNumCode);

            String stringCharCode = charCode.getText().toString() + list.get(position).getString("CharCode");
            charCode.setText(stringCharCode);

            String stringNominal = nominal.getText().toString() + list.get(position).getInt("Nominal");
            nominal.setText(stringNominal);

            String stringName = name.getText().toString() + list.get(position).getString("Name");
            name.setText(stringName);

            String stringValue = value.getText().toString() + list.get(position).getDouble("Value");
            value.setText(stringValue);

            String stringPrevious = previous.getText().toString() + list.get(position).getDouble("Previous");
            previous.setText(stringPrevious);


        }catch (JSONException e){
            e.printStackTrace();
        }
        return itemView;
    }


}
