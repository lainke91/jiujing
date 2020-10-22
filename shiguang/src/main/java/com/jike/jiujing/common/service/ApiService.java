package com.jike.jiujing.common.service;

import com.jike.jiujing.common.entry.CaptainUser;
import com.jike.jiujing.common.entry.ResultData;
import com.jike.jiujing.common.entry.SignInActivityList;
import com.jike.jiujing.common.param.LoginParam;
import com.jike.jiujing.common.param.ScoreParam;
import com.jike.jiujing.common.param.SignInParam;
import com.jike.jiujing.common.param.TaskParam;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface ApiService {
    @POST("mychevy/rest/api/public/teamBuliding/login")
    Observable<ResultData<CaptainUser>> login(@Body LoginParam param);

    @POST("mychevy/rest/api/public/teamBuliding/changeName")
    Observable<ResultData> changeTeamName(@Body CaptainUser param);

    @POST("mychevy/rest/api/public/teamBuliding/taskFinish")
    Observable<ResultData<CaptainUser>> taskFinish(@Body TaskParam param);

    @POST("mychevy/rest/api/public/teamBuliding/getActivity")
    Observable<ResultData<SignInActivityList>> getActivity(@Body TaskParam param);

    @POST("mychevy/rest/api/public/teamBuliding/joinActivity")
    Observable<ResultData<CaptainUser>> joinActivity(@Body SignInParam param);

    @POST("mychevy/rest/api/public/teamBuliding/scoreActivity")
    Observable<ResultData> scoreActivity(@Body ScoreParam param);

}
