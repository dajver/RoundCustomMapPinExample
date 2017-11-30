package project.dajver.com.roundpinwithavatarexample.view.repo;

import io.reactivex.Observable;
import project.dajver.com.roundpinwithavatarexample.view.model.PinsModel;
import project.dajver.com.roundpinwithavatarexample.view.repo.model.ResponseModel;

/**
 * Created by gleb on 11/30/17.
 */

public interface IRepository {
    Observable<ResponseModel> getImages(String url, PinsModel model);
}
