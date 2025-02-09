package com.example.fetchcodingexercise

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.fetchcodingexercise.ui.theme.FetchCodingExerciseTheme
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FetchCodingExerciseTheme {
                Surface(
                    modifier = Modifier
                        .fillMaxSize()
                        .statusBarsPadding().navigationBarsPadding(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    DisplayData()
                }
            }
        }
    }

    @Composable
    fun SingleRow(data: HiringDataItem){
        //Card to display the data
        Card(modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .padding(5.dp),
            shape = RoundedCornerShape(CornerSize(10.dp)),
            colors = CardColors(
                contentColor = Color.Black,
                containerColor = Color.LightGray,
                disabledContainerColor = Color.Gray,
                disabledContentColor = Color.Unspecified
            )
        ) {
            //First row is listId
            Row(modifier = Modifier.fillMaxWidth()) {
                Text(text = "List ID: ${data.listId}", textAlign = TextAlign.Left, fontWeight = FontWeight.Bold, fontSize = 20.sp)
            }
            //Second row is name, ignoring id because it is the same as item name
            Row(modifier = Modifier.fillMaxWidth()) {
                data.name?.let { Text(text = it) }
            }
        }

    }

    @Composable
    fun DisplayData() {
        val info = HiringDataInstance.getHiringData.getHiringInfo()
        val data = remember { mutableStateOf<HiringData>(HiringData()) }

        //get response from retrofit call
        info.enqueue(object : Callback<HiringData> {
            override fun onResponse(call: Call<HiringData>, response: Response<HiringData>) {
                val hiringData = response.body()
                if(hiringData != null){
                    data.value = hiringData
                }
            }

            override fun onFailure(call: Call<HiringData>, t: Throwable) {
                Toast.makeText(applicationContext,t.toString(),Toast.LENGTH_LONG).show()
            }
        })
        //List to display all cards
        LazyColumn {
            //filter out all blank and null names as well as sort by the listId then by name
            items(data.value.filter { it.name !in listOf("", null) }.sortedWith(compareBy<HiringDataItem> { it.listId }
                .thenBy { it.name })) { data ->
                SingleRow(data = data)
            }
        }
    }
}