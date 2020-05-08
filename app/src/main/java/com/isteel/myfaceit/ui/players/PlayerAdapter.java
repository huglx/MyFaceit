package com.isteel.myfaceit.ui.players;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.isteel.myfaceit.data.model.ResponsePlayer;
import com.isteel.myfaceit.databinding.PlayerItemBinding;
import com.isteel.myfaceit.ui.base.BaseViewHolder;
import com.isteel.myfaceit.ui.players.profile.ProfileActivity;

import java.util.List;

public class PlayerAdapter extends RecyclerView.Adapter<PlayerAdapter.PlayerViewHolder> {

    private List<ResponsePlayer.PlayerByNick> mPlayerResponseList;

    public GameAdapterListener mListener;


    public PlayerAdapter(List<ResponsePlayer.PlayerByNick> mPlayerResponseList) {
        this.mPlayerResponseList = mPlayerResponseList;

    }

    @NonNull
    @Override
    public PlayerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        PlayerItemBinding gameItemViewBinding = PlayerItemBinding.inflate(LayoutInflater.from(parent.getContext()),
                parent, false);
        return new PlayerViewHolder(gameItemViewBinding);

    }

    @Override
    public void onBindViewHolder(@NonNull PlayerViewHolder holder, int position) {
        holder.onBind(position);
        holder.itemView.setOnClickListener(v -> v.getContext().startActivity(ProfileActivity.newIntent(v.getContext(), mPlayerResponseList.get(position).getPlayer_id())));
    }

    @Override
    public int getItemCount() {
        return mPlayerResponseList.size();
    }

    public void addItems(List<ResponsePlayer.PlayerByNick> playerList) {
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

    public class PlayerViewHolder extends BaseViewHolder {

        private PlayerItemBinding mBinding;
        private PlayerItemViewModel viewModel;

        public PlayerViewHolder(PlayerItemBinding binding) {
            super(binding.getRoot());
            mBinding = binding;
        }

        @Override
        public void onBind(int position) {
            final ResponsePlayer.PlayerByNick player = mPlayerResponseList.get(position);
            viewModel = new PlayerItemViewModel( player);
            mBinding.setViewModel(viewModel);
            // Immediate Binding
            // When a variable or observable changes, the binding will be scheduled to change before
            // the next frame. There are times, however, when binding must be executed immediately.
            // To force execution, use the executePendingBindings() method.
            mBinding.executePendingBindings();
        }
    }
}
