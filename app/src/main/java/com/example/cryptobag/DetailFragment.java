package com.example.cryptobag;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ScrollView;
import android.widget.TextView;

import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link DetailFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DetailFragment extends Fragment {
    private static final String TAG = "DetailFragment";
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private final List<Coin> mCoinList = Coin.getCoins();
    Coin coin;
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public DetailFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment DetailFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static DetailFragment newInstance(String param1, String param2) {
        DetailFragment fragment = new DetailFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }




    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_detail, container, false);

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        Bundle extra = getArguments();
        coin = mCoinList.get(extra.getInt("pos"));
        Log.d(TAG, "Check if coin exists: " + Boolean.toString(coin!=null));

        Coin selectedCoin = coin;
        View view = getView();
        TextView name = view.findViewById(R.id.name);
        name.setText(selectedCoin.getName());
        TextView symbol = view.findViewById(R.id.symbol);
        symbol.setText(selectedCoin.getSymbol());
        TextView value = view.findViewById(R.id.value);
        value.setText(Double.toString(selectedCoin.getValue()));
        TextView change1h = view.findViewById(R.id.change1h);
        change1h.setText(Double.toString(selectedCoin.getChange1h()));
        TextView change24h = view.findViewById(R.id.change24h);
        change24h.setText(Double.toString(selectedCoin.getChange24h()));
        TextView change7d = view.findViewById(R.id.change7d);
        change7d.setText(Double.toString(selectedCoin.getChange7d()));
        TextView marketcap = view.findViewById(R.id.marketcap);
        marketcap.setText(Double.toString(selectedCoin.getMarketcap()));
        TextView volume = view.findViewById(R.id.volume);
        volume.setText(Double.toString(selectedCoin.getVolume()));
        final String searchvalue = selectedCoin.getName();

        ImageButton search = view.findViewById(R.id.searchButton);
        search.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse("https://www.google.com.au/search?q="+searchvalue+" Cryptocurrency rate"));
                startActivity(intent);
            }
        });
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState){

    }
}
