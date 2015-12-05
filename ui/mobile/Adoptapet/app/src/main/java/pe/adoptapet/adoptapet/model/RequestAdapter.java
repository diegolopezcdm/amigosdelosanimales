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
import com.parse.ParseFile;
import com.parse.ParseObject;

import java.util.ArrayList;

import pe.adoptapet.adoptapet.R;

/**
 * Created by AndrewSteven on 30/11/2015.
 */
public class RequestAdapter extends ArrayAdapter<ParseObject> {
    private final Activity context;

    public RequestAdapter(Activity context, ArrayList<ParseObject> lstPet) {
        super(context, R.layout.request_item, lstPet);
        // TODO Auto-generated constructor stub

        this.context=context;
    }

    public View getView(int position,View view,ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View rowView = inflater.inflate(R.layout.request_item, null, true);

        if (position % 2 == 0) {
            rowView.setBackgroundResource(R.drawable.bg_trans_orange);
        } else {
            rowView.setBackgroundResource(R.drawable.bg_trans_yellow);
        }

        TextView txtName = (TextView) rowView.findViewById(R.id.request_item_name);
        TextView txtLastName = (TextView) rowView.findViewById(R.id.request_item_lastname);
        final ImageView imageView = (ImageView) rowView.findViewById(R.id.request_item_icon);
        ParseObject reqUser = getItem(position).getParseObject("reqUser");
        txtName.setText((String)reqUser.get("name"));
        txtLastName.setText((String)reqUser.get("lastname"));
        //imageView.setImageResource(getItem(position).getPhotoRes());
        byte[] photo = null;
        ParseFile fimg = reqUser.getParseFile("photo");
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
