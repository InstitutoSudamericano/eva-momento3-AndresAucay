package com.example.evam3.service

import com.example.evam3.entity.Film
import com.example.evam3.entity.Scene
import com.example.evam3.repository.FilmRepository
import com.example.evam3.repository.SceneRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException

@Service
class SceneService {
    @Autowired
    lateinit var filmRepository: FilmRepository
    @Autowired
    lateinit var sceneRepository: SceneRepository

    fun list ():List<Scene>{
        return sceneRepository.findAll()
    }

    fun save(scene: Scene): Scene {
        try {

            scene.description?.takeIf { it.trim().isNotEmpty() }
                ?: throw Exception("La descripción no debe estar vacía")


            val film = filmRepository.findById(scene.filmId)
                ?: throw Exception("ID del film no encontrado")



            if (scene.budget!! > film.investment!!) {
                throw Exception("El presupuesto de la escena excede la inversión de la película")
            }

            // Verificar si el total de minutos excede
            val films = filmRepository.findById(scene.filmId)
            val costTotal = sceneRepository.sumMinutesByFilmId(scene.filmId!!) ?: 0

            if (films != null) {
                if (costTotal + (scene.minutes ?: 0) > (film.duration ?: 0)) {
                    throw Exception("El total de minutos excede la duración de la película")
                }
            }

            return sceneRepository.save(scene)
        } catch (ex: Exception) {
            // Manejar cualquier excepción
            throw ResponseStatusException(
                HttpStatus.BAD_REQUEST, ex.message, ex
            )
        }
    }

    fun update(scene: Scene): Scene{
        try {
            sceneRepository.findById(scene.id)
                ?: throw Exception("ID no existe")

            return sceneRepository.save(scene)
        }
        catch (ex:Exception){
            throw ResponseStatusException(HttpStatus.NOT_FOUND,ex.message)
        }
    }
    fun delete (id: Long?):Boolean?{
        try{
            val response = sceneRepository.findById(id)
                ?: throw Exception("ID no existe")
            sceneRepository.deleteById(id!!)
            return true
        }
        catch (ex:Exception){
            throw ResponseStatusException(HttpStatus.NOT_FOUND,ex.message)
        }
    }
}
