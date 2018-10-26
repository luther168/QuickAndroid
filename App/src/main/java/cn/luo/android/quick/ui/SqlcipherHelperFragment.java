package cn.luo.android.quick.ui;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.text.TextUtils;
import android.view.View;
import android.widget.RadioGroup;

import com.blankj.utilcode.util.ToastUtils;

import net.sqlcipher.database.SQLiteDatabase;

import java.io.File;

import cn.luo.android.quick.R;
import cn.luo.android.quick.databinding.FragmentSqlcipherHelperBinding;
import cn.luo.android.quick.entity.Signal;
import cn.luo.android.quick.library.base.BaseFragment;
import cn.luo.android.quick.util.DbEncrypt;
import cn.luo.android.quick.util.RxJavaUtils;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import ru.bartwell.exfilepicker.ExFilePicker;
import ru.bartwell.exfilepicker.data.ExFilePickerResult;

/**
 * @author Hurston
 * @version V1.0.0
 * @description
 * @createdTime 2018/10/12 16:13
 * @note:
 */
public class SqlcipherHelperFragment extends BaseFragment {

    private static final int REQUEST_CODE_SELECT_SOURCE_FILE_PATH = 88;
    private static final int REQUEST_CODE_SELECT_TARGET_DIR = 888;
    private static final int TYPE_ENCRYPT = 352;
    private static final int TYPE_DECRYPT = 353;

    private FragmentSqlcipherHelperBinding binding;
    private ExFilePicker exFilePicker = new ExFilePicker();
    private int operationType = TYPE_ENCRYPT;
    private Signal signal = new Signal();

    @Override
    protected void initBeforeView() {
        SQLiteDatabase.loadLibs(context);
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.fragment_sqlcipher_helper;
    }

    @Override
    protected void initView() {
        binding = DataBindingUtil.bind(rootView);
        if (binding != null) {
            binding.setListener(this);
            binding.rgType.check(R.id.rbEncrypt);
            binding.rgType.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(RadioGroup group, int checkedId) {
                    if (checkedId == R.id.rbEncrypt) {
                        binding.tvLabelSrcFilePath.setText(R.string.label_decrypted_database_file_path);
                        operationType = TYPE_ENCRYPT;
                    } else {
                        binding.tvLabelSrcFilePath.setText(R.string.label_encrypted_database_file_path);
                        operationType = TYPE_DECRYPT;
                    }
                }
            });
        }
    }

    @Override
    protected void initAfterView() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnSelectSrcFilePath:
                exFilePicker.setChoiceType(ExFilePicker.ChoiceType.FILES);
                exFilePicker.start(activity, REQUEST_CODE_SELECT_SOURCE_FILE_PATH);
                break;
            case R.id.btnSelectTargetDirPath:
                exFilePicker.setChoiceType(ExFilePicker.ChoiceType.DIRECTORIES);
                exFilePicker.start(activity, REQUEST_CODE_SELECT_TARGET_DIR);
                break;
            case R.id.btnStart:
                doAction(operationType);
                break;
        }
    }

    void doAction(final int type) {
        RxJavaUtils.doIOTask(new ObservableOnSubscribe<Signal>() {
            @Override
            public void subscribe(@NonNull ObservableEmitter<Signal> emitter) throws Exception {
                String sourceDb = binding.tvSrcFilePath.getText().toString();
                if (TextUtils.isEmpty(sourceDb)) {
                    ToastUtils.showShort(R.string.select_source_db);
                    return;
                }
                String targetDirPath = binding.tvTargetDirPath.getText().toString();
                if (TextUtils.isEmpty(targetDirPath)) {
                    ToastUtils.showShort(R.string.select_target_dir);
                    return;
                }
                String filename = binding.etTargetFilename.getText().toString();
                if (TextUtils.isEmpty(filename)) {
                    ToastUtils.showShort(R.string.input_filename);
                    return;
                }
                String targetDb = targetDirPath + filename;
                String key = binding.etKey.getText().toString();
                if (TextUtils.isEmpty(key)) {
                    ToastUtils.showShort(R.string.input_key);
                    return;
                }
                boolean success = false;
                switch (type) {
                    case TYPE_ENCRYPT:
                        success = DbEncrypt.encrypt(sourceDb, targetDb, key);
                        break;
                    case TYPE_DECRYPT:
                        success = DbEncrypt.decrypt(sourceDb, targetDb, key);
                        break;
                }
                signal.setType(success ? Signal.TYPE_SUCCESS : Signal.TYPE_FAILED);
                emitter.onNext(signal);
            }
        }).subscribe(new Observer<Signal>() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {

            }

            @Override
            public void onNext(@NonNull Signal signal) {
                switch (signal.getType()) {
                    case Signal.TYPE_SUCCESS:
                        ToastUtils.showShort(R.string.operate_suc);
                        break;
                    case Signal.TYPE_FAILED:
                        ToastUtils.showShort(R.string.operate_failed);
                        break;
                }
                onComplete();
            }

            @Override
            public void onError(@NonNull Throwable e) {
                e.printStackTrace();
                ToastUtils.showShort(R.string.operate_error);
            }

            @Override
            public void onComplete() {
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE_SELECT_SOURCE_FILE_PATH) {
            ExFilePickerResult result = ExFilePickerResult.getFromIntent(data);
            if (result != null && result.getCount() > 0) {
                String srcFilePath = result.getPath() + result.getNames().get(0);
                binding.tvSrcFilePath.setText(srcFilePath);
                String targetDirPath = result.getNames().get(0).replace(".", "_after.");
                binding.etTargetFilename.setText(targetDirPath);
            }
            return;
        }
        if (requestCode == REQUEST_CODE_SELECT_TARGET_DIR) {
            ExFilePickerResult result = ExFilePickerResult.getFromIntent(data);
            if (result != null && result.getCount() > 0) {
                String targetDirPath = result.getPath() + result.getNames().get(0) + File.separator;
                binding.tvTargetDirPath.setText(targetDirPath);
            }
        }
    }
}
