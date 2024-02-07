package com.pabloat.apiandroid.data.local

import android.content.Context
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.stateIn

class MercadonaDatasource(applicationContext: Context) {
    private val db: AppDataBase = AppDataBase.getDatabase(applicationContext)
    private val productoDao: LocalProductoDao = db.productoDao()
    private val categoriaDao: LocalCategoriaDao = db.categoriaDao()

    /**
     * CATEGORÍA
     */
    suspend fun getAllCategorias(): Flow<List<Categoria>> {
        return categoriaDao.getAll().stateIn(GlobalScope)
    }
    suspend fun insertCategoria(categoria: Categoria) {
        categoriaDao.insert(categoria)
    }
    suspend fun updateCategoria(categoria: Categoria) {
        categoriaDao.update(categoria)
    }
    suspend fun deleteCategoria(categoria: Categoria) {
        categoriaDao.delete(categoria)
    }

    /**
     * PRODUCTO
     */

    suspend fun getAllProductos(): Flow<List<Producto>> {
        return productoDao.getAll()
    }
    suspend fun insertProducto(producto: Producto) {
        productoDao.insert(producto)
    }
    suspend fun updateProducto(producto: Producto) {
        productoDao.update(producto)
    }
    suspend fun deleteProducto(producto: Producto) {
        productoDao.delete(producto)
    }
}
