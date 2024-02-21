package com.pabloat.GameHubConnect.viewmodel

import android.content.Context
import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase
import com.pabloat.GameHubConnect.data.local.User
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class FireBaseViewModel:ViewModel() {
    private val auth:FirebaseAuth = Firebase.auth
    private val _loading = MutableLiveData(false)
    val errorMessage: MutableState<String> = mutableStateOf("")

    private val _storedError: MutableStateFlow<String> = MutableStateFlow("")
    val storedError: StateFlow<String> = _storedError.asStateFlow()

    fun storeError(value: String) {
        _storedError.value = value
    }
    fun getStoredError(): String {
        return _storedError.value
    }

    fun SingInWithEmailAndPassword(context: Context, email: String, password: String, home: () -> Unit, fail: () -> Unit) = viewModelScope.launch {
        val preferencesUtils = PreferenceUtils()
        try {
            auth.signInWithEmailAndPassword(email, password).addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    Log.d("MV", "Logueado")
                    if (preferencesUtils.getRememberMeState(context)){
                        preferencesUtils.saveUserCredentials(email,password,context)
                    }
                    home()
                } else {
                    Log.d("MV", "Inicio de sesión fallido")
                    fail()
                }
            }
        } catch (ex: Exception) {
            _storedError.value = ex.message.toString()
            Log.e("MV", "Excepción en SingInWithEmailAndPassword: ${ex.message}", ex)
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
            quote = "",
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

    private val _storedString: MutableStateFlow<String> = MutableStateFlow("")
    val storedString: StateFlow<String> = _storedString.asStateFlow()

    fun storeEmail(value: String) {
        _storedString.value = value
    }
    fun getStoredEmail(): String {
        return _storedString.value
    }
    fun getCurrentUserRoleFromFirestore(userId: String, onSuccess: (String?) -> Unit, onFailure: (Exception) -> Unit) {
        val db = FirebaseFirestore.getInstance()
        val userRef = db.collection("users").document(userId)

        userRef.get()
            .addOnSuccessListener { document ->
                if (document != null && document.exists()) {
                    val rol = document.getString("rol")
                    onSuccess(rol)
                } else {
                    onFailure(Exception("El documento del usuario no existe"))
                }
            }
            .addOnFailureListener { exception ->
                onFailure(exception)
            }
    }
    fun getCurrentUserName(): String? {
        val currentUser = auth.currentUser
        return currentUser?.displayName
    }
}