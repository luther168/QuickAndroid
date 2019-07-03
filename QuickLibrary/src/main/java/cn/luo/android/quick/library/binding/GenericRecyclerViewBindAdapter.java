package cn.luo.android.quick.library.binding;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

import cn.luo.android.quick.library.BR;

/**
 * @author Hurston
 * @version 1.0.0
 * @description
 * @createdTime 2018/11/28 16:55
 * @note
 */
public class GenericRecyclerViewBindAdapter<T, V extends ViewDataBinding>
        extends BaseQuickAdapter<T, GenericRecyclerViewBindAdapter.ViewHolder<V>> {

    public GenericRecyclerViewBindAdapter(@LayoutRes int layoutResId, @Nullable List<T> data) {
        super(layoutResId, data);
    }

    public GenericRecyclerViewBindAdapter(@Nullable List<T> data) {
        super(data);
    }

    public GenericRecyclerViewBindAdapter(@LayoutRes int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(ViewHolder<V> helper, T item) {
        helper.bind(item);
    }

    public static class ViewHolder<V extends ViewDataBinding> extends BaseViewHolder {
        private V binding;

        ViewHolder(View convertView) {
            super(convertView);
            this.binding = DataBindingUtil.bind(convertView);
        }

        public V getBinding() {
            return binding;
        }

        void bind(@NonNull Object item) {
            binding.setVariable(BR.item, item);
            binding.setVariable(BR.position, getLayoutPosition());
        }
    }
}
