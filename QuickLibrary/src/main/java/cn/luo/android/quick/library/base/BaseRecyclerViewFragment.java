package cn.luo.android.quick.library.base;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;

import com.ajguan.library.EasyRefreshLayout;
import com.ajguan.library.LoadModel;

import cn.luo.android.quick.library.R;

/**
 * AUTHOR:       Luo
 * VERSION:      V1.0
 * DESCRIPTION:  description
 * CREATE TIME:  2018/4/3 17:43
 * NOTE:
 */
public abstract class BaseRecyclerViewFragment extends BaseFragment {

    protected RecyclerView recyclerView;
    protected EasyRefreshLayout easyRefreshLayout;
    protected View emptyView;

    private LinearLayout llTop;

    @Override
    protected int getLayoutResId() {
        return R.layout.fragment_base_recycler_view;
    }

    @Override
    protected void initView() {
        easyRefreshLayout = rootView.findViewById(R.id.easyRefreshLayout);
        easyRefreshLayout.setEnablePullToRefresh(false);
        easyRefreshLayout.setLoadMoreModel(LoadModel.NONE);

        recyclerView = rootView.findViewById(R.id.recyclerView);
        RecyclerView.LayoutManager layoutManager = getLayoutManager();
        recyclerView.setLayoutManager(layoutManager == null ? new LinearLayoutManager(context) : layoutManager);

        emptyView = rootView.findViewById(R.id.emptyView);
        emptyView.setVisibility(View.GONE);

        llTop = rootView.findViewById(R.id.llTop);

        initRecyclerView();
    }

    protected void addTopView(View view) {
        llTop.addView(view);
    }

    protected abstract RecyclerView.LayoutManager getLayoutManager();

    protected abstract void initRecyclerView();
}
