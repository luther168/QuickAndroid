package cn.luo.android.quick.library.binding;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import cn.luo.android.quick.library.BR;

import java.util.List;

/**
 * @author      Hurston
 * @version     1.0.0
 * @description
 * @createdTime 2018/11/28 16:55
 * @note
 */
public class GenericRecyclerViewBindAdapter<T> extends BaseQuickAdapter<T, GenericRecyclerViewBindAdapter.ViewHolder> {

    private View.OnClickListener listener;

    public GenericRecyclerViewBindAdapter(@LayoutRes int layoutResId, @Nullable List<T> data, View.OnClickListener listener) {
        super(layoutResId, data);
        this.listener = listener;
    }

    public GenericRecyclerViewBindAdapter(@Nullable List<T> data, View.OnClickListener listener) {
        super(data);
        this.listener = listener;
    }

    public GenericRecyclerViewBindAdapter(@LayoutRes int layoutResId, View.OnClickListener listener) {
        super(layoutResId);
        this.listener = listener;
    }

    @Override
    protected void convert(ViewHolder helper, T item) {
        helper.bind(item, listener);
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

        void bind(@NonNull Object item, View.OnClickListener listener) {
            binding.setVariable(BR.item, item);
            if (listener != null) {
                binding.setVariable(BR.listener, listener);
            }
            binding.setVariable(BR.position, getLayoutPosition());
        }
    }
}
