package pe.adoptapet.adoptapet.view;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.support.v4.widget.DrawerLayout;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import pe.adoptapet.adoptapet.R;
import pe.adoptapet.adoptapet.model.AdoptResultAdapter;
import pe.adoptapet.adoptapet.model.Campaign;
import pe.adoptapet.adoptapet.model.CampaignAdapter;
import pe.adoptapet.adoptapet.model.Constants;
import pe.adoptapet.adoptapet.model.Pet;


public class MainActivity extends ActionBarActivity
        implements NavigationDrawerFragment.NavigationDrawerCallbacks {

    /**
     * Fragment managing the behaviors, interactions and presentation of the navigation drawer.
     */
    private NavigationDrawerFragment mNavigationDrawerFragment;

    public Pet []lstPet;
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

        lstPet = new Pet[10];
        lstPet[0] = new Pet("1", "Antón", "Cat", "Luis Valencia", "1", "Medium", "Siamese", "0 - 1 year", null, R.drawable.cat_profile, "Sample of description");
        lstPet[1] = new Pet("2", "Eby", "Dog", "Tomás Aquino", "1", "Small", "Retriever", "0 - 1 year", null, R.drawable.dog_profile, "Sample of description");

        lstPet[2] = new Pet("3", "Dexter", "Cat", "Rodrigo Monsup", "1", "Big", "Coon", "2 - 3 years", null, R.drawable.cat2_profile, "Sample of description");
        lstPet[3] = new Pet("4", "Eko", "Dog", "Flor Gutierrezz", "1", "Small", "Caniche", "2 - 3 years", null, R.drawable.dog2_profile, "Sample of description");

        lstPet[4] = new Pet("5", "Fumet", "Cat", "Estela Butters", "1", "Medium", "Exotic", "0 - 1 year", null, R.drawable.cat3_profile, "Sample of description");

        lstPet[5] = new Pet("6", "Goldo", "Dog", "Woolder Apologeo", "1", "Big", "Tizu", "0 - 1 year", null, R.drawable.dog3_profile, "Sample of description");
        lstPet[6] = new Pet("7", "Halley", "Cat", "Roberto Ginoccio", "1", "Medium", "Russian", "4 - more", null, R.drawable.cat4_profile, "Sample of description");
        lstPet[7] = new Pet("8", "Jara", "Dog", "Miriam Palacios", "1", "big", "embroke", "0 - 1 year", null, R.drawable.dog4_profile, "Sample of description");
        lstPet[8] = new Pet("9", "Laceyr", "Cat", "Esmeralda Saenz", "1", "Medium", "syberian", "0 - 1 year", null, R.drawable.cat5_profile, "Sample of description");
        lstPet[9] = new Pet("10", "Maki", "Dog", "Carlos Artillera", "1", "Small", "Terrier", "0 - 1 year", null, R.drawable.dog5_profile, "Sample of description");
    }

    @Override
    public void onNavigationDrawerItemSelected(int position) {
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
                mTitle = getString(R.string.title_adopt_results);
                break;
            case 7:
                fg = new PetFragment();
                ((PetFragment)fg).setPet((Pet)obj);
                mTitle = getString(R.string.title_pet_detail);
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

    public void goToAdoptResult(View v){
        onNavigationDrawerItemSelected(5);
        restoreActionBar();
    }

    public void adoptPet(View v){
        Pet pet = PetFragment.pet;
        Toast.makeText(getApplicationContext(),"Your request has been sent!", Toast.LENGTH_LONG).show();
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

        Constants.imageGallerySelected(this,requestCode,resultCode,data,imgPickPhoto);


    }





    public static class HomeFragment extends Fragment {
        public HomeFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_main_home, container, false);
            return rootView;
        }
    }
    public static class AccountFragment extends Fragment {
        public AccountFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_main_account, container, false);
            return rootView;
        }
    }
    public static class AdoptFragment extends Fragment {
        public AdoptFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_main_adopt, container, false);
            return rootView;
        }
    }
    public static class GiveFragment extends Fragment {
        public GiveFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_main_give, container, false);
            return rootView;
        }
    }
    public static class CampaignFragment extends Fragment {
        public static ListView lvCampain;
        public CampaignFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_main_campaign, container, false);
            lvCampain = (ListView)rootView.findViewById(R.id.lvCampaign);


            final Campaign[]lstCampaign = new Campaign[3];
            lstCampaign[0] = new Campaign("1", "Beds for pets", "Comfortable pet beds now!", "http://animales.mercadolibre.com.pe/perros-casas-y-camas/", R.drawable.campaign_1, null);
            lstCampaign[1] = new Campaign("2", "Pet food", "delicious food for pets", "http://www.petfoodindustry.com/", R.drawable.campaign_2, null);
            lstCampaign[2] = new Campaign("3", "Pet Spa", "sauna, hair, bath, shaping claws, etc", "https://es-la.facebook.com/VeterinariaPetSpa", R.drawable.campaign_3, null);

            String []itemPetName = new String[lstCampaign.length];
            for(int i=0; i<lstCampaign.length; i++){
                itemPetName[i]=lstCampaign[i].getCampaignName();
            }

            lvCampain.setAdapter(new CampaignAdapter(getActivity(),lstCampaign,itemPetName));
            lvCampain.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view,
                                        int position, long id) {
                    Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(lstCampaign[position].getCampaignLink()));
                    startActivity(browserIntent);
                }
            });
            return rootView;
        }
    }
    public static class AdoptResultFragment extends Fragment {

        public static ListView lvAdoptResult;
        public AdoptResultFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_main_adopt_result, container, false);
            lvAdoptResult = (ListView)rootView.findViewById(R.id.lvAdoptResult);


            final Pet []lstPet = ((MainActivity)getActivity()).lstPet;
            String []itemPetName = new String[lstPet.length];
            for(int i=0; i<lstPet.length; i++){
                itemPetName[i]=lstPet[i].getPetName();
            }

            lvAdoptResult.setAdapter(new AdoptResultAdapter(getActivity(),lstPet,itemPetName));
            lvAdoptResult.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view,
                                        int position, long id) {
                    ((MainActivity)getActivity()).selectFragment(6, lstPet[position]);
                }
            });



            return rootView;
        }
    }

    public static class PetFragment extends Fragment {
        private TextView txtPetName, txtPetAge, txtPetRace, txtPetOwner, txtPetSize, txtPetDescription;
        private ImageView imgPhoto;
        private static Pet pet;
        public PetFragment() {
        }

        public void setPet(Pet pet){
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

            if(pet!=null){
                if(pet.getPetName()!=null)txtPetName.setText(pet.getPetName());
                if(pet.getPetAge()!=null)txtPetAge.setText("Age: "+pet.getPetAge());
                if(pet.getPetRace()!=null)txtPetRace.setText("Race: "+pet.getPetRace());
                if(pet.getOwnerName()!=null)txtPetOwner.setText("Owner: "+pet.getOwnerName());
                if(pet.getPetSize()!=null)txtPetSize.setText("Size: "+pet.getPetSize());
                if(pet.getPetDescription()!=null)txtPetDescription.setText(pet.getPetDescription());

                if(pet.getPhotoRes()>0)imgPhoto.setImageResource(pet.getPhotoRes());

            }
            return rootView;
        }


    }

}
