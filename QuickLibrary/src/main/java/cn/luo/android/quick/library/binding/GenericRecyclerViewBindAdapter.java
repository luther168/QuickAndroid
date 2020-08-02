package cn.luo.android.quick.library.binding;

import android.view.View;

import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.ViewDataBinding;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.module.LoadMoreModule;
import com.chad.library.adapter.base.viewholder.BaseDataBindingHolder;

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
        extends BaseQuickAdapter<T, GenericRecyclerViewBindAdapter.ViewHolder<V>>
        implements LoadMoreModule {

    public GenericRecyclerViewBindAdapter(@LayoutRes int layoutResId) {
        super(layoutResId);
    }

    public GenericRecyclerViewBindAdapter(@LayoutRes int layoutResId, @Nullable List<T> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(ViewHolder<V> helper, T item) {
        helper.bind(item);
    }

    public static class ViewHolder<V extends ViewDataBinding> extends BaseDataBindingHolder<V> {

        ViewHolder(View convertView) {
            super(convertView);
        }

        void bind(@NonNull Object item) {
            V binding = getDataBinding();
            if (binding != null) {
                binding.setVariable(BR.item, item);
                binding.setVariable(BR.position, getLayoutPosition());
            }
        }
    }
}
