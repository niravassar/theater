package com.virtualpairprogrammers.theater.domain

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import java.math.BigDecimal

@Entity
data class Performance(
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    val id: Long,
    val title: String) {
}
