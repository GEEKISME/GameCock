package com.biotag.gamecock.Widget;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.biotag.gamecock.R;
import com.biotag.gamecock.utiles.UiUtils;


/**
 * Created by duanfangfang on 2016/9/21.
 */
public abstract class CommonLoadingPager extends NewLoadingPager {

    protected TextView mTvHint;//加载为空时的提示
    protected View mRefreshButton;

    public CommonLoadingPager(Context context) {
        super(context);
    }

    @Override
    public View createLoadingView() {
        return UiUtils.inflate(R.layout.loading);
    }

    @Override
    protected View createEmptyView() {
        View emptyView = UiUtils.inflate(R.layout.empty);
        mTvHint = (TextView) emptyView.findViewById(R.id.tv_hint);
        return emptyView;
    }

    @Override
    public View createErrorView() {
        View refresh_view = UiUtils.inflate(R.layout.refresh);
        mRefreshButton = refresh_view.findViewById(R.id.refreshbutton);
        mRefreshButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                show();
            }
        });
        return refresh_view;
    }


}
