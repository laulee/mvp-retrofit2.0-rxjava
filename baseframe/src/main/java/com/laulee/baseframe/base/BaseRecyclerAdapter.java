package com.laulee.baseframe.base;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by laulee on 17/1/17.
 */

public abstract class BaseRecyclerAdapter<T, VH extends RecyclerView.ViewHolder>
        extends RecyclerView.Adapter<VH> {

    protected List<T> datas;
    private IOnItemClickListener<T> listener;

    public BaseRecyclerAdapter( List<T> datas ) {
        this.datas = datas;
    }

    @Override
    public VH onCreateViewHolder( ViewGroup parent, int viewType ) {
        View view = LayoutInflater.from( parent.getContext( ) ).inflate( layoutResId( ), null );
        VH viewHolder = createViewHolder( view );
        return viewHolder;
    }

    protected abstract VH createViewHolder( View view );

    /**
     * item布局
     *
     * @return
     */
    protected abstract int layoutResId();

    @Override
    public void onBindViewHolder( VH holder, final int position ) {

        final T entity = getItem( position );

        if( entity != null ) {
            if( listener != null ) {
                holder.itemView.setOnClickListener( new View.OnClickListener( ) {
                    @Override
                    public void onClick( View v ) {
                        listener.onItemClick( v, entity, position );
                    }
                } );
            }
            onBindViewHolder( holder, entity );
        }
    }

    @Override
    public int getItemCount() {
        if( datas != null )
            return datas.size( );
        return 0;
    }

    /**
     * 获得item对象
     *
     * @param position
     * @return
     */
    public T getItem( int position ) {
        if( datas != null && datas.size( ) > 0 )
            return datas.get( position );
        return null;
    }

    /**
     * 更新数据
     *
     * @param list
     */
    public void update( List<T> list ) {
        this.datas = list;
        notifyDataSetChanged( );
    }

    protected abstract void onBindViewHolder( VH holder, T entity );

    /**
     * 设置item监听
     *
     * @param listener
     */
    public void setIOnItemClickListener( IOnItemClickListener<T> listener ) {
        this.listener = listener;
    }

    public void addItem( T entity ) {
        this.datas.add( entity );
        notifyItemInserted( datas.size( ) - 1 );
    }

    public void addList( List<T> list ) {
        this.datas.addAll( list );
        notifyDataSetChanged( );
    }

    /**
     * 点击Item监听事件
     */
    public interface IOnItemClickListener<T> {
        void onItemClick( View view, T entity, int position );
    }
}
