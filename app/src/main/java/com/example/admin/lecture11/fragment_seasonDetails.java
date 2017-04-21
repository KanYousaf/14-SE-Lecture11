package com.example.admin.lecture11;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by Admin on 4/20/2017.
 */

public class fragment_seasonDetails extends Fragment {
    private TextView displayPassedData, displaySeasonDetails;
    public static final String[] Season_Details={"Silicon Valley","Silicon Valley is about Richard Henricks and his company named pied piper",
            "Game of Thrones","Game of Thrones is a fantasy drama",
            "Big Bang Theory","Big Bang Theory is scientific sci-fi drama",
            "Prison Break","Prison Break is about the story of Micheal Scofield and his brother",
            "Citizen Khan","Citizen Khan: Mr. Khan , a pakistani citizen living abroad in UK",
            "Divinci Demons","Divinci Demons: Story about Leonardo Divinci",
            "Mr. Robot","Mr. Robot is about hacker's story and how he wants to take revenge on society",
            "House of Cards","House of Cards is about underwood and his compaign to become president of USA",
            "Sherlock Holmes","Sherlock Holmes: Detective to solve crimes"
    };

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rowView=inflater.inflate(R.layout.fragment_season_detail, container, false);

        displayPassedData=(TextView)rowView.findViewById(R.id.display_pass_data);
        displaySeasonDetails=(TextView)rowView.findViewById(R.id.display_season_detail);
        // receiving data via bundle
        Bundle received_data=getArguments();
        //checking if data received or not
        if(received_data!=null){
            String seasonNameReceived=received_data.getString("season_name");
            Float seasonRateReceived=received_data.getFloat("season_rate");

            displayPassedData.setText("The choosen data is: "+seasonNameReceived+ ": rating: "+seasonRateReceived);

            for(int i=0 ; i< Season_Details.length; i+=2) {
                if (Season_Details[i].equals(seasonNameReceived)) {
                    displaySeasonDetails.setText(Season_Details[i + 1]);
                }
            }
                }else{
            Toast.makeText(getActivity(), "Data Not Found",Toast.LENGTH_SHORT).show();
        }



        return rowView;
    }
}
