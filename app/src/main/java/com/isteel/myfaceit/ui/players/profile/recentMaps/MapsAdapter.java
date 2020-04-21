package com.isteel.myfaceit.ui.players.profile.recentMaps;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.isteel.myfaceit.data.model.ResponseGame;
import com.isteel.myfaceit.data.model.ResponsePlayer;
import com.isteel.myfaceit.databinding.MapItemBinding;
import com.isteel.myfaceit.ui.base.BaseViewHolder;
import com.isteel.myfaceit.ui.players.PlayerItemViewModel;
import com.isteel.myfaceit.ui.players.profile.ProfileActivity;

import java.util.List;

public class MapsAdapter extends RecyclerView.Adapter<MapsAdapter.MapsViewHolder> {

    private List<ResponseGame.Segment> mSegmentList;

    public GameAdapterListener mListener;


    public MapsAdapter(List<ResponseGame.Segment> mSegmentList) {
        this.mSegmentList = mSegmentList;
    }

    @NonNull
    @Override
    public MapsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        MapItemBinding mapsViewBinding = MapItemBinding.inflate(LayoutInflater.from(parent.getContext()),
                parent, false);
        return new MapsViewHolder(mapsViewBinding);

    }

    @Override
    public void onBindViewHolder(@NonNull MapsViewHolder holder, int position) {
        holder.onBind(position);
    }

    @Override
    public int getItemCount() {
        return mSegmentList.size();
    }

    public void addItems(List<ResponseGame.Segment> segment) {
        assert segment != null;
        mSegmentList.addAll(segment);
        notifyDataSetChanged();
    }

    public void clearItems() {
        mSegmentList.clear();
    }


    public void setListener(GameAdapterListener listener) {
        this.mListener = listener;
    }

    public interface GameAdapterListener {
        void onRetryClick();
    }

    public class MapsViewHolder extends BaseViewHolder {

        private MapItemBinding mBinding;
        private MapItemViewModel viewModel;

        public MapsViewHolder(MapItemBinding binding) {
            super(binding.getRoot());
            mBinding = binding;
        }

        @Override
        public void onBind(int position) {
            final ResponseGame.Segment segment = mSegmentList.get(position);
            viewModel = new MapItemViewModel( segment);
            mBinding.setViewModel(viewModel);
            // Immediate Binding
            // When a variable or observable changes, the binding will be scheduled to change before
            // the next frame. There are times, however, when binding must be executed immediately.
            // To force execution, use the executePendingBindings() method.
            mBinding.executePendingBindings();
        }
    }
}
