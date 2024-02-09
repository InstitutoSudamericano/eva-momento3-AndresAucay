package com.example.evam3.repository

import com.example.evam3.entity.Film
import com.example.evam3.entity.ViewFilms
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

    @Repository
    interface ViewFilmsRepository : JpaRepository<ViewFilms, Long?> {


}