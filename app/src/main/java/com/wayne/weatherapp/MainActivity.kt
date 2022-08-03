package com.wayne.weatherapp


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import org.w3c.dom.Text


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val queue = Volley.newRequestQueue(this)
        val weatherLink = "https://api.weatherapi.com/v1/current.json?key=f269d6ac5ca5477896375924220208&q=Mombasa"
        val weatherRequest = JsonObjectRequest(Request.Method.GET,weatherLink,null,
            {
                firstJsonObject ->
                val mainObject = firstJsonObject.getJSONObject("location")
                val city = mainObject.get("name")
                val country = mainObject.get("country")
                val updateTime = firstJsonObject.getJSONObject("current").get("last_updated")
                val condition = firstJsonObject.getJSONObject("current").getJSONObject("condition").get("text")
                val temperature = firstJsonObject.getJSONObject("current").get("temp_c")
                val cloud = firstJsonObject.getJSONObject("current").get("cloud")
                val wind = firstJsonObject.getJSONObject("current").get("wind_dir")
                val pressure = firstJsonObject.getJSONObject("current").get("pressure_mb")
                val humidity = firstJsonObject.getJSONObject("current").get("humidity")
                //Log.d("WEATHER","onCreate: City is $city")
                findViewById<TextView>(R.id.address).text = city.toString().trim()
                findViewById<TextView>(R.id.country).text = country.toString().trim()
                findViewById<TextView>(R.id.updated_at).text = updateTime.toString()
                findViewById<TextView>(R.id.status).text = condition.toString()
                findViewById<TextView>(R.id.temp).text = temperature.toString()
                findViewById<TextView>(R.id.cloud).text = cloud.toString()
                findViewById<TextView>(R.id.wind).text = wind.toString()
                findViewById<TextView>(R.id.pressure).text = pressure.toString()
                findViewById<TextView>(R.id.humidity).text = humidity.toString()
            },
            {
                error->
                Log.d("WEATHER","onCreate: Error while fetching weather data",error)
            })
        queue.add(weatherRequest)








    }
}