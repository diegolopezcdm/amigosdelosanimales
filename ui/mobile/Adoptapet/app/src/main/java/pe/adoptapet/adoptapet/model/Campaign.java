package pe.adoptapet.adoptapet.model;

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

    private File campaignPhoto;

    public Campaign(String objectId, String campaignName, String campaignShortDesc, String campaignLink, int campaignRes, File campaignPhoto) {
        this.objectId = objectId;

        this.campaignName = campaignName;
        this.campaignShortDesc = campaignShortDesc;
        this.campaignLink = campaignLink;
        this.campaignRes = campaignRes;
        this.campaignPhoto = campaignPhoto;
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

    public File getCampaignPhoto() {
        return campaignPhoto;
    }

    public void setCampaignPhoto(File campaignPhoto) {
        this.campaignPhoto = campaignPhoto;
    }
}
