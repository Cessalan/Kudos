package com.example.kudos;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class navigationFragment extends Fragment {
    private TextView userIdTv;

    public navigationFragment() {
        // Required empty public constructor
    }

//create an intent
public void navIntent(Class destination ){
        Context depart=getContext();
        Intent intent= new Intent(depart,destination);
        startActivity(intent);

}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        // Inflate the layout for this fragment
       View view= inflater.inflate(R.layout.fragment_navigation, container, false);

        // show userId from login activity inside the TextView
        Bundle b= getArguments();
         userIdTv= view.findViewById(R.id.userId);
         /*TO WORK ON*/
        // userIdTv.setText("NAME GOES HERE");

       //get spinner from xml
       Spinner spinner=view.findViewById(R.id.spinner);

       //create adapter
        ArrayAdapter <String> adapter= new ArrayAdapter<String>(
                getContext(),android.R.layout.simple_expandable_list_item_1,getResources().getStringArray(R.array.nav));

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);


        //set adapter on spinner that was created
        spinner.setAdapter(adapter);

        // put a click listener on the spinner
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {


                //go to the right activity based on the user selection
                switch (position){
                    case 1:

                        navIntent(MainActivity.class);
                        break;
                    case 2:

                       // Toast.makeText(getContext(),"1st",Toast.LENGTH_LONG).show();
                        navIntent(LoginActivity.class);

                        break;
                    case 3:

                        navIntent(WritePostActivity.class);
                        break;

                    case 4:

                        //set status to 0 to
                            SharedPreferences preferences=getActivity().getSharedPreferences("status",0);
                            SharedPreferences.Editor editor=preferences.edit();
                            editor.putInt("status",0);
                            editor.apply();

                            //show dialog box
                            Dialog dialog= new Dialog();
                            dialog.setTxt("You are now logged out");
                            dialog.show(getFragmentManager(),"go");

                       break;
                }



            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

       return view;
    }


}
