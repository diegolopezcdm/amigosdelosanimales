package pe.adoptapet.adoptapet.model;

import com.parse.GetDataCallback;
import com.parse.ParseException;
import com.parse.ParseFile;

import java.io.File;

/**
 * Created by AndrewSteven on 01/12/2015.
 */
public class Campaign {
    private String objectId;
    private String campaignName;
    private String campaignShortDesc;
    private String campaignLink;
    private int campaignRes;
    private ParseFile campaignPhoto;
    private byte[] bytePhoto;
    public Campaign(){

    }

    public Campaign(String objectId, String campaignName, String campaignShortDesc, String campaignLink, int campaignRes, ParseFile campaignPhoto) {
        this.objectId = objectId;
        this.campaignName = campaignName;
        this.campaignShortDesc = campaignShortDesc;
        this.campaignLink = campaignLink;
        this.campaignRes = campaignRes;
        this.setCampaignPhoto(campaignPhoto);
    }

    public String getObjectId() {
        return objectId;
    }

    public void setObjectId(String objectId) {
        this.objectId = objectId;
    }

    public String getCampaignName() {
        return campaignName;
    }

    public void setCampaignName(String campaignName) {
        this.campaignName = campaignName;
    }

    public String getCampaignShortDesc() {
        return campaignShortDesc;
    }

    public void setCampaignShortDesc(String campaignShortDesc) {
        this.campaignShortDesc = campaignShortDesc;
    }

    public String getCampaignLink() {
        return campaignLink;
    }

    public void setCampaignLink(String campaignLink) {
        this.campaignLink = campaignLink;
    }

    public int getCampaignRes() {
        return campaignRes;
    }

    public void setCampaignRes(int campaignRes) {
        this.campaignRes = campaignRes;
    }

    public ParseFile getCampaignPhoto() {
        return campaignPhoto;
    }

    public void setCampaignPhoto(ParseFile campaignPhoto) {
        this.campaignPhoto = campaignPhoto;
    }

    public byte[] getBytePhoto() {
        return bytePhoto;
    }

    public void setBytePhoto(byte[] bytePhoto) {
        this.bytePhoto = bytePhoto;
    }
}
