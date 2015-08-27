package com.zhy.base.loadandretry;

import android.content.Context;
import android.os.Looper;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;

/**
 * Created by zhy on 15/8/26.
 */
public class LoadingAndRetryLayout extends FrameLayout
{
    private View mLoadingView;
    private View mRetryView;
    private View mContentView;
    private LayoutInflater mInflater;


    public LoadingAndRetryLayout(Context context, AttributeSet attrs, int defStyleAttr)
    {
        super(context, attrs, defStyleAttr);
        mInflater = LayoutInflater.from(context);
    }


    public LoadingAndRetryLayout(Context context, AttributeSet attrs)
    {
        this(context, attrs, -1);
    }

    public LoadingAndRetryLayout(Context context)
    {
        this(context, null);
    }

    private boolean isMainThread()
    {
        return Looper.myLooper() == Looper.getMainLooper();
    }

    public void showLoading()
    {
        if (isMainThread())
        {
            showView(mLoadingView);
        } else
        {
            post(new Runnable()
            {
                @Override
                public void run()
                {
                    showView(mLoadingView);
                }
            });
        }
    }

    public void showRetry()
    {
        if (isMainThread())
        {
            showView(mRetryView);
        } else
        {
            post(new Runnable()
            {
                @Override
                public void run()
                {
                    showView(mRetryView);
                }
            });
        }

    }

    public void showContent()
    {
        if (isMainThread())
        {
            showView(mContentView);
        } else
        {
            post(new Runnable()
            {
                @Override
                public void run()
                {
                    showView(mContentView);
                }
            });
        }
    }


    private void showView(View view)
    {
        if (view == null) return;

        if (view == mLoadingView)
        {
            mLoadingView.setVisibility(View.VISIBLE);
            if (mRetryView != null)
                mRetryView.setVisibility(View.GONE);
            if (mContentView != null)
                mContentView.setVisibility(View.GONE);
        } else if (view == mRetryView)
        {
            mRetryView.setVisibility(View.VISIBLE);
            if (mLoadingView != null)
                mLoadingView.setVisibility(View.GONE);
            if (mContentView != null)
                mContentView.setVisibility(View.GONE);
        } else if (view == mContentView)
        {
            mContentView.setVisibility(View.VISIBLE);
            if (mLoadingView != null)
                mLoadingView.setVisibility(View.GONE);
            if (mRetryView != null)
                mRetryView.setVisibility(View.GONE);
        }


    }

    public View setContentView(int layoutId)
    {
        mContentView = mInflater.inflate(layoutId, this, false);
        addView(mContentView);
        return mContentView;
    }

    public View setLoadingView(int layoutId)
    {
        mLoadingView = mInflater.inflate(layoutId, this, false);
        addView(mLoadingView);
        return mLoadingView;
    }

    public View setRetryView(int layoutId)
    {
        mRetryView = mInflater.inflate(layoutId, this, false);
        addView(mRetryView);
        return mRetryView;
    }

    public void setLoadingView(View view)
    {
        mLoadingView = view;
        addView(mLoadingView);
    }


    public void setRetryView(View view)
    {
        mRetryView = view;
        addView(mRetryView);
    }

    public void setContentView(View view)
    {
        mContentView = view;
        addView(mContentView);
    }

    public View getRetryView()
    {
        return mRetryView;
    }

}
