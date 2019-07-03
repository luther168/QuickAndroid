package cn.luo.android.quick.util;

import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

public class QuickTextWatch implements TextWatcher {

    private String beforeText;
    private EditText editText;
    private Validator validator;

    public QuickTextWatch(EditText editText, Validator validator) {
        this.editText = editText;
        this.validator = validator;
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        this.beforeText = s.toString();
    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        String afterText = s.toString();
        if (validator != null && !validator.isValidated(s)) {
            int difLength = afterText.length() - beforeText.length();
            editText.setText(beforeText);
            editText.setSelection(afterText.length() - difLength);
        }
    }

    @Override
    public void afterTextChanged(Editable s) {

    }

    public interface Validator {
        boolean isValidated(CharSequence s);
    }
}