package homepunk.lesson.series.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import homepunk.lesson.first.contollers.R;
import homepunk.lesson.series.model.HdrezkaSeries;

public class UpdatesRecyclerAdapter extends RecyclerView.Adapter<UpdatesRecyclerAdapter.ViewHolder> {
    protected List<HdrezkaSeries> updates;

    public UpdatesRecyclerAdapter(List<HdrezkaSeries> updates) {
        this.updates = updates;
    }

    @Override
    public UpdatesRecyclerAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View root = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item_updates, parent, false);

        return new ViewHolder(root);
    }

    @Override
    public void onBindViewHolder(UpdatesRecyclerAdapter.ViewHolder holder, int position) {
        HdrezkaSeries series = updates.get(position);

        holder.loadTitle(series.toString());
    }

    @Override
    public int getItemCount() {
        return updates.size();
    }

    public void updateData(List<HdrezkaSeries> updates){
        this.updates.clear();
        this.updates.addAll(updates);
        notifyItemRangeChanged(0, updates.size());
    }

    public void updateData(HdrezkaSeries hdrezkaSeries){
        this.updates.add(hdrezkaSeries);
        notifyItemRangeChanged(0, updates.size());
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.item_updates_title) TextView title;

        public ViewHolder(View root) {
            super(root);
            ButterKnife.bind(this, root);
        }

        public void loadTitle(String title){
            this.title.setText(title);
        }
    }
}
