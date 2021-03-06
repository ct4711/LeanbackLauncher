package com.amazon.tv.firetv.leanbacklauncher.apps;

import android.content.Context;
import android.content.pm.PackageManager;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.support.v7.preference.PreferenceManager;
import android.util.Log;
import android.widget.Toast;

import com.amazon.tv.leanbacklauncher.MainActivity;
import com.amazon.tv.leanbacklauncher.R;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by rockon999 on 3/25/18.
 */

public class RowPreferences {
    public static final String TAG = "AppPrefs";

    public static int[] getFavoriteRowConstraints(Context context) {
        SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(context);

        Resources res = context.getResources();

        return new int[]{
                pref.getInt(context.getString(R.string.pref_min_favorites_rows), res.getInteger(R.integer.min_num_banner_rows)),
                pref.getInt(context.getString(R.string.pref_max_favorites_rows), res.getInteger(R.integer.max_num_banner_rows))
        };
    }

    public static int[] getAllAppsConstraints(Context context) {
        SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(context);

        Resources res = context.getResources();

        return new int[]{
                pref.getInt(context.getString(R.string.pref_min_apps_rows), res.getInteger(R.integer.min_num_banner_rows)),
                pref.getInt(context.getString(R.string.pref_max_apps_rows), res.getInteger(R.integer.max_num_banner_rows))
        };
    }

    public static int[] getRowConstraints(AppCategory cat, Context context) {
        SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(context);

        Resources res = context.getResources();

        switch (cat) {
            case GAME:
                return new int[]{
                        pref.getInt(context.getString(R.string.pref_min_games_rows), res.getInteger(R.integer.min_num_banner_rows)),
                        pref.getInt(context.getString(R.string.pref_max_games_rows), res.getInteger(R.integer.max_num_banner_rows))
                };
            case MUSIC:
                return new int[]{
                        pref.getInt(context.getString(R.string.pref_min_music_rows), res.getInteger(R.integer.min_num_banner_rows)),
                        pref.getInt(context.getString(R.string.pref_max_music_rows), res.getInteger(R.integer.max_num_banner_rows))
                };
            case VIDEO:
                return new int[]{
                        pref.getInt(context.getString(R.string.pref_min_videos_rows), res.getInteger(R.integer.min_num_banner_rows)),
                        pref.getInt(context.getString(R.string.pref_max_videos_rows), res.getInteger(R.integer.max_num_banner_rows))
                };
            default:
                return new int[]{0, 0};
        }
    }

    public static boolean areFavoritesEnabled(Context context) {
        SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(context);
        return pref.getBoolean(context.getString(R.string.pref_enable_favorites_row), true);
    }

    public static boolean setFavoritesEnabled(Context context, boolean value) {
        SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(context);
        pref.edit().putBoolean(context.getString(R.string.pref_enable_favorites_row), value).apply();
        return true;
    }

    public static boolean areInputsEnabled(Context context) {
        SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(context);
        return pref.getBoolean(context.getString(R.string.pref_enable_inputs_row), false);  // true
    }

    public static boolean setInputsEnabled(Context context, boolean value) {
        SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(context);
        pref.edit().putBoolean(context.getString(R.string.pref_enable_inputs_row), value).apply();
        return true;
    }

    public static boolean areRecommendationsEnabled(Context context) {
        SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(context);
        return pref.getBoolean(context.getString(R.string.pref_enable_recommendations_row), false); // true
    }

    public static boolean setRecommendationsEnabled(Context context, boolean value) {
        if (context.getPackageManager().checkPermission("android.permission.WRITE_SECURE_SETTINGS", context.getPackageName()) != PackageManager.PERMISSION_DENIED) {
            SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(context);
            pref.edit().putBoolean(context.getString(R.string.pref_enable_recommendations_row), value).apply();
        } else {
            Toast.makeText(context, context.getString(R.string.recs_warning), Toast.LENGTH_LONG).show();
        }
        return true;
    }

    public static Set<AppCategory> getEnabledCategories(Context context) {
        SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(context);
        Set<AppCategory> categories = new HashSet<>();

        if (pref.getBoolean(context.getString(R.string.pref_enable_games_row), true))
            categories.add(AppCategory.GAME);
        if (pref.getBoolean(context.getString(R.string.pref_enable_music_row), true))
            categories.add(AppCategory.MUSIC);
        if (pref.getBoolean(context.getString(R.string.pref_enable_videos_row), true))
            categories.add(AppCategory.VIDEO);

        return categories;
    }

    public static boolean setGamesEnabled(Context context, boolean value) {
        SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(context);
        pref.edit().putBoolean(context.getString(R.string.pref_enable_games_row), value).apply();
        return true;
    }

    public static boolean setMusicEnabled(Context context, boolean value) {
        SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(context);
        pref.edit().putBoolean(context.getString(R.string.pref_enable_music_row), value).apply();
        return true;
    }

    public static boolean setVideosEnabled(Context context, boolean value) {
        SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(context);
        pref.edit().putBoolean(context.getString(R.string.pref_enable_videos_row), value).apply();
        return true;
    }    

    public static boolean setFavoriteRowConstraints(Context context, int min, int max) {
        SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(context);
        Resources res = context.getResources();
        
        pref.edit().putInt(context.getString(R.string.pref_min_favorites_rows), min).apply();
        pref.edit().putInt(context.getString(R.string.pref_max_favorites_rows), max).apply();
        return true;
    }

    public static boolean setFavoriteRowMin(Context context, int min) {
        SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(context);
        Resources res = context.getResources();        
        pref.edit().putInt(context.getString(R.string.pref_min_favorites_rows), min).apply();
        return true;
    }

    public static boolean setFavoriteRowMax(Context context, int max) {
        SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(context);
        Resources res = context.getResources();        
        pref.edit().putInt(context.getString(R.string.pref_max_favorites_rows), max).apply();
        return true;
    }

    public static boolean setAllAppsConstraints(Context context, int min, int max) {
        SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(context);
        Resources res = context.getResources();

        pref.edit().putInt(context.getString(R.string.pref_min_apps_rows), min).apply();
        pref.edit().putInt(context.getString(R.string.pref_max_apps_rows), max).apply();
        return true;
    }

    public static boolean setAllAppsMin(Context context, int min) {
        SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(context);
        Resources res = context.getResources();        
        pref.edit().putInt(context.getString(R.string.pref_min_apps_rows), min).apply();
        return true;
    }

    public static boolean setAllAppsMax(Context context, int max) {
        SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(context);
        Resources res = context.getResources();        
        pref.edit().putInt(context.getString(R.string.pref_max_apps_rows), max).apply();
        return true;
    }

    public static boolean setRowConstraints(AppCategory cat, Context context, int min, int max) {
        SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(context);
        Resources res = context.getResources();

        switch (cat) {
            case GAME:
                    pref.edit().putInt(context.getString(R.string.pref_min_games_rows), min).apply();
                    pref.edit().putInt(context.getString(R.string.pref_max_games_rows), max).apply();
            case MUSIC:
                    pref.edit().putInt(context.getString(R.string.pref_min_music_rows), min).apply();
                    pref.edit().putInt(context.getString(R.string.pref_max_music_rows), max).apply();
            case VIDEO:
                    pref.edit().putInt(context.getString(R.string.pref_min_videos_rows), min).apply();
                    pref.edit().putInt(context.getString(R.string.pref_max_videos_rows), max).apply();
        }
        return true;
    }
    public static boolean setRowMin(AppCategory cat, Context context, int min) {
        SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(context);
        Resources res = context.getResources();

        switch (cat) {
            case GAME:
                    pref.edit().putInt(context.getString(R.string.pref_min_games_rows), min).apply();
            case MUSIC:
                    pref.edit().putInt(context.getString(R.string.pref_min_music_rows), min).apply();
            case VIDEO:
                    pref.edit().putInt(context.getString(R.string.pref_min_videos_rows), min).apply();
        }
        return true;
    }
    public static boolean setRowMax(AppCategory cat, Context context, int max) {
        SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(context);
        Resources res = context.getResources();

        switch (cat) {
            case GAME:
                    pref.edit().putInt(context.getString(R.string.pref_max_games_rows), max).apply();
            case MUSIC:
                    pref.edit().putInt(context.getString(R.string.pref_max_music_rows), max).apply();
            case VIDEO:
                    pref.edit().putInt(context.getString(R.string.pref_max_videos_rows), max).apply();
        }
        return true;
    }

}
