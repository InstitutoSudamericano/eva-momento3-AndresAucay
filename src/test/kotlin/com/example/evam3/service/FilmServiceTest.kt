package com.example.evam3.service

import com.example.evam3.entity.Film
import com.example.evam3.repository.FilmRepository
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito
import org.springframework.boot.test.context.SpringBootTest
import java.io.File

@SpringBootTest
internal class FilmServiceTest {
    @InjectMocks
    lateinit var filmService: FilmService //clae que se va a probar

    @Mock   //objeto simulado
    lateinit var filmRepository: FilmRepository

    val jsonStringFilm = File("./src/test/resources/film.json").readText(Charsets.UTF_8)
    val filmMock = Gson().fromJson(jsonStringFilm, Film::class.java){}


    @Test
    fun savefilmCorrect(){
        Mockito.`when`(filmRepository.save(Mockito.any(Film::class.java))).thenReturn(filmMock)
        val response = filmService.save(filmMock)
        Assertions.assertEquals(response.id, filmMock.id)
    }


    @Test
    fun savefilmWhenTitleIsBlank(){

        Assertions.assertThrows(Exception::class.java) {
            filmMock.apply { title=" "}
            Mockito.`when`(filmRepository.save(Mockito.any(Film::class.java))).thenReturn(filmMock)
            filmService.save(filmMock)

        }
    }