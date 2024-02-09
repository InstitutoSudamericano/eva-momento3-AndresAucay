package com.example.evam3.service

import com.example.evam3.entity.Characters

import com.example.evam3.repository.CharactersRepository
import com.example.evam3.repository.SceneRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException

@Service
class CharactersService {
    @Autowired
    lateinit var sceneRepository: SceneRepository
    @Autowired
    lateinit var charactersRepository: CharactersRepository

    fun list ():List<Characters>{
        return charactersRepository.findAll()
    }

    fun save(characters: Characters): Characters {
        try {
            sceneRepository.findById(characters.sceneId)
                ?: throw Exception("Id de la escena no encontrado")

            characters.namePersonaje?.takeIf { it.trim().isNotEmpty() }
                ?: throw Exception("nombre del personaje  no debe ser vacio")

            characters.description?.takeIf { it.trim().isNotEmpty() }
                ?: throw Exception("Descripcion no debe ser vacio")

            // Calcular el costo total de los personajes
            val scene = sceneRepository.findById(characters.sceneId)
            val currentTotalCost = charactersRepository.sumCostBySceneId(characters.sceneId!!) ?: 0.0
            /*
            if (scene != null) {
                if (currentTotalCost + (characters.cost ?: 0.0) > (scene.budget ?: 0.0)) {
                    throw Exception("El costo total de los personajes excede el presupuesto de la escena")
                }
            }*/

            characters.gender?.takeIf { it.trim().isNotEmpty() }
                ?: throw Exception("Bando no debe ser vacio")
            return charactersRepository.save(characters)
        } catch (ex: Exception) {
            throw ResponseStatusException(HttpStatus.BAD_REQUEST, ex.message)
        }
    }
    fun update(characters: Characters): Characters{
        try {
            charactersRepository.findById(characters.id)
                ?: throw Exception("ID no existe")

            return charactersRepository.save(characters)
        }
        catch (ex:Exception){
            throw ResponseStatusException(HttpStatus.NOT_FOUND,ex.message)
        }
    }
    fun delete (id: Long?):Boolean?{
        try{
            val response = charactersRepository.findById(id)
                ?: throw Exception("ID no existe")
            charactersRepository.deleteById(id!!)
            return true
        }
        catch (ex:Exception){
            throw ResponseStatusException(HttpStatus.NOT_FOUND,ex.message)
        }
    }

}
