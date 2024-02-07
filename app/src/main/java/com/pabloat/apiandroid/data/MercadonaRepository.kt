package com.pabloat.apiandroid.data

import com.antpaniagua.basicretrofit.data.remote.RemoteMercadonaDataSource
import com.pabloat.apiandroid.data.local.Categoria
import com.pabloat.apiandroid.data.local.MercadonaDatasource
import com.pabloat.apiandroid.data.local.Producto

class MercadonaRepository(
    private val localds: MercadonaDatasource,
    private val remoteds: RemoteMercadonaDataSource
) {
    suspend fun getRemoteCategoria(
        id: Int,
        name: String,
        products: List<Producto>
    ): List<Producto> {
        //Tomamos el resultado y lo convertimos en una entidad local
        val forecasts: Categoria = Categoria(0, "", listOf(Producto(0, "", 0, "", 0)))

        //Primera prueba, compartimos datos mezclados
        //val forecasts = remoteds.getWeather(latitude, longitude, daily, timezone).toLocalEntity()
        //Log.d("REPO", forecasts.toString())
        //return listOf(forecasts)

        val forecastsList = remoteds.getCategorias(id, name, products).toLocalList()
        return forecastsList
    }

}
