package br.com.zup.marvel.domain.repository

import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.UserProfileChangeRequest
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class AuthenticatonRepository {

    private val auth: FirebaseAuth = Firebase.auth

    fun updateUser(name: String): Task<Void>? {
        val profile = UserProfileChangeRequest.Builder().setDisplayName(name).build()
        return auth.currentUser?.updateProfile(profile)
    }
    fun getUserName(): String = auth.currentUser?.displayName.toString()

    fun register(email: String, password: String) : Task<AuthResult> {
        return auth.createUserWithEmailAndPassword(email, password)
    }
    fun login(email: String, password: String) : Task<AuthResult> {
        return auth.signInWithEmailAndPassword(email, password)
    }
    fun logout() {
        auth.signOut()
    }
}