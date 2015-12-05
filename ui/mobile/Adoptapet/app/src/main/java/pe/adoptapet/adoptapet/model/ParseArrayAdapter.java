package pe.adoptapet.adoptapet.model;

import android.app.Activity;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.parse.ParseObject;

import java.util.ArrayList;

import pe.adoptapet.adoptapet.R;

/**
 * Created by amostacero on 03/12/2015.
 */
public class ParseArrayAdapter extends ArrayAdapter<ParseObject>  {
    private Activity context;
    private String column;
    public ParseArrayAdapter(Activity context, ArrayList<ParseObject> lst, String column)
    {
        super(context, R.layout.spiner_parse_item, lst);
        this.context = context;
        this.column = column;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        TextView vItem = (TextView)context.getLayoutInflater().inflate(R.layout.spiner_parse_item, null);
        vItem.setText(getItem(position).getString(column));
        return vItem;
    }

    // And here is when the "chooser" is popped up
    // Normally is the same view, but you can customize it if you want
    @Override
    public View getDropDownView(int position, View convertView,ViewGroup parent) {
        TextView vItem = (TextView)context.getLayoutInflater().inflate(R.layout.spiner_parse_item, null);
        vItem.setText(getItem(position).getString(column));
        return vItem;
    }
}
