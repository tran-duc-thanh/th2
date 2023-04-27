package com.example.th2.model;

import java.io.Serializable;

public class Cat implements Serializable {
    private int id;
    private String diaChi;
    private String dichVu;
    private double dienTich;
    private double gia;
    private Integer soNguoi;

    public Cat() {
    }

    public Cat(int id, String diaChi, String dichVu, double dienTich, double gia, Integer soNguoi) {
        this.id = id;
        this.diaChi = diaChi;
        this.dichVu = dichVu;
        this.dienTich = dienTich;
        this.gia = gia;
        this.soNguoi = soNguoi;
    }

    public Cat(String diaChi, String dichVu, double dienTich, double gia, Integer soNguoi) {
        this.diaChi = diaChi;
        this.dichVu = dichVu;
        this.dienTich = dienTich;
        this.gia = gia;
        this.soNguoi = soNguoi;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public String getDichVu() {
        return dichVu;
    }

    public void setDichVu(String dichVu) {
        this.dichVu = dichVu;
    }

    public double getDienTich() {
        return dienTich;
    }

    public void setDienTich(double dienTich) {
        this.dienTich = dienTich;
    }

    public double getGia() {
        return gia;
    }

    public void setGia(double gia) {
        this.gia = gia;
    }

    public Integer getSoNguoi() {
        return soNguoi;
    }

    public void setSoNguoi(Integer soNguoi) {
        this.soNguoi = soNguoi;
    }
}
