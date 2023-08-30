package br.edu.ifsp.dmo.qualcombustivel;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText gasEditText;
    private EditText etanolEditText;
    private Button calcularButton;
    private TextView respostaTextView;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        findById();
        setClickListener();
    }

    private void setClickListener() {
        calcularButton.setOnClickListener(this);
    }

    private void findById() {
        gasEditText = findViewById(R.id.edittext_gasolina);
        etanolEditText = findViewById(R.id.edittext_etanol);
        calcularButton = findViewById(R.id.idbutton_calcular);
        respostaTextView = findViewById(R.id.textview_resposta);
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.idbutton_calcular){
            calcular();
        }
    }

    private void calcular() {
        if (etanolEditText.getText().toString().isEmpty() || gasEditText.getText().toString().isEmpty()){
            Toast.makeText(this, "Informe o valor dos dois combustiveis", Toast.LENGTH_SHORT).show();
        }
        else {
            double gas = recuperarValor(gasEditText);
            double eta = recuperarValor(etanolEditText);

            double resultado = eta/gas;

            if (resultado < 0.7){
                respostaTextView.setText(R.string.answer_etanol);
                respostaTextView.setTextColor(getResources().getColor(R.color.etanol, getTheme()));
            }
            else {
                respostaTextView.setText(R.string.answer_gas);
                respostaTextView.setTextColor(getResources().getColor(R.color.gasolina, getTheme()));
            }
        }
    }

    private double recuperarValor(EditText input) {
        double value = 0;

        try {
            value = Double.valueOf(input.getText().toString());
        }catch (NumberFormatException nfe){
            Toast.makeText(this, "Valor inválido", Toast.LENGTH_SHORT).show();
            value = 0;
        }
        return value;
    }
}