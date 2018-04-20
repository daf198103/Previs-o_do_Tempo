package com.example.aluno.myapplication;
import android.app.Activity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;


import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import org.json.JSONArray;

import java.util.Date;
import java.util.List;
import java.util.LinkedList;

import static com.example.aluno.myapplication.R.*;

public class MainActivity extends Activity {


    Connection c = new Connection();
    Connection c2 = new Connection();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


    }


    public void buscar(View view) throws Exception {

        EditText text = (EditText) findViewById(R.id.City);
        String nome = text.getText().toString();
        //tratamento da excessão sem digitar uma cidade
        if (TextUtils.isEmpty(nome.toString())) {
            text.setError("Digite uma Cidade");
            return;
        }


        EditText text2 = (EditText) findViewById(R.id.Estado);
        String estado = text2.getText().toString();
        //tratamento da excessão sem digitar um estado
        if (TextUtils.isEmpty(estado.toString())) {
            text.setError("Digite um estado");
            return;
        }


        final TextView resultArea = (TextView) findViewById(R.id.result);



        Weather weather = c.sendGetId(nome, estado);

        // Tratamento da excessão de cidade errada ou inexistente.
        if (weather.getName()== null) {
            StringBuilder sb = new StringBuilder();
            sb.append("-------------------------------------------------------------------"+"\n");
            sb.append("         CIDADE OU ESTADO INVÁLIDO           "+"\n");
            sb.append("         POR FAVOR DIGITE NOVAMENTE!      "+"\n");
            sb.append("-------------------------------------------------------------------"+"\n");
            resultArea.setText(sb);


        } else {
            Weather weather2 = c2.sendGet2(weather.getId());



            StringBuilder sb = new StringBuilder();
            sb.append("--------------------------------------------------" + "\n");
            sb.append("\n");
            sb.append("|    Data: " + weather2.getData().getDia().toString() + "\n");
            sb.append("|    Cidade: " + weather.getName() + "\n");
            sb.append("|    ID: " + Integer.toString(weather.getId()) + "\n");
            sb.append("|    Estado: " + weather.getState().toString() + "\n");
            sb.append("|    País: " + weather.getCountry().toString() + "\n");
            sb.append("|    Temperatura: " + Double.toString(weather2.getData().getTemperature()) + " °C" + "\n");
            sb.append("|    Direção do Vento: " + weather2.getData().getWindDirection().toString() + "\n");
            sb.append("|    Velocidade do Vento: " + Integer.toString(weather2.getData().getWindVelocity()) + " km/h" + "\n");
            sb.append("|    Humidade: " + Integer.toString(weather2.getData().getHumidity()) + " %" + "\n");
            sb.append("|    Condição: " + weather2.getData().getCondition().toString() + "\n");
            sb.append("|    Pressão: " + weather2.getData().getPressure().toString() + "\n");
            sb.append("|    Sensação Térmica: " + weather2.getData().getSensation().toString() + " °C" + "\n");
            sb.append("----------------------------------------------------");


            resultArea.setText(sb);


        }
    }
}
