package com.chip.chiptest.presentation.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import com.chip.chiptest.R
import com.chip.chiptest.domain.model.BreedsResponseDomain
import com.chip.chiptest.presentation.ui.screen.AlertDialogExample
import com.chip.chiptest.presentation.ui.screen.ChipTestTheme
import com.chip.chiptest.presentation.viewmodels.DogViewModel
import com.chip.chiptest.utils.ApiResult
import com.chip.chiptest.utils.capitalizeFirstLetter
import org.koin.androidx.viewmodel.ext.android.viewModel

/**
 * The main activity of the application.
 *
 * This activity displays a list of dog breeds using Jetpack Compose.
 */
@OptIn(ExperimentalMaterial3Api::class)
class MainActivity : ComponentActivity() {

    private val dogsViewModel: DogViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ChipTestTheme {
                Scaffold(
                    topBar = {
                        TopAppBar(
                            title = {
                                Row(
                                    modifier = Modifier.fillMaxWidth(),
                                    horizontalArrangement = Arrangement.Start
                                ) {
                                    Icon(
                                        painter = painterResource(id = R.drawable.app_icon),
                                        contentDescription = null,
                                        tint = Color.Unspecified, // Use this to preserve the original icon color
                                        modifier = Modifier
                                            .size(dimensionResource(id = R.dimen.dimen_32dp))  // Adjust the size as needed
                                    )
                                    Text(
                                        text = getString(R.string.list_of_breeds_toolbar_title),
                                        modifier = Modifier.padding(start = dimensionResource(id = R.dimen.dimen_8dp))
                                    )

                                }
                            },
                            navigationIcon = {
                            },
                            actions = {
                            },
                            modifier = Modifier.fillMaxWidth()

                            )
                    },
                    content = { _ ->
                        Surface(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(top = dimensionResource(id = R.dimen.dimen_62dp)),
                            color = MaterialTheme.colorScheme.background
                        ) {
                            val state = dogsViewModel.breedsLiveData.observeAsState()
                            DogsList(state)
                        }
                    }
                )
            }
        }


        if (savedInstanceState == null) {
            dogsViewModel.loadBreeds()
        }
    }

    /**
     * A Composable function that displays a list of dog breeds.
     *
     * @param state The current state of the API result, which can be Loading, Error, or Success.
     */

    @Composable
    fun DogsList(state: State<ApiResult<BreedsResponseDomain>?>) {

        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            when (val result = state.value) {
                ApiResult.Loading -> {

                    CircularProgressIndicator(
                        modifier = Modifier.size( dimensionResource(id = R.dimen.dimen_50dp)),
                        color = MaterialTheme.colorScheme.primary
                    )

                }

                is ApiResult.Error -> {
                    AlertDialogExample(
                        onConfirmation = {
                            finish()
                        },
                        dialogText = stringResource(R.string.dialog_error_msg),
                        dialogTitle = stringResource(R.string.dialog_error_title)
                    )
                }

                is ApiResult.Success -> {
                    LazyColumn {

                        val listOfBreeds = result.data.listOfBreeds

                        if (listOfBreeds.isNullOrEmpty()) {
                            item {
                                Text(stringResource(R.string.no_results_list_empty), textAlign = TextAlign.Center)
                            }
                        } else {
                            items(listOfBreeds) { breed ->
                                BreedCard(breed = breed)
                            }
                        }
                    }

                }

                else -> {}
            }
        }


    }

    /**
     * A Composable function that displays a card representing a breed.
     *
     * @param breed The name of the breed to display.
     */
    @Composable
    fun BreedCard(breed: String) {
        Card(
            modifier = Modifier
                .padding( dimensionResource(id = R.dimen.dimen_8dp))
                .fillMaxWidth(),
            onClick = {
                BreedsGalleryActivity.openActivity(this, breed = breed)
            }
        ) {
            Column(
                modifier = Modifier.padding( dimensionResource(id = R.dimen.dimen_16dp))
            ) {
                Text(
                    text = breed.capitalizeFirstLetter(),
                    style = MaterialTheme.typography.headlineMedium
                )
            }
        }
    }


}
