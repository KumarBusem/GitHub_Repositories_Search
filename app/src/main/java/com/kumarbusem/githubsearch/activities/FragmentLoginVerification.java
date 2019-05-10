package com.kumarbusem.githubsearch.activities;

import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v4.app.ActivityOptionsCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.kumarbusem.githubsearch.R;
import com.kumarbusem.githubsearch.utility.Constants;

import static com.kumarbusem.githubsearch.R.layout.fragment_login_verification;

public class FragmentLoginVerification extends Fragment {
    View view;
    ImageView back_button;
    Bundle bundle;
    public FragmentLoginVerification() {
        // Required empty public constructor
    }
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(fragment_login_verification, container,false);
        back_button = view.findViewById(R.id.backButton);
        LinearLayout verifyOTP = view.findViewById(R.id.verifyOTPButton);

        bundle = ActivityOptionsCompat.makeCustomAnimation(getContext(),
                R.anim.fade_in, R.anim.fade_out).toBundle();

        verifyOTP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.e("\n login Button clicked", "before");
                Intent intent = new Intent(Constants.context, MainActivity.class);
                FragmentManager fm = FragmentLoginVerification.this.getFragmentManager();
                fm.popBackStack();
                fm.popBackStack();
                FragmentLoginVerification.this.getActivity().finish();
                FragmentLoginVerification.this.startActivity(intent, bundle);
            }
        });
        back_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager fm = FragmentLoginVerification.this.getFragmentManager();
                fm.popBackStack();
            }
        });
        return view;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }
}