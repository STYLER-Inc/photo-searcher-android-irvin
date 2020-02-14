package data.endpoint;

import io.reactivex.Single;
import model.PhotosResponse;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface Service {

    @GET(Constants.IMAGE_SEARCH)
    Single<PhotosResponse> searchPhotos(
            @Query(Constants.QUERY_METHOD) String method,
            @Query(Constants.QUERY_API_KEY) String key,
            @Query(Constants.QUERY_TEXT) String text,
            @Query(Constants.QUERY_FORMAT) String format,
            @Query(Constants.QUERY_CALLBAK) Integer callback);

}
