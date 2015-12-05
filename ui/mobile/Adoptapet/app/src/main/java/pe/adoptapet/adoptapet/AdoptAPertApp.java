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
        Parse.initialize(this, "5CtQfQhlBmSn76pqLq0JK24gvb9dvfGGeMGgd2qN", "yamLbFomjmy15ZkWMoum2zlHRvbiQs0KcpKR73qS");

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
