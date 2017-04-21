package com.example.admin.lecture11;

import android.content.res.Configuration;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.RatingBar;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by Admin on 4/20/2017.
 */

public class fragment_seasonList extends Fragment {
    private ArrayList<String> seasonNameAL;
    private ArrayList<Integer> seasonImagesAL;
    private ListView season_listView;
    private CustomAdapter myOwnAdapter;
    private static final String[] myFavSeasonArray = {
            "Silicon Valley",
            "Game of Thrones",
            "Big Bang Theory",
            "Prison Break",
            "Citizen Khan",
            "Divinci Demons",
            "Mr. Robot",
            "House of Cards",
            "Sherlock Holmes"};

    private int[] myFavSeasonImageArray = {
            R.drawable.siliconvalley,
            R.drawable.gameofthrones,
            R.drawable.bigbangtheory,
            R.drawable.prisonbreak,
            R.drawable.citizenkhan,
            R.drawable.divincidemons,
            R.drawable.mrrobot,
            R.drawable.houseofcards,
            R.drawable.sherlockholmes};


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rowView=inflater.inflate(R.layout.fragment_season_list, container, false);

        season_listView=(ListView)rowView.findViewById(R.id.list_of_seasons);
        //arrayLists for adding data of arrays
        seasonNameAL=new ArrayList<>(Arrays.asList(myFavSeasonArray));

        seasonImagesAL=new ArrayList<>();
        for(int i=0; i< myFavSeasonImageArray.length; i++){
            seasonImagesAL.add(myFavSeasonImageArray[i]);
        }
        myOwnAdapter=new CustomAdapter(getActivity(), seasonNameAL, seasonImagesAL);
        //attaching adapter to listview
        season_listView.setAdapter(myOwnAdapter);
        season_listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String season_name_to_pass=season_listView.getItemAtPosition(position).toString();
                Float season_rate_to_pass=((RatingBar)view.findViewById(R.id.season_rate)).getRating();
                //use bundle to pass data to other fragment
                Bundle args=new Bundle();

                //fragment transaction is responsible for switching to other fragment
                FragmentTransaction transaction=getFragmentManager().beginTransaction();
                fragment_seasonDetails seasonDetails=new fragment_seasonDetails();

                args.putString("season_name", season_name_to_pass);
                args.putFloat("season_rate",season_rate_to_pass);

                seasonDetails.setArguments(args);
                //check if orientation=portrait, then change framelayout with the details
                if(getResources().getConfiguration().orientation
                        == Configuration.ORIENTATION_PORTRAIT){
                    transaction.replace(R.id.container_frame, seasonDetails);
                }else{
                    transaction.replace(R.id.display_season_detail_frame, seasonDetails);
                }
                transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });

        return rowView;
    }
}
