package ejt.edu.tesoem.genita212.itic.archivostxt;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Environment;
import android.text.Html;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.io.IOException;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    EditText txtnombre;
    TextView mostrar;
    private  final String nomarch="datosGenita212.txt";
    private ArrayList<String> TextoCompleto= new ArrayList<String>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtnombre= findViewById(R.id.txtNombre);
        mostrar= findViewById(R.id.lblmostrar);

    }

    public void mgrabar(View v){
        manejoArchivoTXT controlador = new manejoArchivoTXT();
        String Texto="";
        try {
            Texto=txtnombre.getText().toString();
            controlador.agregar(Texto,TextoCompleto);
            TextoCompleto=controlador.getContenido();
            if(controlador.grabar(TextoCompleto,this,nomarch)){
                Toast.makeText(this,"Se grabo correctamente",Toast.LENGTH_LONG).show();
            }else{
                Toast.makeText(this,"no se grabo correctamente",Toast.LENGTH_LONG).show();
            }
            cargarinfo();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void mleer(View v){ cargarinfo();}

    private void cargarinfo(){
        manejoArchivoTXT objmanarch= new manejoArchivoTXT();
        if(objmanarch.verificar(this,nomarch)){
            Toast.makeText(this,"Existe el archivo...",Toast.LENGTH_LONG).show();

            if(objmanarch.leer(this,nomarch)){
                TextoCompleto= objmanarch.getContenido();
                String cadenita="";

                for(String micadena:TextoCompleto){
                    cadenita+="\n"+micadena;
                }
                mostrar.setText(cadenita);
            }
        }else{
            Toast.makeText(this,"no existe el archivo...",Toast.LENGTH_LONG).show();
        }
    }
}
