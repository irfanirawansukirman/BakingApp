package com.scoproject.bakingapp.ui.activity.home;

import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import com.scoproject.bakingapp.BakingApp;
import com.scoproject.bakingapp.R;
import com.scoproject.bakingapp.ui.fragment.detailstep.DetailStepFragment;
import com.scoproject.bakingapp.ui.fragment.receipe.ReceipeFragment;
import com.scoproject.bakingapp.ui.fragment.step.StepFragment;

import java.util.HashMap;

import javax.inject.Inject;

import butterknife.ButterKnife;
import timber.log.Timber;

/**
 * Created by ibnumuzzakkir on 8/17/17.
 * Android Engineer
 * SCO Project
 */

public class HomeActivity extends AppCompatActivity implements HomeContract.View {
    @Inject
    HomePresenter mHomePresenter;


    private HomeContract.UserActionListener mActionListener;
    private ReceipeFragment mReceipeFragment;
    private StepFragment mStepFragment;
    private HashMap<String, Fragment> fragments;
    private Fragment currentFragment;
    private FragmentManager mFragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ButterKnife.bind(this);
        setupActivityComponent();
        mActionListener = mHomePresenter;
        mHomePresenter.setView(this);
        setupFragment();
        loadFragment("receipeFragment", null,"Receipe Time");
        Timber.tag(getClass().getName());
    }

    private void setupActivityComponent() {
        BakingApp.get()
                .getAppComponent()
                .plus(new HomeModule(this))
                .inject(this);
    }


    @Override
    public void loadFragment(String fragmentId, Bundle bundle, String title) {
        mFragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = mFragmentManager.beginTransaction();
        if(!fragmentId.equals("receipeFragment")){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        currentFragment = fragments.get(fragmentId);
        if(bundle != null){
            currentFragment.setArguments(bundle);
        }
        getSupportActionBar().setTitle(title);
        transaction.replace(R.id.frame_home, currentFragment, fragmentId);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    @Override
    public void setupFragment() {
        mReceipeFragment = new ReceipeFragment();
        mStepFragment = new StepFragment();
        fragments = new HashMap<>();
        fragments.put("receipeFragment", mReceipeFragment);
        fragments.put("stepFragment", mStepFragment);
    }
}
