package com.unokim.codelab.guesstheword.screens.score

import androidx.lifecycle.ViewModel
import timber.log.Timber

class ScoreViewModel(finalScore: Int) : ViewModel() {

    // The final score
    var score = finalScore

    init {
        Timber.i("Final score is $finalScore")
    }
}