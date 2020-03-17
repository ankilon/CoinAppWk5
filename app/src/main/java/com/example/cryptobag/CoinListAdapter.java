package com.example.cryptobag;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import java.util.LinkedList;
import java.util.List;

public class CoinListAdapter extends RecyclerView.Adapter<CoinListAdapter.CoinViewHolder>{
    private static final String TAG = "CoinListAdapter";
    //private final LinkedList<String> mWordList;
    private final List<Coin> mCoinList;
    private LayoutInflater mInflater;
    boolean mIsDualPane;
    private Context context;

    public CoinListAdapter(Context context,
                           List<Coin> coinList) {
        mInflater = LayoutInflater.from(context);
        this.mCoinList = coinList;
    }

    @NonNull
    @Override
    public CoinListAdapter.CoinViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View mItemView = mInflater.inflate(R.layout.coinlist_item,
                parent, false);
        return new CoinViewHolder(mItemView, this);
    }

    @Override
    public void onBindViewHolder(@NonNull CoinListAdapter.CoinViewHolder holder, int position) {

        Coin mCurrent = mCoinList.get(position);
        String mName = mCurrent.getName();
        holder.coinName.setText(mName);
        String mValue = Double.toString(mCurrent.getValue());
        holder.value.setText("$"+mValue);
        String mPercentage = Double.toString(mCurrent.getChange1h());
        holder.percentage.setText(mPercentage+ " %");
    }

    @Override
    public int getItemCount() {
        return mCoinList.size();
    }


    class CoinViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        //public final TextView coinItemView;
        public final TextView coinName;
        public final TextView value;
        public final TextView percentage;
        final CoinListAdapter mAdapter;

        public CoinViewHolder(View itemView, CoinListAdapter adapter) {
            super(itemView);
            coinName = itemView.findViewById(R.id.name);
            value = itemView.findViewById(R.id.value);
            percentage = itemView.findViewById(R.id.percentage);
            this.mAdapter = adapter;
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            int mPosition = getLayoutPosition();

            View articleView = v.getRootView().findViewById(R.id.tablet_coinDetails);
            //Log.d(TAG, "The view resulted in:" + Boolean.toString(articleView != null));
           // Log.d(TAG, Boolean.toString(articleView.getVisibility() == View.VISIBLE));
            mIsDualPane = articleView != null &&
                    articleView.getVisibility() == View.VISIBLE;
            Log.d(TAG, Boolean.toString(mIsDualPane));
            if(mIsDualPane){
            DetailFragment fragment = new DetailFragment();
            Bundle bundle = new Bundle();
            bundle.putInt("pos", mPosition);
            fragment.setArguments(bundle);
                ((FragmentActivity) v.getContext()).getSupportFragmentManager().beginTransaction()
                        .replace(R.id.tablet_coinDetails, fragment)
                        .commit();

                /*FragmentManager fragman = ((Activity)context).getFragmentManager();
                FragmentTransaction ft= fragman.beginTransaction();
                ft.replace(R.id.tablet_coinDetails, fragment);
*/
            }else {
                Log.d(TAG, "Launching Intent");
                Intent intent = new Intent(v.getContext(), DetailedActivity.class);
                intent.putExtra("pos", mPosition);
                v.getContext().startActivity(intent);
            }
        }
    }

 //   public void onCoinSelected(int index) {
 //       mArtIndex = index;
 //       if (mIsDualPane) {
  //          /* display article on the right pane */
  //          mArticleFragment.displayArticle(mCurrentCat.getArticle(index));
  //      } else {
  //          /* start a separate activity */
  //          Intent intent = new Intent(this, ArticleActivity.class);
  //          intent.putExtra("catIndex", mCatIndex);
  //          intent.putExtra("artIndex", index);
  //          startActivity(intent);
  //      }
  //  }

}
