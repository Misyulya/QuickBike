package com.example.dmitry.quickbike.entity;

import static com.example.dmitry.quickbike.entity.ConstantsKt.ID;
import static com.example.dmitry.quickbike.entity.ConstantsKt.SHOP_ID;

import java.util.List;

import android.arch.persistence.room.Embedded;
import android.arch.persistence.room.Relation;

public class ShopWithBicycles {
    @Embedded
    public Shop shop;
    @Relation(entity = Bicycle.class, parentColumn = ID, entityColumn = SHOP_ID)
    public List<Bicycle> bicycles;
}
