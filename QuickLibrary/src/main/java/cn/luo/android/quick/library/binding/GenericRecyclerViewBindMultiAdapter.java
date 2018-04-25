package cn.luo.android.quick.library.binding;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.View;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.chad.library.adapter.base.entity.MultiItemEntity;

import cn.luo.android.quick.library.BR;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/9/5.
 * 用来实现万能绑定适配器
 */
public class GenericRecyclerViewBindMultiAdapter<T extends MultiItemEntity> extends BaseMultiItemQuickAdapter<T, GenericRecyclerViewBindMultiAdapter.ViewHolder> {

    private Fragment fragment;

    public GenericRecyclerViewBindMultiAdapter(List<T> data,
                                               Fragment fragment,
                                               List<QuickViewType> viewTypeList) {
        super(data);
        this.fragment = fragment;
        for (int i = 0; i < viewTypeList.size(); i++) {
            addItemType(viewTypeList.get(i).getViewType(), viewTypeList.get(i).getLayoutResId());
        }
    }

    public GenericRecyclerViewBindMultiAdapter(Fragment fragment,
                                               List<QuickViewType> viewTypeList) {
        super(new ArrayList<T>());
        this.fragment = fragment;
        for (int i = 0; i < viewTypeList.size(); i++) {
            addItemType(viewTypeList.get(i).getViewType(), viewTypeList.get(i).getLayoutResId());
        }
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
