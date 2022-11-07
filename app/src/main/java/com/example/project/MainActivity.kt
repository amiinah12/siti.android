package com.example.project

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.project.Conversation
import com.example.project.GenerateDataDummyUser
import com.example.project.R
import com.example.project.ui.theme.ProjectTheme
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import com.example.project.tataletak.TataLetakActivity
import android.content.Intent
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.*
import com.example.project.ui.theme.Teal200

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ProjectTheme(){
                Column() {
                    Button(onClick = {
                        // todo
                        val i = Intent(this@MainActivity, TataLetakActivity::class.java)
                        startActivity(i)
                    }) {

                    }
                    Conversation(GenerateDataDummyUser())
                }
            }
        }
    }
}

fun GenerateDataDummyUser(): ArrayList<User>{
    var users: ArrayList<User> = ArrayList()

    // buat objek user
    var user: User = User("Siti", "Papua asdfasdfaas asdfsafasd asdfsadfa sadfasdfas asdfsafa", 20)
    users.add(user)

    user = User("Maxiel", "Sorongsadfasdfa  asdfsadfasf asdfsafafsa asdfsafsafas", 30)
    users.add(user)

    user = User("Alex", "Merauke asdfsafsa asdfasfa asdfasdfsa asdfsdafafsaasdf asdfadfasadfasdf sadf", 25)
    users.add(user)

    return users
}

data class User(
    val name: String,
    val address: String,
    val umur: Int
)

@Composable
fun Pesan(user: User){
    Row(
        modifier = Modifier.padding(all = 8.dp)
    ) {
        Image(painter =
        painterResource(id = R.drawable.univmusamus),
            contentDescription = "Uni musamus",
            modifier = Modifier
                // Set image size to 40 dp
                .size(40.dp)
                // Clip image to be shaped as a circle
                .clip(CircleShape)
                .border(1.5.dp, MaterialTheme.colors.secondary, CircleShape),
            contentScale = ContentScale.Crop)
        Spacer(modifier = Modifier.width(8.dp))

        // We keep track if the message is expanded or not in this
        // variable
        var isExpanded by remember { mutableStateOf(false) }


        Column(modifier = Modifier.clickable { isExpanded = !isExpanded }) {
            Text(text = user.name, color = Teal200)
            Spacer(modifier = Modifier.height(8.dp))
            Surface(shape = MaterialTheme.shapes.medium, elevation = 2.dp) {
                Text(
                    text = user.address,
                    modifier = Modifier.padding(all = 4.dp),
                    // If the message is expanded, we display all its content
                    // otherwise we only display the first line
                    maxLines = if (isExpanded) Int.MAX_VALUE else 1,

                    style = MaterialTheme.typography.body2
                )
            }
        }
    }
}

@Preview
@Composable
fun PreviewMenampilkanPesan(){
    val user = User("Unmus", "Papua", 30)
    Pesan(user)
}

@Composable
fun Conversation(users: ArrayList<User>){
    LazyColumn {
        items(users) { user ->
            Pesan(user = user)
        }
    }
}