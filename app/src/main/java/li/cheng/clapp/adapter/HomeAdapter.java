package li.cheng.clapp.adapter;

import android.content.Context;
import android.content.pm.ResolveInfo;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import li.cheng.clapp.R;

/**
 * Created by wfy 2018/1/5 14:21.
 */

public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.MyViewHolder> implements View.OnClickListener {
    private Context mActivity;
    private List<ResolveInfo> apps;
    public HomeAdapter(Context mActivity, List<ResolveInfo> apps) {
        this.mActivity = mActivity;
        this.apps = apps;
    }

    private OnItemClickListener mOnItemClickListener = null;

    @Override
    public void onClick(View view) {
        if(mOnItemClickListener!=null)mOnItemClickListener.onItemClick(view,(int)view.getTag());
    }

    //define interface
    public interface OnItemClickListener {
        void onItemClick(View view , int position);
    }
    public void setOnItemClickListener(OnItemClickListener listener) {
        this.mOnItemClickListener = listener;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        View view=LayoutInflater.from(
                mActivity).inflate(R.layout.item_home, parent,
                false);
        MyViewHolder holder = new MyViewHolder(view);
        view.setOnClickListener(this);
        return holder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position)
    {
        ResolveInfo info = apps.get(position);
        holder.imv_icon.setImageDrawable(info.activityInfo.loadIcon(mActivity.getPackageManager()));
        holder.tv_name.setText(info.activityInfo.loadLabel(mActivity.getPackageManager()));
        holder.itemView.setTag(position);
    }

    @Override
    public int getItemCount()
    {
        return apps.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder
    {

        ImageView imv_icon;
        TextView tv_name;
        public MyViewHolder(View view)
        {
            super(view);
            imv_icon = (ImageView) view.findViewById(R.id.imv_icon);
            tv_name = (TextView) view.findViewById(R.id.tv_name);
        }
    }
}
