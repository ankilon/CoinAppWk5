package com.example.cryptobag;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.widget.Button;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.LinkedList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    String textToDisplay = "Here is my text";
    private final List<Coin> mCoinList = Coin.getCoins();
    // Put initial data into the word list.

    private RecyclerView currencyList;
    private CoinListAdapter cAdapter;
    private RecyclerView.LayoutManager layoutManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Get a handle to the RecyclerView.
        currencyList = findViewById(R.id.coinList);
        if(currencyList != null) {
            // Create an adapter and supply the data to be displayed.
            cAdapter = new CoinListAdapter(this, mCoinList);
            // Connect the adapter with the RecyclerView.
            currencyList.setAdapter(cAdapter);
            // Give the RecyclerView a default layout manager.
            currencyList.setLayoutManager(new LinearLayoutManager(this));
        }
    }


}
