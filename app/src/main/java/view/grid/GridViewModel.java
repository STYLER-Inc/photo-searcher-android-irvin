package view.grid;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

import javax.inject.Inject;

import data.repository.PhotosRepository;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;
import model.Photo;

public class GridViewModel extends ViewModel {

    private final PhotosRepository photosRepository;

    private CompositeDisposable compositeDisposable;
    private MutableLiveData photos = new MutableLiveData();
    private MutableLiveData repoLoadError = new MutableLiveData<>();
    private MutableLiveData loading = new MutableLiveData<>();

    LiveData<List<Photo>> getPhotoUrls() {
        return photos;
    }

    LiveData<Boolean> getError() {
        return repoLoadError;
    }

    LiveData<Boolean> getLoading() {
        return loading;
    }

    @Inject
    public GridViewModel(PhotosRepository repository) {
        photosRepository = repository;
        compositeDisposable = new CompositeDisposable();
    }

    public void searchPhotos(String typedText) {

        compositeDisposable.add(
                photosRepository.getPhotos(typedText)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .doOnSubscribe(disposable -> loading.setValue(false))
                        .subscribeWith(
                                new DisposableSingleObserver<List<Photo>>() {

                                    @Override
                                    public void onSuccess(List<Photo> value) {
                                        photos.setValue(value);
                                        repoLoadError.setValue(false);
                                        loading.setValue(false);
                                    }

                                    @Override
                                    public void onError(Throwable e) {
                                        repoLoadError.setValue(true);
                                        loading.setValue(false);
                                    }
                                }
                        )
        );
    }
}
