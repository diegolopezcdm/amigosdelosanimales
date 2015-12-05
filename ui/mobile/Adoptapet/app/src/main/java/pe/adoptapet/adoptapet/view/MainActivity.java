package pe.adoptapet.adoptapet.view;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.support.v4.widget.DrawerLayout;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import com.parse.FindCallback;
import com.parse.GetDataCallback;
import com.parse.ParseException;
import com.parse.ParseFile;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;
import com.parse.SaveCallback;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;

import pe.adoptapet.adoptapet.R;
import pe.adoptapet.adoptapet.model.AdoptResultAdapter;
import pe.adoptapet.adoptapet.model.Campaign;
import pe.adoptapet.adoptapet.model.CampaignAdapter;
import pe.adoptapet.adoptapet.model.Constants;
import pe.adoptapet.adoptapet.model.ParseArrayAdapter;
import pe.adoptapet.adoptapet.model.RequestAdapter;


public class MainActivity extends ActionBarActivity
        implements NavigationDrawerFragment.NavigationDrawerCallbacks {

    /**
     * Fragment managing the behaviors, interactions and presentation of the navigation drawer.
     */
    private NavigationDrawerFragment mNavigationDrawerFragment;


    /**
     * Used to store the last screen title. For use in {@link #restoreActionBar()}.
     */
    private CharSequence mTitle;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //getSupportActionBar().setBackgroundDrawable(new ColorDrawable(0xFFFF7F08));

        mNavigationDrawerFragment = (NavigationDrawerFragment)
                getSupportFragmentManager().findFragmentById(R.id.navigation_drawer);
        mTitle = getTitle();

        // Set up the drawer.
        mNavigationDrawerFragment.setUp(
                R.id.navigation_drawer,
                (DrawerLayout) findViewById(R.id.drawer_layout));



    }

    public void loadPetOptionList(final Spinner spin, final ArrayList<ParseObject>lstOpt, final String parseClass, final String column){
        lstOpt.clear();
        spin.setAdapter(new ParseArrayAdapter(this,lstOpt,column));
        ParseQuery<ParseObject> query = ParseQuery.getQuery(parseClass);
        lstOpt.clear();
        ParseObject temp = new ParseObject(parseClass);
        temp.put(column, "-- select --");
        lstOpt.add(temp);
        ((ParseArrayAdapter) spin.getAdapter()).notifyDataSetChanged();
        query.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> objects, com.parse.ParseException e) {
                if (e == null) {
                    lstOpt.clear();
                    ParseObject temp = new ParseObject(parseClass);
                    temp.put(column, "-- select --");
                    lstOpt.add(temp);
                    for (ParseObject obj : objects) {
                        lstOpt.add(obj);
                    }
                    ((ParseArrayAdapter) spin.getAdapter()).notifyDataSetChanged();
                } else {
                    Log.d(getClass().getSimpleName(), "Error: " + e.getMessage());
                }
            }
        });
    }
/*
    public void loadPetSexList(){
        if(lstPetSex == null) lstPetSex = new ArrayList<ParseObject>();
        else lstPetSex.clear();
        if(adaptPetSex == null){
            adaptPetSex = new ParseArrayAdapter(this,lstPetSex,"petSexDescription");
        }

        ParseQuery<ParseObject> query = ParseQuery.getQuery("petSex");

        query.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> objects, com.parse.ParseException e) {
                if (e == null) {
                    lstPetSex.clear();
                    ParseObject temp = new ParseObject("petSex");
                    temp.put("petSexDescription","-- select --");
                    lstPetSex.add(temp);
                    for (ParseObject obj : objects) {
                        lstPetSex.add(obj);
                    }
                    adaptPetSex.notifyDataSetChanged();
                } else {
                    Log.d(getClass().getSimpleName(), "Error: " + e.getMessage());
                }
            }
        });
    }

    public void loadPetSizeList(){
        if(lstPetSize == null) lstPetSize = new ArrayList<ParseObject>();
        else lstPetSize.clear();
        if(adaptPetSize == null){
            adaptPetSize = new ParseArrayAdapter(this,lstPetSize,"petSizeDescription");
        }

        ParseQuery<ParseObject> query = ParseQuery.getQuery("petSize");

        query.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> objects, com.parse.ParseException e) {
                if (e == null) {
                    lstPetSize.clear();
                    ParseObject temp = new ParseObject("petSize");
                    temp.put("petSizeDescription","-- select --");
                    lstPetSize.add(temp);
                    for (ParseObject obj : objects) {
                        lstPetSize.add(obj);
                    }
                    adaptPetSize.notifyDataSetChanged();
                } else {
                    Log.d(getClass().getSimpleName(), "Error: " + e.getMessage());
                }
            }
        });
    }

    public void loadPetTypeList(){
        if(lstPetType == null) lstPetType = new ArrayList<ParseObject>();
        else lstPetType.clear();
        if(adaptPetType == null){
            adaptPetType = new ParseArrayAdapter(this,lstPetType,"petTypeName");
        }

        ParseQuery<ParseObject> query = ParseQuery.getQuery("PetType");

        query.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> objects, com.parse.ParseException e) {
                if (e == null) {
                    lstPetType.clear();
                    ParseObject temp = new ParseObject("PetType");
                    temp.put("petTypeName","-- select --");
                    lstPetType.add(temp);
                    for (ParseObject obj : objects) {
                        lstPetType.add(obj);
                    }
                    adaptPetType.notifyDataSetChanged();
                } else {
                    Log.d(getClass().getSimpleName(), "Error: " + e.getMessage());
                }
            }
        });
    }
*/

    @Override
    public void onNavigationDrawerItemSelected(int position) {
        if(position==5){
            logOutSession();
            return;
        }
        selectFragment(position, null);
    }

    public void selectFragment(int position, Object obj){
        Fragment fg = fragmentAttached(position+1, obj);
        // update the main content by replacing fragments
        FragmentManager fragmentManager = getSupportFragmentManager();
        if(position < 5){
            fragmentManager.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
            fragmentManager.beginTransaction()
                    .replace(R.id.container, fg)
                    .setCustomAnimations(R.anim.abc_popup_enter, R.anim.abc_popup_exit)
                    //.setTransition(android.support.v4.app.FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                    .commit();
        }else{
            fragmentManager.beginTransaction()
                    .replace(R.id.container, fg)
                    //.setTransition(android.support.v4.app.FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                    .setCustomAnimations(R.anim.abc_popup_enter, R.anim.abc_popup_exit)
                    .addToBackStack(fg.toString())
                    .commit();
        }
    }

    public Fragment fragmentAttached(int number) {
        return fragmentAttached(number, null);
    }

    public Fragment fragmentAttached(int number, Object obj) {
        Fragment fg = null;
        switch (number) {
            case 1:
                fg = new HomeFragment();
                mTitle = getString(R.string.app_name);
                break;
            case 2:
                fg = new AdoptFragment();
                mTitle = getString(R.string.title_adopt);
                break;
            case 3:
                fg = new GiveFragment();
                ((GiveFragment)fg).petID = (String)obj;
                mTitle = getString(R.string.title_give);
                break;
            case 4:
                fg = new CampaignFragment();
                mTitle = getString(R.string.title_campaign);
                break;
            case 5:
                fg = new AccountFragment();
                mTitle = getString(R.string.title_account);
                break;
            case 6:
                fg = new AdoptResultFragment();
                ((AdoptResultFragment)fg).setLista((ArrayList<ParseObject>)obj);
                mTitle = getString(R.string.title_adopt);
                break;
            case 7:
                fg = new PetFragment();
                ((PetFragment)fg).setPet((ParseObject) obj);
                mTitle = getString(R.string.title_pet_detail);
                break;
            case 8:
                fg = new GiveListFragment();
                mTitle = getString(R.string.title_give);
                break;
            case 9:
                fg = new MyRequestFragment();
                mTitle = getString(R.string.title_adopt);
                break;
            case 10:
                fg = new PetRequestFragment();
                ((PetRequestFragment)fg).setPet((ParseObject)obj);
                mTitle = getString(R.string.title_adopt);
                break;
        }

        return fg;
    }

    public void restoreActionBar() {
        ActionBar actionBar = getSupportActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
        actionBar.setDisplayShowTitleEnabled(true);
        actionBar.setTitle(mTitle);
    }

    public void logOutSession(){
        ParseUser.logOut();
        Intent i = new Intent(this,LoginActivity.class);
        startActivity(i);
        finish();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (!mNavigationDrawerFragment.isDrawerOpen()) {
            // Only show items in the action bar relevant to this screen
            // if the drawer is not showing. Otherwise, let the drawer
            // decide what to show in the action bar.
            getMenuInflater().inflate(R.menu.main, menu);
            restoreActionBar();
            return true;
        }
        return super.onCreateOptionsMenu(menu);
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





    private  static ImageView imgPickPhoto;
    public void pickPhotoGallery(View v){
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

    public void goToGiveList(View v){
        selectFragment(7, null);
    }






    public static class HomeFragment extends Fragment {
        public HomeFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_main_home, container, false);
            TextView lblWelcome = (TextView)rootView.findViewById(R.id.txtWelcome);
            ParseUser usr = ParseUser.getCurrentUser();
            lblWelcome.setText("Welcome back "+usr.get("name")+" "+usr.get("lastname")+"!");
            return rootView;
        }
    }
    public static class AccountFragment extends Fragment {
        public EditText txtUser, txtPassword, txtEmail, txtFirstName, txtLastName,txtBirthday,txtDNI, txtPhone, txtAdress;
        public ImageView imgPhoto;
        public Button btnUpdate;
        ProgressDialog barProgressDialog;


        public AccountFragment() {
        }

        public void populateFields(){
            ParseUser user = ParseUser.getCurrentUser();
            String username = user.getUsername();
            String password = "";
            String email = user.getEmail();
            String firstname = (String)user.get("name");
            String lastname = (String)user.get("lastname");
            String birthday = (String)user.get("birthday");
            String address = (String)user.get("address");
            String dni = (String)user.get("dni");
            String phone = (String)user.get("phono");

            txtUser.setText(username);
            txtPassword.setText(password);
            txtEmail.setText(email);
            txtFirstName.setText(firstname);
            txtLastName.setText(lastname);
            txtBirthday.setText(birthday);
            txtAdress.setText(address);
            txtDNI.setText(dni);
            txtPhone.setText(phone);

            byte []photo = null;
            ParseFile userPhoto = user.getParseFile("photo");
            if(userPhoto!=null){
                userPhoto.getDataInBackground(new GetDataCallback() {
                    @Override
                    public void done(byte[] data, com.parse.ParseException e) {
                        if (e != null) {
                            e.printStackTrace();
                        } else {
                            try {
                                Bitmap bm = BitmapFactory.decodeByteArray(data, 0, data.length);
                                imgPhoto.setImageBitmap(bm);
                            } catch (Exception ex) {
                                ex.printStackTrace();
                            }
                        }
                    }
                });
            }
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_main_account, container, false);
            imgPhoto = (ImageView)rootView.findViewById(R.id.updaccount_img_pick);
            txtUser = (EditText)rootView.findViewById(R.id.txtUpdAccountUser);
            txtPassword = (EditText)rootView.findViewById(R.id.txtUpdAccountPassword);
            txtEmail = (EditText)rootView.findViewById(R.id.txtUpdAccountEmail);
            txtFirstName = (EditText)rootView.findViewById(R.id.txtUpdAccountFirstName);
            txtLastName = (EditText)rootView.findViewById(R.id.txtUpdAccountLastName);
            txtBirthday = (EditText)rootView.findViewById(R.id.txtUpdAccountBirthday);
            txtAdress = (EditText)rootView.findViewById(R.id.txtUpdAccountAddress);
            txtDNI = (EditText)rootView.findViewById(R.id.txtUpdAccountDNI);
            txtPhone = (EditText)rootView.findViewById(R.id.txtUpdAccountPhone);
            btnUpdate = (Button)rootView.findViewById(R.id.btnSaveUpdAccount);

            txtUser.setEnabled(false);
            txtEmail.setEnabled(false);
            populateFields();

            btnUpdate.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String username = txtUser.getText().toString().trim();
                    String password = txtPassword.getText().toString().trim();
                    String email = txtEmail.getText().toString().trim();
                    String firstname = txtFirstName.getText().toString().trim();
                    String lastname = txtLastName.getText().toString().trim();
                    String birthday = txtBirthday.getText().toString().trim();
                    String address = txtAdress.getText().toString().trim();
                    String dni = txtDNI.getText().toString().trim();
                    String phone = txtPhone.getText().toString().trim();


                    if (username.isEmpty() || email.isEmpty() || firstname.isEmpty() || lastname.isEmpty() || birthday.isEmpty() || dni.isEmpty() || phone.isEmpty() || address.isEmpty()) {
                        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                        builder.setMessage("All field are required!")
                                .setTitle("Updae Account Error")
                                .setPositiveButton(android.R.string.ok, null);
                        AlertDialog dialog = builder.create();
                        dialog.show();
                    } else {
                        showProgressBar(true);

                        final ParseUser updUser = ParseUser.getCurrentUser();
                        //updUser.setUsername(username);
                        if(!password.equals("")){
                            updUser.setPassword(password);
                        }
                        //updUser.setEmail(email);
                        updUser.put("name", firstname);
                        updUser.put("lastname", lastname);
                        updUser.put("birthday", birthday);
                        updUser.put("address", address);
                        updUser.put("dni", dni);
                        updUser.put("phono", phone);
                        //updUser.put("emailVerified", true);

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
                                    updUser.put("photo", file);

                                    updUser.saveInBackground(new SaveCallback() {
                                        @Override
                                        public void done(com.parse.ParseException e) {
                                            showProgressBar(false);

                                            if (e == null) {
                                                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                                                builder.setMessage("Account has been updated!")
                                                        .setTitle("Updae Account")
                                                        .setPositiveButton(android.R.string.ok, null);
                                                AlertDialog dialog = builder.create();
                                                dialog.show();
                                            } else {
                                                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                                                builder.setMessage(e.getMessage())
                                                        .setTitle("Updae Account Error")
                                                        .setPositiveButton(android.R.string.ok, null);
                                                AlertDialog dialog = builder.create();
                                                dialog.show();
                                            }
                                        }
                                    });
                                }else{
                                    AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                                    builder.setMessage(e2.getMessage())
                                            .setTitle("Updae Account Error")
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
                barProgressDialog = ProgressDialog.show(getActivity(), "Please wait ...", "Updating account ...", true);
                barProgressDialog.setCancelable(false);
            }else{
                if(barProgressDialog!=null){
                    barProgressDialog.dismiss();
                    barProgressDialog=null;
                }
            }
        }
    }
    public static class AdoptFragment extends Fragment {

        private ArrayList<ParseObject> lstPetAge;
        private ArrayList<ParseObject> lstPetSex;
        private ArrayList<ParseObject> lstPetSize;
        private ArrayList<ParseObject> lstPetType;
        private Spinner spinPetType, spinPetAge, spinPetSize, spinPetSex;
        private Button btnSearch;
        private ImageView btnAdoptList;
        ProgressDialog barProgressDialog;

        public AdoptFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_main_adopt, container, false);

            spinPetType = (Spinner)rootView.findViewById(R.id.spinSearchPetType);
            spinPetAge = (Spinner)rootView.findViewById(R.id.spinSearchPetAge);
            spinPetSize = (Spinner)rootView.findViewById(R.id.spinSearchPetSize);
            spinPetSex = (Spinner)rootView.findViewById(R.id.spinSearchPetSex);
            btnAdoptList = (ImageView)rootView.findViewById(R.id.btnListAdoptPet);

            btnAdoptList.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    ((MainActivity)getActivity()).selectFragment(8,null);
                }
            });

            btnSearch = (Button)rootView.findViewById(R.id.btnSearchPet);
            btnSearch.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    showProgressBar(true);
                    ParseQuery<ParseObject> query = ParseQuery.getQuery("Pet");
                    query.include("giveUser");
                    query.include("newOwner");

                    ParseObject selectedPetAge = (ParseObject)spinPetAge.getSelectedItem();
                    ParseObject selectedPetSex = (ParseObject)spinPetSex.getSelectedItem();
                    ParseObject selectedPetSize = (ParseObject)spinPetSize.getSelectedItem();
                    ParseObject selectedPetType = (ParseObject)spinPetType.getSelectedItem();

                    if(selectedPetAge!=null && selectedPetAge.getObjectId()!=null){
                        query.whereEqualTo("petAge", selectedPetAge);
                    }

                    if(selectedPetSex!=null && selectedPetSex.getObjectId()!=null){
                        query.whereEqualTo("petSex", selectedPetSex);
                    }

                    if(selectedPetSize!=null && selectedPetSize.getObjectId()!=null){
                        query.whereEqualTo("petSize", selectedPetSize);
                    }

                    if(selectedPetType!=null && selectedPetType.getObjectId()!=null){
                        query.whereEqualTo("petType", selectedPetType);
                    }


                    query.findInBackground(new FindCallback<ParseObject>() {
                        @Override
                        public void done(List<ParseObject> objects, com.parse.ParseException e) {
                            showProgressBar(false);
                            if (e == null) {
                                if(objects!=null && objects.size()>0){
                                    ((MainActivity)getActivity()).selectFragment(5, objects);
                                    ((MainActivity)getActivity()).restoreActionBar();
                                }else{
                                    AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                                    builder.setMessage("No results")
                                            .setTitle("Message")
                                            .setPositiveButton(android.R.string.ok, null);
                                    AlertDialog dialog = builder.create();
                                    dialog.show();
                                }
                            } else {
                                Log.d(getClass().getSimpleName(), "Error: " + e.getMessage());
                            }
                        }
                    });
                }
            });

            MainActivity act = ((MainActivity)getActivity());
            lstPetAge = new ArrayList<ParseObject>();
            lstPetSex = new ArrayList<ParseObject>();
            lstPetSize = new ArrayList<ParseObject>();
            lstPetType = new ArrayList<ParseObject>();

            act.loadPetOptionList(spinPetAge, lstPetAge, "petAge", "petAgeDescription");
            act.loadPetOptionList(spinPetSex, lstPetSex, "petSex", "petSexDescription");
            act.loadPetOptionList(spinPetSize, lstPetSize, "petSize", "petSizeDescription");
            act.loadPetOptionList(spinPetType, lstPetType, "PetType", "petTypeName");

            return rootView;
        }

        public void showProgressBar(boolean state){
            if(state){
                barProgressDialog = ProgressDialog.show(getActivity(), "Please wait ...",	"Searching ...", true);
                barProgressDialog.setCancelable(false);
            }else{
                if(barProgressDialog!=null){
                    barProgressDialog.dismiss();
                    barProgressDialog=null;
                }
            }
        }
    }
    public static class GiveFragment extends Fragment {

        private ImageView imgPickPetPhoto;
        private EditText txtPetName, txtPetStory, txtPetRace;
        private Spinner spinPetType, spinPetAge, spinPetSize, spinPetSex;
        private String petID;

        private Button btnGive;

        private ArrayList<ParseObject> lstPetAge;
        private ArrayList<ParseObject> lstPetSex;
        private ArrayList<ParseObject> lstPetSize;
        private ArrayList<ParseObject> lstPetType;

        ProgressDialog barProgressDialog;

        public GiveFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_main_give, container, false);

            imgPickPetPhoto = (ImageView)rootView.findViewById(R.id.give_img_pick);
            txtPetName = (EditText)rootView.findViewById(R.id.txtGivePetName);
            txtPetStory = (EditText)rootView.findViewById(R.id.txtGivePetDescription);
            txtPetRace = (EditText)rootView.findViewById(R.id.txtGivePetRace);
            spinPetType = (Spinner)rootView.findViewById(R.id.spin_give_pettype);
            spinPetAge = (Spinner)rootView.findViewById(R.id.spin_give_petage);
            spinPetSize = (Spinner)rootView.findViewById(R.id.sping_give_petsize);
            spinPetSex = (Spinner)rootView.findViewById(R.id.spin_give_petsex);

            btnGive = (Button)rootView.findViewById(R.id.btnSaveGivePet);

            btnGive.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String petName = txtPetName.getText().toString().trim();
                    String petStory = txtPetStory.getText().toString().trim();
                    String petRace = txtPetRace.getText().toString().trim();

                    ParseObject parseAge = (ParseObject)spinPetAge.getSelectedItem();
                    ParseObject parseSex = (ParseObject)spinPetSex.getSelectedItem();
                    ParseObject parseSize = (ParseObject)spinPetSize.getSelectedItem();
                    ParseObject parseType = (ParseObject)spinPetType.getSelectedItem();

                    if(petName.isEmpty() || petStory.isEmpty() || petRace.isEmpty()  || parseAge.getObjectId()==null || parseSex.getObjectId()==null || parseSize.getObjectId()==null || parseType.getObjectId()==null){
                        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                        builder.setMessage("All field are required!")
                                .setTitle("Give adoption")
                                .setPositiveButton(android.R.string.ok, null);
                        AlertDialog dialog = builder.create();
                        dialog.show();
                    }else{
                        showProgressBar(true);

                        final ParseObject newPet = new ParseObject("Pet");
                        newPet.put("petName", petName);
                        newPet.put("petStory", petStory);
                        newPet.put("petRace", petRace);
                        newPet.put("petAge", parseAge);
                        newPet.put("petSex", parseSex);
                        newPet.put("petSize", parseSize);
                        newPet.put("petType", parseType);
                        newPet.put("giveUser", ParseUser.getCurrentUser());
                        newPet.put("petStatus", "0");

                        imgPickPetPhoto.buildDrawingCache();
                        ByteArrayOutputStream stream = new ByteArrayOutputStream();
                        Bitmap bm = imgPickPetPhoto.getDrawingCache();
                        bm.compress(Bitmap.CompressFormat.PNG, 100, stream);
                        byte[] bfile = stream.toByteArray();
                        final ParseFile file = new ParseFile(((int)(Math.random()*100000)) + ".png", bfile);
                        file.saveInBackground(new SaveCallback() {
                            @Override
                            public void done(com.parse.ParseException e2) {
                                if(e2 == null){
                                    newPet.put("petPhoto", file);

                                    newPet.saveInBackground(new SaveCallback() {
                                        @Override
                                        public void done(com.parse.ParseException e) {
                                            showProgressBar(false);

                                            if (e == null) {
                                                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                                                builder.setMessage("Pet saved!")
                                                        .setTitle("Publish")
                                                        .setPositiveButton(android.R.string.ok, null);
                                                AlertDialog dialog = builder.create();
                                                dialog.show();
                                                ((MainActivity) getActivity()).goToGiveList(null);
                                            } else {
                                                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                                                builder.setMessage(e.getMessage())
                                                        .setTitle("Publish")
                                                        .setPositiveButton(android.R.string.ok, null);
                                                AlertDialog dialog = builder.create();
                                                dialog.show();
                                            }
                                        }
                                    });
                                }else{
                                    AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                                    builder.setMessage(e2.getMessage())
                                            .setTitle("Publish Error")
                                            .setPositiveButton(android.R.string.ok, null);
                                    AlertDialog dialog = builder.create();
                                    dialog.show();
                                }
                            }
                        });

                    }

                }
            });

            if(petID!=null) loadPetData();

            MainActivity act = ((MainActivity)getActivity());

            lstPetAge = new ArrayList<ParseObject>();
            lstPetSex = new ArrayList<ParseObject>();
            lstPetSize = new ArrayList<ParseObject>();
            lstPetType = new ArrayList<ParseObject>();

            act.loadPetOptionList(spinPetAge, lstPetAge, "petAge", "petAgeDescription");
            act.loadPetOptionList(spinPetSex, lstPetSex, "petSex", "petSexDescription");
            act.loadPetOptionList(spinPetSize, lstPetSize, "petSize", "petSizeDescription");
            act.loadPetOptionList(spinPetType, lstPetType, "PetType", "petTypeName");


            return rootView;
        }

        public void showProgressBar(boolean state){
            if(state){
                barProgressDialog = ProgressDialog.show(getActivity(), "Please wait ...",	"Publishing pet ...", true);
                barProgressDialog.setCancelable(false);
            }else{
                if(barProgressDialog!=null){
                    barProgressDialog.dismiss();
                    barProgressDialog=null;
                }
            }
        }

        public void loadPetData(){

        }
    }
    public static class CampaignFragment extends Fragment {
        public static ListView lvCampain;

        public ArrayList<Campaign> lstCampaign;

        public CampaignFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_main_campaign, container, false);
            lvCampain = (ListView)rootView.findViewById(R.id.lvCampaign);


            lstCampaign = new ArrayList<Campaign>();
            //lstCampaign.add(new Campaign("1", "Beds for pets", "Comfortable pet beds now!", "http://animales.mercadolibre.com.pe/perros-casas-y-camas/", R.drawable.campaign_1, null));
            //lstCampaign.add(new Campaign("2", "Pet food", "delicious food for pets", "http://www.petfoodindustry.com/", R.drawable.campaign_2, null));
            //lstCampaign.add(new Campaign("3", "Pet Spa", "sauna, hair, bath, shaping claws, etc", "https://es-la.facebook.com/VeterinariaPetSpa", R.drawable.campaign_3, null));

            /*String []itemPetName = new String[lstCampaign.length];
            for(int i=0; i<lstCampaign.length; i++){
                itemPetName[i]=lstCampaign[i].getCampaignName();
            }*/

            lvCampain.setAdapter(new CampaignAdapter(getActivity(),lstCampaign));
            lvCampain.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view,
                                        int position, long id) {
                    Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(lstCampaign.get(position).getCampaignLink()));
                    startActivity(browserIntent);
                }
            });
            loadCampaignList();
            return rootView;
        }

        public void loadCampaignList(){
            ParseQuery<ParseObject> query = ParseQuery.getQuery("Campaign");

            query.findInBackground(new FindCallback<ParseObject>() {
                @Override
                public void done(List<ParseObject> objects, com.parse.ParseException e) {
                    if (e == null) {
                        lstCampaign.clear();
                        for (ParseObject obj : objects) {
                            Campaign campaign = new Campaign();
                            campaign.setObjectId(obj.getObjectId());
                            campaign.setCampaignName(obj.getString("campaignName"));
                            campaign.setCampaignShortDesc(obj.getString("campaignShortDesc"));
                            campaign.setCampaignLink(obj.getString("campaignLink"));
                            campaign.setCampaignPhoto(obj.getParseFile("campaignPhoto"));
                            lstCampaign.add(campaign);
                        }
                        ((CampaignAdapter)lvCampain.getAdapter()).notifyDataSetChanged();
                    } else {
                        Log.d(getClass().getSimpleName(), "Error: " + e.getMessage());
                    }
                }
            });
        }
    }
    public static class AdoptResultFragment extends Fragment {

        public static ListView lvAdoptResult;
        public ArrayList<ParseObject> lstPet;
        public AdoptResultFragment() {
        }

        public void setLista(ArrayList<ParseObject> lst){
            lstPet = lst;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_main_adopt_result, container, false);
            lvAdoptResult = (ListView)rootView.findViewById(R.id.lvAdoptResult);


            if(lstPet!=null && lstPet.size()>0){
                lvAdoptResult.setAdapter(new AdoptResultAdapter(getActivity(),lstPet));
                lvAdoptResult.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view,
                                            int position, long id) {
                        ((MainActivity) getActivity()).selectFragment(6, (ParseObject)lstPet.get(position));
                    }
                });
            }





            return rootView;
        }
    }


    public static class MyRequestFragment extends Fragment {

        public static ListView lvAdoptList;
        public ArrayList<ParseObject> lstRequest, lstPet;
        public MyRequestFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_main_adopt_myrequest, container, false);
            lvAdoptList = (ListView)rootView.findViewById(R.id.lvAdoptList);
            lstPet = new ArrayList<ParseObject>();
            lstRequest = new ArrayList<ParseObject>();

            ParseQuery<ParseObject> query = ParseQuery.getQuery("AdoptRequest");
            query.include("reqPet");
            query.include("reqPet.giveUser");
            query.include("reqUser");
            query.whereEqualTo("reqUser", ParseUser.getCurrentUser());
            query.findInBackground(new FindCallback<ParseObject>() {
                @Override
                public void done(List<ParseObject> objects, com.parse.ParseException e) {

                    if (e == null) {
                        if(objects!=null && objects.size()>0){
                            lstPet.clear();
                            for(ParseObject obj : objects){
                                ParseObject reqPet = obj.getParseObject("reqPet");
                                lstPet.add(reqPet);
                                lstRequest.add(obj);
                            }
                            ((AdoptResultAdapter)lvAdoptList.getAdapter()).notifyDataSetChanged();
                        }
                    } else {
                        Log.d(getClass().getSimpleName(), "Error: " + e.getMessage());
                    }
                }
            });


                lvAdoptList.setAdapter(new AdoptResultAdapter(getActivity(),lstPet));
                lvAdoptList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view,
                                            final int position, long id) {

                        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                        builder.setMessage("Are you sure to cancel your request?")
                                .setTitle("Message")
                                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                        lstRequest.get(position).deleteInBackground();
                                        lstPet.remove(position);
                                        lstRequest.remove(position);
                                        ((AdoptResultAdapter)lvAdoptList.getAdapter()).notifyDataSetChanged();
                                    }
                                })
                                .setNegativeButton(android.R.string.no, null);
                        AlertDialog dialog = builder.create();
                        dialog.show();


                    }
                });





            return rootView;
        }
    }

    public static class PetRequestFragment extends Fragment {
        private TextView txtPetName, txtPetOwner;
        private ImageView imgPet;
        public static ListView lvRequest;
        public ArrayList<ParseObject> lstRequest;
        ParseObject pet;
        public PetRequestFragment() {
        }

        public void setPet(ParseObject pet){
            this.pet = pet;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_main_petrequest, container, false);
            lvRequest = (ListView)rootView.findViewById(R.id.lvPetRequest);
            txtPetName = (TextView)rootView.findViewById(R.id.txtGiveReqPetName);
            txtPetOwner = (TextView)rootView.findViewById(R.id.txtGiveReqPetOwner);
            imgPet = (ImageView)rootView.findViewById(R.id.imgPetRequest);

            ParseFile fimg = pet.getParseFile("petPhoto");
            if(fimg!=null){
                fimg.getDataInBackground(new GetDataCallback() {
                    @Override
                    public void done(byte[] data, ParseException e) {
                        if (e != null) {
                            e.printStackTrace();
                        } else {
                            try {
                                Bitmap bm = BitmapFactory.decodeByteArray(data, 0, data.length);
                                imgPet.setImageBitmap(bm);
                            } catch (Exception ex) {
                                ex.printStackTrace();
                            }
                        }
                    }
                });
            }

            txtPetName.setText((String) pet.get("petName"));
            ParseObject giveUser = pet.getParseObject("giveUser");
            ParseObject newOwner = pet.getParseObject("newOwner");
            if(newOwner!=null){
                String ownerName = "";
                for (String key : newOwner.keySet()) {
                    if(key.equals("name")){
                        ownerName+=newOwner.get(key);
                    }else if(key.equals("lastname")){
                        ownerName+=" "+newOwner.get(key);
                    }
                    //System.out.println("Key-> "+key + ":" + giveUser.get(key));
                }
                txtPetOwner.setText("Adopted by "+ownerName);
            }else if(giveUser!=null){
                String ownerName = "";
                for (String key : giveUser.keySet()) {
                    if(key.equals("name")){
                        ownerName+=giveUser.get(key);
                    }else if(key.equals("lastname")){
                        ownerName+=" "+giveUser.get(key);
                    }
                    //System.out.println("Key-> "+key + ":" + giveUser.get(key));
                }
                txtPetOwner.setText(ownerName);
            }

            lstRequest = new ArrayList<ParseObject>();

            ParseQuery<ParseObject> query = ParseQuery.getQuery("AdoptRequest");
            query.include("reqPet");
            query.include("reqPet.giveUser");
            query.include("reqUser");
            query.whereEqualTo("reqPet", pet);
            query.findInBackground(new FindCallback<ParseObject>() {
                @Override
                public void done(List<ParseObject> objects, com.parse.ParseException e) {

                    if (e == null) {
                        if (objects != null && objects.size() > 0) {
                            lstRequest.clear();
                            for (ParseObject obj : objects) {
                                lstRequest.add(obj);
                            }
                            ((RequestAdapter) lvRequest.getAdapter()).notifyDataSetChanged();
                        }
                    } else {
                        Log.d(getClass().getSimpleName(), "Error: " + e.getMessage());
                    }
                }
            });


            lvRequest.setAdapter(new RequestAdapter(getActivity(),lstRequest));
            if(pet.getString("petStatus")!=null && pet.getString("petStatus").equals("0")){
                lvRequest.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view,
                                            final int position, long id) {

                        final ParseObject newOwner = (ParseObject)lstRequest.get(position).get("reqUser");

                        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                        builder.setMessage("Are you sure to approve this request?")
                                .setTitle("Message")
                                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                        pet.put("newOwner", newOwner);
                                        pet.put("petStatus", "1");
                                        pet.saveInBackground();
                                        txtPetOwner.setText("Adopt by " + newOwner.get("name") + " " + newOwner.get("lastname"));
                                        ((RequestAdapter)lvRequest.getAdapter()).notifyDataSetChanged();
                                    }
                                })
                                .setNegativeButton(android.R.string.no, null);
                        AlertDialog dialog = builder.create();
                        dialog.show();


                    }
                });
            }





            return rootView;
        }
    }

    public static class PetFragment extends Fragment {
        private TextView txtPetName, txtPetAge, txtPetRace, txtPetOwner, txtPetSize, txtPetDescription;
        private ImageView imgPhoto;
        private Button btnAdopt;
        private static ParseObject pet;
        ProgressDialog barProgressDialog;
        public PetFragment() {
        }

        public void setPet(ParseObject pet){
            this.pet = pet;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_main_pet, container, false);
            txtPetName = (TextView)rootView.findViewById(R.id.pet_petname);
            txtPetAge = (TextView)rootView.findViewById(R.id.pet_petage);
            txtPetRace = (TextView)rootView.findViewById(R.id.pet_petrace);
            txtPetOwner = (TextView)rootView.findViewById(R.id.pet_owner);
            txtPetSize = (TextView)rootView.findViewById(R.id.pet_petsize);
            txtPetDescription = (TextView)rootView.findViewById(R.id.pet_petdescription);
            imgPhoto = (ImageView)rootView.findViewById(R.id.img_pet_petphoto);

            btnAdopt = (Button)rootView.findViewById(R.id.btnAdoptPet);

            btnAdopt.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    showProgressBar(true);
                    final ParseObject newReq = new ParseObject("AdoptRequest");
                    newReq.put("reqPet", pet);
                    newReq.put("reqUser", ParseUser.getCurrentUser());
                    newReq.put("reqStatus", "0");
                    newReq.saveInBackground(new SaveCallback() {
                        @Override
                        public void done(com.parse.ParseException e) {
                            showProgressBar(false);

                            if (e == null) {
                                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                                builder.setMessage("Request has been sent!")
                                        .setTitle("Message")
                                        .setPositiveButton(android.R.string.ok, null);
                                AlertDialog dialog = builder.create();
                                dialog.show();
                                btnAdopt.setEnabled(false);
                                btnAdopt.setText("Sent!");
                            } else {
                                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                                builder.setMessage(e.getMessage())
                                        .setTitle("Request Error")
                                        .setPositiveButton(android.R.string.ok, null);
                                AlertDialog dialog = builder.create();
                                dialog.show();
                            }
                        }
                    });
                }
            });

            btnAdopt.setVisibility(View.GONE);

            if(pet!=null){

                String petName = (String)pet.get("petName");
                String petRace = (String)pet.get("petRace");
                String petStory = (String)pet.get("petStory");
                final String petStatus = (String)pet.get("petStatus");
                ParseFile petPhoto = (ParseFile)pet.get("petPhoto");

                ParseObject petAge = pet.getParseObject("petAge");
                ParseObject petSex = pet.getParseObject("petSex");
                ParseObject petSize = pet.getParseObject("petSize");
                ParseObject petType = pet.getParseObject("petType");


                ParseObject newOwner = pet.getParseObject("newOwner");
                ParseObject giveUer = pet.getParseObject("giveUser");

                if(petStatus!=null && petStatus.equals("0") && giveUer.getObjectId()!=ParseUser.getCurrentUser().getObjectId()){

                    ParseQuery<ParseObject> query = ParseQuery.getQuery("AdoptRequest");
                    query.include("reqPet");
                    query.include("reqUser");
                    query.whereEqualTo("reqStatus", "0");
                    query.whereEqualTo("reqPet", pet);
                    query.whereEqualTo("reqUser", ParseUser.getCurrentUser());


                    query.findInBackground(new FindCallback<ParseObject>() {
                        @Override
                        public void done(List<ParseObject> objects, com.parse.ParseException e) {

                            if (e == null) {
                                if(objects!=null && objects.size()>0){
                                            btnAdopt.setVisibility(View.VISIBLE);
                                            btnAdopt.setEnabled(false);
                                            btnAdopt.setText("Sent!");
                                    }else{
                                        btnAdopt.setVisibility(View.VISIBLE);
                                    }
                            } else {
                                Log.d(getClass().getSimpleName(), "Error: " + e.getMessage());
                            }
                        }
                    });









                }



                if(petName!=null)txtPetName.setText(petName);
                if(petAge!=null)txtPetAge.setText("Age: "+petAge.get("petAgeDescription"));
                if(petRace!=null)txtPetRace.setText("Race: "+petRace);

                if(newOwner!=null){
                    String ownerName = "";
                    for (String key : newOwner.keySet()) {
                        if(key.equals("name")){
                            ownerName+=newOwner.get(key);
                        }else if(key.equals("lastname")){
                            ownerName+=" "+newOwner.get(key);
                        }
                        //System.out.println("Key-> "+key + ":" + giveUser.get(key));
                    }
                    txtPetOwner.setText("Adopted by "+ownerName);
                }else{
                    String ownerName = "";
                    for (String key : giveUer.keySet()) {
                        if(key.equals("name")){
                            ownerName+=giveUer.get(key);
                        }else if(key.equals("lastname")){
                            ownerName+=" "+giveUer.get(key);
                        }
                        //System.out.println("Key-> "+key + ":" + giveUser.get(key));
                    }
                    txtPetOwner.setText("Owner: "+ownerName);
                }

                if(petSize!=null)txtPetSize.setText("Size: "+petSize.get("petSizeDescription"));
                if(petStory!=null)txtPetDescription.setText(petStory);

                if(petPhoto!=null){
                    petPhoto.getDataInBackground(new GetDataCallback() {
                        @Override
                        public void done(byte[] data, com.parse.ParseException e) {
                            if (e != null) {
                                e.printStackTrace();
                            } else {
                                try {
                                    Bitmap bm = BitmapFactory.decodeByteArray(data, 0, data.length);
                                    imgPhoto.setImageBitmap(bm);
                                } catch (Exception ex) {
                                    ex.printStackTrace();
                                }
                            }
                        }
                    });
                }

            }


            return rootView;
        }
        public void showProgressBar(boolean state){
            if(state){
                barProgressDialog = ProgressDialog.show(getActivity(), "Please wait ...",	"Sending request ...", true);
                barProgressDialog.setCancelable(false);
            }else{
                if(barProgressDialog!=null){
                    barProgressDialog.dismiss();
                    barProgressDialog=null;
                }
            }
        }

    }

    public static class GiveListFragment extends Fragment {

        public static ListView lvGiveList;
        public ArrayList<ParseObject> lstGivePet;
        public GiveListFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_main_give_list, container, false);
            lvGiveList = (ListView)rootView.findViewById(R.id.lvGiveList);


            lstGivePet = new ArrayList<ParseObject>();
            lvGiveList.setAdapter(new AdoptResultAdapter(getActivity(), lstGivePet));

            lvGiveList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view,
                                        int position, long id) {
                    ((MainActivity)getActivity()).selectFragment(9, lstGivePet.get(position));
                }
            });

            getGiveLst();

            return rootView;
        }

        public void getGiveLst(){
            lstGivePet.clear();

            ParseQuery<ParseObject> query = ParseQuery.getQuery("Pet");
            query.include("giveUser");
            query.include("newOwner");
            query.whereEqualTo("giveUser", ParseUser.getCurrentUser());
            query.findInBackground(new FindCallback<ParseObject>() {
                @Override
                public void done(List<ParseObject> objects, com.parse.ParseException e) {
                    if (e == null) {
                        lstGivePet.clear();
                        for (ParseObject obj : objects) {
                            lstGivePet.add(obj);
                        }
                        ((AdoptResultAdapter)lvGiveList.getAdapter()).notifyDataSetChanged();
                    } else {
                        Log.d(getClass().getSimpleName(), "Error: " + e.getMessage());
                    }
                }
            });
        }
    }

}
