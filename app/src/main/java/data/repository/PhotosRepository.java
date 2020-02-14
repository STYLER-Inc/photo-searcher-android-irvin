package data.repository;

import java.util.List;

import javax.inject.Inject;

import data.endpoint.Service;
import io.reactivex.Single;
import jp.styler.challenge.BuildConfig;
import model.Photo;
import model.PhotosResponse;

public class PhotosRepository {

    private final Service service;

    @Inject
    public PhotosRepository(Service service) {
        this.service = service;
    }

    public Single<List<Photo>> getPhotos(String typedText) {

        return service.searchPhotos(
                "flickr.photos.search",
                BuildConfig.FLICKR_KEY,
                typedText,
                "json",
                1
        ).map(this::mapPhotoSearch);
    }

    private List<Photo> mapPhotoSearch(PhotosResponse searchPhotoResponse) {
        return searchPhotoResponse.getPhotos().getPhoto();
    }
}
