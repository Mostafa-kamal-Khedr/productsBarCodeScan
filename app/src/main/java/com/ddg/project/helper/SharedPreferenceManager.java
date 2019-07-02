package com.ddg.project.helper;
import android.content.Context;
import android.content.SharedPreferences;

public class SharedPreferenceManager {

    private static String USER_ID = "userId";
    private static String USER_TYPE = "userType";
    private static String USER_NAME = "userName";
    public static String USER_SIGNED_IN = "userSignedIn";
    public static String USER_TOKEN = "userToken";
    private static String RESTRAUNT_ID = "restraunt_id";

    private static String LANGUAGE = "selectedLanguage";
    public static String PREFS = "PREFS";

    private static final String USER_ADDRESS = "userAddress";
    private static final String CATEGORY_NAME = "categoryName";
    private static final String USER_MAPADDRESS = "userMapAddress";
    private static final String USER_LAT = "userLat";
    private static final String USER_LONG = "userLong";
    public static SharedPreferences sharedPreferences;


    Context context;

    public SharedPreferenceManager(Context context) {


        this.context = context;
        sharedPreferences = context.getSharedPreferences(SharedPreferenceManager.PREFS, context.MODE_PRIVATE);

    }


    public void saveLanguage(String code) {
        SharedPreferences.Editor myEditor = sharedPreferences.edit();
        myEditor.putString(LANGUAGE, code);
        myEditor.apply();
    }

    public String loadLanguage() {
        return sharedPreferences.getString(LANGUAGE, "ar");
    }

    public void saveRestrauntId(String restrauntId) {
        SharedPreferences.Editor myEditor = sharedPreferences.edit();
        myEditor.putString(RESTRAUNT_ID, restrauntId);
        myEditor.apply();
    }
    public String loadRestrauntId(){
        return sharedPreferences.getString(RESTRAUNT_ID,"1");
    }


    public void saveUserInfo(String userId, String userType, String userName, Boolean isSigned, String userToken, float userLat, float userLong) {
        SharedPreferences.Editor myEditor = sharedPreferences.edit();
        myEditor.putString(USER_ID, userId);
        myEditor.putString(USER_TYPE, userType);
        myEditor.putString(USER_NAME, userName);
        myEditor.putBoolean(USER_SIGNED_IN, isSigned);
        myEditor.putString(USER_TOKEN, userToken);
        myEditor.putFloat(USER_LAT, userLat);
        myEditor.putFloat(USER_LONG, userLong);


        myEditor.apply();
        myEditor.commit();

    }

/*
    public void saveUserCoordinates(String userAddress, String userMapAddress, LatLng userLatLong) {
        SharedPreferences.Editor myEditor = sharedPreferences.edit();
        myEditor.putString(USER_ADDRESS, userAddress);
        myEditor.putString(USER_MAPADDRESS, userMapAddress);
        myEditor.putFloat(USER_LAT, (float) userLatLong.latitude);
        myEditor.putFloat(USER_LONG, (float) userLatLong.longitude);

        myEditor.apply();
        myEditor.commit();
    }
*/

    public void saveCategoryName(String categoryName) {
        SharedPreferences.Editor myEditor = sharedPreferences.edit();
        myEditor.putString(CATEGORY_NAME, categoryName);


        myEditor.apply();
        myEditor.commit();
    }


    public String loadCategoryName() {
        return sharedPreferences.getString(CATEGORY_NAME, null);
    }

    public String loadUserAddress() {
        return sharedPreferences.getString(USER_ADDRESS, null);
    }

    public String loadUserType() {
        return sharedPreferences.getString(USER_TYPE, null);
    }

    public String loadUserMapAddress() {
        return sharedPreferences.getString(USER_MAPADDRESS, null);
    }

    public float loadUserLat() {
        return sharedPreferences.getFloat(USER_LAT, 25.5f);
    }

    public float loadUserLong() {
        return sharedPreferences.getFloat(USER_LONG, 27.5f);
    }

    public void saveUserLatLong(float userLat, float userLong) {
        SharedPreferences.Editor myEditor = sharedPreferences.edit();

        myEditor.putFloat(USER_LAT, userLat);
        myEditor.putFloat(USER_LONG, userLong);


        myEditor.apply();
        myEditor.commit();
    }

    public String loadUserId() {
        return sharedPreferences.getString(USER_ID, null);
    }


    public String loadUserName() {
        return sharedPreferences.getString(USER_NAME, null);
    }

    public Boolean loadUserSigned() {
        return sharedPreferences.getBoolean(USER_SIGNED_IN, false);
    }

    public String loadUserToken() {
        return sharedPreferences.getString(USER_TOKEN, null);
    }

    public void deleteUserInfo() {
        SharedPreferences.Editor myEditor = sharedPreferences.edit();
        myEditor.remove(USER_ID);
        myEditor.remove(USER_TYPE);
        myEditor.remove(USER_NAME);
        myEditor.putBoolean(USER_SIGNED_IN, false);
        myEditor.remove(USER_TOKEN);
        myEditor.apply();
        myEditor.commit();
    }

    public void Clean() {
        SharedPreferences.Editor myEditor = sharedPreferences.edit();
        myEditor.clear();
        myEditor.apply();
        myEditor.commit();
    }

    public void cleanUserLocation() {
        SharedPreferences.Editor myEditor = sharedPreferences.edit();
        myEditor.remove(USER_ADDRESS);
        myEditor.remove(USER_MAPADDRESS);
        myEditor.remove(USER_LAT);
        myEditor.remove(USER_LONG);


        myEditor.commit();
    }
}
