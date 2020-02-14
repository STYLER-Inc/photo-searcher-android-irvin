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

public class GridViewModel extends ViewModel {

    private final PhotosRepository photosRepository;

    private CompositeDisposable compositeDisposable;
    private MutableLiveData photoUrls = new MutableLiveData();
    private MutableLiveData repoLoadError = new MutableLiveData<>();
    private MutableLiveData loading = new MutableLiveData<>();

    LiveData<List<String>> getPhotoUrls() {
        return photoUrls;
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
                                new DisposableSingleObserver<List<String>>() {

                                    @Override
                                    public void onSuccess(List<String> value) {
                                        photoUrls.setValue(value);
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
