package com.virtualpairprogrammers.theater.services

import com.virtualpairprogrammers.theater.data.BookingRepository
import com.virtualpairprogrammers.theater.data.PerformanceRepository
import com.virtualpairprogrammers.theater.data.SeatRepository
import com.virtualpairprogrammers.theater.domain.Performance
import com.virtualpairprogrammers.theater.domain.Seat
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class BookingService {

    @Autowired
    lateinit var bookingRepository: BookingRepository

    @Autowired
    lateinit var seatRepository: SeatRepository

    fun isSeatFree(seat : Seat, performance: Performance) : Boolean {
        var bookings = bookingRepository.findAll()
        var matchedBookings = bookings.filter { it.seat == seat && it.performance == performance}
        return matchedBookings.size == 0
    }

    fun findSeat(seatNum : Int, seatRow: Char) : Seat?  {
        var allSeats = seatRepository.findAll()
        var foundSeats = allSeats.filter {  it.num == seatNum && it.row == seatRow }
        return foundSeats.firstOrNull()
    }
}