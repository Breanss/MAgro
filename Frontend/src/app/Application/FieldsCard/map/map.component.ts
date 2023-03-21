import {Component, AfterViewInit} from '@angular/core';
import "@vaadin/vaadin-combo-box";
import * as L from 'leaflet';
// @ts-ignore
import wellknown from "wellknown/wellknown.js";


@Component({
  selector: 'app-map',
  templateUrl: './map.component.html',
  styleUrls: ['./map.component.css']
})
export class MapComponent implements AfterViewInit {
  map?: L.Map;
  private geojsonLayer: any = undefined;

  private initMap(): void {

    this.map = L.map('map', {
      center: [51.8282, 19.5795],
      zoom: 6
    });

    const tiles = L.tileLayer('https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png', {
      maxZoom: 18,
      minZoom: 3,
      attribution: '&copy; <a href="http://www.openstreetmap.org/copyright">OpenStreetMap</a>'
    });

    tiles.addTo(this.map);
  }


  constructor() {
  }

  ngAfterViewInit(): void {
    this.initMap();
  }

  wktToGeoJSON(wkt: string): GeoJSON.GeometryObject {
    return wellknown(wkt);
  }

  async getParcelById(teryt: string = "") {
    if (!this.geojsonLayer) {
      this.geojsonLayer = L.geoJSON(undefined, {
        onEachFeature: function (feature, layer) {
          layer.bindPopup(feature.properties.popupContent)
        }
      }).addTo(this.map!);
    }

    const url = `https://uldk.gugik.gov.pl/?request=GetParcelById&result=geom_wkt&id=${teryt}&srid=4326`;
    const text = await fetch(url).then((r) => r.text());
    const result = text.substring(1).trim();
    const wkt = (result.includes(";") ? result.split(";")[1] : result)
      ?.trim()
      .split("\n")[0];

    const wktJSON = this.wktToGeoJSON(wkt);
    const dataJSON = {
      type: "FeatureCollection",
      features: [
        {
          type: "Feature",
          geometry: wktJSON,
          properties: {
            popupContent: `<b>Województwo</b>: lubelskie <br>
            `
          },
          id: 1,
        },
      ],
    };
    this.geojsonLayer.clearLayers();
    this.geojsonLayer.addData(dataJSON);
    this.map?.fitBounds(this.geojsonLayer.getBounds(), {})
  }


}
