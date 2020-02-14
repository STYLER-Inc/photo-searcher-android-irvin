package data.endpoint;

import io.reactivex.Single;
import model.SearchPhotoResponse;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface Service {

    // https://www.flickr.com/services/rest/?method=flickr.photos.search&api_key=2e5c23da39c1157ef7c00a6c3805b757&text=dogs&format=json&nojsoncallback=1

    @GET(Constants.IMAGE_SEARCH)
    Single<SearchPhotoResponse> searchPhotos(
            @Query(Constants.QUERY_METHOD) String method,
            @Query(Constants.QUERY_API_KEY) String key,
            @Query(Constants.QUERY_TEXT) String text,
            @Query(Constants.QUERY_FORMAT) String format,
            @Query(Constants.QUERY_CALLBAK) Integer callback);


    // https://farm{farm-id}.staticflickr.com/{server-id}/{id}_{secret}_[b].jpg  Big Image

    // https://farm{farm-id}.staticflickr.com/{server-id}/{id}_{secret}.jpg  // Detail Image
}
