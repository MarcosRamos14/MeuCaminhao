package com.dys.mobile.meucaminhao.viewmodels

import com.dys.mobile.meucaminhao.domain.state.DefaultUiStateManager
import com.dys.mobile.meucaminhao.domain.state.UiStateManager

abstract class BaseViewModel : ExpectBaseViewModel(), UiStateManager by DefaultUiStateManager()