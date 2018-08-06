package com.org.network.data.local;

import android.content.Context;
import android.content.SharedPreferences;

public class PreferenceHelper {

    private final String NAME = "app";
    private final SharedPreferences mPref;

    public PreferenceHelper(Context context) {
        mPref = context.getSharedPreferences(NAME, Context.MODE_PRIVATE);
    }

    public void setPrefListener(SharedPreferences.OnSharedPreferenceChangeListener prefListener) {
        if (prefListener != null)
            mPref.registerOnSharedPreferenceChangeListener(prefListener);
    }

    public void clearPrefListener(SharedPreferences.OnSharedPreferenceChangeListener prefListener) {
        if (prefListener != null)
            mPref.unregisterOnSharedPreferenceChangeListener(prefListener);
    }


}
