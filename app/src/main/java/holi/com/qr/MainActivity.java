package holi.com.qr;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Activity implements View.OnClickListener{

    Button btCliente, btLibro;
    EditText etCliente, etLibro;

    String btnSelected;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //botones
        btCliente = (Button)findViewById(R.id.btCliente);
        btLibro = (Button)findViewById(R.id.btLibro);

        //EditText
        etCliente = (EditText)findViewById(R.id.etCliente);
        etLibro = (EditText)findViewById(R.id.etLibro);

        btCliente.setOnClickListener(this);
        btLibro.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btCliente:
                btnSelected = "cliente";
                new IntentIntegrator(MainActivity.this).initiateScan();
                break;
            case R.id.btLibro:
                btnSelected = "libro";
                new IntentIntegrator(MainActivity.this).initiateScan();
                break;
        }
    }

    //resultado de la respuesta del intent qr
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        final IntentResult scanResult = IntentIntegrator.parseActivityResult(requestCode, resultCode, intent);
        handleResult(scanResult);
        //actualizarEditText(scanResult.getContents());
    }

    //Manejador del resultado
    private void handleResult(IntentResult scanResult) {
        if (scanResult != null) {
            actualizarEditText(scanResult.getContents());
        } else {
            Toast.makeText(this, "No se ha leído nada :(", Toast.LENGTH_SHORT).show();
        }
    }

    //Seteador de edittext
    private void actualizarEditText(String scan_result) {
        if(btnSelected.equals("cliente")){
            etCliente.setText(scan_result);
        }if(btnSelected.equals("libro")){
            etLibro.setText(scan_result);
        }
    }

}
