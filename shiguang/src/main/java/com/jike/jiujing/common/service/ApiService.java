package com.jike.jiujing.common.service;

import com.jike.jiujing.common.entry.CaptainUser;
import com.jike.jiujing.common.entry.ResultData;
import com.jike.jiujing.common.param.LoginParam;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface ApiService {
    @POST("mychevy/rest/api/public/teamBuliding/login")
    Observable<ResultData<CaptainUser>> login(@Body LoginParam loginParam);
}
