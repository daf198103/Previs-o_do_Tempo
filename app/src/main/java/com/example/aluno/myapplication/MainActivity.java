package com.example.aluno.myapplication;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;


import android.view.View;
import android.view.WindowManager;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.w3c.dom.Text;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.LinkedList;

import static com.example.aluno.myapplication.R.*;

public class MainActivity extends Activity implements AdapterView.OnItemSelectedListener {


    Connection c = new Connection();
    Connection c2 = new Connection();
    String estado = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //SPINNER DOS ESTADOS
        // Spinner element
        Spinner spinner = (Spinner) findViewById(R.id.spinner);

        // Spinner click listener
        spinner.setOnItemSelectedListener(this);

        // Spinner Drop down elements
        List<String> categories = new ArrayList<String>();
        categories.add("SP");categories.add("SC");categories.add("PR");categories.add("RS");categories.add("MG");
        categories.add("RJ");categories.add("ES");categories.add("MS");categories.add("MT");categories.add("GO");
        categories.add("TO");categories.add("DF");categories.add("BA");categories.add("SE");categories.add("AL");
        categories.add("PE");categories.add("PB");categories.add("RN");categories.add("CE");categories.add("PI");
        categories.add("MA");categories.add("PA");categories.add("RO");categories.add("AP");categories.add("AM");
        categories.add("RR");categories.add("AC");

        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, categories);

        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to spinner
        spinner.setAdapter(dataAdapter);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        // On selecting a spinner item
        String item = parent.getItemAtPosition(position).toString();
        estado = item;

    }
    public void onNothingSelected(AdapterView<?> arg0) {

    }


    @SuppressLint("ResourceType")
    public void buscar(View view) throws Exception {

        EditText text = (EditText) findViewById(id.City);
        String nome = text.getText().toString();
        //tratamento da excessão sem digitar uma cidade
        if (TextUtils.isEmpty(nome.toString())) {
            text.setError("Digite uma Cidade");
            return;
        }



        /*EditText text2 = (EditText) findViewById(id.Estado);
        String estado = text2.getText().toString();
        //tratamento da excessão sem digitar um estado
        if (TextUtils.isEmpty(estado.toString())) {
            text.setError("Digite um estado");
            return;
        }*/


        final TextView resultArea = (TextView) findViewById(id.result);
        resultArea.setVisibility(View.VISIBLE);

        //IMAGE VIEWS DOS ÍCONES DE CONDIÇÃO DO TEMPO

        final ImageView icone1 = (ImageView) findViewById(id.icone1);
        final ImageView icone1n = (ImageView) findViewById(id.icone1n);
        final ImageView icone2 = (ImageView) findViewById(id.icone2);
        final ImageView icone2n = (ImageView) findViewById(id.icone2n);
        final ImageView icone2r = (ImageView) findViewById(id.icone2r);
        final ImageView icone2rn = (ImageView) findViewById(id.icone2rn);
        final ImageView icone3 = (ImageView) findViewById(id.icone3);
        final ImageView icone3n = (ImageView) findViewById(id.icone3n);
        final ImageView icone3tm =  (ImageView) findViewById(id.icone3tm);
        final ImageView icone4 =  (ImageView) findViewById(id.icone4);
        final ImageView icone4n =  (ImageView) findViewById(id.icone4n);
        final ImageView icone4r =  (ImageView) findViewById(id.icone4r);
        final ImageView icone4rn =  (ImageView) findViewById(id.icone4rn);
        final ImageView icone4t =  (ImageView) findViewById(id.icone4t);
        final ImageView icone4tn =  (ImageView) findViewById(id.icone4tn);
        final ImageView icone5 =  (ImageView) findViewById(id.icone5);
        final ImageView icone5n =  (ImageView) findViewById(id.icone5n);
        final ImageView icone6 =  (ImageView) findViewById(id.icone6);
        final ImageView icone6n =  (ImageView) findViewById(id.icone6n);
        final ImageView icone7 =  (ImageView) findViewById(id.icone7);
        final ImageView icone7n =  (ImageView) findViewById(id.icone7n);
        final ImageView icone8 =  (ImageView) findViewById(id.icone8);
        final ImageView icone8n =  (ImageView) findViewById(id.icone8n);
        final ImageView icone9 =  (ImageView) findViewById(id.icone9);
        final ImageView icone9n =  (ImageView) findViewById(id.icone9n);


        Weather weather = c.sendGetId(nome, estado);

        // Tratamento da excessão de cidade errada ou inexistente.
        if (weather.getName()== null) {
            StringBuilder sb = new StringBuilder();
            sb.append("-------------------------------------"+"\n");
            sb.append("         CIDADE OU ESTADO            "+"\n");
            sb.append("             INVÁLIDO                "+"\n");
            sb.append("         POR FAVOR DIGITE            "+"\n");
            sb.append("             NOVAMENTE!              "+"\n");
            sb.append("-------------------------------------"+"\n");
            resultArea.setText(sb);


        } else {
            Weather weather2 = c2.sendGet2(weather.getId());

            String ic = weather2.getData().getIcon().toString();

            //If else para mostrar os ícones do tempo nas cidades
            if (ic.equals("1")){icone1.getDrawable();icone1.setVisibility(View.VISIBLE);
            }else if(ic.equals("1n")){icone1n.getDrawable();icone1n.setVisibility(View.VISIBLE);
            }else if(ic.equals("2")) {icone2.getDrawable();icone2.setVisibility(View.VISIBLE);
            }else if(ic.equals("2n")){icone2n.getDrawable();icone2n.setVisibility(View.VISIBLE);
            }else if(ic.equals("2r")){icone2r.getDrawable();icone2r.setVisibility(View.VISIBLE);
            }else if(ic.equals("2rn")){icone2rn.getDrawable();icone2rn.setVisibility(View.VISIBLE);
            }else if(ic.equals("3")){icone3.getDrawable();icone3.setVisibility(View.VISIBLE);
            }else if(ic.equals("3n")){icone3n.getDrawable();icone3n.setVisibility(View.VISIBLE);
            }else if (ic.equals("3tm")){icone3tm.getDrawable();icone3tm.setVisibility(View.VISIBLE);
            }else if (ic.equals("4")){icone4.getDrawable();icone4.setVisibility(View.VISIBLE);
            }else if (ic.equals("4n")){icone4n.getDrawable();icone4n.setVisibility(View.VISIBLE);
            }else if (ic.equals("4r")){icone4r.getDrawable();icone4r.setVisibility(View.VISIBLE);
            }else if (ic.equals("4rn")){icone4rn.getDrawable();icone4rn.setVisibility(View.VISIBLE);
            }else if (ic.equals("4t")){icone4t.getDrawable();icone4t.setVisibility(View.VISIBLE);
            }else if (ic.equals("4tn")){icone4tn.getDrawable();icone4tn.setVisibility(View.VISIBLE);
            }else if (ic.equals("5")){icone5.getDrawable();icone5.setVisibility(View.VISIBLE);
            }else if (ic.equals("5n")){icone5n.getDrawable();icone5n.setVisibility(View.VISIBLE);
            }else if (ic.equals("6")){icone6.getDrawable();icone6.setVisibility(View.VISIBLE);
            }else if (ic.equals("6n")){icone6n.getDrawable();icone6n.setVisibility(View.VISIBLE);
            }else if (ic.equals("7")){icone7.getDrawable();icone7.setVisibility(View.VISIBLE);
            }else if (ic.equals("7n")){icone7n.getDrawable();icone7n.setVisibility(View.VISIBLE);
            }else if (ic.equals("8")){icone8.getDrawable();icone8.setVisibility(View.VISIBLE);
            }else if (ic.equals("8n")){icone8n.getDrawable();icone8n.setVisibility(View.VISIBLE);
            }else if (ic.equals("9")){icone9.getDrawable();icone9.setVisibility(View.VISIBLE);
            }else if (ic.equals("9n")){icone9n.getDrawable();icone9n.setVisibility(View.VISIBLE);
            }else{
            }

            StringBuilder sb = new StringBuilder();
            sb.append("--------------------------------------------------" + "\n");
            sb.append("\n");
            sb.append("    Data: " + weather2.getData().getDia() + "\n");
            sb.append("\n");
            sb.append("    Cidade: " + weather.getName() + "\n");
            sb.append("    ID: " + Integer.toString(weather.getId()) + "\n");
            sb.append("    Estado: " + weather.getState().toString() + "\n");
            sb.append("    País: " + weather.getCountry().toString() + "\n");
            sb.append("    Temperatura: " + Double.toString(weather2.getData().getTemperature()) + " °C" + "\n");
            sb.append("    Direção do Vento: " + weather2.getData().getWindDirection().toString() + "\n");
            sb.append("    Velocidade do Vento: " + Integer.toString(weather2.getData().getWindVelocity()) + " km/h" + "\n");
            sb.append("    Humidade: " + Integer.toString(weather2.getData().getHumidity()) + " %" + "\n");
            sb.append("    Condição: " + weather2.getData().getCondition().toString() + "\n");
            sb.append("    Pressão: " + weather2.getData().getPressure().toString() + "\n");
            sb.append("    Sensação Térmica: " + weather2.getData().getSensation().toString() + " °C" + "\n");
            sb.append("----------------------------------------------------");


            resultArea.setText(sb);




        }


    }


}