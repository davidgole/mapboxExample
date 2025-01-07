package com.example.mapbox_example

import android.content.Intent
import android.graphics.BitmapFactory
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.mapbox.geojson.Point
import com.mapbox.maps.MapView
import com.mapbox.maps.Style
import com.mapbox.maps.plugin.annotation.annotations
import com.mapbox.maps.plugin.annotation.generated.PointAnnotationManager
import com.mapbox.maps.plugin.annotation.generated.PointAnnotationOptions
import com.mapbox.maps.plugin.annotation.generated.createPointAnnotationManager

class DarkmapActivity : AppCompatActivity() {
    private lateinit var mapView: MapView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_darkmap)

        mapView = findViewById(R.id.mapView)

        // Naloži slog zemljevida in dodaj marker
        mapView.getMapboxMap().loadStyleUri(Style.MAPBOX_STREETS) { style ->
            // Dodamo marker šele po tem, ko je stil zemljevida uspešno naložen
            addMarker()
        }

        // Logika za preusmeritev nazaj na MainActivity
        val buttonBack = findViewById<Button>(R.id.button_back)
        buttonBack.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }

    private fun addMarker() {
        val annotationApi = mapView.annotations
        val pointAnnotationManager: PointAnnotationManager =
            annotationApi.createPointAnnotationManager()
        val point = Point.fromLngLat(-98.0, 39.5)
        val bitmap = BitmapFactory.decodeResource(resources, R.drawable.red_marker)
        val pointAnnotationOptions = PointAnnotationOptions()
            .withPoint(point)
            .withIconImage(bitmap)
            .withIconSize(0.3)
        pointAnnotationManager.create(pointAnnotationOptions)
    }

    override fun onStart() {
        super.onStart()
        mapView.onStart()
    }

    override fun onStop() {
        super.onStop()
        mapView.onStop()
    }

    override fun onDestroy() {
        super.onDestroy()
        mapView.onDestroy()
    }
}
