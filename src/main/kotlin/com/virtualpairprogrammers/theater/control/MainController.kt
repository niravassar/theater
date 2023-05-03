package com.virtualpairprogrammers.theater.control

import com.virtualpairprogrammers.theater.data.PerformanceRepository
import com.virtualpairprogrammers.theater.data.SeatRepository
import com.virtualpairprogrammers.theater.domain.Booking
import com.virtualpairprogrammers.theater.domain.Performance
import com.virtualpairprogrammers.theater.domain.Seat
import com.virtualpairprogrammers.theater.services.BookingService
import com.virtualpairprogrammers.theater.services.TheaterService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.servlet.ModelAndView

@Controller
class MainController {

    @Autowired
    lateinit var theaterService : TheaterService

    @Autowired
    lateinit var bookingService: BookingService

    @Autowired
    lateinit var seatRepository: SeatRepository

    @Autowired
    lateinit var performanceRepository: PerformanceRepository

    @RequestMapping("helloWorld")
    fun helloWorld(): ModelAndView {
        return ModelAndView("helloWorld")
    }

    @RequestMapping("/")
    fun homePage(): ModelAndView {
        val model = mapOf( "bean" to CheckAvailabilityBackBean(),
                            "performances" to performanceRepository.findAll(),
                            "seatNums" to 1..36,
                            "seatRows" to 'A'..'O')

       return ModelAndView("seatBooking", model)
    }

    @RequestMapping("checkAvailability", method = arrayOf(RequestMethod.POST))
    fun checkAvailability(bean : CheckAvailabilityBackBean) : ModelAndView {
        val selectedSeat : Seat = bookingService.findSeat( bean.selectedSeatNum, bean.selectedSeatRow)!!
        var selectedPerformance = performanceRepository.findById(bean.selectedPerformance!!).get()
        bean.seat = selectedSeat
        bean.performance = selectedPerformance

        val available = bookingService.isSeatFree(selectedSeat, selectedPerformance)
        bean.available = available
        return ModelAndView("seatBooking", "bean", bean)
    }

    @RequestMapping("bootstrap")
    fun createInitialData() : ModelAndView {
        // create data save to database
        val seats = theaterService.seats
        seatRepository.saveAll(seats)
        return homePage()
    }

}

class CheckAvailabilityBackBean() {
    var selectedSeatNum : Int = 1
    var selectedSeatRow : Char = 'A'
    var selectedPerformance: Long? = null
    var result : String = ""

    var available : Boolean? = null
    var seat : Seat? = null
    var performance: Performance? = null
    var booking : Booking? = null
}