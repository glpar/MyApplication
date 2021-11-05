package com.example.myapplication;

        import android.annotation.SuppressLint;
        import android.content.Intent;
        import android.graphics.Color;
        import android.media.MediaPlayer;
        import android.os.Bundle;
        import android.view.View;
        import android.view.View.OnClickListener;
        import android.widget.Button;
        import android.widget.ListView;
        import android.widget.PopupMenu;
        import android.widget.TextView;
        import android.widget.Toast;
        import android.widget.ToggleButton;

        import androidx.appcompat.app.AppCompatActivity;
        import androidx.constraintlayout.widget.ConstraintLayout;

        import android.widget.EditText;
        import android.view.Menu;
        import android.view.MenuItem;
        import android.widget.ArrayAdapter;
        import android.widget.AutoCompleteTextView;
        import android.widget.RelativeLayout;
        import android.widget.RelativeLayout.LayoutParams;

        import android.widget.AdapterView;
        import android.widget.Spinner;

        import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {
    EditText eText;
    Button btn;
    RelativeLayout rl;
    AutoCompleteTextView auto;
    Spinner spnr;
    MediaPlayer mySound;
    ListView listView;

    ArrayList<String> array;

    String[] celebrities = {
            "Luffy",
            "Law",
            "Kid",
            "Shanks",
            "Barba Negra",
            "God Usopp"
    };

    private ToggleButton toggleButton1, toggleButton2;
    private Button btnDisplay;
    private Button button;
    private Button button1;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView txtView = (TextView) findViewById(R.id.txtView);
        txtView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                // TODO Auto-generated method stub
                Toast.makeText(getApplicationContext(),
                        "Você me apertou demais kkkkk :)", 3000).show();
                return true;
            }
        });
        txtView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Toast.makeText(getApplicationContext(), "Não me apertou o suficiente :(",
                        2000).show();
            }
        });
        array = new ArrayList<>();
        array.add("Luffy");

        listView = findViewById(R.id.listView);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, array);
        listView.setAdapter(adapter);
        mySound = MediaPlayer.create(this,R.raw.op);

        button1 = (Button) findViewById(R.id.button1);
        button1.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                //Creating the instance of PopupMenu
                PopupMenu popup = new PopupMenu(MainActivity.this, button1);
                //Inflating the Popup using xml file
                popup.getMenuInflater()
                        .inflate(R.menu.popup_menu, popup.getMenu());

                //registering popup with OnMenuItemClickListener
                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    public boolean onMenuItemClick(MenuItem item) {
                        Toast.makeText(
                                MainActivity.this,
                                "You Clicked : " + item.getTitle(),
                                Toast.LENGTH_SHORT
                        ).show();
                        return true;
                    }
                });

                popup.show(); //showing popup menu
            }
        }); //closing the setOnClickListener method
        eText = (EditText) findViewById(R.id.edittext);
        btn = (Button) findViewById(R.id.button);
        button = (Button) findViewById(R.id.button2);
        button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                openActivity2();
            }
        });
        btn.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                String str = eText.getText().toString();
                Toast msg = Toast.makeText(getBaseContext(),str,Toast.LENGTH_LONG);
                msg.show();
            }
        });
        String str[]={"Arun","Mathev","Vishnu","Vishal","Arjun",
                "Arul","Balaji","Babu","Boopathy","Godwin","Nagaraj"};

        rl = (RelativeLayout) findViewById(R.id.rl);

        auto = new AutoCompleteTextView (MainActivity.this);

        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams
                ((int) LayoutParams.WRAP_CONTENT, (int) LayoutParams.WRAP_CONTENT);

        params.leftMargin = 80;
        params.topMargin = 130;

        auto.setLayoutParams(params);
        auto.setEms(10);

        ArrayAdapter<String> adp=new ArrayAdapter<String>(this,
                android.R.layout.simple_dropdown_item_1line,str);

        auto.setThreshold(1);
        auto.setAdapter(adp);

        rl.addView(auto);

        addListenerOnButton();

        spnr = (Spinner)findViewById(R.id.spinner);
        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(
                this, android.R.layout.simple_spinner_item, celebrities);

        spnr.setAdapter(adapter2);
        spnr.setOnItemSelectedListener(
                new AdapterView.OnItemSelectedListener() {

                    @Override
                    public void onItemSelected(AdapterView<?> arg0, View arg1,
                                               int arg2, long arg3) {

                        int position = spnr.getSelectedItemPosition();
                        Toast.makeText(getApplicationContext(),"You have selected "+celebrities[+position],Toast.LENGTH_LONG).show();
                        // TODO Auto-generated method stub
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> arg0) {
                        // TODO Auto-generated method stub

                    }

                }
        );
    }

    public void Lista(){}

    @Override
    protected void onPause(){
        super.onPause();
        mySound.release();
    }
    public void pauseMusic(View view) {
        mySound.pause();

    }

    public void playMusic(View view) {
        mySound.start();
        mySound.setVolume(100,100);
    }

    public void addListenerOnButton() {

        toggleButton1 = (ToggleButton) findViewById(R.id.toggleButton1);
        toggleButton2 = (ToggleButton) findViewById(R.id.toggleButton2);
        btnDisplay = (Button) findViewById(R.id.btnDisplay);

        btnDisplay.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {

                StringBuffer result = new StringBuffer();
                result.append("toggleButton1 : ").append(toggleButton1.getText());
                result.append("\ntoggleButton2 : ").append(toggleButton2.getText());

                Toast.makeText(MainActivity.this, result.toString(),
                        Toast.LENGTH_SHORT).show();

            }

        });

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }
    public boolean onOptionsItemSelected(MenuItem item){
        ConstraintLayout main_view = (ConstraintLayout) findViewById(R.id.menu_view);

        switch (item.getItemId()){
            case R.id.menu_red:
                if (item.isChecked())
                    item.setChecked(false);
                else
                    item.setChecked(true);
                main_view.setBackgroundColor(Color.RED);
                return true;
            case R.id.menu_blue:
                if (item.isChecked())
                    item.setChecked(false);
                else
                    item.setChecked(true);
                main_view.setBackgroundColor(Color.BLUE);
                return true;
            case R.id.menu_yellow:
                if (item.isChecked())
                    item.setChecked(false);
                else
                    item.setChecked(true);
                main_view.setBackgroundColor(Color.YELLOW);
                return true;
            case R.id.menu_green:
                if (item.isChecked())
                    item.setChecked(false);
                else
                    item.setChecked(true);
                main_view.setBackgroundColor(Color.GREEN);
                return true;
            case R.id.menu_white:
                if (item.isChecked())
                    item.setChecked(false);
                else
                    item.setChecked(true);
                main_view.setBackgroundColor(Color.WHITE);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
    public void openActivity2(){
        Intent intent = new Intent(this, MainActivity2.class);
        startActivity(intent);
    }
}