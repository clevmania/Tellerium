package com.clevmania.tellerium.ui.model

/**
 * @author by Lawrence on 10/24/20.
 * for Tellerium
 */
data class TelleriumApiResponse<out T>(val status : String?, val data: T?)