package com.moontowertickets.randomuserdemo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.moontowertickets.randomuserdemo.data.remote.NameDto
import com.moontowertickets.randomuserdemo.data.remote.PictureDto
import com.moontowertickets.randomuserdemo.data.remote.UserDto
import com.moontowertickets.randomuserdemo.ui.theme.RandomUserDemoTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val viewModel: UserViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel.loadUsers(page = 2, results = 50)

        setContent {
            RandomUserDemoTheme {

                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Column {
                        Greeting("Android")
                        UserList(state = viewModel.state)
                    }
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
            .padding(bottom = 30.dp)
    )
}

@Composable
fun UserList(
    state: UserState,
) {
    LazyColumn(
        modifier = Modifier.fillMaxSize()
    ) {
        items(state.users) { item ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                horizontalArrangement = Arrangement.Start
            ) {
                AsyncImage(
                    model = item.picture.large,
                    contentDescription = "${item.name.first} user imager",
                    modifier = Modifier
                        .padding(end = 16.dp)
                        .width(100.dp)
                        .height(100.dp)
                        .clip(RoundedCornerShape(20.dp))
                )
                Spacer(
                    modifier = Modifier.width(16.dp)
                )
                Text(
                    text = "Hello ${item.name.title} ${item.name.first}",
                    fontSize = 12.sp
                )
                Text(
                    text = " ${item.name.last}",
                    fontSize = 12.sp
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    RandomUserDemoTheme {
        Column {
            Greeting("Android")
            UserList(
                state = UserState(
                    users = List(1) {
                        UserDto(
                            email = "",
                            gender = "",
                            name = NameDto(
                                first = "First",
                                last = "Last",
                                title = "Title"
                            ),
                            picture = PictureDto(
                                large = "https://randomuser.me/api/portraits/women/61.jpg",
                                medium = "https://randomuser.me/api/portraits/women/61.jpg",
                                thumbnail = "https://randomuser.me/api/portraits/women/61.jpg"
                            )
                        )
                    }
                )
            )
        }
    }
}