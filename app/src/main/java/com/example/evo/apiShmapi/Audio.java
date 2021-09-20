package com.example.evo.apiShmapi;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Audio {
    @ColumnInfo(name = "image")
    public int image;

    @ColumnInfo(name = "name")
    public String name;

    @ColumnInfo(name = "listeners")
    public String listeners;

    @ColumnInfo(name = "length")
    public String length;
}

