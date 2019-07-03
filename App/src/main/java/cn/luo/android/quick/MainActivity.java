package cn.luo.android.quick;

import android.databinding.DataBindingUtil;
import android.graphics.Color;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;

import java.util.ArrayList;
import java.util.List;

import cn.luo.android.quick.databinding.ActivityMainBinding;
import cn.luo.android.quick.databinding.ItemMenuBinding;
import cn.luo.android.quick.library.base.BaseActivity;
import cn.luo.android.quick.library.base.BaseFragment;
import cn.luo.android.quick.library.binding.GenericRecyclerViewBindAdapter;

public class MainActivity extends BaseActivity {

    private String curFragmentPath;

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initBeforeView() {

    }

    @Override
    protected void initView() {
        ActivityMainBinding binding = DataBindingUtil.bind(findViewById(R.id.llMain));
        if (binding != null) {
            final Toolbar toolbar = binding.toolbar;
            setSupportActionBar(toolbar);

            final DrawerLayout drawerLayout = binding.drawerLayout;
            ActionBarDrawerToggle drawerToggle = new ActionBarDrawerToggle(this, drawerLayout,
                    toolbar, R.string.open, R.string.close) {
                @Override
                public void onDrawerOpened(View drawerView) {
                    super.onDrawerOpened(drawerView);
                }

                @Override
                public void onDrawerClosed(View drawerView) {
                    super.onDrawerClosed(drawerView);
                }
            };
            drawerToggle.syncState();
            drawerLayout.addDrawerListener(drawerToggle);

            RecyclerView recyclerViewLeft = binding.recyclerViewLeft;
            recyclerViewLeft.setLayoutManager(new LinearLayoutManager(context));
            List<MainMenu> list = new ArrayList<>();
            list.add(new MainMenu(getString(R.string.about), AppRouterUtils.FRAGMENT_ABOUT));
            final GenericRecyclerViewBindAdapter<MainMenu, ItemMenuBinding> adapter = new GenericRecyclerViewBindAdapter<>(R.layout.item_menu, list);
            adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
                @Override
                public void onItemClick(BaseQuickAdapter baseQuickAdapter, View view, int position) {
                    MainMenu mainMenu = adapter.getItem(position);
                    if (mainMenu != null) {
                        if (!TextUtils.equals(mainMenu.getPath(), curFragmentPath)) {
                            BaseFragment baseFragment = AppRouterUtils.getBaseFragment(mainMenu.getPath());
                            switchFragment(baseFragment, R.id.flContainer);
                            toolbar.setTitle(mainMenu.getTitle());
                            curFragmentPath = mainMenu.getPath();
                        }
                    }
                    drawerLayout.closeDrawers();
                }
            });
            recyclerViewLeft.setAdapter(adapter);
            MainMenu mainMenu = adapter.getItem(0);
            if (mainMenu != null) {
                BaseFragment baseFragment = AppRouterUtils.getBaseFragment(mainMenu.getPath());
                switchFragment(baseFragment, R.id.flContainer);
                toolbar.setTitle(mainMenu.getTitle());
                curFragmentPath = mainMenu.getPath();
            }
        }
    }

    @Override
    protected void initAfterView() {

    }

    @Override
    protected int getFinishType() {
        return 0;
    }
}
