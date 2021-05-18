package com.example.paindiaryapp.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.paindiaryapp.Adapter.RecyclerViewAdapterDailyRecord;
import com.example.paindiaryapp.R;
import com.example.paindiaryapp.databinding.FragmentDailyRecordBinding;
import com.example.paindiaryapp.entity.DailyPainRecord;
import com.example.paindiaryapp.viewmodel.DailyPainRecordViewModel;

import java.util.List;


public class DailyRecordFragment extends Fragment {

    private FragmentDailyRecordBinding binding;
    RecyclerViewAdapterDailyRecord adapter;
    RecyclerView recyclerView;
    DailyPainRecordViewModel dailyPainRecordViewModel;
    List < DailyPainRecord > dailyPainRecordRList;


    public DailyRecordFragment ( ) {
        // Required empty public constructor
    }

    @Override
    public void onCreate ( Bundle savedInstanceState ) {
        super.onCreate ( savedInstanceState );
    }

    @Override
    public View onCreateView ( LayoutInflater inflater, ViewGroup container,
                               Bundle savedInstanceState ) {

        binding = FragmentDailyRecordBinding.inflate ( inflater, container, false );
        View view = binding.getRoot ( );

        recyclerView = view.findViewById ( R.id.recyclerView);
        recyclerView.setLayoutManager ( new LinearLayoutManager ( getContext ( ) ) );
        recyclerView.setHasFixedSize ( true );

        adapter = new RecyclerViewAdapterDailyRecord ( dailyPainRecordRList );

        dailyPainRecordViewModel = new
                ViewModelProvider ( requireActivity ( ) ).get ( DailyPainRecordViewModel.class );

        dailyPainRecordViewModel.getAllDailyPainRecord ( ).observe ( getViewLifecycleOwner ( ), new
                Observer < List < DailyPainRecord > > ( ) {
                    @Override
                    public void onChanged ( List < DailyPainRecord > dailypainRecordList ) {
                        if ( dailypainRecordList.size ( ) > 0 ) {
                            dailyPainRecordRList = dailypainRecordList;
                            adapter.setPainRecordList ( dailyPainRecordRList );
                            recyclerView.setAdapter ( adapter );
                        }

                    }
                } );



        return view;
    }

}
/*




        painRecordViewModel1 = new
                ViewModelProvider ( requireActivity ( ) ).get ( PainRecordViewModel.class );

        painRecordViewModel1.getAllPainRecords ( ).observe ( getViewLifecycleOwner ( ), new
                Observer < List < PainRecord > > ( ) {
                    @Override
                    public void onChanged ( List < PainRecord > painRecordList ) {
                        if ( painRecordList.size ( ) > 0 ) {
                            painRecList = painRecordList;
                            adapter.setPainRecordList ( painRecList );
                            recyclerView.setAdapter ( adapter );
                        }

                    }
                } );

        return v;
    }


}

 */
