package com.example.evam3.entity

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table

@Entity
@Table(name="film_view")
class ViewFilms {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(updatable = false)
    var id: Long? = null
    @Column(name="film_id")
    var filmId: Long? = null
    @Column(name="film_title")
    var filmTittle:String? = null
    @Column(name="film_director")
    var filmDirector:String? = null
    @Column(name="scene_id")
    var sceneId: Long? = null
    @Column(name="scene_description")
    var sceneDescription:String? = null

}