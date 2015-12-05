package pe.adoptapet.adoptapet.view;

import android.app.Activity;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;

import pe.adoptapet.adoptapet.R;
import pe.adoptapet.adoptapet.model.Constants;


public class LoginActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        getSupportActionBar().hide();

        setContentView(R.layout.activity_login);

        goToHomeLogin();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_login, menu);
        return true;

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void goToHomeLogin() {

        // update the main content by replacing fr
        // agments

        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()

                .replace(R.id.fgLoginContainer, new HomeFragment())

                .commit();
    }

    public void goToLoginFragment(View v){

        FragmentManager fragmentManager = getSupportFragmentManager();

        fragmentManager.beginTransaction()
                .replace(R.id.fgLoginContainer, new LoginFragment())
                .setTransition(android.support.v4.app.FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                .addToBackStack("loginFrag")
                .commit();

    }

    public void goToCreateFragment(View v){

        FragmentManager fragmentManager = getSupportFragmentManager();

        fragmentManager.beginTransaction()
                .replace(R.id.fgLoginContainer, new CreateFragment())
                .setTransition(android.support.v4.app.FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                .addToBackStack("createFrag")
                .commit();

    }

    public void goToMain(View v){

        Intent i = new Intent(this, MainActivity.class);

        startActivity(i);

    }

    public void goToSigInFragment(View v){
        Log.d("HomeFragment", "goToSigInFragment");
    }


    public static class HomeFragment extends Fragment {

        public HomeFragment() {

        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {

            View rootView = inflater.inflate(R.layout.fragment_login_home, container, false);

            return rootView;

        }
    }

    public static class LoginFragment extends Fragment {

        public LoginFragment() {

        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {

            View rootView = inflater.inflate(R.layout.fragment_login_login, container, false);

            return rootView;
        }
    }

    public static class CreateFragment extends Fragment {
        public CreateFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {

            View rootView = inflater.inflate(R.layout.fragment_login_create, container, false);

            return rootView;
        }
    }

    private  static ImageView imgPickPhoto;
    public void selectAccountPhoto(View v){
        imgPickPhoto = (ImageView)v;
        Intent i = new Intent(
                Intent.ACTION_PICK,

                android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(i, Constants.RESULT_LOAD_IMAGE);
        
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        Constants.imageGallerySelected(this, requestCode, resultCode, data, imgPickPhoto);


    }
}
