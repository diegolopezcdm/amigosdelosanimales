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

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;

import pe.adoptapet.adoptapet.R;

/**
 * Created by AndrewSteven on 30/11/2015.
 */
public class CampaignAdapter extends ArrayAdapter<Campaign> {
    private final Activity context;

    public CampaignAdapter(Activity context, ArrayList<Campaign> lstCampaign) {
        super(context, R.layout.campaign_item, lstCampaign);
        // TODO Auto-generated constructor stub

        this.context=context;
    }

    public View getView(int position,View view,ViewGroup parent) {
        LayoutInflater inflater=context.getLayoutInflater();
        View rowView=inflater.inflate(R.layout.campaign_item, null,true);

        if(position%2==0){
            rowView.setBackgroundResource(R.drawable.bg_trans_orange);
        }else{
            rowView.setBackgroundResource(R.drawable.bg_trans_yellow);
        }

        TextView txtTitle = (TextView) rowView.findViewById(R.id.campaign_item_text);
        TextView txtDesc = (TextView) rowView.findViewById(R.id.campaign_item_desc);
        final ImageView imageView = (ImageView) rowView.findViewById(R.id.campaign_item_icon);



        txtTitle.setText(getItem(position).getCampaignName());
        txtDesc.setText(getItem(position).getCampaignShortDesc());
        int resID = getItem(position).getCampaignRes();
        byte []photo = getItem(position).getBytePhoto();
        getItem(position).getCampaignPhoto().getDataInBackground(new GetDataCallback() {
            @Override
            public void done(byte[] data, ParseException e) {
                if (e != null) {
                    e.printStackTrace();
                }else{
                    try{
                        Bitmap bm = BitmapFactory.decodeByteArray(data,0,data.length);
                        imageView.setImageBitmap(bm);
                    }catch (Exception ex){ex.printStackTrace();}
                }
            }
        });
        /*byte []photo = getItem(position).getBytePhoto();
        if(photo!=null){
            try{
                Bitmap bm = BitmapFactory.decodeByteArray(photo,0,photo.length);
                imageView.setImageBitmap(bm);
            }catch (Exception ex){}
        }else if(resID!=0){
            imageView.setImageResource(resID);
        }*/

        return rowView;

    };
}
