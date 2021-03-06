package com.notification;

import android.app.Notification;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.TaskStackBuilder;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.RemoteViews;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import push.NotificationManager;

public class MainActivity extends AppCompatActivity {

    private static int PROGRESS_NO_ID = 3;//进度条通知id

    @BindView(R.id.button01)
    TextView mButton01;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }


    @OnClick({R.id.button01, R.id.button02,
            R.id.button03, R.id.button04,
            R.id.button05, R.id.button06,
            R.id.button07, R.id.button08})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.button01://第一种方式
                showNotification();
                break;
            case R.id.button02://第二种方式
                showNotion();
                break;
            case R.id.button03://自定义view的方式
                showCustomNotification();
                break;
            case R.id.button04://bigTest通知
                showBigTextNotification();
                break;
            case R.id.button05://bigPicture 通知
                showBigPictureNotification();
                break;
            case R.id.button06://inboxStyle 通知
                showInboxNotification();
                break;
            case R.id.button07://自定义带按钮的通知
                showCustomNotificationButton();
                break;
            case R.id.button08://带进度条的通知
                showProgressNotification();
                break;
        }
    }


    /**
     * 展示InboxStyle 通知
     */
    private void showInboxNotification() {
        Intent resultIntent = new Intent(this, SecondActivity.class);
        resultIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
        PendingIntent resultPendingIntent = PendingIntent.getActivity(this, (new Random().nextInt(1000)),
                resultIntent, PendingIntent.FLAG_UPDATE_CURRENT);
        List<String> lineList = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            lineList.add("wwwwwwwwww" + i);
        }
        NotificationManager.showInboxNotification(this, "标题标题", "InboxStyle形式的通知", lineList, resultPendingIntent);
    }

    /**
     * 展示bigPicture 通知
     */
    private void showBigPictureNotification() {
        Intent resultIntent = new Intent(this, SecondActivity.class);
        resultIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
        PendingIntent resultPendingIntent = PendingIntent.getActivity(this, (new Random().nextInt(1000)),
                resultIntent, PendingIntent.FLAG_UPDATE_CURRENT);
        NotificationManager.showBigPictureNotification(this, "标题", "bigPictureStyle形式的通知", resultPendingIntent);
    }

    /**
     * 展示bigText 通知
     */
    private void showBigTextNotification() {
        Intent resultIntent = new Intent(this, SecondActivity.class);
        resultIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
        PendingIntent resultPendingIntent = PendingIntent.getActivity(this, (new Random().nextInt(1000)),
                resultIntent, PendingIntent.FLAG_UPDATE_CURRENT);
        NotificationManager.showBigTextNotification(this, "标题", "摘要摘要", "bigTextStyle通知", resultPendingIntent);
    }


    /**
     * 第一种方式，普通通知
     */
    private void showNotification() {
        Intent resultIntent = new Intent(this, SecondActivity.class);
        resultIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
        //(new Random().nextInt(1000) 这个地方这么写的原因，是部分低版本的不能跳转，比如说小米3
        PendingIntent resultPendingIntent = PendingIntent.getActivity(this, (new Random().nextInt(1000)),
                resultIntent, PendingIntent.FLAG_UPDATE_CURRENT);
        NotificationManager.showNotification(this, getString(R.string.app_name),
                "普通通知", resultPendingIntent);
    }

    /**
     * 第二种方式的普通通知
     */
    private void showNotion() {
        Intent resultIntent = new Intent(this, SecondActivity.class);
        TaskStackBuilder stackBuilder = TaskStackBuilder.create(this);
        stackBuilder.addParentStack(SecondActivity.class);
        stackBuilder.addNextIntent(resultIntent);
        PendingIntent resultPendingIntent = stackBuilder.getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT);
        resultIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);

        NotificationManager.showNotification(this, getString(R.string.app_name),
                "第二种方式的普通通知", resultPendingIntent);

    }


    /**
     * 自定义view 的普通通知
     *
     * @param
     */
    private void showCustomNotification() {
        //自定义显示布局
        RemoteViews contentViews = new RemoteViews(this.getPackageName(), R.layout.custom_notification);
        //通过控件的Id设置属性
        contentViews.setImageViewResource(R.id.ImageView, R.mipmap.ic_launcher);
        contentViews.setTextViewText(R.id.title, "自定义通知标题");
        contentViews.setTextViewText(R.id.content, "自定义通知内容");
        Intent resultIntent = new Intent(this, SecondActivity.class);
        resultIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
        //(new Random().nextInt(1000) 这个地方这么写的原因，是部分低版本的不能跳转，比如说小米3
        PendingIntent resultPendingIntent = PendingIntent.getActivity(this, (new Random().nextInt(1000)),
                resultIntent, PendingIntent.FLAG_UPDATE_CURRENT);

        NotificationManager.showCustomNotification(this, contentViews, "ticker", resultPendingIntent);
    }


    /**
     * 带按钮的自定义通知
     *
     * @param
     */
    private void showCustomNotificationButton() {
        //自定义显示布局
        RemoteViews contentViews = new RemoteViews(this.getPackageName(), R.layout.custom_notification_button);
        //通过控件的Id设置属性
        contentViews.setImageViewResource(R.id.ImageView, R.mipmap.ic_launcher);
        contentViews.setTextViewText(R.id.title, "自定义通知标题");
        contentViews.setTextViewText(R.id.content, "自定义通知内容");


        Intent cancelIntent = new Intent(this, NotificationServices.class);
        cancelIntent.putExtra("key_cancel", "cancel");
        cancelIntent.putExtra("key_conform", "confirm");
        PendingIntent cancelPendingIntent = PendingIntent.getService(this, 1, cancelIntent, PendingIntent.FLAG_UPDATE_CURRENT);
        contentViews.setOnClickPendingIntent(R.id.cancel_button, cancelPendingIntent);
        contentViews.setOnClickPendingIntent(R.id.confirm_button, cancelPendingIntent);

        Intent resultIntent = new Intent(this, SecondActivity.class);
        resultIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
        //(new Random().nextInt(1000) 这个地方这么写的原因，是部分低版本的不能跳转，比如说小米3
        PendingIntent resultPendingIntent = PendingIntent.getActivity(this, (new Random().nextInt(1000)),
                resultIntent, PendingIntent.FLAG_UPDATE_CURRENT);
        NotificationManager.showCustomNotification(this, contentViews, "ticker", resultPendingIntent);
    }


    /**
     * 进度条通知
     */
    private void showProgressNotification() {
        Intent resultIntent = new Intent(this, SecondActivity.class);
        resultIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
        PendingIntent resultPendingIntent = PendingIntent.getActivity(this, (new Random().nextInt(1000)),
                resultIntent, PendingIntent.FLAG_UPDATE_CURRENT);

        final NotificationCompat.Builder builder = NotificationManager.showProgressNotify(this, "下载", "正在下载", resultPendingIntent);

        Notification notification = builder.build();
        notification.flags = Notification.FLAG_AUTO_CANCEL;//当通知被用户点击之后会自动被清除(cancel)
        final android.app.NotificationManager notificationManager = (android.app.NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        if (notificationManager != null) {
            //这里最好使用固定的id，因为如果通知id不固定的话，每次更新就会弹出多个通知
            notificationManager.notify(PROGRESS_NO_ID, notification);
        }
        builder.setProgress(100, 0, false);
        //下载以及安装线程模拟
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 100; i++) {
                    builder.setProgress(100, i, false);
                    notificationManager.notify(PROGRESS_NO_ID, builder.build());
                    //下载进度提示
                    builder.setContentText("下载" + i + "%");
                    try {
                        Thread.sleep(50);//演示休眠50毫秒
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                //下载完成后更改标题以及提示信息
                builder.setContentTitle("开始安装");
                builder.setContentText("安装中...");
                //设置进度为不确定，用于模拟安装
                builder.setProgress(0, 0, true);
                notificationManager.notify(PROGRESS_NO_ID, builder.build());
            }
        }).start();
    }


}
