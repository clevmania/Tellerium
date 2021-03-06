package com.clevmania.tellerium.utils

/**
 * @author by Lawrence on 10/24/20.
 * for Tellerium
 */
object Constants{
    const val  PREF_IMAGE_URL = "image_url_pref"
    const val  PREF_IMAGE_URL_KEY = "image_url_pref_key"
    const val  PREF_DEFAULT_IMAGE_URL = "default_image_url"
    const val DATABASE_NAME: String = "farmers.db"
    const val PERSONAL_PAGE_INDEX = 0
    const val IDENTITY_PAGE_INDEX = 1
    const val FARM_PAGE_INDEX = 2
    const val FetchLimit : String = "100"
    const val IO_ERROR = "An error occurred and we are unable to process request at the moment"
    const val CONNECTION_ERROR = "Unable to process request, please check your network connection and try again"
    const val SOCKET_TIMEOUT_ERROR = "Connection timed out, please check your network connection and try again"
    const val CLIENT_ERROR = "An error occurred while processing your request, please try again"
    const val SERVER_ERROR = "Unable to reach the server at the moment, please try again later"
}