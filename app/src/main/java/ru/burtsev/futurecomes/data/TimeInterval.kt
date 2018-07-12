package ru.burtsev.futurecomes.data

import com.google.gson.annotations.SerializedName

data class TimeInterval(
        @SerializedName("interval") val interval: LinkedHashMap<String, Int>
)