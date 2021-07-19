package com.priyo.myinputbiodata;

import android.os.Parcel;
import android.os.Parcelable;

public class  Mahasiswa implements Parcelable {
    int id;
    String nim;
    String nama;
    String alamat;
    Boolean jns_ke;// true = laki2 | false = perempuan
    Double tinggi_badan;

    public Mahasiswa() {
    }

    public Mahasiswa(String nim, String nama, String alamat, Boolean jns_ke, Double tinggi_badan) {
        this.nim = nim;
        this.nama = nama;
        this.alamat = alamat;
        this.jns_ke = jns_ke;
        this.tinggi_badan = tinggi_badan;
    }

    public Mahasiswa(int id, String nim, String nama, String alamat, Boolean jns_ke, Double tinggi_badan) {
        this.id = id;
        this.nim = nim;
        this.nama = nama;
        this.alamat = alamat;
        this.jns_ke = jns_ke;
        this.tinggi_badan = tinggi_badan;
    }

    protected Mahasiswa(Parcel in) {
        id = in.readInt();
        nim = in.readString();
        nama = in.readString();
        alamat = in.readString();
        byte tmpJns_ke = in.readByte();
        jns_ke = tmpJns_ke == 0 ? null : tmpJns_ke == 1;
        if (in.readByte() == 0) {
            tinggi_badan = null;
        } else {
            tinggi_badan = in.readDouble();
        }
    }

    public static final Creator<Mahasiswa> CREATOR = new Creator<Mahasiswa>() {
        @Override
        public Mahasiswa createFromParcel(Parcel in) {
            return new Mahasiswa(in);
        }

        @Override
        public Mahasiswa[] newArray(int size) {
            return new Mahasiswa[size];
        }
    };

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNim() {
        return nim;
    }

    public void setNim(String nim) {
        this.nim = nim;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public Boolean getJns_ke() {
        return jns_ke;
    }

    public void setJns_ke(Boolean jns_ke) {
        this.jns_ke = jns_ke;
    }

    public Double getTinggi_badan() {
        return tinggi_badan;
    }

    public void setTinggi_badan(Double tinggi_badan) {
        this.tinggi_badan = tinggi_badan;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(nim);
        dest.writeString(nama);
        dest.writeString(alamat);
        dest.writeByte((byte) (jns_ke == null ? 0 : jns_ke ? 1 : 2));
        if (tinggi_badan == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeDouble(tinggi_badan);
        }
    }
}
