package com.example.cleanarchitecture.data.remote.exception

import com.example.cleanarchitecture.data.remote.response.ServerErrorResponse
import okhttp3.ResponseBody
import retrofit2.Converter
import retrofit2.Response
import retrofit2.Retrofit
import timber.log.Timber
import java.io.IOException

class RetrofitException(
    private val _message: String?,
    private val _url: String?,
    private val _response: Response<*>?,
    private val _kind: Kind,
    private val _exception: Throwable?,
    private val _retrofit: Retrofit?
) : RuntimeException(_message, _exception) {

    private var _errorData: ServerErrorResponse? = null

    companion object {
        fun httpError(url: String, response: Response<*>, retrofit: Retrofit): RetrofitException {
            val message = response.code().toString() + " " + response.message()
            return RetrofitException(message, url, response, Kind.HTTP, null, retrofit)
        }

        fun httpErrorWithObject(
            url: String,
            response: Response<*>,
            retrofit: Retrofit
        ): RetrofitException {
            val message = response.code().toString() + " " + response.message()
            val error =
                RetrofitException(message, url, response, Kind.HTTP_422_WITH_DATA, null, retrofit)
            error.deserializeServerError()
            return error
        }

        fun networkError(exception: IOException): RetrofitException {
            return RetrofitException(exception.message, null, null, Kind.NETWORK, exception, null)
        }

        fun unexpectedError(exception: Throwable): RetrofitException {
            return RetrofitException(
                exception.message,
                null,
                null,
                Kind.UNEXPECTED,
                exception,
                null
            )
        }
    }

    /** The request URL which produced the error. */
    fun getUrl() = _url

    /** Response object containing status code, headers, body, etc. */
    fun getResponse() = _response

    /** The event kind which triggered this error. */
    fun getKind() = _kind

    /** The Retrofit this request was executed on */
    fun getRetrofit() = _retrofit

    /** The data returned from the server in the response body*/
    fun getErrorData(): ServerErrorResponse? = _errorData

    private fun deserializeServerError() {
        if (_response?.errorBody() != null) {
            try {
                _errorData = getErrorBodyAs(ServerErrorResponse::class.java)
            } catch (e: IOException) {
                Timber.e("Server error deserialization $e")
            }
        }
    }

    /**
     * HTTP response body converted to specified `type`. `null` if there is no
     * response.
     * @throws IOException if unable to convert the body to the specified `type`.
     */
    @Throws(IOException::class)
    fun <T> getErrorBodyAs(type: Class<T>): T? {
        if (_response?.errorBody() == null || _retrofit == null) {
            return null
        }
        val converter: Converter<ResponseBody, T> =
            _retrofit.responseBodyConverter(type, arrayOfNulls<Annotation>(0))
        return _response.errorBody()?.let { converter.convert(it) }
    }

    enum class Kind {
        /** An [IOException] occurred while communicating to the server.  */
        NETWORK,
        /** A non-200 HTTP status code was received from the server.  */
        HTTP,
        HTTP_422_WITH_DATA,
        /**
         * An internal error occurred while attempting to execute a request. It is best practice to
         * re-throw this exception so your application crashes.
         */
        UNEXPECTED
    }
}