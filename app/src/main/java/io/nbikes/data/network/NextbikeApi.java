package io.nbikes.data.network;

import io.nbikes.data.network.response.GetListResponse;
import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface NextbikeApi {
    @GET("maps/nextbike-official.json?countries=pl")
    Observable<GetListResponse> getList(
            @Query("south_west") String southWest,
            @Query("north_east") String northEast
    );
}
