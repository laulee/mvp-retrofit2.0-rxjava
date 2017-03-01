package com.laulee.retrofit2.ui.gank.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.laulee.retrofit2.R;
import com.laulee.retrofit2.base.BaseRecyclerAdapter;
import com.laulee.retrofit2.bean.entity.GankItemEntity;

import java.util.List;

/**
 * Created by laulee on 17/2/28.
 */

public class AndroidAdapter extends BaseRecyclerAdapter<GankItemEntity, AndroidAdapter.ViewHolder> {

    public AndroidAdapter( List<GankItemEntity> datas ) {
        super( datas );
    }

    @Override
    protected ViewHolder createViewHolder( View view ) {
        return new ViewHolder( view );
    }

    @Override
    protected int layoutResId() {
        return R.layout.item_tech;
    }

    @Override
    protected void onBindViewHolder( ViewHolder holder, GankItemEntity entity ) {
        holder.title.setText( entity.getSource( ) );
        holder.author.setText( entity.getWho( ) );
        holder.time.setText( entity.getCreatedAt( ) );
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView title;
        TextView author;
        TextView time;

        public ViewHolder( View itemView ) {
            super( itemView );
            title = (TextView) itemView.findViewById( R.id.tv_tech_title );
            author = (TextView) itemView.findViewById( R.id.tv_tech_author );
            time = (TextView) itemView.findViewById( R.id.tv_tech_time );
        }
    }
}
