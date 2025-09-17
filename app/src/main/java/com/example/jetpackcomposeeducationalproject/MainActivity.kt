package com.example.jetpackcomposeeducationalproject

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class MainActivity: ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ContactDetails(contactSecond())
        }
    }

    @Composable
    private fun contactFirst() : Contact {
        return Contact(
            name = stringResource(R.string.name_1),
            surname = stringResource(R.string.patronymic_1),
            familyName = stringResource(R.string.surname_1),
            imageRes = null,
            isFavorite = true,
            phone = stringResource(R.string.phone_1),
            address = stringResource(R.string.address_1),
            email = stringResource(R.string.email_1)
        )
    }

    @Composable
    private fun contactSecond() : Contact {
        return Contact(
            name = stringResource(R.string.name_2),
            surname = null,
            familyName = stringResource(R.string.surname_2),
            imageRes = R.drawable.neo,
            isFavorite = false,
            phone = stringResource(R.string.phone_2),
            address = stringResource(R.string.address_2),
            email = null
        )
    }

    @Preview( name = "portrait", showSystemUi = true)
    @Composable
    fun ContactPreviewFirst() {
        ContactDetails(contactFirst())
    }

    @Preview( name = "portrait", showSystemUi = true)
    @Composable
    fun ContactPreviewSecond() {
        ContactDetails(contactSecond())
    }

    @Composable
    fun ContactDetails(contact: Contact) {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(0.25F),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(48.dp)
                        .background(MaterialTheme.colors.primary)
                ) {
                    Text(
                        text = stringResource(R.string.app_name),
                        fontSize = 16.sp,
                        color = MaterialTheme.colors.onPrimary,
                        modifier = Modifier.align(Alignment.CenterStart)
                            .padding(start = 16.dp)
                    )
                }

                Box(
                    modifier = Modifier
                        .weight(0.5F),
                    contentAlignment = Alignment.Center
                ) {
                    ImageInfo(contact)
                }

                Column(
                    modifier = Modifier
                        .weight(0.5F),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    NameInfo(contact)
                }
            }
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(0.75F),
            ) {
                RowInfo(
                    title = stringResource(R.string.phone_title),
                    info = contact.phone
                )
                RowInfo(
                    title = stringResource(R.string.address_title),
                    info = contact.address
                )
                RowInfo(
                    title = stringResource(R.string.email_title),
                    info = contact.email
                )
            }
        }
    }

    @Composable
    fun NameInfo(contact: Contact) {
        Text(
            text = if (contact.surname.isNullOrEmpty()) contact.name else "${contact.name} ${contact.surname}",
            style = MaterialTheme.typography.h6
        )
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Text(
                text = contact.familyName,
                style = MaterialTheme.typography.h5
            )
            if (contact.isFavorite)
                Image(
                    modifier = Modifier.padding(start = 8.dp),
                    painter = painterResource(android.R.drawable.star_big_on),
                    contentDescription = stringResource(R.string.is_favourite_title),
                )
        }
    }

    @Composable
    fun ImageInfo(contact: Contact) {
        if (contact.imageRes != null)
            Image(
                modifier = Modifier
                    .padding(top = 16.dp)
                    .fillMaxSize(),
                painter = painterResource(contact.imageRes),
                contentDescription = stringResource(R.string.image_title),
                contentScale = ContentScale.Fit
            )
        else {
            Box(
                modifier = Modifier.padding(top = 16.dp),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    modifier = Modifier.fillMaxSize(),
                    painter = painterResource(R.drawable.circle),
                    contentDescription = stringResource(R.string.image_title),
                    tint = Color.LightGray

                )
                Text(
                    text = getInitials(contact.name, contact.familyName),
                    style = MaterialTheme.typography.h6
                )
            }
        }
    }

    fun getInitials(name: String, surname: String): String {
        return "${name[0]}${surname[0]}"
    }

    @Composable
    fun RowInfo(title: String, info: String?) {
        if (info != null) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(end = 16.dp, top = 16.dp, start = 16.dp),
            ) {
                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(0.5F),
                    text = title,
                    style = MaterialTheme.typography.body1,
                    fontStyle = FontStyle.Italic,
                    textAlign = TextAlign.End
                )
                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(0.5F)
                        .padding(start = 16.dp),
                    text = info,
                    style = MaterialTheme.typography.body2
                )
            }
        }
    }
}