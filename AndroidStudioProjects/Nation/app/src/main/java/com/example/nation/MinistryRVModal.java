package com.example.nation;

import android.os.Parcel;
import android.os.Parcelable;

public class MinistryRVModal implements Parcelable {
    private String MinistryName;
    private String MinistryDescription;
    private String MinistryCode;
    private String MinistryBestSuitedFor;
    private String MinistryImage;
    private String MinistryLink;
    private String MinistryID;

    public MinistryRVModal(){

    }

    protected MinistryRVModal(Parcel in) {
        MinistryName = in.readString();
        MinistryDescription = in.readString();
        MinistryCode = in.readString();
        MinistryBestSuitedFor = in.readString();
        MinistryImage = in.readString();
        MinistryLink = in.readString();
        MinistryID = in.readString();
    }

    public static final Creator<MinistryRVModal> CREATOR = new Creator<MinistryRVModal>() {
        @Override
        public MinistryRVModal createFromParcel(Parcel in) {
            return new MinistryRVModal(in);
        }

        @Override
        public MinistryRVModal[] newArray(int size) {
            return new MinistryRVModal[size];
        }
    };

    public String getMinistryName() {
        return MinistryName;
    }

    public void setMinistryName(String ministryName) {
        MinistryName = ministryName;
    }

    public String getMinistryDescription() {
        return MinistryDescription;
    }

    public void setMinistryDescription(String ministryDescription) {
        MinistryDescription = ministryDescription;
    }

    public String getMinistryCode() {
        return MinistryCode;
    }

    public void setMinistryCode(String ministryCode) {
        MinistryCode = ministryCode;
    }

    public String getMinistryBestSuitedFor() {
        return MinistryBestSuitedFor;
    }

    public void setMinistryBestSuitedFor(String ministryBestSuitedFor) {
        MinistryBestSuitedFor = ministryBestSuitedFor;
    }

    public String getMinistryImage() {
        return MinistryImage;
    }

    public void setMinistryImage(String ministryImage) {
        MinistryImage = ministryImage;
    }

    public String getMinistryLink() {
        return MinistryLink;
    }

    public void setMinistryLink(String ministryLink) {
        MinistryLink = ministryLink;
    }

    public String getMinistryID() {
        return MinistryID;
    }

    public void setMinistryID(String ministryID) {
        MinistryID = ministryID;
    }

    public MinistryRVModal(String ministryName, String ministryDescription, String ministryCode, String ministryBestSuitedFor, String ministryImage, String ministryLink, String ministryID) {
        MinistryName = ministryName;
        MinistryDescription = ministryDescription;
        MinistryCode = ministryCode;
        MinistryBestSuitedFor = ministryBestSuitedFor;
        MinistryImage = ministryImage;
        MinistryLink = ministryLink;
        MinistryID = ministryID;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(MinistryName);
        parcel.writeString(MinistryDescription);
        parcel.writeString(MinistryCode);
        parcel.writeString(MinistryBestSuitedFor);
        parcel.writeString(MinistryImage);
        parcel.writeString(MinistryLink);
        parcel.writeString(MinistryID);
    }
}
