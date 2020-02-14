package data.repository;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import data.endpoint.Constants;
import data.endpoint.Service;
import io.reactivex.Single;
import jp.styler.challenge.BuildConfig;
import model.SearchPhotoResponse;

public class PhotosRepository {

    private final Service service;

    @Inject
    public PhotosRepository(Service service) {
        this.service = service;
    }

    public Single<List<String>> getPhotos(String typedText) {

        return service.searchPhotos(
                "flickr.photos.search",
                BuildConfig.FLICKR_KEY,
                typedText,
                "json",
                1
        ).map(this::mapPhotoSearch);
    }

    private List<String> mapPhotoSearch(SearchPhotoResponse searchPhotoResponse) {

        List<String> photoUrls = new ArrayList();

        for (SearchPhotoResponse.Photo photo : searchPhotoResponse.photo) {
            photoUrls.add(String.format(Constants.IMAGE_CONSTRUCT_THUMB, photo.farm, photo.server, photo.id, photo.secret));
        }
        return photoUrls;
    }
}
