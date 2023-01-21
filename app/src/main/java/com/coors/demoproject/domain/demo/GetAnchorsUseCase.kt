package com.coors.demoproject.domain.demo

import com.coors.commoncore.domain.FlowUseCase
import com.coors.commoncore.model.AnchorModel
import com.coors.commoncore.model.AnchorWrapper
import com.coors.commoncore.result.Result
import com.coors.commoncore.result.map
import com.coors.demoproject.data.demo.DemoRepository
import com.coors.demoproject.data.home.HomeMenu
import com.coors.demoproject.data.home.HomeRepository
import com.coors.demoproject.di.qualifier.IoDispatcher
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

@OptIn(ExperimentalCoroutinesApi::class)
class GetAnchorsUseCase @Inject constructor(
    private val repository: DemoRepository,
    @IoDispatcher coroutineDispatcher: CoroutineDispatcher
) : FlowUseCase<Unit, List<AnchorModel>>(coroutineDispatcher) {

    override fun execute(parameters: Unit): Flow<Result<List<AnchorModel>>> = flow {
//        emit(Result.Success(getMockAnchors()?.data ?: emptyList()))
        val result = repository.getAnchors()
        if (result is Result.Success) {
            emit(Result.Success(result.data.data))
        } else {
            emit(Result.Error(Exception("get anchors failed")))
        }
    }

    private fun getMockAnchors(): AnchorWrapper? {
        val moshi: Moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()
        val jsonAdapter: JsonAdapter<AnchorWrapper> = moshi.adapter(AnchorWrapper::class.java)
        return jsonAdapter.fromJson(json)
    }

    val json = """
        {
        "data": [
        {
        "backgroundImage": "",
        "guestTeamName": "蒙彼利埃",
        "hostId": 105,
        "hostName": "十兩金",
        "hostPhotoId": "6353c850556ea91c938bb90f.jpg",
        "language": "",
        "league": "法国甲级联赛",
        "liveRoomId": 848,
        "liveStatus": 1,
        "liveStreamId": "bty122502",
        "masterTeamName": "洛里昂",
        "matchId": "723215",
        "matchType": "足球",
        "multiLanguageGuestTeamName": {
        "turkish": "",
        "en": "",
        "vietnam": "",
        "thai": "",
        "zh_CN": "蒙彼利埃"
        },
        "multiLanguageMasterTeamName": {
        "turkish": "",
        "en": "",
        "vietnam": "",
        "thai": "",
        "zh_CN": "洛里昂"
        },
        "multiLanguageMatchLeague": {
        "turkish": "",
        "en": "",
        "vietnam": "",
        "thai": "",
        "zh_CN": "法国甲级联赛"
        },
        "popularity": 9,
        "redEnvelopeTotalAmount": 0,
        "sort": 1,
        "startDate": 1672329600000,
        "videoSource": {
        "flv": "https://hdl.byyw087635w11.com/live/bty122502_gaoqing.flv",
        "m3u8": "https://hls.byyw087635w11.com/live/bty122502_gaoqing.m3u8",
        "rtmp": "https://hdl.byyw087635w11.com/live/bty122502_gaoqing"
        },
        "voidSourceUrl": "",
        "watcherCount": 9,
        "homeName": "洛里昂",
        "awayName": "蒙彼利埃",
        "half": "",
        "gameTime": "",
        "homeScore": "",
        "awayScore": "",
        "homeLogo": "https://m-www.ballbet7.com/api/forehead/system/file/get/61c3db42ba1ee2380d70cb7d.png",
        "awayLogo": "https://m-www.ballbet7.com/api/forehead/system/file/get/61c3db42ba1ee2380d70cb7d.png"
        },
        {
        "backgroundImage": "",
        "guestTeamName": "蒙彼利埃",
        "hostId": 104,
        "hostName": "九兩金",
        "hostPhotoId": "6353c840556ea91c938bb8fd.jpg",
        "language": "",
        "league": "法国甲级联赛",
        "liveRoomId": 847,
        "liveStatus": 1,
        "liveStreamId": "bty122501",
        "masterTeamName": "洛里昂",
        "matchId": "723215",
        "matchType": "足球",
        "multiLanguageGuestTeamName": {
        "turkish": "",
        "en": "",
        "vietnam": "",
        "thai": "",
        "zh_CN": "蒙彼利埃"
        },
        "multiLanguageMasterTeamName": {
        "turkish": "",
        "en": "",
        "vietnam": "",
        "thai": "",
        "zh_CN": "洛里昂"
        },
        "multiLanguageMatchLeague": {
        "turkish": "",
        "en": "",
        "vietnam": "",
        "thai": "",
        "zh_CN": "法国甲级联赛"
        },
        "popularity": 7,
        "redEnvelopeTotalAmount": 1000,
        "sort": 1,
        "startDate": 1672329600000,
        "videoSource": {
        "flv": "https://hdl.byyw087635w11.com/live/bty122501_gaoqing.flv",
        "m3u8": "https://hls.byyw087635w11.com/live/bty122501_gaoqing.m3u8",
        "rtmp": "https://hdl.byyw087635w11.com/live/bty122501_gaoqing"
        },
        "voidSourceUrl": "",
        "watcherCount": 8,
        "homeName": "洛里昂",
        "awayName": "蒙彼利埃",
        "half": "",
        "gameTime": "",
        "homeScore": "",
        "awayScore": "",
        "homeLogo": "https://m-www.ballbet7.com/api/forehead/system/file/get/61c3db42ba1ee2380d70cb7d.png",
        "awayLogo": "https://m-www.ballbet7.com/api/forehead/system/file/get/61c3db42ba1ee2380d70cb7d.png"
        },
        {
        "backgroundImage": "",
        "guestTeamName": "欧塞尔",
        "hostId": 113,
        "hostName": "牛肉爐",
        "hostPhotoId": "637d9ea6955cb7648ac4f0d5.jpg",
        "language": "",
        "league": "法国甲级联赛",
        "liveRoomId": 850,
        "liveStatus": 1,
        "liveStreamId": "bty122504",
        "masterTeamName": "南特",
        "matchId": "736639",
        "matchType": "足球",
        "multiLanguageGuestTeamName": {
        "turkish": "",
        "en": "",
        "vietnam": "",
        "thai": "",
        "zh_CN": "欧塞尔"
        },
        "multiLanguageMasterTeamName": {
        "turkish": "",
        "en": "",
        "vietnam": "",
        "thai": "",
        "zh_CN": "南特"
        },
        "multiLanguageMatchLeague": {
        "turkish": "",
        "en": "",
        "vietnam": "",
        "thai": "",
        "zh_CN": "法国甲级联赛"
        },
        "popularity": 2,
        "redEnvelopeTotalAmount": 50,
        "sort": 1,
        "startDate": 1672581600000,
        "videoSource": {
        "flv": "https://hdl.byyw087635w11.com/live/bty122504_gaoqing.flv",
        "m3u8": "https://hls.byyw087635w11.com/live/bty122504_gaoqing.m3u8",
        "rtmp": "https://hdl.byyw087635w11.com/live/bty122504_gaoqing"
        },
        "voidSourceUrl": "",
        "watcherCount": 0,
        "homeName": "南特",
        "awayName": "欧塞尔",
        "half": "",
        "gameTime": "",
        "homeScore": "",
        "awayScore": "",
        "homeLogo": "https://m-www.ballbet7.com/api/forehead/system/file/get/61c3db42ba1ee2380d70cb7d.png",
        "awayLogo": "https://m-www.ballbet7.com/api/forehead/system/file/get/61c3db42ba1ee2380d70cb7d.png"
        },
        {
        "backgroundImage": "",
        "guestTeamName": "欧塞尔",
        "hostId": 112,
        "hostName": "羊肉爐",
        "hostPhotoId": "637d908e955cb7648ac4e608.png",
        "language": "",
        "league": "法国甲级联赛",
        "liveRoomId": 849,
        "liveStatus": 1,
        "liveStreamId": "bty122503",
        "masterTeamName": "南特",
        "matchId": "736639",
        "matchType": "足球",
        "multiLanguageGuestTeamName": {
        "turkish": "",
        "en": "",
        "vietnam": "",
        "thai": "",
        "zh_CN": "欧塞尔"
        },
        "multiLanguageMasterTeamName": {
        "turkish": "",
        "en": "",
        "vietnam": "",
        "thai": "",
        "zh_CN": "南特"
        },
        "multiLanguageMatchLeague": {
        "turkish": "",
        "en": "",
        "vietnam": "",
        "thai": "",
        "zh_CN": "法国甲级联赛"
        },
        "popularity": 9,
        "redEnvelopeTotalAmount": 88,
        "sort": 1,
        "startDate": 1672581600000,
        "videoSource": {
        "flv": "https://hdl.byyw087635w11.com/live/bty122503_gaoqing.flv",
        "m3u8": "https://hls.byyw087635w11.com/live/bty122503_gaoqing.m3u8",
        "rtmp": "https://hdl.byyw087635w11.com/live/bty122503_gaoqing"
        },
        "voidSourceUrl": "",
        "watcherCount": 4,
        "homeName": "南特",
        "awayName": "欧塞尔",
        "half": "",
        "gameTime": "",
        "homeScore": "",
        "awayScore": "",
        "homeLogo": "https://m-www.ballbet7.com/api/forehead/system/file/get/61c3db42ba1ee2380d70cb7d.png",
        "awayLogo": "https://m-www.ballbet7.com/api/forehead/system/file/get/61c3db42ba1ee2380d70cb7d.png"
        },
        {
        "backgroundImage": "",
        "guestTeamName": "布里斯班狮吼",
        "hostId": 113,
        "hostName": "牛肉爐",
        "hostPhotoId": "637d9ea6955cb7648ac4f0d5.jpg",
        "language": "",
        "league": "澳大利亚超级联赛",
        "liveRoomId": 853,
        "liveStatus": 1,
        "liveStreamId": "bty010601",
        "masterTeamName": "墨尔本胜利",
        "matchId": "705236",
        "matchType": "足球",
        "multiLanguageGuestTeamName": {
        "turkish": "",
        "en": "",
        "vietnam": "",
        "thai": "",
        "zh_CN": "布里斯班狮吼"
        },
        "multiLanguageMasterTeamName": {
        "turkish": "",
        "en": "",
        "vietnam": "",
        "thai": "",
        "zh_CN": "墨尔本胜利"
        },
        "multiLanguageMatchLeague": {
        "turkish": "",
        "en": "",
        "vietnam": "",
        "thai": "",
        "zh_CN": "澳大利亚超级联赛"
        },
        "popularity": 0,
        "redEnvelopeTotalAmount": 0,
        "sort": 1,
        "startDate": 1672994700000,
        "videoSource": {
        "flv": "https://hdl.byyw087635w11.com/live/bty010601_gaoqing.flv",
        "m3u8": "https://hls.byyw087635w11.com/live/bty010601_gaoqing.m3u8",
        "rtmp": "https://hdl.byyw087635w11.com/live/bty010601_gaoqing"
        },
        "voidSourceUrl": "",
        "watcherCount": 0,
        "homeName": "墨尔本胜利",
        "awayName": "布里斯班狮吼",
        "half": "",
        "gameTime": "",
        "homeScore": "",
        "awayScore": "",
        "homeLogo": "https://m-www.ballbet7.com/api/forehead/system/file/get/61c3db42ba1ee2380d70cb7d.png",
        "awayLogo": "https://m-www.ballbet7.com/api/forehead/system/file/get/61c3db42ba1ee2380d70cb7d.png"
        },
        {
        "backgroundImage": "",
        "guestTeamName": "老虎大学",
        "hostId": 123,
        "hostName": "甜蜜教主",
        "hostPhotoId": "63c21ce043b87d63452ec455.jpeg",
        "language": "",
        "league": "墨西哥超级联赛",
        "liveRoomId": 863,
        "liveStatus": 1,
        "liveStreamId": "Cyndi007",
        "masterTeamName": "蒂华纳",
        "matchId": "745629",
        "matchType": "足球",
        "multiLanguageGuestTeamName": {
        "turkish": "",
        "en": "",
        "vietnam": "",
        "thai": "",
        "zh_CN": ""
        },
        "multiLanguageMasterTeamName": {
        "turkish": "",
        "en": "",
        "vietnam": "",
        "thai": "",
        "zh_CN": ""
        },
        "multiLanguageMatchLeague": {
        "turkish": "",
        "en": "",
        "vietnam": "",
        "thai": "",
        "zh_CN": ""
        },
        "popularity": 0,
        "redEnvelopeTotalAmount": 0,
        "sort": 1,
        "startDate": 1674270600000,
        "videoSource": {
        "flv": "https://hdl.byyw087635w11.com/live/Cyndi007_gaoqing.flv",
        "m3u8": "https://hls.byyw087635w11.com/live/Cyndi007_gaoqing.m3u8",
        "rtmp": "https://hdl.byyw087635w11.com/live/Cyndi007_gaoqing"
        },
        "voidSourceUrl": "",
        "watcherCount": 2,
        "homeName": "蒂华纳",
        "awayName": "老虎大学",
        "half": "未开赛",
        "homeLogo": "https://newsports-static-image.s3.ap-northeast-1.amazonaws.com/data/f6f6157fb6019339505ed52aaa778bc6.png",
        "awayLogo": "https://newsports-static-image.s3.ap-northeast-1.amazonaws.com/data/fcc82379eba3fe32df2b8dea3b54d4e5.png",
        "gamePart": "未开赛"
        }
        ]
        }

    """.trimIndent()

}