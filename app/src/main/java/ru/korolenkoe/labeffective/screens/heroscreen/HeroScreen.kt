package ru.korolenkoe.labeffective.screens.heroscreen

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorMatrix
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import ru.korolenkoe.labeffective.cards.ErrorCard
import ru.korolenkoe.labeffective.screens.heroscreen.components.*
import ru.korolenkoe.labeffective.screens.mainscreen.viewmodels.CharacterDBViewModel


@SuppressLint("StateFlowValueCalledInComposition")
@Composable
fun HeroScreen(
    navController: NavController?,
    id: Int,
    viewModel: ViewModelGetHeroApi = ViewModelGetHeroApi(),
    characterDBViewModel: CharacterDBViewModel?
) {

    val hero = GetHeroById(id, viewModel, characterDBViewModel!!)
    val colorMatrix = ColorMatrix()

    BackButton(navController)

    if (viewModel.status.collectAsState().value.name == "ERROR") {
        ErrorCard()
    } else {
        colorMatrix.setToSaturation(0f)
        Box(
            modifier = Modifier
                .fillMaxSize()
        ) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                HeroLogo(hero)
            }
        }

        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(20.dp),
            contentAlignment = Alignment.BottomStart
        ) {
            Column {
                HeroName(hero.name)
                HeroDescription(hero.description)
            }
        }
    }
}

@Preview
@Composable
fun HeroScreenPreview() {
//    ClearHroScreen()
}
