package com.exopen.binkssound;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import android.annotation.SuppressLint;
import android.os.Bundle;

import com.exopen.binkssound.AlertClass.FlagIntent;
import com.exopen.binkssound.Class.Constant;
import com.exopen.binkssound.Class.SharedPref;
import com.exopen.binkssound.Fragment.Recherche;
import com.exopen.binkssound.Fragment.Home;
import com.exopen.binkssound.Fragment.Profile;
import com.exopen.binkssound.Fragment.Artistes;
import com.exopen.binkssound.Identifiant.LogIn;
import com.exopen.binkssound.Identifiant.SetUp;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {

    private ViewPager2 viewPager;
    private TabLayout tabLayout;
    private ViewPagerAdapter viewPagerAdapter;
    private SharedPref sharedPref;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        sharedPref = new SharedPref(this);
        if (sharedPref.loadNightModeState()){
            //setTheme(R.style.DarkTheme);
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        }
        else AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        viewPager = findViewById(R.id.view_pager);
        tabLayout = findViewById(R.id.id_tablayout);

        viewPagerAdapter = new ViewPagerAdapter(this);
        viewPager.setAdapter(viewPagerAdapter);
        new TabLayoutMediator(tabLayout, viewPager,
                (tab, position) -> {
                    // Définir les titres des onglets
                    switch (position) {
                        case 0:
                            tab.setIcon(R.drawable.accueil_24);
                            tab.setText(getString(R.string.home));
                            break;
                        case 1:
                            tab.setIcon(R.drawable.search_24);
                            tab.setText(R.string.rechercher);
                            break;
                        case 2:
                            tab.setIcon(R.drawable.ic_account_24);
                            tab.setText(getString(R.string.artistes));
                            break;
                        case 3:
                            tab.setIcon(R.drawable.user_24);
                            tab.setText(getString(R.string.profil));
                            break;
                    }
                }).attach();

    }

    private class ViewPagerAdapter extends FragmentStateAdapter {

        public ViewPagerAdapter(FragmentActivity fragmentActivity) {
            super(fragmentActivity);
        }

        @NonNull
        @Override
        public Fragment createFragment(int position) {
            // Créer et retourner les fragments en fonction de la position
            switch (position) {
                case 0:
                    return new Home();
                case 1:
                    return new Recherche();
                case 2:
                    return new Artistes();
                case 3:
                    return new Profile();

                default:
                    return null;
            }
        }

        @Override
        public int getItemCount() {
            // Nombre total d'onglets
            return 4;
        }
    }

    @Override
    protected void onStart() {
        super.onStart();

        FirebaseAuth mAuth= FirebaseAuth.getInstance();
        FirebaseUser user= mAuth.getCurrentUser();

        DatabaseReference uRef= FirebaseDatabase.getInstance().getReference().child(Constant.user);

        if (user== null){
            new FlagIntent(this, LogIn.class);
        }else {
            String uid= mAuth.getCurrentUser().getUid();
           uRef.child(uid).addValueEventListener(new ValueEventListener() {
               @Override
               public void onDataChange(@NonNull DataSnapshot snapshot) {
                   if (!snapshot.exists()){
                       new FlagIntent(MainActivity.this, SetUp.class);
                   }
               }
               @Override
               public void onCancelled(@NonNull DatabaseError error) {}
           });
        }
    }
}