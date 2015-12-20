package top.itmp.opentimes;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.preference.Preference;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private  int counts = 0;
    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final TextView textView = (TextView)findViewById(R.id.counts);

        SharedPreferences sharedPreferences =  getPreferences(MODE_WORLD_READABLE); //getSharedPreferences("counts", MODE_WORLD_READABLE);
        // /data/data/top.itmp.opentims/shared_prefs/Acitivity.xml
        if(sharedPreferences == null) {
            textView.setText(0 + "次");
           // sharedPreferences.
        }
        else
        textView.setText((counts = sharedPreferences.getInt("counts", 0)) + "次");

        final SharedPreferences.Editor edit = sharedPreferences.edit();
        edit.putInt("counts", counts + 1); // counts++
        edit.commit();

        Button button = new Button(this);
        RelativeLayout relativeLayout = (RelativeLayout)findViewById(R.id.relative);

        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT,
                RelativeLayout.LayoutParams.WRAP_CONTENT);
        layoutParams.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
        layoutParams.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
        button.setLayoutParams(layoutParams);
        button.setText("Clear");
        button.setId(View.generateViewId()); // setid

        relativeLayout.addView(button);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edit.clear().commit();
            }
        });

        Button button1 = new Button(this);
        RelativeLayout.LayoutParams layoutParams1 = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT,
                RelativeLayout.LayoutParams.WRAP_CONTENT);
        layoutParams1.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
        //layoutParams.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
       // layoutParams1.addRule(RelativeLayout.ALIGN_TOP, button.getId());
        button1.setLayoutParams(layoutParams1);
        button1.setText("获取其他的值");
        relativeLayout.addView(button1);

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Context context = null;
                try {
                    context = createPackageContext("top.itmp.preferencetest", CONTEXT_IGNORE_SECURITY);
                } catch (PackageManager.NameNotFoundException e) {
                    e.printStackTrace();
                }

                SharedPreferences sharedPreferences1 = context.getSharedPreferences("MainActivity", MODE_WORLD_READABLE);

                String tmp = sharedPreferences1.getString("theme", null);
                if (tmp != null)
                    textView.setText(tmp);
            }
        });


    }
}
