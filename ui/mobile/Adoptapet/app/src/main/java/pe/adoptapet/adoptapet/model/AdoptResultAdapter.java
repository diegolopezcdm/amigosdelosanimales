package pe.adoptapet.adoptapet.model;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import pe.adoptapet.adoptapet.R;

/**
 * Created by AndrewSteven on 30/11/2015.
 */
public class AdoptResultAdapter extends ArrayAdapter<String> {
    private final Activity context;
    private final Pet[] lstPet;

    public AdoptResultAdapter(Activity context, Pet[] lstPet, String []itemname) {
        super(context, R.layout.adopt_result_item, itemname);
        // TODO Auto-generated constructor stub

        this.context=context;
        this.lstPet=lstPet;
    }

    public View getView(int position,View view,ViewGroup parent) {
        LayoutInflater inflater=context.getLayoutInflater();
        View rowView=inflater.inflate(R.layout.adopt_result_item, null,true);

        if(position%2==0){
            rowView.setBackgroundResource(R.drawable.bg_trans_orange);
        }else{
            rowView.setBackgroundResource(R.drawable.bg_trans_yellow);
        }

        TextView txtTitle = (TextView) rowView.findViewById(R.id.adopt_result_item_text);
        TextView txtOwner = (TextView) rowView.findViewById(R.id.adopt_result_item_owner);
        ImageView imageView = (ImageView) rowView.findViewById(R.id.adopt_result_item_icon);

        txtTitle.setText(lstPet[position].getPetName());
        txtOwner.setText(lstPet[position].getOwnerName());
        imageView.setImageResource(lstPet[position].getPhotoRes());

        return rowView;

    };
}
