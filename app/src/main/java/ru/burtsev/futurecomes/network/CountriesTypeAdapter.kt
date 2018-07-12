//package ru.burtsev.stations.network
//
//import com.google.gson.GsonBuilder
//import com.google.gson.JsonDeserializationContext
//import com.google.gson.JsonDeserializer
//import com.google.gson.JsonElement
//import ru.burtsev.stations.data.City
//import ru.burtsev.stations.data.Countries
//import ru.burtsev.stations.data.Country
//import ru.burtsev.stations.data.Stations
//import java.lang.reflect.Type
//
///**
// * Конвертер который преобразует json со списком станций в более удобную для работу структуру.
// * Группируя странции по названию страны
// */
//class CountriesTypeAdapter : JsonDeserializer<Countries> {
//
//
//    override fun deserialize(json: JsonElement, typeOfT: Type, context: JsonDeserializationContext): Countries {
//        val listCountiesTo = mutableListOf<Country>()
//        val listCountiesFrom = mutableListOf<Country>()
//        val gson = GsonBuilder().create()
//        val (citiesTo, citiesFrom) = gson.fromJson(json, Stations::class.java)
//        citiesTo?.let {
//            val cities = it.groupBy(City::countryTitle)
//            for ((key, value) in cities) {
//                val country = Country(key ?: "", value)
//                listCountiesTo.add(country)
//            }
//        }
//
//        citiesFrom?.let {
//            val cities = it.groupBy(City::countryTitle)
//            for ((key, value) in cities) {
//                val country = Country(key ?: "", value)
//                listCountiesFrom.add(country)
//            }
//        }
//
//
//        return Countries(listCountiesTo, listCountiesFrom)
//    }
//
//}
