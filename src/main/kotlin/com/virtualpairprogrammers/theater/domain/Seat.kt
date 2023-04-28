package com.virtualpairprogrammers.theater.domain

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import java.math.BigDecimal

@Entity
data class Seat(
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    val id: Long,
    val row: Char,
    val num: Int,
    val price: BigDecimal,
    val description: String) {
    override fun toString(): String = "Seat $row-$num $$price ($description)"
}
