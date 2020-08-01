package cn.luo.android.quick.library.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;

import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.view.View;

/**
 * @author Hurston
 * @version 1.0.0
 * @description
 * @createdTime 2018/11/28 17:01
 * @note
 */
public class DividerGridItemDecoration extends RecyclerView.ItemDecoration {

    private static final int[] ATTRS = new int[]{
            android.R.attr.listDivider
    };

    private Drawable mDivider;
    private Paint mPaint;
    private int mDividerHeight = 2;

    private Rect rightBounds;

    public DividerGridItemDecoration(Context context) {
        final TypedArray a = context.obtainStyledAttributes(ATTRS);
        mDivider = a.getDrawable(0);
        a.recycle();
    }

    public DividerGridItemDecoration(Context context, int drawableId) {
        mDivider = ContextCompat.getDrawable(context, drawableId);
        if (mDivider != null) {
            mDividerHeight = mDivider.getIntrinsicHeight();
        }
    }

    public DividerGridItemDecoration(Context context, int dividerHeight, int dividerColor) {
        mDividerHeight = dividerHeight;
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setColor(context.getResources().getColor(dividerColor));
        mPaint.setStyle(Paint.Style.FILL);
    }

    @Override
    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
        drawHorizontal(c, parent);
        drawVertical(c, parent);
    }

    private int getSpanCount(RecyclerView parent) {
        int spanCount = -1;
        RecyclerView.LayoutManager layoutManager = parent.getLayoutManager();
        if (layoutManager instanceof GridLayoutManager) {
            spanCount = ((GridLayoutManager) layoutManager).getSpanCount();
        } else if (layoutManager instanceof StaggeredGridLayoutManager) {
            spanCount = ((StaggeredGridLayoutManager) layoutManager).getSpanCount();
        }
        return spanCount;
    }

    private void drawHorizontal(Canvas c, RecyclerView parent) {
        int childCount = parent.getChildCount();

        final int spanCount = getSpanCount(parent);
        for (int i = 0; i < childCount; i++) {
            final View child = parent.getChildAt(i);
            if (child.getLayoutParams() instanceof RecyclerView.LayoutParams) {
                final RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child.getLayoutParams();
                int left = child.getLeft() - params.leftMargin;
                int right = child.getRight() + params.rightMargin + mDividerHeight;
                int top = child.getTop() - params.topMargin - mDividerHeight;
                int bottom = top + mDividerHeight;
                if (mDivider != null) {
                    mDivider.setBounds(left, top, right, bottom);
                    mDivider.draw(c);
                }
                if (mPaint != null) {
                    c.drawRect(left, top, right, bottom, mPaint);
                }
                if (isLastRow(parent, i, spanCount, childCount)) {
                    left = child.getLeft() - params.leftMargin;
                    right = child.getRight() + params.rightMargin + mDividerHeight;
                    top = child.getBottom() + params.bottomMargin;
                    bottom = top + mDividerHeight;
                    if (mDivider != null) {
                        mDivider.setBounds(left, top, right, bottom);
                        mDivider.draw(c);
                    }
                    if (mPaint != null) {
                        c.drawRect(left, top, right, bottom, mPaint);
                    }
                }
            }
        }
    }

    private void drawVertical(Canvas c, RecyclerView parent) {
        int childCount = parent.getChildCount();

        final int spanCount = getSpanCount(parent);
        for (int i = 0; i < childCount; i++) {
            final View child = parent.getChildAt(i);
            if (child.getLayoutParams() instanceof RecyclerView.LayoutParams) {
                final RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child.getLayoutParams();
                int top = child.getTop() - params.topMargin;
                int bottom = child.getBottom() + params.bottomMargin;
                int left = child.getLeft() - params.leftMargin;
                int right = left + mDividerHeight;
                if (mDivider != null) {
                    mDivider.setBounds(left, top, right, bottom);
                    mDivider.draw(c);
                }
                if (mPaint != null) {
                    c.drawRect(left, top, right, bottom, mPaint);
                }
                if (isLastColumn(parent, i, spanCount, childCount)) {
                    top = child.getTop() - params.topMargin;
                    bottom = child.getBottom() + params.bottomMargin;
                    left = child.getRight() + params.rightMargin;
                    right = left + mDividerHeight;
                    if (rightBounds == null) {
                        rightBounds = new Rect(left, top, right, bottom);
                    } else {
                        if (left < rightBounds.left) {
                            left = rightBounds.left;
                            right = rightBounds.right;
                        }
                    }
                    if (mDivider != null) {
                        mDivider.setBounds(left, top, right, bottom);
                        mDivider.draw(c);
                    }
                    if (mPaint != null) {
                        c.drawRect(left, top, right, bottom, mPaint);
                    }
                }
            }
        }
    }

    private boolean isLastColumn(RecyclerView parent, int pos, int spanCount, int childCount) {
        RecyclerView.LayoutManager layoutManager = parent.getLayoutManager();
        if (layoutManager instanceof GridLayoutManager) {
            return (pos + 1) % spanCount == 0;
        } else if (layoutManager instanceof StaggeredGridLayoutManager) {
            int orientation = ((StaggeredGridLayoutManager) layoutManager).getOrientation();
            if (orientation == StaggeredGridLayoutManager.VERTICAL) {
                return (pos + 1) % spanCount == 0;
            } else {
                childCount = childCount - childCount % spanCount;
                return pos >= childCount;
            }
        }
        return false;
    }

    private boolean isLastRow(RecyclerView parent, int pos, int spanCount, int childCount) {
        RecyclerView.LayoutManager layoutManager = parent.getLayoutManager();
        if (layoutManager instanceof GridLayoutManager) {
            int remainder = childCount % spanCount;
            if (remainder == 0) {
                remainder = spanCount;
            }
            childCount = childCount - remainder;
            return pos >= childCount;
        } else if (layoutManager instanceof StaggeredGridLayoutManager) {
            int orientation = ((StaggeredGridLayoutManager) layoutManager).getOrientation();
            if (orientation == StaggeredGridLayoutManager.VERTICAL) {
                int remainder = childCount % spanCount;
                if (remainder == 0) {
                    remainder = spanCount;
                }
                childCount = childCount - remainder;
                return pos > childCount;
            } else {
                return (pos + 1) % spanCount == 0;
            }
        }
        return false;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        int spanCount = getSpanCount(parent);
        int childCount = parent.getAdapter().getItemCount();
        int itemPosition = parent.getChildAdapterPosition(view);
        if (isLastRow(parent, itemPosition, spanCount, childCount)) {
            outRect.set(0, 0, mDividerHeight, 0);
        } else if (isLastColumn(parent, itemPosition, spanCount, childCount)) {
            outRect.set(0, 0, 0, mDividerHeight);
        } else {
            outRect.set(0, 0, mDividerHeight, mDividerHeight);
        }
    }
}
