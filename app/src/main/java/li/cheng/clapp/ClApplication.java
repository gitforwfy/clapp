package li.cheng.clapp;

import android.app.Application;

import com.baidu.trace.LBSTraceClient;
import com.baidu.trace.Trace;
import com.baidu.trace.model.OnTraceListener;
import com.baidu.trace.model.PushMessage;
import com.wuzhou.wlibrary.utils.WLog;

/**
 * Created by wfy 2018/1/5 13:24.
 */

public class ClApplication extends Application{

    @Override
    public void onCreate() {
        super.onCreate();

        // 轨迹服务ID
        long serviceId = 157886;
        // 设备标识
        String entityName = "myTrace";
        // 是否需要对象存储服务，默认为：false，关闭对象存储服务。注：鹰眼 Android SDK v3.0以上版本支持随轨迹上传图像等对象数据，若需使用此功能，该参数需设为 true，且需导入bos-android-sdk-1.0.2.jar。
        boolean isNeedObjectStorage = false;
        // 初始化轨迹服务
        Trace mTrace = new Trace(serviceId, entityName, isNeedObjectStorage);
        // 初始化轨迹服务客户端
        LBSTraceClient mTraceClient = new LBSTraceClient(getApplicationContext());


        // 定位周期(单位:秒)
        int gatherInterval = 5;
        // 打包回传周期(单位:秒)
        int packInterval = 10;
        // 设置定位和打包周期
        mTraceClient.setInterval(gatherInterval, packInterval);



        // 初始化轨迹服务监听器
        OnTraceListener mTraceListener = new OnTraceListener() {
            @Override
            public void onBindServiceCallback(int i, String s) {

            }

            // 开启服务回调
            @Override
            public void onStartTraceCallback(int status, String message) {
                WLog.printe("轨迹服务已开启");
            }
            // 停止服务回调
            @Override
            public void onStopTraceCallback(int status, String message) {
                WLog.printe("轨迹服务已停止");
            }
            // 开启采集回调
            @Override
            public void onStartGatherCallback(int status, String message) {
                WLog.printe("开启采集");
            }
            // 停止采集回调
            @Override
            public void onStopGatherCallback(int status, String message) {
                WLog.printe("停止采集");
            }
            // 推送回调
            @Override
            public void onPushCallback(byte messageNo, PushMessage message) {}

            @Override
            public void onInitBOSCallback(int i, String s) {

            }
        };
        // 开启服务
        mTraceClient.startTrace(mTrace, mTraceListener);

        // 开启采集
        mTraceClient.startGather(mTraceListener);
    }
}
