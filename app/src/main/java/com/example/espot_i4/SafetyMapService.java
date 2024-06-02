package com.example.espot_i4;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

// API 서비스 정의
interface SafetyMapService {
    @GET("openApiService/wms/getLayerData")
    Call<ApiResponse> getYourApiData(@Query("LADF3JBO-LADF-LADF-LADF-LADF3JBOFY") String apiKey,
                                     @Query("layername") String layerName,
                                     @Query("styles") String styles);
}

// API 응답 클래스
class ApiResponse {
    // API 응답 구조에 맞게 클래스 정의
    // 예시 필드
    private String status;
    private String message;

    // Getter와 Setter
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}


// find_cs 클래스는 그대로 유지, YourApiService와 ApiResponse 클래스를 외부로 이동
