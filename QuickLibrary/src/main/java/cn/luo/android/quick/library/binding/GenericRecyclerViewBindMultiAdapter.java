package cn.luo.android.quick.library.binding;

import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.annotation.NonNull;

import android.view.View;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.entity.MultiItemEntity;
import com.chad.library.adapter.base.module.LoadMoreModule;
import com.chad.library.adapter.base.viewholder.BaseDataBindingHolder;

import java.util.ArrayList;
import java.util.List;

import cn.luo.android.quick.library.BR;

/**
 * @author Hurston
 * @version 1.0.0
 * @description
 * @createdTime 2018/11/28 16:56
 * @note
 */
public class GenericRecyclerViewBindMultiAdapter<T extends MultiItemEntity>
        extends BaseMultiItemQuickAdapter<T, GenericRecyclerViewBindMultiAdapter.ViewHolder>
        implements LoadMoreModule {

    public GenericRecyclerViewBindMultiAdapter(List<QuickViewType> viewTypeList) {
        super(new ArrayList<T>());
        for (int i = 0; i < viewTypeList.size(); i++) {
            addItemType(viewTypeList.get(i).getViewType(), viewTypeList.get(i).getLayoutResId());
        }
    }

    public GenericRecyclerViewBindMultiAdapter(List<T> data, List<QuickViewType> viewTypeList) {
        super(data);
        for (int i = 0; i < viewTypeList.size(); i++) {
            addItemType(viewTypeList.get(i).getViewType(), viewTypeList.get(i).getLayoutResId());
        }
    }

    @Override
    protected void convert(ViewHolder helper, T item) {
        helper.bind(item);
    }

    public static class ViewHolder extends BaseDataBindingHolder {
        private ViewDataBinding binding;

        ViewHolder(View convertView) {
            super(convertView);
            this.binding = DataBindingUtil.bind(convertView);
        }

        void bind(@NonNull Object item) {
            binding.setVariable(BR.item, item);
            binding.setVariable(BR.position, getLayoutPosition());
        }
    }

    public static class QuickViewType {
        private int viewType;
        private int layoutResId;

        public QuickViewType(int viewType, int layoutResId) {
            this.viewType = viewType;
            this.layoutResId = layoutResId;
        }

        public int getViewType() {
            return viewType;
        }

        public void setViewType(int viewType) {
            this.viewType = viewType;
        }

        public int getLayoutResId() {
            return layoutResId;
        }

        public void setLayoutResId(int layoutResId) {
            this.layoutResId = layoutResId;
        }
    }
}
