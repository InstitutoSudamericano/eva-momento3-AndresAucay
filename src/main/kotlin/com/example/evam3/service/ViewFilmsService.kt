package com.example.evam3.service

import com.example.evam3.entity.ViewFilms
import com.example.evam3.repository.ViewFilmsRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class ViewFilmsService {

    @Autowired
    lateinit var viewFilmsRepository: ViewFilmsRepository

    //llamar a findall de nuevo repositorio
    fun listViewFilms(): List<ViewFilms> {
        return viewFilmsRepository.findAll()
    }
}