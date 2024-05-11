package com.example.test.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.test.bottom_nav.Route
import com.example.test.models.HistoryVModel
import com.example.test.ui.theme.LightColor
import com.example.test.ui.theme.TextColor
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.MarkerState
import com.google.maps.android.compose.rememberCameraPositionState

@Composable
fun MapScreen(
    vm: HistoryVModel,
    navHostController: NavHostController
) {
    val cameraPositionState = rememberCameraPositionState {
        position = vm.cameraPosition
    }

    LaunchedEffect(cameraPositionState.position) {
        vm.cameraPosition = cameraPositionState.position
    }
    Column(
        modifier = Modifier
            .fillMaxSize(),
//            .padding(horizontal = 24.dp),
//        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        GoogleMap(
            modifier = Modifier.fillMaxWidth().fillMaxHeight(0.905F),
            cameraPositionState = cameraPositionState,
            onMapClick =  { latLng ->
                vm.curLatLng = latLng
            },
        ) {
            Marker(
                state = MarkerState(position = vm.curLatLng),
            )
        }
        Row(

            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .fillMaxWidth()
        ) {
            Button(
                onClick = {
                    vm.submitOnMap()
                    navHostController.navigate(Route.HistoryScreen) },
                modifier = Modifier.height(80.dp).fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(containerColor = LightColor),
                shape = RoundedCornerShape(2.dp),
                elevation = ButtonDefaults.buttonElevation(
                    defaultElevation = 2.dp,
                    pressedElevation = 1.dp,
                    )
            ) {
                Text(
                    "Submit",
                    fontSize = 16.sp,
                    color = TextColor
                )
            }
        }
    }
}

