package com.example.calccarbohydrates;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;


import com.example.calccarbohydrates.account.LoginFragment;
import com.example.calccarbohydrates.ui.products.ListProductsFragment;
import com.example.calccarbohydrates.ui.recipes.RecipesFragment;
import com.example.calccarbohydrates.ui.journal.JournalFragment;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentTransaction;

import android.view.MenuItem;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private DrawerLayout drawer;
    private FragmentTransaction fragmentTransaction;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        firebaseAuth = FirebaseAuth.getInstance();
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new ListProductsFragment()).commit();
        }
    }

    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.nav_login:
                fragmentTransaction = getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.fragment_container, LoginFragment.newInstance(), LoginFragment.class.getSimpleName());
                fragmentTransaction.commit();
                break;
            case R.id.products:
                fragmentTransaction = getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.fragment_container, ListProductsFragment.newInstance(), ListProductsFragment.class.getSimpleName());
                fragmentTransaction.commit();
                break;
            case R.id.journal:
                fragmentTransaction = getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.fragment_container, JournalFragment.newInstance(), JournalFragment.class.getSimpleName());
                fragmentTransaction.commit();
                break;
            case R.id.recipes:
                fragmentTransaction = getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.fragment_container, RecipesFragment.newInstance(), RecipesFragment.class.getSimpleName());
                fragmentTransaction.commit();
                break;
            case R.id.bg_lang:
                LocaleUtils.initialize(this, LocaleUtils.BULGARIAN);
                recreate();
                break;
            case R.id.en_lang:
                LocaleUtils.initialize(this, LocaleUtils.ENGLISH);
                recreate();
                break;
            case R.id.nav_logout:
                firebaseAuth.signOut();
                finish();
                startActivity(new Intent(MainActivity.this, LoginFragment.class));
                break;
        }
        drawer.closeDrawer(GravityCompat.START);

        return false;
    }

}