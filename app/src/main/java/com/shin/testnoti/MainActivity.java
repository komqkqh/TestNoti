package com.shin.testnoti;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.app.NotificationCompat;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Context mContext;

    Button btn_noti_1 = null;
    Button btn_noti_2 = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mContext = this;

        btn_noti_1 = (Button) findViewById(R.id.btn_noti_1);
        btn_noti_2 = (Button) findViewById(R.id.btn_noti_2);

        btn_noti_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setNoti_1(mContext);
            }
        });
        btn_noti_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setNoti_2();
            }
        });

    }


    private void setNoti_1(Context context){

        NotificationCompat.Builder builder = new NotificationCompat.Builder(this);
        builder.setSmallIcon(R.mipmap.ic_launcher);
        builder.setTicker("미리보기!"); // 상단 미리보기
        builder.setWhen(System.currentTimeMillis());
        builder.setContentTitle("Test2");
        builder.setContentText("Test3");
        builder.setAutoCancel(true);
        builder.setPriority(Notification.PRIORITY_MAX);

        Intent push = new Intent();
        push.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        push.setClass(context, MainActivity.class);

        PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, push, PendingIntent.FLAG_CANCEL_CURRENT);
        builder.setFullScreenIntent(pendingIntent, true);       // HeadUp 설정
        builder.setContentIntent(pendingIntent);

        NotificationManager nm = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        nm.notify(123456, builder.build());
    }


    private void setNoti_2(){
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, new Intent(this, MainActivity.class), PendingIntent.FLAG_UPDATE_CURRENT);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(this);

        // 작은 아이콘 이미지.
        builder.setSmallIcon(R.mipmap.ic_launcher);

        // 알림이 출력될 때 상단에 나오는 문구.
        builder.setTicker("미리보기 입니다.");

        // 알림 출력 시간.
        builder.setWhen(System.currentTimeMillis());

        // 알림 제목.
        builder.setContentTitle("내용보다 조금 큰 제목!");

        // 프로그래스 바.
        builder.setProgress(100, 50, false);

        // 알림 내용.
        builder.setContentText("제목 하단에 출력될 내용!");

        // 알림시 사운드, 진동, 불빛을 설정 가능.
        builder.setDefaults(Notification.DEFAULT_SOUND | Notification.DEFAULT_LIGHTS);

        // 알림 터치시 반응.
        builder.setContentIntent(pendingIntent);

        // 알림 터치시 반응 후 알림 삭제 여부.
        builder.setAutoCancel(true);

        // 우선순위.
        builder.setPriority(NotificationCompat.PRIORITY_MAX);

        // 행동 최대3개 등록 가능.
        builder.addAction(R.mipmap.ic_launcher, "Show", pendingIntent);
        builder.addAction(R.mipmap.ic_launcher, "Hide", pendingIntent);
        builder.addAction(R.mipmap.ic_launcher, "Remove", pendingIntent);

        // 고유ID로 알림을 생성.
        NotificationManager nm = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        nm.notify(123456, builder.build());
    }

}
