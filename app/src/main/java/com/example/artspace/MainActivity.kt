package com.example.artspace

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.artspace.ui.theme.ArtSpaceTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ArtSpaceTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {

                    val datos=loadData();
                    ArtApp(datos)
                }
            }
        }
    }
}


fun loadData(): ArrayList<DatosImagen> {
    val datosImagenes = ArrayList<DatosImagen>()
    datosImagenes.add(DatosImagen(R.drawable.caminante_64390e7409ef4,"El caminante sobre el mar de nubes","Caspar David Friedrich", "1818"))
    datosImagenes.add(DatosImagen(R.drawable.elle_escuela_de_atenas_1_1586849897,"La escuela de Atenas","Rafael Sanzio", "1510-1512"))
    datosImagenes.add(DatosImagen(R.drawable.el_nacimiento_de_venus_1639395197,"El nacimiento de Venus","Sandro Botticelli", "1482-1485"))
    datosImagenes.add(DatosImagen(R.drawable.la_muerte_de_marat_1639394168,"La muerte de Marat","Jacques-Louis David", "1793"))
    return datosImagenes
}

@Composable
fun ArtApp(datos: ArrayList<DatosImagen>, modifier: Modifier = Modifier) {
    val tamDatos = datos.size
    var indexDatosImagenActual by remember { mutableStateOf(0) };
    val datosImagenActual=datos[indexDatosImagenActual]

    Column(modifier= modifier
        .fillMaxSize()
        .background(Color.Gray),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally)
    {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier= modifier
            .fillMaxWidth()
            .background(Color.Green))
        {
            Image(painter = painterResource(datosImagenActual.id), contentDescription = datosImagenActual.titulo)

        }
        Column(modifier= modifier
            .weight(1f)
            .background(Color.Blue)){
            Text(
                text = datosImagenActual.titulo
            )
            Text(
                text = datosImagenActual.autor
            )
            Text(
                text = datosImagenActual.fecha
            )
        }
        Row(modifier=modifier.background(Color.Red)){
            Button(onClick =
            {
                if(indexDatosImagenActual==0)
                {
                    indexDatosImagenActual=tamDatos-1;
                }
                else
                {
                    indexDatosImagenActual--;
                }
            }){
                Text("Previous")
            }
            Button(onClick =
            {
                if(indexDatosImagenActual+1==tamDatos)
                {
                    indexDatosImagenActual=0;
                }
                else
                {
                    indexDatosImagenActual++;
                }
            }){
                Text("Next")
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ArtSpaceTheme {
        ArtApp(loadData())
    }
}