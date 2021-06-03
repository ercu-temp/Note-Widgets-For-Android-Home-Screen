package com.topuz.notebookwidget;
import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RemoteViews;

import androidx.appcompat.app.AppCompatActivity;

public class Info extends AppCompatActivity {
    public static final String SHARED_PREFS = "prefs";
    public static final String KEY_BUTTON_TEXT = "keyButtonText";
    private int appWidgetId = AppWidgetManager.INVALID_APPWIDGET_ID;
    private EditText editText;
    private Button kaydet;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.xccx);
        Intent configIntent = getIntent();
        Bundle extras = configIntent.getExtras();
        if (extras != null) {
            appWidgetId = extras.getInt(AppWidgetManager.EXTRA_APPWIDGET_ID,
                    AppWidgetManager.INVALID_APPWIDGET_ID);
        }
        Intent resultValue = new Intent();
        resultValue.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, appWidgetId);
        setResult(RESULT_CANCELED, resultValue);
        if (appWidgetId == AppWidgetManager.INVALID_APPWIDGET_ID) {
            finish();
        }
        kaydet=findViewById(R.id.button);
        editText = findViewById(R.id.editTextTextPersonName);
        kaydet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(Info.this);
                Intent intent = new Intent(Info.this, MainActivity.class);
                PendingIntent pendingIntent = PendingIntent.getActivity(Info.this, 0, intent, 0);
                String buttonText = editText.getText().toString();
                RemoteViews views = new RemoteViews(Info.this.getPackageName(), R.layout.ornek_widget);
                views.setOnClickPendingIntent(R.id.widgetButton, pendingIntent);
                views.setCharSequence(R.id.widgetButton, "setText", buttonText);
                appWidgetManager.updateAppWidget(appWidgetId, views);
                SharedPreferences prefs = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
                SharedPreferences.Editor editor = prefs.edit();
                editor.putString(KEY_BUTTON_TEXT + appWidgetId, buttonText);
                editor.apply();
                Intent resultValue = new Intent();
                resultValue.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, appWidgetId);
                setResult(RESULT_OK, resultValue);
                finish();

            }
        });


    }

}