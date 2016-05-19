/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.deivydas.voroneckis.rest;

import com.deivydas.voroneckis.rest.Geometry;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 *
 * @author vdeiv
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Result {

    public Result() {

    }

    private Geometry geometry;

    public Geometry getGeometry() {
        return geometry;
    }

    public void setGeometry(Geometry geometry) {
        this.geometry = geometry;
    }
}
