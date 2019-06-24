package com.wonder.sabbir.robin.DatabaseHelper;

public class userRegistrationDatabase {
   String mName,mEmailAddress,mPassword;

    public userRegistrationDatabase() {
    }

    public userRegistrationDatabase(String mName, String mEmailAddress, String mPassword) {
        this.mName = mName;
        this.mEmailAddress = mEmailAddress;
        this.mPassword = mPassword;
    }

    public String getmName() {
        return mName;
    }

    public void setmName(String mName) {
        this.mName = mName;
    }

    public String getmEmailAddress() {
        return mEmailAddress;
    }

    public void setmEmailAddress(String mEmailAddress) {
        this.mEmailAddress = mEmailAddress;
    }

    public String getmPassword() {
        return mPassword;
    }

    public void setmPassword(String mPassword) {
        this.mPassword = mPassword;
    }

}
