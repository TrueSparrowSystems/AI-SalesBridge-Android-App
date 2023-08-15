package com.truesparrowsystemspvtltd.salessparrow.api

import com.truesparrowsystemspvtltd.salessparrow.models.SalesForceConnectRequest
import com.truesparrowsystemspvtltd.salessparrow.models.RedirectUrl
import com.truesparrowsystemspvtltd.salessparrow.models.SaveNote
import com.truesparrowsystemspvtltd.salessparrow.models.CurrentUserResponse
import com.truesparrowsystemspvtltd.salessparrow.models.AccountListResponse
import com.truesparrowsystemspvtltd.salessparrow.models.AccountNotesResponse
import com.truesparrowsystemspvtltd.salessparrow.models.NotesDetailResponse
import com.truesparrowsystemspvtltd.salessparrow.models.SaveNoteRequest
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET("./v1/accounts")
    suspend fun getAccounts(@Query("q") query: String): Response<AccountListResponse>

    @GET("./v1/auth/salesforce/redirect-url")
    @Headers("$MOCK_RESPONSE_HEADER: RedirectUri.json")
    suspend fun getSalesForceRedirectUrl(@Query("redirect_uri") redirectUri: String): Response<RedirectUrl>

    @POST("./v1/auth/salesforce/connect")
    @Headers("Content-Type: application/json")
    suspend fun salesForceConnect(
        @Body request: SalesForceConnectRequest
    ): Response<CurrentUserResponse>

    @GET("./v1/users/current")
    @Headers("$MOCK_RESPONSE_HEADER: CurrentUserResponse.json")
    suspend fun getCurrentUser(): Response<CurrentUserResponse>

    @POST("./v1/accounts/{account_id}/notes")
    @Headers("$MOCK_RESPONSE_HEADER: SaveNoteResponse.json")
    suspend fun saveNote(
        @Query("account_id") accountId: String,
        @Body request: SaveNoteRequest
    ): Response<SaveNote>

    @GET("v1/accounts/{account_id}/notes")
    @Headers("$MOCK_RESPONSE_HEADER: NotesResponse.json")
    suspend fun getAccountNotes(
       @Path(value = "account_id", encoded = true) accountId: String
    ): Response<AccountNotesResponse>

    @GET("v1/accounts/{account_id}/notes/{note_id}")
    @Headers("$MOCK_RESPONSE_HEADER: NoteDetailsResponse.json")
    suspend fun getNoteDetails(
        @Path(value = "account_id") accountId: String,
        @Path(value = "note_id") noteId: String
    ): Response<NotesDetailResponse>


    @Headers("$MOCK_RESPONSE_HEADER: LogoutResponse.json")
    @POST("./v1/auth/logout")
    suspend fun logout(): Response<Unit>

    @POST("./v1/auth/disconnect")
    suspend fun disconnectSalesForce(): Response<Unit>

}