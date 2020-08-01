package cn.luo.android.quick.library.view;

import android.app.Activity;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;

import androidx.annotation.ColorInt;
import androidx.annotation.ColorRes;
import androidx.annotation.DrawableRes;
import androidx.annotation.StringRes;

import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import cn.luo.android.quick.library.R;

/**
 * @author Hurston
 * @version 1.0.0
 * @description
 * @createdTime 2018/11/28 17:01
 * @note
 */
public class TitleView extends RelativeLayout {

    private Context context;

    // View
    private TextView mTvTitle;
    private LinearLayout mLlLeft;
    private ImageView mIvLeft;
    private TextView mTvLeft;
    private LinearLayout mLlRight;
    private ImageView mIvRight;
    private TextView mTvRight;
    private View mVLine;

    private boolean showBottomLine;

    private OnClickTitleViewListener mOnClickTitleViewListener;

    public TitleView(Context context) {
        this(context, null);
    }

    public TitleView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        initView(context);
        initAttrs(context, attrs);
    }

    private void initView(Context context) {
        // Load the layout
        View.inflate(context, R.layout.view_title, this);

        mTvTitle = findViewById(R.id.tvTitle);

        mLlLeft = findViewById(R.id.llLeft);
        mIvLeft = findViewById(R.id.ivLeft);
        mTvLeft = findViewById(R.id.tvLeft);

        mLlRight = findViewById(R.id.llRight);
        mIvRight = findViewById(R.id.ivRight);
        mTvRight = findViewById(R.id.tvRight);

        mVLine = findViewById(R.id.vLine);
    }

    private void initAttrs(Context context, AttributeSet attrs) {
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.TitleView);

        // Title
        int titleBackgroundColor = typedArray.getColor(R.styleable.TitleView_titleBackground, Color.WHITE);
        int titleTextGravity = typedArray.getInt(R.styleable.TitleView_titleTextGravity, Gravity.CENTER);
        String titleText = typedArray.getString(R.styleable.TitleView_titleText);
        int titleTextColor = typedArray.getColor(R.styleable.TitleView_titleTextColor, Color.GRAY);
        int titleTextSize = typedArray.getDimensionPixelSize(R.styleable.TitleView_titleTextSize, sp2px(context, 14));

        // Left
        int leftImageId = typedArray.getResourceId(R.styleable.TitleView_leftImage, 0);
        String leftText = typedArray.getString(R.styleable.TitleView_leftText);
        int leftTextColor = typedArray.getColor(R.styleable.TitleView_leftTextColor, Color.GRAY);
        int leftTextSize = typedArray.getDimensionPixelSize(R.styleable.TitleView_leftTextSize, sp2px(context, 14));

        // Right
        int rightImageId = typedArray.getResourceId(R.styleable.TitleView_rightImage, 0);
        String rightText = typedArray.getString(R.styleable.TitleView_rightText);
        int rightTextColor = typedArray.getColor(R.styleable.TitleView_rightTextColor, Color.GRAY);
        int rightTextSize = typedArray.getDimensionPixelSize(R.styleable.TitleView_rightTextSize, sp2px(context, 14));

        // Bottom Divider
        showBottomLine = typedArray.getBoolean(R.styleable.TitleView_showLine, true);

        // Set the values
        setTitleBarBackground(titleBackgroundColor);
        setTitle(titleText);
        setTitleTextGravity(titleTextGravity);
        setTitleTextSize(titleTextSize);
        setTitleTextColor(titleTextColor);

        setLeftIcon(leftImageId);
        setLeftText(leftText);
        setLeftTextSize(leftTextSize);
        setLeftTextColor(leftTextColor);

        setRightIcon(rightImageId);
        setRightText(rightText);
        setRightTextColor(rightTextColor);
        setRightTextSize(rightTextSize);

        showBottomLine(showBottomLine);

        typedArray.recycle();

        // Set listener to the view
        mLlLeft.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mOnClickTitleViewListener != null) {
                    mOnClickTitleViewListener.onClickLeft(v);
                }
            }
        });
        mLlRight.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mOnClickTitleViewListener != null) {
                    mOnClickTitleViewListener.onClickRight(v);
                }
            }
        });
    }

    private void setTitleTextGravity(int gravity) {
        mTvTitle.setGravity(gravity);
    }

    public void setTitle(String title) {
        if (TextUtils.isEmpty(title)) {
            mTvTitle.setVisibility(GONE);
        } else {
            mTvTitle.setText(title);
            mTvTitle.setVisibility(VISIBLE);
        }
    }

    public void setTitle(@StringRes int resId) {
        String title = getContext().getString(resId);
        if (TextUtils.isEmpty(title)) {
            mTvTitle.setVisibility(GONE);
        } else {
            mTvTitle.setText(title);
            mTvTitle.setVisibility(VISIBLE);
        }
    }

    public void setTitleTextSize(int textSize) {
        mTvTitle.setTextSize(TypedValue.COMPLEX_UNIT_PX, textSize);
    }

    public void setTitleTextColor(@ColorInt int textColor) {
        mTvTitle.setTextColor(textColor);
    }

    public void setLeftText(String text) {
        if (TextUtils.isEmpty(text)) {
            mTvLeft.setVisibility(GONE);
        } else {
            mTvLeft.setVisibility(VISIBLE);
            mTvLeft.setText(text);
        }
    }

    public void setLeftText(@StringRes int textResId) {
        setLeftText(context.getString(textResId));
    }

    public void setLeftTextSize(int textSize) {
        mTvLeft.setTextSize(TypedValue.COMPLEX_UNIT_PX, textSize);
    }

    public void setLeftTextColor(@ColorInt int textColor) {
        mTvLeft.setTextColor(textColor);
    }

    public void setRightText(String text) {
        if (TextUtils.isEmpty(text)) {
            mTvRight.setVisibility(GONE);
        } else {
            mTvRight.setVisibility(VISIBLE);
            mTvRight.setText(text);
        }
    }

    public void setRightText(@StringRes int textResId) {
        setRightText(context.getString(textResId));
    }

    public void setRightTextSize(int textSize) {
        mTvRight.setTextSize(TypedValue.COMPLEX_UNIT_PX, textSize);
    }

    public void setRightTextColor(@ColorInt int textColor) {
        mTvRight.setTextColor(textColor);
    }

    public void setLeftIcon(@DrawableRes int resId) {
        if (resId == 0) {
            mIvLeft.setVisibility(View.GONE);
        } else {
            mIvLeft.setVisibility(View.VISIBLE);
            mIvLeft.setImageResource(resId);
        }
    }

    public void setRightIcon(@DrawableRes int resId) {
        if (resId == 0) {
            mIvRight.setVisibility(View.GONE);
        } else {
            mIvRight.setVisibility(View.VISIBLE);
            mIvRight.setImageResource(resId);
        }
    }

    public void setOnClickTitleViewListener(OnClickTitleViewListener listener) {
        mOnClickTitleViewListener = listener;
    }

    public void showBottomLine(boolean visible) {
        mVLine.setVisibility(visible ? VISIBLE : INVISIBLE);
        showBottomLine = visible;
    }

    public void setShowRight(boolean visible) {
        mLlRight.setVisibility(visible ? VISIBLE : GONE);
    }

    public void setShowLeft(boolean visible) {
        mLlLeft.setVisibility(visible ? VISIBLE : GONE);
    }

    public void setTitleBarBackgroundRes(@ColorRes int resId) {
        setBackgroundResource(resId);
    }

    public void setTitleBarBackground(@ColorInt int resId) {
        setBackgroundColor(resId);
    }

    public static int dp2px(Context context, float dpValue) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dpValue, context.getResources().getDisplayMetrics());
    }

    public static int sp2px(Context context, float spValue) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, spValue, context.getResources().getDisplayMetrics());
    }

    public void setDefaultClickTitleViewListener(final Activity activity) {
        setOnClickTitleViewListener(new OnClickTitleViewListener() {
            @Override
            public void onClickLeft(View view) {
                activity.finish();
            }

            @Override
            public void onClickRight(View view) {

            }
        });
    }

    public interface OnClickTitleViewListener {
        void onClickLeft(View view);

        void onClickRight(View view);
    }
}
