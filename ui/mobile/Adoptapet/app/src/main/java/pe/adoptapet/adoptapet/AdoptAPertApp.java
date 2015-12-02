package pe.adoptapet.adoptapet;

import android.app.Application;
import android.content.Context;

import com.parse.Parse;
import com.parse.ParseObject;

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

        //ParseObject testObject = new ParseObject("TestObject");
        //testObject.put("foo", "bar");
        //testObject.saveInBackground();
    }

    public static Context getContext(){
        return mContext;
    }

}
