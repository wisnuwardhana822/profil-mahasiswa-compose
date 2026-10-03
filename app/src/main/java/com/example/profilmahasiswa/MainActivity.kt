package com.example.profilmahasiswa

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.profilmahasiswa.ui.theme.ProfilMahasiswaTheme

private val Midnight = Color(0xFF111C3A)
private val Navy = Color(0xFF183B74)
private val Blue = Color(0xFF315FE8)
private val Cyan = Color(0xFF15B8D4)
private val Purple = Color(0xFF7157E8)

private val Background = Color(0xFFF3F6FA)
private val White = Color(0xFFFFFFFF)
private val Border = Color(0xFFD9E1EC)

private val TextMain = Color(0xFF172033)
private val TextMuted = Color(0xFF6A7486)

private val Success = Color(0xFF137A59)
private val SuccessSoft = Color(0xFFE7F7F0)

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            ProfilMahasiswaTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = Background
                ) {
                    ProfilMahasiswaScreen()
                }
            }
        }
    }
}

@Composable
fun ProfilMahasiswaScreen() {

    // STATE input
    var nama by remember {
        mutableStateOf("")
    }

    var nim by remember {
        mutableStateOf("")
    }

    var programStudi by remember {
        mutableStateOf("")
    }

    // STATE data setelah disimpan
    var savedNama by remember {
        mutableStateOf("")
    }

    var savedNim by remember {
        mutableStateOf("")
    }

    var savedProdi by remember {
        mutableStateOf("")
    }

    var sudahDisimpan by remember {
        mutableStateOf(false)
    }

    val formValid =
        nama.isNotBlank() &&
                nim.isNotBlank() &&
                programStudi.isNotBlank()

    val jumlahTerisi = listOf(
        nama.isNotBlank(),
        nim.isNotBlank(),
        programStudi.isNotBlank()
    ).count { it }

    val progress = jumlahTerisi / 3f

    Scaffold(
        containerColor = Background
    ) { innerPadding ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .verticalScroll(
                    rememberScrollState()
                )
                .padding(
                    horizontal = 18.dp,
                    vertical = 16.dp
                ),
            verticalArrangement =
                Arrangement.spacedBy(16.dp)
        ) {

            HeroSection()

            ProgressSection(
                jumlahTerisi = jumlahTerisi,
                progress = progress,
                formValid = formValid
            )

            LiveProfilePreview(
                nama = nama,
                nim = nim,
                programStudi = programStudi
            )

            FormSection(
                nama = nama,
                nim = nim,
                programStudi = programStudi,

                onNamaChange = {
                    // EVENT -> UPDATE STATE
                    nama = it
                    sudahDisimpan = false
                },

                onNimChange = { value ->
                    if (value.all { it.isDigit() }) {
                        nim = value
                        sudahDisimpan = false
                    }
                },

                onProdiChange = {
                    programStudi = it
                    sudahDisimpan = false
                },

                formValid = formValid,

                onSave = {
                    savedNama = nama.trim()
                    savedNim = nim.trim()
                    savedProdi = programStudi.trim()

                    // perubahan state memicu recomposition
                    sudahDisimpan = true
                }
            )

            if (sudahDisimpan) {
                SavedProfileSection(
                    nama = savedNama,
                    nim = savedNim,
                    programStudi = savedProdi
                )
            }

            Spacer(
                modifier = Modifier.height(10.dp)
            )
        }
    }
}

@Composable
fun HeroSection() {

    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(30.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.Transparent
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 5.dp
        )
    ) {

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    brush = Brush.linearGradient(
                        colors = listOf(
                            Midnight,
                            Navy,
                            Blue
                        )
                    )
                )
                .padding(22.dp)
        ) {

            Column {

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement =
                        Arrangement.SpaceBetween,
                    verticalAlignment =
                        Alignment.CenterVertically
                ) {

                    Row(
                        verticalAlignment =
                            Alignment.CenterVertically
                    ) {

                        Box(
                            modifier = Modifier
                                .size(58.dp)
                                .clip(
                                    RoundedCornerShape(18.dp)
                                )
                                .background(White),
                            contentAlignment =
                                Alignment.Center
                        ) {

                            Text(
                                text = "UM",
                                color = Navy,
                                fontSize = 20.sp,
                                fontWeight =
                                    FontWeight.ExtraBold
                            )
                        }

                        Spacer(
                            modifier = Modifier.size(13.dp)
                        )

                        Column {

                            Text(
                                text = "UNIVERSITAS MULIA",
                                color = White,
                                fontSize = 14.sp,
                                fontWeight = FontWeight.Bold
                            )

                            Text(
                                text = "Mobile Profile Studio",
                                color = White.copy(
                                    alpha = 0.68f
                                ),
                                fontSize = 11.sp
                            )
                        }
                    }

                    Box(
                        modifier = Modifier
                            .clip(
                                RoundedCornerShape(50.dp)
                            )
                            .background(
                                White.copy(
                                    alpha = 0.12f
                                )
                            )
                            .padding(
                                horizontal = 11.dp,
                                vertical = 7.dp
                            )
                    ) {

                        Text(
                            text = "2026",
                            color = White,
                            fontSize = 10.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }

                Spacer(
                    modifier = Modifier.height(25.dp)
                )

                Text(
                    text = "Student Profile",
                    color = White,
                    fontSize = 31.sp,
                    fontWeight = FontWeight.ExtraBold
                )

                Spacer(
                    modifier = Modifier.height(5.dp)
                )

                Text(
                    text = "Kelola profil mahasiswa dan lihat perubahan data secara real-time.",
                    color = White.copy(alpha = 0.78f),
                    fontSize = 13.sp,
                    lineHeight = 19.sp
                )

                Spacer(
                    modifier = Modifier.height(18.dp)
                )

                Row(
                    horizontalArrangement =
                        Arrangement.spacedBy(7.dp)
                ) {
                    Tag("STATE")
                    Tag("EVENT")
                    Tag("RECOMPOSITION")
                }
            }
        }
    }
}

@Composable
fun Tag(text: String) {

    Box(
        modifier = Modifier
            .clip(
                RoundedCornerShape(50.dp)
            )
            .background(
                White.copy(alpha = 0.12f)
            )
            .padding(
                horizontal = 11.dp,
                vertical = 6.dp
            )
    ) {

        Text(
            text = text,
            color = White.copy(alpha = 0.90f),
            fontSize = 9.sp,
            fontWeight = FontWeight.Bold
        )
    }
}

@Composable
fun ProgressSection(
    jumlahTerisi: Int,
    progress: Float,
    formValid: Boolean
) {

    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(24.dp),
        colors = CardDefaults.cardColors(
            containerColor = White
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 2.dp
        )
    ) {

        Column(
            modifier = Modifier.padding(18.dp)
        ) {

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement =
                    Arrangement.SpaceBetween,
                verticalAlignment =
                    Alignment.CenterVertically
            ) {

                Column {

                    Text(
                        text = "Profile Completion",
                        color = TextMuted,
                        fontSize = 12.sp
                    )

                    Spacer(
                        modifier = Modifier.height(3.dp)
                    )

                    Text(
                        text = "$jumlahTerisi dari 3 data",
                        color = TextMain,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold
                    )
                }

                Box(
                    modifier = Modifier
                        .clip(
                            RoundedCornerShape(50.dp)
                        )
                        .background(
                            if (formValid)
                                SuccessSoft
                            else
                                Color(0xFFF1F4F8)
                        )
                        .padding(
                            horizontal = 13.dp,
                            vertical = 8.dp
                        )
                ) {

                    Text(
                        text =
                            if (formValid)
                                "READY"
                            else
                                "${(progress * 100).toInt()}%",
                        color =
                            if (formValid)
                                Success
                            else
                                TextMuted,
                        fontSize = 11.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            }

            Spacer(
                modifier = Modifier.height(14.dp)
            )

            LinearProgressIndicator(
                progress = { progress },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(8.dp)
                    .clip(
                        RoundedCornerShape(50.dp)
                    ),
                color = Blue,
                trackColor = Color(0xFFE8EDF5)
            )
        }
    }
}

@Composable
fun LiveProfilePreview(
    nama: String,
    nim: String,
    programStudi: String
) {

    val displayNama =
        nama.ifBlank {
            "Nama Mahasiswa"
        }

    val displayNim =
        nim.ifBlank {
            "NIM belum diisi"
        }

    val displayProdi =
        programStudi.ifBlank {
            "Program Studi"
        }

    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(26.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color(0xFFEEF3FF)
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 0.dp
        )
    ) {

        Column(
            modifier = Modifier.padding(18.dp)
        ) {

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement =
                    Arrangement.SpaceBetween,
                verticalAlignment =
                    Alignment.CenterVertically
            ) {

                Text(
                    text = "LIVE PROFILE",
                    color = Blue,
                    fontSize = 11.sp,
                    fontWeight = FontWeight.Bold
                )

                Box(
                    modifier = Modifier
                        .clip(
                            RoundedCornerShape(50.dp)
                        )
                        .background(
                            Color(0xFFDCE6FF)
                        )
                        .padding(
                            horizontal = 10.dp,
                            vertical = 6.dp
                        )
                ) {

                    Text(
                        text = "AUTO UPDATE",
                        color = Blue,
                        fontSize = 9.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            }

            Spacer(
                modifier = Modifier.height(15.dp)
            )

            Row(
                verticalAlignment =
                    Alignment.CenterVertically
            ) {

                Box(
                    modifier = Modifier
                        .size(64.dp)
                        .clip(CircleShape)
                        .background(
                            brush =
                                Brush.linearGradient(
                                    colors = listOf(
                                        Blue,
                                        Purple
                                    )
                                )
                        ),
                    contentAlignment =
                        Alignment.Center
                ) {

                    Text(
                        text = initials(displayNama),
                        color = White,
                        fontSize = 21.sp,
                        fontWeight = FontWeight.Bold
                    )
                }

                Spacer(
                    modifier = Modifier.size(14.dp)
                )

                Column {

                    Text(
                        text = displayNama,
                        color = TextMain,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold
                    )

                    Spacer(
                        modifier = Modifier.height(3.dp)
                    )

                    Text(
                        text = displayNim,
                        color = TextMuted,
                        fontSize = 13.sp
                    )

                    Text(
                        text = displayProdi,
                        color = Blue,
                        fontSize = 12.sp,
                        fontWeight = FontWeight.SemiBold
                    )
                }
            }
        }
    }
}

@Composable
fun FormSection(
    nama: String,
    nim: String,
    programStudi: String,
    onNamaChange: (String) -> Unit,
    onNimChange: (String) -> Unit,
    onProdiChange: (String) -> Unit,
    formValid: Boolean,
    onSave: () -> Unit
) {

    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(28.dp),
        colors = CardDefaults.cardColors(
            containerColor = White
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 3.dp
        )
    ) {

        Column(
            modifier = Modifier.padding(20.dp),
            verticalArrangement =
                Arrangement.spacedBy(15.dp)
        ) {

            Text(
                text = "Informasi Mahasiswa",
                color = TextMain,
                fontSize = 21.sp,
                fontWeight = FontWeight.Bold
            )

            Text(
                text = "Masukkan data berikut. Live Profile akan berubah otomatis saat input diperbarui.",
                color = TextMuted,
                fontSize = 13.sp,
                lineHeight = 19.sp
            )

            InputField(
                title = "Nama Lengkap",
                value = nama,
                placeholder = "Wisnu Wardhana",
                onValueChange = onNamaChange
            )

            Column(
                verticalArrangement =
                    Arrangement.spacedBy(7.dp)
            ) {

                FieldTitle("NIM")

                OutlinedTextField(
                    value = nim,
                    onValueChange = onNimChange,
                    placeholder = {
                        Text("2411037")
                    },
                    keyboardOptions =
                        KeyboardOptions(
                            keyboardType =
                                KeyboardType.Number
                        ),
                    singleLine = true,
                    modifier =
                        Modifier.fillMaxWidth(),
                    shape =
                        RoundedCornerShape(16.dp),
                    colors = inputColors()
                )
            }

            InputField(
                title = "Program Studi",
                value = programStudi,
                placeholder = "S1 Informatika",
                onValueChange = onProdiChange
            )

            if (!formValid) {

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(
                            RoundedCornerShape(14.dp)
                        )
                        .background(
                            Color(0xFFF7F9FC)
                        )
                        .padding(13.dp)
                ) {

                    Text(
                        text = "Lengkapi semua data untuk mengaktifkan tombol Simpan Profil.",
                        color = TextMuted,
                        fontSize = 11.sp,
                        lineHeight = 17.sp
                    )
                }
            }

            Button(
                onClick = onSave,
                enabled = formValid,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp),
                shape = RoundedCornerShape(16.dp),
                colors =
                    ButtonDefaults.buttonColors(
                        containerColor = Blue,
                        contentColor = White,
                        disabledContainerColor =
                            Color(0xFFDDE4ED),
                        disabledContentColor =
                            Color(0xFF97A2B1)
                    )
            ) {

                Text(
                    text = "Simpan Profil",
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}

@Composable
fun InputField(
    title: String,
    value: String,
    placeholder: String,
    onValueChange: (String) -> Unit
) {

    Column(
        verticalArrangement =
            Arrangement.spacedBy(7.dp)
    ) {

        FieldTitle(title)

        OutlinedTextField(
            value = value,
            onValueChange = onValueChange,
            placeholder = {
                Text(placeholder)
            },
            singleLine = true,
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(16.dp),
            colors = inputColors()
        )
    }
}

@Composable
fun FieldTitle(text: String) {

    Text(
        text = text,
        color = TextMain,
        fontSize = 12.sp,
        fontWeight = FontWeight.SemiBold
    )
}

@Composable
fun inputColors() =
    OutlinedTextFieldDefaults.colors(
        focusedBorderColor = Blue,
        unfocusedBorderColor = Border,
        cursorColor = Blue,
        focusedContainerColor = White,
        unfocusedContainerColor = White
    )

@Composable
fun SavedProfileSection(
    nama: String,
    nim: String,
    programStudi: String
) {

    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(30.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.Transparent
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 5.dp
        )
    ) {

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    brush =
                        Brush.linearGradient(
                            colors = listOf(
                                Midnight,
                                Navy,
                                Purple
                            )
                        )
                )
                .padding(22.dp)
        ) {

            Column {

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement =
                        Arrangement.SpaceBetween,
                    verticalAlignment =
                        Alignment.CenterVertically
                ) {

                    Column {

                        Text(
                            text = "PROFILE VERIFIED",
                            color = Cyan,
                            fontSize = 11.sp,
                            fontWeight = FontWeight.Bold
                        )

                        Spacer(
                            modifier = Modifier.height(5.dp)
                        )

                        Text(
                            text = "Data Tersimpan",
                            color = White,
                            fontSize = 25.sp,
                            fontWeight = FontWeight.ExtraBold
                        )
                    }

                    Box(
                        modifier = Modifier
                            .size(48.dp)
                            .clip(CircleShape)
                            .background(Success),
                        contentAlignment =
                            Alignment.Center
                    ) {

                        Text(
                            text = "✓",
                            color = White,
                            fontSize = 22.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }

                Spacer(
                    modifier = Modifier.height(20.dp)
                )

                HorizontalDivider(
                    color = White.copy(alpha = 0.13f)
                )

                Spacer(
                    modifier = Modifier.height(18.dp)
                )

                SavedData(
                    label = "NAMA LENGKAP",
                    value = nama
                )

                Spacer(
                    modifier = Modifier.height(15.dp)
                )

                SavedData(
                    label = "NIM",
                    value = nim
                )

                Spacer(
                    modifier = Modifier.height(15.dp)
                )

                SavedData(
                    label = "PROGRAM STUDI",
                    value = programStudi
                )

                Spacer(
                    modifier = Modifier.height(20.dp)
                )

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(
                            RoundedCornerShape(16.dp)
                        )
                        .background(
                            White.copy(alpha = 0.09f)
                        )
                        .padding(14.dp)
                ) {

                    Text(
                        text = "Perubahan state berhasil diproses. Tampilan diperbarui otomatis melalui recomposition.",
                        color = White.copy(alpha = 0.78f),
                        fontSize = 11.sp,
                        lineHeight = 17.sp
                    )
                }
            }
        }
    }
}

@Composable
fun SavedData(
    label: String,
    value: String
) {

    Column {

        Text(
            text = label,
            color = White.copy(alpha = 0.50f),
            fontSize = 10.sp,
            fontWeight = FontWeight.SemiBold
        )

        Spacer(
            modifier = Modifier.height(4.dp)
        )

        Text(
            text = value,
            color = White,
            fontSize = 17.sp,
            fontWeight = FontWeight.Bold
        )
    }
}

fun initials(name: String): String {

    val words = name
        .trim()
        .split(" ")
        .filter {
            it.isNotBlank()
        }

    return when {

        words.isEmpty() ->
            "UM"

        words.size == 1 ->
            words.first()
                .take(2)
                .uppercase()

        else ->
            (
                    words[0].take(1) +
                            words[1].take(1)
                    ).uppercase()
    }
}