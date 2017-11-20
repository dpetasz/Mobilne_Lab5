package com.petasz.darek.lab_4;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.widget.TextView;

public class SecoundActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_secound);


        TextView imiePobrane = (TextView)findViewById(R.id.imiePobrane);
        TextView nazwiskoPobrane = (TextView)findViewById(R.id.nazwiskoPobrane);
        TextView miastoPobrane = (TextView)findViewById(R.id.miastoPobrane);
        TextView ulicaPobrane = (TextView)findViewById(R.id.ulicaPobrane);
        TextView dataUrodzeniaPobrane = (TextView)findViewById(R.id.dataUrodzeniaPobrane);
        TextView ulubionyKolorPobrane = (TextView)findViewById(R.id.ulubioneKoloryPobrane);
        TextView ulubioneZjeciaPobrane = (TextView) findViewById(R.id.ulubioneZajeciaPobrane);

        Bundle bundle = getIntent().getExtras();
        if(bundle !=null)
        {
            String imie = bundle.getString("imie");
            String nazwisko = bundle.getString("nazwisko");
            String miasto = bundle.getString("miasto");
            String ulica = bundle.getString("ulica");
            String dataUrodzenia = bundle.getString("dataUrodzenia");
            String ulubionyKolor = bundle.getString("ulubionyKolor");
            String ulubioneZajecia = bundle.getString("ulubioneZajecia");

            imiePobrane.setText("Imie: "+imie);
            nazwiskoPobrane.setText("Nazwisko: " + nazwisko);
            miastoPobrane.setText("Miasto: " + miasto);
            ulicaPobrane.setText("Ulica: " + ulica);
            dataUrodzeniaPobrane.setText("Data urodzenia: " + dataUrodzenia);
            ulubionyKolorPobrane.setText(ulubionyKolor);
            ulubioneZjeciaPobrane.setText(ulubioneZajecia);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.my, menu);
        return true;
    }
}
