package pe.adoptapet.adoptapet.view;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
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
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.parse.LogInCallback;
import com.parse.ParseFile;
import com.parse.ParseUser;
import com.parse.ProgressCallback;
import com.parse.SaveCallback;
import com.parse.SignUpCallback;

import java.io.ByteArrayOutputStream;
import java.text.ParseException;

import pe.adoptapet.adoptapet.R;
import pe.adoptapet.adoptapet.model.Constants;


public class LoginActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_login);

        ParseUser currentUser = ParseUser.getCurrentUser();
        if (currentUser == null) {
            goToHomeLogin();
        }else{
            goToMain(null);
        }

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
        // update the main content by replacing fragments
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
        finish();
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
        public EditText txtUser, txtPassword;
        public Button btnLogin;

        ProgressDialog barProgressDialog;

        public LoginFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_login_login, container, false);
            txtUser = (EditText)rootView.findViewById(R.id.txtLoginUser);
            txtPassword = (EditText)rootView.findViewById(R.id.txtLoginPassword);
            btnLogin = (Button)rootView.findViewById(R.id.btnLoginLogin);
            btnLogin.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String username = txtUser.getText().toString().trim();
                    final String password = txtPassword.getText().toString().trim();


                    if (username.isEmpty() || password.isEmpty()) {
                        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                        builder.setMessage("User and password are required!")
                                .setTitle("Login error")
                                .setPositiveButton(android.R.string.ok, null);
                        AlertDialog dialog = builder.create();
                        dialog.show();
                    }else {
                        showProgressBar(true);

                        ParseUser.logInInBackground(username, password, new LogInCallback() {
                            @Override
                            public void done(ParseUser user, com.parse.ParseException e) {
                                showProgressBar(false);

                                if (e == null) {
                                    ((LoginActivity)getActivity()).goToMain(null);
                                } else {
                                    // Fail
                                    AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                                    builder.setMessage(e.getMessage())
                                            .setTitle("Login Error")
                                            .setPositiveButton(android.R.string.ok, null);
                                    AlertDialog dialog = builder.create();
                                    dialog.show();
                                }
                            }
                        });
                    }

                }
            });
            return rootView;
        }

        public void showProgressBar(boolean state){
            if(state){
                barProgressDialog = ProgressDialog.show(getActivity(), "Please wait ...",	"Login to Adopt a pet ...", true);
                barProgressDialog.setCancelable(false);
            }else{
                barProgressDialog.dismiss();
                barProgressDialog=null;
            }
        }
    }

    public static class CreateFragment extends Fragment {
        public EditText txtUser, txtPassword, txtEmail, txtFirstName, txtLastName,txtBirthday,txtDNI, txtPhone, txtAdress;
        public ImageView imgPhoto;
        public Button btnCreate;

        ProgressDialog barProgressDialog;
        public CreateFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_login_create, container, false);
            imgPhoto = (ImageView)rootView.findViewById(R.id.newaccount_img_pick);
            txtUser = (EditText)rootView.findViewById(R.id.txtNewAccountUser);
            txtPassword = (EditText)rootView.findViewById(R.id.txtNewAccountPassword);
            txtEmail = (EditText)rootView.findViewById(R.id.txtNewAccountEmail);
            txtFirstName = (EditText)rootView.findViewById(R.id.txtNewAccountFirstName);
            txtLastName = (EditText)rootView.findViewById(R.id.txtNewAccountLastName);
            txtBirthday = (EditText)rootView.findViewById(R.id.txtNewAccountBirthday);
            txtAdress = (EditText)rootView.findViewById(R.id.txtNewAccountAddress);
            txtDNI = (EditText)rootView.findViewById(R.id.txtNewAccountDNI);
            txtPhone = (EditText)rootView.findViewById(R.id.txtNewAccountPhone);
            btnCreate = (Button)rootView.findViewById(R.id.btnSaveNewAccountPet);



            btnCreate.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String username = txtUser.getText().toString().trim();
                    final String password = txtPassword.getText().toString().trim();
                    String email = txtEmail.getText().toString().trim();
                    String firstname = txtFirstName.getText().toString().trim();
                    String lastname = txtLastName.getText().toString().trim();
                    String birthday = txtBirthday.getText().toString().trim();
                    String address = txtAdress.getText().toString().trim();
                    String dni = txtDNI.getText().toString().trim();
                    String phone = txtPhone.getText().toString().trim();


                    if (username.isEmpty() || password.isEmpty() || email.isEmpty() || firstname.isEmpty() || lastname.isEmpty() || birthday.isEmpty() || dni.isEmpty() || phone.isEmpty() || address.isEmpty()) {
                        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                        builder.setMessage("All field are required!")
                                .setTitle("Create Account Error")
                                .setPositiveButton(android.R.string.ok, null);
                        AlertDialog dialog = builder.create();
                        dialog.show();
                    } else {
                        showProgressBar(true);

                        final ParseUser newUser = new ParseUser();
                        newUser.setUsername(username);
                        newUser.setPassword(password);
                        newUser.setEmail(email);
                        newUser.put("name", firstname);
                        newUser.put("lastname", lastname);
                        newUser.put("birthday", birthday);
                        newUser.put("address", address);
                        newUser.put("dni", dni);
                        newUser.put("phono", phone);
                        //newUser.put("emailVerified", true);

                        imgPhoto.buildDrawingCache();
                        ByteArrayOutputStream stream = new ByteArrayOutputStream();
                        Bitmap bm = imgPhoto.getDrawingCache();
                        bm.compress(Bitmap.CompressFormat.PNG, 100, stream);
                        byte[] bfile = stream.toByteArray();
                        final ParseFile file = new ParseFile(username + ".png", bfile);
                        file.saveInBackground(new SaveCallback() {
                            @Override
                            public void done(com.parse.ParseException e2) {
                               if(e2 == null){
                                   newUser.put("photo", file);

                                   newUser.signUpInBackground(new SignUpCallback() {
                                       @Override
                                       public void done(com.parse.ParseException e) {
                                           showProgressBar(false);

                                           if (e == null) {
                                               ((LoginActivity) getActivity()).goToMain(null);
                                           } else {
                                               AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                                               builder.setMessage(e.getMessage())
                                                       .setTitle("Create Account Error")
                                                       .setPositiveButton(android.R.string.ok, null);
                                               AlertDialog dialog = builder.create();
                                               dialog.show();
                                           }
                                       }
                                   });
                               }else{
                                   AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                                   builder.setMessage(e2.getMessage())
                                           .setTitle("Create Account Error")
                                           .setPositiveButton(android.R.string.ok, null);
                                   AlertDialog dialog = builder.create();
                                   dialog.show();
                               }
                            }
                        });

                    }
                }
            });
            return rootView;
        }



        public void showProgressBar(boolean state){
            if(state){
                barProgressDialog = ProgressDialog.show(getActivity(), "Please wait ...",	"Creating account ...", true);
                barProgressDialog.setCancelable(false);
            }else{
                if(barProgressDialog!=null){
                    barProgressDialog.dismiss();
                    barProgressDialog=null;
                }
            }
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
