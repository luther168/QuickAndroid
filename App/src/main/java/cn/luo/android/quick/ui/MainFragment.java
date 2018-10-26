package cn.luo.android.quick.ui;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;

import java.util.ArrayList;
import java.util.List;

import cn.luo.android.quick.R;
import cn.luo.android.quick.entity.Demo;
import cn.luo.android.quick.library.base.BaseRecyclerViewFragment;
import cn.luo.android.quick.library.binding.GenericRecyclerViewBindAdapter;
import cn.luo.android.quick.library.view.DividerLinearItemDecoration;
import cn.luo.android.quick.util.RouterUtils;

/**
 * AUTHOR:       Luo
 * VERSION:      V1.0
 * DESCRIPTION:  description
 * CREATE TIME:  2018/4/3 17:50
 * NOTE:
 */
public class MainFragment extends BaseRecyclerViewFragment {

    @Override
    protected RecyclerView.LayoutManager getLayoutManager() {
        return null;
    }

    @Override
    protected void initRecyclerView() {
        recyclerView.addItemDecoration(new DividerLinearItemDecoration(context, DividerLinearItemDecoration.VERTICAL_LIST));

        final GenericRecyclerViewBindAdapter<Demo> adapter = new GenericRecyclerViewBindAdapter<>(R.layout.item_demo_binding, this);
        recyclerView.setAdapter(adapter);

        List<Demo> demoList = getDemoList();
        adapter.replaceData(demoList);

        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter baseQuickAdapter, View view, int position) {
                Demo demo = adapter.getItem(position);
                if (demo != null) {
                    RouterUtils.toActivity(demo.getRouterUrl());
                }
            }
        });
    }

    private List<Demo> getDemoList() {
        List<Demo> demoList = new ArrayList<>();
        demoList.add(new Demo(R.string.about, RouterUtils.ACTIVITY_ABOUT));
        demoList.add(new Demo(R.string.database, RouterUtils.ACTIVITY_DATABASE));
        demoList.add(new Demo(R.string.sqlcipher_helper, RouterUtils.ACTIVITY_SQLCIPHER_HELPER));
        return demoList;
    }

    @Override
    protected void initBeforeView() {

    }

    @Override
    protected void initAfterView() {

    }

    @Override
    public void onClick(View v) {

    }
}
