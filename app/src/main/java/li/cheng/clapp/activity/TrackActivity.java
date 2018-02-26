package li.cheng.clapp.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ZoomControls;

import com.baidu.mapapi.SDKInitializer;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.MapStatus;
import com.baidu.mapapi.map.MapStatusUpdate;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.MarkerOptions;
import com.baidu.mapapi.map.OverlayOptions;
import com.baidu.mapapi.model.LatLng;
import com.baidu.trace.LBSTraceClient;
import com.baidu.trace.Trace;
import com.baidu.trace.api.track.HistoryTrackRequest;
import com.baidu.trace.api.track.HistoryTrackResponse;
import com.baidu.trace.api.track.OnTrackListener;
import com.baidu.trace.api.track.TrackPoint;
import com.baidu.trace.model.OnTraceListener;
import com.baidu.trace.model.PushMessage;
import com.wuzhou.wlibrary.utils.WLog;

import java.util.ArrayList;
import java.util.List;

import li.cheng.clapp.CActivity;
import li.cheng.clapp.ClApplication;
import li.cheng.clapp.R;

public class TrackActivity extends CActivity {
    LBSTraceClient mTraceClient;
    private MapView mMapView = null;
    BaiduMap mBaiduMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        WLog.printe("TrackActivity----onCreate");
        SDKInitializer.initialize(getApplicationContext());
        setContentView(R.layout.activity_track);
        initView();
        init();
    }

    private void initView() {
        mMapView = (MapView) findViewById(R.id.bmapView);
        mBaiduMap=mMapView.getMap();
        // 隐藏logo
        View child = mMapView.getChildAt(1);
        if (child != null && (child instanceof ImageView || child instanceof ZoomControls)){
            child.setVisibility(View.INVISIBLE);
        }
        //地图上比例尺
        mMapView.showScaleControl(false);
        // 隐藏缩放控件
        mMapView.showZoomControls(false);
    }

    OnTraceListener mTraceListener;
    private void init() {
        // 轨迹服务ID
        long serviceId = ClApplication.serviceId;
        // 设备标识
//        String entityName = "chengli";
        String entityName = "wangfengyuan";
        // 是否需要对象存储服务，默认为：false，关闭对象存储服务。注：鹰眼 Android SDK v3.0以上版本支持随轨迹上传图像等对象数据，若需使用此功能，该参数需设为 true，且需导入bos-android-sdk-1.0.2.jar。
        boolean isNeedObjectStorage = false;
        // 初始化轨迹服务
        Trace mTrace = new Trace(serviceId, entityName, isNeedObjectStorage);
        // 初始化轨迹服务客户端
        mTraceClient = new LBSTraceClient(getApplicationContext());


        // 定位周期(单位:秒)
        int gatherInterval = 5;
        // 打包回传周期(单位:秒)
        int packInterval = 10;
        // 设置定位和打包周期
        mTraceClient.setInterval(gatherInterval, packInterval);



        // 初始化轨迹服务监听器
       mTraceListener = new OnTraceListener() {
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

    public void start(View view) {

        // 请求标识
        int tag = 1;
        // 轨迹服务ID
        long serviceId = ClApplication.serviceId;
        // 设备标识
//        String entityName = user.getSweet_user().getId();
        String entityName = "myTrace";



        // 创建历史轨迹请求实例
        HistoryTrackRequest historyTrackRequest = new HistoryTrackRequest(tag, serviceId, entityName);

        //设置轨迹查询起止时间
        // 开始时间(单位：秒)
        long startTime = System.currentTimeMillis() / 1000 - 12 * 60 * 60;
        // 结束时间(单位：秒)
        long endTime = System.currentTimeMillis() / 1000;
        // 设置开始时间
        historyTrackRequest.setStartTime(startTime);
        // 设置结束时间
        historyTrackRequest.setEndTime(endTime);


        // 初始化轨迹监听器
        OnTrackListener mTrackListener = new OnTrackListener() {
            // 历史轨迹回调
            @Override
            public void onHistoryTrackCallback(HistoryTrackResponse response) {

                WLog.printe("onHistoryTrackCallback");
                List<TrackPoint> points = response.getTrackPoints();//获取轨迹点

                for (TrackPoint trackPoint : points) {
                    //将轨迹点转化为地图画图层的LatLng类
                    com.baidu.trace.model.LatLng lating=trackPoint.getLocation();
                    LatLng cl=new LatLng(lating.getLatitude(),lating.getLongitude());
                    pointList.add(cl);
                }
//                if(!pointList.isEmpty()){
//                    if(pointList.size()>2){
//                        OverlayOptions ooPolyline = new PolylineOptions().width(10)
//                                .color(0xAAFF0000).points(pointList);
//                        mBaiduMap.addOverlay(ooPolyline);
//                    }else{
//                        LatLng point = pointList.get(0);
//                        //构建Marker图标
//                        BitmapDescriptor bitmap = BitmapDescriptorFactory
//                                .fromResource(R.mipmap.icon_point);
//                        //构建MarkerOption，用于在地图上添加Marker
//                        OverlayOptions option = new MarkerOptions()
//                                .position(point)
//                                .icon(bitmap);
//                        //在地图上添加Marker，并显示
//                        mBaiduMap.addOverlay(option);
//                    }
//                }
        if(!pointList.isEmpty()){
            LatLng point = pointList.get(0);
        //                //构建Marker图标
            BitmapDescriptor bitmap = BitmapDescriptorFactory
                    .fromResource(R.mipmap.icon_point);
            //构建MarkerOption，用于在地图上添加Marker
            OverlayOptions option = new MarkerOptions()
                    .position(point)
                    .icon(bitmap);
            //在地图上添加Marker，并显示
            mBaiduMap.addOverlay(option);

            MapStatus mMapStatus = new MapStatus.Builder()
                    //要移动的点
                    .target(point)
                    //放大地图到20倍
                    .zoom(18)
                    .build();
            //定义MapStatusUpdate对象，以便描述地图状态将要发生的变化
            MapStatusUpdate mMapStatusUpdate = MapStatusUpdateFactory.newMapStatus(mMapStatus);

            //改变地图状态
            mBaiduMap.setMapStatus(mMapStatusUpdate);
        }


            }
        };

        // 查询历史轨迹
        mTraceClient.queryHistoryTrack(historyTrackRequest, mTrackListener);
    }
    private List<LatLng> pointList = new ArrayList<>();//轨迹点集合

    public void stop(View view) {
        mTraceClient.stopGather(mTraceListener);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //在activity执行onDestroy时执行mMapView.onDestroy()，实现地图生命周期管理
        mMapView.onDestroy();
    }

    @Override
    protected void onResume() {
        super.onResume();
        //在activity执行onResume时执行mMapView. onResume ()，实现地图生命周期管理
        mMapView.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        //在activity执行onPause时执行mMapView. onPause ()，实现地图生命周期管理
        mMapView.onPause();
    }
}
