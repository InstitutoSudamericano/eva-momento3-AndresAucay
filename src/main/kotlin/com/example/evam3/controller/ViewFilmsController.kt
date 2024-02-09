package com.example.evam3.controller

import com.example.evam3.entity.ViewFilms
import com.example.evam3.service.CharactersService
import com.example.evam3.service.ViewFilmsService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/view-movies")
class ViewFilmsController {
    @Autowired
    lateinit var viewFilmsService: ViewFilmsService

    @GetMapping()
    fun listViewFilms ():List<ViewFilms>{
        return  viewFilmsService.listViewFilms ()
    }
}