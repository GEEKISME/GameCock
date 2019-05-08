package com.biotag.gamecock.Widget;

import android.content.Context;
import android.view.View;
import android.widget.FrameLayout;

import com.biotag.gamecock.R;
import com.biotag.gamecock.manager.ThreadManager;
import com.biotag.gamecock.utiles.UiUtils;

import java.util.List;

public abstract class NewLoadingPager extends FrameLayout {

    // 默认的状态
    protected static final int STATE_DEFAULTE = 1;
    // 加载状态
    protected static final int STATE_LOADING = 2;
    // 错误状态
    protected static final int STATE_ERROR = 3;
    // 加载成功。但是服务器没有任何数据。空的状态
    protected static final int STATE_EMPTY = 4;
    // 加载成功的状态
    protected static final int STATE_SUCCEED = 5;

    private View mLoadingView;
    private View mErrorView;
    private View mEmptyView;
    private View mSucceedView;

    // 初始化默认的状态
    protected int mState;

    public NewLoadingPager(Context context) {
        super(context);
        init();
    }


    private void init() {
        mState = STATE_DEFAULTE;// 初始化状态

        // 创建对应的View，并添加到布局中
        mLoadingView = createLoadingView();
        if (null != mLoadingView) {
            addView(mLoadingView, new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
        }

        mErrorView = createErrorView();
        if (null != mErrorView) {
            addView(mErrorView, new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
        }

        mEmptyView = createEmptyView();
        if (null != mEmptyView) {
            addView(mEmptyView, new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
        }
        // 显示对应的View
        showPageSafe();
    }

    private void showPageSafe() {
        UiUtils.runInMainThread(new Runnable() {
            @Override
            public void run() {
                showPage();
            }
        });
    }

    protected void showPage() {
        if (null != mLoadingView) {
            mLoadingView.setVisibility(mState == STATE_DEFAULTE || mState == STATE_LOADING ? View.VISIBLE : View.INVISIBLE);
        }
        if (null != mErrorView) {
            mErrorView.setVisibility(mState == STATE_ERROR ? View.VISIBLE : View.INVISIBLE);
        }
        if (null != mEmptyView) {
            mEmptyView.setVisibility(mState == STATE_EMPTY ? View.VISIBLE : View.INVISIBLE);
        }

        if (mState == STATE_SUCCEED && mSucceedView == null) {
            mSucceedView = createSuccessView();
            addView(mSucceedView, new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
        }

        if (null != mSucceedView) {
            mSucceedView.setVisibility(mState == STATE_SUCCEED ? View.VISIBLE : View.INVISIBLE);
        }

    }

    public void show() {
        if (mState == STATE_ERROR || mState == STATE_EMPTY) {
            mState = STATE_DEFAULTE;
        }

        if (mState == STATE_LOADING || mState == STATE_DEFAULTE) {
            mState = STATE_LOADING;
            LoadingTask task = new LoadingTask();
            ThreadManager.getInstance().execute(task);
        }
        showPageSafe();
    }

    public void setData(int state) {
        mState = state;
    }

    protected View createLoadingView() {
        View loadingView = UiUtils.inflate(R.layout.loading);
        loadingView.setBackgroundColor(UiUtils.getColor(R.color.translucent));
        return loadingView;
    }

    protected View createEmptyView() {
        return UiUtils.inflate(R.layout.loading_page_empty);
    }

    public abstract View createErrorView();

    public abstract View createSuccessView();

    public abstract LoadResult load();

    class LoadingTask implements Runnable {
        @Override
        public void run() {
            final LoadResult loadResult = load();
            UiUtils.runInMainThread(new Runnable() {
                @Override
                public void run() {
                    mState = loadResult.getValue();
                    showPage();
                }
            });
        }
    }


    /**
     * 检查服务器返回的数据是否有问题
     *
     * @param object
     * @return
     */
    public NewLoadingPager.LoadResult check(Object object) {

        if (object == null) {
            return NewLoadingPager.LoadResult.STATE_ERROR;
        }
        // 把当前服务器返回的数据当前子类
        if (object instanceof List) {
            List list = (List) object;
            if (list.size() == 0) {
                return NewLoadingPager.LoadResult.STATE_EMPTY;
            }
        }
        //
        return NewLoadingPager.LoadResult.STATE_SUCEESS;
    }

    public enum LoadResult {
        STATE_ERROR(3), STATE_EMPTY(4), STATE_SUCEESS(5);
        int value;

        LoadResult(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }
    }
}
