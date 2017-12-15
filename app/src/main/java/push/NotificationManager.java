package push;

import android.app.Notification;
import android.app.PendingIntent;
import android.content.Context;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.widget.RemoteViews;

import com.notification.R;


/**
 * 作者： chengcheng
 * 时间： 17/12/4- 下午3:32O
 * 描述： 通知管理类
 */

public class NotificationManager {
    private static int NOTICE_ID_DEFAULT = 100000;

    public NotificationManager() {
    }

    /**
     * 基本展示通知方法
     *
     * @param ctx      上下文
     * @param noticeId 通知id
     * @param title    通知title
     * @param message  通知内容
     * @param intent   PendingIntent
     */
    private static void showNotify(Context ctx, int noticeId, String title, String message, PendingIntent intent) {
        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(ctx, null);
        mBuilder.setSmallIcon(R.mipmap.ic_launcher);
        mBuilder.setContentTitle(title);
        mBuilder.setContentText(message);
        mBuilder.setContentIntent(intent);
        mBuilder.setAutoCancel(true);
        mBuilder.setTicker(title);
        mBuilder.setPriority(NotificationManagerCompat.IMPORTANCE_HIGH);
        Notification notification = mBuilder.build();
        notification.flags = Notification.FLAG_AUTO_CANCEL;
        notification.defaults |= Notification.DEFAULT_SOUND;//默认声音
        notification.defaults |= Notification.DEFAULT_LIGHTS;//默认闪烁
        android.app.NotificationManager notificationManager = (android.app.NotificationManager) ctx.getSystemService(Context.NOTIFICATION_SERVICE);
        if (notificationManager != null) {
            notificationManager.notify(noticeId, notification);
        }
    }


    /**
     * 设置自定义通知 view
     *
     * @param ctx         上下文
     * @param remoteViews 自定义view
     * @param noticeId    通知id
     * @param ticker      ticker
     * @param intent      PendingIntent
     */
    private static void showCustomNotify(Context ctx, RemoteViews remoteViews, int noticeId, String ticker, PendingIntent intent) {
        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(ctx, null);
        mBuilder.setSmallIcon(R.mipmap.ic_launcher);
        mBuilder.setContentIntent(intent);
        mBuilder.setAutoCancel(true);
        mBuilder.setContent(remoteViews);
        mBuilder.setTicker(ticker);
        mBuilder.setPriority(NotificationManagerCompat.IMPORTANCE_HIGH);
        Notification notification = mBuilder.build();
        notification.flags = Notification.FLAG_AUTO_CANCEL;
        notification.defaults |= Notification.DEFAULT_SOUND;//默认声音
        notification.defaults |= Notification.DEFAULT_LIGHTS;//默认闪烁
        android.app.NotificationManager notificationManager = (android.app.NotificationManager) ctx.getSystemService(Context.NOTIFICATION_SERVICE);
        if (notificationManager != null) {
            notificationManager.notify(noticeId, notification);
        }
    }


    /**
     * 设置 BigText公告
     *
     * @param ctx         上下文
     * @param noticeId    通知id
     * @param title       标题
     * @param summaryText 摘要
     * @param bigText     内容
     * @param intent      PendingIntent
     */
    private static void showBigTextNotify(Context ctx, int noticeId, String title, String summaryText, String bigText, PendingIntent intent) {
        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(ctx, null);
        mBuilder.setSmallIcon(R.mipmap.ic_launcher);
        mBuilder.setContentIntent(intent);
        mBuilder.setAutoCancel(true);
        mBuilder.setTicker(title);
        mBuilder.setPriority(NotificationManagerCompat.IMPORTANCE_HIGH);

        NotificationCompat.BigTextStyle bigTextStyle = new NotificationCompat.BigTextStyle();
        bigTextStyle.setBigContentTitle(title)
                .setSummaryText(summaryText)
                .bigText(bigText);
        mBuilder.setStyle(bigTextStyle); //设置大文本样式

        Notification notification = mBuilder.build();
        notification.flags = Notification.FLAG_AUTO_CANCEL;
        notification.defaults |= Notification.DEFAULT_SOUND;//默认声音
        notification.defaults |= Notification.DEFAULT_LIGHTS;//默认闪烁
        android.app.NotificationManager notificationManager = (android.app.NotificationManager) ctx.getSystemService(Context.NOTIFICATION_SERVICE);
        if (notificationManager != null) {
            notificationManager.notify(noticeId, notification);
        }
    }

    /**
     * 展示bigTest 通知
     *
     * @param ctx         上下文
     * @param noticeId    通知id
     * @param title       标题
     * @param summaryText 摘要
     * @param bigText     内容
     * @param intent      PendingIntent
     */
    public static void showBigTextNotification(Context ctx, int noticeId, String title, String summaryText, String bigText, PendingIntent intent) {
        showBigTextNotify(ctx, noticeId, title, summaryText, bigText, intent);
    }

    /**
     * @param ctx         上下文对象
     * @param title       标题
     * @param summaryText 摘要
     * @param bigText     内容
     * @param intent      PendingIntent
     */
    public static void showBigTextNotification(Context ctx, String title, String summaryText, String bigText, PendingIntent intent) {
        showBigTextNotify(ctx, ++NOTICE_ID_DEFAULT, title, summaryText, bigText, intent);
    }


    /**
     * 展示通知默认方法
     *
     * @param ctx     上下文对象
     * @param title   通知标题
     * @param message 通知内容
     * @param intent  PendingIntent
     */
    public static void showNotification(Context ctx, String title, String message, PendingIntent intent) {
        showNotify(ctx, ++NOTICE_ID_DEFAULT, title, message, intent);
    }

    /**
     * 自定义通知 noticeId 时调用该方法
     *
     * @param ctx      上下文对象
     * @param noticeId 自定义通知id 必须小于 默认值（100000）
     * @param title    通知标题
     * @param message  通知内容
     * @param intent   PendingIntent对象
     */
    public static void showNotification(Context ctx, int noticeId, String title, String message, PendingIntent intent) {
        if (noticeId > NOTICE_ID_DEFAULT) {
            throw new IllegalArgumentException("自定义的noticeId 必须小于" + NOTICE_ID_DEFAULT);
        }
        showNotify(ctx, noticeId, title, message, intent);
    }


    /**
     * 展示自定义通知默认方法
     *
     * @param ctx         上下文
     * @param remoteViews 自定义布局
     * @param ticker      ticker
     * @param intent      PendingIntent对象
     */
    public static void showCustomNotification(Context ctx, RemoteViews remoteViews, String ticker, PendingIntent intent) {
        showCustomNotify(ctx, remoteViews, ++NOTICE_ID_DEFAULT, ticker, intent);
    }


    /**
     * 自定义通知 noticeId 时调用该方法
     *
     * @param ctx         上下文
     * @param remoteViews 自定义布局
     * @param noticeId    通知id
     * @param ticker      ticker
     * @param intent      PendingIntent对象
     */
    public static void showCustomNotification(Context ctx, RemoteViews remoteViews, int noticeId, String ticker, PendingIntent intent) {
        showCustomNotify(ctx, remoteViews, noticeId, ticker, intent);
    }

}
