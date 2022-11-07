package com.example.project.tataletak

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
//import com.example.project.Conversation
//import com.example.project.GenerateDataDummyUser
import com.example.project.R
import com.example.project.ui.theme.ProjectTheme
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import com.example.project.ui.theme.ProjectTheme


class TataLetakActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ProjectTheme(){
                Column(){
                    SearchBar()
                    AlignYourBodyRow()
                    Row() {
                        FavoriteCollectionCard(
                            text = R.string.ab1_inversions,
                            drawable = R.drawable.univmusamus
                        )
                        FavoriteCollectionCard(
                            text = R.string.ab2_inversions,
                            drawable = R.drawable.univmusamus
                        )
                    }
                }
            }
        }
    }
}
@Preview
@Composable
fun SearchBar(modifier: Modifier = Modifier){
    TextField(
        value = "",
        onValueChange = {},
        leadingIcon = {
            Icon(
                imageVector = Icons.Default.Search,
                contentDescription = null
            )
        },
        placeholder = {
            Text(stringResource(id = R.string.placeholder_search))
        },
        colors = TextFieldDefaults.textFieldColors(
            backgroundColor = MaterialTheme.colors.surface
        ),
        modifier = modifier
            .fillMaxWidth()
            .heightIn(min = 56.dp))
}


@Composable
fun AlignYourBodyElement(
    @DrawableRes drawable: Int,
    @StringRes text: Int,
    modifier: Modifier = Modifier){
    Column(modifier = modifier.padding(8.dp), horizontalAlignment = Alignment.CenterHorizontally) {
        Image(
            painter = painterResource(drawable),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(88.dp)
                .clip(CircleShape)
        )
        Text(
            text = stringResource(text),
            modifier = Modifier.paddingFromBaseline(
                top = 24.dp, bottom = 8.dp
            )
        )
    }
}

@Composable
fun FavoriteCollectionCard(
    @DrawableRes drawable: Int,
    @StringRes text: Int,
    modifier: Modifier = Modifier
) {
    Surface(shape = MaterialTheme.shapes.small, modifier = modifier) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.width(192.dp)
        ){
            Image(
                painter = painterResource(id = drawable),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier.size(56.dp))
            Text(
                text = stringResource(id = text),
                modifier = Modifier.padding(horizontal = 16.dp)
            )
        }
    }
}

data class Body(
    val image: Int,
    val name: Int
)

fun generateDataBody(): ArrayList<Body>{
    var data: ArrayList<Body> = ArrayList()

    var body: Body = Body(R.drawable.univmusamus, R.string.ab1_inversions)
    data.add(body)

    body = Body(R.drawable.univmusamus, R.string.ab2_inversions)
    data.add(body)

    body = Body(R.drawable.univmusamus, R.string.ab3_inversions)
    data.add(body)

    body = Body(R.drawable.univmusamus, R.string.ab4_inversions)
    data.add(body)

    return data
}

@Composable
fun AlignYourBodyRow(
    modifier: Modifier = Modifier
){
    LazyRow(modifier = modifier) {
        items(generateDataBody()) { item ->
            AlignYourBodyElement( item.image, item.name)
        }
    }
}