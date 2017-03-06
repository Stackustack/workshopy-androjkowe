package qaworkshops.android.netguru.co.qaworshopsandroid.feature.main.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import java.util.Collection;
import java.util.List;

import qaworkshops.android.netguru.co.qaworshopsandroid.data.ListItem;

public class MainListAdapter extends RecyclerView.Adapter<MainListViewHolder> {

    @NonNull
    private List<ListItem> itemList;

    public MainListAdapter(@NonNull List<ListItem> itemList) {
        this.itemList = itemList;
    }

    @Override
    public MainListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MainListViewHolder(parent);
    }

    @Override
    public void onBindViewHolder(MainListViewHolder holder, int position) {
        holder.bind(itemList.get(position));
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }

    public List<ListItem> getData() {
        return itemList;
    }

    public void setNewItems(List<ListItem> itemList) {
        this.itemList = itemList;
        notifyDataSetChanged();
    }

    public void addNewShots(Collection<ListItem> itemsToAdd) {
        int oldSize = this.itemList.size();
        this.itemList.addAll(itemsToAdd);
        notifyItemRangeInserted(oldSize, itemsToAdd.size() + 1);
    }
}
