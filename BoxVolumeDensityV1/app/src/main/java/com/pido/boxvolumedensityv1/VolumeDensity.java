package com.pido.boxvolumedensityv1;

public class VolumeDensity {
    private int height, width, length;
    private float density, weight;

    public VolumeDensity(int h, int w, int l, float we){
        height = h;
        width = w;
        length = l;
        weight = we;
    }

    public int getVolume (){
        return height * width * length;
    }

    public float getDensity(){
        return weight/getVolume();
    }

    // setter if you want.
    public void setHeight(int h){
        height = h;
    }

    public void setLength(int l){
        length = l;
    }

    public void setWidth(int w){
        width = w;
    }

    public void setWeight(float we){
        weight = we;
    }
}
