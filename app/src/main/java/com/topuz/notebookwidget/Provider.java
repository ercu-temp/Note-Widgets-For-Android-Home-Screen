package com.topuz.notebookwidget;
import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.widget.RemoteViews;
import static com.topuz.notebookwidget.Info.KEY_BUTTON_TEXT;
import static com.topuz.notebookwidget.Info.SHARED_PREFS;

public class Provider extends AppWidgetProvider {
    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        for (int appWidgetId : appWidgetIds) {
            Intent intent = new Intent(context, MainActivity.class);
            intent.setAction(AppWidgetManager.ACTION_APPWIDGET_UPDATE);
            intent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_IDS,appWidgetIds);
            PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);

            SharedPreferences prefs = context.getSharedPreferences(SHARED_PREFS, Context.MODE_PRIVATE);
            String buttonText = prefs.getString(KEY_BUTTON_TEXT + appWidgetId, "Metin giriniz");
            RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.ornek_widget);
            views.setOnClickPendingIntent(R.id.textView, pendingIntent);
            views.setCharSequence(R.id.widgetButton, "setText", buttonText);
            appWidgetManager.updateAppWidget(appWidgetId, views);
        }
    }
}