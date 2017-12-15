package com.notification;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.TaskStackBuilder;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.RemoteViews;
import android.widget.TextView;

import java.util.Random;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import push.NotificationManager;

public class MainActivity extends AppCompatActivity {


    @BindView(R.id.button01)
    TextView mButton01;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        // 183 2141 0040
    }


    @OnClick({R.id.button01, R.id.button02,
            R.id.button03, R.id.button04,
            R.id.button05})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.button01://第一种方式
                showNotification();
                break;
            case R.id.button02://第二种方式
                showNotion();
                break;
            case R.id.button03://自定义view的方式
                showCustomNotification(this);
                break;
            case R.id.button04://bigTest通知
                showBigTextNotification();
                break;
            case R.id.button05://bigPicture 通知
                Intent resultIntent = new Intent(this, SecondActivity.class);
                resultIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                PendingIntent resultPendingIntent = PendingIntent.getActivity(this, (new Random().nextInt(1000)),
                        resultIntent, PendingIntent.FLAG_UPDATE_CURRENT);
                NotificationManager.showBigPictureNotify(this, 0, "标题", "摘要", resultPendingIntent);
                break;
        }
    }

    /**
     * 展示bigText 通知
     */
    private void showBigTextNotification() {
        Intent resultIntent = new Intent(this, SecondActivity.class);
        resultIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
        PendingIntent resultPendingIntent = PendingIntent.getActivity(this, (new Random().nextInt(1000)),
                resultIntent, PendingIntent.FLAG_UPDATE_CURRENT);
        NotificationManager.showBigTextNotification(this, "标题", "摘要摘要", "内容内蓉", resultPendingIntent);
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
                "alert 消息", resultPendingIntent);
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
                "alert 消息", resultPendingIntent);

    }


    /**
     * 自定义view 的普通通知
     *
     * @param context
     */
    private static void showCustomNotification(Context context) {
        //自定义显示布局
        RemoteViews contentViews = new RemoteViews(context.getPackageName(), R.layout.custom_notification);
        //通过控件的Id设置属性
        contentViews.setImageViewResource(R.id.ImageView, R.mipmap.ic_launcher);
        contentViews.setTextViewText(R.id.title, "自定义通知标题");
        contentViews.setTextViewText(R.id.content, "自定义通知内容");

        Intent resultIntent = new Intent(context, SecondActivity.class);
        resultIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
        //(new Random().nextInt(1000) 这个地方这么写的原因，是部分低版本的不能跳转，比如说小米3
        PendingIntent resultPendingIntent = PendingIntent.getActivity(context, (new Random().nextInt(1000)),
                resultIntent, PendingIntent.FLAG_UPDATE_CURRENT);

        NotificationManager.showCustomNotification(context, contentViews, "ticker", resultPendingIntent);

    }


}
