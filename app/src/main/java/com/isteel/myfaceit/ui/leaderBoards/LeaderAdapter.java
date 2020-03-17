package com.isteel.myfaceit.ui.leaderBoards;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.isteel.myfaceit.data.model.ResponsePlayer;
import com.isteel.myfaceit.databinding.LeaderItemBinding;
import com.isteel.myfaceit.databinding.PlayerItemBinding;
import com.isteel.myfaceit.ui.base.BaseViewHolder;
import com.isteel.myfaceit.ui.players.PlayerAdapter;
import com.isteel.myfaceit.utils.LogUtil;

import java.util.List;

public class LeaderAdapter extends RecyclerView.Adapter<LeaderAdapter.LeaderViewHolder> {

    private List<ResponsePlayer.Player> mPlayerResponseList;

    public GameAdapterListener mListener;

    public LeaderAdapter(List<ResponsePlayer.Player> mPlayerResponseList) {
        this.mPlayerResponseList = mPlayerResponseList;

    }

    @NonNull
    @Override
    public LeaderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LogUtil.log("safdasfaf");

        LeaderItemBinding leaderItemBinding = LeaderItemBinding.inflate(LayoutInflater.from(parent.getContext()),
                parent, false);
        return new LeaderViewHolder(leaderItemBinding);

    }

    @Override
    public void onBindViewHolder(@NonNull LeaderViewHolder holder, int position) {
        holder.onBind(position);
    }

    @Override
    public int getItemCount() {
        return mPlayerResponseList.size();
    }

    public void addItems(List<ResponsePlayer.Player> playerList) {
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

    public class LeaderViewHolder extends BaseViewHolder implements LeaderItemViewModel.GameItemViewModelListener{

        private LeaderItemBinding mBinding;
        private LeaderItemViewModel viewModel;

        public LeaderViewHolder(LeaderItemBinding binding) {
            super(binding.getRoot());
            mBinding = binding;
        }

        @Override
        public void onBind(int position) {
            final ResponsePlayer.Player player = mPlayerResponseList.get(position);
            viewModel = new LeaderItemViewModel(this, player);
            mBinding.setViewModel(viewModel);

            // Immediate Binding
            // When a variable or observable changes, the binding will be scheduled to change before
            // the next frame. There are times, however, when binding must be executed immediately.
            // To force execution, use the executePendingBindings() method.
            mBinding.executePendingBindings();
        }

        @Override
        public void onItemClick() {

        }
    }
}
