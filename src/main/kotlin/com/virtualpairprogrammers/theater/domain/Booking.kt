package com.virtualpairprogrammers.theater.domain

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.ManyToOne
import java.math.BigDecimal

@Entity
data class Booking(
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    val id: Long,
    val customerName : String) {

    @ManyToOne
    lateinit var seat: Seat
    @ManyToOne
    lateinit var performance: Performance
}
