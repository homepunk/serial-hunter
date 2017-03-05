package homepunk.lesson.series.utils;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import homepunk.lesson.first.contollers.R;
import homepunk.lesson.series.ui.detailed.DetailedActivity;
import homepunk.lesson.series.ui.detailed.DetailedFragment;

import static homepunk.lesson.series.data.Constants.KEY_ID;

public class NavigationUtils {
    public static void navigateToDetailed(Context context, int id){
        Intent intent = new Intent(context, DetailedActivity.class);
        intent.putExtra(KEY_ID, id);

        context.startActivity(intent);
    }

    public static int getId(Intent intent) {
        return (int) intent.getSerializableExtra(KEY_ID);
    }

    public static void sendIdToFragment(int id, FragmentManager fm){
        DetailedFragment fragmentDetailed = newDetailedFragment(id);
        FragmentTransaction ft = fm.beginTransaction();
        ft.add(R.id.content_fragment_id, fragmentDetailed);
        ft.commit();
    }

    private static DetailedFragment newDetailedFragment(int id) {
        DetailedFragment fragment = new DetailedFragment();

        Bundle arguments = new Bundle();
        arguments.putInt(KEY_ID, id);
        fragment.setArguments(arguments);

        return fragment;
    }
}
