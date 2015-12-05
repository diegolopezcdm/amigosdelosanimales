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
public class CampaignAdapter extends ArrayAdapter<String> {

    private final Activity context;

    private final Campaign[] lstCampaign;

    public CampaignAdapter(Activity context, Campaign[] lstCampaign, String[] itemname) {

        super(context, R.layout.campaign_item, itemname);
        // TODO Auto-generated constructor stub

        this.context=context;

        this.lstCampaign=lstCampaign;
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

        ImageView imageView = (ImageView) rowView.findViewById(R.id.campaign_item_icon);

        txtTitle.setText(lstCampaign[position].getCampaignName());

        txtDesc.setText(lstCampaign[position].getCampaignShortDesc());
        
        imageView.setImageResource(lstCampaign[position].getCampaignRes());

        return rowView;

    };
}
