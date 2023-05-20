package org.d3if3142.assesment2_6706213142.model

import org.d3if3142.assesment2_6706213142.db.HasilEntity

fun HasilEntity.hitungsisi(): HasilLuas {
    val hasil = sisi * sisi

    return HasilLuas(hasil)
}