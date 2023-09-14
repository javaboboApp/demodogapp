package com.chip.chiptest.presentation.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import coil.compose.rememberAsyncImagePainter
import com.chip.chiptest.R
import com.chip.chiptest.domain.model.BreedsImageDomain
import com.chip.chiptest.presentation.ui.screen.AlertDialogExample
import com.chip.chiptest.presentation.ui.screen.ChipTestTheme
import com.chip.chiptest.presentation.viewmodels.DogViewModel
import com.chip.chiptest.utils.ApiResult
import com.chip.chiptest.utils.capitalizeFirstLetter
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.lang.IllegalStateException

/**
 * The BreedsGalleryActivity displays a gallery of dog images for a specific breed.
 */

@OptIn(ExperimentalMaterial3Api::class)
class BreedsGalleryActivity : ComponentActivity() {

    private val dogsViewModel: DogViewModel by viewModel()


    private lateinit var breed: String

    companion object {
        const val BREED_KEY = "BREED_KEY"

        fun openActivity(
            context: Context,
            breed: String
        ) {
            val intent = Intent(context, BreedsGalleryActivity::class.java)
            intent.putExtra(BREED_KEY, breed)
            context.startActivity(intent)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        breed = intent?.getStringExtra(BREED_KEY)
            ?: throw IllegalStateException("You need to pass a breed! by argument")

        setContent {
            ChipTestTheme {
                Scaffold(
                    topBar = {
                        TopAppBar(
                            title = {
                                Text(
                                    text = getString(
                                        R.string.breed_gallery_title_toolbar,
                                        breed.capitalizeFirstLetter()
                                    ),
                                    fontWeight = FontWeight.Bold
                                )

                            },
                            navigationIcon = {
                                IconButton(
                                    onClick = {
                                        finish()
                                    }
                                ) {
                                    Icon(
                                        imageVector = Icons.Default.ArrowBack,
                                        contentDescription = null
                                    )
                                }
                            },
                            actions = {
                            },
                            modifier = Modifier.fillMaxWidth(),

                            )
                    },
                    content = { _ ->
                        Surface(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(top = dimensionResource(id = R.dimen.dimen_62dp)),
                            color = MaterialTheme.colorScheme.background
                        ) {
                            val state = dogsViewModel.breedsImagesLiveData.observeAsState()
                            DogsImagesList(state)
                        }
                    }
                )
            }
        }

        if (savedInstanceState == null) {
            dogsViewModel.loadRandomImages(breed = breed)
        }

    }

    /**
     * A Composable function that displays a list of dog images.
     *
     * @param state The current state of the API result, which can be Loading, Error, or Success.
     */
    @Composable
    fun DogsImagesList(state: State<ApiResult<BreedsImageDomain>?>) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            when (val result = state.value) {
                ApiResult.Loading -> {
                    CircularProgressIndicator(
                        modifier = Modifier.size(dimensionResource(id = R.dimen.dimen_50dp)),
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
                    val images = result.data.images
                    if (images.isNullOrEmpty()) {
                        Text(
                            stringResource(R.string.no_images_results_text),
                            modifier = Modifier.padding(dimensionResource(id = R.dimen.dimen_16dp)),
                            textAlign = TextAlign.Center
                        )
                    } else {
                        LazyVerticalGrid(
                            columns = GridCells.Fixed(1),
                            content = {
                                items(images.size) { index ->
                                    ImageFromUrl(images[index])
                                }
                            }
                        )
                    }

                }

                else -> {}
            }
        }
    }

    /**
     * A Composable function for displaying an image loaded from a URL.
     *
     * @param imageUrl The URL of the image to be loaded.
     */
    @Composable
    fun ImageFromUrl(imageUrl: String) {
        val painter = rememberAsyncImagePainter(
            model = imageUrl,
            placeholder = painterResource(id = R.drawable.placeholder_image),
            error = painterResource(id = R.drawable.placeholder_image)
        )

        Image(
            painter = painter,
            contentDescription = null,
            contentScale = ContentScale.Crop, // Apply center crop effect
            modifier = Modifier
                .width(dimensionResource(id = R.dimen.dimen_300dp))
                .aspectRatio(1f) // Ensure the aspect ratio is 1:1 for a fixed size
        )
    }

}