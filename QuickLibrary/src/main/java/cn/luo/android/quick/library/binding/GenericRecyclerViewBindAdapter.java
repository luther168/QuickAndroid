package cn.luo.android.quick.library.binding;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import cn.luo.android.quick.library.BR;

import java.util.List;

/**
 * Created by Administrator on 2016/9/5.
 * 用来实现万能绑定适配器
 */
public class GenericRecyclerViewBindAdapter<T> extends BaseQuickAdapter<T, GenericRecyclerViewBindAdapter.ViewHolder> {

    private Fragment fragment;

    public GenericRecyclerViewBindAdapter(@LayoutRes int layoutResId, @Nullable List<T> data, Fragment fragment) {
        super(layoutResId, data);
        this.fragment = fragment;
    }

    public GenericRecyclerViewBindAdapter(@Nullable List<T> data, Fragment fragment) {
        super(data);
        this.fragment = fragment;
    }

    public GenericRecyclerViewBindAdapter(@LayoutRes int layoutResId, Fragment fragment) {
        super(layoutResId);
        this.fragment = fragment;
    }

    @Override
    protected void convert(ViewHolder helper, T item) {
        helper.bind(item, fragment);
    }

    public static class ViewHolder extends BaseViewHolder {
        private ViewDataBinding binding;

        ViewHolder(View convertView) {
            super(convertView);
            this.binding = DataBindingUtil.bind(convertView);
        }

        public ViewDataBinding getBinding() {
            return binding;
        }

        void bind(@NonNull Object item, Fragment fragment) {
            binding.setVariable(BR.item, item);
            if (fragment != null) {
                binding.setVariable(BR.listener, fragment);
            }
            binding.setVariable(BR.position, getLayoutPosition());
        }
    }
}
