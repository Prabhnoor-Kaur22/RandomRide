package com.example.drake

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.drake.ui.theme.DrakeTheme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            DrakeTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Box(modifier = Modifier.padding(innerPadding)) {
                        ConnectingScreen()
                    }
                }
            }
        }
    }
}

data class Parts<out T>(val first: T, val second: T)


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    DrakeTheme  {
        FicPic()
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview2() {
    DrakeTheme {
        ConnectingScreen()    }
}
@Preview(showBackground = true)
@Composable
fun GreetingPreview1() {
    DrakeTheme {
        StartScreen(onStartClicked = { /* Preview doesn't need functionality */ })
    }
}


@Composable
fun FicPic() {
    var currentStep by remember { mutableStateOf(1) }
    var verse by remember { mutableStateOf(1) }
    val imageParts: Parts<Any> = when (currentStep) {
        1 -> Parts(R.drawable.download__2_, "porsche")
        2 -> Parts(R.drawable.porche, "mercedes")
        3 -> Parts(R.drawable.hot_ass_laferreri, "ferrari")
        else -> Parts(R.drawable.mercedes, "super cars")
    }
    val drawableResourceId = imageParts.first as Int
    val contentDescription = imageParts.second as String


    val textParts: Parts<Any> = when (verse) {
        1 -> Parts(R.string.porsche, 30.sp)
        2 -> Parts(R.string.mercedes, 35.sp)
        3 -> Parts(R.string.ferrari, 25.sp)
        else -> Parts(R.string.cars, 25.sp)


    }

    val resourceLabelId = textParts.first as Int
    val fontSize = textParts.second as TextUnit




    Setting(
        drawableResourceId = drawableResourceId,
        resourceLabelId = resourceLabelId,
        contentDescription = contentDescription,
        fontSize = fontSize,
        onOmenButtonClick = { currentStep = (1..4).random() },
        onVerseButtonClick = { verse = (1..4).random() }
    )
}
@Composable
fun StartScreen(onStartClicked: () -> Unit){

    Column(modifier = Modifier

        .fillMaxSize()
        .background(Color.Black),


        verticalArrangement = Arrangement.Center,

        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            "Drive Your Mind Forward",
            fontWeight = FontWeight.Bold,
            fontSize = 20.sp,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        Button({ onStartClicked()  },
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.Unspecified,
                contentColor = Color.White
            ),
            shape = MaterialTheme.shapes.medium,

        ) {

            Text(stringResource(R.string.start))

        }
    }

}
@Composable
fun ConnectingScreen(){
    var shouldShowStartScreen by remember { mutableStateOf(true) }

    if(shouldShowStartScreen){
        StartScreen(onStartClicked = { shouldShowStartScreen = false })
    }else{
        (FicPic())
    }

}



@Composable
fun Setting(drawableResourceId:Int,
            resourceLabelId:Int,
            contentDescription:String,
            fontSize: TextUnit = 25.sp,
            onOmenButtonClick: () -> Unit,
            onVerseButtonClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .wrapContentSize()


    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Black)

        ) {
            Image(
                painter = painterResource(id = drawableResourceId),
                contentDescription = contentDescription,
                modifier = Modifier
                    .size(width = 550.dp, height = 1200.dp), 

            )
            Text(
                text = stringResource(id = resourceLabelId),
                fontWeight = FontWeight.Bold,
                color = Color.White,
                fontSize = fontSize,
                modifier = Modifier
                    .align(Alignment.TopEnd)

                    .padding(16.dp)
                    .background(
                        color = Color.Black.copy(alpha = 0.3f),
                    )


            )



            Row(
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .padding(bottom = 48.dp)
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                Spacer(modifier = Modifier.height(16.dp))
                Button(onClick = onOmenButtonClick) {
                    Spacer(Modifier.width(16.dp))
                    Text(stringResource(R.string.Omen))
                }
                Spacer(
                    modifier = Modifier.height(16.dp),


                    )
                Button(onClick = onVerseButtonClick,
                        shape = RoundedCornerShape(size = 8.dp)
                ) {
                    Spacer(Modifier.width(16.dp))
                    Text(stringResource(R.string.Verse))
                }
            }
        }



    }
}