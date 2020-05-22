package com.isteel.myfaceit.ui.players.profile.recentMaps;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.isteel.myfaceit.data.model.ResponseMatch;
import com.isteel.myfaceit.databinding.RecentMapsItemBinding;
import com.isteel.myfaceit.ui.base.BaseViewHolder;
import com.isteel.myfaceit.ui.players.profile.recentMaps.recentMapsStats.RecentMapsStatsActivity;

import java.util.List;

import static androidx.core.content.ContextCompat.startActivity;

public class RecentMapsAdapter extends RecyclerView.Adapter<RecentMapsAdapter.RecentMapsViewHolder> {

    private List<ResponseMatch.Match> matchList;


    public RecentMapsAdapter(List<ResponseMatch.Match> matchList) {
        this.matchList = matchList;
    }

    @NonNull
    @Override
    public RecentMapsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        RecentMapsItemBinding recentMapsItemBinding = RecentMapsItemBinding.inflate(LayoutInflater.from(parent.getContext()),
                parent, false);
        return new RecentMapsViewHolder(recentMapsItemBinding);

    }

    @Override
    public void onBindViewHolder(@NonNull RecentMapsViewHolder holder, int position) {
        holder.onBind(position);
    }

    @Override
    public int getItemCount() {
        return matchList.size();
    }

    public void addItems(List<ResponseMatch.Match> segment) {
        assert segment != null;
        matchList.addAll(segment);
        notifyDataSetChanged();
    }

    public void clearItems() {
        matchList.clear();
    }

    public class RecentMapsViewHolder extends BaseViewHolder  implements RecentMapItemViewModel.RecentMapItemViewModelListener {

        private RecentMapsItemBinding mBinding;
        private RecentMapItemViewModel viewModel;

        public RecentMapsViewHolder(RecentMapsItemBinding binding) {
            super(binding.getRoot());
            mBinding = binding;
        }
        @Override
        public void onBind(int position) {
            final ResponseMatch.Match match = matchList.get(position);
            viewModel = new RecentMapItemViewModel(this, match);
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

                    startActivity(itemView.getContext(), RecentMapsStatsActivity.newIntent(itemView.getContext(), id),null);
                } catch (Exception e) {

                }
            }
        }
    }
}
