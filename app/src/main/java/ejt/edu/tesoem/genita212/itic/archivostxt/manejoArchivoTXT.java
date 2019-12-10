package ejt.edu.tesoem.genita212.itic.archivostxt;

import android.app.Activity;
import android.content.Context;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

public class manejoArchivoTXT {
    private ArrayList<String> TextoCompleto=new ArrayList<String>();

    public void agregar(String dato, ArrayList<String> contenido){
        TextoCompleto=contenido;
        TextoCompleto.add(dato);
    }

    public boolean grabar(ArrayList<String> dato, Context contexto, String nomarch) throws IOException {
        boolean estado=true;
        try{
            OutputStreamWriter achivo = new OutputStreamWriter(contexto.openFileOutput(nomarch, Activity.MODE_PRIVATE));
            for(String Texto:dato)
                achivo.write(Texto+"\n");
                achivo.flush();
                achivo.close();
        }catch (Exception e){
            estado=false;
        }
        return estado;
    }

    public boolean leer(Context context, String nomarch){
        ArrayList<String> textcompleto=new ArrayList<String>();
        try{
            InputStreamReader archivo = new InputStreamReader(context.openFileInput(nomarch));
            BufferedReader br= new BufferedReader(archivo);
            String cadena= br.readLine();
            while(cadena!=null){
                textcompleto.add(cadena);
                cadena=br.readLine();
            }

        }catch(Exception e){
            e.printStackTrace();
            return false;
        }
        TextoCompleto=textcompleto;
        return true;
    }

    public boolean verificar(Context context, String nomarch){
        String[] archivos= context.fileList();
        for (String archivo: archivos){
            if(archivo.equals(nomarch)) return true;
        }
        return false;
    }

    public ArrayList<String> getContenido()
    {
        return TextoCompleto;
    }
}
