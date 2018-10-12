package cn.luo.android.quick.ui;

import android.databinding.DataBindingUtil;
import android.support.v4.util.SparseArrayCompat;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;

import com.blankj.utilcode.util.TimeUtils;
import com.blankj.utilcode.util.ToastUtils;

import java.util.ArrayList;
import java.util.List;

import cn.luo.android.quick.App;
import cn.luo.android.quick.R;
import cn.luo.android.quick.databinding.TopViewDatabaseBinding;
import cn.luo.android.quick.entity.DatabaseObj;
import cn.luo.android.quick.entity.Note;
import cn.luo.android.quick.library.base.BaseRecyclerViewFragment;
import cn.luo.android.quick.library.binding.GenericRecyclerViewBindAdapter;
import cn.luo.android.quick.library.view.DividerGridItemDecoration;
import cn.luo.android.quick.library.view.DividerLinearItemDecoration;
import cn.luo.android.quick.util.QuickTextWatch;
import io.objectbox.Box;

/**
 * @author Hurston
 * @version V1.0.0
 * @description
 * @createdTime 2018/9/28 17:02
 * @note:
 */
public class DatabaseFragment extends BaseRecyclerViewFragment {

    private static final int KEY_TITLE = 0;
    private static final int KEY_OBJECT_BOX = 1;

    private GenericRecyclerViewBindAdapter<String> adapter;
    private SparseArrayCompat<DatabaseObj> databaseObjSparseArray = new SparseArrayCompat<>();

    private EditText etCount;

    @Override
    protected RecyclerView.LayoutManager getLayoutManager() {
        return new GridLayoutManager(context, 5);
    }

    @Override
    protected void initRecyclerView() {
        recyclerView.addItemDecoration(new DividerGridItemDecoration(context));

        adapter = new GenericRecyclerViewBindAdapter<>(R.layout.item_database_obj, this);

        databaseObjSparseArray.put(KEY_TITLE, new DatabaseObj("Insert", "Update", "Query", "Delete"));
        databaseObjSparseArray.put(KEY_OBJECT_BOX, new DatabaseObj("ObjectBox", "", "", "", ""));
        adapter.replaceData(getStringList(databaseObjSparseArray));

        adapter.bindToRecyclerView(recyclerView);
    }

    private List<String> getStringList(SparseArrayCompat<DatabaseObj> sparseArray) {
        List<String> list = new ArrayList<>();
        for (int i = 0; i < sparseArray.size(); i++) {
            DatabaseObj databaseObj = sparseArray.get(sparseArray.keyAt(i));
            list.addAll(databaseObj.toStringList());
        }
        return list;
    }

    @Override
    protected void initBeforeView() {

    }

    @Override
    protected void initAfterView() {
        TopViewDatabaseBinding topViewDatabaseBinding = DataBindingUtil.inflate(inflater,
                R.layout.top_view_database, null, false);
        topViewDatabaseBinding.setListener(this);
        etCount = topViewDatabaseBinding.etCount;
        etCount.addTextChangedListener(new QuickTextWatch(etCount, new QuickTextWatch.Validator() {
            @Override
            public boolean isValidated(CharSequence s) {
                String afterText = s.toString();
                return TextUtils.isEmpty(afterText) || afterText.matches("^[1-9]\\d*|0$");
            }
        }));
        addTopView(topViewDatabaseBinding.getRoot());
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnStart:
                String countStr = etCount.getText().toString();
                if (TextUtils.isEmpty(countStr)) {
                    ToastUtils.showShort(R.string.input_count);
                } else {
                    int count = Integer.valueOf(countStr);
                    if (count > 0) {
                        List<Note> noteList = new ArrayList<>();
                        Note baseNote = new Note();
                        baseNote.content =  "content";
                        baseNote.createdTime = TimeUtils.getNowDate();
                        baseNote.modifiedTime = TimeUtils.getNowDate();
                        for (int i = 0; i < count; i++) {
                            noteList.add(new Note(baseNote));
                        }

                        Box<Note> noteBox = App.getBoxStore().boxFor(Note.class);
                        long startTime = System.currentTimeMillis();
                        noteBox.put(noteList);
                        long endTime = System.currentTimeMillis();
                        long costTime = endTime - startTime;
                        DatabaseObj databaseObj = databaseObjSparseArray.get(KEY_OBJECT_BOX);
                        databaseObj.setInsertTime(costTime);
                        adapter.setData(KEY_OBJECT_BOX * 5 + 1, databaseObj.getInsertTime());
                    }
                }
                break;
        }
    }
}
