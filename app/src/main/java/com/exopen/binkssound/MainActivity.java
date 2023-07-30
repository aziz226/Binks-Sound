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

import com.exopen.binkssound.Class.SharedPref;
import com.exopen.binkssound.Fragment.CompteFacture;
import com.exopen.binkssound.Fragment.Home;
import com.exopen.binkssound.Fragment.Conseils;
import com.exopen.binkssound.Fragment.Factures;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

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
                            tab.setIcon(R.drawable.user_24);
                            tab.setText(getString(R.string.artistes));
                            break;
                        case 3:
                            tab.setIcon(R.drawable.ic_setting_24);
                            tab.setText(getString(R.string.param_tres));
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
                    return new CompteFacture();
                case 2:
                    return new Factures();
                case 3:
                    return new Conseils();

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

}