package pe.adoptapet.adoptapet.model;

import java.io.File;

/**
 * Created by AndrewSteven on 30/11/2015.
 */
public class Pet {
    private String objectId;
    private String petName;
    private String ownerName;
    private String petType;
    private String ownerId;
    private String petSize;
    private String petRace;
    private String petAge;
    private File petPhoto;
    private int photoRes;
    private String petDescription;

    public Pet(){

    }



    public Pet(String objectId, String petName, String petType, String ownerName, String ownerId, String petSize, String petRace, String petAge, File petPhoto, int photoRes, String petDescription) {
        this.objectId = objectId;
        this.petName = petName;
        this.petType = petType;
        this.ownerName = ownerName;
        this.ownerId = ownerId;
        this.petSize = petSize;
        this.petRace = petRace;
        this.petAge = petAge;
        this.petPhoto = petPhoto;
        this.photoRes = photoRes;
        this.petDescription = petDescription;
    }

    public String getObjectId() {
        return objectId;
    }

    public void setObjectId(String objectId) {
        this.objectId = objectId;
    }

    public String getPetName() {
        return petName;
    }

    public void setPetName(String petName) {
        this.petName = petName;
    }

    public String getPetType() {
        return petType;
    }

    public void setPetType(String petType) {
        this.petType = petType;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    public String getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(String ownerId) {
        this.ownerId = ownerId;
    }

    public String getPetSize() {
        return petSize;
    }

    public void setPetSize(String petSize) {
        this.petSize = petSize;
    }

    public String getPetRace() {
        return petRace;
    }

    public void setPetRace(String petRace) {
        this.petRace = petRace;
    }

    public String getPetAge() {
        return petAge;
    }

    public void setPetAge(String petAge) {
        this.petAge = petAge;
    }

    public File getPetPhoto() {
        return petPhoto;
    }

    public void setPetPhoto(File petPhoto) {
        this.petPhoto = petPhoto;
    }

    public int getPhotoRes() {
        return photoRes;
    }

    public void setPhotoRes(int photoRes) {
        this.photoRes = photoRes;
    }

    public String getPetDescription() {
        return petDescription;
    }

    public void setPetDescription(String petDescription) {
        this.petDescription = petDescription;
    }
}
