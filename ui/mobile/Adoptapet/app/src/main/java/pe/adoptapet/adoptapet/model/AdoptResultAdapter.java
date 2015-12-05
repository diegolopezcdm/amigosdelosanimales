package pe.adoptapet.adoptapet.model;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.parse.GetDataCallback;
import com.parse.ParseException;

import java.lang.reflect.Array;
import java.util.ArrayList;

import pe.adoptapet.adoptapet.R;

/**
 * Created by AndrewSteven on 30/11/2015.
 */
public class AdoptResultAdapter extends ArrayAdapter<Pet> {
    private final Activity context;

    public AdoptResultAdapter(Activity context, ArrayList<Pet> lstPet) {
        super(context, R.layout.adopt_result_item, lstPet);
        // TODO Auto-generated constructor stub

        this.context=context;
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
        final ImageView imageView = (ImageView) rowView.findViewById(R.id.adopt_result_item_icon);

        txtTitle.setText(getItem(position).getPetName());
        txtOwner.setText(getItem(position).getOwnerName());
        //imageView.setImageResource(getItem(position).getPhotoRes());
        byte []photo = null;
        getItem(position).getPetPhoto().getDataInBackground(new GetDataCallback() {
            @Override
            public void done(byte[] data, ParseException e) {
                if (e != null) {
                    e.printStackTrace();
                } else {
                    try {
                        Bitmap bm = BitmapFactory.decodeByteArray(data, 0, data.length);
                        imageView.setImageBitmap(bm);
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }
            }
        });
        return rowView;

    };
}
