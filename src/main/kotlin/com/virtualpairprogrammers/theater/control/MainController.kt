package com.virtualpairprogrammers.theater.control

import com.virtualpairprogrammers.theater.data.SeatRepository
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

    @RequestMapping("helloWorld")
    fun helloWorld(): ModelAndView {
        return ModelAndView("helloWorld")
    }

    @RequestMapping("/")
    fun homePage(): ModelAndView = ModelAndView("seatBooking", "bean", CheckAvailabilityBackBean())

    @RequestMapping("checkAvailability", method = arrayOf(RequestMethod.POST))
    fun checkAvailability(bean : CheckAvailabilityBackBean) : ModelAndView {
        val selectedSeat = theaterService.find(bean.selectedSeatNum, bean.selectedSeatRow)
        val available = bookingService.isSeatFree(selectedSeat)
        bean.result = "Seat check is row: ${ bean.selectedSeatRow}, seat ${bean.selectedSeatNum}. It exists and priced at: $selectedSeat. Availability: $available"
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
    val seatNums = 1..36
    val seatRows = 'A'..'O'
    var selectedSeatNum : Int = 1
    var selectedSeatRow : Char = 'A'
    var result : String = ""
}