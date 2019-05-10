package com.kumarbusem.githubsearch.activities;

import android.app.AlertDialog;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.Window;

import com.kumarbusem.githubsearch.R;
import com.kumarbusem.githubsearch.utility.Constants;


public class LoginActivity extends BaseActivity {


    Fragment myfragmentLoginAnimation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Window window = this.getWindow();
        window.setStatusBarColor(this.getResources().getColor(R.color.mapprr_blue));

        Constants.context = LoginActivity.this;
        myfragmentLoginAnimation = new FragmentLoginAnimation();
        FragmentLoginAnimationClick();

    }

    public void FragmentLoginAnimationClick() {

        FragmentManager fm = getFragmentManager();
        FragmentTransaction fragmentTransaction = fm.beginTransaction();
        fragmentTransaction.add(R.id.login_fragment_switch, myfragmentLoginAnimation);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();

    }
    @Override
    public void onBackPressed() {
//        super.onBackPressed();
        FragmentManager fm = getFragmentManager();
        if(fm.getBackStackEntryCount() == 1){
            exit_dialog();
        }
        else if(fm.getBackStackEntryCount() == 2){
            fm.popBackStack();
        }
    }
    public void exit_dialog(){
        AlertDialog.Builder builder1 = new AlertDialog.Builder(Constants.context);
        builder1.setMessage("Do you want to Exit?");
        builder1.setCancelable(true);
        builder1.setPositiveButton(
                "Yes",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        LoginActivity.this.finishActivity();
                        System.exit(0);
                    }
                });

        builder1.setNegativeButton(
                "No",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });

        AlertDialog alert11 = builder1.create();
        alert11.show();
    }



    public void finishActivity(){
        finish();
    }
}

