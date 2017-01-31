package com.example.dessusdi.myfirstapp.model;

import android.util.Log;

import com.example.dessusdi.myfirstapp.R;
import com.example.dessusdi.myfirstapp.recycler_view.AqcinListAdapter;
import com.example.dessusdi.myfirstapp.tools.AqcinRequestService;

import java.net.URL;

/**
 * Created by dessusdi on 30/01/2017.
 * DESSUS Dimitri
 */
public class WaqiObject {
    private AqcinRequestService waqiService;
    private GlobalObject globalObject;
    private AqcinListAdapter adpaterList;
    private String url = "";

    public WaqiObject(String url, AqcinRequestService waqiService, AqcinListAdapter adpater) {
        this.url = url;
        this.waqiService = waqiService;
        this.adpaterList = adpater;
    }

    public void fetchData() {
        this.waqiService.sendRequestWithUrl(this.url,
        new AqcinRequestService.VolleyCallback() {
            @Override
            public void onSuccess(GlobalObject global) {
                globalObject = global;
                adpaterList.notifyDataSetChanged();
            }
        });
    }

    public String getName() {
        String name = "Loading...";
        if (this.globalObject != null) {
            name = this.globalObject.getRxs().getObs().get(0).getMsg().getCity().getName();
        }
        return name;
    }

    public String getAirQuality() {
        int airQuality = 0;
        if (this.globalObject != null) {
            airQuality = this.globalObject.getRxs().getObs().get(0).getMsg().getAqi();
        }
        return String.valueOf(airQuality);
    }

    public int getColorId() {
        int airQuality = Integer.parseInt(this.getAirQuality());
        int color = R.color.red;

        if(airQuality < 40)
            color = R.color.green;
        else if(airQuality >= 40 && airQuality < 90)
            color = R.color.orange;

        return color;
    }

    public String getGPSCoordinate() {
        String airQuality = "(not found)";
        if (this.globalObject != null) {
            airQuality = String.format("%.6f - %.6f", this.globalObject.getRxs().getObs().get(0).getMsg().getCity().getGeo().get(0), this.globalObject.getRxs().getObs().get(0).getMsg().getCity().getGeo().get(1));
        }
        return airQuality;
    }

    public String getMinTemp() {
        String minTemp = "0";
        if (this.globalObject != null) {
            if(this.globalObject.getRxs().getObs().get(0).getMsg().getForecast().getAqi().size() > 0) {
                minTemp = String.format("Min : %d°C", this.globalObject.getRxs().getObs().get(0).getMsg().getForecast().getAqi().get(0).getV().get(0));
            }
        }
        return minTemp;
    }

    public String getMaxTemp() {
        String maxTemp = "0";
        if (this.globalObject != null) {
            if(this.globalObject.getRxs().getObs().get(0).getMsg().getForecast().getAqi().size() > 0) {
                maxTemp = String.format("Max : %d°C", this.globalObject.getRxs().getObs().get(0).getMsg().getForecast().getAqi().get(0).getV().get(1));
            }
        }
        return maxTemp;
    }

    public String getId() {
        return this.globalObject.getRxs().getObs().get(0).getMsg().getCity().getId();
    }
}
