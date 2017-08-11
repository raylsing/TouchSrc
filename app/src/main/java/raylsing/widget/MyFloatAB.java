package raylsing.widget;


import android.animation.ObjectAnimator;
import android.content.Context;
import android.support.design.widget.FloatingActionButton;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.animation.DecelerateInterpolator;

import raylsing.utils.ScreenUtils;


/**
 * Created by Administrator on 2017/7/13.
 */

public class MyFloatAB extends FloatingActionButton {

    private static final String TAG = "MyFloatTV";

    /**
     * 各个屏幕参数
     */
    private int screenWidth;
    private int screenHeight;
    private int screenWidth1tenths;
    private int screenWidth9tenths;
    private int statusHeight;


    /**
     * x、y轴
     */
    private int lastX;
    private int lastY;
    private boolean isDrag;
    private boolean isInEdge;

    public MyFloatAB(Context context) {
        super(context);
        init();
    }

    public MyFloatAB(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public MyFloatAB(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    /**
     * <1/10    >9/10
     */
    private void init() {
        screenWidth = ScreenUtils.getScreenWidth(getContext());
        screenWidth1tenths = screenWidth / 10;
        screenWidth9tenths = screenWidth * 9 / 10;
        screenHeight = ScreenUtils.getScreenHeight(getContext());
        statusHeight = ScreenUtils.getStatusHeight(getContext());
        isInEdge = false;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int rawX = (int) event.getRawX();
        int rawY = (int) event.getRawY();
        switch (event.getAction() & MotionEvent.ACTION_MASK) {
            case MotionEvent.ACTION_DOWN:
                isDrag = false;
                getParent().requestDisallowInterceptTouchEvent(true);
                lastX = rawX;
                lastY = rawY;
                break;
            case MotionEvent.ACTION_MOVE:
                isDrag = true;
                //计算手指移动了多少
                int dx = rawX - lastX;
                int dy = rawY - lastY;
                //这里修复一些华为手机无法触发点击事件的问题
                int distance = (int) Math.sqrt(dx * dx + dy * dy);
                if (distance == 0) {
                    isDrag = false;
                    break;
                }
                float x = getX() + dx;
                float y = getY() + dy;
                //检测是否到达边缘 左上右下
                x = x < 0 ? 0 : x > screenWidth - getWidth() ? screenWidth - getWidth() : x;
                y = y < statusHeight ? statusHeight : y + getHeight() > screenHeight ? screenHeight - getHeight() : y;
                setX(x);
                setY(y);
                lastX = rawX;
                lastY = rawY;
                //Log.i("getX="+getX()+";getY="+getY()+";screenHeight="+screenHeight);
                break;
            case MotionEvent.ACTION_UP:
                if (isDrag) {
                    //恢复按压效果
                    setPressed(false);
                    /**
                     *
                     * 吸附在屏幕留下1/2图标
                     *
                     */
                    if (rawX >= screenWidth9tenths) {
                        animate().setInterpolator(new DecelerateInterpolator())
                                .setDuration(100)
                                .xBy(screenWidth - getWidth() - getX() + getWidth() / 2)
                                .start();
                        isInEdge = true;
                    } else if (rawX <= screenWidth1tenths) {
                        ObjectAnimator oa = ObjectAnimator.ofFloat(this, "x", getX(), -(getWidth() / 2));
                        oa.setInterpolator(new DecelerateInterpolator());
                        oa.setDuration(100);
                        oa.start();
                        isInEdge = true;
                    }
                }
                break;
        }
        //如果是拖拽则消耗事件，否则正常传递即可。
        return isDrag || super.onTouchEvent(event);
    }

    /**
     * 判断按钮是否置于屏幕边缘
     *
     * @return
     */
    private boolean isInEdge() {
        return isInEdge;
    }


}
