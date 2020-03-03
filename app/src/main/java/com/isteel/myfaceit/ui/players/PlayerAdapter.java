package com.isteel.myfaceit.ui.players;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.isteel.myfaceit.data.model.ResponsePlayer;
import com.isteel.myfaceit.databinding.PlayerItemBinding;
import com.isteel.myfaceit.ui.base.BaseViewHolder;
import com.isteel.myfaceit.utils.LogUtil;

import java.util.List;

public class PlayerAdapter extends RecyclerView.Adapter<PlayerAdapter.PlayerViewHolder> {

    private List<ResponsePlayer.Player> mPlayerResponseList;

    public GameAdapterListener mListener;

    public PlayerAdapter(List<ResponsePlayer.Player> mPlayerResponseList) {
        this.mPlayerResponseList = mPlayerResponseList;

    }

    @NonNull
    @Override
    public PlayerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LogUtil.log("safdasfaf");

        PlayerItemBinding gameItemViewBinding = PlayerItemBinding.inflate(LayoutInflater.from(parent.getContext()),
                parent, false);
        return new PlayerViewHolder(gameItemViewBinding);

    }

    @Override
    public void onBindViewHolder(@NonNull PlayerViewHolder holder, int position) {
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

    public class PlayerViewHolder extends BaseViewHolder implements PlayerItemViewModel.GameItemViewModelListener{

        private PlayerItemBinding mBinding;
        private PlayerItemViewModel viewModel;

        public PlayerViewHolder(PlayerItemBinding binding) {
            super(binding.getRoot());
            mBinding = binding;
        }

        @Override
        public void onBind(int position) {
            final ResponsePlayer.Player player = mPlayerResponseList.get(position);
            viewModel = new PlayerItemViewModel(this, player);
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
