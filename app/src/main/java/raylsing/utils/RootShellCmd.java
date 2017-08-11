package raylsing.utils;

import android.util.Log;

import java.io.OutputStream;

import static android.content.ContentValues.TAG;

/**
 * 用root权限执行Linux下的Shell指令
 * Created by Administrator on 2017/7/13.
 */

public class RootShellCmd {

    private OutputStream os;

    /**
     * 执行shell指令
     *
     * @param cmd 指令
     */
    public final void exec(String cmd) {
        Log.e(TAG, "exec: " );
        try {
            if (os == null) {
                os = Runtime.getRuntime().exec("su").getOutputStream();
            }
            os.write(cmd.getBytes());
            os.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 后台模拟全局按键
     *
     * @param keyCode 键值
     */
    public final void simulateKey(int keyCode) {

        exec("input keyevent " + keyCode + "\n");
    }

    /**
     * 后台模拟点击坐标
     *
     * @param x 横坐标
     * @param y 纵坐标
     */
    public final void clickPoint(int x, int y) {
        Log.e(TAG, "clickPoint: " );
        exec("input keyevent " + x + " " + y + "\n");
    }

}
