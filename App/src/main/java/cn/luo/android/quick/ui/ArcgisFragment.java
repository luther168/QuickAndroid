package cn.luo.android.quick.ui;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.view.View;

import com.blankj.utilcode.util.SDCardUtils;
import com.esri.android.map.MapView;
import com.esri.android.map.ags.ArcGISLocalTiledLayer;
import com.esri.android.runtime.ArcGISRuntime;

import cn.luo.android.quick.R;
import cn.luo.android.quick.databinding.FragmentArcgisBinding;
import cn.luo.android.quick.library.base.BaseFragment;
import ru.bartwell.exfilepicker.ExFilePicker;
import ru.bartwell.exfilepicker.data.ExFilePickerResult;

/**
 * @author Hurston
 * @version V1.0.0
 * @description
 * @createdTime 2018/10/26 11:01
 * @note:
 */
public class ArcgisFragment extends BaseFragment {

    private static final int REQUEST_CODE_SELECT_TPK_FILE = 1;
    private FragmentArcgisBinding binding;
    private MapView mapView;

    @Override
    protected void initBeforeView() {
        ArcGISRuntime.setClientId("1eFHW78avlnRUPHm");
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.fragment_arcgis;
    }

    @Override
    protected void initView() {
        binding = DataBindingUtil.bind(rootView);

        if (binding != null) {
            mapView = binding.mapView;
            mapView.setMapBackground(0xFFFFFF, 0xFFFFFF, 0, 0);
            binding.ibSelectFile.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ExFilePicker exFilePicker = new ExFilePicker();
                    exFilePicker.setChoiceType(ExFilePicker.ChoiceType.FILES);
                    exFilePicker.start(activity, REQUEST_CODE_SELECT_TPK_FILE);
                }
            });
        }
    }

    private void addTpkLayer(MapView mapView, String filePath) {
        ArcGISLocalTiledLayer localTiledLayer = new ArcGISLocalTiledLayer(filePath);
        mapView.addLayer(localTiledLayer);
    }

    @Override
    protected void initAfterView() {

    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE_SELECT_TPK_FILE) {
            ExFilePickerResult result = ExFilePickerResult.getFromIntent(data);
            if (result != null && result.getCount() > 0) {
                String filePath = result.getPath() + result.getNames().get(0);
                mapView.removeLayer(0);
                addTpkLayer(mapView, filePath);
            }
        }
    }
}
