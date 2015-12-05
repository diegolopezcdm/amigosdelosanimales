package pe.adoptapet.adoptapet;

import android.app.Application;
import android.content.Context;

import com.parse.Parse;
import com.parse.ParseACL;
import com.parse.ParseObject;
import com.parse.ParseUser;

/**
 * Created by amostacero on 30/11/2015.
 */
public class AdoptAPertApp extends Application {
    private static Context mContext;

    public void onCreate(){
        super.onCreate();
        mContext = this;
        Parse.enableLocalDatastore(this);
        Parse.initialize(this, "vV1EElXhOIc1pOgCaRkfQD6FfRYVC7v38Ys8nOYh", "QisDlcaV1VhT4cnYblAGyoYTNu8GmhC2oFAZgSL3");

        /*ParseUser.enableAutomaticUser();
        ParseACL defaultACL = new ParseACL();

        // If you would like all objects to be private by default, remove this
        // line.
        defaultACL.setPublicReadAccess(true);

        ParseACL.setDefaultACL(defaultACL, true);
        //ParseObject testObject = new ParseObject("TestObject");
        //testObject.put("foo", "bar");
        //testObject.saveInBackground();*/
    }

    public static Context getContext(){
        return mContext;
    }

}
