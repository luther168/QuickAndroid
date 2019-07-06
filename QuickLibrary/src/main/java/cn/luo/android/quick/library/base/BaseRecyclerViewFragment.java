package cn.luo.android.quick.library.base;

import android.support.annotation.StringRes;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ajguan.library.EasyRefreshLayout;
import com.ajguan.library.LoadModel;

import cn.luo.android.quick.library.R;

/**
 * @author      Hurston
 * @version     1.0.0
 * @description
 * @createdTime 2018/11/28 16:17
 * @note
 */
public abstract class BaseRecyclerViewFragment extends BaseFragment {

    protected RecyclerView recyclerView;
    protected EasyRefreshLayout easyRefreshLayout;
    protected View emptyView;
    private TextView tvEmpty;
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
        tvEmpty = emptyView.findViewById(R.id.tvEmpty);

        llTop = rootView.findViewById(R.id.llTop);

        initRecyclerView();
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
