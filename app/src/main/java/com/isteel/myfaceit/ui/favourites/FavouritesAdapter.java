package com.isteel.myfaceit.ui.favourites;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.isteel.myfaceit.data.model.db.PlayerByNickDB;
import com.isteel.myfaceit.databinding.GameItemBinding;
import com.isteel.myfaceit.ui.base.BaseViewHolder;
import com.isteel.myfaceit.ui.players.profile.ProfileActivity;
import com.isteel.myfaceit.ui.players.profile.recentMaps.recentMapsStats.RecentMapsStatsActivity;
import com.isteel.myfaceit.utils.LogUtil;

import java.util.List;

import static androidx.core.content.ContextCompat.startActivity;

public class FavouritesAdapter extends RecyclerView.Adapter<FavouritesAdapter.GameViewHolder> {

    private List<PlayerByNickDB> mGameResponseList;

    public GameAdapterListener mListener;

    public FavouritesAdapter(List<PlayerByNickDB> mGameResponseList) {
        this.mGameResponseList = mGameResponseList;

    }

    @NonNull
    @Override
    public GameViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LogUtil.log("safdasfaf");

        GameItemBinding gameItemViewBinding = GameItemBinding.inflate(LayoutInflater.from(parent.getContext()),
                parent, false);
        return new GameViewHolder(gameItemViewBinding);

    }

    @Override
    public void onBindViewHolder(@NonNull GameViewHolder holder, int position) {
        holder.onBind(position);
    }

    @Override
    public int getItemCount() {
        return mGameResponseList.size();
    }

    public void addItems(List<PlayerByNickDB> gameList) {
        mGameResponseList.addAll(gameList);

        notifyDataSetChanged();
    }

    public void clearItems() {
        mGameResponseList.clear();
    }


    public void setListener(GameAdapterListener listener) {
        this.mListener = listener;
    }

    public interface GameAdapterListener {
        void onRetryClick();
    }

    public class GameViewHolder extends BaseViewHolder implements FavouritesItemViewModel.GameItemViewModelListener{

        private GameItemBinding mBinding;
        private FavouritesItemViewModel viewModel;

        public GameViewHolder(GameItemBinding binding) {
            super(binding.getRoot());
            mBinding = binding;
        }

        @Override
        public void onBind(int position) {
            final PlayerByNickDB game = mGameResponseList.get(position);
            viewModel = new FavouritesItemViewModel(this, game);
            mBinding.setViewModel(viewModel);

            // Immediate Binding
            // When a variable or observable changes, the binding will be scheduled to change before
            // the next frame. There are times, however, when binding must be executed immediately.
            // To force execution, use the executePendingBindings() method.
            mBinding.executePendingBindings();
        }

        @Override
        public void onItemClick(String id) {
            if (id != null) {
                try {

                    startActivity(itemView.getContext(), ProfileActivity.newIntent(itemView.getContext(), id),null);
                } catch (Exception e) {

                }
            }
        }
    }
}
