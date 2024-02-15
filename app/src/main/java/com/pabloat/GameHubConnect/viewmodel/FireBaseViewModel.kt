package com.pabloat.GameHubConnect.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase
import com.pabloat.GameHubConnect.data.local.User
import kotlinx.coroutines.launch

class FireBaseViewModel:ViewModel() {
    private val auth:FirebaseAuth = Firebase.auth
    private val _loading = MutableLiveData(false)

    fun SingInWithEmailAndPassword(email:String , password:String, home:()->Unit)
    =viewModelScope.launch {
        try {
            auth.signInWithEmailAndPassword(email,password).addOnCompleteListener{
                task->
                if (task.isSuccessful){
                    Log.d("MV","Logeado")
                    home()
                }else{
                    Log.d("MV",task.result.toString())
                }
            }
        }catch (ex:Exception){
            Log.d("MV","${ex.message}")
        }
    }

    fun createUserWithEmailAndPassword(
        email: String,
        password: String,
        home: () -> Unit
    ){
        if(_loading.value==false){
            _loading.value = true
            auth.createUserWithEmailAndPassword(email,password)
                .addOnCompleteListener{task->
                    if(task.isSuccessful){
                        val displayName = task.result.user?.email?.split("@")?.get(0)
                        createUser(displayName)
                        home()
                    }else{
                        Log.d("MV",task.result.toString())
                    }
                    _loading.value=false
                }
        }
    }

    private fun createUser(displayName: String?) {
        val userId = auth.currentUser?.uid
        //val user = mutableMapOf<String,Any>()

        val user = User(
            userId = userId.toString(),
            displayName = displayName.toString(),
            avatarUrl = "",
            quote = "chupamela",
            rol = "user",
            id = null
        ).toMap()

        //user["user_id"] = userId.toString()
        //user["display_name"] = displayName.toString()
        FirebaseFirestore.getInstance().collection("users")
            .add(user)
            .addOnSuccessListener {
                Log.d("MV","Creado ${it.id}")
            }.addOnFailureListener{
                Log.d("MV","Mal ${it}")

            }
    }
}