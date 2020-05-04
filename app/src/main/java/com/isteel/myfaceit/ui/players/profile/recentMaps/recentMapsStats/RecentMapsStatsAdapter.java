package com.isteel.myfaceit.ui.players.profile.recentMaps.recentMapsStats;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.isteel.myfaceit.data.model.ResponsePlayer;
import com.isteel.myfaceit.databinding.PlayerItemBinding;
import com.isteel.myfaceit.databinding.RecentMapsItemBinding;
import com.isteel.myfaceit.databinding.RecentMapsStatsItemBinding;
import com.isteel.myfaceit.ui.base.BaseViewHolder;
import com.isteel.myfaceit.ui.players.profile.ProfileActivity;

import java.util.List;

public class RecentMapsStatsAdapter extends RecyclerView.Adapter<RecentMapsStatsAdapter.RecentMapsStatsViewHolder> {

    private List<ResponsePlayer.Player> mPlayerResponseList;

    public GameAdapterListener mListener;


    public RecentMapsStatsAdapter(List<ResponsePlayer.Player> mPlayerResponseList) {
        this.mPlayerResponseList = mPlayerResponseList;
    }

    @NonNull
    @Override
    public RecentMapsStatsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        RecentMapsStatsItemBinding gameItemViewBinding = RecentMapsStatsItemBinding.inflate(LayoutInflater.from(parent.getContext()),
                parent, false);
        return new RecentMapsStatsViewHolder(gameItemViewBinding);

    }

    @Override
    public void onBindViewHolder(@NonNull RecentMapsStatsViewHolder holder, int position) {
        holder.onBind(position);
    }

    @Override
    public int getItemCount() {
        return mPlayerResponseList.size();
    }

    public void addItems(List<ResponsePlayer.Player> playerList) {
        if(playerList.size()!=5){
            for (int i = playerList.size(); i < 5;i++){
                playerList.add(i,new ResponsePlayer.Player("Leaver"));
            }
        }
        mPlayerResponseList.addAll(playerList);
        notifyDataSetChanged();
    }

    public void clearItems() {
        mPlayerResponseList.clear();
    }


    public void setListener(GameAdapterListener listener) {
        this.mListener = listener;
    }

    public interface GameAdapterListener {
        void onRetryClick();
    }

    public class RecentMapsStatsViewHolder extends BaseViewHolder {

        private RecentMapsStatsItemBinding mBinding;
        private RecentMapsStatsItemViewModel viewModel;

        public RecentMapsStatsViewHolder(RecentMapsStatsItemBinding binding) {
            super(binding.getRoot());
            mBinding = binding;
        }

        @Override
        public void onBind(int position) {
            final ResponsePlayer.Player player = mPlayerResponseList.get(position);
            viewModel = new RecentMapsStatsItemViewModel(player);
            mBinding.setViewModel(viewModel);
            // Immediate Binding
            // When a variable or observable changes, the binding will be scheduled to change before
            // the next frame. There are times, however, when binding must be executed immediately.
            // To force execution, use the executePendingBindings() method.
            mBinding.executePendingBindings();
        }
    }
}
