package com.pabloat.apiandroid.data


import com.pabloat.apiandroid.data.local.Categoria
import com.pabloat.apiandroid.data.local.VideojuegoDatasource
import com.pabloat.apiandroid.data.local.Producto
import com.pabloat.apiandroid.data.remote.RemoteMercadonaDataSource
import kotlinx.coroutines.flow.Flow

class MercadonaRepository(
    private val localds: VideojuegoDatasource,
    private val remoteds: RemoteMercadonaDataSource
) {
    suspend fun getRemoteCategoria(
        id: Int, name: String, products: List<Producto>
    ): List<Categoria> {
        //Tomamos el resultado y lo convertimos en una entidad local
     //   val forecasts: Categoria = Categoria(0, "", listOf(Producto(0, "", 0, "", 0)))

        //Primera prueba, compartimos datos mezclados
        //val forecasts = remoteds.getWeather(latitude, longitude, daily, timezone).toLocalEntity()
        //Log.d("REPO", forecasts.toString())
        //return listOf(forecasts)

        val forecastsList = remoteds.getCategorias(id, name, products).toList()
        return forecastsList
    }

    suspend fun getLocalCategoria(): Flow<List<Categoria>> {
        return localds.getAllCategorias()
    }

    suspend fun insertLocalCategoria(categoria: Categoria){
        localds.insertCategoria(categoria)
    }

    suspend fun getLocalProducto(): Flow<List<Producto>> {
        return localds.getAllVideojuego()
    }

    suspend fun insertLocalProducto(producto: Producto){
        localds.insertVideojuego(producto)
    }

}
