package view.detail;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import javax.inject.Inject;

import data.repository.PhotosRepository;

public class ImageDetailViewModel extends ViewModel {

    private final PhotosRepository photosRepository;

    private final MutableLiveData<String> selectedImageUrl = new MutableLiveData<>();

    public LiveData<String> getSelectedImageUrl() {
        return selectedImageUrl;
    }

    @Inject
    public ImageDetailViewModel(PhotosRepository repository) {
        photosRepository = repository;
    }

    public void setImageSelected(String imageSelected) {
        selectedImageUrl.setValue(imageSelected);
    }
}
