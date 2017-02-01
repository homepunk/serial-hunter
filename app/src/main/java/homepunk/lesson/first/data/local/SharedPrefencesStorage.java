package homepunk.lesson.first.data.local;

import android.content.Context;
import android.content.SharedPreferences;

import homepunk.lesson.first.data.database.Constants;

public class SharedPrefencesStorage {
    private final Context context;

    public SharedPrefencesStorage(Context context) {
        this.context = context;
    }

    public void saveId(int id){
        SharedPreferences sharedPref = context.getSharedPreferences(Constants.SHARED_PREF_NAME,Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putInt(Constants.SHARED_PREF_KEY_ID, id);
        editor.commit();
    }

    public int getId(){
        SharedPreferences sharedPref = context.getSharedPreferences(Constants.SHARED_PREF_NAME,Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();

        int id = sharedPref.getInt(Constants.SHARED_PREF_KEY_ID, 0);
        editor.clear();
        editor.commit();

        return id;
    }
}
