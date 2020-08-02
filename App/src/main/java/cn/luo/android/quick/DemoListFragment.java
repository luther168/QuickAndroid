package cn.luo.android.quick;

import android.view.View;

import androidx.recyclerview.widget.RecyclerView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.chad.library.adapter.base.module.BaseLoadMoreModule;

import java.util.ArrayList;
import java.util.List;

import cn.luo.android.quick.databinding.ItemDemoBinding;
import cn.luo.android.quick.library.base.BaseRecyclerViewFragment;
import cn.luo.android.quick.library.binding.GenericRecyclerViewBindAdapter;
import cn.luo.android.quick.library.view.DividerLinearItemDecoration;

/**
 * @author Hubert
 * @version V1.0
 * @description description
 * @createTime 2020/8/1 22:08
 * @note
 */
@Route(path = AppRouterUtils.FRAGMENT_DEMO_LIST)
public class DemoListFragment extends BaseRecyclerViewFragment {

    private static final String ROW = "row";

    private GenericRecyclerViewBindAdapter<String, ItemDemoBinding> adapter;
    private BaseLoadMoreModule loadMoreModule;

    @Override
    protected RecyclerView.LayoutManager getLayoutManager() {
        return null;
    }

    @Override
    protected void initRecyclerView() {
        recyclerView.addItemDecoration(new DividerLinearItemDecoration(context, DividerLinearItemDecoration.VERTICAL_LIST));

        adapter = new GenericRecyclerViewBindAdapter<>(R.layout.item_demo);
        loadMoreModule = adapter.getLoadMoreModule();
        loadMoreModule.setEnableLoadMore(false);
        loadMoreModule.setOnLoadMoreListener(this);
        recyclerView.setAdapter(adapter);

        refreshLayout.setEnabled(true);
    }

    @Override
    public void onRefresh() {
        loadMoreModule.setEnableLoadMore(false);
        getData(0, new GetDataListener() {
            @Override
            public void onSuccess(List<String> list) {
                adapter.setList(list);
                refreshLayout.setRefreshing(false);
                loadMoreModule.setEnableLoadMore(true);
            }
        });
    }

    @Override
    public void onLoadMore() {
        getData(adapter.getItemCount(), new GetDataListener() {
            @Override
            public void onSuccess(List<String> list) {
                adapter.addData(list);
                loadMoreModule.loadMoreComplete();
                if (adapter.getItemCount() > 100) {
                    loadMoreModule.setEnableLoadMore(false);
                    loadMoreModule.loadMoreEnd();
                }
            }
        });
    }

    private void getData(final int count, final GetDataListener listener) {
        refreshLayout.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (listener != null) {
                    List<String> list = new ArrayList<>();
                    for (int i = 0; i < 30; i++) {
                        list.add(ROW + (i + count + 1));
                    }
                    listener.onSuccess(list);
                }
            }
        }, 1000);
    }

    @Override
    protected void initBeforeView() {

    }

    @Override
    protected void initAfterView() {

    }

    @Override
    public void onClick(View view) {

    }

    interface GetDataListener {
        void onSuccess(List<String> list);
    }
}
