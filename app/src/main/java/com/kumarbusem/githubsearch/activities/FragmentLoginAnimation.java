package com.kumarbusem.githubsearch.activities;

import android.Manifest;
import android.accounts.Account;
import android.accounts.AccountManager;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewFlipper;

import com.kumarbusem.githubsearch.R;
import com.kumarbusem.githubsearch.utility.Constants;

import static com.kumarbusem.githubsearch.R.layout.fragment_login_animation;

public class FragmentLoginAnimation extends Fragment {
    View view;
    Fragment myFragmentLoginVerification;

    public FragmentLoginAnimation() {
        // Required empty public constructor
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(fragment_login_animation, container, false);
        ViewFlipper flipper = view.findViewById(R.id.view_flipper);
        flipper.setInAnimation(Constants.context, R.anim.view_transition_in_left);
        flipper.setOutAnimation(Constants.context, R.anim.view_transition_out_left);
        myFragmentLoginVerification = new FragmentLoginVerification();
        LinearLayout loginButton = view.findViewById(R.id.loginButton);
        TextView loginPasswordButton = view.findViewById(R.id.loginPasswordButton);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.e("\n login Button clicked", "before");

                TelephonyManager tMgr = (TelephonyManager) Constants.context.getSystemService(Constants.context.TELEPHONY_SERVICE);
                if (ActivityCompat.checkSelfPermission(Constants.context, Manifest.permission.READ_SMS) != PackageManager.PERMISSION_GRANTED
                        && ActivityCompat.checkSelfPermission(Constants.context, Manifest.permission.READ_PHONE_NUMBERS) != PackageManager.PERMISSION_GRANTED
                        && ActivityCompat.checkSelfPermission(Constants.context, Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {

                }

                FragmentManager fm = FragmentLoginAnimation.this.getFragmentManager();
                FragmentTransaction fragmentTransaction = fm.beginTransaction();
                fragmentTransaction.setCustomAnimations(R.animator.fragment_slide_left_enter,
                        R.animator.fragment_slide_left_exit,
                        R.animator.fragment_slide_right_enter,
                        R.animator.fragment_slide_right_exit);
                fragmentTransaction.add(R.id.login_fragment_switch, myFragmentLoginVerification);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        });
        loginPasswordButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager fm = FragmentLoginAnimation.this.getFragmentManager();
                FragmentTransaction fragmentTransaction = fm.beginTransaction();
                fragmentTransaction.setCustomAnimations(R.animator.fragment_slide_left_enter,
                        R.animator.fragment_slide_left_exit,
                        R.animator.fragment_slide_right_enter,
                        R.animator.fragment_slide_right_exit);
                fragmentTransaction.add(R.id.login_fragment_switch, myFragmentLoginVerification);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        });

        return view;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }
}