package com.petasz.darek.lab_4;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.icu.text.DateFormat;
import android.icu.util.Calendar;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import static com.petasz.darek.lab_4.R.id.dataUrodzeniaPobrane;
import static com.petasz.darek.lab_4.R.id.imie;
import static com.petasz.darek.lab_4.R.id.miasto;
import static com.petasz.darek.lab_4.R.id.nazwisko;
import static com.petasz.darek.lab_4.R.id.text;
import static com.petasz.darek.lab_4.R.id.ulica;
import static com.petasz.darek.lab_4.R.id.ulubioneKoloryPobrane;


@RequiresApi(api = Build.VERSION_CODES.N)
public class MainActivity extends AppCompatActivity {

    ArrayList<String> zaznaczoneKolory = new ArrayList<>();
    ArrayList<String> zaznaczoneZajecia = new ArrayList<>();

    CheckBox cbKolor1, cbKolor2,cbKolor3;
    CheckBox cbZajecie1, cbZajecie2,cbZajecie3, cbZajecie4;
    TextView tvKolor, tvZajecie, tvPlik;

    EditText imie,nazwisko,miasto, ulica;

    Calendar data = Calendar.getInstance();
    DateFormat formatDaty = DateFormat.getDateInstance();

    private TextView dataUrodzenia;
    private Button dataPrzycisk;

    private String plik = "Lab_5";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imie = (EditText) findViewById(R.id.imie) ;
        nazwisko = (EditText) findViewById(R.id.nazwisko);
        miasto = (EditText) findViewById(R.id.miasto);
        ulica = (EditText) findViewById(R.id.ulica);

        cbKolor1 = (CheckBox) findViewById(R.id.zielony);
        cbKolor2 = (CheckBox) findViewById(R.id.czerwony);
        cbKolor3 = (CheckBox) findViewById(R.id.niebieski);

        cbZajecie1 = (CheckBox) findViewById(R.id.sport);
        cbZajecie2 = (CheckBox) findViewById(R.id.muzyka);
        cbZajecie3 = (CheckBox) findViewById(R.id.czytanie);
        cbZajecie4 = (CheckBox) findViewById(R.id.szachy);

        tvKolor = (TextView) findViewById(R.id.wyswietlKolory);
        tvZajecie = (TextView) findViewById(R.id.wyswietlZajecia);

        tvPlik = (TextView) findViewById(R.id.wyswietlPlik);

        dataUrodzenia = (TextView) findViewById(R.id.tvDataUrodzenia);
        dataPrzycisk = (Button) findViewById(R.id.dataPrzycisk);



        Button przyciskZatwierdz = (Button) findViewById(R.id.zatwierdzDanePrzycisk);
        przyciskZatwierdz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), SecoundActivity.class);
                intent.putExtra("imie", imie.getText().toString());
                intent.putExtra("nazwisko", nazwisko.getText().toString());
                intent.putExtra("miasto", miasto.getText().toString());
                intent.putExtra("ulica", ulica.getText().toString());
                intent.putExtra("dataUrodzenia", dataUrodzenia.getText().toString());
                intent.putExtra("ulubionyKolor", tvKolor.getText().toString());
                intent.putExtra("ulubioneZajecia", tvZajecie.getText().toString());

                startActivity(intent);
            }
        });
        dataPrzycisk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                aktualizujData();
            }


        });

        aktualizujDateUrodzenia();
    }



    private void aktualizujData() {
        new DatePickerDialog(this,d, data.get(Calendar.YEAR),data.get(Calendar.MONTH),data.get(Calendar.DAY_OF_MONTH)).show();
    }

    DatePickerDialog.OnDateSetListener d = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
            data.set(Calendar.YEAR, i);
            data.set(Calendar.MONTH, i1);
            data.set(Calendar.DAY_OF_MONTH, i2);
            aktualizujDateUrodzenia();
        }
    };
    private  void aktualizujDateUrodzenia()
    {
        dataUrodzenia.setText(formatDaty.format(data.getTime()));
    }

    public void zatwierdzAnkieta(View view)
    {
        String txtKolor = "", txtZajeca = "";
        int x=0,y=0;

        for(String a : zaznaczoneKolory)
        {
            x++;
            txtKolor += "\n\t\t\t\t\t\t"+ x +". " + a ;
        }
        for(String a : zaznaczoneZajecia)
        {
            y++;
            txtZajeca +="\n\t\t\t\t\t\t"+ y +". " + a ;
        }
        tvKolor.setText("Twoje ulubione kolory:" + txtKolor);
        tvZajecie.setText("\nToje ulubione zajęcia:" + txtZajeca);
    }
    public void zaznacz(View view)
    {
        boolean zaznaczenie = ((CheckBox) view).isChecked();
        switch (view.getId())
        {
            case R.id.zielony:
                if(zaznaczenie)
                {
                    zaznaczoneKolory.add("zielony");
                }
                else
                {
                    zaznaczoneKolory.remove("zielony");
                }
                break;
            case R.id.czerwony:
                if(zaznaczenie)
                {
                    zaznaczoneKolory.add("czerwony");
                }
                else
                {
                    zaznaczoneKolory.remove("czerwony");
                }
                break;
            case R.id.niebieski:
                if(zaznaczenie)
                {
                    zaznaczoneKolory.add("niebieski");
                }
                else
                {
                    zaznaczoneKolory.remove("niebieski");
                }
                break;
            case R.id.muzyka:
                if(zaznaczenie)
                {
                    zaznaczoneZajecia.add("muzyka");
                }
                else
                {
                    zaznaczoneZajecia.remove("muzyka");
                }
                break;
            case R.id.sport:
                if(zaznaczenie)
                {
                    zaznaczoneZajecia.add("sport");
                }
                else
                {
                    zaznaczoneZajecia.remove("sport");
                }
                break;
            case R.id.czytanie:
                if(zaznaczenie)
                {
                    zaznaczoneZajecia.add("czytanie");
                }
                else
                {
                    zaznaczoneZajecia.remove("czytanie");
                }
                break;
            case R.id.szachy:
                if(zaznaczenie)
                {
                    zaznaczoneZajecia.add("szachy");
                }
                else
                {
                    zaznaczoneZajecia.remove("szachy");
                }
                break;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.my, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();
        if(id==R.id.pokaz)
        {
            openPokaz();
            return  true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void openPokaz() {
        String i = "Imie: " + imie.getText().toString() + "\n";
        String n = "Nazwisko: " + nazwisko.getText().toString()+ "\n";
        String m = "Miasto: " + miasto.getText().toString()+ "\n";
        String u = "Ulica: " + ulica.getText().toString()+ "\n";
        String dU = "Data urodzenia: "+ dataUrodzenia.getText().toString()+ "\n";
        String uK = tvKolor.getText().toString()+ "\n";
        String uZ = tvZajecie.getText().toString();
        Toast.makeText(this, i +n +m+ u+ dU +uK + uZ,Toast.LENGTH_LONG).show();
    }

    public void zapiszPlik(View view) {
        String pobranyTekst = "Imie: " + imie.getText().toString() + "\n"+
                "Nazwisko: " + nazwisko.getText().toString()+ "\n"+
                "Miasto: " + miasto.getText().toString()+ "\n"+
                "Ulica: " + ulica.getText().toString()+ "\n"+
                "Data urodzenia: "+ dataUrodzenia.getText().toString()+ "\n"+
                tvKolor.getText().toString()+ "\n"+
                tvZajecie.getText().toString();
        try {
            FileOutputStream fileOutputStream = openFileOutput(plik, Context.MODE_PRIVATE);
            fileOutputStream.write(pobranyTekst.getBytes());
            fileOutputStream.close();

            Toast.makeText(getApplicationContext(), "Zapisano dane formularza", Toast.LENGTH_SHORT).show();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void pobierzDane(View view) {

        try {
            String wyswietlDane ;
            FileInputStream fileInputStream = openFileInput(plik);

            InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream);

            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

            StringBuffer stringBuffer = new StringBuffer();

            while ((wyswietlDane = bufferedReader.readLine())!= null)
            {
                stringBuffer.append(wyswietlDane + "\n");
            }

            tvPlik.setText(stringBuffer.toString());
            tvPlik.setVisibility(View.VISIBLE);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
