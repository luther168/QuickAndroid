package cn.luo.android.quick.library.base;

import androidx.annotation.StringRes;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.listener.OnLoadMoreListener;

import cn.luo.android.quick.library.R;

/**
 * @author Hurston
 * @version 1.0.0
 * @description
 * @createdTime 2018/11/28 16:17
 * @note
 */
public abstract class BaseRecyclerViewFragment extends BaseFragment
        implements SwipeRefreshLayout.OnRefreshListener, OnLoadMoreListener {

    protected RecyclerView recyclerView;
    protected SwipeRefreshLayout refreshLayout;
    protected View emptyView;
    private TextView tvEmpty;
    private LinearLayout llTop;

    @Override
    protected int getLayoutResId() {
        return R.layout.fragment_base_recycler_view;
    }

    @Override
    protected void initView() {
        recyclerView = rootView.findViewById(R.id.recyclerView);
        RecyclerView.LayoutManager layoutManager = getLayoutManager();
        recyclerView.setLayoutManager(layoutManager == null ? new LinearLayoutManager(context) : layoutManager);

        refreshLayout = rootView.findViewById(R.id.refreshLayout);
        refreshLayout.setOnRefreshListener(this);
        refreshLayout.setEnabled(false);

        emptyView = rootView.findViewById(R.id.emptyView);
        emptyView.setVisibility(View.GONE);
        tvEmpty = emptyView.findViewById(R.id.tvEmpty);

        llTop = rootView.findViewById(R.id.llTop);

        initRecyclerView();
        refreshLayout.setRefreshing(true);
        onRefresh();
    }

    @Override
    public void onRefresh() {

    }

    @Override
    public void onLoadMore() {

    }

    protected void addTopView(View view) {
        llTop.addView(view);
    }

    protected void setEmptyText(String text) {
        tvEmpty.setText(text);
    }

    protected void setEmptyText(@StringRes int res) {
        tvEmpty.setText(res);
    }

    protected abstract RecyclerView.LayoutManager getLayoutManager();

    protected abstract void initRecyclerView();
}
