package pe.adoptapet.adoptapet.model;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.parse.GetDataCallback;
import com.parse.ParseException;
import com.parse.ParseFile;
import com.parse.ParseObject;
import com.parse.ParseUser;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;

import pe.adoptapet.adoptapet.R;

/**
 * Created by AndrewSteven on 30/11/2015.
 */
public class AdoptResultAdapter extends ArrayAdapter<ParseObject> {
    private final Activity context;

    public AdoptResultAdapter(Activity context, ArrayList<ParseObject> lstPet) {
        super(context, R.layout.adopt_result_item, lstPet);
        // TODO Auto-generated constructor stub

        this.context=context;
    }

    public View getView(int position,View view,ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View rowView = inflater.inflate(R.layout.adopt_result_item, null, true);

        if (position % 2 == 0) {
            rowView.setBackgroundResource(R.drawable.bg_trans_orange);
        } else {
            rowView.setBackgroundResource(R.drawable.bg_trans_yellow);
        }

        TextView txtTitle = (TextView) rowView.findViewById(R.id.adopt_result_item_text);
        TextView txtOwner = (TextView) rowView.findViewById(R.id.adopt_result_item_owner);
        final ImageView imageView = (ImageView) rowView.findViewById(R.id.adopt_result_item_icon);

        txtTitle.setText((String)getItem(position).get("petName"));

        ParseObject giveUser = getItem(position).getParseObject("giveUser");
        ParseObject newOwner = getItem(position).getParseObject("newOwner");
        if(newOwner!=null){
            String ownerName = "";
            for (String key : newOwner.keySet()) {
                if(key.equals("name")){
                    ownerName+=newOwner.get(key);
                }else if(key.equals("lastname")){
                    ownerName+=" "+newOwner.get(key);
                }
                //System.out.println("Key-> "+key + ":" + giveUser.get(key));
            }
            txtOwner.setText("Adopted by "+ownerName);
        }else if(giveUser!=null){
            String ownerName = "";
            for (String key : giveUser.keySet()) {
                if(key.equals("name")){
                    ownerName+=giveUser.get(key);
                }else if(key.equals("lastname")){
                    ownerName+=" "+giveUser.get(key);
                }
                //System.out.println("Key-> "+key + ":" + giveUser.get(key));
            }
            txtOwner.setText(ownerName);
        }

        //imageView.setImageResource(getItem(position).getPhotoRes());
        byte[] photo = null;
        ParseFile fimg = getItem(position).getParseFile("petPhoto");
        if(fimg!=null){
            fimg.getDataInBackground(new GetDataCallback() {
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
        }
        return rowView;

    };
}
