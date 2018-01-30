package com.asseco.model;

import com.asseco.enumeration.VidLiceEnum;

import javax.persistence.*;
import java.util.Date;

/**
 * @author petar.milevski on 3/15/2017.
 */
@Entity
@Table(name = "LICA")
public class Lice extends AbstractEntity {

    @Column(name = "IME", length = 200)
    private String ime;

    @Column(name = "PREZIME", length = 200)
    private String prezime;

    @Column(name = "TELEFON", length = 200)
    private String telefon;

    @Column(name = "VID_LICE", length = 200)
    @Enumerated(EnumType.ORDINAL)
    private VidLiceEnum vidLice;

    @Column(name = "BROJ_DOGOVOR", length = 200)
    private String brojDogovor;

    @Column(name = "DATUM_DOGOVOR", length = 200)
    @Temporal(TemporalType.TIMESTAMP)
    private Date datumDogovor;

    @Column(name = "DATUM_VAZNOST_DOGOVOR", length = 200)
    @Temporal(TemporalType.TIMESTAMP)
    private Date datumVaznostDogovor;

    @Override
    public String getSelectMenuLabel() {
        return prezime + " " + ime;
    }

    @Override
    public void setClassData() {
        return;
    }

    @Override
    public void genericSet(String attributeName, Object value) throws Exception {
        this.getClass().getDeclaredField(attributeName).set(this, this.genericSetTemplate(attributeName, value));
    }

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public String getPrezime() {
        return prezime;
    }

    public void setPrezime(String prezime) {
        this.prezime = prezime;
    }

    public VidLiceEnum getVidLice() {
        return vidLice;
    }

    public void setVidLice(VidLiceEnum vidLice) {
        this.vidLice = vidLice;
    }

    public String getBrojDogovor() {
        return brojDogovor;
    }

    public void setBrojDogovor(String brojDogovor) {
        this.brojDogovor = brojDogovor;
    }

    public Date getDatumDogovor() {
        return datumDogovor;
    }

    public void setDatumDogovor(Date datumDogovor) {
        this.datumDogovor = datumDogovor;
    }

    public Date getDatumVaznostDogovor() {
        return datumVaznostDogovor;
    }

    public void setDatumVaznostDogovor(Date datumVaznostDogovor) {
        this.datumVaznostDogovor = datumVaznostDogovor;
    }

    public String getTelefon() {
        return telefon;
    }

    public void setTelefon(String telefon) {
        this.telefon = telefon;
    }

    public String getFullName() {
        return this.prezime + " " + this.ime;
    }
}
