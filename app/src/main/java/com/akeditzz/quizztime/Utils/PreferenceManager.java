package com.akeditzz.quizztime.Utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.akeditzz.quizztime.R;

/**
 * Created by Akshay on 25-03-2018.
 */

public class PreferenceManager {
    private Context mContext;
    private SharedPreferences settings ;
    private String preference_name;

    public PreferenceManager(Context context)
    {
        mContext= context;
        preference_name = context.getString(R.string.preference_name);
        settings = mContext.getSharedPreferences(preference_name, 0);
    }

    public void clearData()
    {
        settings = mContext.getSharedPreferences(preference_name, 0);
        SharedPreferences.Editor editor = settings.edit();
        editor.apply();
    }

    public void insertUserName(String userName)
    {

        settings = mContext.getSharedPreferences(preference_name, 0);
        SharedPreferences.Editor editor = settings.edit();
        editor.putString(mContext.getString(R.string.preference_username), userName);
        editor.apply();

    }

    public String getUserName()
    {
        return settings.getString(mContext.getString(R.string.preference_username), "");
    }

}
