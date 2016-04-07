package todo.api;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.text.TextUtils;
import android.util.Log;

import com.android.volley.*;
import com.android.volley.toolbox.*;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

import todo.api.dto.*;
import todo.api.enumeration.*;

import java.io.UnsupportedEncodingException;
import java.util.*;
import java.util.concurrent.ConcurrentLinkedQueue;


public class ApiService {

    public static final String USERNAME = "username";
    public static final String ACCESS_TOKEN = "accessToken";
    public static final String ACCESS_TOKEN_EXPIRES_AT = "accessTokenExpires";
    public static final String REFRESH_TOKEN = "refreshToken";
    public static final String REFRESH_TOKEN_EXPIRES_AT = "refreshTokenExpires";
    public static final String CREDENTIALS = "credentials";
    private static final int REFRESH_THRESHOLD = 20 * 60 * 1000;

    private static ApiService INSTANCE;

    private final String TAG = "";
    private final String AUTHORIZATION = "Authorization";
    private final String serverHost;
    private final Context context;
    private RequestQueue mRequestQueue;
    private Queue<Request> supportQueue = new ConcurrentLinkedQueue<Request>();
    private final Gson gson = new Gson();

    private String accessToken = null;
    private String refreshToken;

    private volatile long accessTokenExpiresAt = 0;
    private volatile boolean currentlyRefreshing = false;

    public static synchronized ApiService getInstance(Context appContext) {
        if (INSTANCE == null) {
            INSTANCE = new ApiService(appContext);
        }
        return INSTANCE;
    }

    private ApiService(Context context) {
        this.serverHost = ""; // TODO WRITE URL HERE
        this.context = context;
        loadTokens();
    }

    // TODO Replace this with your custom code by calling: setRefreshErrorListener(Response.ErrorListener refreshErrorListener)
    private Response.ErrorListener refreshErrorListener = new Response.ErrorListener() {
        @Override
        public void onErrorResponse(VolleyError error) {
            Log.w(TAG, "Failed to refresh access token. Replace this default error listener using setRefreshErrorListener(Response.ErrorListener refreshErrorListener)!");
        }
    };

    public void setRefreshErrorListener(Response.ErrorListener refreshErrorListener) {
        this.refreshErrorListener = refreshErrorListener;
    }

    public void findTodo(FindTodoRequest requestDto, Response.Listener<FindTodoResponse> listener, Response.ErrorListener errorListener, String tag) {
        String url = serverHost + "/todos" + "?drop=" + requestDto.getDrop() + "&take=" + requestDto.getTake();
        Map<String, String> headers = new HashMap<String, String>();
        headers.put(AUTHORIZATION, accessToken);

        get(url, headers, null, FindTodoResponse.class, listener, errorListener, tag);
    }

    public void readTodo(ReadTodoRequest requestDto, Response.Listener<ReadTodoResponse> listener, Response.ErrorListener errorListener, String tag) {
        String url = serverHost + "/todo/" + requestDto.getId() + "";
        Map<String, String> headers = new HashMap<String, String>();
        headers.put(AUTHORIZATION, accessToken);

        get(url, headers, null, ReadTodoResponse.class, listener, errorListener, tag);
    }

    public void createTodo(CreateTodoRequest requestDto, Response.Listener<CreateTodoResponse> listener, Response.ErrorListener errorListener, String tag) {
        String url = serverHost + "/todo";
        Map<String, String> headers = new HashMap<String, String>();
        headers.put(AUTHORIZATION, accessToken);

        post(url, headers, requestDto, CreateTodoResponse.class, listener, errorListener, tag);
    }

    public void updateTodo(UpdateTodoRequest requestDto, Response.Listener<UpdateTodoResponse> listener, Response.ErrorListener errorListener, String tag) {
        String url = serverHost + "/todo/" + requestDto.getId() + "";
        Map<String, String> headers = new HashMap<String, String>();
        headers.put(AUTHORIZATION, accessToken);

        RestUpdateTodoRequest bodyDto = new RestUpdateTodoRequest(requestDto.getUserId(), requestDto.getTask(), requestDto.getDate());

        put(url, headers, bodyDto, UpdateTodoResponse.class, listener, errorListener, tag);
    }

    public void deleteTodo(DeleteTodoRequest requestDto, Response.Listener<Void> listener, Response.ErrorListener errorListener, String tag) {
        String url = serverHost + "/todo/" + requestDto.getId() + "";
        Map<String, String> headers = new HashMap<String, String>();
        headers.put(AUTHORIZATION, accessToken);

        delete(url, headers, null, Void.class, listener, errorListener, tag);
    }

    public void allTodos(Response.Listener<AllTodosResponse[]> listener, Response.ErrorListener errorListener, String tag) {
        String url = serverHost + "/allTodos";
        Map<String, String> headers = new HashMap<String, String>();
        headers.put(AUTHORIZATION, accessToken);

        get(url, headers, null, AllTodosResponse[].class, listener, errorListener, tag);
    }

    public void users(Response.Listener<UsersResponse[]> listener, Response.ErrorListener errorListener, String tag) {
        String url = serverHost + "/users";
        Map<String, String> headers = new HashMap<String, String>();
        headers.put(AUTHORIZATION, accessToken);

        get(url, headers, null, UsersResponse[].class, listener, errorListener, tag);
    }

    public void userTodos(UserTodosRequest requestDto, Response.Listener<UserTodosResponse> listener, Response.ErrorListener errorListener, String tag) {
        String url = serverHost + "/userTodos" + "?userId=" + requestDto.getUserId() + "&drop=" + requestDto.getDrop() + "&take=" + requestDto.getTake();
        Map<String, String> headers = new HashMap<String, String>();
        headers.put(AUTHORIZATION, accessToken);

        get(url, headers, null, UserTodosResponse.class, listener, errorListener, tag);
    }

    public void authenticate(AuthenticateRequest requestDto, Response.Listener<AuthenticateResponse> listener, Response.ErrorListener errorListener, String tag) {
        String url = serverHost + "/authenticate";

        post(url, null, requestDto, AuthenticateResponse.class, listener, errorListener, tag);
    }

    public void signOut(Response.Listener<Void> listener, Response.ErrorListener errorListener, String tag) {
        String url = serverHost + "/signOut";
        Map<String, String> headers = new HashMap<String, String>();
        headers.put(AUTHORIZATION, accessToken);

        post(url, headers, null, Void.class, listener, errorListener, tag);
    }

    public void refreshToken(RefreshTokenRequest requestDto, Response.Listener<RefreshTokenResponse> listener, Response.ErrorListener errorListener, String tag) {
        String url = serverHost + "/refreshToken";

        post(url, null, requestDto, RefreshTokenResponse.class, listener, errorListener, tag);
    }

    public void signUp(SignUpRequest requestDto, Response.Listener<SignUpResponse> listener, Response.ErrorListener errorListener, String tag) {
        String url = serverHost + "/signUp";

        post(url, null, requestDto, SignUpResponse.class, listener, errorListener, tag);
    }

    public void signIn(SignInRequest requestDto, Response.Listener<SignInResponse> listener, Response.ErrorListener errorListener, String tag) {
        String url = serverHost + "/signIn";

        post(url, null, requestDto, SignInResponse.class, listener, errorListener, tag);
    }

    public void resetPassword(ResetPasswordRequest requestDto, Response.Listener<Void> listener, Response.ErrorListener errorListener, String tag) {
        String url = serverHost + "/resetPassword";

        post(url, null, requestDto, Void.class, listener, errorListener, tag);
    }

    public void verifyEmail(VerifyEmailRequest requestDto, Response.Listener<VerifyEmailResponse> listener, Response.ErrorListener errorListener, String tag) {
        String url = serverHost + "/verifyEmail";

        post(url, null, requestDto, VerifyEmailResponse.class, listener, errorListener, tag);
    }

    public void changePassword(ChangePasswordRequest requestDto, Response.Listener<ChangePasswordResponse> listener, Response.ErrorListener errorListener, String tag) {
        String url = serverHost + "/changePassword";
        Map<String, String> headers = new HashMap<String, String>();
        headers.put(AUTHORIZATION, accessToken);

        post(url, headers, requestDto, ChangePasswordResponse.class, listener, errorListener, tag);
    }

    public void cancelPendingRequests(String tag) {
        final Set<Request> matchingRequests = new HashSet<Request>();
        for (Request request : supportQueue) {
            if (request.getTag().equals(tag)) {
                matchingRequests.add(request);
            }
        }
        supportQueue.removeAll(matchingRequests);
        getRequestQueue().cancelAll(tag);
    }

    protected <T> void get(String url, Map<String, String> headers, Object request, Class<T> responseClass, Response.Listener<T> listener, Response.ErrorListener errorListener, String tag) {
        addToRequestQueue(new GsonRequest<T>(Request.Method.GET, url, headers, request, responseClass, listener, errorListener), tag);
    }

    protected <T> void post(String url, Map<String, String> headers, Object request, Class<T> responseClass, Response.Listener<T> listener, Response.ErrorListener errorListener, String tag) {
        addToRequestQueue(new GsonRequest<T>(Request.Method.POST, url, headers, request, responseClass, listener, errorListener), tag);
    }

    protected <T> void put(String url, Map<String, String> headers, Object request, Class<T> responseClass, Response.Listener<T> listener, Response.ErrorListener errorListener, String tag) {
        addToRequestQueue(new GsonRequest<T>(Request.Method.PUT, url, headers, request, responseClass, listener, errorListener), tag);
    }

    protected <T> void delete(String url, Map<String, String> headers, Object request, Class<T> responseClass, Response.Listener<T> listener, Response.ErrorListener errorListener, String tag) {
        addToRequestQueue(new GsonRequest<T>(Request.Method.DELETE, url, headers, request, responseClass, listener, errorListener), tag);
    }

    private RequestQueue getRequestQueue() {
        // lazy initialize the request queue, the queue instance will be
        // created when it is accessed for the first time
        if (mRequestQueue == null) {
            mRequestQueue = Volley.newRequestQueue(context.getApplicationContext());
        }

        return mRequestQueue;
    }

    public void loadTokens() {
        SharedPreferences preferences = context.getSharedPreferences(CREDENTIALS, 0);
        accessToken = preferences.getString(ACCESS_TOKEN, "");
        accessTokenExpiresAt = Long.parseLong(preferences.getString(ACCESS_TOKEN_EXPIRES_AT, "0"));
        refreshToken = preferences.getString(REFRESH_TOKEN, "");
    }

    private void clearTokens() {
        SharedPreferences preferences = context.getSharedPreferences(CREDENTIALS, 0);
        SharedPreferences.Editor editor = preferences.edit();
        editor.remove(USERNAME);
        editor.remove(ACCESS_TOKEN);
        editor.remove(REFRESH_TOKEN);
        editor.remove(ACCESS_TOKEN_EXPIRES_AT);
        editor.remove(REFRESH_TOKEN_EXPIRES_AT);
        editor.commit();
    }

    private class RefreshTokenRequest extends Request<AuthenticationResponseDto> {

        protected static final String PROTOCOL_CHARSET = "utf-8";

        private static final String PROTOCOL_CONTENT_TYPE = String.format("application/json; charset=%s", PROTOCOL_CHARSET);

        public RefreshTokenRequest() {
            super(Method.POST, serverHost + "/refreshToken", refreshErrorListener);
        }

        public byte[] getBody() {
            String json = gson.toJson(new RefreshTokenDto(refreshToken));
            Log.i(TAG, "Request Body:" + json);
            byte[] bytes = new byte[0];
            try {
                bytes = json.getBytes(PROTOCOL_CHARSET);
            } catch (UnsupportedEncodingException e) {
                Log.e(TAG, e.getMessage()); // This shouldn't happen UTF-8 IS supported.
            }
            return bytes;
        }

        @Override
        public String getBodyContentType() {
            return PROTOCOL_CONTENT_TYPE;
        }

        @Override
        protected Response<AuthenticationResponseDto> parseNetworkResponse(NetworkResponse response) {
            clearTokens();
            switch (response.statusCode) {
                case 200: {
                    try {
                        String json = new String(response.data, PROTOCOL_CHARSET);
                        return Response.success(gson.fromJson(json, AuthenticationResponseDto.class), HttpHeaderParser.parseCacheHeaders(response));
                    } catch (UnsupportedEncodingException e) {
                        return Response.error(new ParseError(e));
                    } catch (JsonSyntaxException e) {
                        return Response.error(new ParseError(e));
                    }
                }
                case 403:
                    return Response.error(new AuthFailureError(response));
                default:
                    return Response.error(new ServerError(response));
            }
        }

        @Override
        protected void deliverResponse(AuthenticationResponseDto response) {
            storeTokens(response);
            loadTokens();
            dispatchPendingRequestsWithNewAccessToken();
            setTokenRefreshInProgres(false);
        }

        private void dispatchPendingRequestsWithNewAccessToken() {
            while (!supportQueue.isEmpty()) {
                final Request request = supportQueue.remove();
                try {
                    request.getHeaders().put(AUTHORIZATION, accessToken);
                } catch (AuthFailureError authFailureError) {
                    Log.w(TAG, authFailureError.getMessage());
                }
                getRequestQueue().add(request);
            }
        }
    }

    private synchronized void setTokenRefreshInProgres(boolean isCurrentlyRefreshing) {
        currentlyRefreshing = isCurrentlyRefreshing;
    }

    private synchronized boolean isTokenRefreshIdle() {
        return !currentlyRefreshing;
    }

    private void storeTokens(AuthenticationResponseDto response) {
        SharedPreferences preferences = context.getSharedPreferences(CREDENTIALS, 0);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(USERNAME, response.getUsername());
        editor.putString(ACCESS_TOKEN, response.getAccessToken());
        editor.putString(REFRESH_TOKEN, response.getRefreshToken());
        editor.putString(ACCESS_TOKEN_EXPIRES_AT, response.getAccessTokenExpires().getTime() + "");
        editor.putString(REFRESH_TOKEN_EXPIRES_AT, response.getRefreshTokenExpires().getTime() + "");
        editor.commit();
    }

    protected <T> void addToRequestQueue(Request<T> req, String tag) {
        Log.i(TAG, "Access token expires at: " + accessTokenExpiresAt + " Now it is: " + System.currentTimeMillis());
        if (accessTokenExpiresAt - System.currentTimeMillis() > REFRESH_THRESHOLD || tag.equalsIgnoreCase("SignIn")) {
            req.setTag(TextUtils.isEmpty(tag) ? TAG : tag); // set the default tag if tag is empty
            VolleyLog.d("Adding request to queue: %s", req.getUrl());
            getRequestQueue().add(req);
        } else {
            supportQueue.add(req);
            if (isTokenRefreshIdle()) {
                RefreshTokenRequest refreshTokenRequest = new RefreshTokenRequest();
                getRequestQueue().add(refreshTokenRequest);
                setTokenRefreshInProgres(true);
            }
        }
    }

}
